package com.masai.service;

import com.masai.beans.Member;
import com.masai.dto.MemberUpdateDto;
import com.masai.exception.AdharCardException;
import com.masai.exception.IdCardException;
import com.masai.exception.LoginException;
import com.masai.exception.MemberException;
import com.masai.exception.PanCardException;
import com.masai.exception.VaccineRegistrationException;

public interface MemberServices {
	
    public Member getMemberByld(String key,int idcard) throws LoginException,MemberException,IdCardException;
    
    public Member getMemberByAdharNo(String adharNo) throws MemberException,AdharCardException;
    
    public Member getMemberByPanNo(String panNo) throws MemberException,PanCardException;
    
    public Member addMember(String key,Member member) throws LoginException,MemberException,VaccineRegistrationException;
    
    public Member updateMember(String key,int idcardid,MemberUpdateDto MemberUpdateDto) throws LoginException,IdCardException,MemberException,VaccineRegistrationException;
    
    public Member deleteMember(String key,int idcardid) throws LoginException,MemberException,IdCardException,VaccineRegistrationException;

}
