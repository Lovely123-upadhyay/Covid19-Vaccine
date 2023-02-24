package com.masai.service;

import java.util.List;

import com.masai.beans.VaccinationCenter;
import com.masai.beans.VaccineInventory;
import com.masai.exception.LoginException;
import com.masai.exception.VaccinationCenterException;

public interface VaccinationCenterService {
	
	public VaccinationCenter addNewVaccinationCenter( String key, VaccinationCenter center) throws LoginException,VaccinationCenterException;
	
	public VaccinationCenter getVaccinationCenterById( String key, Integer id) throws LoginException,VaccinationCenterException;
	
	public List<VaccinationCenter> getAllVaccivationCenters( String key ) throws LoginException,VaccinationCenterException;
	
	public VaccinationCenter updateVaccinationCenter( String key , VaccinationCenter center) throws LoginException,VaccinationCenterException ;
	
	public Boolean deleteVaccinationCenter( String key , Integer id) throws LoginException,VaccinationCenterException ;
}
