package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.VaccinationCenter;
import com.masai.dto.CurrentUserSession;
import com.masai.exception.LoginException;
import com.masai.exception.VaccinationCenterException;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.VaccinationCenterRepository;

@Service
public class VaccinationCenterServiceImpl implements VaccinationCenterService{
	
	@Autowired
	private CurrentUserSessionRepo SessRepo;
	
	@Autowired
	private VaccinationCenterRepository vaccineCenterRepo;

	@Override
	public VaccinationCenter addNewVaccinationCenter(String key, VaccinationCenter center) throws LoginException, VaccinationCenterException {
		
		//first we will check for logged in or not
		CurrentUserSession cus = SessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
			//if user is admin or member
			if( cus.getAdmin() ) {
				
				return vaccineCenterRepo.save(center);
				
			}else {
				
				throw new LoginException("Please login as admin first !");
			}
		}else {
			
			throw new LoginException("Please login first !");
		}
	}

	@Override
	public VaccinationCenter getVaccinationCenterById(String key, Integer id) throws LoginException, VaccinationCenterException {
		
		//first we will check for logged in or not
		CurrentUserSession cus = SessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
			Optional<VaccinationCenter> opt = vaccineCenterRepo.findById(id);
			
			if( opt.isPresent() ) {
				
				return opt.get();
			}else {
				
				throw new VaccinationCenterException("No Vaccination Center found !");
			}
			
		}else {
			
			throw new LoginException("Please login first !");
		}
	}

	@Override
	public List<VaccinationCenter> getAllVaccivationCenters(String key) throws LoginException, VaccinationCenterException {

		//first we will check for logged in or not
		CurrentUserSession cus = SessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
			//if user is admin or member
			if( cus.getAdmin() ) {
				
				List<VaccinationCenter> centers = vaccineCenterRepo.findAll();
				
				if( ! centers.isEmpty() ) {
					
					return centers;
				}else {
					
					throw new VaccinationCenterException("No Vaccination Center found !");
				}
				
			}else {
				
				throw new LoginException("Please login as admin first !");
			}
		}else {
			
			throw new LoginException("Please login first !");
		}
	}

	@Override
	public VaccinationCenter updateVaccinationCenter(String key, VaccinationCenter center) throws LoginException, VaccinationCenterException {
		
		//first we will check for logged in or not
		CurrentUserSession cus = SessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
			//if user is admin or member
			if( cus.getAdmin() ) {
				
				Optional<VaccinationCenter> opt = vaccineCenterRepo.findById(center.getCode());
				
				if( opt.isPresent() ) {
					
					VaccinationCenter savedCenter = opt.get();
					
					savedCenter.setAddress(center.getAddress());
					savedCenter.setAppointments(center.getAppointments());
					savedCenter.setCenterName(center.getCenterName());
					savedCenter.setInventory(center.getInventory());
					
					return vaccineCenterRepo.save(savedCenter);
				}else {
					
					throw new VaccinationCenterException("No Vaccination Center found !");
				}
				
			}else {
				
				throw new LoginException("Please login as admin first !");
			}
		}else {
			
			throw new LoginException("Please login first !");
		}
	}

	@Override
	public Boolean deleteVaccinationCenter(String key, Integer id) throws LoginException, VaccinationCenterException {
		
		//first we will check for logged in or not
		CurrentUserSession cus = SessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
			//if user is admin or member
			if( cus.getAdmin() ) {
				
				Optional<VaccinationCenter> opt = vaccineCenterRepo.findById(id);
				
				if( opt.isPresent() ) {
					
					VaccinationCenter center = opt.get();
					
					vaccineCenterRepo.delete(center);
					
					return true;
				}else {
					
					throw new VaccinationCenterException("No Vaccination Center found !");
				}
				
			}else {
				
				throw new LoginException("Please login as admin first !");
			}
		}else {
			
			throw new LoginException("Please login first !");
		}
	}
}
