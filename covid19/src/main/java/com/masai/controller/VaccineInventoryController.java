package com.masai.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.VaccinationCenter;
import com.masai.beans.Vaccine;
import com.masai.beans.VaccineCount;
import com.masai.beans.VaccineInventory;
import com.masai.exception.LoginException;
import com.masai.exception.VaccinationCenterException;
import com.masai.exception.VaccineException;
import com.masai.exception.VaccineInventoryException;
import com.masai.service.VaccinationCenterService;
import com.masai.service.VaccineInventoryService;
import com.masai.service.VaccineServices;

@RestController("/inventory")
public class VaccineInventoryController {
	
	@Autowired
	private VaccineInventoryService InventoryService;
	
	@Autowired
	private VaccineServices vaccineService;
	
	@Autowired
	private VaccinationCenterService centerService;
	
	@PostMapping("AddInventory")
	public ResponseEntity<VaccineInventory> addNewInventory(@RequestParam("key") String key,@RequestBody VaccineInventory i) throws LoginException, VaccinationCenterException{
		VaccineInventory saved=InventoryService.addInventory(key, i);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<VaccineInventory>> getAllInventories(@RequestParam("key") String key) throws LoginException{
		List<VaccineInventory> list=InventoryService.getAllVaccineInventories(key);
		return new ResponseEntity<>(list,HttpStatus.FOUND);
	}
	
	@GetMapping("/getById/{centerID}")
	public ResponseEntity<VaccineInventory> getVacineInventoryByCenter(@RequestParam("key") String key,@RequestParam Integer centerID) throws VaccinationCenterException, LoginException{
		VaccineInventory inventory=InventoryService.getInventoryByVaccinationCenter(key, centerID);
		return new ResponseEntity<>(inventory,HttpStatus.FOUND);
	}
	
	@GetMapping("/getByDate/{date}")
	public ResponseEntity<List<VaccineInventory>> getInventoriesByDate(@RequestParam("key") String key,@RequestParam String date) throws LoginException{
		List<VaccineInventory> list=InventoryService.getInventoryByDate(key, LocalDate.parse(date));
		return new ResponseEntity<>(list,HttpStatus.FOUND);
	}
	
	@PostMapping("/addVaccineCount/{CenterId}/{vaccineName}/{quantity}")
	public ResponseEntity<Set<VaccineCount>> addVacccineCount(@RequestParam("key") String key,@RequestParam Integer CenterId,@RequestParam String vaccineName ,@RequestParam Integer quantity) throws VaccineInventoryException, VaccineException, LoginException, VaccinationCenterException{
		Vaccine v=vaccineService.getVaccineByName(key, vaccineName);
		VaccinationCenter c=centerService.getVaccinationCenterById(key, CenterId);
		Set<VaccineCount> count=InventoryService.addVaccineCount(key, c.getInventory().getInventoryId(), v, quantity);
		return new ResponseEntity<>(count,HttpStatus.CREATED);
	}
	
	
}
