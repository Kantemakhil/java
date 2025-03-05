package net.syscon.s4.sa.admin.mergeoffenders;

import java.util.List;

import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;

/**
 * Interface OuimtstpService
 */
public interface OuimtstpService {
	List<VMergeTransactionProcesses> mergTxnProcExecuteQuery(VMergeTransactionProcesses object);

}
