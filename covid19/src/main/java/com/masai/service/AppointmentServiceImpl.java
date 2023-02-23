package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Appointment;
import com.masai.beans.Member;
import com.masai.beans.VaccinationCenter;
import com.masai.beans.VaccineRegistration;
import com.masai.exception.AppointmentException;
import com.masai.exception.MemberException;
import com.masai.exception.VaccinationCenterException;
import com.masai.exception.VaccineRegistrationException;
import com.masai.repositery.MemberRepo;
import com.masai.repository.AppointmentRepository;
import com.masai.repository.VaccinationCenterRepository;
import com.masai.repository.VaccineRegistrationRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private MemberRepo memberRepo; 
	
	@Autowired
	private VaccineRegistrationRepository VccRegRepo; 
	
	@Autowired
	private VaccinationCenterRepository VccCenRepo;
	
	@Autowired
	private AppointmentRepository AptRepo;
	
	@Override
	public Appointment addAppointment(Appointment appointment , Integer mid, Integer centerCode) throws AppointmentException, MemberException, VaccinationCenterException, VaccineRegistrationException {
		
		//first we need to find member to add appointment: 
		Optional<Member> opt = memberRepo.findById(mid);
		
		//if member found then we will check for Vaccine Registration by mob no of appointment:
		if( opt.isPresent() ) {
			
			Member member = opt.get();
			
			VaccineRegistration vr = VccRegRepo.findByMobileno(appointment.getMobileNo());
			
			//if vaccine registration found then we will check for member in member list of vaccine registration:
			if( vr.getMembers().contains(member) ) {
				
				//if member found in registration then we will check for Vaccination center:
				Optional<VaccinationCenter> opt2 = VccCenRepo.findById(centerCode);
				
				//center found then we will save and return appointment:
				if( opt2.isPresent() ) {
					
					//first we will check if member has already taken dose1:
					if( member.isDose1status() ) {
						
						//if dose 1 true then we will assign dose 2 as true:
						member.setDose2status(appointment.getBookingStatus());
					}else {
						
						member.setDose1status(appointment.getBookingStatus());
						member.setDose1date(appointment.getDateOfBooking().plusMonths(3));
					}
					
					//saved member:
					memberRepo.save(member);
					
					//associated member with appointment:
					appointment.setMember(member);
					
					//saved and returned appointment:
					return AptRepo.save(appointment);
				}else {
					
					throw new VaccinationCenterException("No Vaccination Center found !");
				}
				
			}else {
				
				throw new VaccineRegistrationException("No Vaccine Registration found !");
			}
			
			
		}else {
			
			throw new MemberException(" No member found ! ");
		}
		
	}

	@Override
	public Appointment getAppointment(Integer bookingId) throws AppointmentException {
		
		Optional<Appointment> opt = AptRepo.findById(bookingId);
		
		if( opt.isPresent() ) {
			
			return opt.get();
		}else {
			
			throw new AppointmentException( "No appointment found !" );
		}
	}

	@Override
	public List<Appointment> getAllAppointments() throws AppointmentException {
		
		List<Appointment> appointments = AptRepo.findAll();
		
		if( ! appointments.isEmpty() ) {
			
			return appointments;
		}else {
			
			throw new AppointmentException( "No appointment found !" );
		}
		
	}

	@Override
	public Appointment updateAppointment(Appointment appointment) throws AppointmentException {
		
		Optional<Appointment> opt = AptRepo.findById(appointment.getBookingId());
		
		if(opt.isPresent()) {
			
			Appointment SavedAppointment = opt.get();
			
			SavedAppointment.setBookingStatus(appointment.getBookingStatus());
			SavedAppointment.setDateOfBooking(appointment.getDateOfBooking());
			SavedAppointment.setSlot(appointment.getSlot());
			SavedAppointment.setVaccinationCenter(appointment.getVaccinationCenter());
			
			return AptRepo.save(SavedAppointment);
			
		}else {
			
			throw new AppointmentException("No Appointment found !");
		}
		
	}

	@Override
	public Boolean deleteAppointment(Integer id) throws AppointmentException {
		
		Optional<Appointment> opt = AptRepo.findById(id);
		
		if(opt.isPresent()) {
			
			Appointment SavedAppointment = opt.get();
			
			AptRepo.delete(SavedAppointment);
			
			return true;
			
		}else {
			
			throw new AppointmentException("No Appointment found !");
		}	
		
	}

}
