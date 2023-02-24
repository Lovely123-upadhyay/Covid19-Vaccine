package com.masai.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.VaccinationCenter;
import com.masai.beans.Vaccine;
import com.masai.beans.VaccineCount;
import com.masai.beans.VaccineInventory;
import com.masai.exception.LoginException;
import com.masai.exception.VaccinationCenterException;
import com.masai.exception.VaccineInventoryException;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.VaccinationCenterRepository;
import com.masai.repository.VaccineCountRepository;
import com.masai.repository.VaccineInventoryRepository;

@Service
public class VaccineInventoryServiceImpl implements VaccineInventoryService{

	@Autowired
	private VaccineInventoryRepository vaccineInventoryRepo;
	
	@Autowired
	private VaccinationCenterRepository vaccinationCenterRepo;
	
	@Autowired
	private CurrentUserSessionRepo SessRepo;
	
	@Autowired
	private VaccineCountRepository countRepo;
	
	@Override
	public List<VaccineInventory> getAllVaccineInventories(String key) throws LoginException {
		if(SessRepo.findByUuid(key)!=null) {
			return vaccineInventoryRepo.findAll();
		}else {
			throw new LoginException("Please login as admin first !");
		}
	}

	@Override
	public Set<VaccineCount> addVaccineCount(String key,Integer inId,Vaccine v,Integer qty) throws VaccineInventoryException, LoginException {
		if(SessRepo.findByUuid(key)!=null) {
			VaccineInventory inventory=vaccineInventoryRepo.findById(inId).orElseThrow(()-> new VaccineInventoryException("Sorry: Inventory Not Found!"));
			if(inventory.getVaccineCount().contains(v)) {
				
			}else {
				VaccineCount c=new VaccineCount();
				c.setVaccine(v);
				c.setQuantity(qty);
				VaccineCount saved=countRepo.save(c);
				inventory.getVaccineCount().add(saved);
			}
			VaccineInventory savedInventory=vaccineInventoryRepo.save(inventory);
			return savedInventory.getVaccineCount();
		}else {
			throw new LoginException("Please login as admin first !");
		}
	}

	@Override
	public VaccineInventory getInventoryByVaccinationCenter(String key,Integer id) throws VaccinationCenterException, LoginException {
		if(SessRepo.findByUuid(key)!=null) {
			VaccinationCenter center=vaccinationCenterRepo.findById(id).orElseThrow(()-> new VaccinationCenterException("Sorry: Inventory Not Found!"));
			return center.getInventory();
		}else {
			throw new LoginException("Please login as admin first !");
		}
		
	}

	@Override
	public List<VaccineInventory> getInventoryByDate(String key,LocalDate date) throws LoginException {
		if(SessRepo.findByUuid(key)!=null) {
			return vaccineInventoryRepo.findByDate(date);
		}else {
			throw new LoginException("Please login as admin first !");
		}
	}

	@Override
	public VaccineInventory addInventory(String key, VaccineInventory inventory)
			throws LoginException, VaccinationCenterException {
		if(SessRepo.findByUuid(key)!=null) {
			return vaccineInventoryRepo.save(inventory);
		}else {
			throw new LoginException("Please login as admin first !");
		}
	}

//	@Override
//	public List<VaccineInventory> getInventoryByVaccine(Vaccine v) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
