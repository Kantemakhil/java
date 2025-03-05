package net.syscon.s4.cm.programsservices;
import java.util.List;
import java.util.Map;

import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasureOutcomes;
import net.syscon.s4.cm.casemanagement.maintenance.beans.EventMeasures;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedulesCommitBean;
/**
 * Interface OcdpatteService 
 */
public interface OcdpatteService  {
	List<AgencyLocations> rgInstProviderRecordGroup(String caseLoadId) ;

	List<ReferenceCodes> rgUnderstandingRecordGroup() ;

	Integer courseScheduleStaffsCommit(CourseScheduleStaffsCommitBean commitBean) ;

	Integer deliveryDetailsCommit(CourseSchedulesCommitBean commitBean) ;

	List<ReferenceCodes> rgScheduleTypeRecordGroup() ;

	List<ReferenceCodes> rgEngagementRecordGroup() ;

	List<OffenderCourseAttendance> offCourseAttendancesExecuteQuery(OffenderCourseAttendance objOffenderCourseAttendances) ;

	List<ReferenceCodes> rgCommProviderRecordGroup(String user) ;

	List<StaffMembers> rgStaffNameRecordGroup(String progInstId) ;

	List<ReferenceCodes> rgStaffRoleRecordGroup() ;

	List<ProgramServices> rgServiceRecordGroup() ;

	List<ReferenceCodes> rgConfirmAttendanceRecordGroup() ;

	List<CourseScheduleStaff> courseScheduleStaffsExecuteQuery(CourseScheduleStaff objCourseScheduleStaffs) ;

	Integer offCourseAttendancesCommit(OffenderCourseAttendancesCommitBean commitBean) ;

	List<CourseSchedules> deliveryDetailsExecuteQuery(CourseSchedules objCourseSchedules) ;

	CourseSchedules getcourseScheduleExecuteQuery(CourseSchedules courseSchedules);

	Map checkUa(OffenderCourseAttendance searchBean);

	String getproviderType(String caseloadId);

	OffenderCourseAttendance   getActOutcomeFlag(OffenderCourseAttendance searchBean);

	Integer isStaffExists(CourseSchedules searchBean);
	
	VAddresses getProgLocation(CourseSchedules courseSchedules);

	Integer ocdpatteCommitBean(OcdpatteCommitBean commitBean);

	List<AgencyLocations> rgAgyLocsRecordGroup(String caseLoadId);

	List<TeamMembers> rgTeamAgyLocsRecordGroup(String user);

	List<TeamMembers> rgCorpLocsRecordGroup();

	List<EventMeasureOutcomes> cancelFlagOutcomeList(EventMeasures eventMeasureObj);

}
