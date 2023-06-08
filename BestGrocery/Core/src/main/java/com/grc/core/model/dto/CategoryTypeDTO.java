package com.grc.core.model.dto;

import java.util.List;

import com.grc.core.model.entity.Category;

public interface CategoryTypeDTO {

	String getId();
	
	String getName();
	
	List<CategoryDTO> getCategories();
	
}
