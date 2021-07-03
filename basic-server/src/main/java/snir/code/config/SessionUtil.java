package snir.code.config;

import io.vertx.ext.web.RoutingContext;

public class SessionUtil {
    public enum SessionKey {
        private_key,
        public_key;
    }

    /**
     *
     * @param routingContext
     * @param key
     * @param val
     */
    public static void put(RoutingContext routingContext, SessionKey key, Object val){
     //   routingContext.session().put(key.toString(), val);
    }

    /**
     *
     * @param routingContext
     * @param key
     * @return
     */
    public static Object getObject(RoutingContext routingContext, SessionKey key){
        try {
            return routingContext.session().get(key.toString());
        }catch (Exception e){
            return null;
        }

    }

    /**
     *
     * @param routingContext
     * @param key
     * @return
     */
    public static Object getString(RoutingContext routingContext, SessionKey key){
        try {
            Object obj = routingContext.session().get(key.toString());
            return obj == null ? null : obj.toString();
        }catch (Exception e){
            return null;
        }


    }

}
