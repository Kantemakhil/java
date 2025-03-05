package net.syscon.s4.sa.recordmaintenance;

import java.util.Map;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OffAllowPayDetailsCommitBean;

public interface CmdAsyncActionsService {
	
	public void updateLegalsData(String query, String authorization, String modifyUserId);
	
	public String remissionDueNotify(Map<String, Object> newMemoModel);
	
	public String saveOffAllowPayDetValues(OffAllowPayDetailsCommitBean commitBean);
}
