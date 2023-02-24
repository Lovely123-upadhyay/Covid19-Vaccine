package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.VaccinationCenter;


public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, Integer>{

}
