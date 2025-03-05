package net.syscon.s4.inmate.trust.generalledger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.BankChequeRegisters;
import net.syscon.s4.inmate.beans.BankClearReconcilesTmp;
import net.syscon.s4.inmate.beans.BankReconAudits;
import net.syscon.s4.inmate.beans.GlTransactions;

/**
 * Interface OtdbacreRepository
 */
public interface OtdbacreRepository {
	Integer sysPflInsertSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	List<GlTransactions> glTxnExecuteQuery(GlTransactions objGlTransactions);

	List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup(String caseloadId,String caseloadType, String userName);

	AccountCodes cgfkchkGlTxnGlTxnAcCode(AccountCodes paramBean);

	Integer glTxnUpdateGlTransactions(List<GlTransactions> lstGlTransactions);

	Integer sysPflDeleteSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	Integer glTxnInsertGlTransactions(List<GlTransactions> lstGlTransactions);

	Integer bcrTmpUpdateBankClearReconcilesTmp(List<BankClearReconcilesTmp> object);

	List<BankClearReconcilesTmp> bcrTmpExecuteQuery(BankClearReconcilesTmp object);

	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	OmsModules createFormGlobals(OmsModules paramBean);

	List<BankReconAudits> bankRcExecuteQuery(BankReconAudits object);

	BankReconAudits getPmaxDate(String caseloadId, Integer accCode);

	List<BankChequeRegisters> postQuery(Long txnId, Long txnEntrySeq, Long glEntrySeq, String caseloadId);

	Integer compareEffectiveDatec(String effectiveDate, String maxDate);

	List<GlTransactions> getBalancecrdrList(Integer accountCode, String caseloadId);

	BigDecimal getCurrentBalfromCaseloadCurrentTxns(Integer accountCode, String caseloadId);

	String getchcqueFlag(Long txnId, String txnEnterySeq, String glEntrySeq, Date cgnbtBankStatementDate);

	Integer updateGlTransactions(Date cgnbtBankStatementDate, Long txnId);

	Integer updateGlTransactionswithN( Long txnId);

	Integer updateBankReconAudits(BankReconAudits searchBean);

	Integer insertBankReconAudits(BankReconAudits searchBean);

	BigDecimal getTrustBal(Long accountCode, String caseloadId);

	Integer fetchTemp(BankClearReconcilesTmp searchRecord);

}
