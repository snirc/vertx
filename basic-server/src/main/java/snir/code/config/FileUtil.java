package snir.code.config;

import java.util.function.Function;


import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;

public class FileUtil {

    public static void getFileContent(Function<JsonObject, Void> nextMethod, String file, Vertx vertx){
        vertx.fileSystem().readFile(file, result -> {
            JsonObject resultJson = new JsonObject();
            if (result.succeeded()) {
                resultJson.put("response_code", MessageConfig.MessageKey.READ_FILE_SUCCESS.val());
                resultJson.put("content", result.result().toString());
                nextMethod.apply(resultJson);
            } else {
                resultJson.put("response_code", MessageConfig.MessageKey.READ_FILE_ERROR.val());
                resultJson.put("cause", result.cause());
                nextMethod.apply(resultJson);
            }

        });
    }


    public static void readFile(String file, Vertx vertx){
        vertx.fileSystem().readFile(file, result -> {
            if (result.succeeded()) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~File OK: "+result.result());
            } else {
                System.err.println("~!~~~~~~~~~~~~~Oh oh ..." + result.cause());
            }

        });
    }
}
