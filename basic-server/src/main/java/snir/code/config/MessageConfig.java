
package snir.code.config;

import io.vertx.core.json.JsonObject;

public class MessageConfig {

    
	public static JsonObject USER_MESSAGES = new JsonObject();
    public static int ERROR_CODE_FROM = 50000;

    
    public enum MessageKey {

    	//OK --------------------    	
    	OK(0),
    	GENERAL_ERROR(50000),
    	
    	READ_FILE_SUCCESS(1),
    	    	
    	READ_FILE_ERROR(50001),

    	PATH_PARAM(2),
    	PATH_PARAM_ERROR(50002),
    	
    	LOGIN_SUCCESS(6),
    	LOGIN_FAILED(50006),
    	SIGNUP_SUCCESS(7),
    	SIGNUP_FAILED(50007),   
    	SIGNUP_FAILED_USERDATA_INSERT_FAILED(500071),
    	SIGNUP_FAILED_USERNAME_DUPLICATE(50008),
    	LOGOUT(9),
    	LOGOUT_FAILED(50009),
    	AUTH_FAILED(50010),
    	
    	BUSINSS_FUNCTION_ADDED(11),
    	BUSINSS_FUNCTION_ADD_ERROR(50011),
    	BUSINSS_FUNCTION_REMOVE(12),
    	BUSINSS_FUNCTION_REMOVE_ERROR(50012),
    	GET_BUSINSS_FUNCTION(13),
    	GET_BUSINSS_FUNCTION_ERROR(50013),    	

    
    	
    	
    	//Mongo
    	MONGO_DATA_UPDATED(20),
    	MONGO_DATA_UPDATE_ERROR(50020),
    	MONGO_GET_DATA(21),
    	MONGO_GET_DATA_ERROR(50021),
    	MONGO_INSERET_DOC(22),
    	MONGO_INSERET_DOC_ERROR(50022),
    	FIND_DOC_AND_MOVE_COLLECTION(23),
    	FIND_DOC_AND_MOVE_COLLECTION_ERROR(50023),
    	
    	
    	
    	//Rule Engine
    	RE_MODEL_GENERATED(600),
    	RE_MODEL_GENERATED_ERROR(50600),
    	RE_SEND_MODEL_FIELDS(601),
    	RE_SEND_MODEL_FIELDS_ERROR(50601),
    	RULE_SET_ADDED(602),
    	RULE_SET_ADD_ERROR(50602),    	
    	RULE_SET_FOUND(603),
    	RULE_SET_NOT_FOUND(50603),
    	RULE_SET_EXIST(50604),
    	RULE_ENV_ADDED(605),
    	RULE_ENV_ADDED_ERROR(50605),
    	RULE_SET_DEPLOYED(606),
    	RULE_SET_DEPLOYED_ERROR(50606),
    	RULE_FIELDS_STRUCTURES_SET(607),
    	RULE_FIELDS_STRUCTURES_ERROR(50607),
    	RULE_FIELDS_STRUCTURE_SET(608),
    	RULE_FIELDS_STRUCTURE_ERROR(50608),
    	RULE_FIELDS_STRUCTURE_FOUND(609),
    	RULE_FIELDS_STRUCTURE_FOUND_ERROR(50609),
    	RULE_DEPLOY(610),
    	RULE_DEPLOY_FAILED(50610),
    	
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
