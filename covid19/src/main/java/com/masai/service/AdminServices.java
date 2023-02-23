package com.masai.service;

import com.masai.beans.Admin;
import com.masai.dto.AdminDto;
import com.masai.dto.CurrentUserSession;
import com.masai.exception.AdminException;
import com.masai.exception.LoginException;

public interface AdminServices {
	
	public Admin saveAdmin(Admin admin) throws AdminException;

	public Admin updateAdmin(Admin admin,String key) throws AdminException, LoginException;

	public String deleteAdmin(String key) throws AdminException, LoginException;
	
	public CurrentUserSession loginAdmin(AdminDto admin) throws LoginException;
	
	public String logoutAdmin(String key) throws LoginException;
}
