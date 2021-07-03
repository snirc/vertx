package snir.code.db;

import io.vertx.core.Handler;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class MongoDependencies {

    ArrayList<Runnable> dependencies = new ArrayList<>();

    public void add(String dbName, Handler<MongoLayer> handler) {
    	if(dbName.equals("otc")) {
    		System.out.println("################################# OTC OTC OTC OTC OTC ################################");
    		System.out.println("################################# OTC OTC OTC OTC OTC ################################");
    		System.out.println("################################# dependencies="+dependencies+" OTC OTC OTC ################################");
    	}
    	
        dependencies.add(()->{
        
    		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%    dependencies.add(()"+dbName+"     %%%%%%%%%%%%%%%%%%%%%%%%%");

        	handler.handle(MongoLayer.getInstance(dbName));
        }
                );
    }

    void executeAll() {
        dependencies.forEach(d-> d.run());
    }
}
