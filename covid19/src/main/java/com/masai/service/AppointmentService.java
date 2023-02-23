package com.masai.service;

import java.util.List;

import com.masai.beans.Appointment;
import com.masai.exception.AppointmentException;

public interface AppointmentService {

	public Appointment addAppointment( Appointment appointment ) throws AppointmentException;
	
	public Appointment getAppointment( Integer bookingId ) throws AppointmentException;
	
	public Appointment getAppointmentByMobNo( String mobNo ) throws AppointmentException;
	
	public List<Appointment> getAllAppointments() throws AppointmentException;
	
	public Appointment updateAppointment( Appointment appointment ) throws AppointmentException;
	
	public Appointment deleteAppointment( Appointment appointment ) throws AppointmentException;
}
