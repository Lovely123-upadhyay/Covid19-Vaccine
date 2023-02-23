package com.masai.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.masai.beans.Vaccine;

public interface VaccineServices {
	public Vaccine addVaccine(Vaccine v);
	public List<Vaccine> allVaccines();
	public Vaccine getVaccineByName(String name);
	public Vaccine getVaccineById(Integer id);
	public Vaccine updateVaccine(Integer id,Vaccine v);
	public Boolean deleteVaccine(Integer id); 
	
}
