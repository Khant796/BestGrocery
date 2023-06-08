package com.grc.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grc.core.model.dto.CategoryDTO;
import com.grc.core.model.dto.CategoryTypeDTO;
import com.grc.core.model.entity.CategoryType;

@Repository
public interface CategoryTypeRepository extends JpaRepository<CategoryType, String> {

	
	CategoryType findByName(String name);
	
	@Query("select ct from CategoryType ct")
	List<CategoryTypeDTO> findAllCatType();
	
	@Query("select ct from CategoryType ct where ct.id = :id")
	CategoryTypeDTO findByCatTypeId(@Param("id") String id);
	
}
