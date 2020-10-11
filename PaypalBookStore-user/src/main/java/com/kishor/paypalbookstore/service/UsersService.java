package com.kishor.paypalbookstore.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.kishor.paypalbookstore.entity.Book;
import com.kishor.paypalbookstore.entity.Users;

public interface UsersService {
	
	public Page<Users> users(int pageNumber);
	
	public void save(Users theUser);

	public void delete(int id);
	
	public Users getUserByUsername(String username);
	
	public Page<Users> getOnlyStaff(int pageNumber);
	
	public Users getUserById(int userId);
	
	public Users findByUsername(String uname);

}
