package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogsCommitBean;

/**
 * Interface OcuverifService
 */
public interface OcuverifService {
	List<Object> cgwhenNewFormInstance();

	Integer workFlCommit(WorkFlowLogsCommitBean commitBean );

	List<WorkFlowLogs> workFlExecuteQuery(OffenderAlerts searchBean);
	
	Integer workFlVerificationCommit(WorkFlowLogsCommitBean commitBean );
	

}
