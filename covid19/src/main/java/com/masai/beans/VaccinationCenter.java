package com.masai.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

//@Entity
@Data
public class VaccinationCenter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer code;
	private String name;
	private String address;
	
	//private VaccineInventory inventory;
}
