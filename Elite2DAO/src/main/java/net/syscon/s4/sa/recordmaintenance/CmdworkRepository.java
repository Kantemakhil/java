package net.syscon.s4.sa.recordmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;

public interface CmdworkRepository {
	List<ReferenceCodes> rgWorkTypeRecordGroup();
	
	List<CmdModules> rgModulesRecordGroup(String columnName);
	
	List<BpmnProcess> rgProcessRecordGroup();
	
	List<WorkItems> workItemsExecuteQuery();
	
	Integer insertWorkItems(List<WorkItems> insertWorkItemsList);
	
	Integer updateWorkItems(List<WorkItems> updateWorkItemsList);
	
	Integer deleteWorkItems(List<WorkItems> deleteWorkItemsList);
	
	Long getWorkId(Long processId);
	
	String getRelatedDto(String moduleName);
	
	List<ModuleTriggers> rgModuleTriggersRecordGroup(); 
	
	Integer deleteTrigger(String triggerId, String modifyUserId);
	
	List<BpmnProcess> validateDeploy(BpmnProcess bpmnProcess);
	
	Integer updateWorkItemsOnProcessDeploy(List<WorkItems> updateWorkItemsList);
	Integer deleteWorkItemsByTriggerId(String triggerId);
	
	ModuleTriggers getModuleTriggerData(String triggerId);
}

