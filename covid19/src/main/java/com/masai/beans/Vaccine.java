package com.masai.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Vaccine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vaccineId;
	
	//@NotNull
	private String vaccineName;
	
	//@Length(min=50)
	private String description;

//	public Vaccine(Integer vaccineId, String vaccineName, String description) {
//		super();
//		this.vaccineId = vaccineId;
//		this.vaccineName = vaccineName;
//		this.description = description;
//	}
//
//	public Vaccine() {
//		super();
//	}
//
//	public Integer getVaccineId() {
//		return vaccineId;
//	}
//
//	public void setVaccineId(Integer vaccineId) {
//		this.vaccineId = vaccineId;
//	}
//
//	public String getVaccineName() {
//		return vaccineName;
//	}
//
//	public void setVaccineName(String vaccineName) {
//		this.vaccineName = vaccineName;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	@Override
//	public String toString() {
//		return "Vaccine [vaccineId=" + vaccineId + ", vaccineName=" + vaccineName + ", description=" + description
//				+ "]";
//	}
}
