package net.syscon.s4.sa.admin;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.sa.admin.beans.RoleInaccessibleRefCodes;

/**
 * Interface OumrestaRepository
 */
public interface OumrestaRepository {
    List<ReferenceCodes> cgfkRleInarc1CodeRecordGroup(String codeInput);

    List<RoleInaccessibleRefCodes> rleInarcRircExecuteQuery(RoleInaccessibleRefCodes objRoleInaccessibleRefCodes);

    Integer rleInarc1DeleteRoleInaccessibleRefCodes(List<RoleInaccessibleRefCodes> lstRoleInaccessibleRefCodes);

    Integer rleInarc1InsertRoleInaccessibleRefCodes(List<RoleInaccessibleRefCodes> lstRoleInaccessibleRefCodes);

    List<OmsModules> rleInarcExecuteQuery(OmsModules objOmsModules);

    Integer rleInarcInsertOmsModules(List<OmsModules> lstOmsModules);

    Integer rleInarcUpdateOmsModules(List<OmsModules> lstOmsModules);

    List<ReferenceCodes> cgfkchkRleInarc1RleIna2(ReferenceCodes paramBean);

    List<ReferenceCodes> cgfkRleInarcModuleNameRecordGroup();

    List<OmsModules> cgfkchkRleInarcRleInarc(OmsModules paramBean);

    List<ModulePrivileges> modPrivExecuteQuery(ModulePrivileges objModulePrivileges);

    List<OmsRoles> cgfkchkModPrivModPrivOms(OmsRoles paramBean);

    List<ModulePrivileges> rleInarcOnCheckDeleteMaster(ModulePrivileges paramBean);

    List<OmsModules> createFormGlobalsCREATE_FORM_GLOBALS(OmsModules paramBean);

    List<ReferenceCodes> cgfkchkRleInarc1RleInarc(ReferenceCodes paramBean);

    List<ReferenceCodes> cgfkRleInarc1DomainRecordGroup();

    String getRoleName(Long roleId);
    
	Date getCurrentDate();

}
