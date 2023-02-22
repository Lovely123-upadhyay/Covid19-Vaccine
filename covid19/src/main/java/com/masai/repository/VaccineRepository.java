package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.Vaccine;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer>{

}
