package com.grc.core.services;

import org.springframework.stereotype.Service;

import com.grc.core.model.dto.ProductDTO;
import com.grc.core.model.entity.Orders;
import com.grc.core.model.form.OrderForm;
import com.grc.core.model.form.ProductForm;
import com.grc.core.model.form.ProductOrderForm;
import com.grc.core.model.session.OrderCartSession;
import com.grc.core.model.session.ProductOrderSession;

import jakarta.servlet.http.HttpSession;

@Service
public interface OrderSessionService {

	
	void addOrdeCartToSession(OrderCartSession o, HttpSession session);
	
	void removeProductFromOrderCart(String productName, HttpSession session);
	
	Orders createOrder(HttpSession session, OrderForm of);
	
	void addProductToOrderCart(ProductOrderForm p, HttpSession session);
	
	OrderCartSession getOrderCartInSession(HttpSession session);
	
	ProductOrderForm createProductOrderForm(String productName);
	
	void updateProductFromOrderCart(String productId, int amount, HttpSession session);
	
}
