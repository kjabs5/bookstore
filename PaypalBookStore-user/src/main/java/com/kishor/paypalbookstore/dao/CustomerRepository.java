package com.kishor.paypalbookstore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kishor.paypalbookstore.entity.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query(value="SELECT customer.user_id,customer.fullname,customer.email,customer.address,customer.city,customer.country,customer.phone,customer.postcode,customer.fullname,customer.register_date,roles_users.role_id from customer,roles_users where customer.user_id=roles_users.user_id AND roles_users.role_id=1",nativeQuery=true)
    public Page<Customer> findCustomersList(Pageable number);
	
}
