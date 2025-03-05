package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.OmsModulesCommitBean;

public interface OumrelmdService {

	List<OmsModules> getRelatedModulesByModuleName(String moduleName);

	List<OmsModules> getRelatedModulesLov(String moduleName);
	
	Integer insertUpdateDelete(OmsModulesCommitBean commitBean);

}
