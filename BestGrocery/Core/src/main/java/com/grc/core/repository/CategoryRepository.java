package com.grc.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grc.core.model.dto.CategoryDTO;
import com.grc.core.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

	
	Category findByName(String name);
	
	Optional<Category> findById(String id);
	
	@Query("select c from Category c where c.onSale")
	List<CategoryDTO> findAllCategory();
	
	@Query("select c from Category c where c.name = :name")
	CategoryDTO findByCatName(@Param("name") String name);
	
	@Query("select size(c.products) from Category c where c.id = :id")
	Integer findNumOfProducts(@Param("id") String id);
	
	@Query("select c from Category c where c.type.id = :id")
	List<CategoryDTO> findByCategoryType(@Param("id") String catTypeId);

}
