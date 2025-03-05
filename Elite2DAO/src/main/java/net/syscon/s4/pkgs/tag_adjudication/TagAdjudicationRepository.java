package net.syscon.s4.pkgs.tag_adjudication;

import java.math.BigDecimal;

import net.syscon.s4.common.beans.OicOffences;

public interface TagAdjudicationRepository {
	Integer getNextSanctionSeq(final Integer pOffenderBookId);

	Integer getAdjudicationFromSanction(final Integer pSanctionSeq, final Integer pOffenderBookId);

	Integer getNextAgencyIncidentId();

	Integer getNextPartySeq(final Integer pAgencyIncidentId);

	Integer getnextchargeseq(final Integer pAgencyIncidentId);

	Integer getnextrepairseq(final Integer pAgencyIncidentId);
	
	OicOffences getoffencedetails(final BigDecimal pOffenceId); 
	
	String getHearingType(Integer hearingId); 

}