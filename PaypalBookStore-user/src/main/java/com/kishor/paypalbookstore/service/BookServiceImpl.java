package com.kishor.paypalbookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kishor.paypalbookstore.dao.BookRepository;
import com.kishor.paypalbookstore.entity.Book;
import com.kishor.paypalbookstore.entity.Category;

@Service
public class BookServiceImpl implements BookService {
    
	@Autowired
	private BookRepository bookRepository;
//	@Override
//	public List<Book> getAllBooks() {
//		// TODO Auto-generated method stub
//		return bookRepository.findAll();
//	}
	@Override
	public Book save(Book theBook) {
		// TODO Auto-generated method stub
		
		return bookRepository.save(theBook);
		
	}
	@Override
	public Page<Book> getAllBooks(int pageNumber) {
		Pageable pageable=PageRequest.of(pageNumber-1,5);
		return bookRepository.findAll(pageable);
	}
	
	@Override
	public Book getBookById(int bookId) {
        Optional<Book> result =bookRepository.findById(bookId);
		
		Book theBook = null;
		
		if (result.isPresent()) {
			theBook = result.get();
		}
		else {
			
			throw new RuntimeException("Did not find the book with id - " + bookId);
		}
		
		return theBook;
	}
	@Override
	public void delete(int bookId) {
		bookRepository.deleteById(bookId);
		
	}
	@Override
	public List<Book> findTopN() {
		return bookRepository.findTopN();
	}
	
	@Override
	public List<Book> findMostSellingBooks() {
		return bookRepository.findMostSellingBooks();
	}
     
	@Override
	 public List<Book> findMostFavouredBooks(){
		return bookRepository.findMostFavouredBooks(); 
	 }
	@Override
	public List<Book> findByDescriptionContainsAllIgnoreCase(String keyword) {
		// TODO Auto-generated method stub
		return bookRepository.findByDescriptionContainsAllIgnoreCase(keyword);
	}
	@Override
	public List<Book> findbookByCategory(int id) {
		List<Book> books=bookRepository.findBookByCategory(id);
		return books;
	}

}
