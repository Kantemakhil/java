package net.syscon.s4.iwp;

import java.util.List;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;

/**
 * Interface OcmshierService
 */
public interface OcmshierService {

	List<AgencyLocations> calAgyLocIdRecordGroup(final String caseloadId);

	List<StaffMembers> staffLrDspLastNameRecordGroup(final String agyLocIdLov);

	List<ReferenceCodes> staffLrPositionRecordGroup();

	List<ReferenceCodes> staffLrRoleRecordGroup();

	List<ReferenceCodes> staffLrScheduleTypeRecordGroup();

	List<StaffMembers> staffLr1DspLastNameRecordGroup(String agyLocIdLov);

	List<StaffLocationRoles> staffLrExecuteQuery(StaffLocationRoles objStaffLR);

	List<StaffLocationRoles> staffLr1ExecuteQuery(StaffLocationRoles searchRecord);

	List<ReferenceCodes> cgfkStaffLr1PositionRecordGroup();

	Integer stafflr1Commit(StaffLocationRoles staffLRole);

	List<ReferenceCodes> cgfkStaffLr1RoleRecordGroup();

	List<ReferenceCodes> cgfkStaffLr1ScheduleTypeRecordGroup();

	List<CaseloadAgencyLocations> calExecuteQuery(CaseloadAgencyLocations objCase);

}
