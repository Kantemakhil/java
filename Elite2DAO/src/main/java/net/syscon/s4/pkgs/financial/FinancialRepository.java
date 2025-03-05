package net.syscon.s4.pkgs.financial;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.OffenderMonDeductions;
import net.syscon.s4.cf.deductions.maintenance.beans.OffenderTierTxnFeeAmounts;
import net.syscon.s4.cf.deductions.maintenance.beans.TieredTransactionFeeAmounts;
import net.syscon.s4.cf.offendertransactions.beans.OffenderCreditPriorPayments;
import net.syscon.s4.cf.offendertransactions.beans.PaymentPlanTransaction;
import net.syscon.s4.common.beans.OffenderPaymentSchedules;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.BeneficiaryTransactions;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

public interface FinancialRepository {

	String updateOffenderBeneficiaries(final BigDecimal deductionId, final BigDecimal payeePersonId,
			final BigDecimal payeeCorporateId, final BigDecimal txnEntryAmount, final String userName);


	Integer offenderPaymentSchedulesUpdate(final PaymentPlanTransaction plan,Long pRecursiveAmt);

	Long seqCur(final PaymentPlanTransaction plan);

	Integer paymentPlanTransactionsInsert(final PaymentPlanTransaction plan);

	Integer finnacialSelect(final Long paymentPlanId);

	Integer offenderPaymentPlansUpdate(final Long paymentPlanId, final String userName);

	List<Object[]> cBenTxn(final Long txnId, final Long txnEntrySeq, final long glEntrySeq);

	String insertIntoBenTransactionsSelect(final Long txnId);

	TransactionOperation getTransactionOperationData(String csldType, String pCsldId);

	AccountCodes getAccountCodesData(String pSubActType, String csldType);

	String getProfileValue();

	String getOverrideFlag(Long pOffId);

	String getAgyLoc(Long pOffBookId);

	List<OffenderDeductions> doDeductionC(String pCsldId, Long pOffId, String pTransType, Long pInfoNumber, String csldType, String offIndFlag, Long pShadowId);

	OffenderDeductions deductionPirorityCur(Long pOffId, int pExtPri, String pCsldId);

	String getMethodFlag();

	Integer lTbdCountCur(Long offenderDeductionId);

	Integer chkDedBenCur(Long offenderDeductionId);

	Date getDefaultEndDate(Long offenderDeductionId);
	
	String getRecOverpayFlag();

	String checkCollectionFee(Long offenderDeductionId, String dedType);

	TieredTransactionFeeAmounts getTieredFeeAmounts(String pCsldId, String deductionType, String pTransType,
			BigDecimal pReceiptAmount);

	BigDecimal getOverrideAmount(Long offenderDeductionId, String dedType);

	BigDecimal getMinTrustBalance(String pCsldId, String deductionType, String pTransType);

	List<OffenderBeneficiaries> getLTbdCheckCur(Long offenderDeductionId, String overrideFlag);

	String getSubActType(Integer accountCode, String csldType);

	OffenderDeductions getOffenderDeductions(Long offenderDeductionId);

	void updateOverrideAmount(Long offenderDeductionId,String modifyUserId);
	
	OffenderBeneficiaries getOffenderBeneficiaries(final BigDecimal offenderDeductionId, final BigDecimal personId,
			final BigDecimal corporateId);
	
	String lvDedLock(BigDecimal pOffId, String pCsldId);
	
	String lvBenLock(BigDecimal pOffId);
	
	String getCfppFlag(BigDecimal pTransNumber);
	
	BigDecimal getTrustAccCode(String pCsldId,String moduleName, String pTransType);
	
	OffenderSubAccounts getAmountPMinbal(String pCsldId, BigDecimal offenderId, BigDecimal trustAccCode);
	
	String chkCaseloadC(String pCsldId);
	
	BigDecimal getInstMiniBal(String pCsldId, String pAgyLocId, BigDecimal trustAccCode);
	
	SystemProfiles systemProfileC();
	
	OffenderDeductions getActiveCollectionFee(BigDecimal offenderId,String pCsldId);
	
	BigDecimal lOffenderBookIdCur(BigDecimal pOffId,String userId); 
	
	Date lPaymentPlanEnddateCur(Long pOffDedId); 
	
	Date lSentenceEnddateCur(Date pDate, Long offBookId);
	
	Date addMonths (Date pDate, Integer interval);
	
	Long lBeneficiaryPaidCur(Long pOffDedId, Long pBenficiaryId, Long pPriority);
	
	OffenderCreditPriorPayments getCfppLocation(Long pTransNumber);
	
	String lvTxnEntryDesc(BigDecimal amntOwing, String lvCppLocation, String lvCppCommentText);
	
	OffenderDeductions lDeductionCur(Long offenderDeductionId);
	
	String lRecursiveOverpayCur();
	
	Long lOffenderBookIdCurOne(String pCsldId, BigDecimal pRootOffId);

	List<OffenderBeneficiaries> lOverrideCure(Long offenderDeductionId);
	
	String insertBenTxnsgetCfppFlag(Long pTxnId);
	
	BigDecimal cfppPaymentPerCur(Long pDeductionId, BigDecimal pPersonId);
	
	BigDecimal cfppPaymentCorpCur(Long pDeductionId, BigDecimal pPersonId);
	
	String vTxnEntryDesc(BigDecimal cfppPayment, String pTxnEntrySeq);
	
	Integer insertIntoBenficiaryTransactions (BeneficiaryTransactions insert);
	
	void updateIntoBeneficiaryTransactions(Long pRevTxnId, Long pRevEntrySeq, Long pRevGlSeq,Long pRevBenSeq);
	
	List<OffenderBeneficiaries> lPriorityCur(Long pOffDedId);
	
	List<OffenderBeneficiaries> lBeneficiaryCur(Long pOffDedId, Long pPriority, String pRecFlag, String pMonthlyFlag);

	void updateOffenderDeductionsFinOffBeneficiaries(Long pOffDedId,String userName);
	
	void updateOffenderDeductionsFinOffDed(Long pOffDedId, Long pAmtToDeduct, String userName);
	
	OffenderMonDeductions lLastRecursivePaidCur(Long pOffDedId);
	
	Integer updateOffMonDeductions(Long lAmtToApply,Long deductionAmt, Long pOffDedId, String modifyUserName);

	Integer insertOffMonDeductions(OffenderMonDeductions insertobj);
	
	Integer monthsBetweenUpdateFin (Date lRecursiveSartdate, Date lRecursiveEndDate);
	
	Integer updateOffMonDeductionsOne(Long lAmtToApply, Long pOffDedId, String modifyUserName);
	
	List<OffenderPaymentSchedules> paymentPeriod(String pInfoNumber, BigDecimal pGroupId, Long pOffId);

	String chkDedBenCurOne(Long cfOffDedId);
	
	OffenderTierTxnFeeAmounts getRpercFlatFee(Long offDedId);
	
	OffenderBeneficiaries getOffenderBeneficiariesById(Long beneficiaryId);

	Long lBeneficiaryMonOwingCur(Long pOffDedId, Long beneficiaryId);
}
