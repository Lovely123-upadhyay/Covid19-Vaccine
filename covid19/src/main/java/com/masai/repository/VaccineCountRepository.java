package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.beans.Vaccine;
import com.masai.beans.VaccineCount;
import com.masai.beans.VaccineInventory;

public interface VaccineCountRepository extends JpaRepository<VaccineCount, Integer> {
	public VaccineCount findByVaccine(Vaccine v);
	public List<VaccineCount> findByInventory(VaccineInventory i);
}
