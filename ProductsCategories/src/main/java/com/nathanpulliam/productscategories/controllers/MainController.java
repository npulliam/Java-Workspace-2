package com.nathanpulliam.productscategories.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nathanpulliam.productscategories.models.Category;
import com.nathanpulliam.productscategories.models.Product;
import com.nathanpulliam.productscategories.services.MainService;

@Controller
public class MainController {
	@Autowired
	private MainService mainServ;
	
	@GetMapping("")
	public String index() {
		return "redirect:/products/new";
	}
	@GetMapping("/products/new")
	public String newProduct(@ModelAttribute("productObj") Product emptyProduct) {
		return "new_product.jsp";
	}
	@PostMapping("/products/new")
	public String createProduct(
		@Valid @ModelAttribute("productObj") Product filledProduct,
		BindingResult results
	) {
		if(results.hasErrors()) {
			return "new_product.jsp";
		} else {
			mainServ.createProduct(filledProduct);
			return "redirect:/products/new";
		}
	}
	@GetMapping("/categories/new")
	public String newCategory(@ModelAttribute("categoryObj") Category emptyCat) {
		return "new_category.jsp";
	}
	@PostMapping("/categories/new")
	public String createCategory(
		@Valid @ModelAttribute("categoryObj") Category filledCat,
		BindingResult results
	) {
		if(results.hasErrors()) {
			return "new_category.jsp";
		} else {
			mainServ.createCategory(filledCat);
			return "redirect:/categories/new";
		}
	}
	@GetMapping("/products/{id}")
	public String showProduct(
			@ModelAttribute("categoryObj") Category emptyObj,
			@PathVariable("id") Long id,
			Model model
		) {
		Product product = mainServ.findProduct(id);
		List<Category> catsFromDb = mainServ.getCategories();
		List<Category> productCats = product.getCategories();
		model.addAttribute("allCategories", catsFromDb);
		model.addAttribute("product", product);
		model.addAttribute("productCats", productCats);
		return "show_product.jsp";
		
	}
	@PostMapping("/products/{id}/addcategory")
	public String addCategory(
			@ModelAttribute("categoryObj") Category filledCategory,
			BindingResult results,
			@PathVariable("id") Long id) {
		Product product = mainServ.findProduct(id);
		Category category = mainServ.findCategory(filledCategory.getId());
		product.getCategories().add(category);
		mainServ.createProduct(product);
		return "redirect:/products/" + id;
	}
	@GetMapping("/categories/{id}")
	public String showProduct(
			@ModelAttribute("productObj") Product emptyObj,
			@PathVariable("id") Long id,
			Model model
		) {
		Category category = mainServ.findCategory(id);
		List<Product> prodsFromDb = mainServ.getProducts();
		List<Product> categoryProds = category.getProducts();
		model.addAttribute("allProducts", prodsFromDb);
		model.addAttribute("category", category);
		model.addAttribute("categoryProds", categoryProds);
		return "show_category.jsp";
		
	}
}

