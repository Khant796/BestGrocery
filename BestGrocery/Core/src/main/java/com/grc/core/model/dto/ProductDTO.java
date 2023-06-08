package com.grc.core.model.dto;


public interface ProductDTO {

	String getId();
	
	String getName();
	
	int getPrice();
	
	int getStock();
	
	CategoryDTO getCategory();
	
	boolean getOnSale();
	
	String getImage();
	
}
