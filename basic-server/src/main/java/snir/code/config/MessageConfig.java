
package snir.code.config;

import io.vertx.core.json.JsonObject;

public class MessageConfig {

    
	public static JsonObject USER_MESSAGES = new JsonObject();
    public static int ERROR_CODE_FROM = 50000;

    
    public enum MessageKey {

    	//OK --------------------    	
    	OK(0),
    	GENERAL_ERROR(10000),
    	
    	READ_FILE_SUCCESS(100),
    	    	
    	READ_FILE_ERROR(10000),

    	PATH_PARAM(101),
    	PATH_PARAM_ERROR(10001),
    	
    	LOGIN_SUCCESS(106),
    	LOGIN_FAILED(10006),
    	SIGNUP_SUCCESS(107),
    	SIGNUP_FAILED(10007),   
    	SIGNUP_FAILED_USERDATA_INSERT_FAILED(10007),
    	SIGNUP_FAILED_USERNAME_DUPLICATE(10008),
    	LOGOUT(109),
    	LOGOUT_FAILED(10009),
    	AUTH_FAILED(10010),
    	
    	BUSINSS_FUNCTION_ADDED(110),
    	BUSINSS_FUNCTION_ADD_ERROR(10010),
    	BUSINSS_FUNCTION_REMOVE(112),
    	BUSINSS_FUNCTION_REMOVE_ERROR(10012),
    	GET_BUSINSS_FUNCTION(113),
    	GET_BUSINSS_FUNCTION_ERROR(10013),   
    	
    	
    	//Mongo
    	MONGO_DATA_UPDATED(200),
    	MONGO_DATA_UPDATE_ERROR(20000),
    	MONGO_GET_DATA(201),
    	MONGO_GET_DATA_ERROR(20001),
    	MONGO_INSERET_DOC(202),
    	MONGO_INSERET_DOC_ERROR(20002),
    	FIND_DOC_AND_MOVE_COLLECTION(203),
    	FIND_DOC_AND_MOVE_COLLECTION_ERROR(20003),
    	
    	
    	
    	//Stock
    	GET_STOCK_SUCCESS(300),
    	GET_STOCK_ERROR(30000),  
    	GET_DB_STOCK_SUCCESS(301),
    	GET_DB_STOCK_ERROR(30001), 
    	STOCK_MOVEMENT(302),
    	STOCK_MOVEMENT_ERROR(30002),  
    	STOCK_SEARCH_RESULT(303),
    	STOCK_SEARCH_ERROR(30002),  
    	
    	END(99999) ;
    	
    	
        int val;
        MessageKey(int i) {
            this.val = i;
        }
        public int val(){
            return val;
        }


    }


    public enum Method {
     
    	GET("getAbs"),
        POST("postAbs");

        String val;
        Method(String i) {
            this.val = i;
        }
        public String val(){
            return val;
        }

    }
}
