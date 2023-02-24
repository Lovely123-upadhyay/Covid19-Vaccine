package com.masai.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.masai.beans.Vaccine;
import com.masai.beans.VaccineInventory;
import com.masai.exception.LoginException;
import com.masai.exception.VaccinationCenterException;
import com.masai.exception.VaccineInventoryException;

public interface VaccineInventoryService {
	public List<VaccineInventory> getAllVaccineInventories(String key) throws LoginException;
	public VaccineInventory getInventoryByVaccinationCenter(String key,Integer id) throws LoginException,VaccinationCenterException;
	public VaccineInventory addVaccineCount(String key,Integer inId,Vaccine v,Integer qty) throws LoginException,VaccineInventoryException; 
	public List<VaccineInventory> getInventoryByDate(String key,LocalDate date)throws LoginException;
//	public List<VaccineInventory> getInventoryByVaccine(Vaccine v);
}
