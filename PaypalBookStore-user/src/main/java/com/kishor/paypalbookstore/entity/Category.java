package com.kishor.paypalbookstore.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="category")
public class Category implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="category_id")
	private Integer category_id;
	 @Column(name="category_name") 
	private String categoryName;
	 
	 @OneToMany(fetch=FetchType.LAZY, 
	    		cascade=CascadeType.ALL,
	    		mappedBy="category")
	    private List<Book> books;
	
	public Category() {
		super();
	}
	


	



	







	public Category(Integer category_id, String categoryName) {
		super();
		this.category_id = category_id;
		this.categoryName = categoryName;
	}















	public Category(Integer category_id, String categoryName, List<Book> books) {
		super();
		this.category_id = category_id;
		this.categoryName = categoryName;
		this.books = books;
	}















	public Integer getCategory_id() {
		return category_id;
	}



	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}



	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}















	public List<Book> getBooks() {
		return books;
	}















	public void setBooks(List<Book> books) {
		this.books = books;
	}















	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((category_id == null) ? 0 : category_id.hashCode());
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
		Category other = (Category) obj;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (category_id == null) {
			if (other.category_id != null)
				return false;
		} else if (!category_id.equals(other.category_id))
			return false;
		return true;
	}
	
	

}
