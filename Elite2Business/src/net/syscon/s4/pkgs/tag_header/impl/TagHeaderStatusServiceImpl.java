package net.syscon.s4.pkgs.tag_header.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.pkgs.tag_header.TagHeaderStatusRepository;
import net.syscon.s4.pkgs.tag_header.TagHeaderStatusService;

@Service
public class TagHeaderStatusServiceImpl implements TagHeaderStatusService{

	Logger logger = LogManager.getLogger(TagHeaderStatusServiceImpl.class);

	@Autowired
	TagHeaderStatusRepository tagHeaderStatusRepository;

	@Autowired
	OmsMiscellaneousService omsMiscellaneousService;

	@Override
	public String getHeaderStatus(String activeFlag, String commActiveFlag, String statusReason, String headerStatusFlag, String commStatus, String inOutStatus, BigDecimal rootOffenderId, Long offenderBookId, String currentUser) {
		// TODO Auto-generated method stub
		String returnStatus = null;
		String caseLoadType = null;
		String vCaseNotType = null;
		String vCaseNoteSubType = null;
		String lvProfileValue1 = getOmsSystemProfile("CLIENT", "B/C_STATUS", 1);
		caseLoadType = getCaseLoadType(currentUser);
		if(caseLoadType.equalsIgnoreCase("COMM")) {
			if(commActiveFlag!= null && commActiveFlag.equalsIgnoreCase("Y")) {
				returnStatus = lvProfileValue1;
				SystemProfiles sysProfiles = new SystemProfiles();
				sysProfiles.setProfileType("CLIENT");
				sysProfiles.setProfileCode("COMM_STATUS2");
				vCaseNotType = omsMiscellaneousService.getProfileValue(sysProfiles);
				if(vCaseNotType != null) {
					vCaseNoteSubType = tagHeaderStatusRepository.cnoteCur(offenderBookId, vCaseNotType);
					if(vCaseNoteSubType != null) {
						returnStatus = lvProfileValue1 + "-" + vCaseNoteSubType;
					} else {
						returnStatus = lvProfileValue1;
					}
				}
				return returnStatus;
			} else if(commActiveFlag!=null && commActiveFlag.equalsIgnoreCase("N")) {
				returnStatus = getBookingCount(rootOffenderId, offenderBookId, caseLoadType);
			} else {
				returnStatus = null;
			}
		} else {
			if(activeFlag!= null && activeFlag.equalsIgnoreCase("Y")) {
				if(inOutStatus.equalsIgnoreCase("OUT") && headerStatusFlag == "Y"){
					returnStatus = "ACTIVE";
				} else {
					returnStatus = "ACTIVE";
				}
			} else if(activeFlag != null && activeFlag.equalsIgnoreCase("N")) {
				if((statusReason!= null && statusReason.length()>7 && statusReason.substring(5, 8).equalsIgnoreCase("ESCP")) || (statusReason != null && statusReason.length()>6 && statusReason.substring(5, 7).equalsIgnoreCase("UAL")) || inOutStatus.equalsIgnoreCase("TRN")) {
					returnStatus = "INACTIVE";
				} else {
					returnStatus = getBookingCount(rootOffenderId, offenderBookId, caseLoadType);
				}
			} else {
				returnStatus = null;
			}
		}
		return returnStatus != null ? Utilities.capitalize(returnStatus.toLowerCase()) : returnStatus;
	}

	@Override
	public String getCaseLoadType(String currentUser) {
		String caseLoadType = null;
		caseLoadType = tagHeaderStatusRepository.getTypeCur(currentUser);
		return caseLoadType;
	}

	@Override
	public String getBookingCount(BigDecimal rootOffenderId, Long offenderBookId, String caseLoadType) {

		int lvBookingCount = 0;
		Long lvBookingId = 0L;
		String returnString = null;
		String lvProfileValue2 = getOmsSystemProfile("CLIENT", "B/C_STATUS", 2);

		if(rootOffenderId != null) {
			lvBookingCount = tagHeaderStatusRepository.getCountCur(rootOffenderId);
			if(lvBookingCount == 1) {
				if(caseLoadType.equalsIgnoreCase("INST")) {
					returnString = "INACTIVE";
				} else {
					returnString = lvProfileValue2;
				}
			} else {
				lvBookingId = tagHeaderStatusRepository.checkMaxBooking(rootOffenderId);
				if((lvBookingId.toString()).equalsIgnoreCase(offenderBookId.toString())) {
					if(caseLoadType.equalsIgnoreCase("INST")) {
						returnString = "INACTIVE";
					} else {
						returnString = lvProfileValue2;
					}
				} else {
					returnString = "HISTORIC";
				}
			}
		}
		return returnString;
	}

	public String getOmsSystemProfile(String profileType, String profileCode, int profileValueNo) {
		String returnString = null;
		try {
			Map<String, Object> profileValue = omsMiscellaneousService.getProfileValues(profileType, profileCode);
			if(profileValue != null && !profileValue.isEmpty()) {
				if(profileValueNo == 1) {
					returnString = profileValue.get("P_PROFILE_VALUE").toString();
				} else if(profileValueNo == 2) {
					returnString = profileValue.get("P_PROFILE_VALUE_2").toString();
				}
			}
		} catch(Exception e) {
			logger.error("Exception occured in TagHeaderStatusServiceImpl - getOmsSystemProfile " + e);
			return null;
		}
		return returnString;
	}
}
