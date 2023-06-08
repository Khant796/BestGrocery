package com.grc.core.model.dto;

import java.time.LocalDate;
import java.util.List;

import com.grc.core.model.entity.OrderAddress;

public interface OrderDTO {

	
	String getId();
	
	LocalDate getOrderDate();
	
	OrderAddressDTO getOrderAddress();
	
	List<ProductOrderDTO> getProducts();
	
	boolean getDelivered();
}
