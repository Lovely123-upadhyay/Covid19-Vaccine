package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Vaccine;
import com.masai.exception.VaccineException;
import com.masai.repository.VaccineRepository;

@Service
public class VaccineServicesImpl implements VaccineServices{
	
	@Autowired
	private VaccineRepository vaccineRepo;

	@Override
	public Vaccine addVaccine(Vaccine v) {
		return vaccineRepo.save(v);
	}

	@Override
	public List<Vaccine> allVaccines() {
		return vaccineRepo.findAll();
	}

	@Override
	public Vaccine getVaccineByName(String name) throws VaccineException {
		Vaccine v= vaccineRepo.findByName(name);
		if(v==null) {
			throw new VaccineException("Sorry: Vaccine Not Found");
		}else {
			return v;
		}
	}

	@Override
	public Vaccine getVaccineById(Integer id) throws VaccineException {
		return vaccineRepo.findById(id).orElseThrow(()->new VaccineException("Sorry: Vaccine Not Found"));
	}

	@Override
	public Vaccine updateVaccine(Integer id,Vaccine v) throws VaccineException {
		Vaccine vaccine=vaccineRepo.findById(id).orElseThrow(()->new VaccineException("Sorry: Vaccine Not Found!"));
		vaccine.setVaccineName(v.getVaccineName());
		vaccine.setDescription(v.getDescription());
		return vaccineRepo.save(vaccine);
	}

	@Override
	public Boolean deleteVaccine(Integer id) throws VaccineException {
		Vaccine vaccine=vaccineRepo.findById(id).orElseThrow(()->new VaccineException("Sorry: Vaccine Not Found!"));
		if(vaccine!=null) {
			vaccineRepo.delete(vaccine);
			return true;
		}else {
			return false;
		}
	}
	
	
}
