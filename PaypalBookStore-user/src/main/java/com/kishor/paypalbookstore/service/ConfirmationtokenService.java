package com.kishor.paypalbookstore.service;

import com.kishor.paypalbookstore.entity.Confirmationtoken;

public interface ConfirmationtokenService {
	
	public void save(Confirmationtoken token);
	
	public Confirmationtoken findByConfirmationtoken(String Confirmation_token);

}
