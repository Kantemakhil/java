package net.syscon.s4.sa.admin.mergeoffenders;

import java.util.List;

import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;
import net.syscon.s4.sa.recordmaintenance.MergeProcesses;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionBean;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionProcessesCommitBean;

/**
 * Interface OumtrnbkService
 */
public interface OumtrnbkService {
	Integer mrgProcCommit(MergeTransactionProcessesCommitBean commitBean);

	List<MergeProcesses> IsProcessDefaultTransfer(MergeProcesses paramBean);

	List<MergeProcesses> IsProcessTimeRequired(MergeProcesses paramBean);

	List<VMergeTransactionProcesses> mrgProcExecuteQuery(MergeTransactionBean object);

	Integer chkOffendersForTransfer(MergeTransactionBean bean);

	String processTransferTransaction(MergeTransactionBean bean);
	
	List<VMergeTransactionProcesses> mrgProcExecuteQueryRet(MergeTransactionBean bean);

}
