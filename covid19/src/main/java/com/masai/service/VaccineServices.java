package com.masai.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.masai.beans.Vaccine;
import com.masai.exception.VaccineException;

public interface VaccineServices {
	public Vaccine addVaccine(Vaccine v);
	public List<Vaccine> allVaccines();
	public Vaccine getVaccineByName(String name) throws VaccineException;
	public Vaccine getVaccineById(Integer id) throws VaccineException;
	public Vaccine updateVaccine(Integer id,Vaccine v) throws VaccineException;
	public Boolean deleteVaccine(Integer id) throws VaccineException; 
	
}
