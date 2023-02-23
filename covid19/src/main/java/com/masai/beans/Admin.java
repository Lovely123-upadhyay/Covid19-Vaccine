package com.masai.beans;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int adminId;
	
//	@Size(min = 2, max = 20, message = "{user.invalid.Name}")
	private String adminName;
	
//	@Pattern(regexp = "^[0-9]{10}", message = "{user.invalid.contact}")
	@Column(unique = true)
	private String adminMobileNumber;
	
//	@email
	@Column(unique = true)
	private String adminUsername;
	
//	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "{user.invalid.password}")
	private String adminPassword;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminMobileNumber() {
		return adminMobileNumber;
	}

	public void setAdminMobileNumber(String adminMobileNumber) {
		this.adminMobileNumber = adminMobileNumber;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	

}
