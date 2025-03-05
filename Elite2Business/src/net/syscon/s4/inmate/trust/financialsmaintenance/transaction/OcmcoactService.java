package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

import java.util.List;

import org.springframework.transaction.support.TransactionOperations;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.AccountCodesCommitBean;

/**
 * Interface OcmcoactService
 */
public interface OcmcoactService {
	List<ReferenceCodes> cgfkAcCodeAccountTypeRecordGroup();

	List<ReferenceCodes> cgfkchkAcCodeAcSubAcct(ReferenceCodes paramBean);

	String acCodeCommit(AccountCodesCommitBean CommitBean);

	List<Caseloads> cgfkCsldAcdCaseloadIdRecordGroup();

	List<AccountCodes> cgfkchkAcCodeAcCodeAcCo(AccountCodes paramBean);

	List<ReferenceCodes> cgfkchkAcCodeAcAcctType(ReferenceCodes paramBean);

	List<Caseloads> cgfkchkCsldAcdCsldAcdCsl(Caseloads paramBean);

	List<ReferenceCodes> cgfkchkAcCodeAcPostType(ReferenceCodes paramBean);

	List<AccountCodes> acCodeExecuteQuery(AccountCodes objAccountCodes);

	List<TransactionOperations> cgriChkAccountCodes(TransactionOperations paramBean);

	List<AccountCodes> cgfkAcCodeRecAccountCodeRecordGroup();

	List<ReferenceCodes> cgfkAcCodeSubAccountTypeRecordGroup();

	List<AccountCodes> cgfkAcCodeParentCodeRecordGroup();

	List<ReferenceCodes> cgfkAcCodeTxnPostingTypeRecordGroup();

	List<AccountCodes> caselaodAccountCodes(Integer accountCode, String caseloadId);

	String chkSubAcTypeTxnCur(Integer accountCode, String caseloadId);

	Integer chkDupSubAcTypeCur(String caseloadId, String subAcType);

	String accountCodeValidation(Integer accountCode);

	String caseloadAccountCodeValidation(Integer accountCode);

	Integer txnTypeOnCheckDeleteMaster(Integer accountCode);

}
