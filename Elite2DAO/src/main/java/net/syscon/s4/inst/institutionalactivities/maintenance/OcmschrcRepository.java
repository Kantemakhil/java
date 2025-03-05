package net.syscon.s4.inst.institutionalactivities.maintenance;
import java.util.List;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;

/**
 * Interface OcmschrcRepository
 */
public interface OcmschrcRepository {
	Integer crsScheduleRulInsertCourseScheduleRules(final List<CourseScheduleRules> courSchRulList) ;

	Integer crsScheduleRulUpdateCourseScheduleRules(final List<CourseScheduleRules> courSchRulList) ;

	List<CourseScheduleRules> crsScheduleRulExecuteQuery(final CourseScheduleRules courSchRules) ;

	Integer crsScheduleRulDeleteCourseScheduleRules(final List<CourseScheduleRules> courSchRulList) ;

	Integer crsActUpdateCourseActivities(final List<CourseActivities> CourActList) ;
	
	CourseScheduleRules buildRecurringSchedule(final CourseScheduleRules courSchRules);
	
	Long getProfileValues();
	
	String getPrgSrvDetails(final Long programId);

	String getFlag(final Long crsActyId);

}
