package net.syscon.s4.cf.offendertransactions;

import java.util.List;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.OffenderTransactions;

/**
 * Interface OcuotrahService
 */
public interface OcuotrahService {
	Integer offTxnCommit(OffenderTransactionsCommitBean commitBean);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffenderTransactions);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

}
