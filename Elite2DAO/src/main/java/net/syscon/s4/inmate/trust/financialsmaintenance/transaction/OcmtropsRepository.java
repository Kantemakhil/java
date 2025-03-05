package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.TransactionOperation;

/**
 * Interface OcmtropsRepository
 */
public interface OcmtropsRepository {
	AccountCodes cgfkchkTxnOperTxnOperCr(AccountCodes paramBean);

	Integer txnOperDeleteTransactionOperations(List<TransactionOperation> lstTransactionOperations);

	AccountCodes cgfkchkTxnOperTxnOper(AccountCodes paramBean);

	AccountCodes cgfkchkTxnOperTxnOperDr(AccountCodes paramBean);

	List<AccountCodes> cgfkTxnOperBankCrAccountRecordGroup(String userName);

	List<AccountCodes> cgfkTxnOperBankDrAccountRecordGroup(String userName);

	List<AccountCodes> cgfkTxnOperCrAccountCodeRecordGroup(String userName);

	Integer txnOperInsertTransactionOperations(List<TransactionOperation> lstTransactionOperations);

	List<ReferenceCodes> cgfkTxnOperTxnOperationTyRecordGroup();

	List<AccountCodes> cgfkTxnOperDrAccountCodeRecordGroup(String userName);

	OmsModules cgfkchkTxnOperTxnOperOms(OmsModules paramBean);

	Caseloads cgfkchkTxnOperTxnOperCsl(Caseloads paramBean);

	AccountCodes cgfkchkTxnOperTxnOperBnk(AccountCodes paramBean);

//	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<TransactionTypes> cgfkTxnOperTxnTypeRecordGroup(String userName);

	List<Caseloads> cgfkTxnOperCaseloadIdRecordGroup(String userName);

	List<OmsModules> cgfkTxnOperModuleNameRecordGroup();

	List<TransactionOperation> txnOperExecuteQuery(TransactionOperation objTransactionOperations);

	Integer txnOperUpdateTransactionOperations(List<TransactionOperation> lstTransactionOperations);

	ReferenceCodes cgfkchkTxnOperTxnOperRef(ReferenceCodes paramBean);

	String txnTypeValidation(String txnType, String moduleName, String caseloadId, Long txnOperationSeq);

}
