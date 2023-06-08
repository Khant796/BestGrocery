package com.grc.core.model.dto;

public interface OrderAddressDTO {

	
	String getId();
	
	String getFirstName();
	
	String getLastName();
	
	String getPhone();
	
	String getAddress();
	
	TownshipDTO getTownship();
	
	OrderDTO getOrder();
	
}
