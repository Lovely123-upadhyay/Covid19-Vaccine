package com.masai.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.masai.beans.Vaccine;
import com.masai.exception.LoginException;
import com.masai.exception.VaccineException;

public interface VaccineServices {
	public Vaccine addVaccine(String key,Vaccine v) throws LoginException;
	public List<Vaccine> allVaccines(String key) throws LoginException,VaccineException;
	public Vaccine getVaccineByName(String key,String name) throws LoginException,VaccineException;
	public Vaccine getVaccineById(String key,Integer id) throws LoginException,VaccineException;
	public Vaccine updateVaccine(String key,Integer id,Vaccine v) throws LoginException,VaccineException;
	public Boolean deleteVaccine(String key,Integer id) throws LoginException, VaccineException; 
	
}
