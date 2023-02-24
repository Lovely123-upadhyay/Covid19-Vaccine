package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Member;
import com.masai.dto.MemberUpdateDto;
import com.masai.exception.AdharCardException;
import com.masai.exception.IdCardException;
import com.masai.exception.LoginException;
import com.masai.exception.MemberException;
import com.masai.exception.PanCardException;
import com.masai.exception.VaccineRegistrationException;
import com.masai.service.MemberServices;

@RestController
@RequestMapping("/memberController")
public class MemberController {
	
	@Autowired
	private MemberServices service;
	
	@GetMapping("/member/{key}/{idcard}")
	public ResponseEntity<Member> getMemberByld(@PathVariable("key") String key,@PathVariable("idcard") int idcardid) throws LoginException,MemberException,IdCardException {

		return new ResponseEntity<Member>(service.getMemberByld(key, idcardid), HttpStatus.OK);

	}
	
	@PostMapping("/addmember/{key}")
	public ResponseEntity<Member> addMember(@Valid @RequestBody Member member,@PathVariable("key") String key) throws LoginException, MemberException, VaccineRegistrationException{

		return new ResponseEntity<Member>(service.addMember(key, member), HttpStatus.OK);

	}
	
	@PutMapping("/updatemember/{key}/{idcard}")
	public ResponseEntity<Member> updateMember(@Valid @RequestBody  MemberUpdateDto MemberUpdateDto,@PathVariable("key") String key,@PathVariable("idcard") int idcardid) throws LoginException, IdCardException, MemberException, VaccineRegistrationException
			{

		return new ResponseEntity<Member>(service.updateMember(key,idcardid,MemberUpdateDto), HttpStatus.OK);

	}
    
	@DeleteMapping("/deletemember/{key}/{idcard}")
	public ResponseEntity<Member> deleteMember(@PathVariable("key") String key,@PathVariable("idcard") int idcardid) throws LoginException, MemberException, IdCardException, VaccineRegistrationException{

		return new ResponseEntity<Member>( service.deleteMember(key, idcardid), HttpStatus.OK);

	}
	
	@GetMapping("/getUserByAdhar")
	public ResponseEntity<Member> getMemberByAdhar(@RequestParam String adharNo) throws MemberException, AdharCardException{

		return new ResponseEntity<Member>(service.getMemberByAdharNo(adharNo), HttpStatus.OK);

	}
	
	@GetMapping("/getUserByPan")
	public ResponseEntity<Member> getUsearByPan(@RequestParam String panNo) throws MemberException, PanCardException{
		
		return new ResponseEntity<Member>( service.getMemberByPanNo(panNo), HttpStatus.OK);

	}
}
