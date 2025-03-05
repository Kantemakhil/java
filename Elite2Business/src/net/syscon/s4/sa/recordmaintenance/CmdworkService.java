package net.syscon.s4.sa.recordmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;

public interface CmdworkService {
	List<ReferenceCodes> rgWorkTypeRecordGroup();
	
	List<CmdModules> rgModulesRecordGroup(String columnName);
	
	List<BpmnProcess> rgProcessRecordGroup();
	
	List<WorkItems> workItemsExecuteQuery();
	
	Integer commitWorkItems(WorkItemsCommitBean commitBean);
	
	List<ModuleTriggers> rgModuleTriggersRecordGroup();
	
	Integer deleteTrigger(BpmnProcess bpmnProcess);
	
	List<BpmnProcess> validateDeploy(BpmnProcess bpmnProcess);
	
	List<ReferenceCodes> getModuleTriggerData(String triggerId);

	String getTriggerName(String triggerId);
}
