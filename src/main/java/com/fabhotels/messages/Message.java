package com.fabhotels.messages;

import java.util.Properties;
import java.util.logging.Logger;

public class Message {

	static Logger log = Logger.getLogger(Message.class.getName());

	static Properties prop;

	public static final String TRY_LOGIN;
	public static final String REGISTRATION_FAILED;
	public static final String REGISTRATION_SUCCESS;
	public static final String SUCCESS;
	public static final String FAILED;
	public static final String NO_USER;
	public static final String INVALID_CREDENTIALS;
	public static final String LOGGED_OUT;
	public static final String MONEY_ADDED_TO_WAALET;
	public static final String MONEY_DEBITTED;
	public static final String MONEY_CREDITTED;

	static {
		try {
			prop = GetProperty.getProperty();
		} catch (Exception e) {
			log.info("Exception occured " + e);
		}

		TRY_LOGIN = prop.getProperty("try_login");
		REGISTRATION_FAILED = prop.getProperty("failed_registration");
		REGISTRATION_SUCCESS = prop.getProperty("registration_success");
		SUCCESS = prop.getProperty("success");
		FAILED = prop.getProperty("failed");
		NO_USER = prop.getProperty("no_user");
		INVALID_CREDENTIALS = prop.getProperty("invalid_username_password");
		LOGGED_OUT = prop.getProperty("logged_out");
		MONEY_ADDED_TO_WAALET = prop.getProperty("money_added_to_wallet");
		MONEY_DEBITTED = prop.getProperty("money_transferred_to");
		MONEY_CREDITTED = prop.getProperty("money_transferred_from");
	}
}
