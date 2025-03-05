package net.syscon.s4.iwp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCommunityFile;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.casemanagement.beans.AssessmentSummaries;
import net.syscon.s4.sa.usersystemsecurity.beans.AssignmentTransfers;
import net.syscon.s4.sa.usersystemsecurity.beans.OffenderWorkAssignments;

public interface OcdatpowRepository {
	List<ReferenceCodes> rgPositionRecordGroup();

	List<OffenderBookings> offBkg1ExecuteQuery(OffenderBookings objOffenderBookings);

	List<Teams> rgTeamRecordGroup(String position, String role, Long staffid);

	List<TeamMembers> vOffDetExecuteQuery(TeamMembers searchBean);

	List<StaffMembers> cgfkchkStaffLrStaffLrSta(StaffMembers paramBean);

	List<ReferenceCodes> rgScheduleTypeRecordGroup();

	List<AgencyLocations> cgfklkpStaffLrStaffLrAgy(AgencyLocations paramBean);

	List<AgencyLocations> cgfkchkStaffLrStaffLrAgy(AgencyLocations paramBean);

	List<OffenderBookings> postQueryForOffbkg1(OffenderBookings ofbkngs);

	List<ReferenceCodes> rgRoleRecordGroup();

	List<ReferenceCodes> rgSexCodeRecordGroup();

	List<StaffMembers> cgfklkpStaffLrStaffLrSta(StaffMembers paramBean);

	List<TeamMembers> cgfkStaffLrDspDescriptionRecordGroup(String caseLoadType);

	List<StaffMembers> cgfkStaffLrDspLastNameRecordGroup();

	long getOfficerId(TeamMembers searchBean);

	// long officerWork(TeamMembers searchBean);

	// public long getOfficerPo(final TeamMembers searchBean);

	public Integer casePlaneCur(OffenderBookings bookings);

	public Integer nextIdCur(OffenderBookings bookings);

	public Date vsSacCur(TeamMembers membrs);

	// public Integer reviewDateCur(OffenderBookings bean);

	// public String vSupervisionLevel(OffenderBookings bean);

	public String getCreateUserCur(OffenderBookings bean);

	public Object getInstInfo(OffenderBookings bean);

	public Integer casePlanesinsert(final List<CasePlans> planes2);

	public Integer casePlanesUpdate(List<CasePlans> bean);

	public List<OffenderBookings> vsGetOffIdCur(Long offender_Book_id, Long root_offender_id);

	public boolean lvProfileValue();

	public Date getStartDate(final OffenderBookings paramBean);

	public Integer updateOffenderCommunityFiles(final OffenderCommunityFile bean);

	public List<OffenderCommunityFile> fileInfoCur(final Long offenderId);

	public Integer assignmentTransfersInsert(List<AssignmentTransfers> list);

	public Date vsSaccalCur(TeamMembers bean);

	public Integer getV_Id();

	public Boolean cgnbtSkillSubTy(BigDecimal staffId, Long offenderBookId);

	public List<OffenderWorkAssignments> vsGetPrevAssignCur(OffenderBookings bookings);

	public Long asStraSeq();

	public Integer assignmentTransfersUpdate(List<AssignmentTransfers> updateList);

	public boolean omMandatory();

	public boolean omMandatoryGrid();

	public List<AssessmentSummaries> getassessment(Long OffenderBookId, Long CasePlaneid);
}
