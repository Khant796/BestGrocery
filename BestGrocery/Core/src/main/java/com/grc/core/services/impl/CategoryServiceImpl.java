package com.grc.core.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.grc.core.model.dto.CategoryDTO;
import com.grc.core.model.entity.Category;
import com.grc.core.model.form.CategoryForm;
import com.grc.core.repository.CategoryRepository;
import com.grc.core.repository.CategoryTypeRepository;
import com.grc.core.repository.ProductOrderRepository;
import com.grc.core.services.CategoryService;
import com.grc.core.services.ImageService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	private CategoryTypeRepository typeRepository;
	private ProductOrderRepository productOrderRepository;
	private ImageService imageService;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryTypeRepository typeRepository,
			ProductOrderRepository productOrderRepository, ImageService imageService) {
		super();
		this.categoryRepository = categoryRepository;
		this.typeRepository = typeRepository;
		this.productOrderRepository = productOrderRepository;
		this.imageService = imageService;
	}

	@Override
	public void saveCategory(CategoryForm c, MultipartFile imageFile) throws IOException {
		Category ct = new Category();
		ct.setName(c.getName());
		ct.setOnSale(true);
		ct.setType(typeRepository.findByName(c.getCategoryTypeName()));
		ct.setImage(imageService.saveImage(imageFile));
		categoryRepository.save(ct);
		
		
	}

	@Override
	public void removeCategory(String categoryId) {
		Optional<Category> res = categoryRepository.findById(categoryId);
		if(res.isPresent()) {
			categoryRepository.delete(res.get());
		}
		
	}

	@Override
	public void updateCategory(CategoryForm c, String categoryId) {
		
		Optional<Category> res = categoryRepository.findById(categoryId);
		if(res.isPresent()) {
			Category ct = res.get();
			ct.setName(c.getName());
			ct.setType(typeRepository.findByName(c.getCategoryTypeName()));
			categoryRepository.save(ct);
		}
	}

	@Override
	public int calculateCategoryRevenue(String categoryId) {
		Integer n = productOrderRepository.findCategoryRevenue(categoryId);
		if(n == null) {
			return 0;
		}else {
			return n;
		}
	
	}

	@Override
	public CategoryDTO getCategoryByName(String categoryName) {
		return categoryRepository.findByCatName(categoryName);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		return categoryRepository.findAllCategory();
	}

	@Override
	public List<CategoryDTO> getCategoriesByType(String categoryTypeId) {
		return categoryRepository.findByCategoryType(categoryTypeId);
	}

	@Override
	public List<Integer> getNumberOfProducts(List<CategoryDTO> categories) {
		List<Integer> num = new ArrayList<>();
		for(CategoryDTO c : categories) {
			num.add(categoryRepository.findNumOfProducts(c.getId()));
		}
		return num;
	}

	@Override
	public List<Integer> getCategoriesRevenues(List<CategoryDTO> categories) {
		List<Integer> num = new ArrayList<>();
		for(CategoryDTO c : categories) {
			num.add(calculateCategoryRevenue(c.getId()));
		}
		return num;
	}

	
	
}
