package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.common.beans.CommGlTransactions;

public interface CommGlTransactionsT1Service {
	Integer commGlTransactionsT1Tgr(List<CommGlTransactions> commGlTransactionsList);
}
