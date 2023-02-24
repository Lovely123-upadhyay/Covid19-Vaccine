package com.masai.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masai.beans.VaccineInventory;

@Repository
public interface VaccineInventoryRepository extends JpaRepository<VaccineInventory, Integer>{
	@Query("Select * from vaccine_inventory where Date_Time=:Date")
	public List<VaccineInventory> findByDate(@Param("Date") LocalDateTime Date);
	
//	@Query("Select * from vaccine_inventory where vaccine")
//	public List<VaccineInventory> findByDate(@Param("Date") LocalDateTime Date);
}
