package net.syscon.s4.globalrbac;

import java.util.List;

import net.syscon.s4.im.beans.OmsRoles;

/**
 * Interface OumrolesRepository
 */
public interface OumrolesRepository {
	Integer omsRoleUpdateOmsRoles(List<OmsRoles> lstOmsRoles);

	Integer omsRoleDeleteOmsRoles(List<OmsRoles> lstOmsRoles);

	Object omsRolePreInsert();

	List<OmsRoles> omsRoleExecuteQuery(OmsRoles objOmsRoles);

	Integer omsRoleInsertOmsRoles(List<OmsRoles> lstOmsRoles);

}
