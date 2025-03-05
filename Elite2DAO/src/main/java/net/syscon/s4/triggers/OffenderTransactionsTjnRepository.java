package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.OffenderTransactions;

public interface OffenderTransactionsTjnRepository {

	Integer insertOffenderTransactionsTjn(final OffenderTransactions obj);

	Integer updateOffenderTransactionsTjn(final OffenderTransactions obj);

	Integer deleteOffenderTransactionsTjn(final OffenderTransactions obj);
}
