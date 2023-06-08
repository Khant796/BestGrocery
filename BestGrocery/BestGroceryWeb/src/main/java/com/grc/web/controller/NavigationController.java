package com.grc.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.grc.core.model.session.OrderCartSession;
import com.grc.core.model.session.ProductOrderSession;
import com.grc.core.services.CategoryService;
import com.grc.core.services.CategoryTypeService;
import com.grc.core.services.OrderSessionService;
import com.grc.core.services.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavigationController {

	
	private CategoryService categoryService;
	private CategoryTypeService categoryTypeService;
	private ProductService productService;
	private OrderSessionService orderSessionService;
	
	@Autowired
	public NavigationController(CategoryService categoryService, CategoryTypeService categoryTypeService,
			ProductService productService, OrderSessionService orderSessionService) {
		super();
		this.categoryService = categoryService;
		this.categoryTypeService = categoryTypeService;
		this.productService = productService;
		this.orderSessionService = orderSessionService;
	}

	@GetMapping("/")
	public String home(HttpSession session) {
		OrderCartSession ocs = new OrderCartSession();
		orderSessionService.addOrdeCartToSession(ocs, session);
		return "home";
	
	}
	
	@GetMapping("/categories")
	public String categories(Model model, @RequestParam("t")String typeId) {
		if(typeId.equals("all")) {
			model.addAttribute("types", categoryTypeService.getAllCategoryTypes());
			model.addAttribute("categories",categoryService.getAllCategories());
			return "categories";
		}else {
			model.addAttribute("types", categoryTypeService.getAllCategoryTypes());
			model.addAttribute("categories",categoryService.getCategoriesByType(typeId));
			return "categories";
		}
	
	}
	
	@GetMapping("/products")
	public String products(Model model, @RequestParam("t") String categoryId) {
		if(categoryId.equals("all")) {
			model.addAttribute("categories", categoryService.getAllCategories());
			model.addAttribute("products", productService.getAllProducts());
			return "products";
		}else {
			model.addAttribute("categories", categoryService.getAllCategories());
			model.addAttribute("products", productService.getProductsByCategory(categoryId));
			return "products";
		}
	
		
		
	}
	
	@GetMapping("/cart")
	public String cart(Model model, HttpSession session) {
		model.addAttribute("products", orderSessionService.getOrderCartInSession(session).getProducts().values());
		return "cart";
	}
	
	
}
