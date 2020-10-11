package com.kishor.paypalbookstore.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.kishor.paypalbookstore.entity.Book;
import com.kishor.paypalbookstore.entity.Category;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
    
	@Query(value="SELECT * FROM book ORDER BY publish_date DESC limit 4",nativeQuery=true)
	List<Book> findTopN();
	@Query(value="SELECT book_id,SUM(quantity) FROM order_detail GROUP BY book_id ORDER BY sum(quantity) DESC limit 4",nativeQuery=true)
	public List<Book> findMostSellingBooks();
	
	@Query(value="SELECT review.book_id,book.publish_date,book.book_title,book.book_author,book.category_id,book.description,book.book_logo,book.isbn,book.publish_date,book.price,Count(review.book_id) as ReviewCount,AVG(review.rating) as AvgRating from review inner join book on book.book_id=review.book_id GROUP BY book_id having AVG(rating) >=3.5 ORDER BY ReviewCount DESC, AvgRating DESC limit 4",nativeQuery=true)
    public List<Book> findMostFavouredBooks();
	
	public List<Book> findByDescriptionContainsAllIgnoreCase(String keyword);
    
	@Query(value="SELECT * FROM book WHERE book.category_id=?1",nativeQuery=true)
	List<Book> findBookByCategory(int id);
	

}
