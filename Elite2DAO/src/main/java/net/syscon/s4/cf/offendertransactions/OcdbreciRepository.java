package net.syscon.s4.cf.offendertransactions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.statements.beans.ocdbreciReportsBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Interface OcdbreciRepository
 */
public interface OcdbreciRepository {

	Object cgfklkpOffTxnOffTxnOff(OffenderDeductions paramBean);

	List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup(String user);

	List<OffenderTransactions> offTxn1ExecuteQuery(OffenderTransactions objOffenderTransactions);

	List<SystemProfiles> runReport(SystemProfiles paramBean);

	Object cgfkchkOffTxnOffTxnOff(OffenderDeductions paramBean);

	Integer offTxn1UpdateOffenderTransactions(List<OffenderTransactions> lstOffenderTransactions);

	List<TransactionTypes> cgfkchkOffTxn1OffTxnTxn(TransactionTypes paramBean);

	Integer offTxn1InsertOffenderTransactions(List<OffenderTransactions> lstOffenderTransactions);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	VTrustHeader getOffenderIdDetails(OffenderTransactions searchBean);

	Long countOffenderIdDet(Long rootOffenderId);

	String getProfileValue();

	Long getReferenceCodesValid(String txnType);

	String chkAccountStatus(String caseloadId, Long offenderId);

	String chkOffenderDeductions(String caseloadId, Long rootOffenderId, String txnType);

	List<OffenderDeductions> checkOffenderDeductionId(Long rootOffenderId);

	String checkOffenderDeductionReceipts(Long offenderDeductionId, String txnType);

	String chkUniqueObligationFlag(Long offenderId);

	String getProfileValuePaymentPlan();

	String chkMissingPayPlan(Long offenderId, String infoNumber);

	String checkAccountClosedFlag(OffenderTransactions offTransactions);

	Integer txnIdNextValData();

	String checkReceiptProductionFlag(OffenderTransactions offTransactions);

	Integer genTrustRcptNmbr(String string);

	Map<String, Object> getSubActType(String string, String txnType, String caseloadId);

	String checkDescriptionTxnType(OffenderTransactions offTransactions);

	Integer insertIntoOffenderTransaction(OffenderTransactions offTransactions);

	Integer processGlTransNew(OffenderTransactions offTransactions) throws Exception;

	void updateOffenderBalance(OffenderTransactions offTransactions);

	Integer financialDoDuctionsFinancial(String caseloadId, Long long1, Long long2, String txnType, Integer pTxnNum,
			Date transDate, String pCrSubAccountType, String string, Double double1, Double double2,
			Integer pTxnEntrySeq, String string2);

	String getAcAndSetIndDate(OffenderTransactions offTransactions);

	List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroup(Long offenderId, String caseloadId, String txnId);

	Long runReportroleCur();

	Integer printReceitsInsertQuery(List<OffenderTransactions> lstOmsRequests);

	Integer printReceiptsTmpDeletequery(Long sessionId, String userId);

	SystemProfiles getFclientValue();

	String getfcaseloadDesc(String caseloadId);

	List<ocdbreciReportsBean> getReportData(OffenderTransactions bean);

	Integer updateOffenderTransactionsrpt(String modulename, Long sessionId, String modifyUserId);

	Integer printReceiptsTmpDeletequeryRpt(String modulename, Long sessionId, String modifyUserId);

	String getOffenderIdData(String caseloadId, BigDecimal offenderId,String userName);

	VHeaderBlock getOffenderNameData(String caseLoadId, BigDecimal offenderId,String userName);

	BigDecimal getTruncAmount(BigDecimal amt);

	String getAmoutninWords(BigDecimal amount);

	BigDecimal getTransfeesAmount(Integer txnId, BigDecimal txnSeq, String txnType);

	OffenderBeneficiaries getTotalAmountandOwingAmount(BigDecimal offenderId);

	BigDecimal getAmountData(Integer txnId, BigDecimal txnSeq, String txnType, BigDecimal amount);

	List<OffenderDeductions> getReportDataQuery(BigDecimal offenderId);

	String dedFlag(String dedType);

	String existFlag(Long dedId);

	BigDecimal monthsBetweenAmount(Long dedId);

	List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroupOne(OffenderTransactions searchBean);

	Integer updateOffFeeAccountProfileHty(FeeAccountProfiles bean);

	String getProfileValueDisableBtn();

	List<OffFeeBillTransactions> offFeeExecuteQuery(OffFeeBillTransactions serachBean);

	Date longestSuperVisionDate(Long offenderBookId);

	Integer offFeeUpdate(OffFeeBillTransactions offFeeBillTxn);

 	Integer getstaffId(String userId);

	Integer getBillTranId(final String billId);

	Integer updateOffenderFees(OffFeeBillTransactions offFeeBillTxn);

	String getSubAccountTypeDesc(String txnType);

	Integer prepaidAccountTransfer(OffenderTransactions offTrans) throws Exception;

	BigDecimal getCrDeductAcntCode(BigDecimal offenderFeeId);

	Integer getDrAccountCode(String txnType, String caseLoadId);
	
	Integer trustInsertGltransNew(OffenderTransactions offTxnObj);
	
	List<ReferenceCodes> docketRecordGroup(final Long offenderBookId);
	
	Integer cgfkOffTxnDspInformationNRecordGroupCount(final Long offenderBookId);
	
	Integer getTxnEntrySeq(Integer txnId, Long offnderId);
	
	Integer getGlEntrySeqTxn(Integer txnId, Long offnderId);

	OffenderTransactions getProdFlagDetails(OffenderTransactions searchBean);
	
	List<FeeAccountProfiles> getOffenderFeeIdList(Long offnderBookId, String feeCode);

	List<OffFeeBillTransactions> getFeeBillPrvsCurrentBlnc(Integer txnId, Long offenderBookId);

	FeeAccountProfiles getOldDataFeeAccountProfiles(FeeAccountProfiles fap);


}
