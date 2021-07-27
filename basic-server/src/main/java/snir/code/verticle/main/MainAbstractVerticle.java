package snir.code.verticle.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import io.vertx.ext.auth.authentication.AuthenticationProvider
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.JWTAuthHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.sstore.SessionStore;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.ext.auth.jwt.JWTAuth;
import snir.code.config.AppConfig;
import snir.code.db.MongoLayer;
import snir.code.routers.CommonRouter;
import snir.code.services.AuthService;
import snir.code.utils.MessageLog;

public abstract class MainAbstractVerticle extends AbstractVerticle {
	protected Set<String> allowedHeaders;
	protected Set<HttpMethod> allowedMethods;
	protected Router router;
	protected MongoLayer mongoLayer;
	protected HttpServer server;
	protected final static Logger logger = LoggerFactory.getLogger(MainAbstractVerticle.class);
	protected JWTAuth authProvider;
	protected JWTAuthHandler authHandler;
	protected Promise<Void> startFuture;
	protected Vertx rxVertx;
	private List<Future> futures;
	private static String httpPort = "0";


	public void start(Promise<Void> startPromise) {
		try {

			this.startFuture = startPromise;
			futures = new ArrayList<Future>();
			rxVertx = io.vertx.rxjava.core.Vertx.vertx();
			
			if(AppConfig.AppParameters.get("MONGO")==null) {
				try {					
					startHttpServer();
				} catch (Exception e) {
					startPromise.fail(e);
				}
			}else {
				MongoLayer.init(rxVertx);
				MessageLog.initLogMongo();	
				MongoLayer.getInstance("mongo", instance -> {
					try {
						mongoLayer = instance;
						startHttpServer();
					} catch (Exception e) {
						startPromise.fail(e);
					}
				});
			}
			


		} catch (Exception e) {
			startPromise.fail(e);
		}
	}

	

	public Future addDependentFuture() {
		Promise<Void> promise = Promise.promise();
		Future<Void> future = promise.future();
		futures.add(future);
		return future;
	}

	
	protected abstract void loadRouter() throws Exception;

	protected abstract void loadComponents() throws Exception;

	protected abstract void addUriRoles() throws Exception;

	private void startHttpServer() throws Exception {
		httpPort = AppConfig.AppParameters.get("PORT");
		router = Router.router(vertx);
//		router.route().failureHandler(failureCtx -> {
//			failureCtx.failure().printStackTrace();
//			failureCtx.end("my end ");
//		});
		server = vertx.createHttpServer();
		loadComponents();
		startRouter();
		if(AppConfig.AppParameters.get("MONGO")!=null) {
			AuthService authService = AuthService.getInstance();
			authService.init(vertx, mongoLayer);
		}
		
		new CommonRouter().rout(router);
		loadRouter();

		server.requestHandler(router).listen(Integer.valueOf(httpPort), ar -> {
			if (ar.succeeded()) {
				System.out.println("HTTP server running on port " + httpPort);
				startFuture.complete();
			} else {
				System.out.println("Could not start a HTTP server: " + ar.cause().getMessage());
				startFuture.fail(ar.cause());
			}
		});

	}

	
	protected void startRouter() throws Exception {
		SessionStore store = LocalSessionStore.create(vertx);
		SessionHandler sessionHandler = SessionHandler.create(store);

		allowedHeaders = AppConfig.allowedHeaders();
		allowedMethods = AppConfig.allowedMethods();

//		router.route().handler((Handler<RoutingContext>) CookieHandler.getDefault());
		router.route().handler(sessionHandler);
		router.route().handler(BodyHandler.create().setMergeFormAttributes(true));
		router.route().handler(CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods)
				.allowedHeader("Content-Type"));

		router.route("/*").handler(BodyHandler.create());
		router.route().handler(BodyHandler.create());
		router.route().handler(ctx -> {
			ctx.put("startTime", System.currentTimeMillis());
			ctx.next();
		});
	}

	
	public void stop(Future<Void> stopFuture) throws Exception {
		System.out.println("MyVerticle stopped!");
		AppConfig.stopVerticle();
	}

	public String verticleName() {
		return getClass().getSimpleName();
	}
}
