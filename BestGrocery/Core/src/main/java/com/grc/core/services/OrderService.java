package com.grc.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grc.core.model.dto.OrderDTO;
import com.grc.core.model.form.OrderForm;

import jakarta.servlet.http.HttpSession;

@Service
public interface OrderService {

	
	void saveOrder(OrderForm of, HttpSession session);
	
	void deleteOrder(String orderId);
	
	OrderDTO getOrderById(String orderId);
	
	List<OrderDTO> getAllOrdersDelivered();
	
	List<OrderDTO> getAllOrdersActive();
	
	int getOrderTotal(String id);
	
	void deliverOrder(String id);
	
}
