package com.kishor.paypalbookstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kishor.paypalbookstore.entity.BookOrder;
import com.kishor.paypalbookstore.service.BookOrderService;



@Controller
@RequestMapping("BookOrder")
public class BookOrderController {
	
	@Autowired
	private BookOrderService bookOrderService;
	
	@RequestMapping("/bookOrders")
	  public String bookOrders(Model theModel)
	  {
		  List<BookOrder> bookOrders=bookOrderService.getAllBookOrders();
		  theModel.addAttribute("orders", bookOrders);
		  return "admin/order/orders";
		  
	  }
	@RequestMapping("/orderDetails/{orderId}")
	  public String showOrderDetails(Model theModel,@PathVariable("orderId")int orderId,HttpSession session)
	  {
		BookOrder orders=bookOrderService.bookOrderDetails(orderId);
		theModel.addAttribute("orders",orders);
		return "admin/order/orderDetails";
		
		  
	  }

}
