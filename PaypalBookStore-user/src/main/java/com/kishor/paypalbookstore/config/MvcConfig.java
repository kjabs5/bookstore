package com.kishor.paypalbookstore.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/access-denied").setViewName("access-denied");
	//	registry.addViewController("/Registration").setViewName("customer/login/Registration");
		
		
	}
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		
//		Path uploadDir=Paths.get("./uploads");
//		String uploadPath=uploadDir.toFile().getAbsolutePath();
//		registry.addResourceHandler("/uploads/**").addResourceLocations("file:/"+uploadPath + "/");
//		
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
	
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		
	}
	
	

}
