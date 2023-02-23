package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.VaccinationCenter;
import com.masai.service.VaccinationCenterService;

@RestController
public class VaccinationCenterController {
	
	@Autowired
	private VaccinationCenterService centerService;
	
//	private VaccineInventoryService inventoryService;
	
	@PostMapping("/addNewCenter")
	public ResponseEntity<VaccinationCenter> AddVaccinationCenter(@RequestBody VaccinationCenter center){
		VaccinationCenter newCenter=centerService.addNewVaccinationCenter(center);
		return new ResponseEntity<>(newCenter,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getAllCenters")
	public ResponseEntity<List<VaccinationCenter>> getAllVaccinationCenters(){
		List<VaccinationCenter> list=centerService.AllVaccivationCenters();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	
}
