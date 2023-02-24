package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.IdCard;
import com.masai.exception.AdharCardException;
import com.masai.exception.IdCardException;
import com.masai.exception.LoginException;
import com.masai.exception.MemberException;
import com.masai.exception.PanCardException;
import com.masai.exception.VaccineRegistrationException;
import com.masai.service.IdCardService;

@RestController
@RequestMapping("/userController")
public class IdCardController {
	
	@Autowired
	private IdCardService ser;
	
	
	
	@PostMapping("/userSignUp/{key}")
	public ResponseEntity<IdCard> registerIdCard(@Valid @RequestBody IdCard user,@PathVariable("key") String key) throws IdCardException, LoginException, MemberException, VaccineRegistrationException {

		return new ResponseEntity<IdCard>(ser.addIdCard(key, user), HttpStatus.OK);

	}
	
	@GetMapping("/getUserByAdhar")
	public ResponseEntity<IdCard> getIdCardByAdhar(@RequestParam String adharNo)
			throws IdCardException, AdharCardException {

		return new ResponseEntity<IdCard>(ser.findIdCardByAdharNo(adharNo), HttpStatus.OK);

	}
	
	@GetMapping("/getUserByPanNo")
	public ResponseEntity<IdCard> getIdCardByPan(@RequestParam String panNo) throws PanCardException, IdCardException {

		return new ResponseEntity<IdCard>(ser.findIdCardBypanNo(panNo), HttpStatus.OK);

	}
}
