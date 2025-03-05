package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.OffenderCases;

public interface OffenderCasesT1Repository {

	OffenderCases getOffenderCases(Long caseId);

	String getProfileValue();

	Integer updateOffenderDeductions(String caseInfoNumber, Long caseId);

	Integer updateOffenderPaymentPlans(String caseInfoNumber, Long caseId);
	
}
