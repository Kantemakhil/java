package net.syscon.s4.cf.offendertransactions;

import java.util.Date;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Interface OcdbreciService
 */
public interface OcdbreciService {
	List<OffenderTransactions> offTxnCommit(OffenderTransactionsCommitBean CommitBean) throws Exception ;

	List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup(String user);

	List<OffenderTransactions> offTxn1ExecuteQuery(OffenderTransactions objOffenderTransactions);

	Object cgfkchkOffTxnOffTxnOff(OffenderDeductions paramBean);

	List<TransactionTypes> cgfkchkOffTxn1OffTxnTxn(TransactionTypes paramBean);

	Object cgfklkpOffTxnOffTxnOff(OffenderDeductions paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	OffenderTransactions whenValidateItem(OffenderTransactions searchBean);

	OffenderTransactions whenValidateItemAmountInfonumber(OffenderTransactions searchBean);

	List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroup(Long offenderId, String caseloadId, String txnId);

	List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroupOne(OffenderTransactions searchBean);

	String getProfileValueDisableBtn();

	List<OffFeeBillTransactions> offFeeExecuteQuery(OffFeeBillTransactions serachBean);

	Date longestSuperVisionDate(Long offenderBookId);

	List<OffenderTransactions> offTxnCommitTemp(final OffenderTransactionsCommitBean commitBean) throws Exception;

	Integer offFeeUpdate(List<OffFeeBillTransactions> offFeeBillTxn) throws Exception;
	
	List<ReferenceCodes> docketRecordGroup(final Long offenderBookId);
	
	Integer cgfkOffTxnDspInformationNRecordGroupCount(final Long offenderBookId);

	List<OffenderTransactions> offTxnCommitRecipt(final OffenderTransactionsCommitBean commitBean)throws Exception;

	List<OffenderTransactions> printReportSupv(OffenderTransactionsCommitBean commitBean);
	
	OffenderTransactions getProdFlagDetails(OffenderTransactions searchBean);

}
