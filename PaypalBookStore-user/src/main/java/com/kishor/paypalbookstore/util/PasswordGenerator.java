package com.kishor.paypalbookstore.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		String passsword="saru6974";
		String encodedpass=encoder.encode(passsword);
		
		System.out.println(""+encodedpass);
		
		
		

	}
	public String encodePassword(String password)
	{   
	
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		String encodedpassword=encoder.encode(password);
		return encodedpassword;
		
	}

}
