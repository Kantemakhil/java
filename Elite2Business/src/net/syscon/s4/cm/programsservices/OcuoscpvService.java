package net.syscon.s4.cm.programsservices;

import java.util.Date;
import java.util.List;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEventsCommitBean;

public interface OcuoscpvService {

	List<OffenderCourseApptRule> offSchDefExecuteQuery(OffenderCourseApptRule objOffender);

	List<VOffenderCourseEvents> offSchExecuteQuery(VOffenderCourseEvents objVOffender);

	Integer weeklyDefCommit(OffenderCourseApptGrpCommitBean commitBean);

	List<CourseActivities> crsActExecuteQuery(CourseActivities objCourse);

	Integer offSchCommit(VOffenderCourseEventsCommitBean commitBean);

	List<CourseScheduleRules> crsScheduleRulExecuteQuery(CourseScheduleRules objCourseSchedule);

	Integer offSchDefCommit(OffenderCourseApptRulesCommitBean commitBean);

	List<OffenderCourseApptGrp> weeklyDefExecuteQuery(OffenderCourseApptGrp OffenderAppt);

	String gettingWeekday(VOffenderCourseEvents searchBean);

	Integer gettingoffSchCheckScheduleConflict(VOffenderCourseEvents searchBean);

	Date gettingStartDate(OffenderCourseApptGrp offenderAppgroup);

	List<CourseScheduleRules> copyFromProviderAvailability(CourseScheduleRules searchBean);

	List<OffenderProgramProfiles> offPrgProfilesExecuteQuery(OffenderProgramProfiles searchBean);

	Integer offPrgProfilesCommit(List<OffenderProgramProfiles> commitBean);

}
