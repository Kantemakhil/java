package net.syscon.s4.cf.offendertransactions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.cf.deductions.beans.offBillingStatements;
import net.syscon.s4.cf.maintenance.beans.FeeAccounts;
import net.syscon.s4.cf.offendertransactions.beans.PrintReceiptsTmp;
import net.syscon.s4.cf.statements.beans.ocdbreciReportsBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.VAgencyAddresses;
import net.syscon.s4.im.beans.VTrustHeader;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Interface OcdreceiRepository
 */
public interface OcdreceiRepository {

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffenderTransactions);

	Object cgfkchkOffTxnOffTxnOff(OffenderDeductions paramBean);

	List<TransactionTypes> cgfklkpOffTxnOffTxnTxn(TransactionTypes paramBean);

	List<TransactionTypes> cgfkOffTxnTxnTypeRecordGroup(String user);

	List<TransactionTypes> cgfkchkOffTxnOffTxnTxn(TransactionTypes paramBean);

	OffenderTransactions getProdFlagDetails(OffenderTransactions searchBean);

	String chkOffenderDeductions(String caseloadId, Long val, String txnType);

	Long getOffenderDeductionId(Long val);

	String checkOffenderDeductionReceipts(Long offenderDeductionId, String txnType);

	Long getReferenceCodesValid(String rootOffenderId);

	Long countOffenderIdDet(Long rootOffenderId);

	String getProfileValue();

	long getSessionId();

	void deletePrintReceiptsTmp(long pSessionId, String modifyUserId);

	void insertPrintReceiptsTmp(Integer txnId, Long offenderId, String receiptNumber, long pSessionId, String createUserId);

	String checkAccountClosedFlag(OffenderTransactions offTransactions);

	String chkAccountStatus(String caseloadId, Long offenderId);

	Map<String, Object> getSubActType(String moduleName, String txnType, String caseloadId);

	String getProfileValuePaymentPlan();

	OffenderTransactions processGlTransNew(OffenderTransactions offTrans) throws Exception;

	Integer financialDoDuctionsFinancial(OffenderTransactions offenderTransactions, String pDedFlag);

	Integer txnIdNextValData();

	List<String> chkMissingPayPlan(Long offenderId, String infoNumber);

	String getSystemProfile();

	String getAutoSubmitProfileVal();

	String getRoleBaseProfileVal();

	VTrustHeader getOffenderIdDetails(OffenderTransactions searchBean);

	OffenderTransactions updateOffenderBalance(OffenderTransactions paramBean) throws Exception;

	Integer genTrustRcptNmbr(String string);

	List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroup(Long offenderBookId);

	void updateOfndrTrustAcc(Long rootOffenderId, String caseloadId);

	String getUniqueObligationFlag(Long rootOffenderId);

	Integer insertIntoOffenderTransaction(OffenderTransactions offenderTransactions);

	Long validateDspInfoNumber(OffenderTransactions searchBean) throws Exception;

	String getAcAndSetIndDate(OffenderTransactions offTransactions);

	SystemProfiles getFclientValue();

	String getfcaseloadDesc(String caseloadId);

	List<ocdbreciReportsBean> getReportData(OffenderTransactions bean);

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

	Integer updateOffenderTransactionsrpt(String modulename, Long sessionId, String modifyUserId);

	Integer printReceiptsTmpDeletequeryRpt(String modulename, Long sessionId, String modifyUserId);

	void insertPrintReceiptsTmp(OffenderTransactions offenderTransactions);

	String getSystemProfileValue();

	List<OffFeeBillTransactions> getOffenederFeeSectionQuery(String offenderIdDisplay,String userName);

	String getCfPaymentSystemProfileValue();

	Date getLongestSupervisionExpireDate(Long offenderBookId);

	Integer updateOffenderFees(OffFeeBillTransactions offFeeBillTxn);

	Integer getBillTranId(final String billId);

	Integer prepaidAccountTransfer(OffenderTransactions offTxn) throws Exception;

	String getSubAccountTypeDesc(String txnType);

	BigDecimal getCrDeductAcntCode(BigDecimal offenderFeeId);

	Integer getDrAccountCode(String txnType, String caseLoadId);

	Integer trustInsertGltransNew(OffenderTransactions offTxnObj);

	Integer getMaxTxnEntrySeq(Long offenderId, Integer txnInd);

	Integer getPaymentObligationCount(Long offenderId);

	List<VAgencyAddresses> getAddreesDetails(VAgencyAddresses addrBean);

	String getClientName(Long offenderBookId,String userName);

	List<CasePlans> getCasePlanDetailsToGetPoName(Long offenderBookId);

	String getTransActionDescription(String txnType);

	Integer getStaffID(String userId);

	List<FeeAccounts> getFeeCodeRecordGroup();

	List<OffFeeBillTransactions> getFeeBillPrvsCurrentBlnc(Integer trustTxnId);

	String getRemitterName(Integer txnId, Integer txnEntrySeq);

	void updateOffFeeBillsDate(List<OffFeeBillTransactions> updateOffFeeBillsList);

	String getbillEndDayPfVal();

	Integer offStmtCommit(offBillingStatements insertList);

	Integer offFeeBillsUpdate(offBillingStatements updateBean);
	
	PrintReceiptsTmp getPrintReceiptsTmpRecord(final String modulename, final Long sessionId);
	
	PrintReceiptsTmp getPrintReceiptsBySessionid(final Long sessionId);

	BigDecimal getPrepaidBalance(Long offenderId, String caseloadId, String feeCode);
Integer updateFeeAccountStatus(FeeAccountProfiles insertObj);
	
	BigDecimal getOffFeeIdTotalBalanceOwing(Long offenderBookId,String feeCode, String caseloadId);

	OffFeeBills getOldDataOffFeeBills(String billId);
}
