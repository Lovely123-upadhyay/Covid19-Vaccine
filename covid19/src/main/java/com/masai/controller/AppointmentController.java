package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Appointment;
import com.masai.exception.AppointmentException;
import com.masai.exception.LoginException;
import com.masai.exception.MemberException;
import com.masai.exception.VaccinationCenterException;
import com.masai.exception.VaccineInventoryException;
import com.masai.exception.VaccineRegistrationException;
import com.masai.service.AppointmentService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@PostMapping("/bookAppointment")
	public ResponseEntity<Appointment> addNewAppoinmentHandler( @RequestParam("key") String key, @RequestParam("aadharNo") String aadharNo, @RequestParam("centerCode") Integer centerCode , @RequestBody Appointment appointment) throws LoginException, AppointmentException, MemberException, VaccinationCenterException, VaccineRegistrationException, com.masai.exception.VaccineInventoryException{
		
		Appointment savedAppointment = appointmentService.addAppointment(key, appointment, aadharNo, centerCode);
		
		return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAppointment")
	public ResponseEntity<Appointment> getAppoinmentHandler( @RequestParam("key") String key, @RequestParam("aadharNo") String aadharNo ) throws AppointmentException, LoginException{
		
		Appointment savedAppointment = appointmentService.getAppointment(key, aadharNo);
		
		return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAppointments")
	public ResponseEntity<List<Appointment>> getAllAppoinmentsHandler( @RequestParam("key") String key ) throws AppointmentException, LoginException{
		
		List<Appointment> savedAppointments = appointmentService.getAllAppointments( key );
		
		return new ResponseEntity<>(savedAppointments, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateAppointment")
	public ResponseEntity<Appointment> updateAppoinmentHandler( @RequestParam("key") String key, @RequestParam("aadharNo") String aadharNo, @RequestBody Appointment appointment  ) throws AppointmentException, LoginException{
		
		Appointment savedAppointment = appointmentService.updateAppointment(key, aadharNo, appointment);
		
		return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteAppointment")
	public ResponseEntity<Boolean> deleteAppoinmentHandler( @RequestParam("key") String key, @RequestParam("aadharNo") String aadharNo ) throws AppointmentException, LoginException{
		
		Boolean status = appointmentService.deleteAppointment(key, aadharNo);
		
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
}
