package com.grc.core.model.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.grc.core.model.dto.ProductDTO;
import com.grc.core.model.entity.Product;

import lombok.Data;

@Data
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderCartSession {

	private Map<String, ProductOrderSession> products;
	
	
	public OrderCartSession() {
		this.products = new HashMap<>();
	}
	
	
	public void addProductToOrderCart(ProductOrderSession p) {
		this.products.put(p.getProductName(), p);
	}
	
	public void replaceProduct(ProductOrderSession p) {
		this.products.replace(p.getProductName(), p);
	}
}
