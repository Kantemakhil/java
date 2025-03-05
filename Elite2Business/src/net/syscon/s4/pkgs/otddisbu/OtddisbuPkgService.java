package net.syscon.s4.pkgs.otddisbu;

import java.util.Map;

import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;

public interface OtddisbuPkgService {

	Map<String, Object> processTransactions(final OtddisbuProcessTransactionsBean proTxn, final String userName);
}
