package net.syscon.s4.sa.recordmaintenance;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;

public interface CmdactionRepository {
	List<AutomationApiQuery> quickActionsExecuteQuery();
	
	Integer insertQuickActions(List<AutomationApiQuery> insertList);
	
	Integer updateQuickActions(List<AutomationApiQuery> updateList);
	
	Integer deleteQuickActions(List<AutomationApiQuery> deleteList);
	
	List<AutomationQueryParameters> parametersExecuteQuery(AutomationApiQuery searchBean);
	
	Integer insertParameters(List<AutomationQueryParameters> insertList);
	
	Integer updateParameters(List<AutomationQueryParameters> updateList);
	
	Integer deleteParameters(List<AutomationQueryParameters> deleteList);
	
	String getActionQuery(String queryKey);
	
	List<AutomationQueryParameters> getParamList(String queryKey);
	
	Integer executeUpdateQuery(Map<String, Object> queryData, String query);
	
	List<Map<String, Object>> executeSelectQuery(Map<String, Object> objMap, String query);
	
	List<ReferenceCodes> rgApiIdRecordGroup();
	
	Integer batchUpdateQuery(List<Map<String, Object>> queryData, String query);
	
	List<ActionApi> preDelete(AutomationApiQuery automationApiQuery);

	byte[] templateText(Map<String, Object> objMap, String query);
	
	List<AutomationApiQuery> getquickAction(String queryKey);
	
	List<AutomationQueryParameters> getActionParams(String queryKey);
	
	List<AutomationApiQuery> getQuickAction(String queryKey);
	
	List<AutomationQueryParameters> getActionParameters(String queryKey);
	
	List<ActionApi> getActionApi(String queryKey);
	
	Integer insertActionApi(List<ActionApi> apiInsertList);
	
	Integer updateActionApi(List<ActionApi> apiUpdateList);
	
	Integer deleteParams(List<AutomationApiQuery> automationApiQuery);
	
	SentenceCalcTypes getSentenceCalcTypes(SentenceCalcTypes sentenceCalcTypes);
	
	OffenderBookings getBookingData(long offenderBookId);
	
	Integer updateSentStatus(List<OffenderBookings> offenderBookingsList);
}
