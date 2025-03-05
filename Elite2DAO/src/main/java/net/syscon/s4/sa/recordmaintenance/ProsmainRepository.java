package net.syscon.s4.sa.recordmaintenance;

import java.sql.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;

public interface ProsmainRepository {

	List<BpmnProcess> processExecuteQuery();
	
	Integer insertProcess(List<BpmnProcess> lstOfProcessMain);
	
	Integer updateProcess(List<BpmnProcess> lstOfProcessMain);
	
	Integer updateBpmnProcess(byte[] fileByteArray, String processId,Date modifyDate,String modifyUser,String processDesc);
	
	Integer deleteProcess(List<BpmnProcess> lstOfProcessMain);
	
	BpmnProcess getBpmnProcess(BpmnProcess bpmnProcess);
	
	Integer updateDeployeId(BpmnProcess bpmnProcess);
	
	List<BpmnProcess> getVersionHistory(BpmnProcess bpmnProcess);
	
	BpmnProcess getProcessDetails(String deployId);
	
	String getProcDefId(String processKey);
	
	Long getInsertProcess(String triggerId);
	
	Long getUpdateProcess(String triggerId);
	
	Long getDeleteProcess(String triggerId);
	
	String getProcessKey(Long process);

	List<BpmnProcess> getProcess(BpmnProcess bpmnProcess);
	
	BpmnProcess getProcessData(Integer processId);
	
	Integer updateTrigger(Long processId);
	
	String getCamundaServerUrl();
	
	String getAppServerUrl();

	void saveCamundaErr(String errorData, String processKey, Long processId);
	
	Integer updateBulkDeployeId(List<BpmnProcess> bpmnProcessList);
	
	List<ReferenceCodes> getCommonProcess();
	
	int insertBulkProcess(List<BpmnProcess> bpmnList);
	
	Integer updateBulkProcess(List<BpmnProcess> lstOfProcessMain);
	
	Long getWorkIdByTrigger(String triggerId);
	Integer updateProcessCategory(List<BpmnProcess> lstOfProcessMain);
}
