package com.masai.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class VaccinationCenter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Code;
	private String centerName;
	private String address;
	
	@OneToMany
	@JsonIgnore
	List<Appointment> appointments = new ArrayList<>();
	
	@OneToMany
	@JsonIgnore
	List<VaccineInventory> vaccineInventories = new ArrayList<>();
}
