package com.masai.beans;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.*;
import com.masai.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IdCard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
//	@Size(min = 2, max = 20, message = "{user.invalid.Name}")
	private String Name;
	
	private Date DateOfBirth ;

//	@NotNull(message="Gender can't be null")
	@Enumerated(EnumType.STRING)
	private Gender Gender;
	
//	@Size(min = 2, max = 20, message = "{user.invalid.Address}")
	private String Address ;
	
//	@Size(min = 2, max = 20, message = "{user.invalid.City}")
	private String city ;
	
//	@Size(min = 2, max = 20, message = "{user.invalid.State}")
	private String State ;
	
	@OneToOne(cascade = CascadeType.ALL)
	private PanCard panCard;
	
	@OneToOne(cascade = CascadeType.ALL)
	private AdharCard adharCard;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Member member;
}
