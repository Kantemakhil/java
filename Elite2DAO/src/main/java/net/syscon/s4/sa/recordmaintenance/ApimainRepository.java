package net.syscon.s4.sa.recordmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;

public interface ApimainRepository {
	List<ActionApi> apimainExecuteQuery();
	
	Integer insertActionapi(List<ActionApi> insertList);
	
	Integer updateActionApi(List<ActionApi> updateList);
	
	Integer deleteActionApi(List<ActionApi> deleteList);
	
	List<ReferenceCodes> rgQueryKeyRecordGroup();
	
	List<ActionApi> getQuickActions();
}
