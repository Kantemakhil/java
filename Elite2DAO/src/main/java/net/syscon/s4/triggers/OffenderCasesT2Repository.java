package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.OffenderCases;

public interface OffenderCasesT2Repository {
	String caseIdentifierExist(String caseInfoPrefix, String caseInfoNumber);

	OffenderCases getOffenderCases(Long caseId);
}
