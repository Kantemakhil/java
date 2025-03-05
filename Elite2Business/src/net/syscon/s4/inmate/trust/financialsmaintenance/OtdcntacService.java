package net.syscon.s4.inmate.trust.financialsmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.OffenderTransactions;

/**
 * Interface OtdcntacService
 */
public interface OtdcntacService {
	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions object);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	OffenderTransactions offTxnCommit(OffenderTransactionsCommitBean commitBean);

	String checkAccountSatus(OffenderTransactions offTrans);

	String getGroupPrivilege(String userName);

}
