package net.syscon.s4.cf.offendertransactions;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;

/**
 * Interface OcuotrahRepository
 */
public interface OcuotrahRepository {
	List<OffenderTransactions> offTxnExecuteQuery(OffenderTransactions objOffenderTransactions);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);
	
	 String offenderTransactionsTxnEntryTime(final String transactionTime);

}
