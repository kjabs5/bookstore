package com.kishor.paypalbookstore.service;

import java.util.List;

import com.kishor.paypalbookstore.entity.BookOrder;
import com.kishor.paypalbookstore.entity.Customer;

public interface BookOrderService {
	
	public void save(BookOrder bookOrder);

	public List<BookOrder> findByCustomer(Customer customer);
	
	public List<BookOrder> getAllBookOrders();
	
	public BookOrder bookOrderDetails(int OrderId);

	public List<BookOrder> mostRecentSales();

}
