package net.syscon.s4.inmate.trust.generalledger;

import java.util.List;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;

/**
 * Interface OtdglirtService
 */
public interface OtdglirtService {
	List<GlTransactions> glTxnExecuteQuery(GlTransactions objGlTransactions);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<ReferenceCodes> cgfkGlTxnReversalReasonRecordGroup();

	Integer glTxnCommit(GlTransactionsCommitBean CommitBean);

	List<GlTransactions> glTxnOneExecuteQuery(GlTransactions searchBean);

	List<GlTransactions> txnReversedFlagData(Long txnId, Long txnEntrySeq);

	Integer whenNextbuttonClick(GlTransactions searchRecord);
	
	OffFeeBillTransactions revarsalPaymentBillAging(OffFeeBillTransactions bean) throws Exception;

}
