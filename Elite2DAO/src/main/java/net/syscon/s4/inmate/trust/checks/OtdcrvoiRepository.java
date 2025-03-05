package net.syscon.s4.inmate.trust.checks;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.BankChequeRegisters;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;

/**
 * Interface OtdcrvoiRepository
 */
public interface OtdcrvoiRepository {
	List<ReferenceCodes> cgfkBankCrAccountCodeRecordGroup(String caseloadId,String userName);

	List<BankChequeRegisters> bankCrExecuteQuery(BankChequeRegisters obj);

	ReferenceCodes cgfkchkBankCrBankCrRef(ReferenceCodes paramBean);

	Integer bankCrUpdateBankChequeRegisters(List<BankChequeRegisters> list);

	Object cgfkchkBankCrBankCrGlAc(CaseloadCurrentAccounts paramBean);

	BankChequeData bankCrOnCheckDeleteMaster(BankChequeData paramBean);

	List<ReferenceCodes> cgfkBankCrChequeStatusRecordGroup();

	List<BankChequeData> bnkCdExecuteQuery(BankChequeData objBankChequeData);

	String chkAccountStatus(String caseloadId, BigDecimal bigDecimal);

	String whenValidateGetCheckTxnType(BigDecimal txnId);

	String whenValidateGetCheckClearTxn(BigDecimal txnId);

	List<String> verifyTxnTypeCount(BigDecimal txnId);

	List<OffenderTransactions> getCOneOffenderDetail(BigDecimal txnId, String chequeFlag, String payeeNameText);

	void updateOffenderTrustAccountsReOpen(Long offenderId, String caseloadId);

	void trustInsertIntoOffenderTrans(BigDecimal pTransNumber, Integer pTransSeq, String pCsldId, Long pOffId,
			Long pOffBookId, String pTransPostType, String pTransType, String pTransDesc, Double pTransAmount,
			Date pTransDate, String pSubActType, String pDeductionFlag, String pPreDedAmount, String pDeductionType,
			String pPayeeCorpId, String pPayeePersonId, String pInfoNumber, String pSlipPrintFlag,
			String pAllowOverdrawn);

	BigDecimal genTrustTrans(String seqId);

	void processGlTransNew(String pCsldId, String pTransType, String pOperationType, Double pTransAmount,
			BigDecimal rTxnNum, Date pTransDate, String pTransDesc, Integer pTransSeq, String pModuleName, Long pOffId,
			Long pOffBookId, String pSubActTypeDr, String pSubActTypeCr, String string, String string2,
			String pPayeeNameText, Integer pGlSqnc, BigDecimal pOffDedId);

	void updateGlTransactionReversedFlag(BigDecimal txnId, Integer txnEntrySeq, Long glEntrySeq);

	void insertGlTransNew(String pPostType, Integer pAccountCode, String pAcntPosting, String pCsldId,
			String pTransType, Double pTransAmount, BigDecimal pTransNumber, Date pTransDate, String pTransDesc,
			Integer pTransSeq, Integer pGlSqnc, Long pOffId, Long pOffBookId, String pInfoNumber, String pPayeePersonId,
			String pPayeeCorporateId, String pPayeeNameText, String pRevrTxnId, String pRevrTxnEntrySeq,
			String pRevrGlEntrySeq, String pTxnRefNumber, String pOffDedId);

	void updateGlTransactionReversedFlagAndValues(String vTxnReversedFlag, BigDecimal reversedTxnId,
			Integer reversedTxnEntrySeq, Long reversedGlEntrySeq, BigDecimal txnId, Integer txnEntrySeq,
			Integer glEntrySeq);

	List<OffenderTransactions> cOffTxnOffenderDetail(BigDecimal txnId, String chequeFlag, String payeeNameText);

	void insertIntoOffenderTransactionsViaQuery(BigDecimal txnId, Integer txnEntrySeq, String txnPostingType,
			BigDecimal oldTxnId, Integer oldTxnEntrySeq);

	void otdcrvoiUpdateOffenderTransactionTxnAdjecenmentFlag(BigDecimal txnId, Integer txnEntrySeq);

	void updateOffenderBalance(String pCsldId, Long pOffId, String pTransPostType, Date pTransDate,
			BigDecimal pTransNumber, String pTransType, Double pTransAmount, String pSubActType,
			String pAllowOverdrawn);

	void deductionGetAcAndSetIndDate(Long offenderId, String caseloadId);

	List<BeneficiaryTransactions> cChequeBeneficiaries(BigDecimal pChequeTxnId);

	void updateBeneficiaryTransactions(Long txnId, BigDecimal personId, BigDecimal corporateId,
			BigDecimal offenderDeductionId);

	List<BigDecimal> cGetAccountCode(Long vTxnId, BigDecimal vPerId, BigDecimal vCorpId, BigDecimal vDedId);

	BankChequeData getOffenderId(Long txnId);

}
