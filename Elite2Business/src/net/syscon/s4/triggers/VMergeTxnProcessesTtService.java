package net.syscon.s4.triggers;

import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;

public interface VMergeTxnProcessesTtService {
	Integer vMergeTxnProcessesTtTgr(VMergeTransactionProcesses old, VMergeTransactionProcesses newObj);
}
