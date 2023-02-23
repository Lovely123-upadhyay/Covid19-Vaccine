package com.masai.beans;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class VaccineRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer regId;
	
	private String mobileno;
	
	private LocalDate dateofregistration;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Member> members = new ArrayList<>();
	
  }