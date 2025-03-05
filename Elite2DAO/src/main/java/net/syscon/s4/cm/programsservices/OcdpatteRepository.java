package net.syscon.s4.cm.programsservices;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;

/**
 * Interface OcdpatteRepository
 */
public interface OcdpatteRepository {
	Integer courseScheduleStaffsUpdateCourseScheduleStaffs(List<CourseScheduleStaff> lstCourseScheduleStaffs);

	List<AgencyLocations> rgInstProviderRecordGroup(String caseLoadId);

	List<ReferenceCodes> rgUnderstandingRecordGroup();

	Integer deliveryDetailsUpdateCourseSchedules(List<CourseSchedules> lstCourseSchedules);

	Integer courseScheduleStaffsInsertCourseScheduleStaffs(List<CourseScheduleStaff> lstCourseScheduleStaffs);

	List<ReferenceCodes> rgScheduleTypeRecordGroup();

	List<ReferenceCodes> rgEngagementRecordGroup();

	List<OffenderCourseAttendance> offCourseAttendancesExecuteQuery(
			OffenderCourseAttendance objOffenderCourseAttendances);

	List<ReferenceCodes> rgCommProviderRecordGroup(String user);

	List<StaffMembers> rgStaffNameRecordGroup(BigDecimal bigDecimal);

	Integer offCourseAttendancesUpdateOffenderCourseAttendances(
			List<OffenderCourseAttendance> lstOffenderCourseAttendances);

	List<ReferenceCodes> rgStaffRoleRecordGroup();

	List<ProgramServices> rgServiceRecordGroup();

	List<ReferenceCodes> rgConfirmAttendanceRecordGroup();

	List<CourseScheduleStaff> courseScheduleStaffsExecuteQuery(CourseScheduleStaff objCourseScheduleStaffs);

	Integer courseScheduleStaffsDeleteCourseScheduleStaffs(List<CourseScheduleStaff> lstCourseScheduleStaffs);

	List<CourseSchedules> deliveryDetailsExecuteQuery(CourseSchedules objCourseSchedules);

	List<VAcpSchedules> getcourseSchedule(CourseSchedules courseSchedules);

	Date getReviewDate(CourseSchedules searchRecord);

	Map checkUa(OffenderCourseAttendance searchBean);

	String getproviderType(String caseloadId);

	OffenderCourseAttendance getActOutcomeFlag(OffenderCourseAttendance searchBean);

	OffenderCourseAttendance getoffcourseattDetails(OffenderCourseAttendance offenderCourseAttendance);

	String getPaylock(long eventId);

	String adjustUa(OffenderCourseAttendance offenderCourseAttendance);

	Offenders getOffenderDetails(OffenderCourseAttendance offenderCourseAttendance);

	String checkUaCheckUaEventOutcome(OffenderCourseAttendance searchBean);

	Integer isStaffExists(CourseSchedules searchBean);

	CourseActivities getcrsactivityDetails(CourseSchedules courseSchedules);

	VAddresses getprogLoc(Long servicesAddressId);

	String getInternalLocationDescription(Long internalLocationId);

	VOffenderSentenceEvents getOffenderSentenceEvents(OffenderCourseAttendance searchBean);

	Integer getOffenderSentenceUa(VOffenderSentenceEvents events);

	List<AgencyLocations> rgAgyLocsRecordGroup(String caseLoadId);

	List<TeamMembers> rgTeamAgyLocsRecordGroup(String user);

	List<TeamMembers> rgCorpLocsRecordGroup();

	List<EventMeasureOutcomes> cancelFlagOutcomeList(EventMeasures eventMeasureObj);

}
