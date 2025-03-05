package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.triggers.OffendersJn;
import net.syscon.s4.triggers.OffendersTjnRepository;
import net.syscon.s4.triggers.OffendersTjnService;

@Service
public class OffendersTjnServiceImpl extends BaseBusiness implements OffendersTjnService {

	@Autowired
	private OffendersTjnRepository repository;
	@Override
	public void offendersTjn(List<Offenders> offenderList, final List<Offenders> oldUpdatedList, String operation) {
		if (operation.equalsIgnoreCase("INSERT")) {
			for (Offenders data : offenderList) {
				OffendersJn obj = new OffendersJn();
				obj.setJnOperation("INS");
				obj.setJnSession(BigDecimal.ZERO);
				obj.setOffenderId(data.getOffenderId());
				obj.setOffenderNameSeq(data.getOffenderNameSeq() != null ?data.getOffenderNameSeq().longValue():null);
				obj.setIdSourceCode(data.getIdSourceCode());
				obj.setLastName(data.getLastName());
				obj.setNameType(data.getNameType());
				obj.setFirstName(data.getFirstName());
				obj.setMiddleName(data.getMiddleName());
				obj.setBirthDate(data.getBirthDate());
				obj.setSexCode(data.getSexCode());
				obj.setSuffix(data.getSuffix());
				obj.setLastNameSoundex(data.getLastNameSoundex());
				obj.setBirthPlace(data.getBirthPlace());
				obj.setBirthCountryCode(data.getBirthCountryCode());
				obj.setCreateDate(data.getCreateDate());
				obj.setLastNameKey(data.getLastNameKey());
				obj.setAliasOffenderId(data.getAliasOffenderId());
				obj.setFirstNameKey(data.getFirstNameKey());
				obj.setMiddleNameKey(data.getMiddleNameKey());
				obj.setOffenderIdDisplay(data.getOffenderIdDisplay());
				obj.setRootOffenderId(data.getRootOffenderId() != null ?data.getRootOffenderId().longValue():null);
				obj.setCaseloadType(data.getCaseloadType());
				obj.setModifyUserId(data.getModifyUserId());
				obj.setAliasNameType(data.getAliasNameType());
				obj.setParentOffenderId(data.getParentOffenderId() != null ?data.getParentOffenderId().longValue():null);
				obj.setUniqueObligationFlag(data.getUniqueObligationFlag());
				obj.setSuspendedFlag(data.getSuspendedFlag());
				obj.setSuspendedDate(data.getSuspendedDate());
				obj.setRaceCode(data.getRaceCode());
				obj.setRemarkCode(data.getRemarkCode());
				obj.setAddInfoCode(data.getAddInfoCode());
				obj.setBirthCounty(data.getBirthCounty());
				obj.setBirthState(data.getBirthState());
				obj.setMiddleName2(data.getMiddleName2());
				obj.setTitle(data.getTitle());
				obj.setAge(data.getAge() != null ?data.getAge().longValue():null);
				obj.setCreateUserId(data.getCreateUserId());
				obj.setLastNameAlphaKey(data.getLastNameAlphaKey());
				obj.setNameSequence(data.getNameSequence());
				obj.setSealFlag(data.getSealFlag());
				obj.setJnOracleUser(data.getCreateUserId());
				obj.setGenderCode(data.getGenderCode());
				repository.insertOffendersJn(obj);
			}

		} else if (operation.equalsIgnoreCase("UPDATE")) {
			for (Offenders old : oldUpdatedList) {
				OffendersJn obj = new OffendersJn();
				obj.setJnOperation("UPD");
				obj.setJnSession(BigDecimal.ZERO);
				obj.setOffenderId(old.getOffenderId());
				if(obj.getOffenderNameSeq()!=null) {
					obj.setOffenderNameSeq(old.getOffenderNameSeq().longValue());	
				}
				obj.setIdSourceCode(old.getIdSourceCode());
				obj.setLastName(old.getLastName());
				obj.setNameType(old.getNameType());
				obj.setFirstName(old.getFirstName());
				obj.setMiddleName(old.getMiddleName());
				obj.setBirthDate(old.getBirthDate());
				obj.setSexCode(old.getSexCode());
				obj.setSuffix(old.getSuffix());
				obj.setLastNameSoundex(old.getLastNameSoundex());
				obj.setBirthPlace(old.getBirthPlace());
				obj.setBirthCountryCode(old.getBirthCountryCode());
				obj.setCreateDate(old.getCreateDate());
				obj.setLastNameKey(old.getLastNameKey());
				obj.setAliasOffenderId(old.getAliasOffenderId());
				obj.setFirstNameKey(old.getFirstNameKey());
				obj.setMiddleNameKey(old.getMiddleNameKey());
				obj.setOffenderIdDisplay(old.getOffenderIdDisplay());
				if(old.getRootOffenderId() !=null) {
				obj.setRootOffenderId(old.getRootOffenderId().longValue());
				}
				obj.setCaseloadType(old.getCaseloadType());
				obj.setModifyUserId(old.getModifyUserId());
				obj.setAliasNameType(old.getAliasNameType());
				if(old.getParentOffenderId() !=null) {
				obj.setParentOffenderId(old.getParentOffenderId().longValue());
				}
				obj.setUniqueObligationFlag(old.getUniqueObligationFlag());
				obj.setSuspendedFlag(old.getSuspendedFlag());
				obj.setSuspendedDate(old.getSuspendedDate());
				obj.setRaceCode(old.getRaceCode());
				obj.setRemarkCode(old.getRemarkCode());
				obj.setAddInfoCode(old.getAddInfoCode());
				obj.setBirthCounty(old.getBirthCounty());
				obj.setBirthState(old.getBirthState());
				obj.setMiddleName2(old.getMiddleName2());
				obj.setTitle(old.getTitle());
				obj.setAge(old.getAge() != null ?old.getAge().longValue():null);
				obj.setCreateUserId(old.getCreateUserId());
				obj.setLastNameAlphaKey(old.getLastNameAlphaKey());
				obj.setNameSequence(old.getNameSequence());
				obj.setSealFlag(old.getSealFlag());
				obj.setJnOracleUser(old.getCreateUserId());
				obj.setGenderCode(old.getGenderCode());
				repository.insertOffendersJn(obj);
			}

		} else if (operation.equalsIgnoreCase("DELETE")) {
			for (Offenders old : oldUpdatedList) {
				OffendersJn obj = new OffendersJn();
				obj.setJnOperation("DEL");
				obj.setJnSession(BigDecimal.ZERO);
				obj.setOffenderId(old.getOffenderId());
				obj.setOffenderNameSeq(old.getOffenderNameSeq().longValue());
				obj.setIdSourceCode(old.getIdSourceCode());
				obj.setLastName(old.getLastName());
				obj.setNameType(old.getNameType());
				obj.setFirstName(old.getFirstName());
				obj.setMiddleName(old.getMiddleName());
				obj.setBirthDate(old.getBirthDate());
				obj.setSexCode(old.getSexCode());
				obj.setSuffix(old.getSuffix());
				obj.setLastNameSoundex(old.getLastNameSoundex());
				obj.setBirthPlace(old.getBirthPlace());
				obj.setBirthCountryCode(old.getBirthCountryCode());
				obj.setCreateDate(old.getCreateDate());
				obj.setLastNameKey(old.getLastNameKey());
				obj.setAliasOffenderId(old.getAliasOffenderId());
				obj.setFirstNameKey(old.getFirstNameKey());
				obj.setMiddleNameKey(old.getMiddleNameKey());
				obj.setOffenderIdDisplay(old.getOffenderIdDisplay());
				obj.setRootOffenderId(old.getRootOffenderId().longValue());
				obj.setCaseloadType(old.getCaseloadType());
				obj.setModifyUserId(old.getModifyUserId());
				obj.setAliasNameType(old.getAliasNameType());
				obj.setParentOffenderId(old.getParentOffenderId().longValue());
				obj.setUniqueObligationFlag(old.getUniqueObligationFlag());
				obj.setSuspendedFlag(old.getSuspendedFlag());
				obj.setSuspendedDate(old.getSuspendedDate());
				obj.setRaceCode(old.getRaceCode());
				obj.setRemarkCode(old.getRemarkCode());
				obj.setAddInfoCode(old.getAddInfoCode());
				obj.setBirthCounty(old.getBirthCounty());
				obj.setBirthState(old.getBirthState());
				obj.setMiddleName2(old.getMiddleName2());
				obj.setTitle(old.getTitle());
				obj.setAge(old.getAge().longValue());
				obj.setCreateUserId(old.getCreateUserId());
				obj.setLastNameAlphaKey(old.getLastNameAlphaKey());
				obj.setNameSequence(old.getNameSequence());
				obj.setSealFlag(old.getSealFlag());
				obj.setGenderCode(old.getGenderCode());
				repository.insertOffendersJn(obj);
			}

		}

	}

}
