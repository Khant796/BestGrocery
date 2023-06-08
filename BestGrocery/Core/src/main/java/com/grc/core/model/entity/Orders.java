package com.grc.core.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Orders")
public class Orders implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	
	private LocalDate orderDate;
	
	@OneToOne(mappedBy = "orders", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@PrimaryKeyJoinColumn
	private OrderAddress orderAddress;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<ProductOrder> products;
	
	private boolean delivered;
	
	public Orders() {
		this.products = new ArrayList<>();
	}
	
	public void setAddress(OrderAddress orderAddress) {
		orderAddress.setOrders(this);
		this.orderAddress = orderAddress;
	}
	
	public void addProduct(ProductOrder p) {
		this.products.add(p);
	}
	
}
