package com.grc.core.model.form;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProductCartForm {

	private String productName;
	
	private int amount;
	
	private int cost;
	
	
}
