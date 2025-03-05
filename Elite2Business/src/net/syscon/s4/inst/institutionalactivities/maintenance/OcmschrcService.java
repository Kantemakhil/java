package net.syscon.s4.inst.institutionalactivities.maintenance;
import java.util.List;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRulesCommitBean;

/**
 * Interface OcmschrcService 
 */
public interface OcmschrcService  {
	CourseScheduleRules crsScheduleRulCommit(final CourseScheduleRulesCommitBean CommitBean) ;

	List<CourseScheduleRules> crsScheduleRulExecuteQuery(final CourseScheduleRules CourSchRules) ;

	CourseScheduleRules buildRecurringSchedule(final CourseScheduleRules CourSchRules);
	
	Long getProfileValues();
	
	String getPrgSrvDetails(final Long programId);
	
	String getHolidayFlag(final CourseActivities crsactModel);

}
