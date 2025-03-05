package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.OffenderDeductions;

/**
 * Interface OtupayinRepository
 * 
 */
public interface OtupayinRepository {
	List<OffenderTransactions> offTxnExecuteQuery(OffenderDeductions searchRecord);

	OffenderDeductions offDedExecuteQuery(OffenderDeductions obj);

	String offDedPostQuery(OffenderDeductions paramBean);

}
