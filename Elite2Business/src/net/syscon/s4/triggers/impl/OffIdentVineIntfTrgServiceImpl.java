package net.syscon.s4.triggers.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.OrAudit;
import net.syscon.s4.triggers.OffIdentVineIntfTrgRepository;
import net.syscon.s4.triggers.OffIdentVineIntfTrgService;

@Service
public class OffIdentVineIntfTrgServiceImpl extends BaseBusiness implements OffIdentVineIntfTrgService{
	
	@Autowired
	private OffIdentVineIntfTrgRepository repository;
	

	@Override
	public void offIdentVineIntfTrg(final List<OffenderIdentifier> offIdList,final List<OffenderIdentifier> oldUpdateList,final String operation) {
		Long vOffId=null;
		OffenderBookings vBookOrRec=null;
		Offenders vOffRec=null;
		if(operation.equalsIgnoreCase("INSERT")) {
			for(OffenderIdentifier data:offIdList) {
				if(data.getIdentifierType() != null && data.getIdentifierType().equals("SSN")) {
					vOffId=data.getOffenderId();
					vBookOrRec=repository.curBookOr(vOffId);
					if(vBookOrRec != null) {
						vOffRec=repository.curOff(data.getOffenderId());
						OrAudit updateOrAudit = new OrAudit();
						updateOrAudit.setActionType("U");
						updateOrAudit.setAgyLocId(vBookOrRec.getAgyLocId());
						updateOrAudit.setOffenderIdDisplay(vOffRec.getOffenderIdDisplay());
						updateOrAudit.setBookingNo(vBookOrRec.getBookingNo());
						updateOrAudit.setBookingBeginDate(vBookOrRec.getBookingBeginDate());
						updateOrAudit.setFirstName(vOffRec.getFirstName());
						updateOrAudit.setMiddleName(vOffRec.getMiddleName());
						updateOrAudit.setLastName(vOffRec.getLastName());
						updateOrAudit.setBirthDate(vOffRec.getBirthDate());
						updateOrAudit.setRaceCode(vOffRec.getRaceCode());
						updateOrAudit.setSexCode(vOffRec.getSexCode());
						updateOrAudit.setOffenderId(vBookOrRec.getOffenderId().longValue());
						updateOrAudit.setOffenderBookId(vBookOrRec.getOffenderBookId());
						updateOrAudit.setModifyUserId(vBookOrRec.getModifyUserId());
						Integer result = repository.updateOrAudit(updateOrAudit);
						if (result != 1) {
							OrAudit insertOrAudit = new OrAudit();
							insertOrAudit.setActionType("A");
							insertOrAudit.setAgyLocId(vBookOrRec.getAgyLocId());
							insertOrAudit.setOffenderIdDisplay(vOffRec.getOffenderIdDisplay());
							insertOrAudit.setBookingNo(vBookOrRec.getBookingNo());
							insertOrAudit.setBookingBeginDate(vBookOrRec.getBookingBeginDate());
							insertOrAudit.setFirstName(vOffRec.getFirstName());
							insertOrAudit.setMiddleName(vOffRec.getMiddleName());
							insertOrAudit.setLastName(vOffRec.getLastName());
							insertOrAudit.setBirthDate(vOffRec.getBirthDate());
							insertOrAudit.setRaceCode(vOffRec.getRaceCode());
							insertOrAudit.setSexCode(vOffRec.getSexCode());
							insertOrAudit.setOffenderId(vBookOrRec.getOffenderId().longValue());
							insertOrAudit.setOffenderBookId(vBookOrRec.getOffenderBookId());
							updateOrAudit.setModifyUserId(vBookOrRec.getModifyUserId());
							repository.insertOrAudit(insertOrAudit);
						}
					}
				}
			}
		} else if(operation.equalsIgnoreCase("UPDATE")) {
			for(OffenderIdentifier data:offIdList) {
				if(data.getIdentifierType() != null && data.getIdentifierType().equals("SSN")) {
					vOffId=data.getOffenderId();
					vBookOrRec=repository.curBookOr(vOffId);
					if(vBookOrRec != null) {
						vOffRec=repository.curOff(data.getOffenderId());
						OrAudit updateOrAudit = new OrAudit();
						updateOrAudit.setActionType("U");
						updateOrAudit.setAgyLocId(vBookOrRec.getAgyLocId());
						updateOrAudit.setOffenderIdDisplay(vOffRec.getOffenderIdDisplay());
						updateOrAudit.setBookingNo(vBookOrRec.getBookingNo());
						updateOrAudit.setBookingBeginDate(vBookOrRec.getBookingBeginDate());
						updateOrAudit.setFirstName(vOffRec.getFirstName());
						updateOrAudit.setMiddleName(vOffRec.getMiddleName());
						updateOrAudit.setLastName(vOffRec.getLastName());
						updateOrAudit.setBirthDate(vOffRec.getBirthDate());
						updateOrAudit.setRaceCode(vOffRec.getRaceCode());
						updateOrAudit.setSexCode(vOffRec.getSexCode());
						updateOrAudit.setOffenderId(vBookOrRec.getOffenderId().longValue());
						updateOrAudit.setOffenderBookId(vBookOrRec.getOffenderBookId());
						updateOrAudit.setModifyUserId(vBookOrRec.getModifyUserId());
						Integer result = repository.updateOrAudit(updateOrAudit);
						if (result != 1) {
							OrAudit insertOrAudit = new OrAudit();
							insertOrAudit.setActionType("U");
							insertOrAudit.setAgyLocId(vBookOrRec.getAgyLocId());
							insertOrAudit.setOffenderIdDisplay(vOffRec.getOffenderIdDisplay());
							insertOrAudit.setBookingNo(vBookOrRec.getBookingNo());
							insertOrAudit.setBookingBeginDate(vBookOrRec.getBookingBeginDate());
							insertOrAudit.setFirstName(vOffRec.getFirstName());
							insertOrAudit.setMiddleName(vOffRec.getMiddleName());
							insertOrAudit.setLastName(vOffRec.getLastName());
							insertOrAudit.setBirthDate(vOffRec.getBirthDate());
							insertOrAudit.setRaceCode(vOffRec.getRaceCode());
							insertOrAudit.setSexCode(vOffRec.getSexCode());
							insertOrAudit.setOffenderId(vBookOrRec.getOffenderId().longValue());
							insertOrAudit.setOffenderBookId(vBookOrRec.getOffenderBookId());
							insertOrAudit.setCreateUserId(vBookOrRec.getCreateUserId());
							repository.insertOrAudit(insertOrAudit);
						}
					}
				}
			}
		} else if(operation.equalsIgnoreCase("DELETE")) {
			for(int i=0;i<offIdList.size();i++) {
				OffenderIdentifier old=oldUpdateList.get(0);
				if(old.getIdentifierType() != null && old.getIdentifierType().equals("SSN")) {
					vOffId=old.getOffenderId();
					vBookOrRec=repository.curBookOr(vOffId);
					if(vBookOrRec != null) {
						vOffRec=repository.curOff(old.getOffenderId());
						OrAudit updateOrAudit = new OrAudit();
						updateOrAudit.setActionType("U");
						updateOrAudit.setAgyLocId(vBookOrRec.getAgyLocId());
						updateOrAudit.setOffenderIdDisplay(vOffRec.getOffenderIdDisplay());
						updateOrAudit.setBookingNo(vBookOrRec.getBookingNo());
						updateOrAudit.setBookingBeginDate(vBookOrRec.getBookingBeginDate());
						updateOrAudit.setFirstName(vOffRec.getFirstName());
						updateOrAudit.setMiddleName(vOffRec.getMiddleName());
						updateOrAudit.setLastName(vOffRec.getLastName());
						updateOrAudit.setBirthDate(vOffRec.getBirthDate());
						updateOrAudit.setRaceCode(vOffRec.getRaceCode());
						updateOrAudit.setSexCode(vOffRec.getSexCode());
						updateOrAudit.setOffenderId(vBookOrRec.getOffenderId().longValue());
						updateOrAudit.setOffenderBookId(vBookOrRec.getOffenderBookId());
						updateOrAudit.setModifyUserId(vBookOrRec.getModifyUserId());
						Integer result = repository.updateOrAudit(updateOrAudit);
						if (result != 1) {
							OrAudit insertOrAudit = new OrAudit();
							insertOrAudit.setActionType("U");
							insertOrAudit.setAgyLocId(vBookOrRec.getAgyLocId());
							insertOrAudit.setOffenderIdDisplay(vOffRec.getOffenderIdDisplay());
							insertOrAudit.setBookingNo(vBookOrRec.getBookingNo());
							insertOrAudit.setBookingBeginDate(vBookOrRec.getBookingBeginDate());
							insertOrAudit.setFirstName(vOffRec.getFirstName());
							insertOrAudit.setMiddleName(vOffRec.getMiddleName());
							insertOrAudit.setLastName(vOffRec.getLastName());
							insertOrAudit.setBirthDate(vOffRec.getBirthDate());
							insertOrAudit.setRaceCode(vOffRec.getRaceCode());
							insertOrAudit.setSexCode(vOffRec.getSexCode());
							insertOrAudit.setOffenderId(vBookOrRec.getOffenderId().longValue());
							insertOrAudit.setOffenderBookId(vBookOrRec.getOffenderBookId());
							insertOrAudit.setCreateUserId(vBookOrRec.getCreateUserId());
							repository.insertOrAudit(insertOrAudit);
						}
					}
				}
			}
		}
		
	}
}
