package com.masai.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Vaccine;
import com.masai.beans.VaccineInventory;
import com.masai.exception.LoginException;
import com.masai.exception.VaccinationCenterException;
import com.masai.exception.VaccineException;
import com.masai.exception.VaccineInventoryException;
import com.masai.service.VaccineInventoryService;
import com.masai.service.VaccineServices;

@RestController("/inventory")
public class VaccineInventoryController {
	
	@Autowired
	private VaccineInventoryService InventoryService;
	
	@Autowired
	private VaccineServices vaccineService;
	
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
	
	@PostMapping("/addVaccineCount/{id}/{name}/{qty}")
	public ResponseEntity<VaccineInventory> addVacccineCount(@RequestParam("key") String key,@RequestParam Integer id,@RequestParam String name,@RequestParam Integer qty) throws VaccineInventoryException, VaccineException, LoginException{
		Vaccine v=vaccineService.getVaccineByName(key, name);
		VaccineInventory inventory=InventoryService.addVaccineCount(key, id, v, qty);
		return new ResponseEntity<>(inventory,HttpStatus.CREATED);
	}
	
	
}
