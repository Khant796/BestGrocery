package com.grc.core.model.entity;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderAddress implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "order_id")
	private String id;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "order_id")
	private Orders orders;
	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private String address;
	
	@ManyToOne
	@JoinColumn(name = "township_id")
	private Township township;
	
}
