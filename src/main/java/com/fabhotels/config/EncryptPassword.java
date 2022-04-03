package com.fabhotels.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import com.fabhotels.controllers.UserController;

public class EncryptPassword {
	
	static Logger log = Logger.getLogger(UserController.class.getName());

	public static String encrptPassword(String password) {

		log.info("----------Password encryption----------");
		
		String encryptedpassword = null;
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(password.getBytes());

			byte[] bytes = m.digest();

			StringBuilder s = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			encryptedpassword = s.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return encryptedpassword;
	}

}
