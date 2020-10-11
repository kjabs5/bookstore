package com.kishor.paypalbookstore.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kishor.paypalbookstore.dao.UsersRepository;
import com.kishor.paypalbookstore.entity.Users;

public class UserDetailsServiceImpl implements UserDetailsService {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Autowired
	private UsersRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		LOGGER.trace("Entering method loadUserbyUsername");
		LOGGER.debug("Authenticating user with usernsame");
		Users user=userRepository.getUserByUsername(username);
		
		if(user==null)
		{
			LOGGER.error("user not found");
			throw new UsernameNotFoundException("could not find the user");
		}
		LOGGER.warn("Testing logging");
		LOGGER.info("user authenticated");
		return new MyUserDetails(user);
	}

}
