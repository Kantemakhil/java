package net.syscon.s4.sa.recordmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;

public interface ApimainService {
	List<ActionApi> apimainExecuteQuery();
	
	Integer apimainCommit(ActionApiCommitBean commitBean);
	
	List<ReferenceCodes> rgQueryKeyRecordGroup();
	
	List<ActionApi> getQuickActions();
	
	List<ReferenceCodes> rgUrlKeyRecordGroup();
}
