package com.grc.core.services.impl;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import com.grc.core.model.dto.ProductDTO;
import com.grc.core.model.entity.Category;
import com.grc.core.model.entity.Product;
import com.grc.core.model.form.ProductForm;
import com.grc.core.repository.CategoryRepository;
import com.grc.core.repository.ProductOrderRepository;
import com.grc.core.repository.ProductRepository;
import com.grc.core.services.CategoryService;
import com.grc.core.services.ImageService;
import com.grc.core.services.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	
	private CategoryRepository categoryRepository;
	
	private ProductRepository productRepository;
	
	private ProductOrderRepository productOrderRepository;
	
	private ImageService imageService;
	
	@Autowired
	public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository,
			ProductOrderRepository productOrderRepository, ImageService imageService) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
		this.productOrderRepository = productOrderRepository;
		this.imageService = imageService;
	}
	

	@Override
	public void saveProduct(ProductForm p, MultipartFile imageFile) throws Exception {
		Product pr = new Product();
		pr.setName(p.getName());
		pr.setOnSale(p.isOnSale());
		pr.setPrice(p.getPrice());
		pr.setStock(p.getStock());
		Category c = categoryRepository.findByName(p.getCategoryName());
		pr.setCategory(c);
		String fileName = imageService.saveImage(imageFile);
		pr.setImage(fileName);
		productRepository.save(pr);
		
	}

	@Override
	public void retireProduct(String productId) {
		Optional<Product> res = productRepository.findById(productId);
		if(res.isPresent()) {
			Product p = res.get();
			p.setOnSale(false);
			productRepository.save(p);
		}
	}

	@Override
	public void updateProductPrice(String productId, int newPrice) {
		Optional<Product> res = productRepository.findById(productId);
		if(res.isPresent()) {
			Product p = res.get();
			p.setPrice(newPrice);
			productRepository.save(p);
		}
		
	}

	@Override
	public void updateProductStock(String productId, int changeInStock, boolean add) {
		Optional<Product> res = productRepository.findById(productId);
		if(res.isPresent()) {
			Product p = res.get();
			if(add == true) {
				p.setStock(p.getStock() + changeInStock);
				productRepository.save(p);
			}else {
				p.setStock(p.getStock() - changeInStock);
				productRepository.save(p);
			}
			
		}
		
	}

	@Override
	public int calculateProductTotalRevenue(String productId) {
		Integer revenue = productOrderRepository.findProductRevenue(productId);
		if(revenue == null) {
			return 0;
		}
		return revenue;
	}

	@Override
	public void renameProduct(String productId, String newName) {
		Optional<Product> res = productRepository.findById(productId);
		if(res.isPresent()) {
			Product p = res.get();
			p.setName(newName);
			productRepository.save(p);
		}
		
	}

	@Override
	public ProductDTO getProductByName(String name) {
		ProductDTO p = productRepository.findByNameDTO(name);
		return p;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		return productRepository.findByOnSaleTrue();
	}

	@Override
	public List<ProductDTO> getProductsByCategory(String categoryId) {
		List<ProductDTO> products = productRepository.findByCategory(categoryId);
		return products;
	}
	


	@Override
	public ProductDTO getProductById(String id) {
		return productRepository.findByIdDTO(id);
	}


	@Override
	public List<Integer> getProductsRevenue(List<ProductDTO> products) {
		List<Integer> revenue = new ArrayList<>();
		for(ProductDTO p : products) {
			var rev = calculateProductTotalRevenue(p.getId());
			revenue.add(rev);
	
		}
		return revenue;
	}


	@Override
	public void saveProductTest(ProductForm p) {
		
			Product pr = new Product();
			pr.setName(p.getName());
			pr.setOnSale(p.isOnSale());
			pr.setPrice(p.getPrice());
			pr.setStock(p.getStock());
			Category c = categoryRepository.findByName(p.getCategoryName());
			pr.setCategory(c);
			productRepository.save(pr);
		
	}
}
