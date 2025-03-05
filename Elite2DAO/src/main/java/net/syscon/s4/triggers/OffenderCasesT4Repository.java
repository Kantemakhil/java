package net.syscon.s4.triggers;

import java.math.BigDecimal;

import net.syscon.s4.im.beans.OffenderCases;

public interface OffenderCasesT4Repository {

//	OffenderCases getOffenderCases(Long caseId);

	BigDecimal getLidsCaseNumber(Long offenderBookId);
}
