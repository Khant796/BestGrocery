package com.grc.core.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.grc.core.model.dto.CategoryDTO;
import com.grc.core.model.entity.Product;
import com.grc.core.model.form.CategoryForm;

@Service
public interface CategoryService {

	
	void saveCategory(CategoryForm c, MultipartFile imageFile) throws IOException;
	
	void removeCategory(String categoryId);
	
	void updateCategory(CategoryForm c, String categoryId);
	
	int calculateCategoryRevenue(String categoryId);
	
	CategoryDTO getCategoryByName(String categoryName);
	
	List<CategoryDTO> getAllCategories();
	
	List<Integer> getNumberOfProducts(List<CategoryDTO> categories);
	
	List<Integer> getCategoriesRevenues(List<CategoryDTO> categories);
	
	List<CategoryDTO> getCategoriesByType(String categoryTypeId);
	
}
