package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.cm.primaryofficeragentassignment.beans.TransferBWOfficerCommitBean;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;

/**
 * Interface OcdtapowService
 */
public interface OcdtapowService {

	List<OffenderBookings> offBkg1ExecuteQuery(StaffMembers objOffBookings);

	List<ReferenceCodes> cgfkVOffDetSkillSubTypeRecordGroup();

	List<StaffLocationRoles> staffLrExecuteQuery(StaffLocationRoles objStaffLRoles);

	List<ReferenceCodes> cgfkVOffDetSkillTypeRecordGroup();

	List<AgencyLocations> cgfkStaffLrDspDescriptionRecordGroup(String caseloadId);

	List<StaffMembers> cgfkStaffLrDspLastNameRecordGroup();

	TransferBWOfficerCommitBean saveData(TransferBWOfficerCommitBean commitBean);

}
