package com.masai.controller;

import java.time.LocalDateTime;
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
import com.masai.service.VaccineInventoryService;
import com.masai.service.VaccineServices;

@RestController("/inventory")
public class VaccineInventoryController {
	
	@Autowired
	private VaccineInventoryService InventoryService;
	
	@Autowired
	private VaccineServices vaccineService;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<VaccineInventory>> getAllInventories(){
		List<VaccineInventory> list=InventoryService.getAllVaccineInventories();
		return new ResponseEntity<>(list,HttpStatus.FOUND);
	}
	
	@GetMapping("/getById/{centerID}")
	public ResponseEntity<VaccineInventory> getVacineInventoryByCenter(@RequestParam Integer centerID){
		VaccineInventory inventory=InventoryService.getInventoryByVaccinationCenter(centerID);
		return new ResponseEntity<>(inventory,HttpStatus.FOUND);
	}
	
	@GetMapping("/getByDate/{date}")
	public ResponseEntity<List<VaccineInventory>> getInventoriesByDate(@RequestParam LocalDateTime date){
		List<VaccineInventory> list=InventoryService.getInventoryByDate(date);
		return new ResponseEntity<>(list,HttpStatus.FOUND);
	}
	
	@PostMapping("/addVaccineCount")
	public ResponseEntity<VaccineInventory> addVacccineCount(@RequestParam(value="InId")Integer id,@RequestParam(value="VName")String name,@RequestParam(value="Qty")Integer qty){
		Vaccine v=vaccineService.getVaccineByName(name);
		VaccineInventory inventory=InventoryService.addVaccineCount(id, v, qty);
		return new ResponseEntity<>(inventory,HttpStatus.CREATED);
	}
	
	
}
