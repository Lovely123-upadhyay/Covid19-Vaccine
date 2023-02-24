package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.beans.Member;
import com.masai.beans.VaccineRegistration;
import com.masai.exception.LoginException;
import com.masai.exception.MemberException;
import com.masai.exception.VaccineRegistrationException;

@Service
public interface VaccineRegistrationService {
	
	public VaccineRegistration addVaccineRegistration ( String key, String aadharNo ,  VaccineRegistration vcr ) throws LoginException, VaccineRegistrationException , MemberException;

	public VaccineRegistration getVaccineRegistrationByMobNo ( String key, String mobileno ) throws LoginException,VaccineRegistrationException;
	
	public List<VaccineRegistration> getAllVaccineRegistrations(String key) throws LoginException,VaccineRegistrationException;
	
	public List<Member> getAllMembersByMobNo ( String key, String mobileno ) throws LoginException,VaccineRegistrationException ;
	
	public VaccineRegistration updateVaccineRegistration( String key, VaccineRegistration vcr ) throws LoginException,VaccineRegistrationException ;
	
	public VaccineRegistration deleteVaccineRegistration( String key, VaccineRegistration vcr ) throws LoginException,VaccineRegistrationException;
}
