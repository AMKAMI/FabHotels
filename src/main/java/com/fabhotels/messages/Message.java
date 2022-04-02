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
	}

}
