package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.TransactionOperation;

/**
 * Interface OtdsubatService
 */
public interface OtdsubatService {
	List<Object> cgwhenNewFormInstance();

	List<ReferenceCodes> cgfkchkOffTxnOffTxnRef(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkOffTxnSubAccountTypeRecordGroup(String fromSubAccount, String caseLoadId);

	List<ReferenceCodes> cgfkchkOffTxn2OffTxnRef(ReferenceCodes paramBean);

	List<OffenderTransactions> offTxn2Commit(OffenderTransactionsCommitBean commitBean, String userName);

//	Integer offTxnCommit(OffenderTransactionsCommitBean commitBean);

	List<OffenderTransactions> offTxn2ExecuteQuery(OffenderTransactions obj);

	List<TransactionOperation> cgfkOffTxn2SubAccountTypeRecordGroup(String caseLoadId);

	String getDescription(String caseloadType, String txnType);

	String getacCode(String code, String caseloadType);

	String getBal(String offenderId, String caseloadId, String acCode);

	String getAcClosedFlg(Long offenderId, String caseloadId);

}
