package snir.code.utils;

import java.util.Map;
import java.util.function.Function;

import org.apache.commons.text.StringSubstitutor;

import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.Vertx;
import snir.code.config.FileUtil;
import snir.code.config.MessageConfig;


public class TemplateUtils {

	private static Vertx rxVertx;
	
	public static void init(Vertx rxVertx) {
		TemplateUtils.rxVertx = rxVertx;
	}
	
	 public static void replaceTemplate(Map<String, String> valuesMap, String fileName, Function<JsonObject,Void> applyTo) {
		 
		 Function replaceTemplate = new Function<JsonObject,Void>(){

			@Override
			public Void apply(JsonObject res) {
				try {

					if (res.getInteger("response_code") < MessageConfig.ERROR_CODE_FROM) {

						String template = res.getString("content");
						String result = replaceString(valuesMap, template);
						res.put("content", result);
						applyTo.apply(res);
					
					}else {
						applyTo.apply(res);
					}
					
				} catch (Exception e) {
					res.put("cause"," an error has ocoured while trying to fill in the template | error Message = " + e.getMessage());
					applyTo.apply(res);
				}
				return null;
			}};
		 
		 FileUtil.getFileContent(replaceTemplate, "template/" + fileName + ".html", rxVertx);
	 }
	
	public static String replaceString(Map<String, String> valuesMap, String templateString) {
		
		StringSubstitutor sub = new StringSubstitutor(valuesMap);
		String resolvedString = sub.replace(templateString);
		
		return resolvedString;
	}
	
	
	
	
}
