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
import com.masai.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/userLoginController")
public class UserController {
	
	@Autowired
	private UserService ser;
	
	
	@PostMapping("/userSignUp")
	public ResponseEntity<Admin> registerUser(@RequestBody Admin user) throws AdminException {

		return new ResponseEntity<Admin>(ser.saveUser(user), HttpStatus.OK);

	}
    
	@PutMapping("/admins")
	public ResponseEntity<Admin> updateUser(@RequestBody Admin user, @RequestParam("key") String key)
			throws AdminException, LoginException {

		return new ResponseEntity<Admin>(ser.updateUser(user, key), HttpStatus.OK);

	}
    
	@DeleteMapping("/users/{key}")
	public ResponseEntity<String> deleteUser(@PathVariable("key") String key)throws AdminException, LoginException {

		return new ResponseEntity<String>(ser.deleteUser(key), HttpStatus.OK);

	}
    
	@PostMapping("/userLogin")
	public ResponseEntity<CurrentUserSession> loginUser(@RequestBody AdminDto admin) throws LoginException {

		return new ResponseEntity<CurrentUserSession>(ser.loginUser(admin), HttpStatus.OK);

	}
    
	@PostMapping("/userLogout/{key}")
	public ResponseEntity<String> logoutUser(@PathVariable("key") String key) throws LoginException {

		return new ResponseEntity<String>(ser.logoutUser(key), HttpStatus.OK);

	}
}

