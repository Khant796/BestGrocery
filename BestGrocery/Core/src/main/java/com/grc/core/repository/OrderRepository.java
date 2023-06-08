package com.grc.core.repository;

import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grc.core.model.dto.OrderDTO;
import com.grc.core.model.entity.Orders;


@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {

	@Query("select o from Orders o where id = :id")
	OrderDTO searchById(@Param("id") String id);
	
	@Query("select o from Orders o where o.delivered = :deli")
	List<OrderDTO> findAllOrdersDelivered(@Param("deli") boolean deli);
	
	@Query("update Orders o set o.delivered = true where o.id = :id")
	void finishDelivery(@Param("id") String id);
	
}
