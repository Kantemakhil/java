package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;

/**
 * Interface OcussessRepository
 */
public interface OcussessRepository {
	List<CourseSchedules> crsSchExecuteQuery(CourseSchedules objCourseSchedules);

}
