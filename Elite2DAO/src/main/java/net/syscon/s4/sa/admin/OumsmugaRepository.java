package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.OmsRoles;

/**
 * Interface OumsmugaRepository
 */
public interface OumsmugaRepository {

	List<StaffMemberRoles> staffRoleExecuteQuery(StaffMemberRoles objStaffMemberRoles);

	Integer staffRoleInsertStaffMemberRoles(List<StaffMemberRoles> lstStaffMemberRoles);

	Integer staffRoleUpdateStaffMemberRoles(List<StaffMemberRoles> lstStaffMemberRoles);

	List<OmsRoles> omsRoleExecuteQuery(OmsRoles objOmsRoles);

	List<ReferenceCodes> cgfkStaffRoleDspUserIdRecordGroup();

	Integer staffRoleDeleteStaffMemberRoles(List<StaffMemberRoles> lstStaffMemberRoles);

	List<StaffMembers> getStaffUserId(int staffId);

	Long getStaffId(String userId);


}
