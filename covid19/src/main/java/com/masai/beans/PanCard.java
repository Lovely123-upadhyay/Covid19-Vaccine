package com.masai.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PanCard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int panId;
	
//	@Size(min = 10, max = 10, message = "{user.invalid.panNumber}")
	@Column(unique = true)
	private String panoNo;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "panCard")
	@JsonIgnore
	private IdCard idCard;
}
