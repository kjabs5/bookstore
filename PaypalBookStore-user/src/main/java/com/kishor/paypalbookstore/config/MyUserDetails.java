package com.kishor.paypalbookstore.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kishor.paypalbookstore.entity.Roles;
import com.kishor.paypalbookstore.entity.Users;

public class MyUserDetails implements UserDetails {

	
	@Autowired
	private Users users;

	public MyUserDetails(Users users) {
		super();
		this.users = users;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		//List<Roles> roles=users.getRoles();
		Set<Roles> roles=users.getRoles();
		List<SimpleGrantedAuthority> authorities=new ArrayList<>();
		
		for(Roles role:roles)
		{
			authorities.add(new SimpleGrantedAuthority(role.getRole_name()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return users.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return users.isEnabled();
	}
	

}
