package com.masai.beans;

import java.util.List;

import javax.persistence.Entity;

import lombok.Data;

//@Entity
@Data
public class VaccineInventory {

	private Integer Quantity;
	private double Price;

	//private VaccinationCenter center;
	private Vaccine vaccineList;
}
