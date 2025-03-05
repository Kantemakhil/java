package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Interface OtupayinService
 * 
 */
public interface OtupayinService {

	List<OffenderTransactions> offTxnExecuteQuery(OffenderDeductions obj);

	OffenderDeductions offDedExecuteQuery(OffenderDeductions obj);

}
