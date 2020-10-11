package com.kishor.paypalbookstore.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kishor.paypalbookstore.entity.Book;
import com.kishor.paypalbookstore.entity.Category;
import com.kishor.paypalbookstore.entity.Customer;
import com.kishor.paypalbookstore.entity.Review;
import com.kishor.paypalbookstore.entity.Users;
import com.kishor.paypalbookstore.service.BookService;
import com.kishor.paypalbookstore.service.CategoryService;
import com.kishor.paypalbookstore.service.ReviewService;
import com.kishor.paypalbookstore.service.UsersService;



@Controller
@RequestMapping("/Book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/bookList")
	public String bookList(Model theModel) {
//		List<Book> books=bookService.getAllBooks();
//		theModel.addAttribute("books",books);
//		return "admin/book/books-list";
		
		return listByPage(theModel,1);
		
	}
	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model theModel, @PathVariable ("pageNumber") int currentPage)
	{
		Page<Book> page=bookService.getAllBooks(currentPage);
		List<Book> books=page.getContent();
		long items_number=page.getTotalElements();
		int total_pages=page.getTotalPages();
		
		theModel.addAttribute("currentPage",currentPage);
		theModel.addAttribute("totalItems",items_number);
		theModel.addAttribute("totalPages",total_pages);
		theModel.addAttribute("books",books);
		return "admin/book/books-list";
	 
	}
	
	@RequestMapping("/bookform")
	public String bookform(Model theModel)
	{   
		List<Category> categories=categoryService.getAllCategories();
		theModel.addAttribute("categories", categories);
		Book book=new Book();
		theModel.addAttribute("book", book);
		theModel.addAttribute("value",0);
        return "admin/book/bookForm";
		
	}
	
	@RequestMapping(value="/registerTheBook",method=RequestMethod.POST)
	public String bookregister(@ModelAttribute Book theBook
			,Model theModel,
			RedirectAttributes ra,
			@RequestParam("image") MultipartFile multipartFile) throws IOException
	{   

		String filename=StringUtils.cleanPath(multipartFile.getOriginalFilename());
		theBook.setBook_logo(filename);
		Book savedbook=bookService.save(theBook);
		String uploadDir="./uploads/" +savedbook.getBookId();
		Path uploadPath=Paths.get(uploadDir);
		if(!Files.exists(uploadPath))
		{
			Files.createDirectories(uploadPath);
		}
		try(InputStream inputStream=multipartFile.getInputStream()){
		Path filePath=uploadPath.resolve(filename);
		Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		}
		catch(IOException e) {
			throw new IOException("Could not upload saved file"+filename);
		}
		
		ra.addFlashAttribute("message","The book with id"+theBook.getBookId()+" has been saved");
		
		
		return "redirect:/Book/bookList";
	
	}
	
	@RequestMapping("/Update/{bookId}")
	public String update(@PathVariable("bookId")int bookId,Model theModel)
	{
		List<Category> categories=categoryService.getAllCategories();
		theModel.addAttribute("categories", categories);
	    
		Book book=bookService.getBookById(bookId);
		
		theModel.addAttribute("book",book);
	    theModel.addAttribute("value",1);
	    return "admin/book/bookForm";	
	}
	
	@RequestMapping("/Delete/{bookId}")
	public String deleteBook(@PathVariable("bookId")int bookId,RedirectAttributes ra,Model theModel)
	{   
		List<Category> categories=categoryService.getAllCategories();
		theModel.addAttribute("categories", categories);
		bookService.delete(bookId);
		ra.addFlashAttribute("message1","The book with id"+bookId+" has been deleted");
		return "redirect:/Book/bookList";
	}
	
//	@RequestMapping("/bookDetails/{bookId}")
//	public String bookDetails(Model theModel, @RequestParam("bookId") int bookId)
//	{
//		Book book=bookService.getBookById(bookId);
//    	theModel.addAttribute("book",book);
//		
//		return "bookDetails";
//	}
	@RequestMapping("/Review/{bookId}")
	public String reviewForm(Model theModel, @PathVariable("bookId") int bookId)
	{   
		Book book=bookService.getBookById(bookId);
	    theModel.addAttribute("book", book);
	    Review review=new Review();
	    theModel.addAttribute("review",review);
		
		return "customer/book/reviewForm";
	}
	
	@RequestMapping("/ReviewTheBook")
	public String reviewTheBook(@ModelAttribute ("review") Review theReview,Principal user,Model theModel,@RequestParam("bookId") int bookId,@RequestParam("rating")int rating)
	{
		 Book book=new Book();
			
		  book.setBookId(bookId);
		  theReview.setBook(book);
		  Customer customer=new Customer();
		  System.out.println(""+user.getName());
		  String name=user.getName();
		  Users Currentuser=usersService.getUserByUsername(name);
		  customer.setId(Currentuser.getId());
		  theReview.setCustomer(customer);
		  theReview.setReviewTime(new Date());
		  reviewService.saveReview(theReview);
		return "redirect:/";
	}

}
