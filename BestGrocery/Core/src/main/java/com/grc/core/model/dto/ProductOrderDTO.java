package com.grc.core.model.dto;

public interface ProductOrderDTO {

	String getId();
	
	int getAmount();
	
	int getCost();
	
	ProductDTO getProduct();
}
