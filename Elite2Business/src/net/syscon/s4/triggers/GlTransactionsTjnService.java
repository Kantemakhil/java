package net.syscon.s4.triggers;

import net.syscon.s4.inmate.beans.GlTransactions;

public interface GlTransactionsTjnService {
Integer glTransactionsTjnTrigger(GlTransactions glTransactions ,String operationType);
}
