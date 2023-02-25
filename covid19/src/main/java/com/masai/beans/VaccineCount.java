package com.masai.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
public class VaccineCount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer countid;
	
	@OneToOne
	private Vaccine vaccine;
	
	@ManyToOne
	@JsonIgnore
	private VaccineInventory inventory;
	
	private Integer quantity;

	@Override
	public String toString() {
		return "VaccineCount [countid=" + countid + ", vaccine=" + vaccine + ", quantity=" + quantity + "]";
	}
}
