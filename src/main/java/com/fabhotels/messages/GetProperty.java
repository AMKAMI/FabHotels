package com.fabhotels.messages;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class GetProperty {

	static Logger log = Logger.getLogger(GetProperty.class.getName());

	public static Properties getProperty() throws Exception {
		Properties prop = null;
		String fileName = GetProperty.class.getClassLoader().getResource("messages.properties").getPath()
				.toString();
		try {
			prop = new Properties();
			prop.load(new FileInputStream(fileName));
		} catch (Exception ex) {
			log.info("Exception occured " + ex);

		}
		return prop;
	}

}
