package net.syscon.s4.sa.recordmaintenance;

import java.sql.Blob;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;

public interface DmnmainRepository {

	List<DmnProcess> dmnsExecuteQuery();
	
	Integer insertDecision(List<DmnProcess> lstOfProcessMain);
	
	Integer updateDecision(List<DmnProcess> lstOfProcessMain);
	
	
	Integer deleteDecision(List<DmnProcess> lstOfProcessMain);
	
	DmnProcess getDmnProcess(DmnProcess DmnDecision);
	
	Integer updateDeployeId(DmnProcess DmnDecision);
	
	List<DmnProcess> getVersionHistory(DmnProcess DmnDecision);
	
	
	String getProcDefId(String processKey);
	
	String getInsertDecision(String moduleName);
	
	String getUpdateDecision(String moduleName);
	
	String getDeleteDecision(String moduleName);
	
	String getDecisionKey(String process);
	
	List<ReferenceCodes> getDmnsDeployedList();
	
	List<DmnProcess> getDmnDataByDmnDesc(DmnProcess DmnDecision);
	
	 Blob getDmnFile(String dmnProcessKey);
	 
	 Integer updateDecisionProcessCategory(List<DmnProcess> lstOfProcessMain);
}
