package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.im.beans.OmsModules;

public interface OumrelmdRepository {

	List<OmsModules> getRelatedModulesByModuleName(String moduleName);
	
	List<OmsModules> getRelatedModulesLov(String moduleName);
	
	Integer insertRelatedModules(List<OmsModules> insertList);
	
	Integer updateRelatedModules(List<OmsModules> updateList);
	
	Integer deleteRelatedModules(List<OmsModules> deleteList);
}
