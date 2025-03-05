package net.syscon.s4.globalrbac;

import java.util.List;

import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.OmsRolesCommitBean;

/**
 * Interface OumrolesService
 */
public interface OumrolesService {
	Integer omsRoleCommit(OmsRolesCommitBean commitBean);

	List<OmsRoles> omsRoleExecuteQuery(OmsRoles objOmsRoles);

	Object omsRolePreInsert();

}
