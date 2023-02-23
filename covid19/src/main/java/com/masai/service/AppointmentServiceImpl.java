package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.masai.beans.Appointment;
import com.masai.exception.AppointmentException;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Override
	public Appointment addAppointment(Appointment appointment) throws AppointmentException {
		
		return null;
	}

	@Override
	public Appointment getAppointment(Integer bookingId) throws AppointmentException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Appointment getAppointmentByMobNo(String mobNo) throws AppointmentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Appointment> getAllAppointments() throws AppointmentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appointment updateAppointment(Appointment appointment) throws AppointmentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appointment deleteAppointment(Appointment appointment) throws AppointmentException {
		// TODO Auto-generated method stub
		return null;
	}

}
