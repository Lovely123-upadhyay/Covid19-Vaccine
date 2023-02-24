package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.IdCard;
import com.masai.beans.Member;
import com.masai.beans.VaccineRegistration;
import com.masai.dto.CurrentUserSession;
import com.masai.dto.MemberUpdateDto;
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


@Service
public class MemberServicesImpl implements MemberServices{
	
	@Autowired
	private VaccineRegistrationRepository vrepo;
	
	@Autowired
	private CurrentUserSessionRepo cus;
	
	@Autowired
	private IdCardRepo icardRepo;
	
	@Autowired
	private MemberRepo memRepo;

	
	
	@Override
	public Member getMemberByld(String key, int idcardid) throws LoginException, MemberException, IdCardException {
		
		CurrentUserSession lu = cus.findByUuid(key);
		
		if(lu!=null) {
		if(lu.getAdmin()==false) {
			Optional<IdCard> idCard = icardRepo.findById(idcardid);
			
			if(idCard.isEmpty()) {
				throw new IdCardException("no id card found with this num " + idcardid);
			}else {
				Optional<Member> mem = memRepo.findById(idcardid);
				return mem.get();
			}
		}else{
			throw new LoginException("pleaee login first");
		}
		}else {
			throw new LoginException("please login first");
		}
	}

	@Override
	public Member getMemberByAdharNo(String adharNo) throws MemberException,AdharCardException {
		
		Member mem = memRepo.findByAdharcardNo(adharNo);
		if (mem == null) {
			throw new MemberException("No member found with this adhar card number " + adharNo);
		}else {
			return mem;
		}
		
	}

	@Override
	public Member getMemberByPanNo(String panNo) throws MemberException,PanCardException {
		Member mem =memRepo.findByPanNo(panNo);
		
		if (mem == null) {
			throw new MemberException("No member found with this pan number " + panNo);
		}else {
			return mem;
		}
	}

	@Override
	public Member addMember(Member member) throws LoginException, MemberException,VaccineRegistrationException {
		
		if(member!=null) {
			 return memRepo.save(member);
		}
		else {
			throw new MemberException("please provide valid input");
		}
		
		
//		CurrentUserSession lu = cus.findByUuid(key);
//		
//		if(lu!=null) {
//			
//		if(lu.getAdmin()==false) {
//			
//			Optional<Member> mem = memRepo.findById(member.getMemberId());
//			
//			Optional<VaccineRegistration> vaccineRegistration = vrepo.findById(lu.getUserId());
//			if(vaccineRegistration.isPresent()) {
//				
//				if(mem.isEmpty()) {
//					
//					VaccineRegistration vr = vaccineRegistration.get();
//					 Member x = member;
//					 vr.getMembers().add(x);
//					 memRepo.save(x);
//					 return x;
//					
//					
//				}
//				else {
//					throw new MemberException("Member already present");
//				}
//			}else {
//				throw new VaccineRegistrationException("No vaccine registration found ");
//			}
//		}else{
//			throw new LoginException("please login first");
//		}
//		}else {
//			throw new LoginException("please login first");
//		}
	}

	@Override
	public Member updateMember(String key,int idcardid, MemberUpdateDto MemberUpdateDto) throws LoginException, MemberException,VaccineRegistrationException,IdCardException  {
		CurrentUserSession loggedInUser = cus.findByUuid(key);
		
		if(loggedInUser!=null) {
			
		if(loggedInUser.getAdmin()==false) {
			Optional<Member> mem = memRepo.findById(idcardid);
		
			mem.get().getIdCard().setName(MemberUpdateDto.getName());
			mem.get().getIdCard().setAddress(MemberUpdateDto.getAddress());
			mem.get().getIdCard().setCity(MemberUpdateDto.getCity());
			mem.get().getIdCard().setDateOfBirth(MemberUpdateDto.getDateOfBirth());
			mem.get().getIdCard().setGender(MemberUpdateDto.getGender());
			mem.get().getIdCard().setState(MemberUpdateDto.getState());
			
			memRepo.save(mem.get());
			return mem.get();
			
			}else{
			throw new LoginException("Person login first");
		}
		
		
		}else {
			throw new LoginException("please login first");
		}
	}

	@Override
	public Member deleteMember(String key,int idcardid) throws LoginException, MemberException,IdCardException,VaccineRegistrationException {
		CurrentUserSession lu = cus.findByUuid(key);
		if(lu!=null) {
			
		if(lu.getAdmin()==false) {
			Optional<VaccineRegistration> vaccineRegistration = vrepo.findById(lu.getUserId());
			if(vaccineRegistration.isEmpty()) {
				
				throw new VaccineRegistrationException("No vaccine registration found ");
				
			
			}else {
				Optional<Member> mem = memRepo.findById(idcardid);
				VaccineRegistration vr = vaccineRegistration.get();
				vr.getMembers().remove(mem.get());
				memRepo.delete(mem.get());
				return mem.get();
				
				
			}
			}else{
			throw new LoginException("Person login first");
		}
		}else {
			throw new LoginException("please login first");
		}
	}

}
