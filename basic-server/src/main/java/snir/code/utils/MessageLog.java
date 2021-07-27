package snir.code.utils;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import snir.code.config.AppConfig;
import snir.code.config.MessageConfig;
import snir.code.db.MongoLayer;

/**
 * This class is a utility for sending response to the client and save it to log
 *
 */
public class MessageLog {

	private static String LOG_INFO_COLLECTION = "";
	public static String HOST_NAME = "Unknown";
	private static final int DAYS_TO_DROP_LOG = 4;
	private static AtomicInteger logId = new AtomicInteger(0);
	private static boolean doPrint = false;
	
	private static Logger deffualtLogger = LoggerFactory.getLogger(MessageLog.class);;

	public static MongoLayer MONGO_API = null;

	public static void setDoPrint(boolean doPrint) {
		MessageLog.doPrint = doPrint;
	}
	
	public static void initLogMongo() {
		
		MongoLayer.getInstance(AppConfig.AppParameters.get("APP"), instance -> {
			try {
				setMongoApi(instance);
			} catch (Exception e) {
				//TODO:  maybe fail start promise we can't get log mongo instance
//				startPromise.fail(e);
			}
		});
	}

	private static void setMongoApi(MongoLayer mongoLayer) {
		MONGO_API = mongoLayer;
		LOG_INFO_COLLECTION = HOST_NAME;
		MONGO_API.createCollection(LOG_INFO_COLLECTION);
		logId.set(0);
	}

	public static void sendMessageObject(RoutingContext routingContext, MessageConfig.MessageKey messageKey,
			String message, Logger logger) {
		String loggerMessage = sendMessageCode(routingContext, messageKey, message);
		if (doPrint)
			logger.info(loggerMessage);
	}

	public static void sendErrorCode(RoutingContext routingContext, MessageConfig.MessageKey messageKey,
			String errorMessage, Logger logger) {		
		String loggerMessage = sendMessageCode(routingContext, messageKey, errorMessage);
		if (doPrint)
			logger.error(loggerMessage);
	}


	/**
	 * Create Json Object with the specified error id and message and write to the
	 * specified logger
	 * 
	 * @param routingContext
	 * @param messageKey
	 * @param errorMessage
	 * @param logger
	 * @param e
	 */
	public static void sendErrorCode(RoutingContext routingContext, MessageConfig.MessageKey messageKey,
			Object errorMessage, Logger logger, Throwable e) {
		JsonObject messageObject = new JsonObject();
		if (errorMessage instanceof JsonObject) {
			messageObject = (JsonObject) errorMessage;
		}
		messageObject.put("Exception_Message", e.getMessage());

		String loggerMessage = sendMessageCode(routingContext, messageKey, messageObject);
		if (doPrint)
			logger.error(loggerMessage);
	}

	public static void sendMessageCode(RoutingContext routingContext, MessageConfig.MessageKey messageKey,
			Object message, Logger logger) {

		String loggerMessage = sendMessageCode(routingContext, messageKey, message);
		if (doPrint)
			logger.info(loggerMessage);
	}

	/**
	 *
	 * @param routingContext
	 * @param messageKey
	 * @return
	 */
	public static String sendMessageCode(RoutingContext routingContext, MessageConfig.MessageKey messageKey,
			Object description) {
		JsonObject messageObject = new JsonObject();

		messageObject.put("response_code", messageKey.val());
		messageObject.put("description", description);
		JsonObject sendMsg = messageObject.copy();

		/** indication for system or client log file **/
		messageObject.put("log_origin", "system");

		if (routingContext != null) {
			try {

				/** indication for system or client log file - if routingContext so client **/
				messageObject.put("log_origin", "client");

				long timeStamp = System.currentTimeMillis();
				long duration = timeStamp - (long) routingContext.get("startTime");
				messageObject.put("duration", duration);
				sendMsg.put("duration", duration);
				sendMsg.put("TimeStamp", timeStamp);
				routingContext.response().putHeader("content-type", "application/json; charset=utf-8").end(sendMsg.toString());
				
			} catch (Exception e) {
			}
			messageObject = getRemoteDetails(routingContext, messageObject);
		}

		if (messageKey.val() < MessageConfig.ERROR_CODE_FROM)
			logInfoToMongo(messageObject, deffualtLogger);
		else
			logErrorToMongo(messageObject, deffualtLogger);

		return Json.encodePrettily(messageObject);
	}


	/**
	 * create json message, write it to log and send the message code to the user
	 * 
	 * @param routingContext
	 * @param messageKey
	 * @param messageObject
	 * @param logger
	 */
	public static void sendMessageObject(RoutingContext routingContext, MessageConfig.MessageKey messageKey,
			JsonObject messageObject, Logger logger) {
		
		messageObject.put("response_code", messageKey.val());
		JsonObject messageToLog = new JsonObject();
		messageToLog.put("response_code", messageKey.val());

		/** indication for system or client log file - if routingContext so client **/
		messageToLog.put("log_origin", "client");
		//System.out.println(routingContext.request().getHeader("Origin"));
		long duration = -1;
		long timeStamp =  System.currentTimeMillis();
		if (routingContext.get("startTime") != null)
			duration = timeStamp - (long) routingContext.get("startTime");
		
		routingContext.response().putHeader("content-type", "application/json; charset=utf-8").end(messageObject.toString());
		
		messageObject.put("duration", duration);
		messageObject.put("TimeStamp", timeStamp);
		
		if (messageToLog.getString("description") == null)
			messageToLog.put("description", messageKey.toString());
		messageToLog = getRemoteDetails(routingContext, messageToLog);
		if (messageKey.val() < MessageConfig.ERROR_CODE_FROM){
			if (doPrint)
				logger.info(messageToLog.encodePrettily());
			logInfoToMongo(messageToLog, logger);
		} else {
			if (doPrint)
				logger.error(messageToLog.encodePrettily());
			logErrorToMongo(messageToLog, logger);
		}
	}

	public static void logMessage(MessageConfig.MessageKey messageKey, Object message, Logger logger) {
		logMessage(null, messageKey, message, logger);
	}
	

	public static void logMessage(RoutingContext routingContext, MessageConfig.MessageKey messageKey,
			Object description, Logger logger) {

		JsonObject messageObject = new JsonObject();

		/** indication for system or client log file by default it is a system log **/
		messageObject.put("log_origin", "system");

		messageObject.put("response_code", messageKey.val());
		messageObject.put("description", description);
		
		if (routingContext != null) {

			/** indication for system or client log file - if routingContext so client **/
			messageObject.put("log_origin", "client");

//			long duration = System.currentTimeMillis() - (long) routingContext.get("startTime");
//			messageObject.put("duration", duration);
			messageObject = getRemoteDetails(routingContext, messageObject);
		}
		if (doPrint)
			logger.info(Json.encodePrettily(messageObject));
		logInfoToMongo(messageObject, logger);
	}

	private static JsonObject getRemoteDetails(RoutingContext routingContext, JsonObject messageObject) {
		JsonObject remoteObject = new JsonObject();
		remoteObject.put("uri", routingContext.request().uri());
		remoteObject.put("port", routingContext.request().remoteAddress().port());
		remoteObject.put("local_Address", routingContext.request().localAddress().toString());
		remoteObject.put("remote_address", routingContext.request().remoteAddress().host());
		// remoteObject.put("Accept-Language",
		// routingContext.request().getHeader("Accept-Language"));
		// remoteObject.put("User-Agent",
		// routingContext.request().getHeader("User-Agent"));
		routingContext.request().headers().forEach(item -> {
			remoteObject.put(item.getKey(), item.getValue());
		});

		messageObject.put("remote_details", remoteObject);
		messageObject.put("host", (String) routingContext.get("host"));
		return messageObject;
	}

	/**
	 * Set the error into the specified logger
	 * 
	 * @param messageKey
	 * @param description
	 * @param logger
	 * @return
	 */
	public static String logError(MessageConfig.MessageKey messageKey, Object description, Logger logger) {
		return logError(messageKey, description, logger, new Exception(""));
	}

	/**
	 *
	 * @param messageKey
	 * @param description
	 * @param logger
	 * @param e
	 * @return
	 */
	public static String logError(MessageConfig.MessageKey messageKey, Object description, Logger logger, Throwable e) {
		JsonObject messageObject = new JsonObject();
		messageObject.put("response_code", messageKey.val());
		messageObject.put("description", description);

		if (doPrint)
			logger.error(messageObject.toString());
		logErrorToMongo(messageObject, logger);
		return Json.encodePrettily(messageObject);
	}

	private static void logToMongo(JsonObject messageObject, String type, Logger logger) {

		if (MONGO_API == null)
			return;

		if (!LOG_INFO_COLLECTION.endsWith(DateUtils.getCurrentDate())) {
			LOG_INFO_COLLECTION = HOST_NAME + "_" + DateUtils.getCurrentDate();
			MONGO_API.createCollection(LOG_INFO_COLLECTION);
			dropCollection();
			logId.set(0);
		}
		try {
			InetAddress ip = InetAddress.getLocalHost();
			String id = type + " (" + logId.incrementAndGet() + ") " + DateUtils.getLastTime();
			messageObject.put("_id", id);
			messageObject.put("time", DateUtils.getLastDateTime());
			messageObject.put("host_name", HOST_NAME);
			messageObject.put("server_port", AppConfig.AppParameters.get("PORT"));
			messageObject.put("level", type);
			messageObject.put("remote_address", ip.getHostAddress());
			MONGO_API.saveDocuments(LOG_INFO_COLLECTION, messageObject).subscribe(onSuccess -> {				
			}, onError -> {
				logger.error("Save log to mongo Error!!!");
			});
	
			String msg = messageObject.encode();
//			logger.info(msg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	private static void logInfoToMongo(JsonObject messageObject, Logger logger) {
		logToMongo(messageObject, "INFO", logger);
	}

	private static void logErrorToMongo(JsonObject messageObject, Logger logger) {
		logToMongo(messageObject, "ERROR", logger);
	}

	private static void dropCollection() {

		try {
			// set the collection date format
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			// get the date x days back, where x = the constant
			cal.add(Calendar.DATE, -DAYS_TO_DROP_LOG);
			Date colDate = cal.getTime();
			// parse the date to wanted format
			String fromdate = format.format(colDate);
			// concat all
			String collectionToDrop = HOST_NAME + "_" + fromdate;
			MONGO_API.dropCollection(collectionToDrop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}