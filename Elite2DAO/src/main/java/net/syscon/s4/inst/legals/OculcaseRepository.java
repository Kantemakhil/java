package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;


public interface OculcaseRepository {
	List<CourtCases> populateLinkCase(CourtCases courtCases);
	
	List<CourtCases> populateLinkLovType(CourtCases courtCases);
	
	List<CourtEvent> populateSelectHearing(CourtEvent courtEvent);

	List<CourtEvent> linkCase(CourtEvent courtEvent);

	List<CourtEvent> unLinkCase(CourtEvent courtEvent);

	boolean chkSentences(Integer caseId, Integer offenderBookId);


}
