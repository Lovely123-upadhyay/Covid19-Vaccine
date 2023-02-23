package com.masai.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.VaccinationCenter;
import com.masai.beans.Vaccine;
import com.masai.beans.VaccineCount;
import com.masai.beans.VaccineInventory;
import com.masai.repository.VaccinationCenterRepository;
import com.masai.repository.VaccineInventoryRepository;

@Service
public class VaccineInventoryServiceImpl implements VaccineInventoryService{

	@Autowired
	private VaccineInventoryRepository vaccineInventoryRepo;
	
	@Autowired
	private VaccinationCenterRepository vaccinationCenterRepo;
	
	@Override
	public List<VaccineInventory> getAllVaccineInventories() {
		return vaccineInventoryRepo.findAll();
	}

	@Override
	public VaccineInventory addVaccineCount(Integer inId,Vaccine v,Integer qty) {
		VaccineInventory inventory=vaccineInventoryRepo.findById(inId).get();
		List<VaccineCount> list=inventory.getVaccineCount();
		int flag=0;
		for(VaccineCount i:list) {
			if(i.getVaccine().getVaccineId()==v.getVaccineId()) {
				i.setQuantity(i.getQuantity()+qty);
				flag=1;
				break;
			}
		}
		if(flag==0 || list.size()==0) {
			VaccineCount newV=new VaccineCount();
			newV.setVaccine(v);
			newV.setQuantity(qty);
			list.add(newV);
		}
		return vaccineInventoryRepo.save(inventory);
	}

	@Override
	public VaccineInventory getInventoryByVaccinationCenter(Integer id) {
		VaccinationCenter center=vaccinationCenterRepo.findById(id).get();
		return center.getInventory();
	}

	@Override
	public List<VaccineInventory> getInventoryByDate(LocalDateTime date) {
		return vaccineInventoryRepo.findByDate(date);
	}

//	@Override
//	public List<VaccineInventory> getInventoryByVaccine(Vaccine v) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
