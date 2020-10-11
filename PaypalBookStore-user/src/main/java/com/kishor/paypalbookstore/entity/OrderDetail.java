package com.kishor.paypalbookstore.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="order_detail")
public class OrderDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name="bookId",column=@Column(name="book_id",nullable=false)),
		@AttributeOverride(name="orderId",column=@Column(name="order_id",nullable=false))
	})
	private OrderDetailId orderDetailId=new OrderDetailId();
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_id",insertable=false,updatable=false,nullable=false)
	private Book book;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id",insertable=false,updatable=false,nullable=false)
	private BookOrder bookOrder;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="sub_total")
	private float subtotal;

	public OrderDetail() {
		super();
	}
	
	public OrderDetail(OrderDetailId orderDetailId)
	{
		this.orderDetailId=orderDetailId;
	}

	public OrderDetail(OrderDetailId orderDetailId, Book book, BookOrder bookOrder, int quantity, float subtotal) {
		super();
		this.orderDetailId = orderDetailId;
		this.book = book;
		this.bookOrder = bookOrder;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	public OrderDetailId getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(OrderDetailId orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
		this.orderDetailId.setBook(book);
	}

	public BookOrder getBookOrder() {
		return bookOrder;
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
		this.orderDetailId.setBookOrder(bookOrder);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
	
	
	
	
	

}
