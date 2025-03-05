package net.syscon.s4.inmate.trust.statements;

import java.util.List;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.im.beans.OffenderTransactions;

/**
 * Interface OturnumbService
 */

public interface OturnumbService {

	Integer offTxnCommit(OffenderTransactionsCommitBean commitBean);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffenderTransactions);

}
