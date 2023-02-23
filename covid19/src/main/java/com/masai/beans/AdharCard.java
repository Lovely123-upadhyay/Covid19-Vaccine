package com.masai.beans;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masai.enums.Fingerprint;
import com.masai.enums.Iris;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AdharCard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int adharId;
	
//	@Pattern(regexp = "^[0-9]{12}", message = "{user.invalid.adharNo}")
	@Column(unique = true)
	private String adharNo;

//	@NotNull(message="FingerPrints can't be null")
	@Enumerated(EnumType.STRING)
	private Fingerprint fringerprints;

//	@NotNull(message="Iris can't be null")
	@Enumerated(EnumType.STRING)
	private Iris irisscan;
	
//	@OneToOne(cascade = CascadeType.ALL,mappedBy = "adharCard")
	@JsonIgnore
	private IdCard idCard;

}
