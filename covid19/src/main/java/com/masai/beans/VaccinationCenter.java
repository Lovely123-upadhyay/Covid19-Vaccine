
package com.masai.beans;


import javax.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;


@Entity
@Data
public class VaccinationCenter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer centerCode;
	private String name;

	private String address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@NotNull
	private VaccineInventory inventory;
	
	@OneToMany
	@JsonIgnore
	List<Appointment> appointments = new ArrayList<>();
}