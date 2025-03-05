package net.syscon.s4.globalrbac;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.ModulePrivilegesCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.OmsRolesCommitBean;

/**
 * Interface OumassmuService
 */
public interface OumassmuService {
	List<Object> cgwhenNewFormInstance();

	List<OmsRoles> rgStaffMemberRolesRoleRecordGroup();

	Integer modPrivCommit(ModulePrivilegesCommitBean commitBean);

	OmsModules cgfkchkModPrivModPrivOms(OmsModules paramBean);

	Integer omsRoleCommit(OmsRolesCommitBean commitBean);

	List<ReferenceCodes> cgfkModPrivAccessPrivilegeRecordGroup();

	List<OmsModules> cgfkModPrivModuleNameRecordGroup();

	List<OmsRoles> omsRoleExecuteQuery(OmsRoles objOmsRoles);

	ModulePrivileges cguvchkModulePrivsPk(ModulePrivileges paramBean);

	List<ModulePrivileges> modPrivExecuteQuery(ModulePrivileges objModulePrivileges);

}
