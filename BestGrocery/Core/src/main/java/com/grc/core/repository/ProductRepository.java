package com.grc.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grc.core.model.dto.ProductDTO;
import com.grc.core.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	
	List<ProductDTO> findByOnSaleTrue();
	
	Optional<Product> findById(String id);
	
	@Query("select p from Product p where p.id = :id")
	ProductDTO findByIdDTO(@Param("id") String id);
	
	@Query("select p from Product p where p.name = :name")
	ProductDTO findByNameDTO(@Param("name") String name);
	
	@Query("select p from Product p where p.name = :name")
	Product findByName(@Param("name") String name);
	
	@Query("select p from Product p where p.category.id = :catId")
	List<ProductDTO> findByCategory(@Param("catId") String catId);
	
}
