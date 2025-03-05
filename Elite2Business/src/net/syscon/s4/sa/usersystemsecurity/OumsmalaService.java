package net.syscon.s4.sa.usersystemsecurity;

import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.StaffMembersCommitBean;
import net.syscon.s4.sa.usersystemsecurity.beans.OffenderWorkAssignments;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRolesCommitBean;

/**
 * Interface OumsmalaService
 */
public interface OumsmalaService {
	List<ReferenceCodes> cgfkSlrPositionRecordGroup();

	List<StaffMembers> smExecuteQuery(StaffMembers objStaffMembers);

	List<ReferenceCodes> CgfkchkSlrCSlrSchedType(ReferenceCodes paramBean);

	Integer smCommit(StaffMembersCommitBean commitBean);

	List<ReferenceCodes> CgfkchkSlrCSlrRoleFk(ReferenceCodes paramBean);

	List<ReferenceCodes> CgfkchkSlrCSlrPosnFk(ReferenceCodes paramBean);

	List<Caseloads> cgfkSacCaseloadIdRecordGroup();

	List<StaffLocationRoles> CguvchkSlrPk(StaffLocationRoles paramBean);

	List<ReferenceCodes> cgfkSlrRoleRecordGroup();

	List<StaffLocationRoles> slrExecuteQuery(StaffLocationRoles objStaffLocationRoles);

	List<ReferenceCodes> cgfkSlrScheduleTypeRecordGroup();

	List<OffenderWorkAssignments> CgrichkStaffLocationRoles(OffenderWorkAssignments paramBean);

	List<AgencyLocations> cgfkCalAgyLocIdRecordGroup();

	StaffLocationRoles slrCommit(StaffLocationRolesCommitBean commitBean);

	List<ReferenceCodes> cgfkSlrStaffUnitRecordGroup();

	List<AgencyLocations> calExecuteQuery(AgencyLocations searchBean);

	Integer cguvchkSlrPk(StaffLocationRoles searchBean);

}
