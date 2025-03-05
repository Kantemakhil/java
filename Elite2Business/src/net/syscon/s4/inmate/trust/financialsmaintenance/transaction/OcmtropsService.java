package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.im.beans.TransactionOperationCommitBean;

/**
 * Interface OcmtropsService
 */
public interface OcmtropsService {
	AccountCodes cgfkchkTxnOperTxnOperCr(AccountCodes paramBean);

	List<Object> cgwhenNewFormInstance();

	AccountCodes cgfkchkTxnOperTxnOper(AccountCodes paramBean);

	List<AccountCodes> cgfkTxnOperBankCrAccountRecordGroup(String userName);

	AccountCodes cgfkchkTxnOperTxnOperBnk(AccountCodes paramBean);

	List<AccountCodes> cgfkTxnOperBankDrAccountRecordGroup(String userName);

	List<AccountCodes> cgfkTxnOperCrAccountCodeRecordGroup(String userName);

	ReferenceCodes cgfkchkTxnOperTxnOperRef(ReferenceCodes paramBean);

	// ReferenceCodes cgfkchkTxnOperTxnOperTxn(); // check

	List<ReferenceCodes> cgfkTxnOperTxnOperationTyRecordGroup();

	List<AccountCodes> cgfkTxnOperDrAccountCodeRecordGroup(String userName);

	AccountCodes cgfkchkTxnOperTxnOperDr(AccountCodes paramBean);

	Caseloads cgfkchkTxnOperTxnOperCsl(Caseloads paramBean);

	List<TransactionTypes> cgfkTxnOperTxnTypeRecordGroup(String userName);

	Integer txnOperCommit(TransactionOperationCommitBean CommitBean);

	OmsModules cgfkchkTxnOperTxnOperOms(OmsModules paramBean);

	List<Caseloads> cgfkTxnOperCaseloadIdRecordGroup(String userName);

	List<OmsModules> cgfkTxnOperModuleNameRecordGroup();

	List<TransactionOperation> txnOperExecuteQuery(TransactionOperation objTransactionOperations);

	Integer txnOperInsertTransactionOperations(List<TransactionOperation> lstOffenderAlerts);

	Integer txnOperUpdateTransactionOperations(List<TransactionOperation> lstOffenderAlerts);

	Integer txnOperDeleteTransactionOperations(List<TransactionOperation> lstOffenderAlerts);

	String txnTypeValidation(String txnType, String moduleName, String caseloadId, Long txnOperationSeq);
}
