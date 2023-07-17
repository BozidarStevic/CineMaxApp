package com.project.cinemax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cinemax.model.Category;
import com.project.cinemax.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}
	
	public void deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}
	
	public void updateCategory(Long categoryId, String categoryName) {
		Category existCategory = categoryRepository.findById(categoryId).orElse(null);
		if (existCategory != null) {
			existCategory.setName(categoryName);
			categoryRepository.save(existCategory);
		}
	}
	
	public void addCategory(String categoryName) {
		Category category = new Category();
		category.setName(categoryName);
		categoryRepository.save(category);
	}
	
}
