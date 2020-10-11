package com.kishor.paypalbookstore.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class PaypalBookStoreErrorController implements ErrorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaypalBookStoreErrorController.class);
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
	
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request,Model theModel)
	{
		String errorPage="error";
		String pageTitle="Error";
		
		Object status=request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(status!=null) {
			Integer statusCode=Integer.valueOf(status.toString());
			if(statusCode==HttpStatus.NOT_FOUND.value())
			{
				 LOGGER.error("page not found");
				pageTitle= "Page Not Found";
				errorPage="error/404";
			   
			}
			else if(statusCode==HttpStatus.INTERNAL_SERVER_ERROR.value())
			{
				pageTitle= "Internal Server Error";
				errorPage="error/500";
			    LOGGER.error("error 500");
			}
			else if(statusCode==HttpStatus.FORBIDDEN.value())
			{
				pageTitle= "Forbidden Error";
				errorPage="error/403";
			    LOGGER.error("error 403");
			}
			else if(statusCode==HttpStatus.BAD_REQUEST.value())
			{
				pageTitle= "Bad Request Error";
				errorPage="error/400";
			    LOGGER.error("error 400");
			}
		}
		theModel.addAttribute("error",pageTitle);		
		return errorPage;
	}
}
