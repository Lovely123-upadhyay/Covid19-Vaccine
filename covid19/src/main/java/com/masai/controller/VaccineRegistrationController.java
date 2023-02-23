package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Member;
import com.masai.beans.VaccineRegistration;
import com.masai.exception.VaccineRegistrationException;
import com.masai.service.VaccineRegistrationService;

@RestController
@RequestMapping("/vaccineRegistration")
public class VaccineRegistrationController {

	@Autowired
	private VaccineRegistrationService VccRegService;
	
	@PostMapping("/registration")
	public ResponseEntity<VaccineRegistration> addVaccineRegistrationHandler( VaccineRegistration vr ) throws VaccineRegistrationException{
		
		VaccineRegistration savedVaccineRegistration = VccRegService.addVaccineRegistration(vr);
		
		return new ResponseEntity<>(savedVaccineRegistration, HttpStatus.CREATED);
	}
	
	@GetMapping("/registration")
	public ResponseEntity<VaccineRegistration> getVaccineRegistrationByMobNoHandler( @RequestParam("mobNo") String mobNo ) throws VaccineRegistrationException{
		
		VaccineRegistration savedVaccineRegistration = VccRegService.getVaccineRegistrationByMobNo(mobNo);
		
		return new ResponseEntity<>(savedVaccineRegistration, HttpStatus.OK);
	}
	
	@GetMapping("/registrations")
	public ResponseEntity<List<VaccineRegistration>> getAllVaccineRegistrationsHandler( ) throws VaccineRegistrationException{
		
		List<VaccineRegistration> savedVaccineRegistrations = VccRegService.getAllVaccineRegistrations();
		
		return new ResponseEntity<>(savedVaccineRegistrations, HttpStatus.OK);
	}
	
	@GetMapping("/registration/members")
	public ResponseEntity<List<Member>> getMembersByMobNoHandler( @RequestParam("mobNo") String mobNo ) throws VaccineRegistrationException{
		
		VaccineRegistration savedVaccineRegistration = VccRegService.getVaccineRegistrationByMobNo(mobNo);
		
		return new ResponseEntity<>(savedVaccineRegistration.getMembers(), HttpStatus.OK);
	}
	
	@PutMapping("/registration")
	public ResponseEntity<VaccineRegistration> updateVaccineRegistrationHandler( VaccineRegistration vr ) throws VaccineRegistrationException{
		
		VaccineRegistration updatedVaccineRegistration = VccRegService.updateVaccineRegistration(vr);
		
		return new ResponseEntity<>(updatedVaccineRegistration, HttpStatus.OK);
	}
	
}
