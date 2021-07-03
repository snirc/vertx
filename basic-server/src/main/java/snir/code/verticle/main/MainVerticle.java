package snir.code.verticle.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.AbstractVerticle;
import snir.code.config.AppConfig;
import snir.code.config.MessageConfig.MessageKey;
import snir.code.db.MongoLayer;
import snir.code.services.CommonService;
import snir.code.utils.MessageLog;

public final class MainVerticle extends MainAbstractVerticle {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CommonService commonService;
	private MongoLayer mongoLayer;
	@Override
	protected void loadRouter() throws Exception {
		
		initSubVerticles();
	}

	@Override
	protected void loadComponents() throws Exception {
		
		MongoLayer.getInstance("mongo", instance->{
			mongoLayer = instance;
		});
		commonService = new CommonService();
		MessageLog.logMessage(MessageKey.OK,"Init completed", logger);
		
	}

	@Override
	protected void addUriRoles() throws Exception {
		// set all rout un map
		MessageLog.logMessage(MessageKey.OK,"URI Roles loaded", logger);
	}

	private void initSubVerticles() throws Exception {
		
	}

	
	public static void main(String[] args) {
		try {
			System.setProperty("vertx.logger-delegate-factory-class-name",
					"io.vertx.core.logging.Log4j2LogDelegateFactory");
			System.out.println("Parameters______________");			
			String verticleName = AppConfig.AppParameters.getOrDefault("Verticle", MainVerticle.class.getName());
			
			Class<?> ctClass = Class.forName(verticleName);
			AbstractVerticle verticle = (AbstractVerticle) ctClass.getDeclaredConstructor().newInstance();
			AppConfig.AppParameters.put("APP", "basic-server");
			AppConfig.AppParameters.put("PORT", "8181");
			System.out.println("Found Vertical: " + ctClass.getName());			
			AppConfig.runVerticle(verticle);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(500);
		}

	}
}