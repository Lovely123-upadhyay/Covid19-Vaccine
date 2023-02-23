package com.masai.repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.masai.beans.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Integer>{
	

}
