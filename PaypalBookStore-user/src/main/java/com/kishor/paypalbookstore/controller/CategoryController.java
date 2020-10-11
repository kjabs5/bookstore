package com.kishor.paypalbookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kishor.paypalbookstore.entity.Book;
import com.kishor.paypalbookstore.entity.Category;
import com.kishor.paypalbookstore.entity.Users;
import com.kishor.paypalbookstore.service.CategoryService;

@Controller
@RequestMapping("/Category")
public class CategoryController {
     
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/categoryList")
	public String bookList(Model theModel) {

		
		return listByPage(theModel,1);
		
	}
	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model theModel, @PathVariable ("pageNumber") int currentPage)
	{
		Page<Category> page=categoryService.getCategories(currentPage);
		List<Category> categories=page.getContent();
		long items_number=page.getTotalElements();
		int total_pages=page.getTotalPages();
		
		theModel.addAttribute("currentPage",currentPage);
		theModel.addAttribute("totalItems",items_number);
		theModel.addAttribute("totalPages",total_pages);
		theModel.addAttribute("categories",categories);
		return "admin/category/Categorylist";
	 
	}
	
	@RequestMapping("/createCategory")
	public String bookform(Model theModel)
	{   
		Category category=new Category();
		theModel.addAttribute("category", category);
        return "admin/category/categoryForm";
		
	}
	
	@RequestMapping("/saveCategory")
	public String saveCategory(@ModelAttribute("category") Category theCategory,Model theModel,RedirectAttributes ra)
	{   
		categoryService.saveCategory(theCategory);
		ra.addFlashAttribute("message","The category with id"+theCategory.getCategory_id()+" has been created");
		return "redirect:/Category/categoryList";
		
	}
	
	@RequestMapping("/updateCategory/{category_id}")
	public String updateCategory(@PathVariable("category_id")int category_id,Model theModel,RedirectAttributes ra)
	{   
		Category category=categoryService.getCategoryById(category_id);
		theModel.addAttribute("category", category);
		return "admin/category/categoryUpdateForm";
		
	}
	@RequestMapping("/updateTheCategory")
	public String updateTheCategory(@ModelAttribute("category") Category theCategory,Model theModel,RedirectAttributes ra)
	{   
		categoryService.saveCategory(theCategory);
		ra.addFlashAttribute("message","The category with id"+theCategory.getCategory_id()+" has been updated");
		return "redirect:/Category/categoryList";
		
	}
	
	@RequestMapping("/delete/{category_id}")
	public String deleteCategory(@PathVariable ("category_id") int category_id,RedirectAttributes ra)
	{ 
		categoryService.deleteCategory(category_id);
		ra.addFlashAttribute("message","The category with id"+category_id+" has been deleted");
		return "redirect:/Category/categoryList";
	}
}
