package net.syscon.s4.inmate.trust.trustaccounts;
import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderTrustTransfers;
import net.syscon.s4.inmate.beans.OffenderTrustTransfersCommitBean;
/**
 * Interface OtdrttfuService 
 */
public interface OtdrttfuService  {
	List<Object> cgwhenNewFormInstance();

	Integer offTxnCommit(OffenderTransactionsCommitBean commitBean);

	List<OffenderTrustTransfers>  offTtCommit(OffenderTrustTransfersCommitBean commitBean);

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffTrans);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<Caseloads> cgfkchkOffTtOffTtCsld(Caseloads paramBean);

	List<OffenderTrustTransfers> offTtExecuteQuery(OffenderTrustTransfers objOffTrust);

}
