package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.VaccinationCenter;
import com.masai.exception.LoginException;
import com.masai.exception.VaccinationCenterException;
import com.masai.service.VaccinationCenterService;

@RestController
@RequestMapping("/vaccineCenter")
public class VaccinationCenterController {
	
	@Autowired
	private VaccinationCenterService centerService;
	
	@PostMapping("/addCenter")
	public ResponseEntity<VaccinationCenter> addNewVaccinationCenterHandler( @RequestParam("key") String key, @RequestBody VaccinationCenter center ) throws LoginException, VaccinationCenterException{
		
		VaccinationCenter savedCenter = centerService.addNewVaccinationCenter(key, center);
		
		return new ResponseEntity<>(savedCenter, HttpStatus.CREATED);
	}
	
	@GetMapping("/getCenter")
	public ResponseEntity<VaccinationCenter> getVaccinationCenterHandler( @RequestParam("key") String key, @RequestParam("id") Integer id) throws LoginException, VaccinationCenterException{
		
		VaccinationCenter savedCenter = centerService.getVaccinationCenterById(key, id);
		
		return new ResponseEntity<>(savedCenter, HttpStatus.CREATED);
	}
	
	@GetMapping("/getCenters")
	public ResponseEntity<List<VaccinationCenter>> getAllVaccinationCenterHandler( @RequestParam("key") String key ) throws LoginException, VaccinationCenterException{
		
		List<VaccinationCenter> centers = centerService.getAllVaccivationCenters(key);
		
		return new ResponseEntity<>(centers, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateCenter")
	public ResponseEntity<VaccinationCenter> updateVaccinationCenterHandler( @RequestParam("key") String key, @RequestBody VaccinationCenter center ) throws LoginException, VaccinationCenterException{
		
		VaccinationCenter savedCenter = centerService.updateVaccinationCenter(key, center);
		
		return new ResponseEntity<>(savedCenter, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteCenter")
	public ResponseEntity<Boolean> deleteVaccinationCenterHandler( @RequestParam("key") String key, @RequestParam("id") Integer id) throws LoginException, VaccinationCenterException{
		
		Boolean status = centerService.deleteVaccinationCenter(key, id);
		
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
}
