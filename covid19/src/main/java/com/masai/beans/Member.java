package com.masai.beans;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Member {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int memberId;

private boolean dose1status=false;

private boolean dose2status=false;

private LocalDate  dose1date=null;

private LocalDate  dose2date=null;



@OneToOne(cascade = CascadeType.ALL)
private IdCard idCard;
}
