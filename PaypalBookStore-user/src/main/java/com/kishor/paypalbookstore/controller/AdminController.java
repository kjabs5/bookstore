package com.kishor.paypalbookstore.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kishor.paypalbookstore.config.PasswordGenerator;
import com.kishor.paypalbookstore.entity.Book;
import com.kishor.paypalbookstore.entity.BookOrder;
import com.kishor.paypalbookstore.entity.Category;
import com.kishor.paypalbookstore.entity.Customer;
import com.kishor.paypalbookstore.entity.Review;
import com.kishor.paypalbookstore.entity.Roles;
import com.kishor.paypalbookstore.entity.Users;
import com.kishor.paypalbookstore.service.BookOrderService;
import com.kishor.paypalbookstore.service.BookService;
import com.kishor.paypalbookstore.service.CategoryService;
import com.kishor.paypalbookstore.service.CustomerService;
import com.kishor.paypalbookstore.service.ReviewService;
import com.kishor.paypalbookstore.service.UsersService;




@Controller
@RequestMapping("/Admin")
public class AdminController {
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private BookService bookService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private BookOrderService bookOrderService;
	
    
	@GetMapping(value= {"/index"})
	public String index(Model theModel){
		List<BookOrder> mostRecentSales=bookOrderService.mostRecentSales();
		theModel.addAttribute("mostRecentSales",mostRecentSales);
		List<Review> mostRecentReviews=reviewService.mostRecentReviews();
		theModel.addAttribute("mostRecentReviews",mostRecentReviews);
		return "admin/index";
		
	}
	
	@RequestMapping("/usersForm")
	public String bookform(Model theModel)
	{   
		List<Category> categories=categoryService.getAllCategories();
		theModel.addAttribute("categories", categories);
		Users user=new Users();
		theModel.addAttribute("user", user);
        return "admin/user/usersForm1";
		
	}
	@RequestMapping("/Update/{id}")
	public String updateUser(@PathVariable("id")int userId,Model theModel)
	{
		List<Category> categories=categoryService.getAllCategories();
		theModel.addAttribute("categories", categories);
	    
		Users user=usersService.getUserById(userId);
		
		theModel.addAttribute("user",user);
	    return "admin/user/updateForm";	
	}
	
	@RequestMapping("/users")
	public String users(Model theModel) {
	   
		return listByPage(theModel,1);
	}
	
	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model theModel, @PathVariable ("pageNumber") int currentPage)
	{
		
		Page<Users> page=usersService.getOnlyStaff(currentPage);
		List<Users> users=page.getContent();
		long items_number=page.getTotalElements();
		int total_pages=page.getTotalPages();
		
		theModel.addAttribute("currentPage",currentPage);
		theModel.addAttribute("totalItems",items_number);
		theModel.addAttribute("totalPages",total_pages);
		theModel.addAttribute("users",users);
		
		return "admin/user/users-list";
		
	}
	
	@RequestMapping("/registerTheUser")
	public String registerTheUser(@ModelAttribute("user") Users theUser,Model theModel,RedirectAttributes ra)
	{
     
      Set<Roles> roles=new HashSet<>();
      Roles r=new Roles(2,"STAFF");
      roles.add(r);
      theUser.setRoles(roles);
      PasswordGenerator pg=new PasswordGenerator();
      theUser.setPassword( pg.encodePassword(theUser.getPassword()));
     
	  usersService.save(theUser);
	  ra.addFlashAttribute("message","The staff with id"+theUser.getId()+" has been added");
	  return "redirect:/Admin/users";
	}
	
	@RequestMapping("/updateTheUser")
	public String updateTheUser(@ModelAttribute("user") Users theUser,Model theModel,RedirectAttributes ra)
	{
     
      Set<Roles> roles=new HashSet<>();
      Roles r=new Roles(2,"STAFF");
      roles.add(r);
      theUser.setRoles(roles);
	  usersService.save(theUser);
	  ra.addFlashAttribute("message","The staff with id"+theUser.getId()+" has been updated");
	  return "redirect:/Admin/users";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable ("id") int id,RedirectAttributes ra)
	{ 
		usersService.delete(id);
		ra.addFlashAttribute("message","The user staff with id"+id+" has been deleted");
		return "redirect:/Admin/users";
	}
	
	/* Customers */
	
	@RequestMapping("/customers")
	public String costumers(Model theModel) {
		return listCustomerByPage(theModel,1);

	}
	
	@GetMapping("/customerpage/{pageNumber}")
	public String listCustomerByPage(Model theModel, @PathVariable ("pageNumber") int currentPage)
	{
		
		Page<Customer> page=customerService.findAllCustomers(currentPage);
		List<Customer> customers=page.getContent();
		long items_number=page.getTotalElements();
		int total_pages=page.getTotalPages();
		
		theModel.addAttribute("currentPage",currentPage);
		theModel.addAttribute("totalItems",items_number);
		theModel.addAttribute("totalPages",total_pages);
		theModel.addAttribute("customers",customers);
		
		return "admin/customer/customer-list";
		
	}
	
	@RequestMapping("/customerUpdate/{id}")
	public String updateCustomer(@PathVariable("id")int customerId,Model theModel)
	{
	

		Customer customer=customerService.getCustomerById(customerId);
		
		theModel.addAttribute("customer",customer);
		
	    return "admin/customer/customerForm";	
	}
	
	@RequestMapping("/updateTheCustomer")
	public String updateTheCustomser(@ModelAttribute("customer") Customer theCustomer,Model theModel,RedirectAttributes ra)
	{
	  customerService.save(theCustomer);
	  ra.addFlashAttribute("message","The customer with id"+theCustomer.getId()+" has been updated");
	  return "redirect:/Admin/customers";
	}
	
	@RequestMapping("/customerDelete/{id}")
	public String customerDelete(@PathVariable ("id") int id,RedirectAttributes ra)
	{ 
		customerService.deleteCustomer(id);
		ra.addFlashAttribute("message","The customer with id"+id+" has been deleted");
		return "redirect:/Admin/users";
	}
	
	/* Reviews */

	  @RequestMapping("/reviews")
		public String review(Model theModel)
		{   
			return listReviewsByPage(theModel,1);
		}
	  
	  @GetMapping("/reviewspage/{pageNumber}")
		public String listReviewsByPage(Model theModel, @PathVariable ("pageNumber") int currentPage)
		{
			
			Page<Review> page=reviewService.getAllReviews(currentPage);
			List<Review> reviews=page.getContent();
			long items_number=page.getTotalElements();
			int total_pages=page.getTotalPages();
			
			theModel.addAttribute("currentPage",currentPage);
			theModel.addAttribute("totalItems",items_number);
			theModel.addAttribute("totalPages",total_pages);
			theModel.addAttribute("reviews",reviews);
			
			return "admin/review/reviews-list";
			
		}
	  
	  @RequestMapping("/reviewsDelete/{id}")
		public String reviewsDelete(@PathVariable ("id") int id,RedirectAttributes ra)
		{   
		   reviewService.deleteReview(id);
			ra.addFlashAttribute("message","The review with id"+id+" has been deleted");
	        return  "redirect:/Admin/reviews";
		}


}
