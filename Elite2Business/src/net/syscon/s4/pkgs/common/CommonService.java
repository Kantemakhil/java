package net.syscon.s4.pkgs.common;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.inst.classification.beans.OiiclassClassInquiry;
import net.syscon.s4.inst.legals.au.VCbSentTerms;
import net.syscon.s4.inst.legalscreens.sentenceadministration.beans.VCbCustodyPeriod;

public interface CommonService {

	 String imageMarkExists(final BigDecimal pOffenderBookId, final String pImageObjectType,
			final String pMarkType, final String pBodyPart, final BigDecimal pObjectSeq);

	 Integer createAgencyTab();

	 void unlockFormModule(final String caseloadId, final String currentUser);

	 void lockFormModule(final String caseloadId, final String userName);

	 void processHold(final Integer txnId, final String caseloadId, final Long offenderId, final String txnType,
			final Integer holdDays, final String subAccountType, final Double txnEntryAmount, final String txnEntryDesc,
			final String txRefNo, final Integer txnnum, final Integer holdNumbers, final String userName);

	 List<OiiclassClassInquiry> oiiclassClassInquiry(final OiiclassClassInquiry objSearchDao);

	 String changeWorkingCaseload(final String pCaseLoadId, String userName);
	 
	Integer otmoncoaGenAccountCodes(final String caseloadId, final Integer accountCode, final String createUserId);
	
	List<VCbSentTerms> procVCbSentTerms(final Long pOffenderId, final Long pOffBookId, final Date pEffectiveDate);

	List<VCbCustodyPeriod> procVCbCustodyPeriod(final Long pOffenderId, final Long pOffBookId,
			final Date pEffectiveDate);
	
	String billStatementExists(final String billId);
	
	Integer insIntoBedAssHist(BedAssignmentHistories bean);
	
	void insertAgencyData();
	
	Boolean checkCallFormAccess(String userId,String role, String callForm);
	
	Boolean checkOffenderSpecificScreenAccess(String userId,String role);
	
	
}
