package com.kishor.paypalbookstore.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.kishor.paypalbookstore.entity.Category;
import com.kishor.paypalbookstore.entity.Users;

public interface CategoryService {
	
	public List<Category> getAllCategories();
	
	public Page<Category> getCategories(int pageNumber);
	
	public void deleteCategory(int id);
	
	public void saveCategory(Category category);
	
	public Category getCategoryById(int category_id);

}
