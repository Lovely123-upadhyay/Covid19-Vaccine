package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.AdharCard;
import com.masai.beans.IdCard;
import com.masai.beans.Member;
import com.masai.beans.PanCard;
import com.masai.beans.VaccineRegistration;
import com.masai.dto.CurrentUserSession;
import com.masai.exception.AdharCardException;
import com.masai.exception.IdCardException;
import com.masai.exception.LoginException;
import com.masai.exception.MemberException;
import com.masai.exception.PanCardException;
import com.masai.exception.VaccineRegistrationException;
import com.masai.repository.AdharCardRepo;
import com.masai.repository.CurrentUserSessionRepo;
import com.masai.repository.IdCardRepo;
import com.masai.repository.MemberRepo;
import com.masai.repository.PanCardRepo;
import com.masai.repository.VaccineRegistrationRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class IdCardServiceImpl implements IdCardService{

    
    @Autowired
	private PanCardRepo panRepo;
	
	@Autowired
	private AdharCardRepo adharRepo;
	
	@Autowired
	private CurrentUserSessionRepo cuRepo;
	
	@Autowired
	private MemberRepo memRepo;
	
	@Autowired
	private VaccineRegistrationRepository vRepo;

	@Override
	public IdCard addIdCard(String key,IdCard idCard) throws LoginException,IdCardException,MemberException,VaccineRegistrationException {
		CurrentUserSession lu = cuRepo.findByUuid(key);
		if(lu!=null) {
			
		if(lu.getAdmin()==false) {
			
			Optional<VaccineRegistration> vr = vRepo.findById(lu.getUserId());
			
			if(vr.isPresent()) {
				
					VaccineRegistration vr1 = vr.get();
					Member mem = new Member();
					mem.setIdCard(idCard);
					
					vr1.getMembers().add(mem);
					memRepo.save(mem);
					return idCard;
				
			}
			else {
				throw new VaccineRegistrationException("Vaccine registration not found ");
			}
		}
		else{
			throw new LoginException("please login as a admin");
		}
		}else {
			throw new LoginException("Please login first");
		}
	}

	@Override
	public IdCard findIdCardByAdharNo(String adharNo) throws IdCardException, AdharCardException {
		AdharCard ac = adharRepo.findByAdharNo(adharNo);
		if (ac == null) {
			throw new AdharCardException("No user found with this adhar no " + ac.getAdharNo());
		}else {
			IdCard eu =  ac.getIdCard();	
			return eu;
	}
	}

	@Override
	public IdCard findIdCardBypanNo(String panNo) throws IdCardException, PanCardException{

		PanCard pnum = panRepo.findBypanoNo(panNo);
		if (pnum == null) {
			throw new PanCardException("No user found with this pan no " + pnum.getPanoNo());
		}else {
            IdCard existing =  pnum.getIdCard();
			return existing;

		}
	}
	
	}
	
