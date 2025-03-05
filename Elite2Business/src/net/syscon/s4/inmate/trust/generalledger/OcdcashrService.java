package net.syscon.s4.inmate.trust.generalledger;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;

/**
 * Interface OcdcashrService
 */
public interface OcdcashrService {
	GlTransactions glTxnCommit(GlTransactionsCommitBean commitBean);

	List<GlTransactions> glTxnExecuteQuery(GlTransactions objGlTransactions);

	Integer glTxn1Commit(GlTransactionsCommitBean CommitBean);

	List<AccountCodes> cgfkGlTxnAccountCodeRecordGroup(String caseloadId, String caseloadType);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	String accountCodeChangeEvent(String caseloadId, String caseloadType, BigDecimal accountCode, String userName);

	String txnAmountDataSlashes(String txnEntryAmount);

}
