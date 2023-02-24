package com.masai.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;


@Data
@Entity
public class VaccineCount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer countid;
	@OneToOne
	private Vaccine vaccine;
	private Integer quantity;
}
