package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Member;
import com.masai.beans.VaccineRegistration;
import com.masai.dto.CurrentUserSession;
import com.masai.exception.LoginException;
import com.masai.exception.MemberException;
import com.masai.exception.VaccineRegistrationException;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.MemberRepo;
import com.masai.repository.VaccineRegistrationRepository;

@Service
public class VaccineRegistrationServiceImpl implements VaccineRegistrationService {
	
	@Autowired
	private VaccineRegistrationRepository VccRegRepo;
	
	@Autowired
	private CurrentUserSessionRepo currSessRepo;
	
	@Autowired
	private MemberRepo memberRepo;

	@Override
	public VaccineRegistration addVaccineRegistration( String key, String aadharNo , VaccineRegistration vcr) throws LoginException, VaccineRegistrationException, MemberException  {
		
		//first we will check for logged in or not
		CurrentUserSession cus = currSessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
			VaccineRegistration toRegister = VccRegRepo.findByMobileno(vcr.getMobileno());
			
			//first we will check if it is already registered or not:
			if( toRegister == null ) {
				
				//if not registered then we will save:
				Member member = memberRepo.findByAdharcardNo(aadharNo);
				
				if( member != null ) {
					
					//Associated both:
					member.setVaccineRegistration(vcr);
					vcr.getMembers().add(member);
					
					return VccRegRepo.save(vcr);
					
				}else {
					
					throw new MemberException( "No Member found !" );
				}
			}else {
				
				//else throw exception:
				throw new VaccineRegistrationException("Vaccine Registration is already done with this mobile number !");
			}
			
		}else {
			
			throw new LoginException("Please login first");
		}
	}

	@Override
	public VaccineRegistration getVaccineRegistrationByMobNo( String key, String mobileno) throws LoginException, VaccineRegistrationException {
	
		//first we will check for logged in or not
		CurrentUserSession cus = currSessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
			VaccineRegistration Registered = VccRegRepo.findByMobileno(mobileno);
			
			//first we will check if it is registered or not:
			if( Registered != null ) {
				
				//if registered then we will return it:
				return Registered;
			}else {
				
				//else throw exception:
				throw new VaccineRegistrationException(" No Vaccine Registration found with this mobile number !");
			}
			
		}else {
			
			throw new LoginException("Please login first");
		}
		
	}
	
	@Override
	public List<VaccineRegistration> getAllVaccineRegistrations( String key ) throws  LoginException, VaccineRegistrationException {
		
		//first we will check for logged in or not
		CurrentUserSession cus = currSessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
			List<VaccineRegistration> vaccineRegistrations = VccRegRepo.findAll();
			
			//first we will check if list is empty or not:
			if( ! vaccineRegistrations.isEmpty() ) {
				
				//if not then we will return it:
				return vaccineRegistrations;
			}else {
				
				//else throw exception:
				throw new VaccineRegistrationException(" No Vaccine Registration found !");
			}
			
		}else {
			
			throw new LoginException("Please login first");
		}
	}

	@Override
	public List<Member> getAllMembersByMobNo( String key, String mobileno) throws LoginException, VaccineRegistrationException {
		
		//first we will check for logged in or not
		CurrentUserSession cus = currSessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
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
			
		}else {
			
			throw new LoginException("Please login first");
		}
	}

	@Override
	public VaccineRegistration updateVaccineRegistration( String key, VaccineRegistration vcr) throws LoginException, VaccineRegistrationException {
		
		//first we will check for logged in or not
		CurrentUserSession cus = currSessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
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
			
		}else {
			
			throw new LoginException("Please login first");
		}
		
		
	}

	@Override
	public VaccineRegistration deleteVaccineRegistration( String key, VaccineRegistration vcr) throws LoginException, VaccineRegistrationException {
		
		//first we will check for logged in or not
		CurrentUserSession cus = currSessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
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
			
		}else {
			
			throw new LoginException("Please login first");
		}
	}
}
