package net.syscon.s4.cf.offendertransactions;

import java.util.List;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.im.beans.OffenderTransactions;

/**
 * Interface OcdcrefuService
 */
public interface OcdcrefuService {
	OffenderTransactions offTxnExecuteQuery(OffenderTransactions objOffenderTransactions);

	OffenderTransactions offTxnCommit(OffenderTransactions commitBean);

	OffenderTransactions offTxnValidateQuery(OffenderTransactions searchBean);

}
