package com.masai.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class VaccineInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer inventoryId;
	private LocalDate date;
	
//	@OneToOne(mappedBy = "centerCode")
//	private VaccinationCenter center;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	@NotNull
	private Set<VaccineCount> vaccineCount=new HashSet<VaccineCount>();
}
