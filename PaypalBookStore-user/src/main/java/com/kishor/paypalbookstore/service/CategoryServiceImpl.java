package com.kishor.paypalbookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kishor.paypalbookstore.dao.CategoryRepository;
import com.kishor.paypalbookstore.entity.Category;
import com.kishor.paypalbookstore.entity.Users;

@Service
public class CategoryServiceImpl implements CategoryService {
    
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public List<Category> getAllCategories() {
		
		return categoryRepository.findAll();
		
	}
	@Override
	public Page<Category> getCategories(int pageNumber) {
		Pageable pageable=PageRequest.of(pageNumber-1,5);
		return categoryRepository.findAll(pageable);
	}
	@Override
	public void deleteCategory(int id) {
		
		categoryRepository.deleteById(id);
		
	}
	@Override
	public void saveCategory(Category category) {
		
		categoryRepository.save(category);
		
	}
	@Override
	public Category getCategoryById(int category_id) {
		Optional<Category> result =categoryRepository.findById(category_id);
		
		Category theCategory = null;
		
		if (result.isPresent()) {
			theCategory = result.get();
		}
		else {
			
			throw new RuntimeException("Did not find the book with id - " +category_id);
		}
		
		return theCategory;
	}

}
