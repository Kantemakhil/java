package net.syscon.s4.pkgs.tag_adjudication.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.pkgs.oms_miscellaneous.OmsMiscellaneousService;
import net.syscon.s4.pkgs.tag_adjudication.TagAdjudicationRepository;
import net.syscon.s4.pkgs.tag_adjudication.TagAdjudicationService;

@Service
public class TagAdjudicationServiceImpl extends BaseBusiness implements TagAdjudicationService {

	@Autowired
	private TagAdjudicationRepository tagAdjudicationRepository;
	
	@Autowired
	private OmsMiscellaneousService omsMiscellaneousService;

	@Override
	public Integer getNextSanctionSeq(final Integer pOffenderBookId) {
		return tagAdjudicationRepository.getNextSanctionSeq(pOffenderBookId);
	}

	@Override
	public Integer getAdjudicationFromSanction(final Integer pSanctionSeq, final Integer pOffenderBookId) {
		return tagAdjudicationRepository.getAdjudicationFromSanction(pSanctionSeq, pOffenderBookId);
	}

	@Override
	public Integer getNextAgencyIncidentId() {
		return tagAdjudicationRepository.getNextAgencyIncidentId();
	}

	
	public Integer getNextPartySeq(final Integer pAgencyIncidentId) {
		return tagAdjudicationRepository.getNextPartySeq(pAgencyIncidentId);
	}

	
	public Integer getnextchargeseq(final Integer pAgencyIncidentId) {
		return tagAdjudicationRepository.getnextchargeseq(pAgencyIncidentId);
	}
	
	
	
	public Integer getnextrepairseq(final Integer pAgencyIncidentId) {
		return tagAdjudicationRepository.getnextrepairseq(pAgencyIncidentId);
	}
	
	
	@Override
	public OicOffences getoffencedetails(BigDecimal pOffenceId) {
		
		return tagAdjudicationRepository.getoffencedetails(pOffenceId);
	}
	
	@Override
	public String getHearingType(Integer hearingId) {
		return tagAdjudicationRepository.getHearingType(hearingId);
	}
}