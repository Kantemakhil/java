package net.syscon.s4.inmate.trust.deductions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.support.TransactionOperations;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.offBillingStatements;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OcdpayobRepository
 */
public interface OcdpayobRepository {
	OffenderDeductions cgfkchkOffBncOffBncOff(OffenderBeneficiaries searchRecord);

	Integer offTxnInsertOffenderTransactions(List<OffenderTransactions> object);

	Integer offBncUpdateOffenderBeneficiaries(List<OffenderBeneficiaries> object);

	Corporates cgfkchkOffBncOffBncCorp(OffenderBeneficiaries object);

	Persons cgfkchkOffBncOffBncPer(OffenderBeneficiaries object);

	List<OffenderBeneficiaries> offBncExecuteQuery(OffenderBeneficiaries object,String deductionId);

	List<TransactionOperation> cgfkOffTxnSubAccountTypeRecordGroup(String caseloadId);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions object);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Object cgfkchkOffTxnOffTxnRef(TransactionOperations paramBean);

	OffenderDeductions offBncPostQuery(OffenderBeneficiaries obj);

	OffenderDeductions offBncWriteOffCur(OffenderBeneficiaries obj);

	BigDecimal offBncVmonAmt(OffenderBeneficiaries obj);

	BigDecimal offBncVrecMAx(OffenderBeneficiaries obj);

	BigDecimal getCurrentBalance(OffenderTransactions searchBean);

	Integer txnIdNextValData();

	Integer financialDoDuctionsFinancial(String caseloadId, BigDecimal offenderId, Long offenderBookId, String txnType,
			Integer txnIdNextVal, Date transDate, String subAccountType, String string, BigDecimal txnEntryAmount,
			Integer pDedAmt, Integer pTxnEntrySeq);

	String getAcAndSetIndDate(BigDecimal offenderId, String caseloadId);

	Integer glTxnsCount(Integer txnId);

	Integer updateOffenderBeneficiaries(OffenderBeneficiaries objOffTransactions);

	String txnTyepOffTxns(String subAccountType, String caseloadType, String caseloadId);

	Integer updateOffenderBeneficiariesOverRidden(OffenderBeneficiaries objOffTransactions);
	
	String getOffenderFeesEnableBtn();
	
	List<OffFeeBillTransactions> offFeeExecuteQuery(OffFeeBillTransactions serachBean);

	Integer offFeesBillTransactionUpdate(List<OffFeeBillTransactions> updateList);
	
	Integer getstaffId(String userId);

	Integer getBillTranId(final String billId);
	
	Integer insertOffenderFees(OffFeeBillTransactions bean);
	
	BigDecimal getCrDeductAcntCode(String dedType,String caseLoad);

	Integer insertIntoOffenderTransaction(OffenderTransactions offTran);

	Integer getDrAccountCode(String billTxnType, String caseloadId);

	String getSubAccountTypeDesc(String txnType);
	
	Integer trustInsertGltransNew(OffenderTransactions offTxnObj);

	OffenderTransactions updateOffenderBalance(OffenderTransactions offTxnObj) throws Exception;

     Integer getMaxTxnEntrySeq(Long offenderId, Integer txnInd);
     
     Integer getStaffID();

	Integer offStmtCommit(offBillingStatements offBillingStatements);

	Integer offFeeBillsUpdate(offBillingStatements offBillingStatements);
	
	Integer getMaxGlEntrySeq(Long offenderId, Integer txnInd);

	void updateOffFeeBillsDate(List<OffFeeBillTransactions> updateOffFeeBillsList);
	
	OffenderBeneficiaries getBeneficiariesOldData(Long beneficiaryId);
}