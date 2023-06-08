package com.grc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grc.core.model.dto.ProductDTO;
import com.grc.core.model.entity.Orders;
import com.grc.core.model.form.OrderForm;
import com.grc.core.model.form.ProductForm;
import com.grc.core.model.form.ProductOrderForm;
import com.grc.core.services.CategoryService;
import com.grc.core.services.OrderService;
import com.grc.core.services.OrderSessionService;
import com.grc.core.services.ProductService;
import com.grc.core.services.TownshipService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductCustomerController {

	
	private ProductService productService;
	private CategoryService categoryService;
	private OrderSessionService orderSessionService;
	private TownshipService townshipService;
	private OrderService orderService;
	
	@Autowired
	public ProductCustomerController(ProductService productService, CategoryService categoryService,
			OrderSessionService orderSessionService, TownshipService townshipService, OrderService orderService) {
		super();
		this.productService = productService;
		this.categoryService = categoryService;
		this.orderSessionService = orderSessionService;
		this.townshipService = townshipService;
		this.orderService = orderService;
	}





	@GetMapping("/product/{name}")
	public String product(@PathVariable("name") String productName, Model model) {
		ProductOrderForm pof = new ProductOrderForm();
		ProductDTO pdto = productService.getProductByName(productName);
		pof.setProductId(pdto.getId());
		pof.setAmount(1);
		model.addAttribute("form", pof);
		model.addAttribute("product", pdto);
		return "product";
	}
	
	@PostMapping("/addcart")
	public String addProductToCart(@ModelAttribute("form") ProductOrderForm pof,HttpSession session) {
	
		orderSessionService.addProductToOrderCart(pof, session);
		return "redirect:/home";
	}
	
	@GetMapping("/remove/{name}")
	public String removeProductFromCart(@PathVariable("name") String name, HttpSession session) {
		orderSessionService.removeProductFromOrderCart(name, session);
		return "redirect:/cart";
	}
	
	@PostMapping("/update")
	public String updateProductInCart(@RequestParam("id") String id, @RequestParam("amount") int amount, HttpSession session) {
		orderSessionService.updateProductFromOrderCart(id, amount, session);
		if(amount != 0) {
			orderSessionService.updateProductFromOrderCart(id, amount, session);
		}else if(amount == 0) {
			ProductDTO pdto = productService.getProductById(id);
			orderSessionService.removeProductFromOrderCart(pdto.getName(), session);
		}
		return "redirect:/cart";
	}
	
	@GetMapping("/orderform")
	public String orderForm(Model model) {
		model.addAttribute("form", new OrderForm());
		model.addAttribute("townships", townshipService.getAllTownships());
		return "orderform";
	}
	
	@PostMapping("/complete")
	public String completeOrder(@ModelAttribute("form") OrderForm form, HttpSession session) {
		orderService.saveOrder(form, session);
		return "redirect:/home";
	}
	
}
