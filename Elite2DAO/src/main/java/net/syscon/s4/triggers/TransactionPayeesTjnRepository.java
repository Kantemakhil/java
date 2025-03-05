package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.im.beans.TransactionPayees;

public interface TransactionPayeesTjnRepository {

	Integer transactionPayessTJNTrigger(List<TransactionPayees> lstTransactionPayess);

}
