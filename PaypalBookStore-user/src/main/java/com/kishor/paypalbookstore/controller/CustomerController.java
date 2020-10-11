package com.kishor.paypalbookstore.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kishor.paypalbookstore.config.PasswordGenerator;
import com.kishor.paypalbookstore.config.UserDetailsServiceImpl;
import com.kishor.paypalbookstore.entity.Book;
import com.kishor.paypalbookstore.entity.Confirmationtoken;
import com.kishor.paypalbookstore.entity.Customer;
import com.kishor.paypalbookstore.entity.Roles;
import com.kishor.paypalbookstore.entity.Users;
import com.kishor.paypalbookstore.service.ConfirmationtokenService;
import com.kishor.paypalbookstore.service.CustomerService;
import com.kishor.paypalbookstore.service.EmailSenderService;
import com.kishor.paypalbookstore.service.UsersService;



@Controller
@RequestMapping("/Customer")
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ConfirmationtokenService confirmationtokenService;
	@Autowired
	private EmailSenderService emailSenderService;
	
	@RequestMapping("/registerform")
	public String registerCustomerForm(Model theModel)
	{
		Users user=new Users();
		theModel.addAttribute("user", user);
		return "customer/login/RegisterCustomer1";
	}
	

	@RequestMapping("/registerTheCustomer")
	public String registerTheCustomer(@ModelAttribute("user") Users theUser,Model theModel,RedirectAttributes ra)
	{ 
	  Users user=usersService.findByUsername(theUser.getUsername());
	  if(user==null)
	  {
      Customer customer=new Customer();
      customer.setUser(theUser);
      Set<Roles> roles=new HashSet<>();
      Roles r=new Roles(1,"customer");
      roles.add(r);
      theUser.setRoles(roles);
      PasswordGenerator pg=new PasswordGenerator();
      theUser.setPassword(pg.encodePassword(theUser.getPassword()));
      theUser.setCustomer(customer);
      
	  usersService.save(theUser);
	  
	  Confirmationtoken confirmationToken = new Confirmationtoken(theUser);

      confirmationtokenService.save(confirmationToken);

      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setTo(theUser.getUsername());
      mailMessage.setSubject("Complete Registration!");
      mailMessage.setFrom("kishor.jabegu5@gmail.com");
      mailMessage.setText("To confirm your account, please click here : "
      +"http://localhost:8080/Customer/confirm-account?token="+confirmationToken.getConfirmationToken());

      emailSenderService.sendEmail(mailMessage);
      theModel.addAttribute("email", theUser.getUsername());
	  return "customer/login/Verification";
	  }
	  else
	  { 
		  LOGGER.info("username already exist");
		Users users=new Users();
	    theModel.addAttribute("user", users);
	    ra.addFlashAttribute("error", "username already exist");
		return "redirect:/Customer/registerform"; 
	  }
	}
	
	 @RequestMapping("/confirm-account")
	    public String confirmUserAccount(@RequestParam("token")String confirmationToken)
	    {
		    LOGGER.info("Inside confirm USer Account");
	         Confirmationtoken token= confirmationtokenService.findByConfirmationtoken(confirmationToken);
            System.out.println("Inside confirm USer Account");
	        if(token != null)
	        {
	        	
	        	System.out.println("Inside confirm USer Account");
	        	 LOGGER.info("Inside token not null");
	            Users user = usersService.findByUsername(token.getUser().getUsername());
	            user.setEnabled(true);
	            
	            SimpleMailMessage mailMessage = new SimpleMailMessage();
	  	      mailMessage.setTo(user.getUsername());
	  	      mailMessage.setSubject("Login Details!");
	  	      mailMessage.setFrom("kishor.jabegu5@gmail.com");
	  	      mailMessage.setText("Welocme to Evergreen BookStore \n To login your account, Here are the credentials :\n username:"+user.getUsername()+"\nPassword:"+user.getPassword());
	  	      emailSenderService.sendEmail(mailMessage);
	            usersService.save(user);
	            return "redirect:/Customer/success";
	        }
	        else
	        {
	        	 LOGGER.info(" token null");
	          return "customer/login/FailToVerify";
	        }

	       
	    }
	 
	 @RequestMapping("/success")
		public String RegisterSuccess(Model theModel,Principal user)
		{
		   
		  return "customer/login/RegistrationSuccess";
		}
	
	@RequestMapping("/profile")
	public String CustomerProfile(Model theModel,Principal user)
	{   
		
		String name=user.getName();
		Users Currentuser=usersService.getUserByUsername(name);
		Customer cus=customerService.getCustomerById(Currentuser.getId());
		theModel.addAttribute("customer",cus);
		return "customer/profile/myProfile";
	}
	
	@RequestMapping("/customerUpdate/{id}")
	public String updateCustomer(@PathVariable("id")int customerId,Model theModel)
	{
	

		Customer customer=customerService.getCustomerById(customerId);
		
		theModel.addAttribute("customer",customer);
		
	    return "customer/profile/editProfile";	
	}
	
	

}
