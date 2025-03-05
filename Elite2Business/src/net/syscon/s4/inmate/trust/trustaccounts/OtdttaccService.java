package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.AccountPeriods;
import net.syscon.s4.inmate.beans.AccountPeriodsCommitBean;
import net.syscon.s4.inmate.beans.CaseloadTransactionTypes;
import net.syscon.s4.inmate.beans.CaseloadTransactionTypesCommitBean;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Interface OtdttaccService
 */
public interface OtdttaccService {
	Caseloads CgfkchkCsldTtCsldTtCsld(Caseloads paramBean);

	List<CaseloadTransactionTypes> csldTtExecuteQuery(CaseloadTransactionTypes objCaseloadTransactionTypes);

	List<Caseloads> cgfkCsldTtCaseloadIdRecordGroup(String caseLoadId);

	Integer acPrdCommit(AccountPeriodsCommitBean commitBean);

	List<AccountPeriods> acPrdExecuteQuery(AccountPeriods objAccountPeriods);

	Integer csldTtCommit(CaseloadTransactionTypesCommitBean commitBean);

	List<OffenderTransactions> offTxnCommit(OffenderTransactionsCommitBean CommitBean);

	List<TransactionTypes> cgfkCsldTtTxnTypeRecordGroup(String caseLoadId);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffenderTransactions);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	TransactionTypes CgfkchkCsldTtCsldTyTxn(TransactionTypes paramBean);

	String getHoldClearFlag(String caseloadId, Long offenderId, String casaeloadType);

	List<OffenderSubAccounts> getHoldBal(String caseloadId, Long offenderId, String txnType, String userName);

	String getDuplicateOffenders(Long offenderId);

	List<Corporates> getCorporateidNames(String toCaseload);

	Object checkTxnType(String toCaseload, String txnType, String caseloadId);

	OffenderTransactions getRootOffenderId(String casaeloadType, String offenderIdDisplay, String userName);

	List<OffenderTransactions> whenNewBlockInstanceRetrive(Long startDate, Long endDate, String currentCaseload,
			String toCaseload, final String txnType, final String caseloadType);

	Integer deleteOffacShads(String caseloadId, Long offenderId, String modifyUserId);

}
