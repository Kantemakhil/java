package net.syscon.s4.triggers;

import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;

public interface VMergeTxnProcessesTtRepository {
	Integer insert(VMergeTransactionProcesses vMerge);

	Integer update(VMergeTransactionProcesses vMerge);

	Integer delete(VMergeTransactionProcesses vMerge);

}
