package snir.code.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.jwt.JWTOptions;
import io.vertx.ext.web.RoutingContext;
import io.vertx.rxjava.ext.auth.User;
import io.vertx.rxjava.ext.auth.mongo.MongoAuth;
import io.vertx.rxjava.ext.mongo.MongoClient;
import rx.Single;
import snir.code.config.MessageConfig.MessageKey;
import snir.code.db.MongoLayer;
import snir.code.utils.MessageLog;

public class AuthService {

	private JWTAuth authProvider;
	private MongoLayer mongoLayer;
	private MongoAuth mongoAuthProvider;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static AuthService instance;
	// apipath role
	private static Map<String, String> roleMap = new HashedMap<>();

	private static Map<String, List<String>> businssFunctionToRole = new HashMap<>();

	public static enum role {
		ADMIN, USER, TEST,
	}

	// query is an object with the same _id to find the roles document
	private JsonObject query = new JsonObject().put("_id", "roles");

	private AuthService() {
	}

	public static AuthService getInstance() {
		if (instance == null) {
			instance = new AuthService();
		}
		return instance;
	}

	public void init(Vertx vertx, MongoLayer mongoLayer) {

		instance = new AuthService();
		createJWTAuthProvider(vertx);
		createMongoAuthProvider(mongoLayer);

	}
	/**
	 * create the jwt auth provider
	 * @param vertx
	 */
	private void createJWTAuthProvider(Vertx vertx) {

		JWTAuthOptions authOptions = new JWTAuthOptions();
		JWTOptions jwtOptions = new JWTOptions().addPermission("my").setIgnoreExpiration(true);
		authOptions.setJWTOptions(jwtOptions);
		authOptions.addPubSecKey(new PubSecKeyOptions().setAlgorithm("HS256").setPublicKey("keyboard cat")
				.setSymmetric(true).setSecretKey("mySecretKey"));
		instance.authProvider = JWTAuth.create(vertx, authOptions);
	}

	/**
	 * create the MongoAuthProvider 
	 * and the mongoLayer 
	 * this functions also calls the initBusinssFunctionToRole() method
	 * to initialize the businssFunctionToRole Map from the DB
	 *  the MongoAuthProvider  is used for both the sign-up and login functions 
	 * @param mongoLayer
	 */
	private void createMongoAuthProvider(MongoLayer mongoLayer) {
		instance.mongoLayer = mongoLayer;
		initBusinssFunctionToRole();
		MongoClient mongoClient = mongoLayer.getMongoClient();
		instance.mongoAuthProvider = MongoAuth.create(mongoClient, new JsonObject());

	}

	/**
	 * sign-up a new user using the MongoAuthProvider
	 * and then calls the addedSignUpUserInfo to add 
	 * Additional user info like email and phone number to the DB 
	 * 
	 * @param ctx
	 */
	public void signUp(RoutingContext ctx) {
		try {

			JsonObject body = ctx.getBodyAsJson();
			String userName = body.getString("username");
			String password = body.getString("password");
			String businssFunction = body.getString("businssFunction");
			List<String> roles = businssFunctionToRole.get(businssFunction);
			List<String> permissions = new ArrayList<String>();

			mongoAuthProvider.insertUser(userName, password, roles, permissions, handler -> {
				try {
					if (handler.succeeded()) {
						addedSignUpUserInfo(ctx, userName, body);
					} else {
						String errorMsg = handler.cause().getMessage();
						if (errorMsg.startsWith("E11000 duplicate key")) {
							MessageLog.sendErrorCode(ctx, MessageKey.SIGNUP_FAILED_USERNAME_DUPLICATE,
									"userName allready exists", logger);
						} else {
							MessageLog.sendErrorCode(ctx, MessageKey.SIGNUP_FAILED, "general DB failure ", logger);
						}
					}

				} catch (Exception e) {
					MessageLog.sendErrorCode(ctx, MessageKey.SIGNUP_FAILED, "", logger, e);
				}
			});
			

		} catch (Exception e) {
			MessageLog.sendErrorCode(ctx, MessageKey.SIGNUP_FAILED, "", logger, e);
		}

	}

	private void addedSignUpUserInfo(RoutingContext ctx, String userName,JsonObject body) {
		
		String email = body.getString("email");
		String fullName = body.getString("fullName");
		String phoneNumber = body.getString("phoneNumber");
		JsonObject userData = new JsonObject()
				.put("phoneNumber", phoneNumber)
				.put("fullName", fullName)
				.put("email", email);
		JsonObject update = new JsonObject().put("$set", userData);
		
		JsonObject userQuery = new JsonObject().put("username", userName);
		mongoLayer.updateDocumentWithAnswer("user", userQuery, update).subscribe(onSuccess ->{
			MessageLog.sendMessageCode(ctx, MessageKey.SIGNUP_SUCCESS, "user succssfuly signedUp", logger);
		}, onError ->{
			MessageLog.sendErrorCode(ctx, MessageKey.SIGNUP_FAILED_USERDATA_INSERT_FAILED,
					"userName allready exists", logger);			
		});
		
	}

	public void login(RoutingContext ctx) {
		
		JsonObject authInfo = ctx.getBodyAsJson();
		mongoAuthProvider.authenticate(authInfo, res -> {
			try {
				if (res.succeeded()) {

					User user = res.result();
					JsonObject authorizations = user.principal();
					JsonArray roleIds = authorizations.getJsonArray("roles");
					String id = authorizations.getString("_id");
					JWTOptions jwtOptions = new JWTOptions().setIgnoreExpiration(true)
							.setPermissions(roleIds.getList());
					JsonObject userInfo = new JsonObject().put("id", id);
					String token = authProvider.generateToken(userInfo, jwtOptions);

					MessageLog.sendMessageObject(ctx, MessageKey.LOGIN_SUCCESS, token, logger);

				} else {
					MessageLog.sendErrorCode(ctx, MessageKey.LOGIN_FAILED, "userName or password are incorrect",
							logger);
				}
			} catch (Exception e) {
				MessageLog.sendErrorCode(ctx, MessageKey.LOGIN_FAILED, "userName or password are incorrect", logger, e);
			}
		});

	}

	/**
	 * authenticate the jwt auth token taken from the Authorization header 
	 * and check if the user has a permission to the current route 
	 * if it does ctx.next moves it to the next handler
	 * @param ctx
	 */
	public void authenticate(RoutingContext ctx) {
		try {
			String auth = ctx.request().getHeader("AUTHORIZATION");
			JsonObject authJson = new JsonObject().put("jwt", auth);
			authProvider.authenticate(authJson, res -> {
				try {
					if (res.succeeded()) {
						io.vertx.ext.auth.User user = res.result();
						String path = ctx.normalisedPath();
						path = path.split("/")[1];
						String role = roleMap.get(path);
						user.isAuthorized(role, resultHandler -> {
							if (resultHandler.succeeded()) {
								ctx.next();
							} else {
								MessageLog.sendErrorCode(ctx, MessageKey.AUTH_FAILED, "failed to authenticate token",
										logger);
							}
						});
					} else {
						MessageLog.sendErrorCode(ctx, MessageKey.AUTH_FAILED, "failed to authenticate token", logger);
					}
				} catch (Exception e) {
					MessageLog.sendErrorCode(ctx, MessageKey.AUTH_FAILED, "failed to authenticate token", logger, e);
				}
			});

		} catch (Exception e) {
			MessageLog.sendErrorCode(ctx, MessageKey.AUTH_FAILED, "failed to authenticate token", logger, e);
		}
	}

	public void logout(RoutingContext ctx) {
		try {
			ctx.clearUser();
			MessageLog.sendMessageCode(ctx, MessageKey.LOGOUT, "logout success", logger);
		} catch (Exception e) {
			MessageLog.sendMessageCode(ctx, MessageKey.LOGOUT, "logout success", logger);
		}

	}

	/**
	 *  add a new BusinssFunction both to memory and to DB 
	 *  this method adds any role that is not already in the DB 
	 *  
	 * @param ctx
	 */
	public void addBusinssFunction(RoutingContext ctx) {
		try {

			JsonObject body = ctx.getBodyAsJson();
			String id = body.getString("id");
			JsonArray roles = body.getJsonArray("roles");
			JsonObject forEach = new JsonObject().put(id, new JsonObject().put("$each", roles));
			JsonObject update = new JsonObject().put("$addToSet", forEach);

			Single<JsonObject> res = mongoLayer.updateDocumentWithAnswer("businss-function", query, update);
			res.subscribe(onSuccess -> {
				List<String> rolesAsList = roles == null ? new ArrayList<String>() : roles.getList();
				List<String> businssFunctionRoles = businssFunctionToRole.get(id);
			
				if(businssFunctionRoles == null) {
					businssFunctionRoles = new ArrayList<String>();
				}
				
				businssFunctionRoles.addAll(rolesAsList);
				businssFunctionToRole.put(id, businssFunctionRoles);
				MessageLog.sendMessageCode(ctx, MessageKey.BUSINSS_FUNCTION_ADDED, "BusinssFunction added ");
			}, onError -> {
				MessageLog.sendErrorCode(ctx, MessageKey.BUSINSS_FUNCTION_ADDED, onError.getMessage(), logger);
			});

		} catch (Exception e) {
			MessageLog.sendErrorCode(ctx, MessageKey.BUSINSS_FUNCTION_ADDED, "failed to update roles in DB ", logger);
		}
	}
	
	/**
	 *  remove BusinssFunction roles both from DB and the  businssFunctionToRole map in memory
	 * @param ctx
	 */
	public void removeBusinssFunction(RoutingContext ctx) {
		try {

			JsonObject body = ctx.getBodyAsJson();
			String id = body.getString("id");
			JsonObject update = new JsonObject().put("$unset", new JsonObject().put(id, ""));
			if (id != null) {
				mongoLayer.updateDocumentWithAnswer("businss-function", query, update).subscribe(onSuccess->{
					businssFunctionToRole.remove(id);
					MessageLog.sendMessageCode(ctx, MessageKey.BUSINSS_FUNCTION_REMOVE, "BusinssFunction removed");
				}, onError ->{
					MessageLog.sendMessageCode(ctx, MessageKey.BUSINSS_FUNCTION_REMOVE_ERROR, onError.getMessage());
				});
			}
			
		} catch (Exception e) {
			MessageLog.sendMessageCode(ctx, MessageKey.BUSINSS_FUNCTION_REMOVE_ERROR, e.getMessage());
		}
	}
	
	/**
	 * get all businssFunctionToRole map 
	 * @param ctx
	 */
	public void getBusinssFunction(RoutingContext ctx) {
		try {
			JsonObject obj = JsonObject.mapFrom(businssFunctionToRole).copy();
			MessageLog.sendMessageObject(ctx, MessageKey.GET_BUSINSS_FUNCTION, obj, logger);
			
		} catch (Exception e) {
			MessageLog.sendMessageCode(ctx, MessageKey.GET_BUSINSS_FUNCTION_ERROR, e.getMessage());
		}
	}

	/**
	 * Initializes the businssFunctionToRole map from the DB 
	 * TODO: maybe add a depended future so that if the function fails the vertical will exit 
	 * Because if businssFunctionToRole is not initialized we can not sign up new users !!
	 */
	private void initBusinssFunctionToRole() {
		try {

			Single<List<JsonObject>> observer = instance.mongoLayer.find("businss-function", query);
			observer.subscribe(onSuccess -> {
				JsonObject result = onSuccess.get(0);
				result.remove("_id");
				result.forEach(roleEntry->{
				try {
					
					String key = roleEntry.getKey();
					JsonArray rolesJson = (JsonArray) roleEntry.getValue();
					rolesJson = rolesJson == null ? new JsonArray() : rolesJson;
					businssFunctionToRole.put(key, rolesJson.getList());

				} catch (Exception e) {
					// pass
				}

				});
			}, onError -> {
				System.out.println("failed to load BusinssFunctionToRole from DB");
			});

		} catch (Exception e) {
			System.out.println("debug");
		}
	}

	/**
	 * adds a uril role to the role Map 
	 * note currently the authentication function will only uses the start of the path i.e everything between the first to "/" characters
	 * so if you have a path that is "/protected/test"
	 * and only users with a role of "officer" should be able to access it then 
	 *  if you will write addUriRole( "/protected/test", role.officer)
	 *  then the authentication function WILL NOT FIND IT as it will search the businssFunctionToRole for the path "protected"
	 * @param path
	 * @param rule
	 */
	public static void addUriRole(String path, role rule) {

		if (roleMap.get(path) == null) {
			roleMap.put(path, rule.name());
		}

	}

}
