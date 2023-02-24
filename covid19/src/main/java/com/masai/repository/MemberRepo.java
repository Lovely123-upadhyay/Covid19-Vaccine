package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.masai.beans.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Integer>{
	
	@Query("from Member where idCard.Id=:card")
	public Member findByidCard(@Param("card") int idcardid);

	@Query("from Member where idCard.adharCard.adharNo=:ano")
	public Member findByAdharcardNo(@Param("ano") String adarno);

	@Query("from Member where idCard.panCard.panoNo=:pnum")
	public Member findByPanNo(@Param("pnum") String panno);

}
