package net.syscon.s4.inmate.trust.trustaccounts;

import java.sql.SQLException;
import java.util.List;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.OffenderTransactions;

public interface OtdreceiService {
	List<Object> offTxn1WhenValidateRecord(AccountCodes paramBean) throws SQLException;

	List<Object> CgwhenNewFormInstance() throws SQLException;

	List<OffenderTransactions> offTxn1Commit(OffenderTransactionsCommitBean commitBean) throws SQLException;

	Integer offTxnCommit(OffenderTransactionsCommitBean CommitBean) throws SQLException;

	List<OffenderTransactions> offTxn1ExecuteQuery(OffenderTransactions objOffenderTransactions) throws SQLException;

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles) throws SQLException;

	Integer sysPflCommit(SystemProfilesCommitBean commitBean) throws SQLException;

	TransactionTypes CgfkchkOffTxn1OffTxnTxn(TransactionTypes paramBean) throws SQLException;

	List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup(String caseloadId) throws SQLException;

	String otdreceiChkReceiptFlag(String txnType, String caseloadId);

	String deductionsChkOffenderDeductions(String caseloasdId, Long offenderId, String txnType, Integer shadowId);

}
