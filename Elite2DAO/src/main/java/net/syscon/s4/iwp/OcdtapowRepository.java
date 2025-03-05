package net.syscon.s4.iwp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderCommunityFile;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.workflow.maintenance.beans.StaffLocationRoles;
import net.syscon.s4.sa.usersystemsecurity.beans.AssignmentTransfers;
import net.syscon.s4.sa.usersystemsecurity.beans.OffenderWorkAssignments;

/**
 * Interface OcdtapowRepository
 */
public interface OcdtapowRepository {

	List<OffenderBookings> offBkg1ExecuteQuery(StaffMembers objOffBokings);

	List<ReferenceCodes> cgfkVOffDetSkillSubTypeRecordGroup();

	List<StaffLocationRoles> staffLrExecuteQuery(StaffLocationRoles objStaffLRoles);

	List<ReferenceCodes> cgfkVOffDetSkillTypeRecordGroup();

	List<AgencyLocations> cgfkStaffLrDspDescriptionRecordGroup(String caseLaadId);

	List<StaffMembers> cgfkStaffLrDspLastNameRecordGroup();

	Offenders getOffenderDetails(OffenderBookings offenderBookings);

	Date getStartDate(OffenderBookings offenderBookings);

	String getpreviosWorkData(StaffLocationRoles searchRecord);

	BigDecimal getcaseOfficerId(OffenderBookings offenderBookings);

	String officerWork(StaffLocationRoles staffLocationRole);

	String getNoOffender(StaffLocationRoles staffLocationRole);

	Long getCasePlanId(Long offenderBookId);

	Date getFromDate(StaffLocationRoles staffLocationRole);

	String getSupLevel(Long offenderBookId);

	int getreviewPeriod(String supervisionLevel);

	Integer casePlanInsert(CasePlans casePlans);

	Integer insertAssessmentSummaries(CasePlans casePlans);

	Integer insertplanDetails(CasePlans casePlans);

	Integer insertCaseWorkSteps(CasePlans casePlans);

	OffenderWorkAssignments getPreWorkAssignemetDet(Long offenderBookId, Integer staffId);

	Integer insertAssignmentTransfers(AssignmentTransfers casePlans);

	Long getOffasIdSeq();

	int updateWorkAssigments(OffenderWorkAssignments offWorkAssign);

	OffenderCommunityFile getOffenderFileDetails(BigDecimal offenderId);

	String getProfileValue();

	void updateCommunityFinanceFiles(OffenderCommunityFile offCommFile);

	Integer pimsFileTracking(OffenderCommunityFile offTrans, StaffMembers staffMembers);

	Integer updatecasePlans(CasePlans casePlans, Long offenderBookId);

	void updateOffenderBookings(CasePlans casePlans, BigDecimal offenderId);

	String casePlanPreInsert(Long planId, Long offenderBookId);
	
	BigDecimal getaliasOffenderId(OffenderBookings offenderBookings);

}
