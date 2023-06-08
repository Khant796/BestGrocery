package com.grc.core.model.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	
	private String name;
	
	private int price;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category", nullable = true)
	private Category category;
	
	private int stock;
	
	private boolean onSale;
	
	private String image;
	
	@OneToMany(mappedBy = "product")
	private List<ProductOrder> productOrders;
	
}
