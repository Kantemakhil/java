package net.syscon.s4.cm.programsservices;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.OffenderProgramProfiles;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;

public interface OcuoscpvRepository {
	Integer offSchDeleteVOffenderCourseEvents(List<VOffenderCourseEvents> lstVOffenderCourseEvents);

	List<OffenderCourseApptRule> offSchDefExecuteQuery(OffenderCourseApptRule objOffenderCourseApptRules);

	List<VOffenderCourseEvents> offSchExecuteQuery(VOffenderCourseEvents objVOffenderCourseEvents);

	Integer weeklyDefInsertOffenderCourseApptGrps(List<OffenderCourseApptGrp> lstOffenderCourseApptGrps);

	List<CourseActivities> crsActExecuteQuery(CourseActivities objCourseActivities);

	Integer weeklyDefUpdateOffenderCourseApptGrps(List<OffenderCourseApptGrp> lstOffenderCourseApptGrps);

	Integer offSchInsertVOffenderCourseEvents(List<VOffenderCourseEvents> lstVOffenderCourseEvents);

	List<CourseScheduleRules> crsScheduleRulExecuteQuery(CourseScheduleRules objCourseScheduleRules);

	List<OffenderCourseApptGrp> weeklyDefExecuteQuery(OffenderCourseApptGrp objOffenderCourseApptGrps);

	Integer offschdefInsertOffenderCourseApptRules(List<OffenderCourseApptRule> lstOffenderCourseApptRules);

	List<CourseActivities> gettingProgramServicePostQuerry(CourseActivities courseActivities);

	String gettingWeekdayOffSch(Date eventDate);

	Long gettingCrsApptid(VOffenderCourseEvents voffendersCourtEvents);

	Long gettingrefferenceId(VOffenderCourseEvents voffendersCourtEvents);

	Integer gettingVReturnNumberForConfict(VOffenderCourseEvents voffendersCourtEvents);

	CourseScheduleRules gettingLvDayMonday(VOffenderCourseEvents voffendersCourtEvents);

	CourseScheduleRules gettingLvDayTuesday(VOffenderCourseEvents voffendersCourtEvents);

	CourseScheduleRules gettingLvDayWednesday(VOffenderCourseEvents voffendersCourtEvents);

	CourseScheduleRules gettingLvDayThursday(VOffenderCourseEvents voffendersCourtEvents);

	CourseScheduleRules gettingLvDayFriday(VOffenderCourseEvents voffendersCourtEvents);

	CourseScheduleRules gettingLvDaySunday(VOffenderCourseEvents voffendersCourtEvents);

	CourseScheduleRules gettingLvDaySaturday(VOffenderCourseEvents voffendersCourtEvents);

	Integer gettingHolidayCountNumber(VOffenderCourseEvents voffendersCourtEvents);

	Integer weeklyDefPreDeleteQuerry(OffenderCourseApptGrp offenderCouseAppGrp);

	Integer weeklyDefDeleteOffenderCourseApptGrps(List<OffenderCourseApptGrp> lstOffenderCourseApptGrps);

	Integer OffSchDefDeleteData(List<OffenderCourseApptRule> deleteList);

	Integer OffSchDefUpdateData(List<OffenderCourseApptRule> updateList);

	List<CourseScheduleRules> gettingSheduleRulesData(CourseScheduleRules searchBean);

	Date gettingStartDate(OffenderCourseApptGrp offenderCourseAppgroup);

	void updatingDirectionCode(VOffenderCourseEvents courtEvents);

	Integer getCrsApptGrpId();

	List<OffenderProgramProfiles> offPrgProfilesExecuteQuery(OffenderProgramProfiles searchBean);
	
	Integer offPrgProfilesCommit(List<OffenderProgramProfiles> updObj);
	
	Integer offPrgProfilesStatusCommit(BigDecimal offPrgrefId,String modifyUserId);

}
