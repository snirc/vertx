package snir.code.services;

import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import io.vertx.core.json.JsonObject;
import snir.code.verticle.main.MainAbstractVerticle;

public class CommonService {

	private JsonObject version = createVersion();
	
	public JsonObject getVersion() {
		return version;
	}
	
	
	/**
	 * retrieve the version from pom file
	 * @return
	 */
	public static JsonObject createVersion() {
		
		
		MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model;
        JsonObject versionObject = new JsonObject();
		try {
			model = reader.read(new FileReader("pom.xml"));			
			
			versionObject.put(model.getArtifactId(), model.getVersion());
			for(Dependency dependency : model.getDependencies()) {
				if(dependency.getArtifactId() != null && dependency.getArtifactId().startsWith("SVC")) {
					versionObject.put(dependency.getArtifactId(),dependency.getVersion());	
				}
							
			}
			
		    
		} catch (IOException | XmlPullParserException e) {			
			versionObject.put("version", "Error: "+e.getMessage());
		}    
		
		System.out.println(versionObject.encodePrettily());
		
		return versionObject;
      
		
	}
}
