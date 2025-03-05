package net.syscon.s4.sa.recordmaintenance;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import net.syscon.s4.common.beans.ReferenceCodes;
public interface ProsmainService {
	List<BpmnProcess> processExecuteQuery();

	Integer processCommit(BpmnProcessCommitBean commitBean);
	
	Integer deployeBpmn(BpmnProcessCommitBean commitBean, String path);
	
	List<BpmnProcess> getVersionHistory(BpmnProcess bpmnProcess);
	
	void enableTriggers(Object commitBean, String authorization, String triggerId);

	List<BpmnProcess> getProcess(BpmnProcess bpmnProcess);
	
	BpmnProcess getProcessData(String processId);
	byte[] exportProcesses(List<BpmnProcess> lstOfProcessMain, String savedLocation) throws Exception;
	
	List<Map<String,Object>> importProcesses(MultipartFile zipFile, String savedLocation) throws Exception;

	Map<String,Object> deployBulkProcesses(List<BpmnProcess> bpmnList, String path);

	List<ReferenceCodes> getCommonProcess();
	
	int processBulkCommit(BpmnProcessCommitBean bpmnProcessCommitBean);

	void sentCalcTrigger(Object commitBean, String authorization, String triggerId);
}
