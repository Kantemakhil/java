package net.syscon.s4.sa.recordmaintenance;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetailsCommitBean;

public interface CmdactionService {
	
	List<ReferenceCodes> rgApiIdRecordGroup();
	
	List<AutomationApiQuery> quickActionsExecuteQuery();
	
	Integer commitQuickActions(AutomationApiQueryCommitBean commitBean);
	
	List<AutomationQueryParameters> parametersExecuteQuery(AutomationApiQuery searchBean);
	
	Integer commitParameters(AutomationQueryParametersCommitBean commitBean);
	
	String executeUpdateQuery(Map<String, Object> queryData);
	
	List<Map<String, Object>> executeSelectQuery (Map<String, Object> queryData, String authorization);
	
	String batchUpdateQuery(Map<String, Object> queryData);
	
	List<ReferenceCodes> rgParameterRecordGroup();

	String templateText(Map<String, Object> queryData);
	
	byte[] exportActions(List<ActionApi> lstOfActionApi, String savedLocation) throws Exception;
	
	Map<String,Object> importActions(MultipartFile zipFile, String savedLocation) throws Exception;
	
	Integer updateActions(Map<String, Object> obj, String userName);
	
	Map<String, Object> getSeqQuery(Map<String, Object> mapObject);
	
	String caclucateSentStatus(OdynfrmSubmitDataBean odynfrmSubmitDataBeanSum, long rootOffenderId);
	
	Integer custodyStatusUpdate(String offenderBookId, String authorization);
	
	Integer sentStatusUpdate(String offenderBookId);
	
	Integer sentStatusUpdatewithStatus(String offenderBookId);
	
	String remissionDueNotify(Map<String, Object> newMemoModel);
	
	void saveOffAllowPayDetValues(OffAllowPayDetailsCommitBean commitBean);
}
