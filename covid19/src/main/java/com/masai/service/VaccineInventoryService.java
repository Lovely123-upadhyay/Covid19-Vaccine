package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;

import com.masai.beans.Vaccine;
import com.masai.beans.VaccineInventory;
import com.masai.exception.VaccinationCenterException;
import com.masai.exception.VaccineInventoryException;

public interface VaccineInventoryService {
	public List<VaccineInventory> getAllVaccineInventories();
	public VaccineInventory getInventoryByVaccinationCenter(Integer id) throws VaccinationCenterException;
	public VaccineInventory addVaccineCount(Integer inId,Vaccine v,Integer qty) throws VaccineInventoryException; 
	public List<VaccineInventory> getInventoryByDate(LocalDateTime date);
//	public List<VaccineInventory> getInventoryByVaccine(Vaccine v);
}
