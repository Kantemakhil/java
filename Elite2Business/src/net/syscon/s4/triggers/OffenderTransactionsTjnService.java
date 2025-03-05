package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.OffenderTransactions;

public interface OffenderTransactionsTjnService {
	
	Integer OffenderTransactionsTjn(final OffenderTransactions newObj, final OffenderTransactions oldObj, final String operation);

}
