package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.ProcessGlTransNewBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;

public interface OtdreceiRepository {
	TransactionTypes cgfkchkOffTxn1OffTxnTxn(TransactionTypes paramBean);

	List<OffenderTransactions> offTxn1ExecuteQuery(OffenderTransactions objOffenderTransactions);

	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<AccountCodes> offTxn1WhenValidateRecordWhenValidateRecord(AccountCodes paramBean);

	Integer offTxn1InsertOffenderTransactions(List<OffenderTransactions> lstOffenderTransactions);

	List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup(String caseloadId);

	String OtdreceiChkReceiptFlag(String txnType, String caseloadId);

	Integer genTrustTrans(String seqId);

	String chkAccountStatus(String caseloadId, Long offenderId);

	Map<String, Object> getSubActType(String moduleName, String txnType, String caseloadId);

	Integer insrtIntoOffenderTransForm(OffenderTransactions offenderTransaction);

	Integer processGlTransNew(ProcessGlTransNewBean offTrans);

	void updateOffenderBalance(OffenderTransactions paramBean);

	TransactionTypes daysCur(String txnType);

	void processHold(Integer txnId, String caseloadId, Long offenderId, String txnType, Integer holdDays,
			String subAccountType, Double txnEntryAmount, String txnEntryDesc, String txnReferenceNumber,
			Integer txnnum, Integer holdNumbers);

	Integer getMaxTxnEntrySeq(Integer txnId);

	void financialDoDuctionsFinancial(String caseloadId, Long offenderId, Long offenderBookId, String txnType,
			Integer txnId, Date txnEntryDate, String subAccountType, String dedFlag, Double txnEntryAmount,
			Integer shadowId, Double dedAmount, Integer txnEntrySeq, String infoNumber);

	void deductionsGetAcAndSetIndDate(Long offenderId, String caseLoadId);

	Integer reopenClosedTrustAccount(Long offenderId, String caseloadId, String modifyUserId);

	String getRTxnType(String moduleName, String caseloadId);

	AccountCodes getSubAccType(String moduleName, String txnType, String caseloadId);

	Integer insertIntoOffenderTransaction(OffenderTransactions offTrans);

	String deductionsChkOffenderDeductions(String caseloasdId, Long offenderId, String txnType, Integer shadowId);

}
