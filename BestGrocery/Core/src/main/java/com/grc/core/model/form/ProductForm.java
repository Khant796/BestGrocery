package com.grc.core.model.form;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProductForm {

	private String name;
	
	private int price;
	
	private String categoryName;
	
	private int stock;
	
	private boolean onSale;
}
