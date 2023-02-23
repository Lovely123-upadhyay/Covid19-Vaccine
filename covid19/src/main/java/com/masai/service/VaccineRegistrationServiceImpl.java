package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Member;
import com.masai.beans.VaccineRegistration;
import com.masai.exception.VaccineRegistrationException;
import com.masai.repository.VaccineRegistrationRepository;

@Service
public class VaccineRegistrationServiceImpl implements VaccineRegistrationService {
	
	@Autowired
	private VaccineRegistrationRepository VccRegRepo;

	@Override
	public VaccineRegistration addVaccineRegistration(VaccineRegistration vcr) throws VaccineRegistrationException {
		
		VaccineRegistration toRegister = VccRegRepo.findByMobileno(vcr.getMobileno());
		
		//first we will check if it is already registered or not:
		if( toRegister == null ) {
			
			//if not registered then we will save:
			return VccRegRepo.save(vcr);
		}else {
			
			//else throw exception:
			throw new VaccineRegistrationException("Vaccine Registration is already done with this mobile number !");
		}
		
	}

	@Override
	public VaccineRegistration getVaccineRegistrationByMobNo(String mobileno) throws VaccineRegistrationException {
	
		VaccineRegistration Registered = VccRegRepo.findByMobileno(mobileno);
		
		//first we will check if it is registered or not:
		if( Registered != null ) {
			
			//if registered then we will return it:
			return Registered;
		}else {
			
			//else throw exception:
			throw new VaccineRegistrationException(" No Vaccine Registration found with this mobile number !");
		}
	}

	@Override
	public List<Member> getAllMembersByMobNo(String mobileno) throws VaccineRegistrationException {
		
		VaccineRegistration Registered = VccRegRepo.findByMobileno(mobileno);
		
		//first we will check if it is registered or not:
		if( Registered != null ) {
			
			//if registered then we check if there are any members added or not:
			if( ! Registered.getMembers().isEmpty() ) {
				
				//if members present then we will return list:
				return Registered.getMembers();
			}else {
				
				//else throw exception:
				throw new VaccineRegistrationException(" No Member found with this registerd mobile number !");
			}
			
		}else {
			
			//else throw exception:
			throw new VaccineRegistrationException(" No Vaccine Registration found with this mobile number !");
		}
	}

	@Override
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration vcr) throws VaccineRegistrationException {
		
		Optional<VaccineRegistration> opt = VccRegRepo.findById(vcr.getRegId());
		
		//first we will check if it is registered or not:
		if(opt.isPresent()) {
			
			//if registered then we will update it and save it and return it:
			VaccineRegistration registerd = opt.get();
			
			//updating:
			registerd.setMobileno(vcr.getMobileno());
			
			//saving and returning:
			return VccRegRepo.save(registerd);
			
		}else {
			
			//else throw exception:
			throw new VaccineRegistrationException(" No Vaccine Registration found with this Id !");
		}
		
	}

	@Override
	public VaccineRegistration deleteVaccineRegistration(VaccineRegistration vcr) throws VaccineRegistrationException {
		
		Optional<VaccineRegistration> opt = VccRegRepo.findById(vcr.getRegId());
		
		//first we will check if it is registered or not:
		if(opt.isPresent()) {
			
			//if registered then we will delete it and return it:
			VaccineRegistration registerd = opt.get();
			
			//Deleting:
			VccRegRepo.delete(registerd);
			
			//returning:
			return registerd;
			
		}else {
			
			//else throw exception:
			throw new VaccineRegistrationException(" No Vaccine Registration found with this Id !");
		}
		
	}
}
