package snir.code.config;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.mail.MailConfig;
import io.vertx.ext.mail.StartTLSOptions;

public class EmailConfig extends MailConfig{

	private static JsonObject zoozcryptopocConfig = new JsonObject()
			.put("user", "app@bitzonex.com")
			.put("password", "ShokoIsThe1")
			.put("smtp_host", "smtp-relay.gmail.com")
			  .put("port", 587);

	public static String USER = zoozcryptopocConfig.getString("user");
	
	public EmailConfig() {
		super.setHostname(zoozcryptopocConfig.getString("smtp_host"));
		super.setPort(zoozcryptopocConfig.getInteger("port"));		
		super.setUsername(zoozcryptopocConfig.getString("user"));
		super.setPassword(zoozcryptopocConfig.getString("password"));
		super.setStarttls(StartTLSOptions.REQUIRED);
	}

}
