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
import com.masai.exception.LoginException;
import com.masai.exception.MemberException;
import com.masai.exception.VaccineRegistrationException;
import com.masai.service.VaccineRegistrationService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/vaccineRegistration")
public class VaccineRegistrationController {

	@Autowired
	private VaccineRegistrationService VccRegService;
	
	@PostMapping("/registration")
	public ResponseEntity<VaccineRegistration> addVaccineRegistrationHandler( @RequestParam("key") String key , String aadharNo , @RequestBody VaccineRegistration vr ) throws VaccineRegistrationException, LoginException, MemberException{
		
		VaccineRegistration savedVaccineRegistration = VccRegService.addVaccineRegistration( key, aadharNo , vr);
		
		return new ResponseEntity<>(savedVaccineRegistration, HttpStatus.CREATED);
	}
	
	@GetMapping("/registration")
	public ResponseEntity<VaccineRegistration> getVaccineRegistrationByMobNoHandler( @RequestParam("key") String key , @RequestParam("mobNo") String mobNo ) throws VaccineRegistrationException, LoginException{
		
		VaccineRegistration savedVaccineRegistration = VccRegService.getVaccineRegistrationByMobNo( key, mobNo);
		
		return new ResponseEntity<>(savedVaccineRegistration, HttpStatus.OK);
	}
	
	@GetMapping("/registrations")
	public ResponseEntity<List<VaccineRegistration>> getAllVaccineRegistrationsHandler( @RequestParam("key") String key ) throws VaccineRegistrationException, LoginException{
		
		List<VaccineRegistration> savedVaccineRegistrations = VccRegService.getAllVaccineRegistrations( key );
		
		return new ResponseEntity<>(savedVaccineRegistrations, HttpStatus.OK);
	}
	
	@GetMapping("/registration/members")
	public ResponseEntity<List<Member>> getMembersByMobNoHandler( @RequestParam("key") String key , @RequestParam("mobNo") String mobNo ) throws VaccineRegistrationException, LoginException{
		
		VaccineRegistration savedVaccineRegistration = VccRegService.getVaccineRegistrationByMobNo( key , mobNo);
		
		return new ResponseEntity<>(savedVaccineRegistration.getMembers(), HttpStatus.OK);
	}
	
	@PutMapping("/registration")
	public ResponseEntity<VaccineRegistration> updateVaccineRegistrationHandler( @RequestParam("key") String key ,  VaccineRegistration vr ) throws VaccineRegistrationException, LoginException{
		
		VaccineRegistration updatedVaccineRegistration = VccRegService.updateVaccineRegistration(key , vr);
		
		return new ResponseEntity<>(updatedVaccineRegistration, HttpStatus.OK);
	}
	
}
