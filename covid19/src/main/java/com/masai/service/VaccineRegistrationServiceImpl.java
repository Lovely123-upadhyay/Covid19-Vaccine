package com.masai.service;

import java.util.Optional;

import com.masai.beans.VaccineRegistration;
import com.masai.repository.VaccineRegistrationRepository;

public class VaccineRegistrationServiceImpl implements VaccineRegistrationService {
	
	private VaccineRegistrationRepository VccRegRepo;

	@Override
	public VaccineRegistration addVaccineRegistration(VaccineRegistration vcr) {
		
		return VccRegRepo.save(vcr);
		
	}

	@Override
	public VaccineRegistration getVaccineRegistration(Long mobileno) {
		
		return VccRegRepo.findByMobileno(mobileno);
		
	}

	@Override
	public VaccineRegistration updateVaccineRegistration(VaccineRegistration vcr) {
		
		Optional<VaccineRegistration> opt = VccRegRepo.findById(vcr.getVrid());
		
		if ( opt.isPresent() ) {
			
			VaccineRegistration vcrRec = opt.get();
			
			vcrRec.setDateofregistration(vcr.getDateofregistration());
			vcrRec.setMobileno(vcr.getMobileno());
			
			return VccRegRepo.save(vcrRec);
		}else {
			
			throw new IllegalArgumentException("No record found...");
		}
	}

	@Override
	public VaccineRegistration deleteVaccineRegistration(VaccineRegistration vcr) {
		
		Optional<VaccineRegistration> opt = VccRegRepo.findById(vcr.getVrid());
		
		if ( opt.isPresent() ) {
			
			VaccineRegistration vcrRec = opt.get();
			
			VccRegRepo.delete(vcrRec);
			
			return VccRegRepo.save(vcrRec);
		}else {
			
			throw new IllegalArgumentException("No record found...");
		}
		
	}

}
