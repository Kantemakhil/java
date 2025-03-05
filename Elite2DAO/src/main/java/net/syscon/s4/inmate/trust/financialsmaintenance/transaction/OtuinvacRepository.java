package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

import java.util.List;

import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.TxnOpsInvalidAccounts;

/**
 * Interface OtuinvacService
 */
public interface OtuinvacRepository {
	//	Integer txnInvCommit(TxnOpsInvalidAccountsCommitBean commitBean);

	List<AccountCodes> cgfkTxnInvInvalidAccountCRecordGroup();

	List<TxnOpsInvalidAccounts> txnInvExecuteQuery(TxnOpsInvalidAccounts searchBean);
	
	Integer txnInvInsertTxnOpsInvalidAccounts(List<TxnOpsInvalidAccounts> lstOffenderAlerts);
	
	Integer txnInvUpdateTxnOpsInvalidAccounts(List<TxnOpsInvalidAccounts> lstOffenderAlerts);
	
	Integer txnInvDeleteTxnOpsInvalidAccounts(List<TxnOpsInvalidAccounts> lstOffenderAlerts);

}
