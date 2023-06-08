package com.grc.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grc.core.model.dto.OrderDTO;
import com.grc.core.model.entity.OrderAddress;
import com.grc.core.model.entity.Orders;
import com.grc.core.model.entity.ProductOrder;
import com.grc.core.model.form.OrderForm;
import com.grc.core.repository.OrderRepository;
import com.grc.core.repository.TownshipRepository;
import com.grc.core.services.OrderService;
import com.grc.core.services.OrderSessionService;
import com.grc.core.services.ProductService;

import jakarta.servlet.http.HttpSession;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	
	private OrderRepository orderRepository;
	private OrderSessionService orderSessionService;
	private TownshipRepository townshipRepository;
	private ProductService productService;
	
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, OrderSessionService orderSessionService,
			TownshipRepository townshipRepository, ProductService productService) {
		super();
		this.orderRepository = orderRepository;
		this.orderSessionService = orderSessionService;
		this.townshipRepository = townshipRepository;
		this.productService = productService;
	}

	@Override
	public void saveOrder(OrderForm of, HttpSession session) {
		
		Orders os = orderSessionService.createOrder(session, of);
		for(ProductOrder po : os.getProducts()) {
			productService.updateProductStock(po.getProduct().getId(), po.getAmount(), false);
		}
		orderRepository.save(os);
	}

	@Override
	public void deleteOrder(String orderId) {
		Optional<Orders> res = orderRepository.findById(orderId);
		if(res.isPresent()) {
			orderRepository.delete(res.get());
		}
		
	}

	@Override
	public OrderDTO getOrderById(String orderId) {
		return orderRepository.searchById(orderId);
	}

	@Override
	public List<OrderDTO> getAllOrdersDelivered() {
		return orderRepository.findAllOrdersDelivered(true);
	}

	@Override
	public List<OrderDTO> getAllOrdersActive() {
		return orderRepository.findAllOrdersDelivered(false);
	}

	@Override
	public int getOrderTotal(String id) {
		Optional<Orders> res = orderRepository.findById(id);
		int total = 0;
		if(res.isPresent()) {
			for(ProductOrder po : res.get().getProducts()) {
				
				total += po.getCost();
			}
		}
		return total;
	}

	@Override
	public void deliverOrder(String id) {
		Optional<Orders> res = orderRepository.findById(id);
		if(res.isPresent()) {
			Orders o = res.get();
			o.setDelivered(true);
			orderRepository.save(o);
		}
		
	}
	
	
	
	

}
