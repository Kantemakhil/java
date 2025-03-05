package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedulesCommitBean;

/**
 * Interface OcussessService
 */
public interface OcussessService {
	List<CourseSchedules> crsSchExecuteQuery(CourseSchedules objCourseSchedules);

	Integer crsSchCommit(CourseSchedulesCommitBean CommitBean);

}
