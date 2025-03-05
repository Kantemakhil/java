package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.StaffMemberRolesCommitBean;

/**
 * Interface OumsmugaService
 */
public interface OumsmugaService {

	List<StaffMemberRoles> staffRoleExecuteQuery(StaffMemberRoles objStaffMemberRoles);

	StaffMemberRoles staffRoleCommit(StaffMemberRolesCommitBean CommitBean);

	List<OmsRoles> omsRoleExecuteQuery(OmsRoles objOmsRoles);

	List<ReferenceCodes> cgfkStaffRoleDspUserIdRecordGroup();

}
