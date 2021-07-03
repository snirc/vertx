package snir.code.config;


import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;

public enum RequestHeader {
    PK,
    UID,
    AID,
    TKN,
    u_ad,
    a_ad,
    OPT;

    public String str(){
        return toString();
    }

    /**
     * Retrieve the user id from the header of the request
     * @param routingContext
     * @return
     * @throws Exception
     */
    public static int getUserId(RoutingContext routingContext)  {
        try {
            return  Integer.valueOf(routingContext.request().getHeader(UID.str()));
        }catch (Exception e){
            try {
                return routingContext.getBodyAsJson().getInteger("user_id");
            }catch (Exception e2){
                return -1;
            }
        }
    }

    public static int getOptionsInt(RoutingContext routingContext)  {
        try {
            return Integer.parseInt(routingContext.request().getHeader(OPT.str()));
        }catch (Exception e){
            try {
                return routingContext.getBodyAsJson().getInteger("OPT");
            }catch (Exception e2){
                return -1;
            }
        }
    }

   
    public static String getToken(RoutingContext routingContext) {
    	try {
    	String token = routingContext.request().getHeader(TKN.str());
    	
    	if ("".equals(token)) {
    		return null;
    	}
    	return token;
    	
    	}catch (Exception e) {
    		return null;
    	}
	}


    public static String getParameter(RoutingContext routingContext, String parameter)  {
        try {
            return routingContext.request().getHeader(parameter);
        }catch (Exception e){
            try {
                return routingContext.getBodyAsJson().getString(parameter);
            }catch (Exception e2){
                return null;
            }
        }
    }

}
