package com.kishor.paypalbookstore.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException, ServletException {

//		String redirectUrl = null;
//		
//
//		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//		for (GrantedAuthority grantedAuthority : authorities) {
//			System.out.println("role " + grantedAuthority.getAuthority());
//			if (grantedAuthority.getAuthority().equals("CUSTOMER")) {
//				redirectUrl = "/";
//				break;
//			} else if (grantedAuthority.getAuthority().equals("ADMIN")) {
//				redirectUrl = "/Admin/index";
//				break;
//			}
//		}
//		System.out.println("redirectUrl " + redirectUrl);
//		if (redirectUrl == null) {
//			throw new IllegalStateException();
//		}
//		new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);

//	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		String redirectUrl = null;
		 DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			System.out.println("role " + grantedAuthority.getAuthority());
			if (grantedAuthority.getAuthority().equals("CUSTOMER")) {
				if(defaultSavedRequest != null){
		           redirectUrl= defaultSavedRequest.getRedirectUrl();
		           break;
		        }
				else
				{
					redirectUrl="/";
					break;
				}
				
				
			} else if (grantedAuthority.getAuthority().equals("ADMIN")) {
				if(defaultSavedRequest != null){
			           redirectUrl= defaultSavedRequest.getRedirectUrl();
			           break;
			        }
					else
					{
						redirectUrl = "/Admin/index";
						break;
					}
				
			}
			else if (grantedAuthority.getAuthority().equals("STAFF")) {
				if(defaultSavedRequest != null){
			           redirectUrl= defaultSavedRequest.getRedirectUrl();
			           break;
			        }
					else
					{
						redirectUrl = "/Admin/index";
						break;
					}
				
			}
		}
		System.out.println("redirectUrl " + redirectUrl);
		if (redirectUrl == null) {
			throw new IllegalStateException();
		}
		new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);

	}

}
