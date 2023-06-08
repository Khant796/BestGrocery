package com.grc.core.model.form;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ProductOrderForm {

	private String productId;
	
	private int amount;
	
}
