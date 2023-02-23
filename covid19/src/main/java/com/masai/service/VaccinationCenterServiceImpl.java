package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.VaccinationCenter;
import com.masai.beans.VaccineInventory;
import com.masai.repository.VaccinationCenterRepository;
import com.masai.repository.VaccineInventoryRepository;

@Service
public class VaccinationCenterServiceImpl implements VaccinationCenterService{
	
	@Autowired
	private VaccinationCenterRepository vaccineCenterRepo;
	
	@Autowired
	private VaccineInventoryRepository vaccineInventoryRepo;
	
	@Override
	public List<VaccinationCenter> AllVaccivationCenters() {
		return vaccineCenterRepo.findAll();
	}

	@Override
	public VaccinationCenter addNewVaccinationCenter(VaccinationCenter center) {
		VaccineInventory inventory =new VaccineInventory();
		center.setInventory(vaccineInventoryRepo.save(inventory));
		return vaccineCenterRepo.save(center);
	}

}
