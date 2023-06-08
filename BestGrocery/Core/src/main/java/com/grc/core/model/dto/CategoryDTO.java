package com.grc.core.model.dto;

import java.util.List;

import com.grc.core.model.entity.CategoryType;

public interface CategoryDTO {

	
	String getId();
	
	String getName();
	
	String getImage();
	
	List<ProductDTO> getProducts();
	
	CategoryTypeDTO getType();
	
}
