package com.masai.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.masai.beans.Vaccine;
import com.masai.beans.VaccineCount;
import com.masai.beans.VaccineInventory;
import com.masai.exception.LoginException;
import com.masai.exception.VaccinationCenterException;
import com.masai.exception.VaccineInventoryException;

public interface VaccineInventoryService {
	public VaccineInventory addInventory(String key,VaccineInventory inventory)throws LoginException,VaccinationCenterException;
	public List<VaccineInventory> getAllVaccineInventories(String key) throws LoginException;
	public VaccineInventory getInventoryByVaccinationCenter(String key,Integer id) throws LoginException,VaccinationCenterException;
	public VaccineCount addVaccineCount(String key,Integer inId,Vaccine v,Integer qty) throws LoginException,VaccineInventoryException; 
	public List<VaccineInventory> getInventoryByDate(String key,LocalDate date)throws LoginException;
//	public List<VaccineInventory> getInventoryByVaccine(Vaccine v);
}
