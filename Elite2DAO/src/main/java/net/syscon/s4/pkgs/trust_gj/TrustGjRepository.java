package net.syscon.s4.pkgs.trust_gj;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TrustGjRepository {
	Long accountPeriodCur(final Date date);

	BigDecimal trustgtSelect(final String caseLoadId, final Integer pAcccountPeriodId);

	BigDecimal trustgtSelectMax(final String caseLoadId);

	String lockRecordCur(final String caseLoadId, final Date txnEntryDate);

	Integer caseloadAccountPeriods(final String caseLoadId, final Date txnEntryDate, final String userName);
	
	BigDecimal getCurrentBalance(final String caseLoadId, Integer accountCode, String userName);
}