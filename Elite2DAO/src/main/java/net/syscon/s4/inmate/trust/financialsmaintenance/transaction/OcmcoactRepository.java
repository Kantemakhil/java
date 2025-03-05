package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

import java.util.List;

import org.springframework.transaction.support.TransactionOperations;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.TxnOpsInvalidAccounts;

/**
 * Interface OcmcoactRepository
 */
public interface OcmcoactRepository {
	List<ReferenceCodes> cgfkAcCodeAccountTypeRecordGroup();

	Integer acCodeUpdateAccountCodes(List<AccountCodes> lstAccountCodes);

	List<TxnOpsInvalidAccounts> cgrichkAccountCodes(TxnOpsInvalidAccounts paramBean);

	List<Caseloads> cgfkchkCsldAcdCsldAcdCsl(Caseloads paramBean);

	List<ReferenceCodes> cgfkchkAcCodeAcPostType(ReferenceCodes paramBean);

	Integer acCodeDeleteAccountCodes(List<AccountCodes> lstAccountCodes);

	List<Caseloads> cgfkCsldAcdCaseloadIdRecordGroup();

	List<AccountCodes> cgfkchkAcCodeAcCodeAcCo(AccountCodes paramBean);

	List<ReferenceCodes> cgfkchkAcCodeAcAcctType(ReferenceCodes paramBean);

	List<TransactionOperations> cgriChkAccountCodes(TransactionOperations paramBean);

	List<AccountCodes> acCodeExecuteQuery(AccountCodes objAccountCodes);

	List<AccountCodes> cgfkAcCodeRecAccountCodeRecordGroup();

	List<ReferenceCodes> cgfkAcCodeSubAccountTypeRecordGroup();

	List<AccountCodes> cgfkAcCodeParentCodeRecordGroup();

	Integer acCodeInsertAccountCodes(List<AccountCodes> lstAccountCodes);

	List<ReferenceCodes> cgfkchkAcCodeAcSubAcct(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkAcCodeTxnPostingTypeRecordGroup();

	List<AccountCodes> caselaodAccountCodes(Integer accountCode, String caseloadId);

	String chkSubAcTypeTxnCur(Integer accountCode, String caseloadId);

	Integer chkDupSubAcTypeCur(String caseloadId, String subAcType);

	String accountCodeValidation(Integer accountCode);

	String caseloadAccountCodeValidation(Integer accountCode);

	Integer transactionOperationsTxnOpeDr(Integer accountCode);

	Integer transactionOperationsTxnOpeCr(Integer accountCode);

	Integer transactionOperationsTxnOpeBankDr(Integer accountCode);

	Integer transactionOperationsTxnOpeBankCr(Integer accountCode);

	Integer transactionOperationsTxnOpeTxnNv(Integer accountCode);

	Integer subAccountViewsAv(Integer accountCode);

	Integer payeeAccountBalancesValid(Integer accountCode);

	Integer interestTransactionTypesValid(Integer accountCode);

	Integer glTransactionsValid(Integer accountCode);

	Integer offenderSubAccountsValid(Integer accountCode);

	Integer caseloadDeductionProfilesValid(Integer accountCode);

	Integer accountCodesAcCodeValid(Integer accountCode);

	Integer lTxnOpExistsCur(Integer accountCode);

	Integer lGlExists(Integer accountCode);

	Integer caseloadAccountSummaries(AccountCodes accountCodes);

	Integer caseloadCurrentAccountsTxns(AccountCodes accountCodes);

	Integer caseloadCurrentAccountsBase(AccountCodes accountCodes);

	Integer caseloadAccountCodesInsert(AccountCodes accountCodes);

}
