package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.common.beans.TransactionTypes;

public interface TransactionTypesTjnRepository {

	Integer transactiontypesTJNTrigger(List<TransactionTypes> lstTransactionPayess);

}
