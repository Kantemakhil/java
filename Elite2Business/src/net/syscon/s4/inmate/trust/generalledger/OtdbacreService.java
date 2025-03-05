package net.syscon.s4.inmate.trust.generalledger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inmate.beans.BankClearReconcilesTmp;
import net.syscon.s4.inmate.beans.BankClearReconcilesTmpCommitBean;
import net.syscon.s4.inmate.beans.BankReconAudits;
import net.syscon.s4.inmate.beans.BankReconAuditsCommitBean;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;

/**
 * Interface OtdbacreService
 */
public interface OtdbacreService {
	List<Object> cgwhenNewFormInstance();

	List<GlTransactions> glTxnExecuteQuery(GlTransactions objGlTransactions);

	List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup(String caseloadId,String caseloadType, String userName);

	Integer bankRcCommit(BankReconAuditsCommitBean commitBean);

	Integer glTxn2Commit(GlTransactionsCommitBean commitBean);

	Integer bcrTmpCommit(BankClearReconcilesTmpCommitBean commitBean);

	AccountCodes cgfkchkGlTxnGlTxnAcCode(AccountCodes paramBean);

	Integer glTxnCommit(GlTransactionsCommitBean commitBean);

	List<BankClearReconcilesTmp> bcrTmpExecuteQuery(BankClearReconcilesTmp object);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer glTxn3Commit(GlTransactionsCommitBean commitBean);

	Integer glTxn1Commit(GlTransactionsCommitBean commitBean);

	List<BankReconAudits> bankRcExecuteQuery(BankReconAudits object);

	OmsModules createFormGlobals(OmsModules paramBean);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	BankReconAudits getPmaxDate(String caseloadId, Integer accCode);

	Integer compareEffectiveDatec(String effectiveDate, String maxDate);

	String getchcqueFlag(Long txnId, String txnEnterySeq, String glEntrySeq, Long cgnbtBankStatementDate);

	Integer updateGlTransactionswithN(Long txnId);

	Integer updateBankReconAudits(BankReconAudits searchBean);

	Integer insertBankReconAudits(BankReconAudits searchBean);

	BigDecimal getTrustBal(Long accountCode, String caseloadId);

}
