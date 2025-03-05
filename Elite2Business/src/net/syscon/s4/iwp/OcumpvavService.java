package net.syscon.s4.iwp;

import java.util.List;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRulesCommitBean;

/**
 * Interface OcumpvavService
 */
public interface OcumpvavService {
	Integer crsScheduleRulCommit(CourseScheduleRulesCommitBean CommitBean);

	List<CourseActivities> crsActExecuteQuery(CourseActivities objCourseActivities);

	List<CourseScheduleRules> crsScheduleRulExecuteQuery(CourseScheduleRules objCourseScheduleRules);
}
