package com.grc.core.model.entity;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "category_type")
	private CategoryType type;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private List<Product> products;
	
	private boolean onSale;
	
	private String image;
	
	@PreRemove
	public void deleteCategory() {
		this.getProducts().forEach(product -> 
		{product.setCategory(null);
		product.setOnSale(false);
		});
	}
	
}
