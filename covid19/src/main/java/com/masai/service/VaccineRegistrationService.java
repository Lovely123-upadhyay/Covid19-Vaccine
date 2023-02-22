package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.beans.VaccineRegistration;

@Service
public interface VaccineRegistrationService {
	
	public VaccineRegistration addVaccineRegistration ( VaccineRegistration vcr );

	public VaccineRegistration getVaccineRegistration( Long mobileno );
	
	public VaccineRegistration updateVaccineRegistration( VaccineRegistration vcr );
	
	public VaccineRegistration deleteVaccineRegistration( VaccineRegistration vcr );
	
}
