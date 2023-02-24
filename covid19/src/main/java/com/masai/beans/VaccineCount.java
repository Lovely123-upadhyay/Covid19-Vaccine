package com.masai.beans;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Embeddable
@Data
public class VaccineCount {
	
	//@OneToOne
	private Vaccine vaccine;
	private Integer quantity;
}
