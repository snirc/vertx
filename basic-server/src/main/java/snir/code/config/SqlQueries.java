
/**
 * package snir.code.config;
 * <p>
 * public class SqlQueries {
 * public static String GET_USERS = "SELECT user_id, first_name, last_name, email from users";
 * <p>
 * public static String GET_USER = "SELECT u.user_id, u.user_name, u.user_status, tables.description as status from users u" +
 * " left JOIN tables ON (tables.table_id=18 and u.user_status=tables.main_code)" +
 * " WHERE u.user_name='%s' OR u.email='%s'";
 * <p>
 * public static String ADD_USER = "INSERT INTO users (user_name, password, email) " +
 * "VALUES ('%s','%s','%s')";
 * <p>
 * public static String TEST_DATA                          = "call crypto.test_get_account_name(?,?);";
 * public static String ALL_GLOBAL_DATA                    = "call crypto.retrieve_all_global_data(?,?,?,?,?)";
 * public static String ALL_CURRENCIES                     = "select crypto.get_currencies() res from dual";
 * <p>
 * public static String ALL_FOREIGN_EXCHANGES              = "select crypto.get_foreign_exchanges() res from dual";
 * public static String ALL_INSTRUMENTS                    = "select crypto.get_instruments() res from dual";
 * public static String ALL_TRADE_SUSPENSIONS              = "select crypto.get_exchange_trade_suspensions()  from dual";
 * public static String ALL_TABLES                         = "select crypto.get_tables() from dual";
 * <p>
 * public static String DB_QUERY_USER_ALL_DATA             = "call crypto.retrieve_all_user_data(?,?,?,?,?,?,?)";          // 1,@v_user_api_keys,@v_user_accounts,@v_users,@v_users_permissions,@v_incoming_messages,@v_outgoing_messages in MySQL_Users.sql
 * public static String DB_QUERY_USER_INFO                 = "select crypto.get_user_info(%d,'%s','%S') res from dual";    // p_user_id integer(10), p_user_name varchar(45), p_email varchar(45) in MySQL_Users.sql
 * public static String DB_SIGNUP_USER                     = "call crypto.signup_user(?,?,?,?,?,?,?)";
 * public static String DB_LOGIN_USER                      = "call crypto.login_user(?,?,?,?,?,?)";
 * public static String DB_UPDATE_USER_STATUS              = "call crypto.update_user_status(?,?,?,?)";
 * public static String DB_QUERY_USER_EVENTS               = "select crypto.get_user_events(%d) from dual";
 * public static String DB_QUERY_USER_API_KEYS             = "select crypto.get_user_api_keys(%d) from dual";
 * public static String DB_QUERY_USER_PERMISSIONS           = "select crypto.get_users_permissions(%d) from dual";
 * public static String DB_QUERY_USER_INCOMING_MESSAGES    = "select crypto.get_incoming_messages(%d) from dual";
 * public static String DB_QUERY_USER_OUTGOING_MESSAGES    = "select crypto.get_outgoing_messages(%d) from dual";
 * <p>
 * public static String DB_QUERY_ACCOUNT_ALL_DATA                = "call crypto.retrieve_all_account_data(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) from dual";
 * public static String DB_QUERY_ACCOUNT_INFO                    = "select crypto.get_account_info(?,?) from dual";
 * public static String DB_QUERY_ACCOUNT_ADDRESSES               = "select crypto.get_account_addresses(?,?) from dual";
 * public static String DB_QUERY_ACCOUNT_BALANCES                = "select crypto.get_account_balances(?,?) from dual";
 * public static String DB_QUERY_ACCOUNT_COMMISSIONS             = "select crypto.get_account_commissions_agreements(?,?) from dual";
 * public static String DB_QUERY_ACCOUNT_DOCUMENTS               = "select crypto.get_account_documents(?,?) from dual";
 * public static String DB_QUERY_ACCOUNT_EXTERNAL_WALLET         = "select crypto.get_account_external_wallets(?,?) from dual";
 * public static String DB_QUERY_ACCOUNT_INTERNAL_WALLETS        = "select crypto.get_account_internal_wallets(?,?) from dual";
 * public static String DB_QUERY_ACCOUNT_BLOCKCHAIN_TRANSACTIONS = "select crypto.get_blockchain_transactions(?,?)from dual";
 * public static String DB_QUERY_ACCOUNT_BLOCKCHAIN_WALLETS      = "select crypto.get_blockchain_wallets(?,?) from dual";
 * public static String DB_QUERY_ACCOUNT_EXECUTIONS              = "select crypto.get_executions(?,?) from dual";
 * public static String DB_QUERY_ACCOUNT_TRANSACTIONS            = "select crypto.get_transactions(?,?) from dual";
 * public static String DB_QUERY_ACCOUNT_API_KEYS                = "select crypto.get_user_api_keys(?,?) res from dual";
 * public static String DB_QUERY_ACCOUNT_EXCHANGE_PUBLIC_WALLETS = "select crypto.get_exchanges_public_wallets(?,?) from dual";
 * <p>
 * public static String DB_QUERY_KYC_ALLDATA               = "call crypto.retrieve_all_kyc_tables (?,?,?,?,?,?,?,?,?,?)";
 * public static String DB_QUERY_KYC_LANGUAGES             = "select crypto.get_single_table(1) from dual";
 * public static String DB_QUERY_KYC_ACCOUNT_TYPES         = "select crypto.get_single_table(3) from dual";
 * public static String DB_QUERY_KYC_DOCUMENT_TYPES        = "select crypto.get_single_table(5) from dual";
 * public static String DB_QUERY_KYC_NATIONALITIES         = "select crypto.get_single_table(9) from dual";
 * public static String DB_QUERY_KYC_EMPLOYMENT_STATUSES   = "select crypto.get_single_table(10) from dual";
 * public static String DB_QUERY_KYC_EMPLOYMENT_INDUSTRIES = "select crypto.get_single_table(11) from dual";
 * public static String DB_QUERY_KYC_SOURCE_FUNDS          = "select crypto.get_single_table(12) from dual";
 * public static String DB_QUERY_KYC_COUNTRIES             = "select crypto.get_countries from dual";
 * public static String DB_QUERY_KYC_COUNTRIES_STATES      = "select crypto.get_countries_states(?) from dual";
 * }
 * Added by Leon @26-03-2018 16:50
 * call crypto.retrieve_all_global_data(?,?,?,?,?); // compared to MySQL_Global.sql, should contain 4 and not 5 params, where all of them are OUTPUTs
 */
/**
 Added by Leon @26-03-2018 16:50
 call crypto.retrieve_all_global_data(?,?,?,?,?); // compared to MySQL_Global.sql, should contain 4 and not 5 params, where all of them are OUTPUTs
 */
package snir.code.config;

public class SqlQueries {
//	public static String UPDATE_2FA_SETTINGS= "Call crypto.update_2fa_settings(?,?,?,?)";
	
	
	/**
    INs:
    in v_user_id                     int(10),
    in v_user_type                    int(10),
    in v_account_id                    int(10),
    in v_account_type                 int(10),
    OUTs:
    out p_change_status                integer,
    out p_change_rejection_reason    varchar(25)
     4 ins  + 2 outs = 6
    */
	public static String COVERAGE_GET_AGG_BALANCES = "SELECT crypto.coverage_agg_balances('%s') res from dual";
	public static String COVERAGE_GET_ENABLED_EXCHANGES = "SELECT crypto.coverage_enabled_exchanges() res from dual";
	public static String GET_SUPPORTED_EXCHANGES_FOR_SYMBOL = "SELECT crypto.get_supported_exchanges_for_instrument('%s') res from dual";
//	public static String DB_QUERY_INSERT_ACCOUNT_DETAILS = "CALL crypto.insert_update_account_details (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String DB_ADMIN_GET_CURRENCIES_TABLE = "CALL crypto.admin_get_currencies_table()";
    public static final String SAVE_USER_AND_ACOUNT = "call crypto.save_user_and_account(?,?,?,?,?,?)";
	public static final String DB_GET_FUNDING_REQUESTS_BY_SYMBOL = "select crypto.get_funding_requests('%s',%d,%d,%d)res from dual;";
	public static final String DB_GET_FUNDING_OFFERS_BY_SYMBOL = "select crypto.get_funding_offers('%s',%d,%d,%d)res from dual;";
	public static final String DB_QUERT_INTEREST_BOOK = "";
	public static String DB_VALIDATE_NEW_DEPOSIT   =    "call crypto.validate_deposit(?,?,?);";
	public static final String DB_GET_PENDING_CS_TRANSACTIONS = "SELECT crypto.get_pending_wd_requests() res from dual;";
	public static final String CREATE_OTC_EXECUTION = "CALL crypto.create_otc_execution (?,?,?)";
	public static final String DB_GET_RECENT_EXECUTIONS = "CALL crypto.get_recent_executions() res from dual;";
	public static final String DB_BALANCE_CHANGE_FUNDING_MATCH = "call crypto.balance_change_funding_match(?,?,?,?,?,?,?,?,?,?)";// 10
	public static String DB_INSERT_NEW_DEPOSIT   =    "call crypto.insert_deposit(?,?,?,?,?,?,?,?,?,?,?,?);"; 																									// variables
	public static final String DB_UPDATE_ALL_BALANCES = "call crypto.update_balance(?,?,?,?,?,?)";// 6 variables
	public static final String DB_QUERY_SAVE_ORDER = "call crypto.save_order  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";// 22
	public static String DB_GET_OPEN_FUNDING_REQUESTS   =    "select crypto.get_open_funding_orders('B')res from dual;"; 																			// in/out
	public static String insertNewsLetrerSignupDetails= "CALL crypto.news_letter_signup(?,?,?,?,?)";																									// vars
	public static final String COVERAGE_GET_ORDERS = "call crypto.get_foreign_exchanges_orders(?,?)";
	public static final String COVERAGE_GET_EXECUTIONS = "call crypto.get_foreign_exchanges_executions()";
//    public static final String DB_GET_FUNDING_REQUESTS_BY_SYMBOL = "select crypto.get_funding_requests('%s',%d,%d,%d)res from dual;";
//    public static final String DB_GET_FUNDING_OFFERS_BY_SYMBOL = "select crypto.get_funding_offers('%s',%d,%d,%d)res from dual;";
//    public static final String DB_QUERT_INTEREST_BOOK = "";
//    public static final String DB_GET_PENDING_CS_TRANSACTIONS = "SELECT crypto.get_pending_wd_requests() res from dual;";
//    public static final String CREATE_OTC_EXECUTION = "CALL crypto.create_otc_execution (?,?,?)";
//    public static final String DB_GET_RECENT_EXECUTIONS = "CALL crypto.get_recent_executions() res from dual;";
//    public static final String DB_BALANCE_CHANGE_FUNDING_MATCH = "call crypto.balance_change_funding_match(?,?,?,?,?,?,?,?,?,?)";//10 variables
//    public static final String DB_UPDATE_ALL_BALANCES = "call crypto.update_balance(?,?,?,?,?,?)";//6 variables
//    public static final String DB_QUERY_SAVE_ORDER                             = "call crypto.save_order  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";// 22 in/out vars
//    public static final String COVERAGE_GET_ORDERS		= "call crypto.get_foreign_exchanges_orders ()";
//    public static final String COVERAGE_GET_EXECUTIONS = "call crypto.get_foreign_exchanges_executions ()";
//    public static String DB_QUERY_SAVE_OTC_ORDER                         = "call crypto.save_otc_order  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";// 24 in/out vars
    public static String DB_GET_OPERATIONS                         = "call crypto.admin_get_operations_for_server ()";// 24 in/out vars
    public static String DB_GET_GET_ORDERS                     = "SELECT crypto.REPLACE_get_orders(%d,%d,%d)res from dual;";
    public static String TEST_FOREGIN_EXCHANGE                  =  "SELECT crypto.test_foregin_exchange() res FROM dual";
    public static String GET_COVERAGE_PARAMS                  =  "call crypto.coverage_get_enabled_params()";
    public static String GET_COVERAGE_PENDING_AMOUNT		=  "CALL crypto.get_max_pending_coverage_amounts()";
    public static String GET_COVERAGE_OPEN_ORDERS		=  "CALL  crypto.get_open_fx_orders()";
    public static String COVERAG_UPDATE_API_PARAMS            =  "CALL crypto.update_api_params(?,?,?,?)";
    
    public static String GET_USER_ACCOUNT_EXECUTIONS = "SELECT crypto.get_account_executions(%d,%d,'%s') res from dual";
    /**
     IN p_fx_id	INT(4),
    IN p_api_params	LONGTEXT,
    
    OUT p_status INT(1),
    OUT p_reject_reason VARCHAR(45)
     */
    public static String GET_COVERAGE_API_PARAMS            =  "CALL crypto.get_api_params(?)";
    //IN p_exchange_id INT(4)
    
    
    /**
    IN p_instrument_id INT(4),
    
    OUT p_status	INT(1),
    OUT p_reject_reason	VARCHAR(45),
    OUT p_data_json		JSON
     */
    public static final String GET_INSTRUMENT_TRADING_DATA = "CALL crypto.get_instrument_monitor_data(?,?,?,?)";
    
    public static String GET_EXECUTION_BY_ID= "call crypto.admin_get_executions_by_id(?);"; 
    /* 
`get_executions_by_params`( 
in p_order_id BIGINT(20), 
     * */ 
    public static String COVERAGE_UPDATE_LIMIT_EXECUTOR = "call crypto.update_limit_executor(?,?,?,?)";
    
/* 
 * 	IN p_fx_id			INT(4),
	IN p_instrument_id	INT(10),
    
    OUT p_status		INT(1),
    OUT p_reject_reason	VARCHAR(45)
 * */
    
    public static String COVERAGE_REMOVE_CONFIG             ="CALL crypto.remove_coverage_config(?,?,?,?,?)"; 
    /** 
     IN p_instrument_id INT(4), 
    IN p_fx_id INT(4), 
     
    OUT p_status INT(1), 
    OUT p_reject_reason VARCHAR(45), 
    OUT p_new_params JSON 
     */ 
    public static String DB_DISABLE_COMMISSION = "CALL crypto.admin_disable_commission(?,?,?,?,?,?,?,?,?)"; 
    
    
    // From dml_procedures.sql 
//    CREATE PROCEDURE crypto.add_permission 
//    in  p_description                  varchar(100), 
//    out p_permission_create_status    integer, 
//    out p_permission_id               integer, 
//    out p_reject_reason    varchar(45) 
    // Status 0: 1 INs + 3 OUTs = 4 
    
    public static String COVARAGE_UPDATE_BALANCE_PARAMS		=  "CALL update_foreign_exchange_balance_params(?,?,?,?,?)";
    
    /**
    IN p_fx_id 		INT(3),
    IN p_currency	VARCHAR(10),
    IN p_balance_params	LONGTEXT,
    
    OUT p_status	INT(1),
    OUT p_reject_reason VARCHAR(45)
     */

	/**
	 * IN p_fx_id INT(3), IN p_currency VARCHAR(10), IN p_balance_params LONGTEXT,
	 * 
	 * OUT p_status INT(1), OUT p_reject_reason VARCHAR(45)
	 */
    

   public static String UPDATE_COVERAGE_PARAMS    ="CALL crypto.update_coverage_params(?,?,?,?,?)"; 

    
    /**
    in p_user_id int(10),
    in p_account_id varchar(10000),
     IN  p_symbols     varchar(10000),
    in p_limit int(4),
    in from_date varchar(45),
    in to_date varchar(45)
*/
//  5 INs + 0 OUTs = 5

public static String ADMIN_GET_EXECUTIONS_FOR_STATEMENT = "CALL crypto.admin_get_executions_for_statement(?,?,?,?,?,?)";

/**
in p_user_id int(10),
in p_account_id varchar(10000),
 IN  p_symbols     varchar(10000),
in p_limit int(4),
in from_date varchar(45),
in to_date varchar(45)
*/
//5 INs + 0 OUTs = 5

public static String ADMIN_GET_BALLANCES_FOR_STATEMENT = "CALL crypto.admin_get_ballances_for_statement(?,?,?,?,?,?)";

//From dml_procedures.sql
//CREATE PROCEDURE crypto.admin_disable_permission
/*
in  p_permission_id                integer(10),
out p_status                          integer,
out p_reason                        varchar(45)
*/
// Status 0: 1 INs + 2 OUTs = 3

public static String DB_DISABLE_PERMISSION = "CALL crypto.admin_disable_permission(?, ?, ?)";


/**
in p_transaction_id BIGINT(20),
in p_user_id int(10),
in p_account_id int(10),
in p_transaction_type INT(3),
in p_limit int(3),
in from_date varchar(45),
in to_date varchar(45)
*/
//  7 INs + 0 OUTs = 7

public static String ADMIN_GET_TRANSACTIONS_FOR_STATEMENT = "CALL crypto.admin_get_transactions_for_statement(?,?,?,?,?,?,?)";
    

    /** 
    IN p_instrument_id	INT(4),
    IN p_fx_id			INT(3),
    IN p_order_params	LONGTEXT,
    
    OUT p_status		INT(1),
    OUT p_reject_reason	VARCHAR(45)
     */ 
    public static String ADMIN_GET_TRANSACTIONS = "CALL crypto.admin_get_transactions_by_params(?,?,?,?,?,?,?,?)"; 
    
    // From dml_procedures.sql 
//    out p_operations               varchar(45) 
    //  0 INs + 1 OUTs = 1 
    
    public static String DB_ADD_ROLE_TO_USER_ACCOUNT               = "CALL crypto.add_role_to_user_account(? ,?, ?, ?, ?)"; 
    
    // From dml_procedures.sql 
//    CREATE PROCEDURE crypto.add_permission_to_user 
    //  in  p_user_id                      integer(10), 
    //  in  p_permission_id                integer(10), 
    //  in  p_parameters                    TINYBLOB, 
    //  out p_status                        integer, 
    //  out p_reason                        varchar(45) 
    // Status 0: 3 INs + 2 OUTs = 5 
    
    public static String DB_UPDATE_PERMISSION = "CALL crypto.admin_update_permission(?, ?, ?, ?, ?)"; 
    
    
 // From dml_procedures.sql 
//     CREATE PROCEDURE crypto.admin_create_commission 
//     in  p_user_id                    INT(10), 
//     in  p_account_id                 INT(10), 
//     in  p_account_type                TINYINT(3), 
//     in  p_transaction_type            TINYINT(2), 
//     in  p_expiration_date             DATETIME, 
//     in  p_from_step                   INT(10), 
//     in  p_to_step                     INT(10), 
//     in  p_comm_type                   TINYINT(2), 
//     in  p_comm_sum                    DECIMAL(10,4), 
//     in  p_comm_min                    DECIMAL(10,4), 
//     in  p_comm_max                    DECIMAL(10,4), 
//     in  p_symbol                      varchar(10), 
//     out p_create_status              integer, 
//     out p_reject_reason              varchar(45) 
     // Status 0: 11 INs + 2 OUTs = 13 
    public static String SELECT_ACCOUNT_BY_PARAMS = "call crypto.get_account_by_params(?,?,?,?,?,?,?,?,?,?,?);";   
    
    /* 
    *  `get_accounts_by_id` 
    in p_user_id int(10), 
     in+out = 2 
    * */
    public static String SELECT_COMMISSION_BY_PARAMS = "call crypto.admin_commission_by_params(?,?,?,?,?,?,?,?,?,?);"; 
    /* 
     * `SELECT USERS BY PARAMS` 
 in p_user_id int(10), 
in p_user_name varchar(45), 
in p_last_name varchar(45), 
in p_email varchar(45), 
in from_date varchar(45), 
in to_date varchar(45) 
 
 in+out = 10*/ 
	public static final String COVERAGE_GET_PRICE_FACTOR = "select crypto.get_price_factors() res from dual";
	public static final String COVERAGE_UPDATE_PRICE_FACTOR = "CALL crypto.update_price_factor(?,?,?,?,?)";
	/**
	 * IN p_fx_id INT(4), IN p_instrument_id INT(4), IN p_factor_price DOUBLE
	 * UNSIGNED,
	 * 
	 * OUT p_status INT(1), OUT p_reject_reason VARCHAR(45)
	 */
    public static String CREATE_INTERNAL_WALLET_TRANSFER = "CALL crypto.update_internal_wallet_balance (?,?,?,?,?,?,?,?)";
    public static String DB_EDIT_FUNDING_REQUEST = "call crypto.edit_funding_request(?,?,?,?,?,?,?,?,?);";
    public static String DB_EDIT_FUNDING_OFFER = "call crypto.edit_funding_offer(?,?,?,?,?,?,?,?,?,?);";
    public static String DB_CANCEL_FUNDING_ORDER = "call crypto.cancel_funding_order(?,?,?,?,?);";
    public static String DB_INSERT_NEW_TRANSACTION = "call crypto.insert_new_transaction(?,?,?,?,?,?,?,?,?,?,?);";
    public static String DB_GET_OPEN_FUNDING_OFFERS = "select crypto.get_open_funding_orders('S')res from dual;";
//    public static String DB_GET_OPEN_FUNDING_REQUESTS = "select crypto.get_open_funding_orders('B')res from dual;";
    public static String DB_NEW_FUNDING_ORDER_MATCH = "call crypto.create_new_funding_order_match(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    public static String DB_CLOSE_FUNDING_ORDERS = "call crypto.close_funding_orders(?,?);";
    public static String DB_CLOSE_FUNDING_ORDER_MATCHES = "call crypto.close_funding_order_matches(?,?);";
    public static String DB_CLOSE_FUNDING = "call crypto.close_funding(?,?);";
    public static String GET_LAST_FUNDING_ORDER_ID = "select LAST_INSERT_ID(order_id) from crypto.funding_orders";
    public static String DB_QUERY_GET_ACCOUNT_BALANCE = "SELECT crypto.get_account_balances(%s,%s) res from dual;";
    public static String DB_GET_BLOCKCHAIN_TX_BY_ADDRESS = "SELECT crypto.get_transactions_by_address(%s) res from dual;";
    public static String DB_GET_BLOCKCHAIN_TX_BY_ID = "SELECT crypto.get_transaction_by_id(%s) res from dual;";
    public static String DB_GET_BLOCKCHAIN_PENDING_TX = "SELECT crypto.get_blockchain_pending_transactions() res from dual;";

    public static String DB_GET_WIRE_TRANSFER          = "select crypto.get_wire_transfer() res from dual;";
    public static String GET_ADDRESS = "select address from crypto.account_internal_wallets " +
            "where user_id=%s and  account_id=%s and currency_symbol='%s' and status=1";

    public static String GET_BALANCE_WITH_IDS = "SELECT * FROM account_balances WHERE account_id=%s and user_id=%s and ('%s'=currency or '%s'='0' )";

    public static String DB_QUERY_ACCOUNT_WITHDRAWAL_REQUESTS = "select crypto.get_withdrawl_requests(%d,%d)res from dual";
    public static String DB_QUERY_ACCOUNT_DEPOSIT_REQUESTS = "select crypto.get_deposits(%d,%d,'%s',%d)res from dual";

    public static String GET_PENDING_TRANSACTIONS_ID_HASH = "select get_pending_transactions_ids() res from dual;";

    public static String GET_BO_USERS = "SELECT * FROM crypto.users WHERE type=3";

//    public static String GET_BO_USERS               ="SELECT * FROM crypto.users WHERE type=3";


    public static String CHANGE_PENDING_TRANSACTIONS_STATUS = "call crypto.update_blockchain_transaction_status(?,?,?,?);";

    public static String CREATE_NEW_WITHDRAW_REQUEST = "call crypto.create_withdraw_request(?,?,?,?,?,?,?,?,?,?,?,?,?);";

    public static String UPDATE_WITHDRAWAL_REQUESTS = "    call crypto.update_withdrawal_request(?,?,?,?,?);";


    public static String GET_PENDING_WITHDRAWAL_REQUESTS = "select crypto.get_pending_withdrawl_requests(%d,%d)res from dual;";

    public static String GET_UNSENT_WITHDRAWAL_REQUESTS = "select crypto.get_unsent_withdrawl_requests(%d,%d)res from dual;";
    public static String GET_ACTIVE_CURRENCIES = "select crypto.get_active_currencies(%d)res from dual;";


    public static String INSPECTOR_VERIFY_WITHDRAWAL = "call crypto.inspector_confirm_withdraw_request(?,?,?,?,?,?,?,?);";

    public static String CREATE_TRANSACTION = "call crypto.create_transaction(?,?,?,?,?,?,?,?,?,?,?);";

    public static String USER_VERIFY_WITHDRAWAL = "CALL user_verify_withdrawal_request(?,?,?,?,?,?);";

    public static String CHANGE_WALLET_STATUS = "call crypto.set_wallet_status(?,?,?,?,?,?,?,?,?);";


    public static String DB_QUERY_ORDERS_BY_PARAMS = "call crypto.retrieve_orders_by_params(?,?,?,?,?,?,?,?,?);";

    public static String DB_QUERY_NOTIFICATIONS_SET = "call crypto.set_notifications_settings(?,?,?);";
    // No funcrions applied
    public static String GET_USERS = "SELECT user_id, first_name, last_name, email FROM users";

    // No funcrions applied
    //NO ONE USES THIS
    public static String GET_USER = "SELECT u.user_id, u.user_name, u.user_status, tables.description as status FROM users u" +
            " left JOIN tables ON (tables.table_id=18 and u.user_status=tables.main_code)" +
            " WHERE u.user_name='%s' OR u.email='%s'";

    public static String UPDATE_NOTIFICATION_SETTINGS = "UPDATE users SET telephone='%s', notifications_settings='%s' WHERE user_id=%d";

    //public static String GET_INFO_FOR_NOTIFICATION = "SELECT telephone, email, notifications_settings FROM users WHERE user_id=%d";
    public static String GET_INFO_FOR_NOTIFICATION = "SELECT crypto.get_user_notifications_settings('%d') res FROM dual";

    //public static String RETRIEVE_CONFIG = "SELECT name,HighLevelParams,DailyLossMgmtParams,ForeignExchangeParams,ForeignExchStatisticsParams,LocalExchangeParams,OrdersAmountParams,PriceLevelsParams,TopOfBookParams,UncoveredPositionMgmtParams FROM crypto.market_maker JOIN crypto.instruments ON crypto.market_maker.instrument_id = crypto.instruments.instrument_id WHERE name='%s'";
    public static String RETRIEVE_CONFIG = "SELECT get_config_data('%s') res FROM dual";

    //public static String RETRIEVE_CONFIG_NAMES = "SELECT name FROM crypto.market_maker JOIN crypto.instruments ON crypto.market_maker.instrument_id = crypto.instruments.instrument_id WHERE symbol='%s'";
    public static String RETRIEVE_CONFIG_NAMES = "SELECT get_config_names('%s') res FROM dual";

    public static String UPDATE_CONFIG = "UPDATE crypto.market_maker SET name='%s',HighLevelParams='%s',DailyLossMgmtParams='%s',ForeignExchangeParams='%s',ForeignExchStatisticsParams='%s',LocalExchangeParams='%s',OrdersAmountParams='%s',PriceLevelsParams='%s',TopOfBookParams='%s',UncoveredPositionMgmtParams='%s' WHERE name='%s'";

    public static String TEST_CONFIG = "UPDATE crypto.market_maker SET config_test_object='%s' WHERE name='%s'";

    //public static String RETRIEVE_USER_PREFERENCES = "SELECT accounts.first_name, accounts.last_name, date_of_birth, password FROM crypto.users JOIN crypto.accounts ON accounts.user_id=users.user_id WHERE users.user_id=%d";
    public static String RETRIEVE_USER_PREFERENCES = "SELECT get_user_preferences('%d') res FROM dual";

    public static String UPDATE_USER_PREFERENCES = "UPDATE crypto.accounts SET first_name='%s', last_name='%s', date_of_birth='%s' WHERE user_id=%d";

    //public static String RETRIEVE_USER_DETAILS = "SELECT user_id, email FROM crypto.users WHERE user_id=%d";
    public static String RETRIEVE_USER_DETAILS = "SELECT get_user_details('%d') res FROM dual";

    public static String RETRIEVE_USER_PERMISSIONS = "SELECT get_user_permissions('%s') res FROM dual";

    //public static String RETRIEVE_ACCOUNT_EXECUTIONS = "SELECT * FROM crypto.v_all_executions WHERE create_date_time>'%s' and create_date_time<'%s' and account_id=%d";
    public static String RETRIEVE_ACCOUNT_EXECUTIONS = "SELECT get_account_executions('%s','%s','%d') res FROM dual";

    public static String RETRIEVE_LIVE_SUBSCRIPTIONS = "SELECT get_live_subscriptions('%d') res FROM dual";

    public static String RETRIEVE_INDICES = "SELECT * FROM crypto.indices";

    public static String RETRIEVE_CRYPTO_USERS = "call crypto.crypto_users_SELECT(?,?,?,?,?,?)";

    public static String GET_ALL_CHANNELS= "call crypto.get_all_channels";

    public static String ADD_ICON_TO_CURRENCY= "call crypto.add_icon_to_currency(?,?,?,?)";

    public static String GET_ALL_CURRENCIES= "call crypto.get_all_currencies()";

    public static String GET_ORDER_BY_ID= "SELECT crypto.get_order_by_id(%d) res FROM dual";

    public static String DB_GET_TRANSACTIONS_BY_CURRENCY = "SELECT crypto.get_transactions_by_currency('%s') res FROM dual;";

    public static String SAVE_ORDER = "call crypto.save_order(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    public static String DB_QUERY_SAVE_OTC_ORDER = "call crypto.save_otc_order  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";// 24 in/out vars

    public static String DB_GET_NEXT_VAL = "SELECT sequence.nextval('%s') res FROM dual;";

    // No funcrions applied
    public static String ADD_USER = "INSERT INTO users (user_name, password, email) " + "VALUES ('%s','%s','%s')";

    // test_get_account_name(?,?) function is missing in dml_procedures.sql, MySQL_Accounts.sql, MySQL_Global.sql,MySQL_KYC.sql, MySQL_Users.sql files
    public static String TEST_DATA = "call crypto.test_get_account_name(?,?);";

    // From MySQL_Global.sql
    // No INs
    // OUT v_currencies json, OUT v_exchange_trade_suspensions json, OUT v_foreign_exchanges json, OUT v_instruments json
    // Status PROBLEMATIC: 0 INs + 4 OUTs = 4 wildcards
    public static String ALL_GLOBAL_DATA = "call crypto.retrieve_all_global_data(?,?,?,?,?)";

    // From MySQL_Global.sql
    // No INs
    // CONCAT JSON of: 'currency_symbol','currency_description','active','wallet'
    // OUT: v_instruments_json JSON
    public static String ALL_CURRENCIES = "SELECT crypto.get_currencies() res FROM dual";

    // From MySQL_Global.sql
    // No INs
    // CONCAT JSON of: 'exchange_id','description','symbol','user_name','password','wss_url','rest_url','api_public_key','api_secret_key','market_fee','taker_fee','deposit_fee','enabled','rating'
    // OUT: v_foreign_exchanges_json
    public static String ALL_FOREIGN_EXCHANGES = "SELECT crypto.get_foreign_exchanges() res FROM dual";

    // From MySQL_Global.sql
    // No INs
    // CONCAT JSON of: 'instrument_id','curr_symbol_1','curr_symbol_2','symbol','instrument_description','instrument_type','price_precision','price_factor','qtty_units','min_required_trading','max_required_trading','trading_exchanges'
    // OUT: v_instruments_json
    public static String ALL_INSTRUMENTS = "SELECT crypto.get_instruments() res FROM dual";

    public static final String UPDATE_ASSET_MAX_PENDING_AMOUNT = "call crypto.update_max_pending_coverage_amount(?,?,?,?,?,?)";
    /**
    IN p_symbol	VARCHAR(15),
    IN p_amount DOUBLE,
    IN p_stop_loss DOUBLE,
    IN p_take_profit DOUBLE,
    
    OUT p_status	INT(1),
    OUT p_reason	VARCHAR(45)
     */
    
    
    
    //    public static String TEST_FOREGIN_EXCHANGE = "SELECT crypto.test_foregin_exchange() res FROM dual";

    // From MySQL_Global.sql
    // No INs
    // CONCAT JSON of:
    // OUT: 'instrument_id','from_date_time','to_date_time','message_to_traders'
    public static String ALL_TRADE_SUSPENSIONS = "SELECT crypto.get_exchange_trade_suspensions() res FROM dual";

    // From MySQL_Global.sql
    /** INs:
     p_table_id */
    // CONCAT JSON of: 'table_id','main_code','sub_code','description','display_sort'
    // OUT: v_tables_json
    public static String ALL_TABLES = "SELECT crypto.get_tables(%d) res FROM dual";

    // From MySQL_Users.sql
    /** INs:
     v_user_id
     OUTs:
     OUT v_user_api_keys 	json,
     OUT v_user_accounts 	json,
     /** out v_user_events 	json, */   // <-- Commented in original JAVA sourcecode
    /** OUT v_users 			json,
     OUT v_users_permissions json,
     OUT v_incoming_messages json,
     OUT v_outgoing_messages json */
    // Status OK: 1 INs + 6 OUTs = 7
    public static String DB_QUERY_USER_ALL_DATA = "CALL crypto.retrieve_all_user_data(?,?,?,?,?,?)";

    // From MySQL_Users.sql
    /** INs:
     p_user_id 	INTEGER(10),
     p_user_name VARCHAR(45),
     p_email 	VARCHAR(45)) */
    // CONCAT JSON of: 'user_id','user_name','first_name','last_name','type','email','default_language','user_status','enabled_currencies','currency_for_fees','user_google_authenticator','user_twilio_authentication','user_fido_authentication','login_2fa','withdrawal_confirmation_2fa','password_changes_2fa','api_key_creation_2fa')))
    // OUT: v_users_json
    //EXAMPLE FROM SNIR
    public static String DB_QUERY_USER_INFO = "SELECT crypto.get_user_info(%d,'%s') res FROM dual";

    // From dml_procedures.sql
    /** INs:
     IN  p_user_name 		VARCHAR(45),
     IN  p_user_type 		INTEGER,
     IN  p_email 			VARCHAR(45),
     IN  p_user_password 	VARCHAR(16),
     OUTs:
     OUT p_signup_status  	INTEGER,
     OUT p_user_id			INTEGER,
     OUT p_reject_reason	VARCHAR(45) */
    // Status OK: 4 INs + 3 OUTs = 7
    //public static String DB_SIGNUP_USER = "CALL crypto.signup_user(?,?,?,?,?,?,?,?)";

    // From dml_procedures.sql
    /** INs:
      in  p_user_name 		varchar(45), 
	  in  p_user_type 		integer, 
	  in  p_email 			varchar(45), 
	  in  p_telephone		varchar(20), 
	  in  p_user_password 	varchar(16),
	  OUTS
	  out p_signup_status  	integer,
	  out p_user_id			integer,
	  out p_reject_reason	varchar(45)*/
    // Status OK: 5 INs + 3 OUTs = 8
    public static String DB_SIGNUP_USER = "CALL crypto.signup_user(?,?,?,?,?,?,?,?)";

    // From MySQL_Users.sql
    /** INs:
     IN p_user_id 		INTEGER(10),
     IN p_user_name 	VARCHAR(45),
     IN p_email 		VARCHAR(45),
     IN p_password 		VARCHAR(16),
     OUTs:
   	 out p_login_status  	integer,
 	 out v_user_id 			integer(10),
 	 out v_user_name     	varchar(45),
 	 out v_settings_2fa  	longtext,
	 out v_type  			integer(10),
	 out v_login_level 		integer(4),
	 out v_phone_number 	varchar(15), 
	 out v_registrated2fa 	longtext **/
    // Status OK: 4 INs + 8 OUTs = 12
    public static String DB_LOGIN_USER = "CALL crypto.login_user(?,?,?,?,?,?,?,?,?,?,?,?)";

    //From MySQL_Users.sql   
    /**INs:
	in UID 					int(10),
	in phone_number 		varchar(15),
	in login_level 			varchar(4),
	OUTs:
	out p_create_status 	integer(10),
	out p_error_message  	varchar(45)
    // Status OK: 3 INs + 2 OUTs = 5
     */
    public static String SETUP_2FA_DATA = "Call crypto.setup_2fa_data(?,?,?,?,?)";

    
    //From MySQL_Users.sql   
    /**INs:
	in v_user_id 				int(10),
	in v_registarated2fa 		Json,
	in v_2fa_settings 			Json,
	
	out p_create_status 		integer(10),
	out p_error_message  		varchar(45)
    // Status OK: 3 INs + 2 OUTs = 5
     */

    public static String UPDATE_2FA_SETTINGS= "Call crypto.update_2fa_settings(?,?,?,?,?)";

//    public static String UPDATE_2FA_SETTINGS= "Call crypto.update_2fa_settings(?,?,?)";

    
    
    // From dml_procedures.sql
    /** INs:
     IN  p_user_id					INTEGER,
     IN  p_new_user_status 			INTEGER,
     OUTs:
     OUT p_change_status				INTEGER,
     OUT p_change_rejection_reason	VARCHAR(25) */
    // Status OK: 2 INs + 2 OUTs = 4
    public static String DB_UPDATE_USER_STATUS = "CALL crypto.update_user_status(?, ?, ?, ?)";
    // From dml_procedures.sql
    /** INs:
     IN  p_user_id					INTEGER,
     IN  p_account_id					INTEGER,
     IN  p_new_account_status 			INTEGER,
     OUTs:
     OUT p_change_status				INTEGER,
     OUT p_change_rejection_reason	VARCHAR(25) */
    // Status OK: 3 INs + 2 OUTs = 5
    public static String DB_UPDATE_ACCOUNT_STATUS = "CALL crypto.update_account_status(?, ?, ?, ? , ?)";


    // From dml_procedures.sql
    /** INs:
     IN  p_document_id					INTEGER,
     OUTs:
     OUT p_agreement				JSON,
     OUT p_change_status				INTEGER,
     OUT p_change_rejection_reason	VARCHAR(25) */
    // Status OK: 2 INs + 2 OUTs = 4
    public static String DB_GET_AGREEMENT_BY_ID = "CALL crypto.admin_get_agreement_by_id(?, ?, ?, ?)";

    // From dml_procedures.sql
    /** INs:
     IN  p_document_id					INTEGER,
     OUTs:
     OUT p_document				    JSON,
     OUT p_change_status				INTEGER,
     OUT p_change_rejection_reason	VARCHAR(25) */
    // Status OK: 2 INs + 2 OUTs = 4
    public static String DB_GET_DOCUMENT_FROM_AGREEMENT_BY_ID = "CALL crypto.admin_get_document_from_agreement_by_id(?, ?, ?, ?)";

    // From dml_procedures.sql
    /** INs:
     IN  p_document_id					INTEGER,
     IN  p_invalid_from					TIMESTAMP,
     OUTs:
     OUT p_change_status				INTEGER,
     OUT p_change_rejection_reason	VARCHAR(25) */
    // Status OK: 2 INs + 2 OUTs = 4
    public static String DB_MARK_AGREEMENT_AS_INVALID = "CALL crypto.admin_mark_as_invalid(?, ?, ?, ?)";

    // From dml_procedures.sql
    /** INs:
     OUTs:
     OUT p_agreements				JSON */

    public static String DB_ADMIN_GET_AGREEMENTS_TYPES = "CALL crypto.admin_get_agreement_types(?)";

    // From dml_procedures.sql
    /** INs:
     OUTs:
     OUT p_permissions json,
     OUT p_roles json,
     OUT p_status  	                    integer,
     OUT p_reason	                    varchar(45) */

    public static String DB_ADMIN_GET_ROLES_PERMISSIONS              = "CALL crypto.admin_get_roles_and_permissions(?,?,?,?)";


    // From dml_procedures.sql
    /** INs:
     OUTs:
     OUT p_verification_levels				JSON */

    public static String DB_ADMIN_GET_VERIFICATION_TYPES = "CALL crypto.admin_get_verification_types(?)";
    // From dml_procedures.sql
    /** INs:
     OUTs:
     OUT p_roles				JSON */

    public static String DB_ADMIN_GET_ROLES_DESCRIPTIONS = "CALL crypto.admin_get_roles_description(?)";
    // From dml_procedures.sql
    /** INs:
     OUTs:
     OUT p_permissions				JSON */

    public static String DB_ADMIN_GET_PERMISSIONS_DESCRIPTIONS = "CALL crypto.admin_get_permissions_description(?)";

    // From dml_procedures.sql
    /** INs:
     OUTs:
     OUT p_agreements				JSON */

    public static String DB_ADMIN_GET_LATEST_AGREEMENTS = "CALL crypto.admin_get_last_agreements(?)";

    // From dml_procedures.sql
    /** INs:
     IN  v_document_type  VARCHAR(100),
     OUTs:
     OUT p_agreements json*/

    public static String DB_ADMIN_GET_AGREEMENT_TYPES = "CALL crypto.admin_get_agreement_types(?,?)";

    // From dml_procedures.sql
    /** INs:
     IN  v_user_id  INT(10),
     OUTs:
     OUT p_agreements json
     OUT p_status  	                    integer,
     OUT p_reason	                    varchar(45)*/

    public static String  DB_ADMIN_GET_USER_ROLES              = "CALL crypto.admin_get_user_roles(?,?,?,?)";

    // From dml_procedures.sql
    /** INs:
     IN  c_verification_level  INT(11),
     OUTs:
     OUT p_requirments json*/

    public static String DB_GET_REQUIREMENTS_BY_VERIFICATION_LEVEL = "CALL crypto.admin_get_requirments_by_verification_level(?,?)";

    // From dml_procedures.sql
//    CREATE PROCEDURE crypto.add_permission
//    in  p_parameters	                   TINYBLOB,
//    in  p_description                  varchar(100),
//    in  p_sub_code                     integer(10)unsigned,
//    out p_permission_create_status  	integer,
//    out p_permission_id			    integer,
//    out p_reject_reason	 varchar(45)
    // Status 0: 3 INs + 3 OUTs = 6

    public static String DB_ADD_PERMISSION = "CALL crypto.add_permission(? ,?,?, ?, ?, ?)";


// From dml_procedures.sql
//    CREATE PROCEDURE crypto.admin_create_commission
//    in  p_user_id	                   INT(10),
//    in  p_account_id	               INT(10),
//    in  p_account_type                TINYINT(3),
//    in  p_transaction_type            TINYINT(2),
//    in  p_expiration_date             DATETIME,
//    in  p_from_step                   INT(10),
//    in  p_to_step                     INT(10),
//    in  p_comm_type                   TINYINT(2),
//    in  p_comm_sum                    DECIMAL(10,4),
//    in  p_comm_min                    DECIMAL(10,4),
//    in  p_comm_max                    DECIMAL(10,4),
//  in  p_symbol                      varchar(10),
//    out p_create_status  	           integer,
//    out p_reject_reason	           varchar(45)
    // Status 0: 11 INs + 2 OUTs = 13

   public static String DB_ADD_COMMISSION = "CALL crypto.admin_create_commission(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


    // From dml_procedures.sql
//    CREATE PROCEDURE crypto.add_permission
//    in  p_description                  varchar(100),
//    out p_permission_create_status  	integer,
//    out p_permission_id			    integer,
//    out p_reject_reason	 varchar(45)
    // Status 0: 1 INs + 3 OUTs = 4

    public static String DB_UPDATE_PARAMETERS_TO_PERMISSION = "CALL crypto.update_parameters_to_permission(? ,?,?, ?, ?, ?,? ,?,?)";
    // From dml_procedures.sql

//    in p_user_id                    int(10),
//    in p_account_id                 int(10),
//    out p_user_type                   int(10),
//    out p_account_type                int(10),
//    out p_create_status			    integer,
//    out p_reject_reason			    varchar(100)
    // Status 0: 2 INs + 4 OUTs = 6

    public static String DB_GET_USER_ACCOUNT_TYPES = "CALL crypto.admin_get_user_account_types(? ,?, ?, ?, ?, ?)";

    // From dml_procedures.sql
//    CREATE PROCEDURE crypto.update_parameters_to_permission
    //   in  p_user_id                      integer(10),
    //  in  p_account_id                   integer(10),
    //  in  p_role_id                      integer(10),
    //  in  p_permission_id                integer(10),
    //  in  p_expiration_date              date,
    //  in  p_disabled                      bit(1),
    //  in  p_parameters	                TINYBLOB,
    //  out p_status  	                    integer,
    //  out p_reason	                    varchar(45)
    // Status 0: 7 INs + 2 OUTs = 9


    public static String DB_REMOVE_PERMISSION_FROM_ROLE = "CALL crypto.remove_permission_from_role(? ,?, ?, ?)";

    // From dml_procedures.sql
//    CREATE PROCEDURE crypto.remove_permission_from_role
//    in  p_role                        integer,
//    in p_permission               	integer,
//    out p_status			            integer,
//    out p_description         	 varchar(45)
    // Status 0: 2 INs + 2 OUTs = 4

    public static String DB_ADD_PERMISSION_TO_ROLE = "CALL crypto.add_permission_to_role(? ,?, ?, ?,?)";

    // From dml_procedures.sql
//    CREATE PROCEDURE crypto.add_permission_to_role
    //   in  p_role_id                      integer(10),
    //  in  p_permission_id                integer(10),
    //  in  p_parameters	                TINYBLOB,
    //  out p_status  	                    integer,
    //  out p_reason	                    varchar(45)
    // Status 0: 3 INs + 2 OUTs = 5

    public static String DB_ADD_PERMISSION_TO_USER = "CALL crypto.add_permission_to_user(? ,?, ?, ?,?)";

    // From dml_procedures.sql
//    CREATE PROCEDURE crypto.add_permission_to_role
 //   in  p_user_id                      integer(10),
  //  in  p_role_id                integer(10),
  //  out p_status  	                    integer,
  //  out p_reason	                    varchar(45)
    // Status 0: 3 INs + 2 OUTs = 5

    public static String DB_ADD_ROLE_TO_USER               = "CALL crypto.add_role_to_user(? ,?, ?, ?)";

    // From dml_procedures.sql
//    CREATE PROCEDURE crypto.add_permission_to_user
    //  in  p_user_id                      integer(10),
    //  in  p_permission_id                integer(10),
    //  in  p_parameters	                TINYBLOB,
    //  out p_status  	                    integer,
    //  out p_reason	                    varchar(45)
    // Status 0: 3 INs + 2 OUTs = 5

    public static String DB_ADD_PERMISSION_TO_ACOOUNT = "CALL crypto.add_permission_to_account(? ,?, ?, ?,?,?)";

    // From dml_procedures.sql
//    CREATE PROCEDURE crypto.add_permission_to_user
    //  in  p_user_id                      integer(10),
    //  in  p_permission_id                integer(10),
    //  in  p_account_id                   integer(10),
    //  in  p_parameters	                TINYBLOB,
    //  out p_status  	                    integer,
    //  out p_reason	                    varchar(45)
    // Status 0: 4 INs + 2 OUTs = 6

    public static String DB_ADD_ROLE = "CALL crypto.add_role(?, ?, ?, ?)";


    // From dml_procedures.sql
//    in  p_name                      varchar(45),
//    in  p_description               varchar(100),
//    in  p_gtoup                     integer(11),
//    in  p_tags                      tinyblob,
//    in  p_fee                       double,
//    in  p_fee_symbol                varchar(16),
//    in  p_fee_period                integer(11),
//    in  p_status                    int(11),
//    in  p_icon                      tinyblob,
//    in  p_changed_by                int(11),
//    in  p_commission                double,
//    in  p_commission_type            int(3),
//    out p_channel_create_status  	 integer(10),
//    out p_channel_id			     integer(10)
    //  12 INs + 2 OUTs = 14

    public static String DB_ADD_CHANNEL = "CALL crypto.add_channel(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // From naftali_sql.sql
//    in  p_user_id					integer,
//    in  p_account_id				integer,
//    in  p_channel_id			    integer,
//    in  p_user_name			    varchar (45),
//    in  p_start_date    			datetime,
//    in  p_expiration_date			datetime,
//    in  p_price      				double,
//    in  p_currency      			double,
//    in  p_balance_type			    int(3),
//    in  p_auto_renewal			    int(1),
//    in  p_renewal_frequency	    int(10),
//    OUT p_subscription_id          BIGINT,
//    OUT p_status 					int(1),
//    OUT p_reject_reason 			VARCHAR(45)
    //  11 INs + 3 OUTs = 14

    public static String DB_CREATE_SUBSCRIPTION = "CALL crypto.create_subscription(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";


    // From dml_procedures.sql
//    in  p_channel_id			     integer(10)
//    in  p_description               varchar(100),
//    in  p_channel_group             integer(11),
//    in  p_tags                      tinyblob,
//    in  p_fee                       double,
//    in  p_fee_symbol                varchar(16),
//    in  p_fee_period                integer(11),
//    in  p_status                    int(11),
//    in  p_icon                      tinyblob,
//    in  p_changed_by                int(11),
//    in  p_commission                double,
//    in  p_commission_type           int(3),
//    out p_channel_update_status  	 integer(10),
//    out p_error_message			 varchar(45)
    //  12 INs + 2 OUTs = 14

    public static String DB_UPDATE_CHANNEL = "CALL crypto.update_channel(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";



    // From dml_procedures.sql
//    out p_operations	             varchar(45)
    //  0 INs + 1 OUTs = 1

    public static String DB_GET_OPERATION = "CALL crypto.admin_get_operations(?)";

    // From dml_procedures.sql
//    out p_operations	             varchar(45)
    //  0 INs + 1 OUTs = 1

    public static String DB_GET_OPERATION_PERMISSIONS = "CALL crypto.admin_get_operations_permissions(?)";
    // From dml_procedures.sql
//    out p_operations	             varchar(45)
    //  0 INs + 1 OUTs = 1

    public static String DB_GET_OPERATION_PERMISSIONS_FOR_SUPPORT = "CALL crypto.admin_get_operations_permissions_for_support(?)";

    // From dml_procedures.sql
//    in p_operation_id	             varchar(45)
    //  1 INs + 0 OUTs = 1

    public static String DB_GET_OPERATION_PERMISSIONS_BY_ID = "CALL crypto.admin_get_operations_permissions_by_id(?)";
    // From dml_procedures.sql
//    in  p_group_id                      int(11),
//    in  p_description                   varchar(100),
//    in  p_parameters                    JSON,
//    in  p_rest                          JSON,
//    in  p_user_type                     int(10),
//    in  p_account_type                  int(10),
//    out p_status                        int(11),
//    out p_reject_reason	             varchar(45)
    //  6 INs + 2 OUTs = 8

    public static String DB_ADD_OPERATION_PERMISSIONS = "CALL crypto.admin_add_operation_permission(?, ?, ?, ?, ?, ?, ?, ?)";


    public static String DB_ADMIN_GET_DESCRIPTION_TABLE = "CALL crypto.admin_get_description_table(?)";

    // From dml_procedures.sql
//    in  p_group_id                      int(11),
//    in  p_url                           varchar(100),
//    in  p_description                   varchar(100),
//    out p_status                       int(11),
//    out p_reject_reason	             varchar(45)
    //  3 INs + 2 OUTs = 5

    public static String DB_ADD_OPERATION = "CALL crypto.admin_add_operation(?, ?, ?, ?, ?)";
    // From dml_procedures.sql
//    in  p_account_id                integer(11),
//    in  p_channel_id                integer(11),
//    in  p_description               varchar(100),
//    in  p_commission                double,
//    out p_manager_add_status  	  integer(10),
//    out p_error_message     	     varchar(10),
    //  4 INs + 2 OUTs = 6

    public static String DB_ADD_CHANNEL_MANAGER              = "CALL crypto.add_channel_manager(?, ?, ?, ?, ?, ?)";


    // From dml_procedures.sql
//    in  p_account_id                integer(11),
//    in  p_channel_id                integer(11),
//    in  p_description               varchar(100),
//    in  p_commission                double,
//    out p_manager_add_status  	  integer(10),
//    out p_error_message     	     varchar(10),
    //  4 INs + 2 OUTs = 6

    public static String DB_INSERT_CHANNEL_MANAGER = "CALL crypto.insert_channel_manager(?, ?, ?, ?, ?)";

    // From dml_procedures.sql
//    in  p_table_id			         integer(10),
//    in  p_sub_code			         integer(10),
//    in  p_description               varchar(100),
//    out p_table_create_status  	 integer(10),
//    out p_error_message			 varchar(45)
    //  3 INs + 2 OUTs = 5

    public static String DB_ADD_TABLE_TO_TABLES = "CALL crypto.add_table(?, ?, ?, ?, ?)";


    // From dml_procedures.sql
//    in  p_table_id			         integer(10),
//    in  p_main_code			     integer(10),
//    in  p_sub_code			         integer(10),
//    in  p_description               varchar(100),
//    out p_table_create_status  	 integer(10),
//    out p_error_message			 varchar(45)
    //  4 INs + 2 OUTs = 6

    public static String DB_ADD_TYPE_TO_TABLE = "CALL crypto.add_type_to_table(?, ?, ?, ?, ?, ?)";


    // From MySQL_Users.sql
    /** INs:
     p_user_id INTEGER(10) */
    // CONCAT JSON of: user_id','event_date_time','event_type','event_text'
    // OUT: v_user_events_json
    public static String DB_QUERY_USER_EVENTS = "SELECT crypto.get_user_events(%d) res FROM dual";

    // From MySQL_Admin.sql
    /** INs:
     p_channel_id INTEGER(10) */
    // OUT:   v_subscriptions_json;
    public static String DB_CHANNEL_SUBSCRIPTIONS = "SELECT crypto.get_channel_subscriptions(%d) res FROM dual";

    // From MySQL_Admin.sql
    /** INs:
     p_channel_id INTEGER(10) */
    // OUT:   v_subscriptions_json;
    public static String DB_CHANNEL_NUMBER_OF_SUBSCRIBERS = "SELECT crypto.get_number_of_channel_subscribers(%d) res FROM dual";

    // From MySQL_Users.sql
    /** INs:
     p_user_id INTEGER(10),
     p_account_id INTEGER(10)) */
    // CONCAT JSON of: 'user_id','api_key_label','account_id','permissions','public_key','secret_key','create_date_time'
    // OUT: v_user_api_keys_json
    // ?????? TODO: verify wildcards number is 2: <?,?>
    public static String DB_QUERY_USER_API_KEYS = "SELECT crypto.get_user_api_keys(%d,%d) res FROM dual";

    // From MySQL_Users.sql
//    INs:
//    IN  p_user_id     integer(10),
//    IN  p_account_id     integer(10),
//    OUTs:
//    OUT p_permissions json,
//    OUT p_roles json,
//    OUT p_status  	                    integer,
//    OUT p_reason	                    varchar(45)
//    CONCAT JSON of: 'user_id','permission_code','expiration_date','create_date_time'
//    OK 2 INs + 4 OUTs = 6   
    public static String DB_QUERY_USER_PERMISSIONS = "CALL crypto.admin_get_account_roles(?,?,?,?,?,?)";
//    public static String DB_QUERY_USER_PERMISSIONS = "CALL crypto.admin_get_user_permissions(?,?,?,?)";
    
//    INs:
//    IN  p_user_id     integer(10),
//    IN  p_account_id     integer(10),
//    OUTs:
//    OUT p_permissions json,
//    OUT p_roles json,
//    OUT p_status  	                    integer,
//    OUT p_reason	                    varchar(45)
//    OK : 2 INs + 4 OUTs = 6
    public static String GET_ACCOUNT_ROLES_AND_PERMISSIONS = "CALL crypto.admin_get_account_roles2(?,?,?,?,?,?)";
    
    
    
    
    public static String GET_PERMISSIONS_FOR_ROLES = "CALL crypto.admin_get_roles_by_operation_id";
    
//    IN  p_user_id     integer(10),
//    IN  p_account_id     integer(10),
//    OUT p_permissions json,
//    OUT p_status  	                    integer,
//    OUT p_reason	                    varchar(45)
//    OK: 2 INs: + 3 OUTs  = 5 
    public static String GET_ACCOUNT_PERMISSIONS = "CALL crypto.admin_get_account_permissions(?,?,?,?,?)";
    
    /*
    IN  p_user_id     integer(10),
    IN  p_account_id     integer(10),
    OUT p_permissions json,
    OUT p_roles json,
    OUT p_status  	                    integer,
    OUT p_reason	                    varchar(45)
     */

    // From MySQL_Users.sql
    /** INs:
     p_user_id INTEGER(10) */
    // CONCAT JSON of: 'to_user_id','chanel_id','create_date_time','from_user_id','message_content'
    // OUT: v_messages_json
    public static String DB_QUERY_USER_INCOMING_MESSAGES = "SELECT crypto.get_incoming_messages(%d) res FROM dual";

    // From MySQL_Users.sql
    /** INs:
     p_user_id INTEGER(10) */
    // CONCAT JSON of: 'to_user_id','chanel_id','create_date_time','from_user_id','message_content'
    // OUT: v_messages_json
    public static String DB_QUERY_USER_OUTGOING_MESSAGES = "SELECT crypto.get_outgoing_messages(%d) res FROM dual";


    // From MySQL_Accounts.sql
    /** INs:
     IN p_user_id							INT(10),
     IN p_account_id						INT(10),
     OUTs:
     OUT v_account_addresses 				JSON,
     OUT v_account_balances 				JSON,
     OUT v_account_commissions_agreements 	JSON,
     OUT v_account_documents 				JSON,
     OUT v_account_external_wallets 		JSON,
     OUT v_account_internal_wallets 		JSON,
     OUT v_accounts 						JSON,
     OUT v_blockchain_transactions 			JSON,
     OUT v_blockchain_wallets	 			JSON,
     OUT v_executions 						JSON,
     OUT v_orders 							JSON,
     OUT v_transactions 					JSON,
     OUT v_withdrawl_requests 				JSON */
    // Status OK: 2 INs + 13 OUTs = 15
    public static String DB_QUERY_ACCOUNT_ALL_DATA = "CALL crypto.retrieve_all_account_data(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) FROM dual";


    // From MySQL_Accounts.sql
    /** INs:
     IN c_account_type INT(10),
     IN c_user_type INT(10),
     IN c_document_type VARCHAR(100),
     IN c_version VARCHAR(45),
     IN c_retroactive BOOLEAN,
     IN c_notification BOOLEAN,
     IN c_valid_from DATETIME,
     IN c_invalid_from DATETIME,
     OUTs:
     OUT p_agreements json,
     OUT p_create_status		integer,
     OUT p_reject_reason        varchar(100) */
    // Status OK: 8 INs + 3 OUTs = 11
    public static String DB_GET_AGREEMENTS_BY_QUERY = "CALL crypto.admin_get_agreements(?,?,?,?,?,?,?,?,?,?,?)";


    /**
     //From MySQL_Accounts.sql
     /**INs:
     p_user_id varchar(45)
     OUTs:
     v_user_id_json
     // Status OK: 1 INs + 0 OUTs = 1
     */
    public static String DB_QUERY_GET_USERID_WITH_EMAIL = "SELECT crypto.get_user_id_with_email('%s') res FROM dual";


    // From MySQL_Accounts.sql
    /** INs:
         p_user_id INTEGER(10),
         p_account_id INTEGER(10)) */
    // CONCAT JSON of: 'user_id','account_id','type''last_name','first_name','middle_name','date_of_birth','nationality','gender','country_code','area_code','phone_number','email','permanent_address_as_residental','employment_status','Industry','job_title','employer_name','employer_country','employer_state_province','employer_city_town','employer_district','employer_building_no','employer_street_name','employer_postal_zipcode','source_of_funds','net_worth','expected_level_of_investment','experience_stock_market','experience_equities','experience_derivatives','understAND_derivatives','understAND_risk_tradINg_crypto','verification_level'
    // OUTs: v_accounts_json
    // Status OK: 2 INs + 0 OUTs = 2
    public static String DB_QUERY_ACCOUNT_INFO              = "SELECT crypto.get_account_info(%d,%d) res FROM dual";


    // From MySQL_Accounts.sql
    /** INs:
         p_user_id INTEGER(10),
         p_account_id INTEGER(10) */
    // CONCAT JSON of: 'user_id','account_id','address_type','country','state_province','city_town','buildINg_no','street_name','apartment_no','postal_zipcode'
    // OUTs: v_account_addresses_json
    // Status OK: 2 INs + 0 OUTs = 2
    public static String DB_QUERY_ACCOUNT_ADDRESSES = "SELECT crypto.get_account_addresses(%d,%d) res FROM dual";


    // From MySQL_Accounts.sql
    /** INs:
     p_user_id INTEGER(10),
     p_account_id INTEGER(10)) */
    // 'user_id','account_id','currency','instrument_id','date','balance_type','balance','avg_cost_price','unrealized_pl'
    // OUTs: v_account_balances_json
    // Status OK: 2 INs + 0 OUTs = 2
    public static String DB_QUERY_ACCOUNT_BALANCES = "SELECT crypto.get_account_balances(%d,%d) res FROM dual";
    public static String DB_SELECT_ACCOUNT_BALANCE = "select crypto.get_user_balances(%d)res from dual";
    public static String DB_QUERY_BALANCES = "SELECT crypto.get_balances(%d,%d) res FROM dual";
    public static String DB_QUERY_FUNDABLE_CURRENCIES = "SELECT crypto.get_fundable_currencies() res FROM dual";
    public static String DB_QUERY_NEW_FUNDING_OFFER = "call crypto.create_new_funding_offer(?,?,?,?,?,?,?,?,?,?,?,?);";
    public static String DB_QUERY_NEW_FUNDING_REQUEST = "call crypto.create_new_funding_request(?,?,?,?,?,?,?,?,?);";

    // From MySQL_Accounts.sql
    /** INs:
     p_user_id INTEGER(10),
     p_account_id INTEGER(10)) */
    // CONCAT JSON of: 'user_id',user_id,'account_id','account_type','transaction_type','expiration_date','FROM_step','to_step','comm_type','comm_sum','comm_mIN','comm_max'
    // OUTs: v_account_commissions_agreements_json
    // Status OK: 2 INs + 0 OUTs = 2
    public static String DB_QUERY_ACCOUNT_COMMISSIONS = "SELECT crypto.get_account_commissions_agreements(%d,%d) res FROM dual";

    public static String DB_SELECT_DEFAULT_COMMISSIONS = "SELECT * from crypto.account_commissions_agreements where user_id=%d AND account_id=%d";
//    public static String DB_SELECT_USER_COMMISSIONS = "SELECT * from crypto.account_commissions_agreements where user_id=%d";
    
    
    public static String GET_USER_COMMISSIONS = "call crypto.get_user_commissions(?);" ;
    public static String GET_ACCOUNT_COMMISIONS_WITH_DATE= "call crypto.get_account_commissions_with_date(?,?,?)";
    					 
    // From MySQL_Accounts.sql
    /** INs:
     p_user_id INTEGER(10),
     p_account_id INTEGER(10)) */
    // CONCAT JSON of: 'user_id','account_id','upload_date_time','document_type','approved_rejected','documment_location','issue_date','expiration_date','document_number','issuINg_country','first_name','last_name','middle_name_1','middle_name_2','file_front','file_back','approval_date_time'
    // OUTs: v_account_documents_json
    // Status OK: 2 INs + 0 OUTs = 2
    public static String DB_QUERY_ACCOUNT_DOCUMENTS = "SELECT crypto.get_account_documents(%d,%d) res FROM dual";

    
//  crypto.get_all_documents_desc                
//    public static String GET_ALL_DOCUMENTS_DESC= "SELECT crypto.get_all_documents_desc(%d,%d) res FROM dual";
    public static String GET_ALL_DOCUMENTS_DESC= "CALL crypto.get_all_documents_desc(?,?)";
    
//  TODO:Adi write ins and outs
    public static String GET_ALL_CORP_DOCUMENTS_DESC = "CALL crypto.get_all_corp_documents_desc(?,?)";
    
    // From MySQL_Accounts.sql
    /** INs:
     p_user_id INTEGER(10),
     p_account_id INTEGER(10)) */
    // CONCAT JSON of: 'user_id',user_id,'account_id','currency_symbol','label','blockchaIN_id','address','create_date_time','status'
    // OUTs: v_account_external_wallets_json
    // Status OK: 2 INs + 0 OUTs = 2
    public static String DB_QUERY_ACCOUNT_EXTERNAL_WALLET = "SELECT crypto.get_account_external_wallets(%d,%d,%s,%s,%s) res FROM dual";

    // From MySQL_Accounts.sql
    /** INs:
     p_user_id INTEGER(10),
     p_account_id INTEGER(10)) */
    // CONCAT JSON of: 'user_id','account_id','currency_symbol','label','blockchain_id','address','create_date_time','status'
    // OUTs: v_account_internal_wallets_json
    // Status OK: 2 INs + 0 OUTs = 2
    public static String DB_QUERY_ACCOUNT_INTERNAL_WALLETS = "SELECT crypto.get_account_internal_wallets(%d,%d,%s,%s,%s) res FROM dual";

    // From MySQL_Accounts.sql
    /** INs:
     p_user_id INTEGER(10),
     p_account_id INTEGER(10)) */
    // CONCAT JSON of: 'blockchain_id','create_date_time','hash_id','FROM_address','to_address','curency_symbol','account_id','amount','fee','IN_OUT','status'
    // OUTs: v_blockchain_transactions_json
    // Status OK: 2 INs + 0 OUTs = 2
    public static String DB_QUERY_ACCOUNT_BLOCKCHAIN_TRANSACTIONS = "SELECT crypto.get_blockchain_transactions(%d,%d) res FROM dual";

    // From MySQL_Accounts.sql
    /** INs:
     p_user_id INTEGER(10),
     p_account_id INTEGER(10)) */
    // CONCAT JSON of: 'account_id','blockchain_id','currency_symbol','wallet_type','public_key','secret_key','status'
    // OUTs: v_blockchain_wallets_keys_json
    // Status OK: 2 INs + 0 OUTs = 2
    public static String DB_QUERY_ACCOUNT_BLOCKCHAIN_WALLETS = "SELECT crypto.get_blockchain_wallets(%d,%d) res FROM dual";

    // From MySQL_Accounts.sql
    /** INs:
     p_user_id INTEGER(10),
     p_account_id INTEGER(10)) */
    // CONCAT JSON of: 'execution_id','order_id','create_date_time','user_id','account_id','instrument_id','buy_sell','amount','price','value','exchange_fee','broker_fee''net_value','status'
    // OUTs: v_executions_json
    // Status OK: 2 INs + 0 OUTs = 2
    public static String DB_QUERY_ACCOUNT_EXECUTIONS = "SELECT crypto.get_executions(%d,%d) res FROM dual";

    // From MySQL_Accounts.sql
    /** INs:
     p_user_id INTEGER(10),
     p_account_id INTEGER(10)) */
    // CONCAT JSON of: 'order_id','user_id','account_id','create_date_time','symbol','instrument_id','buy_sell','amount','limit_type','limit_price','is_stop','stop_limit_price','time_IN_force','expiration_date_time','value','exchange_fee','broker_fee','executed_amount','net_value','is_cxl','is_closed','is_editable','update_date_time'
    // OUTs: v_orders_json
    // Status OK: 2 INs + 0 OUTs = 2
    public static String DB_QUERY_ACCOUNT_ORDERS = "SELECT crypto.get_orders(%d,%d) res FROM dual";

    // From MySQL_Accounts.sql
    /** INs:
     p_user_id INTEGER(10),
     p_account_id INTEGER(10)) */
    // CONCAT JSON of: 'transaction_id','create_date_time','account_id','transaction_type',transaction_type,'instrument_id','amount','remarks'
    // OUTs: v_transactions_json
    // Status OK: 2 INs + 0 OUTs = 2
    public static String DB_QUERY_ACCOUNT_TRANSACTIONS = "SELECT crypto.get_transactions(%d,%d) res FROM dual";

    // From MySQL_Accounts.sql
    /** INs:
     p_user_id INTEGER(10),
     p_account_id INTEGER(10)) */
    // CONCAT JSON of: 'account_id','user_request_date_time','currency_symbol','amount','wallet_label','to_address','user_verified','inspector_verified','blockchain_sent','blockchain_transaction_hash_id','blockchain_verified','user_verified_date_time','inspector_approval_date_time','blockchain_sent_date_time','blockchain_response_date_time'
    // OUTs: v_withdrawl_requests_json
    // Status OK: 2 INs + 0 OUTs = 2

    // From MySQL_Accounts.sql
    /** INs:
     p_user_id INTEGER(10),
     p_account_id INTEGER(10)) */
    // CONCAT JSON of: 'user_id','api_key_label','account_id','permissions','public_key','secret_key','create_date_time'
    // OUTs: v_user_api_keys_json
    public static String DB_QUERY_ACCOUNT_API_KEYS = "SELECT crypto.get_user_api_keys(%d,%d)res FROM dual";

    // From MySQL_Accounts.sql
    /** INs:
     p_user_id INTEGER(10),
     p_account_id INTEGER(10)) */
    // CONCAT JSON of: 'account_id','label','currency_symbol','wallet_type','wallet_address'
    // OUTs: v_exchanges_public_wallets_json
    // Status OK: 2 INs + 0 OUTs = 2
    public static String DB_QUERY_ACCOUNT_EXCHANGE_PUBLIC_WALLETS = "SELECT crypto.get_exchanges_public_wallets(%d,%d) res FROM dual";

    //save agreement
    /**
     IN p_document_type VARCHAR(100),
     IN p_retroactive BOOLEAN,
     IN p_notification BOOLEAN,
     IN p_country_id VARCHAR(60),
     IN p_subdiv_id VARCHAR(60),
     IN p_version VARCHAR(45),
     IN p_valid_from timestamp,
     IN p_nationality INT(10),
     IN p_user_type INT(10),
     IN p_account_type INT(10),
     IN p_document BLOB,
     out p_document_id integer,
     out p_create_status			integer,
     out p_reject_reason			varchar(100)**/

    // 11 INs + 3 OUTs = 14

    public static String DB_ADMIN_SAVE_AGREEMENT = "CALL crypto.admin_save_agreement (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


    //save requirment
    /**
     IN p_verification_level INT(11),
     IN p_description VARCHAR(100),
     IN p_role_id INT(11),
     IN p_sign BOOLEAN,
     IN p_document_type VARCHAR(100),
     IN p_provide BOOLEAN,
     IN p_of_group JSON,
     IN p_created_by INT(10),
     IN p_limit_to_user_type INT(10),
     IN p_limit_to_account_type INT(10),
     OUT p_requirement_id integer,
     OUT p_create_status			integer,
     OUT p_reject_reason			varchar(100)**/

    // 11 INs + 3 OUTs = 14

    public static String DB_ADMIN_SAVE_REQUIREMENT = "CALL crypto.admin_save_requirement(?,?,?,?,?,?,?,?,?,?,?,?,?)";

    //save verification level
    /**
     IN p_verification_level INT(11),
     IN p_role_id INT(11),
     IN p_limit_to_user_type INT(10),
     IN p_limit_to_account_type INT(10),
     OUT p_create_status			integer,
     OUT p_reject_reason			varchar(100)**/

    // 4 INs + 2 OUTs = 6

    public static String DB_ADMIN_SAVE_VERIFICATION_LEVEL = "CALL crypto.admin_save_verification_level(?,?,?,?,?,?)";    //save verification level


    /**
     IN p_verification_level INT(11),
     IN p_user_id INT(11),
     OUT p_create_status			integer,
     OUT p_reject_reason			varchar(100)**/

    // 2 INs + 2 OUTs = 4

    public static String DB_ADMIN_DISABLE_VERIFICATION_LEVEL = "CALL crypto.admin_disable_verification_level(?,?,?,?)";

    /**
     //From dml_procedures.sql
     //INs:
     p_user_id integer(10),
     p_account_id integer(10)
     // OUTs: v_executions_json
     */
    public static String DB_QUERY_ACCOUNT_LAST_TRADES = "SELECT crypto.get_account_last_trades(%d,%d) res FROM dual";
    /** INs:
    in p_document_type VARCHAR(100),
    in p_version VARCHAR(45),
    in p_limit int(4) */

   public static String DB_ADMIN_GET_AGREEMENTS_SIGNATURES = "CALL crypto.admin_get_agreement_sagnatories(?,?,?)";

    // From MySQL_KYC.sql
    // No INs
    /** OUTs:
     OUT v_languages json,
     OUT v_account_types json,
     OUT v_document_types json,
     OUT v_nationalities json,
     OUT v_employment_status json,
     OUT v_employment_industry json,
     OUT v_source_funds json,
     OUT v_countries json */
    // Status OK: 0 INs + 8 OUTs = 8 wildcards
    public static String DB_QUERY_KYC_ALL_DATA = "CALL crypto.retrieve_all_kyc_tables (?,?,?,?,?,?,?,?,?,?)";

    // From MySQL_KYC.sql
    // No INs
    // CONCAT JSON of: 'main_code','description'
    // OUTs: v_table_json
    public static String DB_QUERY_KYC_LANGUAGES = "SELECT crypto.get_single_table(1) res FROM dual";


    public static String DB_QUERY_KYC_ACCOUNT_TYPES = "SELECT crypto.get_single_table(3) res FROM dual";

    public static String DB_QUERY_KYC_DOCUMENT_TYPES = "SELECT crypto.get_single_table(5) res FROM dual";

    public static String DB_QUERY_KYC_NATIONALITIES = "SELECT crypto.get_single_table(9) res FROM dual";

    public static String DB_QUERY_KYC_EMPLOYMENT_STATUSES = "SELECT crypto.get_single_table(10) res FROM dual";

    public static String DB_QUERY_KYC_EMPLOYMENT_INDUSTRIES = "SELECT crypto.get_single_table(11) res FROM dual";

    public static String DB_QUERY_KYC_SOURCE_FUNDS = "SELECT crypto.get_single_table(12) res FROM dual";


    // From MySQL_KYC.sql
    // No INs
    // CONCAT JSON of: 'name','alpha2','alpha3','code','iso3166','region','sub_region','region_code','sub_region_code'
    // OUTs: v_countries_json
    public static String DB_QUERY_KYC_COUNTRIES = "SELECT crypto.get_countries() res FROM dual";

    // From MySQL_KYC.sql
    // No INs
    // CONCAT JSON of: 'country_name','subdiv','subdiv_name','level_name','alt_names','subdiv_star','subdiv_id','country_id','country_code_2','country_code_3'
    // OUTs: v_countries_states_json
    public static String DB_QUERY_KYC_COUNTRIES_STATES = "SELECT crypto.get_countries_states('%s') res FROM dual";


// ------------------------------------------------------------------------------------------------------- //
// ------------------------------------------------------------------------------------------------------- //
// ------------------------------------------------------------------------------------------------------- //


// TODO: Missing procedures and functions to be implemented

// =======================
// From dml_procedures.sql
// =======================

// save_account_financial_details()
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
// 21 INs + 2 OUTs = 23
    public static String DB_QUERY_SAVE_FINANCIAL_DETAILS = "CALL crypto.save_account_financial_details (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

// ----------------------------------------------- //

// save_account_identity_details()
    /** INs:
     IN p_user_id         INTEGER(10) UNSIGNED,
     IN p_account_id         INTEGER(10) UNSIGNED,
     IN p_account_type    TINYINT(1) UNSIGNED,
     IN p_last_name         VARCHAR(45),
     IN p_first_name        VARCHAR(45),
     IN p_middle_name    VARCHAR(45),
     IN p_date_of_birth     VARCHAR(45),
     IN p_nationality    TINYINT(4),
     IN p_gENDer             CHAR(1),
     IN p_country_code    TINYINT(4),
     IN p_area_code         TINYINT(4),
     IN p_phone_number    INTEGER(11),
     IN p_email             VARCHAR(45),
     IN p_landline_number          INT(11),
     IN  p_landline_country_code      INT(4),

     OUTs:
     OUT p_status        INTEGER,
     OUT p_reject_reason VARCHAR(45) */
// 15 INs + 2 OUTs = 15
    public static String DB_QUERY_SAVE_ACCOUNT_CONTACT_DETAILS = "CALL crypto.save_account_contact_details (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

// ----------------------------------------------- //



// ----------------------------------------------- //

// record_user_event()
    /** INs:
     IN  p_user_id				  INTEGER,
     IN  p_event_type	 		  INTEGER,
     IN  p_event_text			  VARCHAR(100),
     OUTs:
     OUT p_change_status			  INTEGER,
     OUT p_change_rejection_reason VARCHAR(25) */
// 3 INs + 2 OUTs = 5
    public static String DB_QUERY_RECORD_EVENT = "CALL crypto.record_user_event (?,?,?,?,?)";

// ----------------------------------------------- //

// update_user_security_settings()
    /** INs:
     IN p_user_id					 INTEGER,
     IN p_user_google_auTHENticatOR	 BOOLEAN,
     IN p_user_twilio_auTHENtication	 BOOLEAN,
     IN p_user_fido_auTHENtication	 BOOLEAN,
     IN p_logIN_2fa					 BOOLEAN,
     IN p_withdrawal_confirmation_2fa BOOLEAN,
     IN p_password_changes_2fa		 BOOLEAN,
     IN p_api_key_creation_2fa		 BOOLEAN,
     OUTs:
     OUT p_update_status 			 INT(1),
     OUT p_reject_reason				 VARCHAR(45) */
// 8 INs + 2 OUTs = 10
    //@deprecated
//    public static String DB_QUERY_UPDATE_SECURITY_SETTINGS = "CALL crypto.update_user_security_settings (?,?,?,?,?,?)";

// ----------------------------------------------- //

// insert_update_account_addresses()
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
// 11 INs + 2 OUTs = 13
    public static String DB_QUERY_INSERT_ACCOUNT_ADDRESSES = "CALL crypto.insert_update_account_addresses (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
 // insert_update_account_details()
    /** INs:
     IN p_user_id          INTEGER(10),
     IN p_account_id       INTEGER(10) UNSIGNED,
     IN p_first_name          VARCHAR(45),
     IN p_middle_name   VARCHAR(45),
     IN p_last_name        VARCHAR(45),
     IN p_date_of_birth        DATE,
     IN p_nationality        int(11),
     IN p_net_worth        int(3),
     IN p_employer_city_town      VARCHAR(45),
     IN p_employer_country      VARCHAR(45),
     IN p_employer_state_province	  VARCHAR(45),
     IN p_gender     CHAR(1),
     OUT p_status 		  INTEGER (1),
     OUT p_reject_reason   VARCHAR(45) */
// 12 INs + 2 OUTs = 14
    public static String DB_QUERY_INSERT_ACCOUNT_DETAILS = "CALL crypto.insert_update_account_details (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//    public static String DB_QUERY_INSERT_ACCOUNT_DETAILS = "CALL crypto.insert_update_account_details (?,?,?,?,?,?,?,?,?,?,?)";

// ----------------------------------------------- //

// create_account_external_wallet
    /** INs:
     IN  p_user_id		  INTEGER,
     IN  p_account_id	  INTEGER,
     IN  p_currency_symbol VARCHAR(15),
     IN  p_label			  VARCHAR(20),
     IN  p_address		  VARCHAR(45),
     OUTs:
     OUT p_create_status	  INTEGER,
     OUT p_reject_reason	  VARCHAR(45) */
// 5 INs + 2 OUTs = 7
    public static String DB_QUERY_CREATE_ACCOUNT_EXTERNAL_WALLET = "CALL crypto.create_account_external_wallet (?,?,?,?,?,?,?)";


    public static String DB_QUERY_CREATE_ACCOUNT_INTERNAL_WALLET = "CALL crypto.create_account_internal_wallet (?,?,?,?,?,?,?)";

    public static String GET_BLOCKCHAIN_CONFIG = "call get_blockchain_config(?,?,?,?,?)";


// ----------------------------------------------- //

// confirm_account_external_wallet()
    /** INs:
     IN  p_user_id		  INTEGER,
     IN  p_account_id	  INTEGER,
     IN  p_currency_symbol VARCHAR(15),
     IN  p_label			  VARCHAR(20),
     OUTs:
     OUT p_create_status	  INTEGER,
     OUT p_reject_reason	  VARCHAR(45) */
// 4 INs + 2 OUTs = 6
    public static String DB_QUERY_CONFIRM_ACCOUNT_EXTERNAL_WALLET = "CALL crypto.confirm_account_external_wallet (?,?,?,?,?,?)";

// ----------------------------------------------- //

// remove_account_external_wallet()
    /** INs:
     IN  p_user_id		  INTEGER,
     IN  p_account_id	  INTEGER,
     IN  p_currency_symbol VARCHAR(15),
     IN  p_label			  VARCHAR(20),
     OUTs:
     OUT p_create_status	  INTEGER,
     OUT p_reject_reason	  VARCHAR(45) */
// 4 INs + 2 OUTs = 6
    public static String DB_QUERY_REMOVE_EXTERNAL_WALLET = "CALL crypto.remove_account_external_wallet (?,?,?,?,?,?)";

// ----------------------------------------------- //

// create_user_api_keys()
    /** INs:
     IN p_user_id 		  INTEGER (10) UNSIGNED,
     IN p_account_id 	  INTEGER (10) UNSIGNED,
     IN p_api_key_label   VARCHAR(20),
     IN p_permissions 	  JSON,
     IN p_public_key 	  VARCHAR(45),
     IN p_secret_key 	  VARCHAR(45),
     IN p_create_date_time DATETIME(6),
     OUTs:
     OUT p_status 		  INTEGER (1),
     OUT p_reject_reason   VARCHAR(45) */
// 7 INs + 2 OUTs = 9
    public static String DB_QUERY_CREATE_USER_API_KEYS = "CALL crypto.create_user_api_keys (?,?,?,?,?,?,?,?,?)";

// ----------------------------------------------- //

    public static String GET_FILTER_CONFIG = "SELECT crypto.get_middle_tier_filter_configs() res FROM dual ";
    /**
     * IN UserId integer(10)
     * OUT  '[', group_concat(json_object(
							    'user_id',user_id,
							    'permission_code',permission_code,
							    'expiration_date',expiration_date,
							    'create_date_time',create_date_time)),']'
     */
    
    
    public static String GET_USER_PERMISSIONS = "select crypto.get_users_permissions(%d) from dual";
    
// confirm_user_api_keys()
    /** INs:
     IN p_user_id 		  INTEGER (10) UNSIGNED,
     IN p_account_id 	  INTEGER (10) UNSIGNED,
     IN p_api_key_label 	  VARCHAR(20)
     OUTs:
     OUT p_status 		  INTEGER (1),
     OUT p_reject_reason   VARCHAR(45) */
// 7 INs + 2 OUTs = 9
    public static String DB_QUERY_CONFIRM_USER_API_KEYS = "CALL crypto.confirm_user_api_keys (?,?,?,?,?)";

// ----------------------------------------------- //

// update_user_api_keys()
    /**
     IN p_user_id 			INTEGER (10) UNSIGNED,
     IN p_account_id 		INTEGER (10) UNSIGNED,
     IN p_api_key_label 		VARCHAR(20),
     IN p_permissions 		JSON,
     OUT p_status 			INTEGER (1),
     OUT p_reject_reason 	VARCHAR(45) */
// 7 INs + 2 OUTs = 9
    public static String DB_QUERY_UPDATE_USER_API_KEYS = "CALL crypto.update_user_api_keys (?,?,?,?,?,?)";

// ----------------------------------------------- //

// remove_user_api_keys()
    /**
     IN p_user_id 			INTEGER (10) UNSIGNED,
     IN p_account_id 		INTEGER (10) UNSIGNED,
     IN p_api_key_label 		VARCHAR(20),
     OUT p_status 			INTEGER (1),
     OUT p_reject_reason 	VARCHAR(45)
     */
    public static String DB_QUERY_REMOVE_USER_API_KEYS = "CALL crypto.remove_user_api_keys (?,?,?,?,?)";

// ----------------------------------------------- //


/**
 // NOT RELEVANT

 // insert_order()
 */
    /** INs:
     IN  p_order_source	 	   INTEGER,
     IN  p_user_id			   INTEGER,
     IN  p_account_id	 	   INTEGER,
     IN  p_buy_sell			   CHAR(1),
     IN  p_instrument_id		   INTEGER,
     IN  p_symbol			   VARCHAR(15),
     IN  p_amount			   DOUBLE,
     IN  p_limit_type		   INT(1),
     IN  p_limit_price		   DOUBLE,
     IN  p_is_stop			   BOOLEAN,
     IN  p_stop_limit_price	   DOUBLE,
     IN  p_time_in_force		   INT(1),
     IN  p_expiration_date_time TIMESTAMP,
     OUTs:
     OUT p_order_id			   INTEGER,
     OUT p_create_status		   INTEGER,
     OUT p_reject_reason		   VARCHAR(45) */

    // 7 INs + 2 OUTs = 9
    public static String DB_QUERY_INSERT_ORDER = "CALL crypto.insert_order (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    // ----------------------------------------------- //

    // update_order()

    /** INs:
     IN  p_user_id		INTEGER,
     IN  p_account_id	INTEGER,
     IN  p_order_id		INTEGER,
     IN  p_amount		DOUBLE,
     IN  p_limit_price	DOUBLE,
     OUTs:
     OUT p_update_status	INTEGER,
     OUT p_reject_reason	VARCHAR(45) */

    // 5 INs + 2 OUTs = 7
    public static String DB_QUERY_UPDATE_ORDER = "CALL crypto.update_order (?,?,?,?,?,?,?,?,?,?)";

    // ----------------------------------------------- //

    // cancel_order()

    /** INs
     IN  p_user_id		INTEGER,
     IN  p_account_id	INTEGER,
     IN  p_order_id		INTEGER,
     OUTs:
     OUT p_cancel_status	INTEGER,
     OUT p_reject_reason	VARCHAR(45) */

    // 3 INs + 2 OUTs = 5
    public static String DB_QUERY_CANCEL_ORDER = "CALL crypto.cancel_order (?,?,?,?,?,?,?,?)";

    // ----------------------------------------------- //

    //approve_account_ducument ()

    /** IN p_bo_user_id	 			  	  	INTEGER(10) UNSIGNED,
     IN p_crypto_user_id	 			  	INTEGER(10) UNSIGNED,
     IN p_document_id	 			    INTEGER(10) UNSIGNED,
     IN p_account_id	 			      	INTEGER(10) UNSIGNED,
     OUT p_status			  	    	INTEGER,
     OUT p_failure_reason       			VARCHAR(45)**/

    // 4 INs + 2 OUTs = 6

    public static String DB_QUERY_APPROVE_ACCOUNT_DOCUMENT = "CALL crypto.approve_account_ducument (?,?,?,?,?,?)";


//  ====================================****BACKOFFICE FUNDS MANAGMENT PROCEDURES****=======================================================

 //approve_account_ducument ()

 /** IN p_bo_user_id                             INTEGER(10) UNSIGNED,
  IN p_crypto_user_id                       INTEGER(10) UNSIGNED,
  IN p_document_id                     INTEGER(10) UNSIGNED,
  IN p_account_id                           INTEGER(10) UNSIGNED,
  OUT p_status                          INTEGER,
  OUT p_failure_reason                   VARCHAR(45)**/

 // 4 INs + 2 OUTs = 6

 public static String DB_QUERY_APPROVE_WITHDRAWAL_REQUEST= "CALL crypto.approve_withdrawal (?,?,?,?,?)";


 // ----------------------------------------------- //


//  in p_withdrawl_request_id bigint,
//          #in p_inspector_verified_timestamp bigint,
//  in p_inspector_approval_date_time datetime,
//  in p_rejected_by int(10),
//  OUT p_status                          INTEGER,
//  OUT p_failure_reason                   VARCHAR(45)

 // 3 INs + 2 OUTs = 5

 public static String DB_QUERY_REJECT_WITHDRAWAL_REQUEST= "CALL crypto.reject_withdrawal (?,?,?,?,?)";


 // ----------------------------------------------- //

 //approve_account_ducument ()

 /** IN p_bo_user_id                             INTEGER(10) UNSIGNED,
  IN p_crypto_user_id                       INTEGER(10) UNSIGNED,
  IN p_document_id                     INTEGER(10) UNSIGNED,
  IN p_account_id                           INTEGER(10) UNSIGNED,
  OUT p_status                          INTEGER,
  OUT p_failure_reason                   VARCHAR(45)**/

 // 4 INs + 2 OUTs = 6

 public static String DB_QUERY_UPDATE_WITHDRAWAL_REQUEST_GATEWAY_ID= "CALL crypto.update_withdrawal_request_id (?,?,?,?)";



 // ----------------------------------------------- //

 //approve_account_ducument ()

 /**
  * in p_withdrawl_request_id bigint,
  * in p_blockchain_sent_date_time datetime,
  * in p_blockchain_transaction_hash_id varchar(100),
  * in p_fee double,
  OUT p_status                          INTEGER,
  OUT p_failure_reason                   VARCHAR(45)**/

 // 4 INs + 2 OUTs = 6

 public static String DB_QUERY_UPDATE_WITHDRAWAL_SUCCEEDED= "CALL crypto.update_withdrawal_succeeded (?,?,?,?,?,?)";



 // ----------------------------------------------- //

 //update_withdrawal_succeeded_gateway_id ()

 /**
  in p_withdrawl_request_id bigint,
  in p_blockchain_sent_date_time datetime,
  in p_fee double,
  OUT p_status                          INTEGER,
  OUT p_failure_reason                   VARCHAR(45)
  **/

 // 4 INs + 2 OUTs = 6

 public static String DB_QUERY_UPDATE_WITHDRAWAL_SUCCEEDED_GATEWAY_ID= "CALL crypto.update_withdrawal_succeeded_gateway_id (?,?,?,?,?,?)";


 // ----------------------------------------------- //


 //update_withdrawal_verified ()

 /**
   in p_blockchain_verified_date_time datetime,
  in p_blockchain_transaction_hash_id varchar(100),
  in p_withdrawal_request_id bigint,

  OUT p_status                          INTEGER,
  OUT p_failure_reason                   VARCHAR(45)
  **/

 // 3 INs + 2 OUTs = 5

 public static String DB_WITHDRAWAL_VERIFIED= "CALL crypto.update_withdrawal_verified (?,?,?,?,?)";
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

    public static String DB_QUERY_REJECT_ACCOUNT_DOCUMENT = "CALL crypto.reject_account_ducument (?,?,?,?,?,?,?,?)";

    // ----------------------------------------------- //


    // nextval()		-- this is function and not drocedure

    /** INs:
     seq_name VARCHAR(100) */

    // 1 INs + 0 OUTs = 1
    public static String GET_NEXTVAL = "SELECT sequence.nextval('%s') res FROM dual";

    // ----------------------------------------------- //

    // insert_execution()

    /** INs:
     IN  p_order_id		INTEGER,
     IN  p_taker_maker	VARCHAR(1),
     IN  p_amount		DOUBLE,
     IN  p_price			DOUBLE,
     OUTs:
     OUT p_execution_id	INTEGER,
     OUT p_create_status	INTEGER,
     OUT p_reject_reason	VARCHAR(45) */

    // 4 INs + 3 OUTs = 7
    public static String DB_QUERY_INSERT_EXECUTION = "CALL crypto.insert_execution (?,?,?,?,?,?,?)";

    // ----------------------------------------------- //

    // update_balance()

    /** INs:
     IN  p_user_id	   INTEGER,
     IN  p_account_id   INTEGER,
     IN  p_balance_type INTEGER,
     IN  p_currency	   VARCHAR(15),
     IN  p_amount	   DOUBLE,
     IN  p_price		   DOUBLE */
    // 6 INs + 0 OUTs = 6
    public static String DB_QUERY_UPDATE_BALANCE = "SELECT crypto.update_balance(%d,%d,%d,'%s',%f,%f) res FROM dual";

    // ----------------------------------------------- //

    /** INs:
     IN  p_user_id	   INTEGER,
     IN  p_account_id   INTEGER,
     IN  p_balance_type INTEGER,
     IN  p_currency	   VARCHAR(15),
     IN  p_amount	   DOUBLE**/
    // 5 INs + 0 OUTs = 5
    public static String DB_QUERY_UPDATE_BALANCE_OUTSTANDING = "CALL crypto.update_balance_outstanding(?,?,?,?,?,?)";

    // ----------------------------------------------- //

// calculate_commission ()		-- this is function and not drocedure
    /** INs:
     p_user_id 		   INTEGER,
     p_account_id 	   INTEGER,
     p_transaction_type INTEGER,
     p_amount 		   INTEGER,
     p_value 		   INTEGER */
// 5 INs + 0 OUTs = 5
    public static String DB_QUERY_CALCULATE_COMMISSION = "SELECT crypto.calculate_commission(%d,%d,%d,%d,%d) res FROM dual";

// ----------------------------------------------- //

// =======================
// From MySQL_Accounts.sql
// =======================

// get_blockchain_wallets_keys()
    /** INs:
     p_user_id    INTEGER(10),
     p_account_id INTEGER(10)) */
// 2 INs + 0 OUTs = 2
    public static String DB_QUERY_BLOCKCHAIN_WALLET_KEYS = "SELECT crypto.get_blockchain_wallets_keys(%d,%d) res FROM dual";

// ----------------------------------------------- //

// get_not_confirmed_transactions()
    /** INs:
     p_user_id    INTEGER(10),
     p_account_id INTEGER(10)) */
// 2 INs + 0 OUTs = 2
    public static String DB_QUERY_GET_NOT_CONFIRMED_TRANSACTIONS = "SELECT crypto.get_not_confirmed_transactions(%d,%d) res FROM dual";

// ----------------------------------------------- //

// =======================
// From MySQL_Users.sql
// =======================

// get_accounts_for_user()
    /** INs:
     p_user_id INTEGER(10) */
// 1 INs + 0 OUTs = 1
    public static String DB_QUERY_GET_ACCOUNTS_FOR_USER = "SELECT crypto.get_accounts_for_user(%d) res FROM dual";

// ----------------------------------------------- ////

// =======================
// From MySQL_Subscriptions.sql
// =======================

// get_all_subscriptions()
    /** INs:
     NO INs**/

    public static String DB_QUERY_GET_ALL_SUBSCRIPTIONS = "SELECT crypto.get_all_subscriptions() res FROM dual";

// ----------------------------------------------- //
// get_all_subscriptions()
    /** INs:
     NO INs**/

    public static String DB_QUERY_GET_ALL_WITHDRAWAL_REQUESTS= "SELECT crypto.get_all_withdrawal_requests() res FROM dual";

// ----------------------------------------------- //
// get_open_orders()
    /** INs:
     p_user_id integer(10),
     p_account_id integer(10) */
// 2 INs + 0 OUTs = 2
    public static String DB_QUERY_GET_OPEN_ORDERS = "SELECT crypto.get_open_orders(%d,%d,'%s') res FROM dual";

    // ----------------------------------------------- //
// get_open_orders()
    /** INs:
     p_user_id integer(10),
     p_account_id integer(10) */
// 2 INs + 0 OUTs = 2
    public static String DB_QUERY_GET_ALL_OPEN_ORDERS = "SELECT crypto.get_all_open_orders(%d,%d) res FROM dual";

// ----------------------------------------------- //
// retrieve_user_security_settings()
    /** INs:
     IN p_user_id					  INT(10) UNSIGNED,
     IN p_account_id				  	  INT(10) UNSIGNED,
     OUTs:
     OUT p_user_google_authenticator   BIT(1),
     OUT p_user_twilio_authentication  BIT(1),
     OUT p_user_fido_authentication    BIT(1),
     OUT p_login_2fa 			   	  TINYINT(3),
     OUT p_withdrawal_confirmation_2fa TINYINT(4),
     OUT p_password_changes_2fa		  BIT(1),
     OUT p_api_key_creation_2fa		  BIT(1),
     OUT p_status			      	  INTEGER,
     OUT p_reject_reason     	  	  VARCHAR(45) */
// 2 INs + 9 OUTs = 11
    public static String DB_QUERY_RETRIEVE_USER_SECURITY_SETTINGS = "CALL crypto.retrieve_user_security_settings (?,?,?,?,?,?,?,?,?,?,?)";

// ----------------------------------------------- //

    /** INs:	p_user_id    INTEGER(10),
     p_account_id    INTEGER(10)) */
    public static String DB_QUERY_GET_ACCOUNT_EXTERNAL_WALLETS = "select crypto.get_account_external_wallets(%d, %d) res from dual";

// ----------------------------------------------- //
    /**
     /** INs:
     p_account_id    INTEGER(10)) */
    public static String DB_QUERY_GET_WALLET_CURRENCIES = "select crypto.get_wallet_currencies() res from dual";


// ----------------------------------------------- //
    /**
     * No INS
     * OOUTs: wallet_currencies */
    public static String DB_QUERY_GET_CHANNEL_DETAILS = "select crypto.get_channel_details(%d) res from dual";


// ----------------------------------------------- //
    /**
     *  IN: table_id
     *  OUTs: table_content */
    public static String GET_SPECIFIC_TABLE = "select crypto.get_tables(%d) res from dual";

// ----------------------------------------------- //

// save_account_document()
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
// 13 INS + 2 OUTs = 15
    public static String DB_QUERY_SAVE_ACCOUNT_DOCUMENT = "call crypto.save_account_document(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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


    /**
     IN 	p_user_id	 			  	INTEGER(10) UNSIGNED,
     IN 	p_account_id	 			INTEGER(10) UNSIGNED,
     out p_financial_details			JSON,
     out p_bank_statement_details		JSON,
     OUT p_status			  	    	INTEGER,
     OUT p_reject_reason       			VARCHAR(45)
     */
    public static String DB_QUERY_RETRIVE_ACCOUNT_FINANCIAL_DETAILS = "call crypto.retrieve_account_financial_details(?,?,?,?,?,?)";


    public static String DB_QUERY_GET_DEPOSIT_INSTRUCTIONS = "select get_deposit_instructions('%s')res from dual;";

    public static String DB_QUERY_GET_BLOCKCHAIN_TRANSACTIONS = "select crypto.get_blockchain_transactions(%d,%d,'%s',%s,%s) res from dual;";

    /*
     * admin queries*/


    /*
    * `update_user`
 in  p_user_id					integer(10) unsigned,
 in  p_user_name			    varchar(45),
 in  p_first_name			    varchar(45),
 in  p_last_name	 			varchar(45),
 in  p_telephone				varchar(20),
 in	 p_default_language			tinyint(3),
 in	 p_email					varchar(45),
 in  p_currency_for_fees		varchar(3),
 out p_update_status			integer,
 out p_reject_reason			varchar(100)

 in+out = 10*/

    public static String DB_QUERY_UPDATE_USER_ADMIN = "call crypto.bo_update_user(?,?,?,?,?,?,?,?,?,?);";



    /*
    * `SELECT USERS BY PARAMS`
 in p_user_id int(10),
in p_user_name varchar(45),
in p_last_name varchar(45),
in p_email varchar(45),
in from_date varchar(45),
in to_date varchar(45)

 in+out = 10*/

    public static String SELECT_USERS_BY_PARAMS = "call crypto.get_users_by_params(?,?,?,?,?,?,?,?);";
    /*
    * `SELECT USERS BY ID`
 in p_user_id int(10),
 in+out = 1*/

    public static String GET_USERS_BY_ID = "call crypto.get_users_by_id(?);";


    /*
    *  `crete_user_admin`
 in  p_user_name 		varchar(45),
 in  p_first_name	    varchar(45),
 in  p_last_name		varchar(45),
 in  p_email 			varchar(45),
 in  p_telephone		varchar(20),
 in	 p_default_language	 tinyint(3),
 in  p_currency_for_fees varchar(3),
 out p_user_create_status  	integer,
 out p_user_id			    integer,
 out p_reject_reason	 varchar(45)
    * */

    public static String DB_QUERY_CREATE_USER_ADMIN = "call crypto.create_user_admin(?,?,?,?,?,?,?,?,?,?);";    /*
    *  `crete_user_admin`
    IN  p_user_name     varchar(45),
    IN  p_password     varchar(16),
    OUT p_user json,
    OUT p_permissions json,
    OUT p_user_id  	                    integer(10),
    OUT p_status  	                    integer,
    OUT p_reason	                    varchar(45)
     in+out = 7
    * */

    public static String DB_QUERY_ADMIN_LOGGED_IN = "call crypto.admin_get_logged_in2(?,?,?,?,?,?,?);";



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

    public static String SELECT_ACCOUNTS_BY_PARAMS = "call crypto.get_accounts_by_params(?,?,?,?,?,?);";   /*
    *  `get_accounts_by_id`
    in p_user_id int(10),
     in+out = 2
    * */

    public static String SELECT_ACCOUNTS_BY_ID = "call crypto.get_accounts_by_id(?,?);";



   /*
    `bo_get_user_executions`(
    in p_user_id int(10),
    in p_account_id int(10)
         in+out = 2
    * */

    public static String GET_ACCOUNT_EXECUTIONS = "call crypto.bo_get_all_account_executions(?,?,?,?,?,?,?);";

    /*
    `admin_get_withdrawals_deposits`(
    in p_user_id int(10),
    in p_account_id int(10)
    OUT p_withdrawals json,
    OUT p_deposits json
         in+out = 4
    * */

    public static String GET_WITHDRAWALS_DEPOSITS = "call crypto.admin_get_withdrawals_deposits(?,?,?,?);";




   /*
  `bo_get_account_open_orders`(

    in p_user_id int(10),
    in p_account_id int(10)

         in+out = 2
    * */

    public static String GET_ACCOUNT_OPEN_ORDERS = "call crypto.bo_get_account_open_orders(?,?);";





   /*
   `bo_get_all_account_orders`(
  in p_user_id int(10),
in p_account_id int(10),
in p_limit int(4),
in p_order_id BIGINT(20),
in p_filled boolean,
in p_open boolean,
in p_closed boolean,
in p_canceled boolean,
in p_rejected boolean,
in p_expiration_display varchar(45),
in p_order_type varchar(45),
in p_symbol varchar(45),
in from_date varchar(45),
in to_date varchar(45),
         in+out = 14
    * */

    public static String GET__ALL_ACCOUNT_ORDERS = "call crypto.bo_get_all_account_orders(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";





   /*
    `bo_get_all_account_balances`(
    in p_user_id int(10),
    in p_account_id int(10)
         in+out = 2
    * */

    public static String GET_ALL_ACCOUNT_BALANCES = "call crypto.bo_get_all_account_balances(?,?);";


   /*
  bo_get_all_account_documents`(

in p_user_id int(10),
in p_account_id int(10)
)
         in+out = 2
    * */

    public static String GET_ALL_ACCOUNT_DOCUMENTS = "call crypto.bo_get_all_account_documents(?,?);";






   /*
   `select_all_users`(

    * */

    public static String GET__ALL_USERS= "call crypto.select_all_users";


   /*
   `select_all_bo_users`(

    * */

    public static String GET_ALL_BO_USERS= "call crypto.select_all_bo_users";


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

    public static String GET_BO_USERS_BY_PARAMS= "call crypto.get_bo_users_by_params (?,?,?,?,?,?);";




   /*
   `select_all_accounts`(

    * */

    public static String GET__ALL_ACCOUNTS= "call crypto.select_all_accounts";




       /*

    `bo_get_document_by_id`(
       in      p_document_id int(10),
out	    p_document_type_out			integer,
out  	p_document_number			integer,
out 	p_issue_date				date,
out	    p_expiration_date			date,
out 	p_issuing_country			varchar(3),
out 	p_first_name				varchar(45),
out 	p_last_name					varchar(45),
out 	p_middle_name				varchar(45),
out 	p_document_front			longblob,
out 	p_document_back				longblob,
out  	p_document_front_file_name 	varchar(45),
out  	p_document_back_file_name  	varchar(45),
out 	p_status 					INTEGER (1),
out 	p_reject_reason 			VARCHAR(45)
        * */

    public static String GET__DOCUMENT_BY_ID= "call crypto.bo_get_document_by_id(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";




    public static String GET_PENDING_APPROVAL_DOCUMENTS= "call crypto.get_pending_approval_documents";


       /*

    `bo_get_document_by_id`(
        in p_document_id int(10)
        * */

    public static String GET_CHANNELS_BY_PARAMS= "call crypto.get_channels_by_params(?,?,?,?,?,?,?);";



           /*
    `get_executions_by_params`(
    in p_order_id BIGINT(20),
            * */

    public static String GET_EXECUTIONS_BY_ORDERID= "call crypto.admin_get_order_executions(?);";

               /*
        `get_acoount_balance_currencies`(
    in p_user_id int(10),
    in p_account_id int(10)
                * */

    public static String GET_ACCOUNT_BALANCE_CURRENCIES= "call crypto.get_account_balance_currencies(?,?);";


           /*
    ` get_all_channels`()
            * */

    public static String GET_ALL_CHANNEL_MANAGERS= "call crypto.get_all_channel_managers";



           /*
    get_all_country_states()
            * */

    public static String GET_ALL_COUNTRY_STATES= "call crypto.get_all_country_states";



           /*
    get_all_country_states()
            * */

    public static String SELECT_ALL_PENDING_WITHDRAWAL_REQUESTS= "call crypto.select_all_withdrawal_requests";





    public static String DB_SELECT_USER_ID = "SELECT user_id from crypto.users where user_name='%s' OR email='%s'";

    /*
        ================= Start Blockchains Gateway DB functions and procedures ======================
    */

    public static final String DB_GET_TRANSACTION = "select blockchains_gateway.get_transaction('%s') res from dual;";
    public static final String DB_CANCEL_BLOCKCHAIN_WD = "select blockchains_gateway.cancel_withdrawal('%d','%d') res from dual;";
    public static final String DB_GET_TRANSACTIONS_BY_ADDRESS = "select blockchains_gateway.get_transactions_by_address('%s') res from dual;";
    public static final String DB_GET_BALANCES = "select blockchains_gateway.get_balances() res from dual;";
    public static final String DB_GET_TRANSACTIONS = "select blockchains_gateway.get_transactions() res from dual;";
    public static final String DB_GET_USER_TRANSACTIONS = "select blockchains_gateway.get_user_transactions('%s') res from dual;";
    public static final String DB_GET_TRANSACTIONS_IDS = "select blockchains_gateway.get_transactions_ids() res from dual;";
    public static final String DB_GET_CURRENCY = "select blockchains_gateway.get_currency('%s') res from dual;";
    public static final String DB_GET_ADDRESSES = "select blockchains_gateway.get_addresses_for_blockchain('%s') res from dual;";
    public static final String DB_GET_PENDING_TRANSACTIONS = "select blockchains_gateway.get_pending_transactions_ids('%s') res from dual;";
    public static final String DB_GET_CURRENCIES = "select blockchains_gateway.get_currencies() res from dual;";
    public static final String DB_CHECK_TRANSACTION_EXISTS = "select blockchains_gateway.check_transaction_exists('%s') res from dual;";
    public static final String DB_GET_PENDING_WITHDRAWALS = "select blockchains_gateway.get_pending_withdarawls() res from dual;";
    public static final String DB_UPDATE_TRANSACTION_STATUS = "CALL blockchains_gateway.update_transaction_status(?,?,?,?,?,?,?)";
    public static final String GET_LAST_TRANSACTION = "select blockchains_gateway.get_blockchain_last_transaction('%s') res from dual;";
    public static final String DB_GET_WD_BLOCKCHAIN = "select blockchains_gateway.get_withdrawal_request('%d') res from dual;";
	/*
	IN  p_tx_id                            VARCHAR(100),
	IN  p_blockchain_id                    INT(3),
	IN  p_block_hash					  VARCHAR(100),
	IN  p_arrival_time					  BIGINT(20),

	OUT p_status                          INTEGER,
	OUT p_reject_reason                   VARCHAR(45)
	 */

    public static final String DB_CREATE_NEW_ADDRESS = "CALL blockchains_gateway.add_address(?,?,?,?,?,?)";
	/*
	* IN  p_blockchain_id 	INT(3) UNSIGNED,
	  IN  p_symbol			VARCHAR(5),
	  IN  p_address			VARCHAR(100),
	  IN  p_account			VARCHAR(100),

	  OUT p_status			INT(2) UNSIGNED,
      OUT p_reject_reason		VARCHAR(45)*/

    public static final String DB_CREATE_TRANSACTION = "CALL blockchains_gateway.create_transaction(?,?,?,?,?,?,?,?,?,?,?,?)";
	/*
	IN  p_user_id					INTEGER(10) UNSIGNED,
	IN  p_blockchain_id				INTEGER(2)  UNSIGNED,
	IN  p_currency_symbol			VARCHAR(5),
	IN  p_tx_id						VARCHAR(100),
	IN  p_wallet_address			VARCHAR(100),
	IN  p_amount					DOUBLE 	   UNSIGNED,
	IN  p_address_issue_date_time	BIGINT(20) UNSIGNED,


	OUT p_status			  	    	INTEGER,
	OUT p_reject_reason       			VARCHAR(45) */


    public static final String COVERAGE_SPECIFIC_PARAMS = "CALL crypto.get_coverage_specific_params()";
    public static final String DB_CREATE_VALIDATED_TRANSACTION = "CALL blockchains_gateway.create_validated_transaction(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String DB_QUERY_CREATE_DEPOSIT = "CALL crypto.create_new_deposit_request(?,?,?,?,?,?,?,?,?,?,?)";
	/*
	*
	IN  p_tx_id 		VARCHAR(100),
   	IN p_user_id		BIGINT(20) UNSIGNED,
    IN 	p_symbol		VARCHAR(15),
    IN  p_address   	VARCHAR(100),
    IN 	p_block_hash	VARCHAR(100),
    IN 	p_amount		DOUBLE UNSIGNED,
    IN  p_time_issued	BIGINT(20),
    IN 	p_arrival_time	BIGINT(20),
    IN 	p_blockchain_id	INT(2),

    OUT	p_status		INT(2),
    OUT	p_reject_reason	VARCHAR(50)
	* */
    public static String DB_UPDATE_WITHDRAWAL_REQUEST = "CALL blockchains_gateway.send_withdrawal(?,?,?,?,?,?,?,?,?,?,?,?)";
    /*
    IN p_request_id			 BIGINT(20) UNSIGNED,
	IN p_user_id			 BIGINT(20) UNSIGNED,
    IN p_blockchain_id		 INT(4)		UNSIGNED,
    IN p_symbol				 VARCHAR(15)		,
    IN p_create_date_time	 BIGINT(20)			,
    IN p_from_wallet_address VARCHAR(100)		,
    IN p_to_wallet			 VARCHAR(100)		,
    IN p_tx_id				 VARCHAR(100)		,
    IN p_sent_date_time		 BIGINT(20)			,
    IN p_fee				 DOUBLE		UNSIGNED,

    OUT p_status			 INT(2)				,
    OUT p_reject_reason		 VARCHAR(45)
     */


    public static String DB_CREATE_NEW_WITHDRAWAL_REQUEST = "CALL blockchains_gateway.create_withdrawal_request(?,?,?,?,?,?,?,?,?,?,?) ";
    /*
    IN p_request_id			BIGINT(20)  UNSIGNED,
	IN p_user_id			BIGINT(20)  UNSIGNED,
    IN p_blockchain_id		INT(4)		UNSIGNED,
    IN p_symbol				VARCHAR(15)			,
    IN p_create_date_time	BIGINT(20)			,
    IN p_update_date_time	BIGINT(20)			,
    IN p_to_wallet			VARCHAR(100)		,
    IN p_amount				DOUBLE 		UNSIGNED,

    OUT p_status			INT(2)				,
    OUT p_reject_reason		VARCHAR(45)			,
    OUT p_new_request_id	BIGINT(20)
    * */
    /*
        ================= End Blockchains Gateway DB functions and procedures ==============
    */

    public static String GET_LOCAL_EXCHANGE_PER_INSTRUMENT = "SELECT crypto.get_localExchange_per_instrument() res from dual;";

}


