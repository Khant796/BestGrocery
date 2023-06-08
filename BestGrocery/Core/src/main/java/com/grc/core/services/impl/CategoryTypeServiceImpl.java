package com.grc.core.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grc.core.model.dto.CategoryTypeDTO;
import com.grc.core.model.entity.CategoryType;
import com.grc.core.model.form.CategoryTypeForm;
import com.grc.core.repository.CategoryTypeRepository;
import com.grc.core.repository.ProductOrderRepository;
import com.grc.core.services.CategoryTypeService;

@Service
public class CategoryTypeServiceImpl implements CategoryTypeService {

	
	private CategoryTypeRepository categoryTypeRepository;
	private ProductOrderRepository productOrderRepository;
	
	
	@Autowired
	public CategoryTypeServiceImpl(CategoryTypeRepository categoryTypeRepository,
			ProductOrderRepository productOrderRepository) {
		super();
		this.categoryTypeRepository = categoryTypeRepository;
		this.productOrderRepository = productOrderRepository;
	}

	@Override
	public void saveCategoryType(CategoryTypeForm c) {
		CategoryType ct = new CategoryType();
		ct.setName(c.getName());
		categoryTypeRepository.save(ct);
	}

	@Override
	public void removeCategoryType(String categoryTypeId) {
		Optional<CategoryType> res = categoryTypeRepository.findById(categoryTypeId);
		if(res.isPresent()) {
			CategoryType ct = res.get();
			categoryTypeRepository.delete(ct);
		}
	}


	@Override
	public int calculateCategoryTypeRevenue(String categoryTypeId) {
		Integer res = productOrderRepository.findCategoryTypeRevenue(categoryTypeId);
		if(res == null) {
			return 0;
		}
		return res;
	}

	@Override
	public CategoryTypeDTO getCategoryTypeById(String categoryTypeId) {
		return categoryTypeRepository.findByCatTypeId(categoryTypeId);
	}

	@Override
	public List<CategoryTypeDTO> getAllCategoryTypes() {
		return categoryTypeRepository.findAllCatType();
	}

	@Override
	public void updateCategoryType(CategoryTypeForm c, String catTypeId) {
		Optional<CategoryType> res = categoryTypeRepository.findById(catTypeId);
		if(res.isPresent()) {
			CategoryType ct = res.get();
			ct.setName(c.getName());
			categoryTypeRepository.save(ct);
		}
	}

	@Override
	public List<Integer> getAllRevenues(List<CategoryTypeDTO> dtos) {
		List<Integer> revenues = new ArrayList<>();
		for(CategoryTypeDTO dto : dtos) {
			revenues.add(calculateCategoryTypeRevenue(dto.getId()));
		}
		return revenues;
	}

}
