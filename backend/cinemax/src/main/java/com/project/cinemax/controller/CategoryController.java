package com.project.cinemax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinemax.model.Category;
import com.project.cinemax.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		
		List<Category> categories = categoryService.findAllCategories();
		
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteCategory")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Category> deleteCategory(@RequestParam Long categoryId) {
		
		categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/updateCategory")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Category> updateCategory(
			@RequestParam Long categoryId,
			@RequestParam String categoryName) {
		
		categoryService.updateCategory(categoryId, categoryName);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/addCategory")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Category> addCategory(@RequestParam String categoryName) {
		
		categoryService.addCategory(categoryName);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
