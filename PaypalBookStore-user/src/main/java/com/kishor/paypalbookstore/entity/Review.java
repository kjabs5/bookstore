package com.kishor.paypalbookstore.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="review")
public class Review implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="review_id")
	private int reviewId;
    
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book book;
    
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Customer customer;
    
    @Column(name="rating")
	private int rating;
    
    @Column(name="headline")
	private String headline;
    
    @Column(name="comment")
	private String comment;
    
    @Column(name="text_time")
	private Date reviewTime;
    
    public Review()
    {
    	
    }
    
    

	public Review(int reviewId, Book book, Customer customer, int rating, String headline, String comment,
			Date reviewTime) {
		super();
		this.reviewId = reviewId;
		this.book = book;
		this.customer = customer;
		this.rating = rating;
		this.headline = headline;
		this.comment = comment;
		this.reviewTime = reviewTime;
	}



	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}
	
	

	@Transient
	public String getStarsForCustomerReview()
	{
		String result="";
		int numberOfStarsOn= rating;
		
		for(int i=1;i<=numberOfStarsOn;i++)
		{
			result+="on,";

		}

		int next=numberOfStarsOn + 1;
	    for(int j=next;j<=5;j++)
		{  

			result+="off,";
		}
		
		return result.substring(0,result.length()-1);
	}



	

}
