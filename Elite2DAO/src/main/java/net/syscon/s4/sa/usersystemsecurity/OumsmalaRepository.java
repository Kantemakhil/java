package net.syscon.s4.sa.usersystemsecurity;

import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.usersystemsecurity.beans.AssignmentTransfers;
import net.syscon.s4.sa.usersystemsecurity.beans.OffenderWorkAssignments;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;

/**
 * Interface OumsmalaRepository
 */
public interface OumsmalaRepository {
	List<ReferenceCodes> cgfkSlrPositionRecordGroup();

	List<StaffMembers> smExecuteQuery(StaffMembers objStaffMembers);

	StaffLocationRoles slrInsertStaffLocationRoles(List<StaffLocationRoles> lstStaffLocationRoles);

	StaffLocationRoles slrUpdateStaffLocationRoles(List<StaffLocationRoles> lstStaffLocationRoles);

	List<ReferenceCodes> cgfkchkSlrCSlrSchedType(ReferenceCodes paramBean);

	List<Caseloads> cgfkSacCaseloadIdRecordGroup();

	List<OffenderWorkAssignments> cgrichkStaffLocationRoles(OffenderWorkAssignments paramBean);

	List<ReferenceCodes> cgfkchkSlrCSlrPosnFk(ReferenceCodes paramBean);

	List<AssignmentTransfers> cgrichkStaffLocationRoles(AssignmentTransfers paramBean);

	List<ReferenceCodes> cgfkSlrRoleRecordGroup();

	List<StaffLocationRoles> slrExecuteQuery(StaffLocationRoles objStaffLocationRoles);

	List<ReferenceCodes> cgfkSlrScheduleTypeRecordGroup();

	List<AgencyLocations> cgfkCalAgyLocIdRecordGroup();

	List<ReferenceCodes> cgfkchkSlrCSlrRoleFk(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkSlrStaffUnitRecordGroup();

	List<AgencyLocations> cgfkchkCalCsldAlAgyLoc(AgencyLocations paramBean);

	Integer cguvchkSlrPk(StaffLocationRoles paramBean);

	List<AgencyLocations> calExecuteQuery(AgencyLocations searchRecord);


}
