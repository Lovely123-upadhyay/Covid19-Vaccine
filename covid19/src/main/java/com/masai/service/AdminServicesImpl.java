package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Admin;
import com.masai.dto.AdminDto;
import com.masai.dto.CurrentUserSession;
import com.masai.exception.AdminException;
import com.masai.exception.LoginException;
import com.masai.repositery.AdminRepo;
import com.masai.repositery.CurrentUserSessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminServicesImpl implements AdminServices{
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private CurrentUserSessionRepo cuRepo;

	@Override
	public Admin saveAdmin(Admin admin) throws AdminException {
		Admin existingUserName = adminRepo.findByAdminUsername(admin.getAdminUsername());

		if (existingUserName != null) {
			throw new AdminException("Admin already exist with this admin user name -  " + admin.getAdminUsername());
		}else {
			return adminRepo.save(admin);
		}
	}
	
	@Override
	public Admin updateAdmin(Admin admin, String key) throws AdminException, LoginException {
		CurrentUserSession loggedInUser = cuRepo.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key to update a admin");
		}

		if (admin.getAdminId() == loggedInUser.getUserId()) {
			return adminRepo.save(admin);
		} else {
			throw new AdminException("Admin Details invalid, please login first");
		}
	}
	
	@Override
	public String deleteAdmin(String key) throws AdminException, LoginException {
		CurrentUserSession loggedInUser = cuRepo.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key to delete Account");
		}
		Optional<Admin> dto = adminRepo.findById(loggedInUser.getUserId());
		
		if (dto.isEmpty()) {
			throw new AdminException("Admin Details invalid, please login first");
		}
		
		Admin existingAdmin = dto.get();
			

		adminRepo.delete(existingAdmin);

		return "Account deleted Successfully";
	}
	

	@Override
	public String logoutAdmin(String key) throws LoginException {
		CurrentUserSession loggedInUser = cuRepo.findByUuid(key);

		if (loggedInUser == null) {
			throw new LoginException("Please provide a valid key");
		}
		cuRepo.delete(loggedInUser);
		return "Logged Out !";
	}

	@Override
	public CurrentUserSession loginAdmin(AdminDto admin) throws LoginException {
	
			Admin existingUser = adminRepo.findByAdminUsername(admin.getAdminUsername());

			if (existingUser == null) {
				throw new LoginException("Username does not exist with this username -" + admin.getAdminUsername());	
			}
			

			Optional<CurrentUserSession> validCustomerSessionOpt = cuRepo.findById(existingUser.getAdminId());

			if (validCustomerSessionOpt.isPresent() && existingUser.getAdminPassword().equals(admin.getAdminPassword())) {
				cuRepo.delete(validCustomerSessionOpt.get());
			}

			if (existingUser.getAdminPassword().equals(admin.getAdminPassword())) {

				String key = RandomString.make(6);

				Boolean isAdmin = true;

				CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getAdminId(), key, isAdmin,LocalDateTime.now());

				cuRepo.save(currentUserSession);

				return currentUserSession;
			} else {
				throw new LoginException("Please Enter a valid password");
			}	
		
	}

}
