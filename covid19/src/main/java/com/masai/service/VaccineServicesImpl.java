package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Vaccine;
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
	public Vaccine getVaccineByName(String name) {
		return null;
	}

	@Override
	public Vaccine getVaccineById(Integer id) {
		return vaccineRepo.findById(id).orElseThrow(()->new NullPointerException());
	}

	@Override
	public Vaccine updateVaccine(Integer id,Vaccine v) {
		Vaccine vaccine=vaccineRepo.findById(id).orElseThrow(()->new NullPointerException());
		vaccine.setVaccineName(v.getVaccineName());
		vaccine.setDescription(v.getDescription());
		return vaccineRepo.save(vaccine);
	}

	@Override
	public Boolean deleteVaccine(Integer id) {
		Vaccine vaccine=vaccineRepo.findById(id).orElseThrow(()->new NullPointerException());
		if(vaccine!=null) {
			vaccineRepo.delete(vaccine);
			return true;
		}else {
			return false;
		}
	}
	
	
}
