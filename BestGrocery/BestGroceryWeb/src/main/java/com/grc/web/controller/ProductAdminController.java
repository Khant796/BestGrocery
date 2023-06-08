package com.grc.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.grc.core.model.dto.CategoryDTO;
import com.grc.core.model.dto.CategoryTypeDTO;
import com.grc.core.model.dto.ProductDTO;
import com.grc.core.model.form.CategoryForm;
import com.grc.core.model.form.CategoryTypeForm;
import com.grc.core.model.form.ProductForm;
import com.grc.core.services.CategoryService;
import com.grc.core.services.CategoryTypeService;
import com.grc.core.services.OrderService;
import com.grc.core.services.OrderSessionService;
import com.grc.core.services.ProductService;

@Controller
@RequestMapping("/admin")
public class ProductAdminController {

	
	
	private CategoryService categoryService;
	private CategoryTypeService categoryTypeService;
	private ProductService productService;
	private OrderSessionService orderSessionService;
	private OrderService orderService;
	
	
	@Autowired
	public ProductAdminController(CategoryService categoryService, CategoryTypeService categoryTypeService,
			ProductService productService, OrderSessionService orderSessionService, OrderService orderService) {
		super();
		this.categoryService = categoryService;
		this.categoryTypeService = categoryTypeService;
		this.productService = productService;
		this.orderSessionService = orderSessionService;
		this.orderService = orderService;
	}

	@GetMapping("/")
	public String admin(Model model) {
		
		return "admin";
	}
	
	@GetMapping("/adminproduct")
	public String adminProducts(Model model) {
		List<ProductDTO> products = productService.getAllProducts();
		model.addAttribute("products", products);
		model.addAttribute("revenues", productService.getProductsRevenue(products));
		return "adminproducts";
	}
	
	@GetMapping("/adminproduct/{id}")
	public String adminProduct(Model model, @PathVariable("id") String id) {
		ProductDTO pdto = productService.getProductById(id);
		model.addAttribute("product", pdto);
		return "adminproduct";
		
	}
	
	@GetMapping("/addproduct")
	public String addProduct(Model model) {
		model.addAttribute("form", new ProductForm());
		model.addAttribute("categories", categoryService.getAllCategories());
		return "addproduct";
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute("form") ProductForm form,@RequestParam("image") MultipartFile file) throws Exception {

			productService.saveProduct(form, file);
		return "redirect:/admin/adminproduct";
	}
	
	@PostMapping("/addcat")
	public String addCategory(@ModelAttribute("form") CategoryForm form, @RequestParam("image") MultipartFile file) {
		try {
			categoryService.saveCategory(form, file);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return "redirect:/admin/admincat";
	}
	
	@GetMapping("/addcat")
	public String addCatForm(Model model) {
		model.addAttribute("form", new CategoryForm());
		model.addAttribute("types", categoryTypeService.getAllCategoryTypes());
		return "addcat";
	}
	
	@GetMapping("/admincat")
	public String admincat(Model model) {
		List<CategoryDTO> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("revenues", categoryService.getCategoriesRevenues(categories));
		return "admincat";
	}
	
	@GetMapping("/admintype")
	public String admintype(Model model) {
		List<CategoryTypeDTO> types = categoryTypeService.getAllCategoryTypes();
		model.addAttribute("types", types);
		model.addAttribute("revenues", categoryTypeService.getAllRevenues(types));
		return "admintype";
	}
	
	@GetMapping("/addtype")
	public String addtype(Model model) {
		CategoryTypeForm form = new CategoryTypeForm();
		model.addAttribute("form", form);
		return "addtype";
	}
	
	@PostMapping("/addtype")
	public String addedType(@ModelAttribute("form") CategoryTypeForm form) {
		categoryTypeService.saveCategoryType(form);
		return "redirect:/admin/admintype";
	}
	
	@GetMapping("/adminorder/{stat}")
	public String adminorder(Model model, @PathVariable("stat") String stat) {
		if(stat.equals("active")) {
			model.addAttribute("orders", orderService.getAllOrdersActive());
			model.addAttribute("title", "Active Orders");
		}else {
			model.addAttribute("orders", orderService.getAllOrdersDelivered());
			model.addAttribute("title", "Delivered Orders");
		}
		
		return "adminorder";
	}
	
	@GetMapping("/order/{id}")
	public String individualOrder(Model model, @PathVariable("id") String id) {
		model.addAttribute("order", orderService.getOrderById(id));
		model.addAttribute("total", orderService.getOrderTotal(id));
		return "order";
	}
	
	@GetMapping("/deliver/{id}")
	public String deliver(@PathVariable("id") String id) {
		orderService.deliverOrder(id);
		return "redirect:/admin/adminorder/deliver";
	}
	
}
