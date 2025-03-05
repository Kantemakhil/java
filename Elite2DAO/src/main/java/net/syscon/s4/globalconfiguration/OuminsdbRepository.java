package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.common.beans.ModuleInsDashboardBean;

public interface OuminsdbRepository {
	List<ModuleInsDashboardBean> getInsModDashboard();
	
	Integer insModDashboardInsert(List<ModuleInsDashboardBean> insertList);
	
	Integer insModDashboardUpdate(List<ModuleInsDashboardBean> updateList);
	
	Integer insModDashboardDelete(List<ModuleInsDashboardBean> deleteList);
	
	Integer updateInsdbFlag(List<ModuleInsDashboardBean> moduleInsList);
	
	ModuleInsDashboardBean getInsDashboardId(String moduleName);
	
	Integer postInsert(List<ModuleInsDashboardBean> insertList);
	
	Integer postDelete(List<ModuleInsDashboardBean> deleteList);
	
	Integer offenderSpecificModuleInsert(ModuleInsDashboardBean insertList);
	
	Integer offenderSpecificModuleDelete(String moduleName);
	
	
	
	
}
