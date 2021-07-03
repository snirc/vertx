
package snir.code.config;

import java.io.IOException;
import java.io.UncheckedIOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.config.Config;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class VertxDeployer {
  
	private final static Logger logger = LoggerFactory.getLogger(VertxDeployer.class);
    private AbstractVerticle abstractVerticle;
    private Vertx vertx;
    
    public VertxDeployer(AbstractVerticle abstractVerticle) {
        this.abstractVerticle = abstractVerticle;
  
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                System.out.println("Vertx process is closing.......");
                closeVertx();
            }
        });

    }

    public void deploy(Vertx vertx){
    	this.vertx = vertx;
    	this.vertx.deployVerticle(abstractVerticle,res -> {
    	    if(res.succeeded()){

    	    	System.out.println("DEPLOYED ========");
            }else{
    	        res.cause().printStackTrace();
    	        System.exit(500);
            }
        });
    }

    public void deploy(Vertx vertx, Handler<AsyncResult<Boolean>> resultHandler){
        this.vertx = vertx;
        VertxOptions options = new VertxOptions()
        		.setBlockedThreadCheckInterval(1000*60*60);
        this.vertx.deployVerticle(abstractVerticle,res -> {
            if(res.succeeded()){
                resultHandler.handle(Future.succeededFuture(true));
            }else{
                res.cause().printStackTrace();
                System.exit(500);
            }
        });
    }
    
    public void deployToCluster(ClusterManager clusterManager) throws Exception{
    	VertxOptions vertxOptions = new VertxOptions()
    			.setClustered(true)
    			.setBlockedThreadCheckInterval(1000*60*60)
    			.setClusterManager(clusterManager);
    	Vertx.clusteredVertx(vertxOptions, resultHandler -> {
    		if(resultHandler.succeeded()) {
    			deploy(resultHandler.result());	
    		}else {
    			throw new UncheckedIOException(new IOException(resultHandler.cause()));
    		}
    		
    	});
    }
    
    public void deployToCluster(String groupName)throws Exception{
    	Config config = new Config();
    	config.getGroupConfig().setName(groupName);
    	deployToCluster(new HazelcastClusterManager(config));
    }

    public void closeVertx(){
        try {

        	this.vertx.deploymentIDs().forEach(vertx::undeploy);
            vertx.close();
        
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
