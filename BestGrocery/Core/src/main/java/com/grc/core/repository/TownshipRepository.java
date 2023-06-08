package com.grc.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grc.core.model.dto.TownshipDTO;
import com.grc.core.model.entity.Township;

@Repository
public interface TownshipRepository extends JpaRepository<Township, String> {

	@Query("select t from Township t")
	List<TownshipDTO> findAllTownship();
	
	@Query("select t from Township t where id = :id")
	TownshipDTO findTownshipById(@Param("id") String id);
	
}
