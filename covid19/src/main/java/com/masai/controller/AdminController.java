package com.masai.controller;

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

import com.masai.beans.Admin;
import com.masai.dto.AdminDto;
import com.masai.dto.CurrentUserSession;
import com.masai.exception.AdminException;
import com.masai.exception.LoginException;
import com.masai.service.AdminServices;

import java.util.List;


@RestController
public class AdminController {
	
	@Autowired
	private AdminServices ser;
	
	
	@PostMapping("/adminSignUp")
	public ResponseEntity<Admin> registerAdmin(@RequestBody Admin user) throws AdminException {

		return new ResponseEntity<Admin>(ser.saveAdmin(user), HttpStatus.OK);

	}
    
	@PutMapping("/admins")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin user, @RequestParam("key") String key)
			throws AdminException, LoginException {

		return new ResponseEntity<Admin>(ser.updateAdmin(user, key), HttpStatus.OK);

	}
    
	@DeleteMapping("/admins/{key}")
	public ResponseEntity<String> deleteAdmin(@PathVariable("key") String key)throws AdminException, LoginException {

		return new ResponseEntity<String>(ser.deleteAdmin(key), HttpStatus.OK);

	}
    
	@PostMapping("/adminLogin")
	public ResponseEntity<CurrentUserSession> loginAdmin(@RequestBody AdminDto admin) throws LoginException {

		return new ResponseEntity<CurrentUserSession>(ser.loginAdmin(admin), HttpStatus.OK);

	}
    
	@PostMapping("/adminLogout/{key}")
	public ResponseEntity<String> logoutAdmin(@PathVariable("key") String key) throws LoginException {

		return new ResponseEntity<String>(ser.logoutAdmin(key), HttpStatus.OK);

	}
}
