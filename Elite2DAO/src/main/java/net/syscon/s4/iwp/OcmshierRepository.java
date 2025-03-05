package net.syscon.s4.iwp;

import java.util.List;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;

/**
 * Interface OcmshierRepository
 */

public interface OcmshierRepository {

	List<AgencyLocations> calAgyLocIdRecordGroup(final String caseloadId);

	List<StaffMembers> staffLrDspLastNameRecordGroup(final String agyLocID);

	List<ReferenceCodes> staffLrPositionRecordGroup();

	List<ReferenceCodes> staffLrRoleRecordGroup();

	List<ReferenceCodes> staffLrScheduleTypeRecordGroup();

	List<StaffMembers> staffLr1DspLastNameRecordGroup(String agyLocIdLov);

	List<StaffLocationRoles> staffLrExecuteQuery(StaffLocationRoles objStaffLR);

	Integer deleteStaffLr1Officer(final StaffLocationRoles staffLR);

	AgencyLocations cgfkchkCalCsldAlAgyLoc(AgencyLocations paramBean);

	ReferenceCodes cgfkchkStaffLr1StaffLrRe(ReferenceCodes paramBean);

	ReferenceCodes cgfkchkStaffLr1StaffLr3(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkStaffLr1PositionRecordGroup();

	List<StaffLocationRoles> staffLr1ExecuteQuery(StaffLocationRoles searchRecord);

	ReferenceCodes cgfkchkStaffLrStaffLr(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkStaffLr1RoleRecordGroup();

	Integer calInsertCaseloadAgencyLocations(List<CaseloadAgencyLocations> lstCaseloadAL);

	StaffMembers cgfkchkStaffLrStaffLrSta(StaffMembers paramBean);

	StaffMembers cgfklkpStaffLrStaffLrSta(StaffMembers paramBean);

	StaffMembers cgfklkpStaffLr1StaffLrSt(StaffMembers paramBean);

	List<ReferenceCodes> cgfkStaffLr1ScheduleTypeRecordGroup();

	StaffMembers cgfkchkStaffLr1StaffLrSt(StaffMembers paramBean);

	ReferenceCodes cgfkchkStaffLrStaffLrRef(ReferenceCodes paramBean);

	List<CaseloadAgencyLocations> calExecuteQuery(CaseloadAgencyLocations objCaseloadAL);

	ReferenceCodes cgfkchkStaffLr1StaffLr2(ReferenceCodes paramBean);

}
