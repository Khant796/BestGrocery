package com.grc.core.model.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.grc.core.model.form.ProductOrderForm;

import lombok.Data;

@Component
@Data
@Scope(value = "session")
public class ProductOrderSession {

	String productName;
	
	String image;
	
	int amount;
	
	int cost;
	
	ProductOrderForm pof;
	
	
}
