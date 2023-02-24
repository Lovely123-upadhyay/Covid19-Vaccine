package com.masai.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
public class VaccineRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer regId;
	
	private String mobileno;
	
	private LocalDate dateofregistration;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Member> members = new ArrayList<>();

	public Integer getRegId() {
		return regId;
	}

	public void setRegId(Integer regId) {
		this.regId = regId;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public LocalDate getDateofregistration() {
		return dateofregistration;
	}

	public void setDateofregistration(LocalDate dateofregistration) {
		this.dateofregistration = dateofregistration;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	
	
  }