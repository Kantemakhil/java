package net.syscon.s4.inmate.trust.financialreports;

import java.util.List;

import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OcusrepsRepository
 */
public interface OcusrepsRepository {
	Integer omsModulesInsertOmsModules(List<OmsModules> lstOmsModules);

	Integer omsModulesUpdateOmsModules(List<OmsModules> lstOmsModules);

	Integer omsModulesDeleteOmsModules(List<OmsModules> lstOmsModules);

	OmsModules createFormGlobals(OmsModules paramBean);

	List<OmsModules> omsModulesExecuteQuery(OmsModules objOmsModules);

}
