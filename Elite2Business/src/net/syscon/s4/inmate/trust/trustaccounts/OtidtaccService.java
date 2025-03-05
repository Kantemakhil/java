package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Interface OtidtaccService
 */
public interface OtidtaccService {

	List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts obj);

	List<OffenderDeductions> offDedExecuteQuery(OffenderDeductions obj);

	List<OffenderTransactions> calcAccountBalancesRecordGroup();

	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions obj);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<OffenderTrustAccounts> offTaExecuteQuery(OffenderTrustAccounts obj);

	OffenderDeductions populateCreditObligation(OffenderSubAccounts searchBean);

}
