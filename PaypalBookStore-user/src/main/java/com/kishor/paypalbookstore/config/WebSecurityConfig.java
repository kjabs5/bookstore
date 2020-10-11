package com.kishor.paypalbookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AuthenticationSuccessHandler successHandler;
	
	@Autowired
	LogoutHandler logoutHandler;
    
	@Bean
	public UserDetailsService userDetailsService() {
		 return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailsService());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		   
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		    
		    
		    .antMatchers("/checkout","/Orders").hasAuthority("CUSTOMER")
		    .antMatchers("/Admin/**","/Category/**","/BookOrder/**").hasAnyAuthority("STAFF","ADMIN") 
		    .antMatchers("/Book/Delete/*").hasAuthority("ADMIN")
		    .antMatchers("/Book/Update/*").hasAnyAuthority("ADMIN","STAFF")
		    .antMatchers("/Book/**").hasAnyAuthority("STAFF","ADMIN","CUSTOMER") 
		    .antMatchers("/*").permitAll()
		    
		    .antMatchers("/resources/**").permitAll()
		    .and()
		    .formLogin()
		       .loginPage("/showMyLoginPage")
			   .loginProcessingUrl("/authenticate")
			   .successHandler(successHandler)
		       .permitAll()
		    .and()
		    .logout()
		       .addLogoutHandler(logoutHandler)
		       .permitAll()
		    .and()
		    .exceptionHandling().accessDeniedPage("/access-denied")
		    ;
		
	}
	
	
	
	
}
