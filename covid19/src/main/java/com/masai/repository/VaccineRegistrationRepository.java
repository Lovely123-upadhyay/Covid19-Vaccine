package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.VaccineRegistration;

@Repository
public interface VaccineRegistrationRepository extends JpaRepository<VaccineRegistration, Integer> {

	//custom method:
	public VaccineRegistration findByMobileno(String mobileno);
}
