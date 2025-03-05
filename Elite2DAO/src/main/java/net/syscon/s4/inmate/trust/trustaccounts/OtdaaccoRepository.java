package net.syscon.s4.inmate.trust.trustaccounts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.GlTransactions;

/**
 * Interface OtdaaccoRepository
 */
public interface OtdaaccoRepository {
	TransactionTypes cgfkchkGlTxn1GlTxnTxnTy(TransactionTypes paramBean);

	Integer glTxn1InsertGlTransactions(List<GlTransactions> lstGlTransactions);

	List<ReferenceCodes> cgfkGlTxnAccountCodeRecordGroup(String txnType, String caseLoadId, String caseLoadType);

	List<ReferenceCodes> cgfkGlTxn1AccountCodeRecordGroup();

	Integer systemProfilesDeleteSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	AccountCodes cgfkchkGlTxnGlTxnAcCode(AccountCodes paramBean);

	List<ReferenceCodes> cgfkGlTxn1TxnTypeRecordGroup(String caseLoadId, String caseLoadType);

	AccountCodes cgfkchkGlTxn1GlTxnAcCod(AccountCodes paramBean);

	List<SystemProfiles> systemProfilesExecuteQuery(SystemProfiles objSystemProfiles);

	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	List<GlTransactions> cgrichkGlTransactions(GlTransactions paramBean);

	Integer systemProfilesInsertSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	Integer systemProfilesUpdateSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	List<GlTransactions> glTxn1ExecuteQuery(GlTransactions objGlTransactions);

	BigDecimal overDraftAmount(String caseloadId, BigDecimal offenderId, BigDecimal accountCodeOne,
			BigDecimal txnEntryAmount);

	String overDraftAmountTotal(String caseloadId, BigDecimal offenderId, BigDecimal accountCodeOne);

	String transactionTypesY(String txnType, String caseloadId);

	String subAccountType(BigDecimal accountCodeOne);

	BigDecimal maxOffenderBookId(String caseloadId, BigDecimal offenderId);

	Integer txnIdNextValData();

	Integer chkOverdrawnData(String caseloadId, BigDecimal offenderId, String subAccountType, BigDecimal txnEntryAmount,
			String txnType, Integer txnEntrySeq, String string, String string2, Integer txnId,
			BigDecimal offenderBookId, Integer txnFee);

	AccountCodes drAccountCodesData(BigDecimal accountCodeOne);

	AccountCodes crAccountCodesData(BigDecimal accountCodeTwo);

	Integer insertIntoOffenderTransaction(Integer pTxnNum, Integer pTxnEntrySeq, String caseloadId,
			BigDecimal offenderId, BigDecimal drOffBookId, String string, String txnType, String adjustDesc,
			BigDecimal txnEntryAmount, Date transDate, String pDrSubAccountType, String reconClearFlag);

	Integer insertGlTransNew(String string, BigDecimal accountCodeOne, String pDrAccntPosting, String caseloadId,
			String txnType, BigDecimal txnEntryAmount, Integer pTxnNum, Date transDate, String adjustDesc,
			Integer pTxnEntrySeq, Integer pGlSeq, BigDecimal offenderId, BigDecimal drOffBookId,
			String txnReferenceNumber);

	Integer financialDoDuctionsFinancial(String caseloadId, BigDecimal nbtOffenderId, BigDecimal crOffBookId,
			String txnType, Integer pTxnNum, Date transDate, String pCrSubAccountType, String string,
			BigDecimal txnEntryAmount, BigDecimal txnEntryAmount2, Integer pTxnEntrySeq);

	String getAcAndSetIndDate(BigDecimal offenderId, String caseloadId);

	String checkNavigation(BigDecimal accountCode);

	String chkOffAcnt(BigDecimal accountCode);

	BigDecimal findRootOffenderId(String offenderIdDisplay);

	String findOffenderIdDisplay(Long offenderId);

	String currentCaseloadValidation(BigDecimal bigDecimal, String caseloadId, BigDecimal acccountCodeOnde);

	String closedAccountValidation(BigDecimal bigDecimal, String caseloadId);

	String drAccountCode(BigDecimal drAccountCode);

	Integer drAccountCodeCaseloadId(String caseloadId, BigDecimal drAccountCode);

	String crAccountCode(BigDecimal drAccountCode);

	String whenCheckBoxChecked(String caseloadId, BigDecimal offfenderId, String txnType);

}
