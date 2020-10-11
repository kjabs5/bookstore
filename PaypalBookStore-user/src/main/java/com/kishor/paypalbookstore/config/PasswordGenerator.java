package com.kishor.paypalbookstore.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class PasswordGenerator {
	private static final Logger LOG = LogManager.getLogger(PasswordGenerator.class);


	
	public String encodePassword(String password)
	{   
		LOG.info("Inside the encode password");
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		String encodedpassword=encoder.encode(password);
		return encodedpassword;
		
	}

}
