package net.syscon.s4.inmate.trust.generalledger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.support.TransactionOperations;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderCreditPriorPayments;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Interface OtdglirtRepository
 */
public interface OtdglirtRepository {
	Integer sysPflInsertSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	List<GlTransactions> glTxnExecuteQuery(GlTransactions objGlTransactions);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflDeleteSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	List<ReferenceCodes> cgfkGlTxnReversalReasonRecordGroup();

	String cgfkchkGlTxnGlTxnAcCod(BigDecimal accountCode,String userId);

	GlTransactions receiptNumberQuery(GlTransactions glTransactions);

	String offenderIdDisplayQuery(GlTransactions glTransactions);

	List<GlTransactions> glTxnOneExecuteQuery(GlTransactions searchRecord);

	Integer glTxnInsertSystemProfiles(List<GlTransactions> insertList);

	List<GlTransactions> txnReversedFlagData(Long txnId, Long txnEntrySeq);

	BigDecimal lvAdjustmentAmtData(BigDecimal deductionId);

	Integer transactionOperationsModule(String txnType,String userName);

	String txnUsageData(String txnType);

	Integer whenNextbuttonClick(GlTransactions searchRecord);

	Integer transactionOperationsDataTest(String txnType, String string);

	Integer transactionOperationsOtdshift(String txnType, String string);

	String txnTypeHor(String txnType, String string);

	Long txnOffenderId(Long txnId, Long txnEntrySeq);

	String accountClosedFlag(Long offId, String string);

	Integer whenNextbuttonUpdates(GlTransactions searchRecord);

	BigDecimal maxOffenderBookId(BigDecimal offId);

	Integer txnIdNextValData();

	String txnType(String userId);

	Map<String, Object> getSubActType(String moduleName, String txnType, String caseloadId);

	Integer insertIntoOffenderTransaction(OffenderTransactions offenderTransactions);

	Integer processGlTransNew(OffenderTransactions offTrans);

	List<GlTransactions> findingDeductionId(Long long1, Long long2);

	OffenderTransactions findingDeductionType(Long long1, Long long2);

	String findingDeductionCategory(String dedType);

	OffenderDeductions findingDeductionAmount(Long lDeductionId);

	List<TransactionOperations> chkHoldTrans(String txnType, String string);

	String bankChequeData(Long txnId);

	String profileValue();

	String beneficiaryTransactions(Long txnId, Long txnEntrySeq);

	String bankChequeRegisters(Long txnId);

	String accountFlagTrust(BigDecimal bigDecimal,String userName);

	OffenderTransactions infoNumberCreditObligationType(GlTransactions searchRecord);

	OffenderTransactions txnIdOffIdData(Long txnId, Long txnEntrySeq);

	OffenderSubAccounts balanceIndDateData(Long offenderId, Long txnId, Long txnEntrySeq,String userName);

	List<GlTransactions> glTransactionsNewData(Long txnId, Long txnEntrySeq, String string);

	String txnPostingTypeTemp(BigDecimal accountCode,String userName);

	Integer offTxnInsertGlTransactions(Long txnId, Long txnEntrySeq, BigDecimal accountCode, Long glSeq, Date transDate,
			String txnType, String acntTypePstng, String caseloadId, BigDecimal offenderId, BigDecimal offenderBookId,
			BigDecimal txnEntryAmount, String txnEntryDesc, Long txnId2, Long txnEntrySeq2, Long glEntrySeq,
			String reversalReasonCode, Date transTime, BigDecimal deductionId,String userName);

	String updateGlBalance(String caseloadId, BigDecimal accountCode, Double pStngAmount, Date transDate);

	String checkCaseloadType(String caseloadId);

	List<GlTransactions> accountClosedFlagData(BigDecimal offenderId,String userName);

	String subAccountTypeOffTrans(BigDecimal accountCode,String userName);

	Integer updateOffenderTransactions(Long txnId, Long txnEntrySeq,String userName);

	Integer offTxnInsertOffenderTransactions(Long txnId, Long txnEntrySeq, BigDecimal offenderId,
			BigDecimal offenderBookId, Date transDate, String caseloadId, BigDecimal txnEntryAmount,
			String reversalReasonCode, String txnEntryDesc, String txnType, String acntTypePstng,
			String subAccountTypeFlag, Long txnId2, Long txnEntrySeq2, String string,String userName);

	void updateOffenderBalance(String caseloadId, BigDecimal offenderId, String acntTypePstng, Date transDate,
			Long txnId, String txnType, BigDecimal txnEntryAmount, String subAccountTypeFlag);

	String findCaseloadId(BigDecimal deductionId);

	String findingDeductionCategoryTemp(String deductionType);

	OffenderCreditPriorPayments paymentAmountData(Long txnId);

	Integer updateOffenderDeductions(String deductionType, BigDecimal offenderId, Double finalAmt,
			BigDecimal deductionId, String createCaseload,String userId);

	Integer updateOffenderDeductionsTemp(String deductionType, BigDecimal offenderId, Double finalAmt,
			BigDecimal deductionId, String createCaseload,String userId);

	Integer updateOffenderBeneficiaries(Double finalAmt, BigDecimal deductionId);

	Integer insertOffenderCreditPriorPayments(Long txnId, String caseloadId, BigDecimal offenderId,
			BigDecimal lvrevdedamount, String location, String commentText,String userName);

	List<GlTransactions> corporateIdPersonId(BigDecimal deductionId);

	String getAcAndSetIndDate(String caseloadId, BigDecimal offenderId);

	String reverseBeneficiaryTrans(Long txnId, Long txnEntrySeq, long glEntrySeq, Long txnId2, Long glSeq,
			String txnType, String txnEntryDesc);

	String updateOffenderBeneficiaries(BigDecimal deductionId, BigDecimal payeePersonId, BigDecimal payeeCorporateId,
			BigDecimal txnEntryAmount);

	String txnTypeOutputData(String txnType);

	OffenderDeductions maxTotalAmountDeductionAamount(BigDecimal deductionId);

	Integer updateOffenderDeductionsAdjustmentAmount(Double lvAdjustmentAmount, BigDecimal txnEntryAmount,
			String lvAdjustmentReason, BigDecimal deductionId, String userName);

	Integer insertOffenderAdjustmentTxns(Long txnId, BigDecimal offenderId, BigDecimal deductionId,
			BigDecimal txnEntryAmount, Long txnId2, String userName);

	Integer updateGlTransactionsTxnReversedFlag(String txnReversedFlag, Long txnId, Long txnEntrySeq, String string,String userId);

	String txnTypeExist(String txnType, String caseloadId);

	Integer updateOffenderWorks(BigDecimal offenderId, Long txnId,String userId);

	String txnEntryDescTempData(String transDesc, String vReversalRsn);

	GlTransactions receiptNumberQueryReturn(GlTransactions searchRecord);
	
OffenderTransactions txnIdBasedData(final Long txnId);
	
	OffFeeBillTransactions offFeeExecuteQuery(final Long txnId,final Long txnSeqId);
	
	OffenderTransactions offenderTxnData(Long txnId,Long txnEntrySeq);
	
	Integer getDrAccountCode(String txnType, String caseLoadId);
	
	Integer getCrAccountCode(String txnType, String caseLoadId);
	
	Integer trustInsertGltransNew(OffenderTransactions offTxnObj);
	
	Integer getGlTxnEntrySeq(Integer txnId, Long offenderId);
	
	OffFeeBills getOffFeeBillsData(String billId);
	
	Integer updateOffenderFees(OffFeeBillTransactions offFeeBillTxn);
		
	List<OffFeeBillTransactions> getUdjustmentFeeBills(OffFeeBillTransactions bean); 
	
	OffFeeBills getUdjustmentFeeBills(String billId); 
	
	OffenderDeductions getOffenderDeductionOldData(Long offDedctionId);
	
	OffenderBeneficiaries getOldDataOfOffenderBeneficiaries(Long offDedctionId);

}
