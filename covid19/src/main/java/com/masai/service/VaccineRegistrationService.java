package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.beans.Member;
import com.masai.beans.VaccineRegistration;
import com.masai.exception.VaccineRegistrationException;

@Service
public interface VaccineRegistrationService {
	
	public VaccineRegistration addVaccineRegistration ( VaccineRegistration vcr ) throws VaccineRegistrationException;

	public VaccineRegistration getVaccineRegistrationByMobNo ( String mobileno ) throws VaccineRegistrationException;
	
	public List<Member> getAllMembersByMobNo ( String mobileno ) throws VaccineRegistrationException ;
	
	public VaccineRegistration updateVaccineRegistration( VaccineRegistration vcr ) throws VaccineRegistrationException ;
	
	public VaccineRegistration deleteVaccineRegistration( VaccineRegistration vcr ) throws VaccineRegistrationException;
}
