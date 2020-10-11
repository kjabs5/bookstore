package com.kishor.paypalbookstore.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.kishor.paypalbookstore.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getAllCustomers();
	
	public Page<Customer> findAllCustomers(int pageNumber);
	
	public Customer getCustomerById(int customerId);
	
	public void save(Customer customer);
	
	public void deleteCustomer(int id);

}
