package snir.code.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;


public class AppConfig {
	
	public static Map<String, String> AppParameters = new HashMap<>();

    public static String MAIN_PORT = "8181";
    public static int SOCKET_PORT = 8040;
	public  static int USER_SESSION_TIME = 60000 * 5; // 5 minutes
	public  static int ORDER_LOCAL_SESSION_TIME = 1000 * 60 * 60 * 24; // 24 hours
	
	private static VertxDeployer vertxDeployer;

	public static String getURL(RoutingContext routingContext){
		return "https://" + routingContext.request().host();
	}

	public static void setArgs(String[] args){
		AppParameters.put("PORT", MAIN_PORT);
		for (int i = 0; i < args.length; i++) {
			System.out.println("Parameter: "+args[i]);
			String[] arg = args[i].split("=");
			AppConfig.AppParameters.put(arg[0], arg[1]);
		}
	
	

	}
	
	
	public static Set<HttpMethod> allowedMethods() {
		
		Set<HttpMethod> allowedMethods = new HashSet<>();
		allowedMethods.add(HttpMethod.GET);
		allowedMethods.add(HttpMethod.POST);
		allowedMethods.add(HttpMethod.OPTIONS);
		allowedMethods.add(HttpMethod.DELETE);
		allowedMethods.add(HttpMethod.PATCH);
		allowedMethods.add(HttpMethod.PUT);
		
		return allowedMethods;
	}
	
	
	public static Set<String> allowedHeaders(){

		Set<String> allowedHeaders = new HashSet<>();
		allowedHeaders.add("PK");
		allowedHeaders.add("OPT");
		allowedHeaders.add("ENV");//Environment id
		allowedHeaders.add("Content-Type");
		allowedHeaders.add("Access-Control-Allow-Method");
		allowedHeaders.add("Access-Control-Allow-Origin");
		allowedHeaders.add("Origin");
		allowedHeaders.add("Access-Control-Allow-Credentials");
		
		return allowedHeaders;
	}


	public static boolean isClustered() throws Exception{
		try {
			String clustered = AppParameters.getOrDefault("CLUSTERED", "false");
			return Boolean.parseBoolean(clustered);
		}catch(NullPointerException np) {
			return false;
		}catch (Exception e) {
			throw new Exception(e);
		}
		
	}
	
	
	public static void runVerticle(AbstractVerticle verticle)throws Exception {
		vertxDeployer = new VertxDeployer(verticle);
		if(isClustered()) {
			vertxDeployer.deployToCluster(AppParameters.getOrDefault("ClusterGroup", "main"));
		}else {
	        VertxOptions options = new VertxOptions()
	        		.setBlockedThreadCheckInterval(1000*60*60);
	        Vertx vertx = Vertx.vertx(options);
			vertxDeployer.deploy(vertx);
		}
	}

	
	public static void stopVerticle() {
		vertxDeployer.closeVertx();
	}


}

