package com.kishor.paypalbookstore.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kishor.paypalbookstore.entity.Book;
import com.kishor.paypalbookstore.entity.BookOrder;
import com.kishor.paypalbookstore.entity.Category;
import com.kishor.paypalbookstore.entity.Customer;
import com.kishor.paypalbookstore.entity.OrderDetail;
import com.kishor.paypalbookstore.entity.ShoppingCart;
import com.kishor.paypalbookstore.entity.Users;
import com.kishor.paypalbookstore.service.BookOrderService;
import com.kishor.paypalbookstore.service.BookService;
import com.kishor.paypalbookstore.service.CategoryService;
import com.kishor.paypalbookstore.service.UsersService;






@Controller
public class HomeController {
	
	@Autowired
	private BookService bookService;
    
	@Autowired
	private BookOrderService bookOrderService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UsersService usersService;
	@RequestMapping(value= {"/index","/"})
	public String home(Model theModel)
	{   
		List<Book> booksList=bookService.findTopN();
   	    theModel.addAttribute("booksList", booksList);
		
   	    List<Category> catlist=categoryService.getAllCategories();
   	    theModel.addAttribute("catList", catlist);
   	    
   	    List<Book> bestSelling=bookService.findMostSellingBooks();
	    theModel.addAttribute("bestSelling", bestSelling);
	    
	    List<Book> mostFavoured=bookService.findMostFavouredBooks();
	    theModel.addAttribute("mostFavoured", mostFavoured);
		
		return "customer/index";
	}
	
	 @RequestMapping("/view_category/{id}")
	 	public String bookByCategory(Model theModel, @PathVariable("id") int id)
	 	
	 	{   
	    	
	    	 
	    	
	    	 List<Book> booksList=bookService.findbookByCategory(id);
	    	
	    	 theModel.addAttribute("booksList", booksList);
	    	 List<Category> categories=categoryService.getAllCategories();
	 	    theModel.addAttribute("catList", categories);
	 	   
	    	return "customer/booksByCategory";
	 		
	 	}
	

    @RequestMapping("/search")
 	public String searchBook(@RequestParam("keyword") String theName,Model theModel)
 	
 	{  
    	//List<Book> theBooks = bookService.searchBy(theName);
        List<Category> catlist=categoryService.getAllCategories();
   	    theModel.addAttribute("catList", catlist);
     	List<Book> theBooks = bookService.findByDescriptionContainsAllIgnoreCase(theName);
    	theModel.addAttribute("books", theBooks);
    	theModel.addAttribute("keyword", theName);
    	return "customer/Search_Result";
 		
 	}
	
	@RequestMapping("/bookDetails/{bookId}")
	public String bookDetails(Model theModel, @PathVariable("bookId") Integer bookId)
	{
		Book book=bookService.getBookById(bookId);
    	theModel.addAttribute("book",book);
		
		return "customer/book/bookDetails";
	}
	
	@RequestMapping("/shoppingCart")
	public String shoppingCart(Model theModel,HttpSession session)
	{
		Object cartObject=session.getAttribute("cart");
		  
		  if(cartObject==null)
		  {
			  ShoppingCart shoppingCart=new ShoppingCart();
			  session.setAttribute("cart", shoppingCart);
		  }
		  
		return "customer/shoppingCart";
	}
	
	@RequestMapping("/addToCart/{bookId}")
	  public String addToCart(Model theModel,@PathVariable("bookId") Integer bookId,HttpSession session)
	  {
		  Object cartObject=session.getAttribute("cart");
		  ShoppingCart shoppingCart=null;
		  if(cartObject!=null && cartObject instanceof ShoppingCart)
		  {
			  shoppingCart=(ShoppingCart) cartObject;
		  }
		  else
		  {
			  shoppingCart=new ShoppingCart();
			  session.setAttribute("cart",shoppingCart);
		  }
		  
		  Book book=bookService.getBookById(bookId);
		
		  shoppingCart.addItem(book);
		
		  return "customer/shoppingCart";
	  }
	
	 @RequestMapping("/updateCart")
	  public String updateCart(@RequestParam("bookId") String[] arrayBookIds,@RequestParam("quantity") String[] quantities,HttpSession session)
	  {   
		  
		  String[] arrayQuantities=new String[arrayBookIds.length];
		  
		  for(int i=1;i<=arrayQuantities.length;i++)
		  {
			  String qty=quantities[i-1];
			  arrayQuantities[i-1]=qty;
		  }
	     int[] bookIds= Arrays.stream(arrayBookIds).mapToInt(Integer::parseInt).toArray();
	     int[] quantities1= Arrays.stream(arrayQuantities).mapToInt(Integer::parseInt).toArray();
	  
	     ShoppingCart cart=(ShoppingCart) session.getAttribute("cart");
	     cart.updateCart(bookIds, quantities1);
	     
	     return "customer/shoppingCart";
	  
	  
	  }
	
	  @RequestMapping("/removeFromCart/{bookId}")
	  public String removeFromCart(Model theModel,@PathVariable("bookId") Integer bookId,HttpSession session)
	  {  
		  Object cartObject=session.getAttribute("cart");
		  ShoppingCart shoppingCart=(ShoppingCart) cartObject;
		  Book book=bookService.getBookById(bookId);
		  shoppingCart.removeItem(book);
	      return "customer/shoppingCart";
	  }
	  
	  @RequestMapping("/ClearCart")
	  public String ClearCart(HttpSession session)
	  {
		 ShoppingCart cart= (ShoppingCart) session.getAttribute("cart");
		 cart.clear();
		  return "customer/shoppingCart";
	  }
	  
	  @RequestMapping("/checkout")
	  public String checkout(HttpSession session,Model theModel,Principal user)
	  {
		 BookOrder bookOrder=new BookOrder();
		 theModel.addAttribute("bookOrder",bookOrder);
//		 Map<String,String> paymentOption =new LinkedHashMap<String,String>();
//		 paymentOption.put("Cash on Delivery","cash on Delivery");
//		 paymentOption.put("Paypal or Credit Card","Paypal or Credit Card");	
//		 theModel.addAttribute("paymentOption",paymentOption);
		 theModel.addAttribute("user", user);
		  return "customer/checkout";
	  }
	
	  
	  @RequestMapping("/placeOrder")
	  public String placeOrder(HttpSession session, @ModelAttribute("bookOrder") BookOrder theBookOrder,Model theModel,
			  @RequestParam("city") String theCity,  @RequestParam("country") String theCountry,
			  @RequestParam("zipcode") String theZipcode,
			  @RequestParam("StreetAddress") String streetAddress,Principal user)
	  {   
		  theBookOrder.setOrderDate(new Date());
		  String name=user.getName();
		  Users Currentuser=usersService.getUserByUsername(name);
		  Customer customer=new Customer();
		  customer.setId(Currentuser.getId());
	      theBookOrder.setCustomer(customer);
	      theBookOrder.setStatus("processing");
	     
	      String shippingAddress=streetAddress+","+ theCity+","+theZipcode+","+theCountry;
	      theBookOrder.setShippingAddress(shippingAddress);
	      theBookOrder.setPaymentMethod("cash on Delivery");
	      
	      ShoppingCart shoppingcart=(ShoppingCart) session.getAttribute("cart");
	      Map<Book,Integer> items=shoppingcart.getItems();
	      Iterator<Book> iterator=items.keySet().iterator();
	      
	      List<OrderDetail> orderDetails=new ArrayList<>();
	     
	      while(iterator.hasNext())
	      {
	    	  Book book=iterator.next();
	    	  Integer quantity=items.get(book);
	    	  float subtotal=quantity * book.getPrice();
	    	  
	    	  OrderDetail orderDetail=new OrderDetail();
	    	  orderDetail.setBook(book);
	    	  orderDetail.setBookOrder(theBookOrder);
	    	  orderDetail.setQuantity(quantity);
	    	  orderDetail.setSubtotal(subtotal);
	    	  
	    	  orderDetails.add(orderDetail);
	    	  
	      }
	      
	      theBookOrder.setOrderDetails(orderDetails);
	      theBookOrder.setTotal(shoppingcart.getTotalAmount());
		  bookOrderService.save(theBookOrder);
		  shoppingcart.clear();
		  String message="order has been placed";
		  theModel.addAttribute("msg", message);
		  return "customer/orderSuccess";
		  
	  }

	  @RequestMapping("/Orders")
	  public String orders(Principal user,Model theModel)
	  {  
		  String name=user.getName();
		  Users Currentuser=usersService.getUserByUsername(name);
		  Customer customer=new Customer();
		  customer.setId(Currentuser.getId());
		  List<BookOrder> orders=bookOrderService.findByCustomer(customer);
		  
		  theModel.addAttribute("orders",orders);
		  return "customer/myOrders";
	  }
	  @RequestMapping("/orders/{orderId}")
	  public String showOrderDetails(Model theModel,@PathVariable("orderId")int orderId)
	  {
		BookOrder orders=bookOrderService.bookOrderDetails(orderId);
		theModel.addAttribute("orders",orders);
		return "customer/orderDetails";
		
		  
	  }
	  
	  

}
