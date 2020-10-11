package com.kishor.paypalbookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kishor.paypalbookstore.dao.ConfirmationtokenRepository;
import com.kishor.paypalbookstore.entity.Confirmationtoken;

@Service
public class ConfirmationtokenServiceImpl implements ConfirmationtokenService{
    
	@Autowired
	private ConfirmationtokenRepository confirmationtokenRepo;
	@Override
	public void save(Confirmationtoken token) {
		
		confirmationtokenRepo.save(token);
	
		
	}
	@Override
	public Confirmationtoken findByConfirmationtoken(String Confirmation_token) {
	 return	confirmationtokenRepo.findByConfirmationToken(Confirmation_token);
	}
	
	

}
