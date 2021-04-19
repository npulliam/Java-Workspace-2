package com.nathanpulliam.productscategories.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathanpulliam.productscategories.models.Category;
import com.nathanpulliam.productscategories.models.Product;
import com.nathanpulliam.productscategories.repositories.CategoryRepository;
import com.nathanpulliam.productscategories.repositories.ProductRepository;

@Service
public class MainService {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	
	public Product createProduct(Product newProduct) {
		return productRepo.save(newProduct);
	}
	
	public Product findProduct(Long id) {
		return productRepo.findById(id).orElse(null);
	}
	
	public Category createCategory(Category newCategory) {
		return categoryRepo.save(newCategory);
	}
	
	public Category findCategory(Long id) {
		return categoryRepo.findById(id).orElse(null);
	}
	
	public List<Category> getCategories() {
		return categoryRepo.findAll();
	}
	
}
