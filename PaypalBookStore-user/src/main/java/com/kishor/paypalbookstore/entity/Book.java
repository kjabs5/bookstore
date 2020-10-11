package com.kishor.paypalbookstore.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;



@Entity
@Table(name="book")
public class Book implements Serializable {
	
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    @Column(name="book_id")
		private Integer bookId;
	    
	    @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="category_id")
	   	private Category category;
	    
	    @Column(name="book_title")
		private String title;
	    
	    @Column(name="book_author")
		private String author;
	    
	    @Column(name="description")
		private String description;
	    
	    @Column(name="isbn")
		private String isbn;
	    
	  
	    
	    @Column(name="price")
		private float price;
	    

	    
	    @Temporal(TemporalType.DATE)
	    @Column(name="publish_date")
		private Date publishDate;
	    
	   
	    
	    @Column(name="book_logo")
		private String book_logo;



		
	    
	    @OneToMany(fetch=FetchType.LAZY, 
	    		cascade=CascadeType.ALL,
	    		mappedBy="book")
	    private List<Review> reviews;
	    
	    @OneToMany(fetch=FetchType.LAZY, 
	    		cascade=CascadeType.ALL,
	    		mappedBy="book")
	    private List<OrderDetail> orderDetail;
	    
	    public Book() {
			super();
		}

	    public Book(Integer bookId)
	    {
	    	super();
	    	this.bookId=bookId;
	    }



		public Book(Integer bookId, Category category, String title, String author, String description, String isbn,
				float price, Date publishDate, String book_logo) {
			super();
			this.bookId = bookId;
			this.category = category;
			this.title = title;
			this.author = author;
			this.description = description;
			this.isbn = isbn;
			this.price = price;
			this.publishDate = publishDate;
			this.book_logo = book_logo;
		}
		
		
		
		
		
		public Book(Integer bookId, Category category, String title, String author, String description, String isbn,
				float price, Date publishDate, String book_logo, List<Review> reviews, List<OrderDetail> orderDetail) {
			super();
			this.bookId = bookId;
			this.category = category;
			this.title = title;
			this.author = author;
			this.description = description;
			this.isbn = isbn;
			this.price = price;
			this.publishDate = publishDate;
			this.book_logo = book_logo;
			this.reviews = reviews;
			this.orderDetail = orderDetail;
		}

		public Integer getBookId() {
			return bookId;
		}
		
		
		
		
		
		public void setBookId(Integer bookId) {
			this.bookId = bookId;
		}
		
		
		
		
		
		public Category getCategory() {
			return category;
		}
		
		
		
		
		
		public void setCategory(Category category) {
			this.category = category;
		}
		
		
		
		
		
		public String getTitle() {
			return title;
		}
		
		
		
		
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		
		
		
		
		public String getAuthor() {
			return author;
		}
		
		
		
		
		
		public void setAuthor(String author) {
			this.author = author;
		}
		
		
		
		
		
		public String getDescription() {
			return description;
		}
		
		
		
		
		
		public void setDescription(String description) {
			this.description = description;
		}
		
		
		
		
		
		public String getIsbn() {
			return isbn;
		}
		
		
		
		
		
		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}
		
		
		
		
		
		public float getPrice() {
			return price;
		}
		
		
		
		
		
		public void setPrice(float price) {
			this.price = price;
		}
		
		
		
		
		
		public Date getPublishDate() {
			return publishDate;
		}
		
		
		
		
		
		public void setPublishDate(Date publishDate) {
			this.publishDate = publishDate;
		}
		
		
		
		
		
		public String getBook_logo() {
			return book_logo;
		}
		
		
		
		
		
		public void setBook_logo(String book_logo) {
			this.book_logo = book_logo;
		}
		
		
			    
		public List<Review> getReviews() {
			return reviews;
		}





		public void setReviews(List<Review> reviews) {
			this.reviews = reviews;
		}

        
		
        


		public List<OrderDetail> getOrderDetail() {
			return orderDetail;
		}

		public void setOrderDetail(List<OrderDetail> orderDetail) {
			this.orderDetail = orderDetail;
		}

		@Override
		public String toString() {
			return "Book [bookId=" + bookId + ", category=" + category + ", title=" + title + ", author=" + author
					+ ", description=" + description + ", isbn=" + isbn + ", price=" + price + ", publishDate="
					+ publishDate + ", book_logo=" + book_logo + ", reviews=" + reviews + "]";
		}

		@Transient
		public String getImagePath() {
			if(bookId==null||book_logo==null)
				{
				return null;
				}
			else {
			return "../uploads/" +bookId+"/"+book_logo;
			}
			
		}
		
		@Transient
		public float rating()
		{
			float rating=0.0f;
			float sum=0.0f;
			
			if(reviews.isEmpty())
			{
				return 0.0f;
			}
		
			for(Review review: reviews)
			{
			sum+=review.getRating();
			}
			rating=sum/reviews.size();
			
		
			return rating;
			
		}
		@Transient
		public String getRatingString(float rating)
		{  
			
			String result="";
			int numberOfStarsOn=(int) rating;
			
			
			
			for(int i=1;i<=numberOfStarsOn;i++)
			{
				result+="on,";
			}
			
			if(rating>numberOfStarsOn)
			{
				result+="half,";
			}
			int next=numberOfStarsOn + 1;
			if(result.length()<17)
			{
				if(result.length()==8)
				{
					for(int j=next+1;j<=5;j++)
					{  
						result+="off,";
						
					}
				}
				else if(result.length()==11)
				{
					for(int j=next+1;j<=5;j++)
					{  
						result+="off,";
						
					}
				}
				else if(result.length()==14)
				{
					for(int j=next+1;j<=5;j++)
					{  
						result+="off,";
						
					}
				}
				else
				{
			for(int j=next;j<=5;j++)
			{  
				result+="off,";
				
			}
				}
			}
	
			return result.substring(0,result.length()-1);
		}
		 
		@Transient
		public String getRatingStars() {
			float rating=rating();
			return getRatingString(rating);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Book other = (Book) obj;
			if (bookId == null) {
				if (other.bookId != null)
					return false;
			} else if (!bookId.equals(other.bookId))
				return false;
			return true;
		}

		
		
		

}
