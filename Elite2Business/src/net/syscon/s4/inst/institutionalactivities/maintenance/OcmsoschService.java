package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.util.List;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedulesCommitBean;

/**
 * Interface OcmsoschService
 */
public interface OcmsoschService {

	Integer courseSchedCommit(CourseSchedulesCommitBean commitBean);

	String getDate(final CourseSchedules courseSchedules);

	List<CourseSchedules> courseSchedExecuteQuery(CourseSchedules courseSchedules);

	String clearSchedules(CourseSchedules courseSchedules);
}
