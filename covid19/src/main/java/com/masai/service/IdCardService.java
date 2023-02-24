package com.masai.service;

import com.masai.beans.IdCard;
import com.masai.exception.AdharCardException;
import com.masai.exception.IdCardException;
import com.masai.exception.LoginException;
import com.masai.exception.MemberException;
import com.masai.exception.PanCardException;
import com.masai.exception.VaccineRegistrationException;

public interface IdCardService {
	public IdCard addIdCard(IdCard idCard) throws LoginException,IdCardException,MemberException,VaccineRegistrationException;
	
	public IdCard findIdCardByAdharNo(String adharNo) throws IdCardException,AdharCardException;
	
	public IdCard findIdCardBypanNo(String panNo) throws IdCardException,PanCardException;
}
