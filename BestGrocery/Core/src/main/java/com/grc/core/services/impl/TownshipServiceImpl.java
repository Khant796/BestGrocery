package com.grc.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grc.core.model.dto.TownshipDTO;
import com.grc.core.model.entity.Township;
import com.grc.core.model.form.TownshipForm;
import com.grc.core.repository.TownshipRepository;
import com.grc.core.services.TownshipService;

@Service
public class TownshipServiceImpl implements TownshipService {

	
	private TownshipRepository townshipRepository;
	
	@Autowired
	public TownshipServiceImpl(TownshipRepository townshipRepository) {
		super();
		this.townshipRepository = townshipRepository;
	}

	@Override
	public void saveTownship(TownshipForm t) {
		Township township = new Township();
		township.setName(t.getTownshipName());
		townshipRepository.save(township);
		
	}

	@Override
	public void removeTownship(String townshipId) {
		Optional<Township> res = townshipRepository.findById(townshipId);
		if(res.isPresent()) {
			townshipRepository.delete(res.get());
		}
		
		
	}

	@Override
	public List<TownshipDTO> getAllTownships() {
		return townshipRepository.findAllTownship();
	}

	@Override
	public TownshipDTO getTownshipById(String townshipId) {
		return townshipRepository.findTownshipById(townshipId);
	}

}
