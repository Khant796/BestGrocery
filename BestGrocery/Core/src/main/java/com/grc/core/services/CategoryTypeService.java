package com.grc.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grc.core.model.dto.CategoryTypeDTO;
import com.grc.core.model.form.CategoryTypeForm;

@Service
public interface CategoryTypeService {

	

	void saveCategoryType(CategoryTypeForm c);
	
	void removeCategoryType(String categoryTypeId);
	
	void updateCategoryType(CategoryTypeForm c, String catTypeId);
	
	int calculateCategoryTypeRevenue(String categoryTypeId);
	
	List<Integer> getAllRevenues(List<CategoryTypeDTO> dtos);
	
	CategoryTypeDTO getCategoryTypeById(String categoryTypeId);
	
	List<CategoryTypeDTO> getAllCategoryTypes();
	
}
