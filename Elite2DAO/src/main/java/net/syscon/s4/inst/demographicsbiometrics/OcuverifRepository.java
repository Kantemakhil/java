package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.casemanagement.beans.CasePlans;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogs;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;

/**
 * Interface OcuverifRepository
 */
public interface OcuverifRepository {
	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	List<WorkFlowLogs> workFlExecuteQuery(WorkFlows searchBean);

	List<WorkFlows> workFlowsExecuteQuery(OffenderAlerts objSearchDao);

	Integer workFlCommit(List<WorkFlowLogs> insertList);

	Integer preInsert(WorkFlowLogs obj);

	Integer offAlertUpdate(List<OffenderAlerts> updateList);
	
	String getUserName(String userId);
	
	Integer workFlUpdate(List<WorkFlowLogs> updateList);
	
   Integer workFlInsert(List<WorkFlowLogs> insertList);
   
   Integer updateNextReviewDate(CasePlans casePlans);
   
   String getOffenderSecurityLevel(Long offenderBookId,String caseloadType);
   
   Integer getReviewDays(String securityLevel);


}
