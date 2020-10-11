package com.kishor.paypalbookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kishor.paypalbookstore.dao.CustomerRepository;
import com.kishor.paypalbookstore.entity.Book;
import com.kishor.paypalbookstore.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
    
	@Autowired
	private CustomerRepository customerRepository;



	@Override
	public Page<Customer> findAllCustomers(int pageNumber) {
		Pageable pageable=PageRequest.of(pageNumber-1,5);
		return customerRepository.findCustomersList(pageable);
	}
	
	@Override
	public Customer getCustomerById(int customerId) {
        Optional<Customer> result =customerRepository.findById(customerId);
		
		Customer theCustomer = null;
		
		if (result.isPresent()) {
			theCustomer = result.get();
		}
		else {
			
			throw new RuntimeException("Did not find the book with id - " + customerId);
		}
		
		return theCustomer;
	}

	@Override
	public void save(Customer customer) {
		customerRepository.save(customer);
		
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(int id) {
		customerRepository.deleteById(id);
		
	}
	
}
