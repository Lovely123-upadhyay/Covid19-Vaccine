package com.masai.service;

import com.masai.beans.Admin;
import com.masai.dto.AdminDto;
import com.masai.dto.CurrentUserSession;
import com.masai.exception.AdminException;
import com.masai.exception.LoginException;

public interface UserService {
	

	public Admin saveUser(Admin admin) throws AdminException;

	public Admin updateUser(Admin admin,String key) throws AdminException, LoginException;

	public String deleteUser(String key) throws AdminException, LoginException;
	
	public CurrentUserSession loginUser(AdminDto admin) throws LoginException;
	
	public String logoutUser(String key) throws LoginException;

}
