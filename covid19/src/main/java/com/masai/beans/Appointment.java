package com.masai.beans;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingId;
	
	private String mobileNo;
	
	private LocalDate dateOfBooking = LocalDate.now();
	
	private String slot;
	
	private Boolean bookingStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Member member;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private VaccinationCenter vaccinationCenter;
}