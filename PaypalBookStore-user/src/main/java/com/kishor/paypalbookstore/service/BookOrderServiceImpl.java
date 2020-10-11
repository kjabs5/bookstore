package com.kishor.paypalbookstore.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.kishor.paypalbookstore.dao.BookOrderRepository;
import com.kishor.paypalbookstore.entity.BookOrder;
import com.kishor.paypalbookstore.entity.Customer;

@Service
public class BookOrderServiceImpl implements BookOrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookOrderServiceImpl.class);
	
	@Autowired
	private BookOrderRepository bookOrderRepository;
	@Override
	public void save(BookOrder bookOrder) {
		bookOrderRepository.save(bookOrder);

	}
	@Override
	public List<BookOrder> findByCustomer(Customer customer) {
		
		List<BookOrder> orders=bookOrderRepository.findByCustomer(customer);
		LOGGER.info("customer order found");
		return orders;
	}
	@Override
	public List<BookOrder> getAllBookOrders() {
		return bookOrderRepository.findAll();
	}
	
	 @Override
	    public BookOrder bookOrderDetails(int OrderId) {
	    	
	    	Optional<BookOrder> bookOrderDetails=bookOrderRepository.findById(OrderId);
	        BookOrder orderDetails = null;
			
			if (bookOrderDetails.isPresent()) {
				orderDetails = bookOrderDetails.get();
			}
			else {
				// we didn't find the employee
				throw new RuntimeException("Did not find order id - " + OrderId);
			}
			
			return orderDetails;
	    	
	    }
	@Override
	public List<BookOrder> mostRecentSales() {
		// TODO Auto-generated method stub
		return bookOrderRepository.mostRecentSales();
	}

}
