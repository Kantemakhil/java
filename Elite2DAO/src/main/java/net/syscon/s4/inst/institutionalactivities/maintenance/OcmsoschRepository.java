package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.util.Date;
import java.util.List;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;

/**
 * Interface OcmsoschRepository
 */
public interface OcmsoschRepository {
	Integer courseSchedUpdateCourseSchedules(List<CourseSchedules> lstCrsSchedules);

	Integer courseSchedInsertCourseSchedules(final List<CourseSchedules> lstCrsSchedules);

	Integer courseSchedDeleteCourseSchedules(List<CourseSchedules> lstCrsSchedules);

	String getDate(final CourseSchedules courseSchedules);

	List<CourseSchedules> courseSchedExecuteQuery(CourseSchedules crsSchedules);

	String clearSchedules(CourseSchedules courseSchedules);

	String diffBetweenDates(Date startTime,Date endTime);

}
