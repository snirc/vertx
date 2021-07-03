package snir.code.config;

import java.util.function.Function;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;

public class DbConfig {

	public static void getMongoConfig(Vertx vertx, Handler<JsonObject> method) {
		Function<JsonObject, Void> func = new Function<JsonObject, Void>() {
			@Override
			public Void apply(JsonObject result) {
				try {
					if (result.getInteger("response_code") < MessageConfig.ERROR_CODE_FROM) {
                        JsonObject contentJson = new JsonObject(result.getString("content"));
						method.handle(contentJson);
					} else {
						method.handle(result);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
				return null;
			}
		};

		FileUtil.getFileContent(func,"config/"+AppConfig.AppParameters.getOrDefault("MONGO","mongo")+".json", vertx);

	}

}