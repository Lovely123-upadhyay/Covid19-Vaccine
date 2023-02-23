package com.masai.service;

import java.util.List;

import com.masai.beans.VaccinationCenter;
import com.masai.beans.VaccineInventory;

public interface VaccinationCenterService {
	public List<VaccinationCenter> AllVaccivationCenters();
	//public VaccinationCenter getVaccinationCenterById(Integer id);
	public VaccinationCenter addNewVaccinationCenter(VaccinationCenter center);
	//public VaccinationCenter updateVaccinationCenter(VaccinationCenter center);
	//public Boolean deleteVaccinationCenter(VaccinationCenter center);
}
