package com.grc.core.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import com.grc.core.model.dto.CategoryDTO;
import com.grc.core.model.dto.CategoryTypeDTO;
import com.grc.core.model.dto.ProductDTO;
import com.grc.core.model.dto.TownshipDTO;
import com.grc.core.model.entity.Orders;
import com.grc.core.model.entity.Township;
import com.grc.core.model.form.CategoryForm;
import com.grc.core.model.form.CategoryTypeForm;
import com.grc.core.model.form.OrderForm;
import com.grc.core.model.form.ProductForm;
import com.grc.core.model.form.ProductOrderForm;
import com.grc.core.model.form.TownshipForm;
import com.grc.core.model.session.OrderCartSession;
import com.grc.core.model.session.ProductOrderSession;
import com.grc.core.repository.ProductRepository;
import com.grc.core.services.CategoryService;
import com.grc.core.services.CategoryTypeService;
import com.grc.core.services.OrderService;
import com.grc.core.services.OrderSessionService;
import com.grc.core.services.ProductService;
import com.grc.core.services.TownshipService;

import jakarta.transaction.Transactional;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ServiceTest {

	
	
	private CategoryTypeService categoryTypeService;
	private CategoryService categoryService;
	private ProductService productService;
	private OrderService orderService;
	private MockHttpSession httpSession;
	private OrderSessionService orderSessionService;
	private TownshipService townshipService;

	@BeforeAll
	void setup() {
		httpSession = new MockHttpSession();
	}
	
	@Autowired
	public ServiceTest(CategoryTypeService categoryTypeService, CategoryService categoryService,
			ProductService productService, OrderSessionService orderSessionService, TownshipService townshipService, OrderService orderService) {
		super();
		this.categoryTypeService = categoryTypeService;
		this.categoryService = categoryService;
		this.productService = productService;
		this.townshipService = townshipService;
		this.orderSessionService = orderSessionService;
		this.orderService = orderService;
	
	}

	@ParameterizedTest
	@CsvSource({"Meat", "Softdrink", "Snacks"})
	@Order(1)
	void addCategoryTypeTest(String name) {
		CategoryTypeForm c = new CategoryTypeForm();
		c.setName(name);
		categoryTypeService.saveCategoryType(c);
	}
	
	@ParameterizedTest
	@CsvSource({"Chicken, Meat", "Pork, Meat", "Beef, Meat", "Mutton, Meat", "Cola, Softdrink"})
	@Order(2)
	void addCategory(String name, String type) {
		CategoryForm c = new CategoryForm();
		c.setName(name);
		c.setCategoryTypeName(type);
	
	}
	
	
	@ParameterizedTest
	@CsvSource({"Chicken wing, 100, 2000, Chicken", "Chicken breast, 200, 5000, Chicken", "Chicken thigh, 50, 2500, Chicken", 
		"Grounded beef, 40, 6500, Beef"})
	@Order(3)
	void addProduct(String name, int stock, int price, String catName) throws Exception {
		ProductForm p = new ProductForm();
		p.setName(name);
		p.setStock(stock);
		p.setPrice(price);
		p.setOnSale(true);
		p.setCategoryName(catName);
		MockMultipartFile mockMultipartFile = new MockMultipartFile(name, new byte[] {});
		productService.saveProductTest(p);
		
	}
	
	@ParameterizedTest
	@CsvSource({"Chicken wing, 2000", "Grounded beef, 6500"})
	@Order(4)
	void getProduct(String name, int price) {
	 ProductDTO p = productService.getProductByName(name);
	 assertEquals(price, p.getPrice());
	}
	
	
	@Test
	@Order(5)
	void getAllProduct() {
		List<ProductDTO> ps = productService.getAllProducts();
		assertEquals(4, ps.size());
	}
	
	@ParameterizedTest
	@CsvSource({"Chicken, 3", "Beef, 1"})
	@Order(6)
	void getProductByCategory(String cat, int expected) {
		CategoryDTO c = categoryService.getCategoryByName(cat);
		List<ProductDTO> ps = productService.getProductsByCategory(c.getId());
		assertEquals(expected, ps.size());
	}
	
	@Test
	@Order(6)
	void getByName() {
		List<ProductDTO> ps = productService.getAllProducts();
		for(ProductDTO p : ps) {
			ProductDTO res = productService.getProductByName(p.getName());
			assertEquals(p.getId(), res.getId());
		}
	}
	
	
	@Test
	@Order(7)
	void getCategories() {
	 List<CategoryDTO> cs = categoryService.getAllCategories();
	 assertEquals(5, cs.size());

	}
	
	@ParameterizedTest
	@CsvSource({"Meat, 4", "Softdrink, 1"})
	@Order(8)
	void getCategoriesFromType(String typeName, int expected) {
		List<CategoryTypeDTO> cts = categoryTypeService.getAllCategoryTypes();
		for(CategoryTypeDTO ct : cts) {
			if(ct.getName().equals(typeName)) {
				List<CategoryDTO> cs = categoryService.getCategoriesByType(ct.getId());
				assertEquals(expected, cs.size());
			}
		}
		
	}
	
	@Test
	@Order(9)
	void test() {
		orderSessionService.addOrdeCartToSession(new OrderCartSession(), this.httpSession);
		
	}
	
	@CsvSource({"20, Chicken wing"})
	@ParameterizedTest
	@Order(10)
	void test2(int amount, String name) {
		ProductOrderForm pof = new ProductOrderForm();
		 ProductDTO p = productService.getProductByName(name);
		pof.setAmount(20);
		pof.setProductId(p.getId());
		orderSessionService.addProductToOrderCart(pof, this.httpSession);
		OrderCartSession ocs = orderSessionService.getOrderCartInSession(this.httpSession);
		assertEquals(1, ocs.getProducts().size());
	}

	@Order(11)
	@Test
	void getProductFromSession() {
		OrderCartSession ocs = orderSessionService.getOrderCartInSession(httpSession);
		assertEquals(1, ocs.getProducts().size());
		TownshipForm f = new TownshipForm();
		f.setTownshipName("Dagon");
		townshipService.saveTownship(f);
		OrderForm of = new OrderForm();
		of.setAddress("somewhere");
		of.setFirstName("someone");
		of.setPhone("09090090");
		of.setLastName("somename");
		of.setTownshipId(townshipService.getAllTownships().get(0).getId());
		orderService.saveOrder(of, httpSession);
	}
	
	@Order(12)
	@Test
	void testRevenue() {
		ProductDTO p = productService.getProductByName("Chicken wing");
		int num = productService.calculateProductTotalRevenue(p.getId());
		assertEquals(40000, num);
		
	
	}
}
