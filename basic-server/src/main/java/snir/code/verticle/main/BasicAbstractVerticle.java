package snir.code.verticle.main; 
 
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.sstore.SessionStore;
import snir.code.config.AppConfig;
import snir.code.db.MongoLayer;

public abstract class BasicAbstractVerticle extends AbstractVerticle{
	protected Set<String> allowedHeaders;
	protected Set<HttpMethod> allowedMethods;
	protected Router router;

	protected HttpServer server;
	protected final static Logger logger = LoggerFactory.getLogger(BasicAbstractVerticle.class);
	protected MongoLayer cryptoMongoLayer;
	protected Future<Void> startFuture;
	private List<Future> futures;
			
	


	protected abstract void loadRouter() throws Exception;
	protected abstract void loadComponents() throws Exception;
	protected abstract void addUriRoles() throws Exception;
	
	 @Override
	  public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("-------------------------------startHttpServer started-----------------------------------");
		HttpServerOptions httpOpts = new HttpServerOptions();
		httpOpts.setCompressionSupported(true);
		
		
		int port = Integer.parseInt(AppConfig.AppParameters.get("PORT"));
		
		if (port == 443) {
			// For HTTPS
			logger.info("start: 2 HTTPS");
			httpOpts.setPemKeyCertOptions(new PemKeyCertOptions()
					.setKeyPath("/home/ec2-user/webapp/cert/www_bitzonex_com.key")
					.setCertPath("/home/ec2-user/webapp/cert/" + AppConfig.AppParameters.get("CRT") + ".crt"));

			
			httpOpts.setSsl(true);
			server = vertx.createHttpServer(httpOpts);
		} else {
		    vertx.createHttpServer().requestHandler(req -> {
		       
		      }).listen(port, http -> {
		        if (http.succeeded()) {
		          startPromise.complete();
		          router = Router.router(vertx);
					try {
						loadComponents();
						startRouter();
						addDefaultUrilRoles();				
						loadRouter();
						addUriRoles();
						System.out.println("STARTED ON PORT "+port);
						logger.info("Start up on port: " + port);
					} catch (Exception e) {
						e.printStackTrace();
						System.exit(1); 
					}
		        } else {
		          startPromise.fail(http.cause());
		        }
		      });
			
		}							
		
				
	}
	
	protected void startRouter() {
		SessionStore store = LocalSessionStore.create(vertx);
		SessionHandler sessionHandler = SessionHandler.create(store);
		
		allowedHeaders = AppConfig.allowedHeaders();
		allowedMethods = AppConfig.allowedMethods();
		
		router.route().handler(sessionHandler);
		router.route().handler(BodyHandler.create().setMergeFormAttributes(true));
		router.route().handler(CorsHandler.create("*")
				.allowedHeaders(allowedHeaders).allowedMethods(allowedMethods).allowedHeader("Content-Type"));
        
		router.route("/*").handler(BodyHandler.create());
		router.route().handler(BodyHandler.create());
	}
	
	private void addDefaultUrilRoles() throws Exception{
		
	}
	
}


