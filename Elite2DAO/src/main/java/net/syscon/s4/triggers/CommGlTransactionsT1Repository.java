package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.CommGlTransactions;

public interface CommGlTransactionsT1Repository {
	CommGlTransactions getCommGlTransactions(CommGlTransactions commGlTransactions);

	String vProfileValue();

	String vModuleName(Long sid);

	Integer insertCommissaryAudits(CommissaryAudits commissaryAudits);
}
