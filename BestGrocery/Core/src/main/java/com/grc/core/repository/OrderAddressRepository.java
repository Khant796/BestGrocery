package com.grc.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grc.core.model.entity.OrderAddress;

@Repository
public interface OrderAddressRepository extends JpaRepository<OrderAddress, String> {

}
