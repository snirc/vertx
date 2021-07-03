package snir.code.db;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.BulkOperation;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.MongoClientDeleteResult;
import io.vertx.ext.mongo.MongoClientUpdateResult;
import io.vertx.ext.mongo.UpdateOptions;
import io.vertx.ext.web.RoutingContext;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.mongo.MongoClient;
import rx.Single;
import snir.code.config.DbConfig;
import snir.code.config.MessageConfig;
import snir.code.utils.MessageLog;

/**
 * 
 * @author snirco@gmail.com
 *
 */
public class MongoLayer {

	public static final String CAUSE = "cause";
	public static final String RESULT = "result";
	public static final String DOCUMENTS_REMOVED = "Number Of Removed Documents";
	public static final String DOCUMENTS_CREATED = "Created Documents";
	public static final String SUCCEEDED = "succeeded";
	public static final String DOCUMENT_UPDATED = "Document Updated";

	public static Vertx vertx;
//    public static io.vertx.core.Vertx vertx; 
	private static Logger logger = LoggerFactory.getLogger(MongoLayer.class);
	private JsonObject configEntries;

	private static Map<String, MongoLayer> instanceMap = new HashMap<>();

	private MongoClient mongoClient;
	public static String entries = new String();

	private static MongoDependencies mongoDependencies = new MongoDependencies();

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	private MongoLayer(String mongoDatabase) {
		configEntries = new JsonObject(entries);
		configEntries.put("db_name", mongoDatabase);
		mongoClient = MongoClient.createNonShared(vertx, configEntries);
	}

	public static void init(Vertx vertx) {
		System.setProperty("org.mongodb.async.type", "netty");
		if (MongoLayer.vertx == null) {
			MongoLayer.vertx = vertx;
		}
		DbConfig.getMongoConfig(vertx, MongoLayer::setMongoConfig);
	
	}

	private static void setMongoConfig(JsonObject entries) {
		MongoLayer.entries = entries.encode();
	}

	public static void initMongoDependencies() {
		if (entries.isEmpty()) {
			vertx.setPeriodic(100, new Handler<Long>() {
				@Override
				public void handle(Long aLong) {
					if (!entries.isEmpty()) {
						vertx.cancelTimer(aLong);
						mongoDependencies.executeAll();
					}
				}
			});
		} else {
			mongoDependencies.executeAll();
		}
	}

	public static void addMongoDependency(String dbName, Handler<MongoLayer> handler) {
		mongoDependencies.add(dbName, handler);
	}

	public static void getInstance(String mongoDatabase, Handler<MongoLayer> handler) {

		if (entries.isEmpty()) {
			vertx.setPeriodic(100, new Handler<Long>() {
				@Override
				public void handle(Long aLong) {
					if (!entries.isEmpty()) {
						vertx.cancelTimer(aLong);
						handler.handle(getInstance(mongoDatabase));
					}
				}
			});
		} else {
			handler.handle(getInstance(mongoDatabase));
		}
	}

	public static MongoLayer getInstance(String mongoDatabase) {

		MongoLayer mongoLayer = instanceMap.get(mongoDatabase);

		if (mongoLayer == null) {
			String hostname = "Unknown";
			try {
				InetAddress addr;
				addr = InetAddress.getLocalHost();
				hostname = addr.getHostName();
			} catch (Exception ex) {
				logger.error("Hostname can not be resolved: " + ex.getMessage());
			}
			MessageLog.HOST_NAME = hostname;
			synchronized (entries) {
				mongoLayer = new MongoLayer(mongoDatabase);
				instanceMap.put(mongoDatabase, mongoLayer);
			}
		}

		return mongoLayer;

	}

	public void createCollection(String collection) {
		this.mongoClient.createCollection(collection, this::logCreateResult);
	}

	private void logCreateResult(AsyncResult<Void> voidAsyncResult) {

		if (voidAsyncResult.succeeded()) {
			// logger.info("collection created "+voidAsyncResult.result());
		} else {
			logger.error("failed to create collection " + voidAsyncResult.cause().getMessage());
		}

	}

	public void findDocAndMoveCollection(String collection, String collectionToMove, JsonObject query,
			Function<List<JsonObject>, Void> func, RoutingContext routingContext) {

		mongoClient.find(collection, query, res -> {
			System.out.println(res.result().get(0));
			if (res.succeeded()) {
				mongoClient.insert(collectionToMove, res.result().get(0), resInsert -> {
					if (resInsert.succeeded()) {
						System.out.println(res.result().get(0));
						mongoClient.removeDocument(collection, query, this::documentRemoved);
						MessageLog.sendMessageObject(routingContext,
								MessageConfig.MessageKey.FIND_DOC_AND_MOVE_COLLECTION, new JsonObject().put("ok", "ok"),
								logger);
					}
				});
				func.apply(res.result());
			} else
				func.apply(new ArrayList<>());

		});
	}

	public void insertingDocuments(String collectionName, JsonObject jsonDoc) {
		try {
			mongoClient.insert(collectionName, jsonDoc, this::logInsertResult);
			// logger.info("saved document");
		} catch (Exception e) {
			logger.error("failed to save document");
		}

	}

	public void insertingDocuments(String collectionName, JsonObject jsonDoc,
			Handler<AsyncResult<String>> resultHandler) {
		try {
			mongoClient.insert(collectionName, jsonDoc, resultHandler);
			// logger.info("saved document");
		} catch (Exception e) {
			logger.error("failed to save document");
		}

	}

	public void insertingDocumentsWithAnswer(String collectionName, JsonObject jsonDoc, Function func) {
		try {
			mongoClient.insert(collectionName, jsonDoc, res -> {
				if (res.succeeded()) {
					JsonObject resultObject = new JsonObject();
					resultObject.put(DOCUMENTS_CREATED, res.result().toString());
					func.apply(resultObject);

				} else {
					func.apply(res.cause());
					logger.error(res.cause().toString());
				}
			});
		} catch (Exception e) {
			logger.error(e.getCause().toString());
		}

	}

	public void saveManyDocuments(String collectionName, List<JsonObject> jsonDocs, JsonObject query,
			boolean deleteNotInsertedDocuments, Function<JsonObject, Void> func) {

		Function firstFunc = new Function<List<JsonObject>, Void>() {
			@Override
			public Void apply(List<JsonObject> findList) {
				List<BulkOperation> bulkOperations = new ArrayList<>();
				boolean didAdd = false;
				boolean doesExist = false;

				// if the list is null, that means no data was found in the collection
				if (findList == null) {
					func.apply(new JsonObject().put("error", "could not find any data in the collection"));
					return null;
				}

				// 2. we go through all the documents we want to save in the db
				for (JsonObject jsonDoc : jsonDocs) {
					// TODO Adi: remove conditions that can't be met (after cleaning the DB there
					// should only be one type of _id, not 2)
					// 3. we go through all the data we found in the db
					for (JsonObject currFind : findList) {
						if (isDbIdMatching(jsonDoc, currFind)) {
							// 5. if the documents id is already in the db then it creates a Replace bulk
							// operation
							// that receives the existing document in the database as the filter parameter
							// of the function
							// and the new document to replace it with, as the document parameter of the
							// function
							bulkOperations.add(BulkOperation.createReplace(currFind, jsonDoc));

							// 6. mark that we already replaced the document and no need to try and add it
							// this basically marks that we dealt with this particular document
							didAdd = true;
							break;
						}
						if (deleteNotInsertedDocuments) {
							for (JsonObject currSearchDoc : jsonDocs) {
								// here we try and find if the user has not sent any of the documents that are
								// already in the DB
								if (isDbIdMatching(currSearchDoc, currFind)) {
									doesExist = true;
									break;
								}
							}
							if (!doesExist) {
								// if the user did not send some documents that are already in the DB, this
								// deletes them.
								bulkOperations.add(BulkOperation.createDelete(currFind));
							}
						}
					}
					// 7. checks if we already dealt with this document
					if (!didAdd) {
						// 8. if we did not deal with this document, it means it was not found in the db
						// already
						// this means we need to create a new document in the db
						// we then create an insert bulk operation with the current document we want to
						// insert, as the document parameter of the function
						bulkOperations.add(BulkOperation.createInsert(jsonDoc));
					}
					didAdd = false;
				}

				mongoClient.bulkWrite(collectionName, bulkOperations, result -> {
					if (result.succeeded()) {
						func.apply(result.result().toJson());
					} else {
						func.apply(new JsonObject().put("error", result.cause().getMessage()));
					}
				});
				return null;
			}
		};

		// 1. we find all the data in the mongo collection
		mongoClient.find(collectionName, query, result -> {
			if (result.succeeded()) {
				firstFunc.apply(result.result());
			} else {
				firstFunc.apply(null);
			}
		});
	}

	private boolean isDbIdMatching(JsonObject outsideDoc, JsonObject inDbDoc) {
		if (inDbDoc.getValue("_id") instanceof JsonObject && inDbDoc.getValue("_id") != null) {

			if (outsideDoc.getValue("_id") instanceof JsonObject) {
				// 4. checks if the documents id is in the db already
				if (inDbDoc.getJsonObject("_id").getString("$oid")
						.equals(outsideDoc.getJsonObject("_id").getString("$oid"))) {
					return true;
				}
			} else if (outsideDoc.getValue("_id") instanceof String) {
				if (inDbDoc.getJsonObject("_id").getString("$oid").equals(outsideDoc.getString("_id"))) {
					return true;
				}
			}
		} else if (inDbDoc.getValue("_id") instanceof String && inDbDoc.getValue("_id") != null) {
			if (outsideDoc.getValue("_id") instanceof String) {
				if (inDbDoc.getString("_id").equals(outsideDoc.getString("_id"))) {
					return true;
				}
			} else if (outsideDoc.getValue("_id") instanceof JsonObject) {
				if (inDbDoc.getString("_id").equals(outsideDoc.getJsonObject("_id").getString("$oid"))) {
					return true;
				}
			}
		}
		return false;
	}

	public void deleteDocuments(String collectionName, JsonObject jsonDoc) {
		try {
			mongoClient.findOneAndDelete(collectionName, jsonDoc, this::logDeleteResult);
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

	private void logDeleteResult(AsyncResult<JsonObject> jsonObjectAsyncResult) {
		if (jsonObjectAsyncResult.succeeded()) {
			// logger.info("Mongo document deleted!");
		} else {
			logger.error("failed to delete document to mongo ", jsonObjectAsyncResult.cause());
		}
	}

	public Single<String> saveDocuments(String collectionName, JsonObject document) throws Exception {
		Single<String> result = mongoClient.rxSave(collectionName, document);
		return result;
	}

	private void logInsertResult(AsyncResult<String> stringAsyncResult) {
		if (stringAsyncResult.succeeded()) {
			// logger.info("Mongo document saved!");
		} else {
			if (!stringAsyncResult.cause().getMessage().contains("E11000 duplicate"))
				logger.error("failed to save document to mongo ", stringAsyncResult.cause());
		}
	}

	public void getCollections(Handler<List> handler) {
		mongoClient.getCollections(res -> {
			if (res.succeeded()) {
				List<String> collections = res.result();
				handler.handle(collections);
			} else {
				res.cause().printStackTrace();
			}
		});
	}

	public void findUserData(String collection, JsonObject query, Handler<AsyncResult<JsonObject>> handler) {

		mongoClient.findOne(collection, query, new JsonObject(), handler);

	}
	
	/**
	 * 
	 * @param collection
	 * @param query
	 * @return
	 */
	public Single<JsonObject> findById(String collection, JsonObject query){
		JsonObject fields = new JsonObject();
		fields.put("_id", query.getString("_id"));
		Single<JsonObject> res = mongoClient.rxFindOne(collection, fields, null);
		return res;
	}

	/**
	 * find by the specified id
	 * @param collection
	 * @param id
	 * @return
	 */
	public Single<JsonObject> findById(String collection, String id){
		JsonObject fields = new JsonObject();
		fields.put("_id", id);
		Single<JsonObject> res = mongoClient.rxFindOne(collection, fields, null);
		return res;
	}
	
	/**
	 * 
	 * @param collection
	 * @param query
	 * @return
	 */
	public Single<List<JsonObject>> find(String collection, JsonObject query){
		Single<List<JsonObject>> res = mongoClient.rxFind(collection, query);
		return res;
	}
	

	public Single<List<JsonObject>> find(String collection){
		JsonObject query = new JsonObject();
		Single<List<JsonObject>> res = mongoClient.rxFind(collection, query);
		return res;
	}
	
	public void find(String collection, JsonObject query, RoutingContext routingContext) {
		find(collection, query, this::sendResult, routingContext);
	}

	public void find(String collection, JsonObject query, Handler<RoutingContext> handler,
			RoutingContext routingContext) {
		mongoClient.find(collection, query, res -> {
			routingContext.put(SUCCEEDED, res.succeeded());
			if (res.succeeded()) {
				JsonObject resultObject = new JsonObject();
				resultObject.put(RESULT, res.result());
				routingContext.put(RESULT, resultObject);
			} else {
				routingContext.put(CAUSE, res.cause());
			}

			handler.handle(routingContext);
		});
	}

	public void find(String collection, JsonObject query, Function<List<JsonObject>, Void> func) {
		mongoClient.find(collection, query, res -> {
			if (res.succeeded())
				func.apply(res.result());
			else
				func.apply(new ArrayList<>());
		});
	}

	public void findOne(String collection, JsonObject query, Function<JsonObject, Void> func) {
		mongoClient.findOne(collection, query, null, res -> {
			if (res.succeeded())
				func.apply(res.result());
			else
				func.apply(null);

		});
	}

	public void findWithFields(String collection, JsonObject query, JsonObject fields,
			Function<List<JsonObject>, Void> func) {
		FindOptions options = new FindOptions();
		options.setFields(fields);
		findWithOptions(collection, query, options, func);
	}

	public void findWithOptions(String collection, JsonObject query, FindOptions options,
			Function<List<JsonObject>, Void> func) {
		mongoClient.findWithOptions(collection, query, options, res -> {
			if (res.succeeded())
				func.apply(res.result());
			else
				func.apply(new ArrayList<>());

		});
	}

	public void findLimit(String collection, JsonObject query, Function<List<JsonObject>, Void> func, int limit) {
		FindOptions findOptions = new FindOptions();
		findOptions.setLimit(limit);
		findOptions.setSort(new JsonObject().put("$natural", -1));
		mongoClient.findWithOptions(collection, query, findOptions, res -> {
			if (res.succeeded())
				func.apply(res.result());
			else
				func.apply(new ArrayList<>());
		});
	}

	private void findSort(String collection, JsonObject query, Handler<RoutingContext> handler,
			RoutingContext routingContext, int limit) {
		FindOptions findOptions = new FindOptions();
		findOptions.setLimit(limit);
		mongoClient.findWithOptions(collection, query, findOptions, res -> {
			routingContext.put(SUCCEEDED, res.succeeded());
			if (res.succeeded()) {
				JsonObject resultObject = new JsonObject();
				resultObject.put(RESULT, res.result());
				routingContext.put(RESULT, resultObject);

			} else {
				routingContext.put(CAUSE, res.cause());
			}

			handler.handle(routingContext);
		});

	}

	public void findSort(String collection, JsonObject query, RoutingContext routingContext, int limit) {
		findSort(collection, query, this::sendResult, routingContext, limit);
	}

	public void findSortByParams(String collection, JsonObject query, RoutingContext routingContext, int way,
			String name) {
		findSortByParams(collection, query, this::sendResult, routingContext, way, name, -1);
	}

	public void findSortByParams(String collection, JsonObject query, RoutingContext routingContext, int way,
			String name, int limit) {
		findSortByParams(collection, query, this::sendResult, routingContext, way, name, limit);
	}

	private void findSortByParams(String collection, JsonObject query, Handler<RoutingContext> handler,
			RoutingContext routingContext, int way, String name, int limit) {
		FindOptions findOptions = new FindOptions();
		findOptions.setSort(new JsonObject().put(name, way));
		if (limit > 0)
			findOptions.setLimit(limit);
		mongoClient.findWithOptions(collection, query, findOptions, res -> {
			routingContext.put(SUCCEEDED, res.succeeded());
			if (res.succeeded()) {
				JsonObject resultObject = new JsonObject();
				resultObject.put(RESULT, res.result());
				routingContext.put(RESULT, resultObject);

			} else {
				routingContext.put(CAUSE, res.cause());
			}

			handler.handle(routingContext);
		});

	}

	public void findUserData(String collection, JsonObject query, RoutingContext routingContext) {
		findUserData(collection, query, this::sendResult, routingContext);
	}

	public void findUserData(String collection, JsonObject query, Handler<RoutingContext> handler,
			RoutingContext routingContext) {
		mongoClient.findOne(collection, query, null, res -> {
			routingContext.put(SUCCEEDED, res.succeeded());
			if (res.succeeded()) {
				routingContext.put(RESULT, res.result());
			} else {
				routingContext.put(CAUSE, res.cause());
			}

			handler.handle(routingContext);
		});
	}

	public void findUserData(String collection, JsonObject query, Function<JsonObject, Void> func) {
		mongoClient.find(collection, query, res -> {

			if (res.succeeded()) {
				JsonObject resultObject = new JsonObject();
				resultObject.put(RESULT, res.result());

				func.apply(resultObject);

			} else {
				logger.error(res.cause().toString());
			}
		});
	}

	private void sendResult(RoutingContext routingContext) {
		try {
			boolean succeeded = (Boolean) routingContext.get(MongoLayer.SUCCEEDED);
			if (succeeded) {
				JsonObject resultObject = (JsonObject) routingContext.get(MongoLayer.RESULT);
				MessageLog.sendMessageObject(routingContext, MessageConfig.MessageKey.MONGO_GET_DATA, resultObject,
						logger);
			}
		} catch (Exception e) {
			MessageLog.sendErrorCode(routingContext, MessageConfig.MessageKey.MONGO_GET_DATA_ERROR,
					"Failed to pars mongo result", logger, e);
		}

	}

	public void removeDocument(String collectionName, JsonObject jsonDoc) {
		try {
			mongoClient.removeDocument(collectionName, jsonDoc, this::documentRemoved);
		} catch (Exception e) {
			logger.error(e.toString());
		}

	}

	public void replaceDocument(String collectionName, JsonObject jsonDoc1, JsonObject jsonDoc2) {
		try {
			mongoClient.replaceDocuments(collectionName, jsonDoc1, jsonDoc2, this::documentReplace);
		} catch (Exception e) {
			logger.error(e.toString());
		}

	}

	private void documentReplace(AsyncResult<MongoClientUpdateResult> mongoClientUpdateResultAsyncResult) {
		// logger.info("Mongo document replaced!
		// "+mongoClientUpdateResultAsyncResult.toString());
	}

	private void documentRemoved(AsyncResult<MongoClientDeleteResult> mongoClientDeleteResultAsyncResult) {
		// logger.info("Mongo document removed!
		// "+mongoClientDeleteResultAsyncResult.toString());
	}

	public void updateDocuments(String collectionName, JsonObject query, JsonObject update) {
		try {
			mongoClient.findOneAndUpdate(collectionName, query, update, this::updateRest);
		} catch (Exception e) {
			logger.error(e.toString());
		}

	}

	public void removeDocumentWithAnswer(String collectionName, JsonObject jsonDoc, Function func) {
		try {
			mongoClient.removeDocument(collectionName, jsonDoc, res -> {
				if (res.succeeded()) {
					JsonObject resultObject = new JsonObject();
					resultObject.put(DOCUMENTS_REMOVED, res.result().getRemovedCount());
					func.apply(resultObject);

				} else {
					logger.error(res.cause().toString());
				}
			});
		} catch (Exception e) {
			logger.error(e.toString());
		}

	}

	public void updateDocuments(String collectionName, JsonObject query, JsonObject update,
			RoutingContext routingContext) {
		try {
			mongoClient.findOneAndUpdate(collectionName, query, update, this::updateRest);
			routingContext.response().end(Json.encodePrettily(
					new JsonObject().put("response_code", MessageConfig.MessageKey.MONGO_DATA_UPDATED)));
		} catch (Exception e) {
			routingContext.response().end(Json.encodePrettily(
					new JsonObject().put("response_code", MessageConfig.MessageKey.MONGO_DATA_UPDATE_ERROR)));
			logger.error(e.toString());
		}

	}

	public void pushToDocuments(String collectionName, JsonObject query, JsonObject update) {
		try {
			JsonObject push = new JsonObject().put("$push", update);
			mongoClient.findOneAndUpdate(collectionName, query, push, this::updateRest);
		} catch (Exception e) {
			logger.error(e.toString());
		}

	}

	private void updateRest(AsyncResult<JsonObject> jsonObjectAsyncResult) {
		if (jsonObjectAsyncResult.succeeded()) {
			// logger.info("Mongo document saved!");
		} else {
			logger.error("failed to removed document to mongo ", jsonObjectAsyncResult.cause());
		}
	}

	public void getGeneratedLinkToPdf(RoutingContext routingContext) {
		String id = routingContext.request().getParam("id");
		JsonObject jsonObject = new JsonObject().put("_id", id);
		find("ContentMessages", jsonObject, routingContext);
	}

	public void dropCollection(String collectionName) {
		try {
			mongoClient.dropCollection(collectionName, res -> {
				if (res.succeeded()) {
				} else {
					res.cause().printStackTrace();
				}
			});
		} catch (Exception e) {
			logger.error(e.toString());
		}

	}

	public void replaceDoc(String collectionName, JsonObject original, JsonObject update) throws Exception {
		try {
			mongoClient.replaceDocuments(collectionName, original, update, res -> {
				if (res.succeeded()) {
					// logger.info("Replaced doc");
				} else {
					logger.error("Failed replacing error");
				}
			});
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void insertingDocuments(String collectionName, JsonObject jsonDoc, Function<AsyncResult<String>, Void> func)
			throws Exception {
		try {
			jsonDoc.remove("_id");
			mongoClient.insert(collectionName, jsonDoc, res -> {
				if (res.succeeded()) {
					func.apply(res);
				} else {
					func.apply(null);
				}
			});
			// logger.info("saved document");
		} catch (Exception e) {
			logger.info("failed to save document");
			throw new Exception(e);
		}

	}

	public void isAddressExists(String collectionName, JsonObject query, Function<JsonObject, Void> func)
			throws Exception {
		try {
			AtomicBoolean isExists = new AtomicBoolean(false);
			JsonObject response = new JsonObject().put("query", query);
			mongoClient.find(collectionName, query, res -> {
				if (res.succeeded()) {
					if (!res.result().isEmpty()) {
						isExists.set(true);
						long userID = res.result().get(0).getLong("userID");
						long accountID = res.result().get(0).getLong("accountID");
						String symbol = res.result().get(0).getString("symbol");
						long timeStamp = res.result().get(0).getLong("time");
						response.put("user_id", userID);
						response.put("symbol", symbol);
						response.put("accountID", accountID);
						response.put("time_stamp", timeStamp);
					}
					response.put("isExists", isExists.get());
					func.apply(response);
				} else {
					func.apply(null);
				}

			});
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public void deleteDocuments(String collectionName, JsonObject jsonDoc, Function func) throws Exception {
		try {
			jsonDoc.remove("_id");
			mongoClient.findOneAndDelete(collectionName, jsonDoc, res -> {
				if (res.succeeded()) {
					JsonObject jsonObject = res.result();
					func.apply(jsonObject);
				} else {
					func.apply(null);
				}
			});
		} catch (Exception e) {
			logger.error(e.toString());
			throw new Exception(e);
		}

	}

	public void removeDocuments(String collectionName, JsonObject query, Function func) {
		mongoClient.removeDocuments(collectionName, query, res -> {
			if (res.succeeded()) {
				func.apply(res.result().toJson());
			} else {
				func.apply(null);
			}
		});
	}

	public void updateDocuments(String collectionName, JsonObject query, JsonObject update,
			Function<AsyncResult<JsonObject>, Void> func) throws Exception {
		try {
			mongoClient.findOneAndUpdate(collectionName, query, update, res -> {
				if (res.succeeded()) {
					func.apply(res);
				} else {
					MessageLog.logError(MessageConfig.MessageKey.MONGO_DATA_UPDATE_ERROR,
							"Failed updating Mongo tx: " + res.cause(), logger);
				}
			});
		} catch (Exception e) {
			logger.error(e.toString());
			throw new Exception(e);
		}

	}

	public Single<JsonObject> updateDocumentWithAnswer(String collectionName, JsonObject query, JsonObject update) {
		Single<JsonObject> res = mongoClient.rxFindOneAndUpdate(collectionName, query, update);
		return res;

	}

	public void updateDocumentArray(String collectionName, JsonObject query, JsonObject update,
			Function<JsonObject, Void> func) throws Exception {
		try {
			mongoClient.findOneAndUpdate(collectionName, query, update, res -> {
				if (res.succeeded())
					func.apply(res.result());
				else
					func.apply(null);
				MessageLog.logError(MessageConfig.MessageKey.MONGO_DATA_UPDATE_ERROR,
						"Failed updating Mongo tx: " + res.cause(), logger);

			});
		} catch (Exception e) {
			logger.error(e.toString());
			throw new Exception(e);
		}

	}

	public void findOneAndUpdateWithOptions(String collectionName, JsonObject query, JsonObject update,
			FindOptions findOptions, UpdateOptions updateOptions, Function<JsonObject, Void> func) {
		try {
			mongoClient.findOneAndUpdateWithOptions(collectionName, query, update, findOptions, updateOptions, res -> {
				if (res.succeeded())
					func.apply(res.result());
				else
					func.apply(null);
			});

		} catch (Exception e) {
			func.apply(null);

		}

	}

}
