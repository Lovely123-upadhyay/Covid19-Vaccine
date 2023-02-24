package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Appointment;
import com.masai.beans.Member;
import com.masai.beans.VaccinationCenter;
import com.masai.beans.VaccineInventory;
import com.masai.beans.VaccineRegistration;
import com.masai.dto.CurrentUserSession;
import com.masai.exception.AppointmentException;
import com.masai.exception.LoginException;
import com.masai.exception.MemberException;
import com.masai.exception.VaccinationCenterException;
import com.masai.exception.VaccineRegistrationException;
import com.masai.repository.MemberRepo;
import com.masai.repository.AppointmentRepository;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.VaccinationCenterRepository;
import com.masai.repository.VaccineInventoryRepository;
import com.masai.repository.VaccineRegistrationRepository;
import com.wincovid.exception.VaccineInventoryException;

@Service
public class AppointmentServiceImpl implements AppointmentService { 
	
	@Autowired
	private CurrentUserSessionRepo currSessRepo;
	
	@Autowired
	private VaccineRegistrationRepository VccRegRepo; 
	
	@Autowired
	private VaccinationCenterRepository VccCenRepo;
	
	@Autowired
	private VaccineInventoryRepository VccInvRepo;
	
	@Autowired
	private AppointmentRepository AptRepo;
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Override
	public Appointment addAppointment( String key, Appointment appointment , String aadharNo, Integer centerCode) throws LoginException , AppointmentException, MemberException, VaccinationCenterException, VaccineRegistrationException, VaccineInventoryException {
		
		//first we will check for logged in or not
		CurrentUserSession cus = currSessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
			//if user is admin or member
			if( ! cus.getAdmin() ) {
				
				//if user is member then 
				//here we need to find member to add appointment: 
				Member member = memberRepo.findByAdharcardNo(aadharNo);
				
				//if member found then we will check for Vaccine Registration by mob no of appointment:
				if( member != null ) {
					
					VaccineRegistration vr = VccRegRepo.findByMobileno(appointment.getMobileNo());
					
					//if vaccine registration found then we will check for member is in members list of vaccine registration:
					if( vr.getMembers().contains(member) ) {
						
						//if member found in registration then we will check for Vaccination center:
						Optional<VaccinationCenter> opt2 = VccCenRepo.findById(centerCode);
						
						//center found then we will save and return appointment:
						if( opt2.isPresent() ) {
							
							VaccinationCenter vaccinationCenter = opt2.get();
							
							List<VaccineInventory> inventories = vaccinationCenter.getVaccineInventories();
							
							//if inventory is not empty
							if( ! inventories.isEmpty() ) {
								
								//first we will check if member has already taken dose1:
								if( member.isDose1status() ) {
									
									//if dose 1 true then we will assign dose 2 as true:
									member.setDose2status(appointment.getBookingStatus());
								}else {
									
									member.setDose1status(appointment.getBookingStatus());
									member.setDose2date(appointment.getDateOfBooking().plusMonths(3));
								}
								
								//saved member:
								memberRepo.save(member);
								
								//associated member with appointment:
								appointment.setMember(member);
								
								//saved and returned appointment:
								return AptRepo.save(appointment);
								
							}else {
								
								throw new VaccineInventoryException("Vaccine inventory is empty !");
							}
							
							
						}else {
							
							throw new VaccinationCenterException("No Vaccination Center found !");
						}
						
					}else {
						
						throw new VaccineRegistrationException("No Vaccine Registration found !");
					}
					
					
				}else {
					
					throw new MemberException(" No member found ! ");
				}
				
			}else {
				
				throw new LoginException("Please login as Member first");
			}
		}else {
			
			throw new LoginException(" Please login first ! ");
		}
		
		
		
	}

	@Override
	public Appointment getAppointment( String key,  String aadharNo) throws AppointmentException, LoginException {
		
		//first we will check for logged in or not
		CurrentUserSession cus = currSessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
			//get appointment logic:
			Member member = memberRepo.findByAdharcardNo(aadharNo);
			
			if( member != null ) {
				
				return member.getAppointment();
			}else {
				
				throw new AppointmentException( "No appointment found !" );
			}
			
		}else {
			
			throw new LoginException(" Please login first ! ");
		}
		
		
	}

	@Override
	public List<Appointment> getAllAppointments( String key ) throws AppointmentException , LoginException {
		
		//first we will check for logged in or not
		CurrentUserSession cus = currSessRepo.findByUuid(key);
		
		//if logged in:
		if( cus != null ) {
			
			//if user is admin or member:
			if( ! cus.getAdmin() ) {
				
				//get all appointments logic:
				List<Appointment> appointments = AptRepo.findAll();
				
				if( ! appointments.isEmpty() ) {
					
					return appointments;
				}else {
					
					throw new AppointmentException( "No appointment found !" );
				}
				
			}else {
				
				throw new LoginException(" Please login as admin first ! ");
			}
			
		}else {
			
			throw new LoginException(" Please login first ! ");
		}
		
		
		
	}

	@Override
	public Appointment updateAppointment( String key, String aadharNo , Appointment appointment) throws AppointmentException , LoginException {
		
		//first we will check for logged in or not
		CurrentUserSession cus = currSessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
			//if user is admin or member
			if( ! cus.getAdmin() ) {
				
				//update appointment logic
				Member member  = memberRepo.findByAdharcardNo(aadharNo);
				
				if( member.getAppointment() != null ) {
					
					Appointment SavedAppointment = member.getAppointment();
					
					SavedAppointment.setBookingStatus(appointment.getBookingStatus());
					SavedAppointment.setDateOfBooking(appointment.getDateOfBooking());
					SavedAppointment.setSlot(appointment.getSlot());
					SavedAppointment.setVaccinationCenter(appointment.getVaccinationCenter());
					
					return AptRepo.save(SavedAppointment);
					
				}else {
					
					throw new AppointmentException("No Appointment found !");
				}
				
			}else {
				
				throw new LoginException(" Please login as admin first ! ");
			}
			
		}else {
			
			throw new LoginException(" Please login first ! ");
		}
		
		
		
	}

	@Override
	public Boolean deleteAppointment( String key, String aadharNo) throws AppointmentException , LoginException {
		
		//first we will check for logged in or not
		CurrentUserSession cus = currSessRepo.findByUuid(key);
		
		//if logged in
		if( cus != null ) {
			
			//delete appointment logic
			Member member = memberRepo.findByAdharcardNo(aadharNo);
			
			if( member.getAppointment() != null ) {
				
				Appointment SavedAppointment = member.getAppointment();
				
				AptRepo.delete(SavedAppointment);
				
				return true;
				
			}else {
				
				throw new AppointmentException("No Appointment found !");
			}	
			
		}else {
			
			throw new LoginException(" Please login first ! ");
		}
	}
}
