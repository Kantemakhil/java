package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;

/**
 * Interface OcumpvavRepository
 */
public interface OcumpvavRepository {
	Integer crsScheduleRulInsertCourseScheduleRules(List<CourseScheduleRules> lstCourseScheduleRules);

	Integer crsScheduleRulUpdateCourseScheduleRules(List<CourseScheduleRules> lstCourseScheduleRules);

	List<CourseActivities> crsActExecuteQuery(CourseActivities objCourseActivities);

	List<CourseScheduleRules> crsScheduleRulExecuteQuery(CourseScheduleRules objCourseScheduleRules);

	Integer crsScheduleRulDeleteCourseScheduleRules(List<CourseScheduleRules> lstCourseScheduleRules);

	String getPrgSrvDetails(Long programId);
	
	Integer holidayFlagUpdateCourseActivities(List<CourseActivities> lstCourseActivities) ;
}
