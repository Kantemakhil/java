package net.syscon.s4.pkgs.financial;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanTransaction;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;

public interface FinancialService {

	String updateOffenderBeneficiaries(final BigDecimal deductionId, final BigDecimal payeePersonId,
			final BigDecimal payeeCorporateId, final BigDecimal txnEntryAmount, final String userName);

	Integer updatePaymentPlanSchedules(final PaymentPlanTransaction pPlan, Long pRecursiveAmt, final String userName);

	void recordReceiptDisbursement(final PaymentPlanTransaction plan, final String userName);

	String reverseBeneficiaryTrans(final long txnId, final long txnEntrySeq, final long glEntrySeq, final Long offTxnId,
			final long glSeq, final String txnType, final String txnEntryDesc);

	Integer insertIntoBenTransactions(final Integer lvTxnId, final Integer lvTxnSeq, final Long lvDeductionId,
			final BigDecimal personId, final BigDecimal corporateId, final BigDecimal unknownBenId,
			final String lvCrAccCode, final String lvTxnDesc, final String caseloadId, final Long beneficiaryId);
	
	Map<String, Object> doDeductionsFinancial(String pCsldId, Long pOffId, Long pOffBookId, String pTransType, Long pTransNumber, Date pTransDate, String pSubActType, 
			String pDedFlag, BigDecimal pReceiptAmount, Long pShadowId, Long pInfoNumber, Integer txnSeq, String userId);

	String getDedPeriod(BigDecimal pTotalMax, BigDecimal pMonthlyMax, BigDecimal pRecursiveAmt, String pUnlimDedFlag);
	
	void lockOffenderDed(BigDecimal pOffId, String pCsldId);
	
	String getCfppFlag(BigDecimal pTransNumber);
	
	BigDecimal subbalMinusMinbal(BigDecimal pOffId, String pCsldId, String pAgyLocId, String modName, String txnType);
	
	String getRecOverpayFlag();
	
	OffenderDeductions getActiveCollectionFee(String pCsldId, BigDecimal pOffId, BigDecimal pOffDedId, String dedType, Date effectiveDate);
	
	Date getRecursiveEndDate(BigDecimal pOffId, Long pOffDedId,String userId);

	BigDecimal getRecursiveAmountOwing( Date pDedEffDate, Date pDedEndDate, Date pSysDate, Long offDedId, Long pPriority, Long pBeneficiaryId, Long pMaxRecursiveAmt, Long pMonBeneficiaryAmt, String pOverpayFlag);
	
	String getCfppTxnDesc(Long  pTransNumber, BigDecimal amntOwing );
	
	void doBeneficiaryTransactions(String pCaseloadId, Long pOffId, Long pOffDedId, Long pAmntToDeduct, Long pTxnId, Long pTxnEntrySeq, Long pGlEntrySeq,Integer pAccountCode, String pTxnPostType,String pTxnType, String pTxnEntryDesc, String pOverrideFlag, Long pMaxTotalAmt, Date pEffectiveDate, Long pMaxReccursiveAmt, String pOrigCsldCrobFlag, String pReceiptTxnType,String userId);
	
	void insertIntoBenTransactionsOne(Long pTxnId, Long pTxnEntrySeq, Long pGlSeq, Long pBenSeq, Long pDeductionId,
			BigDecimal pPersonId, BigDecimal pPCrporateId, BigDecimal pUnknownBenId, Long pAccountCode, String pTxnPostType, String pTxnType, String pTxnEntryDesc, Long pAmountToBen, String pCsldId, Long pRevTxnId, Long pRevEntrySeq, Long pRevGlSeq, Long pRevBenSeq, String pRevTxnFlag, String pOrigCsldCrobFlag, String pReceiptTxnType, Long pBeneficiaryId , String userId );

	void updateOffenderDeductionsFin(Long pOffId, String pDedType, String pInfoNumber, Long pMaxTotalAmount, Long pAmtToDeduct, Long pOffDedId,Date pEffectDate, Long pMaxMonthlyAmt, Date pLastMonthDeductDate, Long pLastMonthDeductAmount, Long pMaxRecursiveAmt, String pUnlimitedFlag, String userName);
	
	void processCollectionFees(OffenderTransactions offtxn, Long cOffDedId, BigDecimal cfDedAmt);
	
}
