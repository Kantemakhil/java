package net.syscon.s4.globalrbac;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OmsRoles;

/**
 * Interface OumassmuRepository
 */
public interface OumassmuRepository {
	Integer modPrivDeleteModulePrivileges(List<ModulePrivileges> lstModulePrivileges);

	List<OmsRoles> rgStaffMemberRolesRoleRecordGroup();

	Integer modPrivInsertModulePrivileges(List<ModulePrivileges> lstModulePrivileges);

	ModulePrivileges cguvchkModulePrivsPk(ModulePrivileges paramBean);

	List<ReferenceCodes> cgfkModPrivAccessPrivilegeRecordGroup();

	List<OmsModules> cgfkModPrivModuleNameRecordGroup();

	List<OmsRoles> omsRoleExecuteQuery(OmsRoles objOmsRoles);

	OmsModules cgfkchkModPrivModPrivOms(OmsModules paramBean);

	List<ModulePrivileges> modPrivExecuteQuery(ModulePrivileges objModulePrivileges);

	Integer modPrivUpdateModulePrivileges(List<ModulePrivileges> lstModulePrivileges);

	ReferenceCodes cgfkchkModPrivModPrivAcc(ReferenceCodes paramBean);

}
