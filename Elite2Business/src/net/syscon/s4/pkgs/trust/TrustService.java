package net.syscon.s4.pkgs.trust;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;
import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.GlTransactions;

public interface TrustService {

	BigDecimal calculateBeneficiariesTotal(final BigDecimal corporateId);

	Map<String, Object> chkAccountStatus(final String caseLoadId, final BigDecimal offenderId);

	String chkFreezeDisbursements(final ChkFreezeDisbursements chkFreeze);

	String getCaseloadType(final String caseLoadId);

	String getLowHighFlag();

	Map<String ,Object> getTransactionFee(final Long offId, final String csldId, final Long dedId,
			final String disbuType, final Double txnAmount, final String lowHighFlag, final Integer txnFeeAmt);

	void insertGlTransNew(final GlTransactions glTrans);

	void insertIntoChequeData(final BankChequeData bankCheqData, String userName);

	Integer updateOffenderBalance(final OffenderTransactions offTrans, final String userName);

	Map<String, Object> getSubActType(final OtddisbuProcessTransactionsBean proTxn);

	Integer processGlTransNew(final String pCsldId, final String pTransType, final Object pOperationType,
			final Double pTransAmount, final Integer pTransNumber, final Date pTransDate, final String pTransDesc,
			final Integer pTransSeq, final String pModuleName, final Integer pOffId, final Object pOffBookId,
			final Object pSubActTypeDr, final String pSubActTypeCr, final Object pPayeePersId,
			final Object pPayeeCorpId, final Object pPayeeNameText, final Integer pGlSqnc, Object pOffDedId, String userId);

	Map<String, Object> chkOverdrawn(final String pCsldId, final BigDecimal pOffId, final String pSubActType,
			final BigDecimal transAmount, final String txntype, final Long seqNo, final String checkInd,
			final String modName, final BigDecimal pTxnId, final BigDecimal pOffBid, final Integer pTxnFee,
			final String pSetupCaseload, final String userName);

	Map<String, Object> getOffenderSubBalance(final String pCsldId, final BigDecimal pOffId, final String pSubActType,
			final BigDecimal pMinbal, final BigDecimal pTrstcode, final String pLockFlag, final String txntype,
			final String modName, final String pSetupCsldId, final String userId);

	Map<String, Object> doLegalLoan(final String caseload, final BigDecimal offenderid, final BigDecimal offbookid,
			final BigDecimal nmbrTrnsctn, final Date todayDate, final Double overdrawn, final String txntype,
			final String obligationtype, final String formName, final Double subBalance, final Long seqNo,
			final String pSetupCaseload, final String userName);

	Map<String, Object> chkOffenderLimit(final String caseloadid, final BigDecimal offenderid, final String limittype,
			final Double amntTrans, final BigDecimal maxLimit, final Double subBalance);

	BigDecimal getPreviousLoanAmount(final Date fromDate, final Date toDate, final String dedType,
			final BigDecimal pOffenderId, final String caseloadId);

	Map<String, Object> createOffenderDeductions(final String caseload, final BigDecimal offenderid,
			final String obligationtype, final BigDecimal payeeperson, final BigDecimal payeecorp,
			final String payeename, final String infonum, final Long offdedid, final String userName);

	void insrtIntoOffenderTrans(final BigDecimal pTransNumber, final Long pTransSeq, final String pCsldId,
			final BigDecimal pOffId, final BigDecimal pOffBookId, final String pTransPostType, final String pTransType,
			final String pTransDesc, final Double pTransAmount, final Date pTransDate, final String pSubActType,
			final String pDeductionFlag, final Double pPreDedAmount, final String pDeductionType,
			final String pPayeeCode, final BigDecimal pPayeeCorpId, final BigDecimal pPayeePersonId,
			final String pPayeeNameText, final String pInfoNumber, final String pSlipPrintFlag,
			final String pAllowOverdrawn, final String userName);

	Integer processGlTransNew(final String pSetupCsldId, final String pPostingCsldId, final String pTransType,
			final String pOperationType, final Double pTransAmount, final BigDecimal pTransNumber,
			final Date pTransDate, final String pTransDesc, final Long pTransSeq, final String pModuleName,
			final BigDecimal pOffId, final BigDecimal pOffBookId, final String pSubActTypeDr,
			final String pSubActTypeCr, final BigDecimal pPayeePersId, final BigDecimal pPayeeCorpId,
			final String pPayeeNameText, final Integer pGlSqnc, final Long pOffDedId);

	String trustCommunityCsld(final String caseloadId);

	Integer insertIntoOffenderTrans(final OffenderTransactions obj);
	
	Long processTransactionFee(final OffenderTransactions obj,String moduleName);

	BigDecimal getOffenderSubBal(String caseloadId, String csldType, Long pOffId, Integer accountCode);

	BigDecimal getSubBal(String pCsldId, Long pOffId, String deductionType);

	String chkMinimumTrustBalance(String pCsldId, Long pOffId, String pTransType, String deductionType,
			BigDecimal amntToDeduct);
}
