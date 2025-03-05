package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.OmsModulesCommitBean;
import net.syscon.s4.sa.admin.beans.RoleInaccessibleRefCodes;
import net.syscon.s4.sa.admin.beans.RoleInaccessibleRefCodesCommitBean;

/**
 * Interface OumrestaService
 */
public interface OumrestaService {
    List<ReferenceCodes> cgfkRleInarc1CodeRecordGroup(String codeInput);

    List<RoleInaccessibleRefCodes> rleInarcRircExecuteQuery(RoleInaccessibleRefCodes objRoleInaccessibleRefCodes);

    Integer rleInarcRircCommit(RoleInaccessibleRefCodesCommitBean CommitBean);

    List<OmsModules> rleInarcExecuteQuery(OmsModules objOmsModules);

    List<ReferenceCodes> cgfkRleInarcModuleNameRecordGroup();

    List<ModulePrivileges> modPrivExecuteQuery(ModulePrivileges objModulePrivileges);

    Integer rleInarcCommit(OmsModulesCommitBean commitBean);

    List<ReferenceCodes> cgfkRleInarc1DomainRecordGroup();

}
