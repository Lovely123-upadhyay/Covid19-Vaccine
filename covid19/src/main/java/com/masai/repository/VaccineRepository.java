package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masai.beans.Vaccine;
import com.masai.exception.VaccineException;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer>{
	
	@Query("from Vaccine where vaccineName=:name")
	public Vaccine findByName(@Param("name") String name) throws VaccineException;
}
