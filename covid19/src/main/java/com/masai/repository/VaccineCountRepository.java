package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.beans.Vaccine;
import com.masai.beans.VaccineCount;

public interface VaccineCountRepository extends JpaRepository<VaccineCount, Integer> {
	public VaccineCount findByVaccine(Vaccine v);
}
