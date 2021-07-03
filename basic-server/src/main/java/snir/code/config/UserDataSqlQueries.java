package snir.code.config;

public class UserDataSqlQueries {


	public static String RETRIEVE_USER_PREFERENCES = "SELECT get_user_preferences('%d') res FROM dual";


//	INs:
//	IN p_user_id			int(10),     #1
//	in p_settings_2fa_id    varchar(45), #2
//	IN p_question_json  	JSON,		 #3
//	OUTs:
//	OUT p_user_type			int(1),		 #4
//	OUT p_account_id 		int(10),	 #5
//	OUT p_account_type		int(1),		 #6	
//	OUT p_update_status 	int(1),		 #7
//	OUT p_rejection_reason 	varchar(45)  #8
//	status OK in 3 + out 5 = 8 
	public static String SAVE_USER_SIGNUP_QUESTIONS = "call users_data.save_signup_questions(?,?,?,?,?,?,?,?)"; 
    // From MySQL_Users.sql
    /** INs:
     p_user_id 	INTEGER(10),
     p_user_name VARCHAR(45),
     p_email 	VARCHAR(45)) */
    // CONCAT JSON of: 'user_id','user_name','first_name','last_name','type','email',telephone', 'user_status')))
    // OUT: v_users_json    
    public static String DB_SWISS_QUERY_USER_INFO = "SELECT users_data.get_user_info(%d,'%s') res FROM dual";

    //From MySQL_Users.sql   
    /**INs:
	in UID 					int(10),
	in v_settings_2fa_id  	varchar(45),
	in phone_number 		varchar(15),
	in login_level 			varchar(4),
	OUTs:
	out p_create_status 	integer(10),
	out p_error_message  	varchar(45)
    // Status OK: 3 INs + 2 OUTs = 5*/
    
    public static String SETUP_2FA_DATA = "Call users_data.setup_2fa_data(?,?,?,?,?)";
    
    
    //From MySQL_Users.sql   
    /**INs:
	in v_user_id 				int(10),
	in v_settings_2fa_id 		varchar(45),
	in v_2fa_settings 			Json,
	
	out p_create_status 		integer(10),
	out p_error_message  		varchar(45)
    // Status OK: 3 INs + 2 OUTs = 5
     */
    
    public static String UPDATE_2FA_SETTINGS= "Call users_data.update_2fa_settings(?,?,?,?,?)";
    
    /**
    INs:
    in  v_user_id					integer(10),
    in  v_update_status_type        int, # email=1 , sms=2
    
    OUTs:
    OUT p_user_type 				int,
    OUT p_account_type              int,
    OUT p_change_status				integer,
    OUT p_change_rejection_reason	varchar(25),
    OUT p_status 					int **/
    // Status OK: 2 INs + 5 OUTs = 7
    public static String UPDATE_REGISTER_STATUS = "CALL users_data.update_register_status(?,?,?,?,?,?,?)";
    
    
    // From dml_procedures.sql
    /** INs:
     IN  p_user_id					INTEGER,
     IN  p_new_user_status 			INTEGER,
     OUTs:
     OUT p_change_status				INTEGER,
     OUT p_change_rejection_reason	VARCHAR(25) */
    // Status OK: 2 INs + 2 OUTs = 4
    // myMarker
    public static String DB_UPDATE_USER_STATUS = "CALL users_data.update_user_status(?, ?, ?, ?)";

    // From dml_procedures.sql
    /** INs:
     IN  p_user_id					INTEGER,
     IN  p_account_id					INTEGER,
     IN  p_new_account_status 			INTEGER,
     OUTs:
     OUT p_change_status				INTEGER,
     OUT p_change_rejection_reason	VARCHAR(25) */
    // Status OK: 3 INs + 2 OUTs = 5
    
    public static String DB_UPDATE_ACCOUNT_STATUS = "CALL users_data.update_account_status(?, ?, ?, ? , ?)";
    
//  in p_user_id                    int(10),
//  in p_account_id                 int(10),
//  out p_user_type                   int(10),
//  out p_account_type                int(10),
//  out p_create_status			    integer,
//  out p_reject_reason			    varchar(100)
  // Status 0: 2 INs + 4 OUTs = 6
  
  public static String DB_GET_USER_ACCOUNT_TYPES = "CALL useres_data.admin_get_user_account_types(? ,?, ?, ?, ?, ?)";
  
  /**
  //From MySQL_Accounts.sql
  /**INs:
  p_user_id varchar(45)
  OUTs:
  v_user_id_json
  // Status OK: 1 INs + 0 OUTs = 1
  */
 
 public static String DB_SWISS_QUERY_GET_USERID_WITH_EMAIL = "SELECT users_data.get_user_id_with_email('%s') res FROM dual";
  

 // From MySQL_Accounts.sql
 /** INs:
      p_user_id INTEGER(10),
      p_account_id INTEGER(10)) */
 // CONCAT JSON of: 'user_id','account_id','type''last_name','first_name','middle_name','date_of_birth','nationality','gender','country_code','area_code','phone_number','email','permanent_address_as_residental','employment_status','Industry','job_title','employer_name','employer_country','employer_state_province','employer_city_town','employer_district','employer_building_no','employer_street_name','employer_postal_zipcode','source_of_funds','net_worth','expected_level_of_investment','experience_stock_market','experience_equities','experience_derivatives','understAND_derivatives','understAND_risk_tradINg_crypto','verification_level'
 // OUTs: v_accounts_json
 // Status OK: 2 INs + 0 OUTs = 2
 
 public static String DB_SWISS_QUERY_ACCOUNT_INFO              = "SELECT users_data.get_account_info(%d,%d) res FROM dual";

  
  
 // From MySQL_Accounts.sql
 /** INs:
      p_user_id INTEGER(10),
      p_account_id INTEGER(10) */
 // CONCAT JSON of: 'user_id','account_id','address_type','country','state_province','city_town','buildINg_no','street_name','apartment_no','postal_zipcode'
 // OUTs: v_account_addresses_json
 // Status OK: 2 INs + 0 OUTs = 2
 
 public static String DB_QUERY_ACCOUNT_ADDRESSES = "SELECT users_data.get_account_addresses(%d,%d) res FROM dual";
  
  
 // From MySQL_Accounts.sql
 /** INs:
  p_user_id INTEGER(10),
  p_account_id INTEGER(10)) */
 // CONCAT JSON of: 'user_id','account_id','upload_date_time','document_type','approved_rejected','documment_location','issue_date','expiration_date','document_number','issuINg_country','first_name','last_name','middle_name_1','middle_name_2','file_front','file_back','approval_date_time'
 // OUTs: v_account_documents_json
 // Status OK: 2 INs + 0 OUTs = 2
 
// public static String DB_QUERY_ACCOUNT_DOCUMENTS = "SELECT users_data.get_account_documents(%d,%d) res FROM dual";

// INs:
// IN p_user_id integer(10),
// IN p_account_id integer(10)
  
 public static String GET_ACCOUNT_DOCUMENTS = "call users_data.get_account_documents(?,?)";
  
 
 
 
 
 public static String GET_ACCOUNT_DOCUMENT_IMG = "call users_data.get_document_images(?,?,?,?,?)";
 
 
 
 
//save_account_financial_details()
 /** INs:
  IN p_user_id	 			  	  	INTEGER(10) UNSIGNED,
  IN p_account_id	 			      	INTEGER(10) UNSIGNED,
  IN p_employment_status		      	TINYINT(1),
  IN p_industry					  	VARCHAR(45),
  IN p_job_title				   	  	VARCHAR(45),
  IN p_employer_name				  	VARCHAR(45),
  IN p_employer_country				TINYINT(4),
  IN p_employer_state_province		VARCHAR(45),
  IN p_employer_city_town			  	TINYINT(4),
  IN p_employer_district			  	VARCHAR(45),
  IN p_employer_building_no			VARCHAR(45),
  IN p_employer_street_name			VARCHAR(45),
  IN p_employer_postal_zipcode		VARCHAR(45),
  IN p_source_of_funds				VARCHAR(45),
  IN p_net_worth					  	INT(11),
  IN p_expected_level_of_investment   INT(11),
  IN p_experience_stock_market		BIT(1),
  IN p_experience_equities			BIT(1),
  IN p_experience_derivatives		  	BIT(1),
  IN p_understand_derivatives		    BIT(1),
  IN p_understand_risk_trading_crypto BIT(1),
  OUTs:
  OUT p_status			  	    	INTEGER,
  OUT p_reject_reason       			VARCHAR(45) */
//21 INs + 2 OUTs = 23
 
 public static String DB_QUERY_SAVE_FINANCIAL_DETAILS = "CALL crypto.save_account_financial_details (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

  
  
//save_account_identity_details()
 /** INs:
		IN p_user_id	 			  INTEGER(10) UNSIGNED,
		IN p_account_id	 			  INTEGER(10) UNSIGNED,
		IN p_account_type	 		  TINYINT(1) UNSIGNED,
		IN p_last_name	 			  VARCHAR(45),
		IN p_first_name	     		  VARCHAR(45),
		IN p_middle_name	 		  VARCHAR(45),
		IN p_date_of_birth 			  VARCHAR(45),
		IN p_nationality	 		  TINYINT(4),
		IN p_gender		 			  CHAR(1),
		IN p_suffixLastName           VARCHAR(45),
		IN p_country_code	          INT(4),
		IN p_area_code	 			  INT(4),
		IN p_phone_number	 		  INTEGER(11),
		IN p_landline_number	      INT(11),
		IN p_landline_country_code	  INT(4),
		IN p_email		 			  VARCHAR(45),
		IN p_employer                 VARCHAR(45),    
		IN p_job                      VARCHAR(45),  
		IN p_source_funds             VARCHAR(45),        
		IN p_why_use_bitzuz           VARCHAR(45),
		OUTs: 
		OUT p_status			  	  INTEGER,
		OUT p_reject_reason 		  VARCHAR(45)*/
	//20 INs + 2 OUTs = 22
 public static String DB_SWISS_QUERY_SAVE_ACCOUNT_CONTACT_DETAILS = "CALL users_data.save_account_contact_details (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

  
//insert_update_account_addresses()
 /** INs:
  IN p_user_id          INTEGER(10),
  IN p_account_id       INTEGER(10) UNSIGNED,
  IN p_address_type     CHAR(1),
  IN p_country          VARCHAR(45),
  IN p_state_provINce   VARCHAR(45),
  IN p_city_town        VARCHAR(45),
  IN p_buildINg_no      VARCHAR(45),
  IN p_street_name      VARCHAR(45),
  IN p_apartment_no	  VARCHAR(45),
  IN p_postal_zipcode	  VARCHAR(45),
  IN p_create_date_time TIMESTAMP,
  OUTs:
  OUT p_status 		  INTEGER (1),
  OUT p_reject_reason   VARCHAR(45) */
//12 INs + 2 OUTs = 14
 

 public static String DB_QUERY_INSERT_ACCOUNT_ADDRESSES = "CALL users_data.insert_update_account_addresses (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

//insert_update_account_addresses()
 /** INs:
  IN p_user_id          INTEGER(10),
  IN p_account_id       INTEGER(10) UNSIGNED,
  IN p_first_name          VARCHAR(45),
  IN p_middle_name   VARCHAR(45),
  IN p_last_name        VARCHAR(45),
  IN p_employer_city_town      VARCHAR(45),
  IN p_employer_country      VARCHAR(45),
  IN p_employer_state_province	  VARCHAR(45),
  IN p_gender     CHAR(1),
  OUTs:
  OUT p_status 		  INTEGER (1),
  OUT p_reject_reason   VARCHAR(45) */
//11 INs + 2 OUTs = 13
 //no one uses this 
// public static String DB_QUERY_INSERT_ACCOUNT_DETAILS = "CALL users_data.insert_update_account_details (?,?,?,?,?,?,?,?,?,?,?)";

 // ----------------------------------------------- //

 //approve_account_ducument ()

 /** IN p_bo_user_id	 			  	  	INTEGER(10) UNSIGNED,
  IN p_crypto_user_id	 			  	INTEGER(10) UNSIGNED,
  IN p_document_id	 			    INTEGER(10) UNSIGNED,
  IN p_account_id	 			      	INTEGER(10) UNSIGNED,
  OUT p_status			  	    	INTEGER,
  OUT p_failure_reason       			VARCHAR(45)**/

 // 4 INs + 2 OUTs = 6
 
 public static String DB_QUERY_APPROVE_ACCOUNT_DOCUMENT = "CALL users_data.approve_account_ducument (?,?,?,?,?,?)";

  
 // ----------------------------------------------- //
 //reject_account_ducument ()

 /**
  IN p_bo_user_id	 			  	  	INTEGER(10) UNSIGNED,
  IN p_crypto_user_id	 			  	INTEGER(10) UNSIGNED,
  IN p_document_id	 			    INTEGER(10) UNSIGNED,
  IN p_account_id	 			      	INTEGER(10) UNSIGNED,
  IN p_rejection_reason_code			INTEGER(3),
  IN p_rejected_reason				VARCHAR(200),
  OUT p_status			  	    	INTEGER,
  OUT p_failure_reason       			VARCHAR(45)
  **/

 // 46INs + 2 OUTs =
 // no one uses this 
// public static String DB_QUERY_REJECT_ACCOUNT_DOCUMENT = "CALL users_data.reject_account_ducument (?,?,?,?,?,?,?,?)";

//=======================
//From MySQL_Users.sql
//=======================

//get_accounts_for_user()
  /** INs:
   p_user_id INTEGER(10) */
//1 INs + 0 OUTs = 1
 
  public static String DB_QUERY_GET_ACCOUNTS_FOR_USER = "SELECT users_data.get_accounts_for_user(%d) res FROM dual";

  

//save_account_document()
   /** INs:
    IN  p_user_id			INTEGER,
    IN  p_account_id		INTEGER,
    IN  p_document_id		INTEGER,
    IN  p_document_number	INTEGER,
    IN  p_document_type		INTEGER,
    IN  p_issue_date		DATE,
    IN  p_expiration_date	DATE,
    IN	p_issuing_country	VARCHAR(3),
    IN  p_first_name		VARCHAR(45),
    IN  p_last_name			VARCHAR(45),
    IN  p_middle_name		VARCHAR(45),
    IN  p_document_front	LONGBLOB,
    IN  p_document_back		LONGBLOB,
    OUTs:
    OUT p_status 			INTEGER (1),
    OUT p_reject_reason 	VARCHAR(45) */
//13 INS + 2 OUTs = 15
 
//  in  p_user_id					integer,
//  in  p_account_id				integer,
//  in  p_document_id				integer,
//  in  p_document_type				integer,
//  in  p_document_number			integer,
//  in  p_issue_date				date,

//  in  p_expiration_date			date,
//  in	p_issuing_country			varchar(3),
//  in  p_document_front			longblob,
//  in  p_document_back				longblob,
//  in  p_document_front_file_name 	varchar(45),
//  in  p_document_back_file_name  	varchar(45),

//  OUT p_status 					INTEGER (1),
//  OUT p_reject_reason 			VARCHAR(45)
// in 12 out 2 = 14  
   public static String DB_QUERY_SAVE_ACCOUNT_DOCUMENT = "call users_data.save_account_document(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; // 14
//   public static String DB_QUERY_SAVE_ACCOUNT_DOCUMENT = "call users_data.save_account_document(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; // 17

   //IN's:
//   in  p_user_id					integer,		
//   in  p_account_id				integer,		
//   in  p_document_type			integer,		
//   in  p_document_number			integer,		
//   in  p_document_front			longblob,  		
//   in  p_document_back			longblob,  		
//   in  p_issue_date				date,			
//   in  p_expiration_date			date,			
//	 OUT's:
//   OUT p_status 					INTEGER (1),
//   OUT p_reject_reason 			VARCHAR(45)
// 	 in 8 out 2 = 10     
  public static String SAVE_ACCOUNT_ID_DOCUMENT = "call users_data.save_account_id_document(?,?,?,?,?,?,?,?,?,?)";

//  IN's:
//  in  p_user_id					integer,		#1
//  in  p_account_id				integer,		#2
//  in  p_document_type				integer,		#3
//  in  p_document_front			longblob,  		#4
//  OUT's:
//  OUT p_status 					INTEGER (1),
//  OUT p_reject_reason 			VARCHAR(45)
  // in 5 out 2 = 7
  public static String DB_QUERY_SAVE_SINGLE_ACCOUNT_DOCUMENT = "call users_data.save_single_account_document(?,?,?,?,?,?)"; 
// retrieve_account_document()
    /** INs:
     IN  	p_user_id					integer,
     IN  	p_account_id				integer,
     IN  	p_document_id				integer,
     IN		p_document_type_in			integer,
     OUTs:
     OUT	p_document_type_out			integer,
     OUT  	p_document_number			integer,
     OUT 	p_issue_date				date,
     OUT	p_expiration_date			date,
     OUT 	p_issuing_country			varchar(3),
     OUT 	p_first_name				varchar(45),
     OUT 	p_last_name				varchar(45),
     OUT 	p_middle_name				varchar(45),
     OUT 	p_document_front			longblob,
     OUT 	p_document_back			longblob,
     OUT 	p_status 					INTEGER (1),
     OUT 	p_reject_reason 			VARCHAR(45)
     [null,null,null,null,"INTEGER","INTEGER","DATE","DATE","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR","INTEGER","VARCHAR"] */
// 4 INs + 12 OUTs = 16
 
    public static String DB_QUERY_ACCOUNT_DOCUMENT = "call crypto.retrieve_account_document(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  
    /**
    in  p_user_id					integer,
    in  p_account_id				integer,
    in  p_document_id				integer,
    OUT p_status 					INTEGER (1),
    OUT p_reject_reason 			VARCHAR(45)
    */

   public static String DB_QUERY_REMOVE_ACCOUNT_DOCUMENT = "call crypto.remove_account_document(?,?,?,?,?)";

   public static String DB_QUERY_REMOVE_ACCOUNT_DOCUMENT_TYPE = "call crypto.remove_account_document_type(?,?,?,?,?)";
  
//   INs:
//   in p_user_id 		integer(10),
//   in p_account_id 	integer(10),
//   in p_kyc_id 		varchar(50),
//	 OUTs:
//   out v_change_status int(1),
//   out v_reject_reason varchar(45)
//   OK: 3 in + 2 out = 5
   public static String ADD_KYC_ID = "call users_data.add_kyc_entity(?,?,?,?,?) ";
   /**
    IN 	p_user_id	 			  	INTEGER(10) UNSIGNED,
    IN 	p_account_id	 			INTEGER(10) UNSIGNED,
    out p_financial_details			JSON,
    out p_bank_statement_details		JSON,
    OUT p_status			  	    	INTEGER,
    OUT p_reject_reason       			VARCHAR(45)
    */

   public static String DB_QUERY_RETRIVE_ACCOUNT_FINANCIAL_DETAILS = "call crypto.retrieve_account_financial_details(?,?,?,?,?,?)";
  
  
   /*
   * `SELECT USERS BY PARAMS`
in p_user_id int(10),
in p_user_name varchar(45),
in p_last_name varchar(45),
in p_email varchar(45),
in from_date varchar(45),
in to_date varchar(45)

in+out = 10*/

   public static String SELECT_USERS_BY_PARAMS = "call users_data.get_users_by_params(?,?,?,?,?,?,?,?);";
  
	   /*
	   * `SELECT USERS BY ID`
	in p_user_id int(10),
	in+out = 1*/
   public static String GET_USERS_BY_ID = "call users_data.get_users_by_id(?);";
  
   /*
    *  `get_accounts_by_params`
    in p_user_id int(10),
    in p_account_id int(10),
    in p_last_name varchar(45),
    in p_email varchar(45),
    in from_date varchar(45),
    in to_date varchar(45)
     in+out = 6
    * */

    public static String SELECT_ACCOUNTS_BY_PARAMS = "call users_data.get_accounts_by_params(?,?,?,?,?,?);"; 
    
    
    /*
     *  `get_accounts_by_id`
     in p_user_id int(10),
      in+out = 2
     * */

     public static String SELECT_ACCOUNTS_BY_ID = "call users_data.get_accounts_by_id(?,?);";
  
  
     /*
     bo_get_all_account_documents`(

   in p_user_id int(10),
   in p_account_id int(10)
   )
            in+out = 2
       * */

       public static String GET_ALL_ACCOUNT_DOCUMENTS = "call users_data.bo_get_all_account_documents(?,?);";
     
   /*
   `get_bo_users_by_params`(
	in p_user_id int(10),
	in p_user_name varchar(45),
	in p_last_name varchar(45),
	in p_email varchar(45),
	in from_date varchar(45),
	in to_date varchar(45)
	
	)
	* */
	public static String GET_BO_USERS_BY_PARAMS= "call users_data.get_bo_users_by_params (?,?,?,?,?,?);";
     
     

	/*
	`select_all_accounts`(
	
	 * */
	// needs to use Swiss DB 
	public static String GET__ALL_ACCOUNTS= "call users_data.select_all_accounts";  
     
 
	public static String GET_PENDING_APPROVAL_DOCUMENTS= "call users_data.get_pending_approval_documents";
     
     
     
	public static String UPDATE_USER_PREFERENCES = "UPDATE users_data.accounts SET first_name='%s', last_name='%s', date_of_birth='%s' WHERE user_id=%d";

     
	public static String RETRIEVE_CRYPTO_USERS = "call users_data.crypto_users_SELECT(?,?,?,?,?,?)";

	 // From dml_procedures.sql
	 /** INs:
	   in  p_user_name 		varchar(45), 
	   in  p_password_id     varchar(45), 
		  in  p_user_type 		integer, 
		  in  p_email 			varchar(45), 
		  in  p_telephone		varchar(20), 
		  in  p_user_password 	varchar(16),
		  OUTS
		  out p_signup_status  	integer,
		  out p_user_id			integer,
		  out p_reject_reason	varchar(45)*/
	 // Status OK: 6 INs + 3 OUTs = 9
	public static String DB_SIGNUP_USER = "CALL users_data.signup_user(?,?,?,?,?,?,?,?,?)";
//	public static String DB_SIGNUP_USER = "CALL users_data.signup_user(?,?,?,?,?,?,?,?)";

	     
     
	 /**
	 INs:
	 IN  p_user_id 		integer(10),
	 IN  p_password_id  varchar(45),  
	 IN  p_user_name 	varchar(45), 
	 IN  p_email 		varchar(45),
	 IN  p_password 	varchar(16),
	 OUTs:
	 OUT login_data_json longtext*/
	//Status OK: 5 INs + 1 OUTs = 6
	public static String DB_LOGIN_USER_SWISS = "CALL users_data.login_user(?,?,?,?,?,?)";
  
	// change_password()
    /** INs:
 	 IN p_change_reason             integer, 1=Change when old known 2=forgot password 
	 IN p_user_id					integer,
	 IN p_password_id      			varchar(45), 
	 IN p_current_password 			varchar(16),
	 IN p_new_password 				varchar(16),
	 OUTs:
	 OUT p_change_password_status  	integer **/
// 5 INs + 1 OUTs = 6
    public static String DB_QUERY_CHANGE_PASSWORD = "CALL users_data.change_password (?,?,?,?,?,?)";
    
    /**
    IN p_user_id		BIGINT(20),
	IN p_acc_id		BIGINT(20),
    IN p_nationality_id TINYINT(4),
    IN p_nationality_json	JSON,
    
    OUT p_status INT(1),
    OUT p_reject_reason VARCHAR(45)
     */
    public static final String ADD_USER_NATIONALITY = "CALL users_data.add_user_nationality(?,?,?,?,?,?)";
  
}





