package com.masai.service;

import java.util.List;

import com.masai.beans.Appointment;
import com.masai.exception.AppointmentException;
import com.masai.exception.MemberException;
import com.masai.exception.VaccinationCenterException;
import com.masai.exception.VaccineRegistrationException;

public interface AppointmentService {

	public Appointment addAppointment( Appointment appointment, Integer mid, Integer centerCode ) throws AppointmentException, MemberException, VaccinationCenterException, VaccineRegistrationException ;
	
	public Appointment getAppointment( Integer bookingId ) throws AppointmentException;
	
	public List<Appointment> getAllAppointments() throws AppointmentException;
	
	public Appointment updateAppointment( Appointment appointment ) throws AppointmentException;
	
	public Boolean deleteAppointment( Integer id ) throws AppointmentException;
}
