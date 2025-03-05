package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.OffenderCases;

public interface OffenderCasesT3Repository {

	Integer insertOffenderCaseStatuses(OffenderCases oldOffenderCases, OffenderCases newOffenderCases);

	OffenderCases getOffenderCases(Long caseId);
}
