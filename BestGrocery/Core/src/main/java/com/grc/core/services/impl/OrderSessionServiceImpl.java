package com.grc.core.services.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grc.core.model.dto.ProductDTO;
import com.grc.core.model.entity.OrderAddress;
import com.grc.core.model.entity.Orders;
import com.grc.core.model.entity.Product;
import com.grc.core.model.entity.ProductOrder;
import com.grc.core.model.form.OrderForm;
import com.grc.core.model.form.ProductForm;
import com.grc.core.model.form.ProductOrderForm;
import com.grc.core.model.session.OrderCartSession;
import com.grc.core.model.session.ProductOrderSession;
import com.grc.core.repository.ProductOrderRepository;
import com.grc.core.repository.ProductRepository;
import com.grc.core.repository.TownshipRepository;
import com.grc.core.services.OrderSessionService;

import jakarta.servlet.http.HttpSession;

@Service
public class OrderSessionServiceImpl implements OrderSessionService {

	
	private TownshipRepository townshipRepository;
	private ProductRepository productRepository;
	private ProductOrderRepository poRepository;
	
	@Autowired
	public OrderSessionServiceImpl(TownshipRepository townshipRepository, ProductRepository productRepository,
			ProductOrderRepository poRepository) {
		super();
		this.townshipRepository = townshipRepository;
		this.productRepository = productRepository;
		this.poRepository = poRepository;
	}


	@Override
	public void addOrdeCartToSession(OrderCartSession o, HttpSession session) {
		OrderCartSession check = getOrderCartInSession(session);
		if(check == null) {
			session.setAttribute("orderCart", o);
		}
		
		
	}


	@Override
	public Orders createOrder(HttpSession session, OrderForm of) {
		
	
		OrderAddress oa = new OrderAddress();
		oa.setAddress(of.getAddress());
		oa.setFirstName(of.getFirstName());
		oa.setLastName(of.getLastName());
		oa.setPhone(of.getPhone());
		oa.setTownship(townshipRepository.findById(of.getTownshipId()).get());
		
		OrderCartSession ocs = (OrderCartSession) session.getAttribute("orderCart");
		
		Orders o = new Orders();
		
		for(ProductOrderSession pos : ocs.getProducts().values()) {
			ProductOrder po = changeToProductOrder(pos);
			o.addProduct(po);
		}
		
		o.setAddress(oa);
		o.setOrderDate(LocalDate.now());
		o.setDelivered(false);
		ocs.getProducts().clear();
		return o;
	}

	@Override
	public void addProductToOrderCart(ProductOrderForm p, HttpSession session) {
		ProductOrderSession pos = new ProductOrderSession();
		Product pr = productRepository.findById(p.getProductId()).get();
		pos.setProductName(pr.getName());
		pos.setImage(pr.getImage());
		ProductOrderForm pof = new ProductOrderForm();
		pof.setProductId(pr.getId());
		pos.setPof(pof);
		
		OrderCartSession ocs = (OrderCartSession) session.getAttribute("orderCart");
		
		if(ocs.getProducts().containsKey(pos.getProductName())) {
			ProductOrderSession current = ocs.getProducts().get(pos.getProductName());
			pos.setAmount(p.getAmount()+current.getAmount());
			int cost = p.getAmount() * pr.getPrice();
			pos.setCost(cost + current.getCost());
			ocs.replaceProduct(pos);
		}else {
			pos.setAmount(p.getAmount());
			pos.setCost(p.getAmount() * pr.getPrice());
			ocs.addProductToOrderCart(pos);
		}
	
		
		
	}

	@Transactional
	private ProductOrder changeToProductOrder(ProductOrderSession pos) {
		ProductOrder po = new ProductOrder();
		po.setAmount(pos.getAmount());
		po.setCost(pos.getCost());
		po.setProduct(productRepository.findByName(pos.getProductName()));
		poRepository.save(po);
		return po;
		
	}


	@Override
	public OrderCartSession getOrderCartInSession(HttpSession session) {
		OrderCartSession ocs = (OrderCartSession) session.getAttribute("orderCart");
		return ocs;
	}


	@Override
	public ProductOrderForm createProductOrderForm(String productName) {
		ProductOrderForm pof = new ProductOrderForm();
		Product p = productRepository.findByName(productName);
		pof.setProductId(p.getId());
		return pof;
	}


	@Override
	public void removeProductFromOrderCart(String productName, HttpSession session) {
		OrderCartSession ocs = (OrderCartSession) session.getAttribute("orderCart");
		ocs.getProducts().remove(productName);
		
	}


	@Override
	public void updateProductFromOrderCart(String productId, int amount, HttpSession session) {
		Product pr = productRepository.findById(productId).get();
		OrderCartSession ocs = (OrderCartSession) session.getAttribute("orderCart");
		ProductOrderSession pos = ocs.getProducts().get(pr.getName());
		pos.setAmount(amount);
		pos.setCost(amount * pr.getPrice());
		ocs.replaceProduct(pos);

		
	}

}
