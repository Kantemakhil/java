package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.inmate.beans.GlTransactions;

public interface GlTransactionsTjnRepository {
	GlTransactions getGlTransactions(GlTransactions glTransactions);

	Integer insertUpdateDelete(List<GlTransactionsJn> glTransacJnList);
}
