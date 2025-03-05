package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;

/**
 * Interface OtdhiremService
 */
public interface OtdhiremService {
	List<Dual> cgwhenNewFormInstance(SysDual paramBean);

	Integer offTxnCommit(OffenderTransactionsCommitBean CommitBean);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffenderTransactions);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

}
