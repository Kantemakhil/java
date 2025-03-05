package net.syscon.s4.pkgs.merge_process;

import net.syscon.s4.pkgs.MergeProcessRules;
import net.syscon.s4.sa.recordmaintenance.MergeProcesses;

public interface MergeProcessRepository {
	
	public Long getStartProcessId(String transactionType);
	
	public MergeProcesses getMergeProcessesInfo(Long processId);
	
	public Long checkTransferFlag(Long mergeTransactionId,Long processId);
	
	public MergeProcessRules getMergeProcessRulesInfo(Long ruleId);
}
