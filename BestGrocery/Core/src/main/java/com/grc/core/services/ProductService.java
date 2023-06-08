package com.grc.core.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.grc.core.model.dto.ProductDTO;
import com.grc.core.model.form.ProductForm;

@Service
public interface ProductService {

	void saveProduct(ProductForm p, MultipartFile imageFile) throws Exception;
	
	void saveProductTest(ProductForm p);
	
	void retireProduct(String productId);
	
	void updateProductPrice(String productId, int newPrice);
	
	void updateProductStock(String productId, int changeInStock, boolean add);
	
	int calculateProductTotalRevenue(String productId);
	
	void renameProduct(String productId, String newName);
	
	ProductDTO getProductByName(String name);
	
	ProductDTO getProductById(String id);
	
	List<ProductDTO> getAllProducts();
	
	List<Integer> getProductsRevenue(List<ProductDTO> products);
	
	List<ProductDTO> getProductsByCategory(String categoryId);
	
}
