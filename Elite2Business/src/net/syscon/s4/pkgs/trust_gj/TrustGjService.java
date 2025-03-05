package net.syscon.s4.pkgs.trust_gj;

import java.math.BigDecimal;
import java.util.Date;

public interface TrustGjService {
	void reopenClosedPeriod(final String caseloadId, final Date txnEntryDate, final String userName);

	Long getAccountPeriod(final Date date);

	Integer getAllowedBackDatedPeriod(final String caseloadId);

	Integer getDefinedBackDatedPeriod();

	BigDecimal getLastClosedPeriod(final String caseloadId, final Integer pAcccountPeriodId);
	
	BigDecimal getCurrentBalance(String caseLoadId, Integer accountCode,String userName);
}