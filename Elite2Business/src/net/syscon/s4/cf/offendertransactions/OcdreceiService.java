package net.syscon.s4.cf.offendertransactions;

import java.util.Date;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.maintenance.beans.FeeAccounts;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.OffenderTransactions;

/**
 * Interface OcdreceiService
 */
public interface OcdreceiService {

	List<TransactionTypes> cgfkOffTxnTxnTypeRecordGroup(String user);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffenderTransactions);

	OffenderTransactions offTxnCommit(OffenderTransactions commitBean);

	List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroup(Long offenderBookId);

	OffenderTransactions whenValidateItem(OffenderTransactions searchBean);

	OffenderTransactions preCommit(OffenderTransactions offenderTransactions);

	OffenderTransactions whenNewBlockInstance(OffenderTransactions searchBean);

	Long validateDspInfoNumber(OffenderTransactions searchBean) throws Exception;

	OffenderTransactions keyCommitTwo(OffenderTransactions offenderTransactions) throws Exception;

	List<OffenderTransactions> printReport(OffenderTransactions commitBean);

	String getSystemProfileValue();

	List<OffFeeBillTransactions> getOffenederFeeSectionQuery(String offenderIdDisplay,String userName);

	String getCfPaymentSystemProfileValue();

	Date getLongestSupervisionExpireDate(Long offenderBookId);

	OffenderTransactions offenderReceiptsCommit(OffenderTransactionsCommitBean commitBean) throws Exception;

	Integer updateOffenderFees(OffFeeBillTransactions offFeeBillTxn, Integer glSeq, Integer txnEntrySeq,
			String txnReferenceNumber, String infoNumber, String receiptNumber, String caseLoad) throws Exception;

	Integer getPaymentObligationCount(Long offenderId);

	List<OffenderTransactions> printReportSupv(OffenderTransactions commitBean);

	List<FeeAccounts> getFeeCodeRecordGroup();

	String getbillEndDayPfVal();
	
	OffFeeBillTransactions billAgingARStatusProcess(OffFeeBillTransactions offFeeBillTxn);
	
	void updateOffFeeBillsDate(List<OffFeeBillTransactions> updateOffFeeBillsList) throws Exception;
}
