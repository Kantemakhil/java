package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.syscon.s4.inst.legals.OculcaseRepository;
import net.syscon.s4.inst.legals.OculcaseService;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;



@Service
public class OculcaseServiceImpl implements OculcaseService{
 
	@Autowired(required=true)
	OculcaseRepository oculcaseRepository;
	
	@Override
	public List<CourtCases> populateLinkCase(CourtCases courtCases) {
		List<CourtCases> resultList = new ArrayList<CourtCases>();
		resultList = oculcaseRepository.populateLinkCase(courtCases);
		return resultList;
	}
	
	@Override
	public List<CourtCases> populateLinkLovType(CourtCases courtCases) {
		List<CourtCases> resultList = new ArrayList<CourtCases>();
		resultList = oculcaseRepository.populateLinkLovType(courtCases);
		return resultList;
	}


	@Override
	public List<CourtEvent> populateSelectHearing(CourtEvent courtEvent) {
		List<CourtEvent> resultList = new ArrayList<CourtEvent>();
		resultList = oculcaseRepository.populateSelectHearing(courtEvent);
		return resultList;
	}

	@Override
	public List<CourtEvent> linkCase(CourtEvent courtEvent) {
		List<CourtEvent> resultList = new ArrayList<CourtEvent>();
		resultList = oculcaseRepository.linkCase(courtEvent);
		return resultList;
	}

	@Override
	public List<CourtEvent> unLinkCase(CourtEvent courtEvent) {
		List<CourtEvent> resultList = new ArrayList<CourtEvent>();
		resultList = oculcaseRepository.unLinkCase(courtEvent);
		return resultList;
	}

	
	public boolean chkSentences(Integer caseId, Integer offenderBookId){
		return oculcaseRepository.chkSentences(caseId,offenderBookId);
		
	}

}
