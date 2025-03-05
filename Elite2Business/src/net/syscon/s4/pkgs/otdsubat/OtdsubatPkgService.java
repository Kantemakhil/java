package net.syscon.s4.pkgs.otdsubat;

import java.util.List;

import net.syscon.s4.im.beans.OffenderTransactions;

public interface OtdsubatPkgService {

	// Map<String, Object> processTransactions(final OtddisbuProcessTransactionsBean
	// proTxn);

	Integer processTransaction(final List<OffenderTransactions> list, final String userName);
}