package net.syscon.s4.sa.admin.mergeoffenders;

import java.util.List;

import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;

/**
 * Interface OuimtstpRepository
 */
public interface OuimtstpRepository {
	String createFormGlobals(OmsModules paramBean);

	List<VMergeTransactionProcesses> mergTxnProcExecuteQuery(VMergeTransactionProcesses object);

}
