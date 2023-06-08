package com.grc.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grc.core.model.entity.ProductOrder;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, String> {

	@Query("select sum(po.cost) from ProductOrder po where po.product.id = :id group by po.product.id")
	Integer findProductRevenue(@Param("id") String productId);
	
	@Query("select sum(po.cost) from ProductOrder po where po.product.category.id = :id group by po.product.category.id")
	Integer findCategoryRevenue(@Param("id") String categoryId);
	
	@Query("select sum(po.cost) from ProductOrder po where po.product.category.type.id = :id group by po.product.category.type.id")
	Integer findCategoryTypeRevenue(@Param("id") String categoryTypeId);
	
}
