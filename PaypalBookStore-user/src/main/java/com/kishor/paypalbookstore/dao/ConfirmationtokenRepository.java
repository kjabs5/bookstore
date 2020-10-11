package com.kishor.paypalbookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishor.paypalbookstore.entity.Category;
import com.kishor.paypalbookstore.entity.Confirmationtoken;

public interface ConfirmationtokenRepository extends JpaRepository<Confirmationtoken, Integer> {
    
	
	public Confirmationtoken findByConfirmationToken(String confirmationToken);

}
