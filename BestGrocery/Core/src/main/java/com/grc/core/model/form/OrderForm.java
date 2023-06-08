package com.grc.core.model.form;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class OrderForm {

	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private String address;
	
	private String townshipId;
	
	
}
