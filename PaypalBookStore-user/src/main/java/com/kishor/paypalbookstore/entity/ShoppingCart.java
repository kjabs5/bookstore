package com.kishor.paypalbookstore.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class ShoppingCart implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Map<Book,Integer> cart=new HashMap<>();
	
	
	
	public void addItem(Book book)
	{   
		
		//if(cart.containsKey(book))
		if(cart.containsKey(book))
		{
			int quantity=cart.get(book)+1;
			cart.put(book,quantity);
			
		}
		else
		{
			
			cart.put(book, 1);
		}
		
	}
	
	public void removeItem(Book book)
	{
		cart.remove(book);
		
	}
	
	public int getTotalQuantity()
	{
		int total=0;
		
		Iterator<Book> iterator=cart.keySet().iterator();
		while(iterator.hasNext())
		{
			Book next=iterator.next();
			Integer quantity=cart.get(next);
			total+=quantity;
		}
		 
		return total;	
	}
	
	public float getTotalAmount()
	{
		float total=0.0f;
		Iterator<Book> iterator=cart.keySet().iterator();
		while(iterator.hasNext())
		{
			Book book=iterator.next();
			Integer quantity=cart.get(book);
			double subTotal=quantity*book.getPrice();
			total+=subTotal;
		}
		
		return total;
	}
	
	public void updateCart(int[] bookIds, int[] quantities)
	{
		for( int i=0; i<bookIds.length;i++)
		{
			Book key=new Book(bookIds[i]);
			Integer value=quantities[i];
			cart.put(key, value);
			
		}
	}
	
	
    public Map<Book,Integer> getItems()
	{
     	return this.cart;
	}
    
    public int getTotalItems(){
    	return cart.size();
    }
	 
	public void clear()
	{
		cart.clear();
	}
	 
	 

}
