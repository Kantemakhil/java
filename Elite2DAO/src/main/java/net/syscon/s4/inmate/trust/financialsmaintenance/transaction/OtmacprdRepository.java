package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

import java.util.List;

import net.syscon.s4.inmate.beans.AccountPeriods;
import net.syscon.s4.inmate.beans.GlTransactions;

/**
 * Interface OtmacprdRepository
 */
public interface OtmacprdRepository {
	Integer acPrdUpdateAccountPeriods(List<AccountPeriods> lstAccountPeriods);

	Integer acPrdDeleteAccountPeriods(List<AccountPeriods> lstAccountPeriods);

	List<AccountPeriods> acPrdExecuteQuery(AccountPeriods objAccountPeriods);

	List<GlTransactions> cgrichkAccountPeriods(GlTransactions paramBean);

	Integer acPrdInsertAccountPeriods(List<AccountPeriods> lstAccountPeriods);

	List<AccountPeriods> overlapdates();

	Integer duplicateAccountperiodId(Long accountPeriodId);

	Integer duplicateOverlapDate(String overlapDate);

}
