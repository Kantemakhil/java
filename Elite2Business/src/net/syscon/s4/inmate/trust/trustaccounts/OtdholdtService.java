package net.syscon.s4.inmate.trust.trustaccounts;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.Offenders;
/**
 * Interface OtdholdtService 
 */
public interface OtdholdtService  {
	List<Object> cgwhenNewFormInstance();

	ReferenceCodes cgfkchkOffTxnOffTxnRef(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkOffTxnSubAccountTypeRecordGroup(String caseLoadId);

	BigDecimal offTxnExecuteQuery(Offenders objOffTrans);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	Map<String,Object> offTxnCommit(OffenderTransactionsCommitBean commitBean);

	List<OffenderTransactions> offBkgOnCheckDeleteMaster(OffenderTransactions paramBean);

	 BigDecimal getExistingHoldAmount(Offenders paramBean);
	 
	 BigDecimal getSubAccountBalance(Offenders paramBean);
}
