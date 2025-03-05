package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;
import java.util.List;

import net.syscon.s4.inmate.beans.AccountPeriods;
import net.syscon.s4.inmate.beans.AccountPeriodsCommitBean;
/**
 * Interface OtmacprdService 
 */
public interface OtmacprdService  {
	String acPrdCommit(AccountPeriodsCommitBean commitBean) ;

	List<AccountPeriods> acPrdExecuteQuery(AccountPeriods objAccountPeriods) ;

	List<AccountPeriods> overlapdates();

	Integer duplicateAccountperiodId(Long accountPeriodId);

	Integer duplicateOverlapDate(String overlapDate);

}
