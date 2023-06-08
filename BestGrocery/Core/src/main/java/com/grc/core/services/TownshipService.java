package com.grc.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grc.core.model.dto.TownshipDTO;
import com.grc.core.model.form.TownshipForm;

@Service
public interface TownshipService {

	
	void saveTownship(TownshipForm t);
	
	void removeTownship(String townshipId);
	
	List<TownshipDTO> getAllTownships();
	
	TownshipDTO getTownshipById(String townshipId);
}
