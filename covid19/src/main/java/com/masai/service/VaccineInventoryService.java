package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;

import com.masai.beans.Vaccine;
import com.masai.beans.VaccineInventory;

public interface VaccineInventoryService {
	public List<VaccineInventory> getAllVaccineInventories();
	public VaccineInventory getInventoryByVaccinationCenter(Integer id);
	public VaccineInventory addVaccineCount(Integer inId,Vaccine v,Integer qty); 
	public List<VaccineInventory> getInventoryByDate(LocalDateTime date);
//	public List<VaccineInventory> getInventoryByVaccine(Vaccine v);
}
