package com.kishor.paypalbookstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kishor.paypalbookstore.entity.BookOrder;
import com.kishor.paypalbookstore.entity.Customer;

public interface BookOrderRepository extends JpaRepository<BookOrder, Integer> {

	List<BookOrder> findByCustomer(Customer customer);
	
	@Query(value="SELECT * FROM book_order ORDER BY order_date DESC limit 4",nativeQuery=true)
    public List<BookOrder> mostRecentSales();

}
