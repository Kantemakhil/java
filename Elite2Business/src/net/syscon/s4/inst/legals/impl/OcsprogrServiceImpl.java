package net.syscon.s4.inst.legals.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.cm.programsservices.VOffenderSentCondActs;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.accreditedprograms.beans.VProgramProviders;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.legals.OcsprogrRepository;
import net.syscon.s4.inst.legals.OcsprogrService;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.inst.programswithoutschedules.OcdxprogService;

@Service
public class OcsprogrServiceImpl implements OcsprogrService {

	private static Logger logger = LogManager.getLogger(OcsprogrServiceImpl.class.getName());

	@Autowired
	private OcsprogrRepository ocsprogrRepository;
	
	@Autowired
	private OcdxprogService ocdxprogService;

	public List<ReferenceCodes> rgRefCodeRecordGroup() {
		return ocsprogrRepository.rgRefCodeRecordGroup();
	}

	public List<ProgramServices> rgAccProgramRecordGroup() {
		List<ProgramServices> returnList = ocsprogrRepository.rgAccProgramRecordGroup();
		returnList.forEach(action -> {
			if ("Y".equals(action.getActiveFlag())) {
				action.setCanDisplay(true);
			} else {
				action.setCanDisplay(false);
			}
		});
		return returnList;
	}

	@Override
	public List<TeamMembers> rgRefCodeProviderGroup(String userId) {
		List<TeamMembers> returnList = ocsprogrRepository.rgRefCodeProviderGroup(userId);

		returnList.forEach(action -> {
			action.setCanDisplay(true);
		});
		return returnList;
	}
	@Override
	public List<VProgramProviders> rgProviderRecordGroupTeam() {
		return ocsprogrRepository.rgProviderRecordGroupTeam();

	}

	@Override
	public List<OffenderBookings> offExecQuery(List<String> status, String programId,String intProviderPartyId,String extProviderPartyId,String currentCaseload) {
		String internalCode = null;
		if(intProviderPartyId!= null && !intProviderPartyId.trim().equals("")) { 
		String userId = null;
		List<TeamMembers> internalLov = rgRefCodeProviderGroup(userId);
		for ( TeamMembers obj :internalLov) {
			if(obj.getCode().equalsIgnoreCase(intProviderPartyId)) {
				internalCode = obj.getDescription();
			}
		}
		}
		return ocsprogrRepository.offExecQuery(status, programId,internalCode,extProviderPartyId,currentCaseload);
	}
	
	
	
	


	@Override
	public List<OffenderSentences> rgOffenderSentencesRecordGroup(OffenderBookings searchBean) {
		List<OffenderSentences> sentenceRecordGrp = new ArrayList<OffenderSentences>();

		List<OffenderSentences> returnList = new ArrayList<OffenderSentences>();
		returnList =	ocdxprogService.rgOffenderSentencesRecordGroup(searchBean.getOffenderBookId().intValue());
		if (returnList != null && !returnList.isEmpty()) {
			List<OffenderBookings> sentences = new ArrayList<OffenderBookings>();
			sentences = ocsprogrRepository.getOffPrgData(searchBean);
			for (OffenderSentences prgObj : returnList) {
				for (OffenderBookings senObj : sentences) {
					if(prgObj.getSentenceSeq() != 0 && (prgObj.getSentenceSeq()== senObj.getSentenceSeq().longValue()))  {

						sentenceRecordGrp.add(prgObj);
					
					}
					
		
		}
			}
		
	}
		return sentenceRecordGrp ;
	

	
}

	
	
}
