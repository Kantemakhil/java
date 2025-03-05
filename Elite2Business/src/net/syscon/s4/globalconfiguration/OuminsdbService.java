package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.common.beans.ModuleInsDashboardBean;
import net.syscon.s4.common.beans.ModuleInsDashboardCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;

public interface OuminsdbService {
	List<ModuleInsDashboardBean> getInsModDashboard();
	
	Integer InsModDashboardCommit(ModuleInsDashboardCommitBean commitBean);
	
	ModuleInsDashboardBean getInsDashboardId(String moduleName);
	
	List<ReferenceCodes> getInsDashboardList();
}
