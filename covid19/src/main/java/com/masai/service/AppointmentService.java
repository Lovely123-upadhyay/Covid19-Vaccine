package com.masai.service;

import java.util.List;

import com.masai.beans.Appointment;
import com.masai.exception.AppointmentException;
import com.masai.exception.LoginException;
import com.masai.exception.MemberException;
import com.masai.exception.VaccinationCenterException;
import com.masai.exception.VaccineRegistrationException;
import com.wincovid.exception.VaccineInventoryException;

public interface AppointmentService {

	public Appointment addAppointment( String key, Appointment appointment, String aadharNo, Integer centerCode ) throws LoginException, AppointmentException, MemberException, VaccinationCenterException, VaccineRegistrationException ,VaccineInventoryException;
	
	public Appointment getAppointment( String key, String aadharNo ) throws AppointmentException , LoginException;
	
	public List<Appointment> getAllAppointments( String key ) throws AppointmentException , LoginException;
	
	public Appointment updateAppointment( String key, String aadharNo , Appointment appointment ) throws AppointmentException , LoginException;
	
	public Boolean deleteAppointment( String key, String aadharNo ) throws AppointmentException , LoginException;
}
