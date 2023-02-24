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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Vaccine;
import com.masai.exception.VaccineException;
import com.masai.service.VaccineServices;

@RestController
public class VaccineController {
	@Autowired
	private VaccineServices vaccineService;
	
	@PostMapping("/newVaccine")
	public ResponseEntity<Vaccine> addVaccine(@RequestBody Vaccine v){
		Vaccine vaccine=vaccineService.addVaccine(v); 
		return new ResponseEntity<>(vaccine,HttpStatus.CREATED);
	}
	
	@GetMapping("/allVaccines")
	public ResponseEntity<List<Vaccine>> getVaccines(){
		List<Vaccine> list=vaccineService.allVaccines();
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getVaccine/{id}")
	public ResponseEntity<Vaccine> getVaccineById(@RequestParam Integer id) throws VaccineException{
		Vaccine vaccine=vaccineService.getVaccineById(id); 
		return new ResponseEntity<>(vaccine,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateVaccine/{id}")
	public ResponseEntity<Vaccine> updateVaccineDetails(@RequestParam Integer id,@RequestBody Vaccine v) throws VaccineException{
		Vaccine vaccine=vaccineService.updateVaccine(id, v);
		return new ResponseEntity<>(vaccine,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteVaccine/{id}")
	public ResponseEntity<String> deleteVaccine(@RequestParam Integer id) throws VaccineException{
		Boolean ans=vaccineService.deleteVaccine(id);
		if(ans) {
			return new ResponseEntity<>("Deleted!",HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>("Vaccine Not Found!",HttpStatus.NOT_FOUND);
		}
		
	}
}
