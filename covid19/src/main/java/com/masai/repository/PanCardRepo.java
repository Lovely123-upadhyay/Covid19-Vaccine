package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.masai.beans.PanCard;

@Repository
public interface PanCardRepo extends JpaRepository<PanCard, Integer>{
	public PanCard findBypanoNo(String panoNo);
}
