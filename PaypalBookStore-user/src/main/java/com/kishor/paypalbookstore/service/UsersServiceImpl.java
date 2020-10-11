package com.kishor.paypalbookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kishor.paypalbookstore.dao.UsersRepository;
import com.kishor.paypalbookstore.entity.Book;
import com.kishor.paypalbookstore.entity.Users;

@Service
public class UsersServiceImpl implements UsersService {
    
	@Autowired
	private UsersRepository usersRepository;
	
	
	@Override
	public Page<Users> users(int pageNumber) {
		Pageable pageable=PageRequest.of(pageNumber-1,5);
		return usersRepository.findAll(pageable);
	}


	@Override
	public void save(Users theUser) {
		// TODO Auto-generated method stub
		usersRepository.save(theUser);
	}


	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		usersRepository.deleteById(id);
	}


	@Override
	public Users getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return usersRepository.getUserByUsername(username);
	}


	@Override
	public Page<Users> getOnlyStaff(int pageNumber) {
		Pageable pageable=PageRequest.of(pageNumber-1,5);
		return usersRepository.getOnlyStaff(pageable);
	}


	@Override
	public Users getUserById(int userId) {
		  Optional<Users> result =usersRepository.findById(userId);
			
			Users theUser = null;
			
			if (result.isPresent()) {
				theUser = result.get();
			}
			else {
				
				throw new RuntimeException("Did not find the book with id - " + userId);
			}
			
			return theUser;
	}


	@Override
	public Users findByUsername(String uname) {
		return usersRepository.findByUsername(uname);
	}


	

}
