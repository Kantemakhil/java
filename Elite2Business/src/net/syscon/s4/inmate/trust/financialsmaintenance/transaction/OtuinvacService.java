package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

import java.util.List;

import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.TxnOpsInvalidAccounts;
import net.syscon.s4.im.beans.TxnOpsInvalidAccountsCommitBean;

/**
 * Interface OtuinvacService
 */
public interface OtuinvacService {
	Integer txnInvCommit(TxnOpsInvalidAccountsCommitBean commitBean);

	List<AccountCodes> cgfkTxnInvInvalidAccountCRecordGroup();

	List<TxnOpsInvalidAccounts> txnInvExecuteQuery(TxnOpsInvalidAccounts searchBean);

}
