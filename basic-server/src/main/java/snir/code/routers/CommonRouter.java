package snir.code.routers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import snir.code.config.MessageConfig.MessageKey;
import snir.code.services.AuthService;
import snir.code.services.CommonService;
import snir.code.utils.MessageLog;
import snir.code.utils.TemplateUtils;
import snir.code.verticle.main.MainAbstractVerticle;

public class CommonRouter {

	private static Logger logger = LoggerFactory.getLogger(CommonRouter.class);
	private CommonService commonService = new CommonService();
	private AuthService authService;

	public void rout(Router router) {

		System.out.println("Now Routing..");
		router.route("/protected/*").handler(AuthService.getInstance()::authenticate);
		/*
		 * router.get("/protected/test").handler( routingContext ->{
		 * MessageLog.sendMessageObject(routingContext, MessageKey.OK,
		 * MainAbstractVerticle.VERSION, logger); });
		 */
		router.get("/my/test").handler(ctx ->{
			try {
				
				Map<String, String> valuesMap = new HashMap<String, String>();
				valuesMap.put("me", "changed");
				Function func = new Function<JsonObject,Void>(){
					
					@Override
					public Void apply(JsonObject res) {
						try {
							MessageLog.sendMessageObject(ctx, MessageKey.BUSINSS_FUNCTION_ADDED, res, logger);
						} catch (Exception e) {
							MessageLog.sendMessageCode(ctx, MessageKey.AUTH_FAILED, e.getMessage());
						}
						
						return null;
						
					}};
					
					TemplateUtils.replaceTemplate(valuesMap, "foo", func);

			} catch (Exception e) {
				MessageLog.sendMessageCode(ctx, MessageKey.AUTH_FAILED, e.getMessage());
			}
		});
		
		router.post("/addbusinssfunction").handler(AuthService.getInstance()::addBusinssFunction);
		router.post("/delete/businssfunction").handler(AuthService.getInstance()::removeBusinssFunction);
		router.get("/get/businssfunction").handler(AuthService.getInstance()::getBusinssFunction);
		
		router.get("/version").handler(routingContext -> {
			MessageLog.sendMessageObject(routingContext, MessageKey.OK, commonService.getVersion(), logger);
		});
		router.post("/login").handler(AuthService.getInstance()::login);
		router.post("/signup").handler(AuthService.getInstance()::signUp);
		
		AuthService.addUriRole("protected", AuthService.role.ADMIN);

	}

}

