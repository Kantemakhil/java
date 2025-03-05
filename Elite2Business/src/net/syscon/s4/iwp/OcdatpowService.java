package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.Teams;

public interface OcdatpowService {

	List<ReferenceCodes> rgPositionRecordGroup();

	Integer offBkg1Commit(OffenderBookingsCommitBean commitBean);

	List<OffenderBookings> offBkg1ExecuteQuery(OffenderBookings objOffenderBookings);

	List<Teams> rgTeamRecordGroup(String sealFlag);
	
	Boolean omMandatoryGrid();

	List<TeamMembers> vOffDetExecuteQuery(TeamMembers searchBean);

	List<ReferenceCodes> rgScheduleTypeRecordGroup();

	List<ReferenceCodes> rgRoleRecordGroup();

	List<ReferenceCodes> rgSexCodeRecordGroup();

	List<TeamMembers> cgfkStaffLrDspDescriptionRecordGroup(String caseLoadType);

	List<StaffMembers> cgfkStaffLrDspLastNameRecordGroup();

}
