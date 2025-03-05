package net.syscon.s4.pkgs.merge_process;

import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.MergeProcessRules;
import net.syscon.s4.sa.recordmaintenance.MergeProcesses;

public interface MergeProcessService {

	public Boolean initProc(Offenders fromOffenders,Offenders toOffenders, Long pMergeTxnId, String pTxnType,String user);

	public Long getStartProcessId(String transactionType);

	public MergeProcesses getMergeProcessesInfo(Long processId, Long pMergeTxnId,String user);

	public Boolean checkTransferRuleTurnedOn(Long mergeTransactionId, Long processId);

	public void checkTooManyProcExecs(Long processId);

	public void checkTooManyExecs(String tNumExecsTab, Long pId);


	public boolean executeProc(String pProcType, String pProc,Long pMergeTxnId, Offenders fromOffenders,Offenders toOffenders, String user);

	public void initProcRules(Long firstRuleId, Long pMergeTxnId, Offenders fromOffenders,Offenders toOffenders,String  user);

	public MergeProcessRules getMergeProcessRulesInfo(Long ruleId);

}
