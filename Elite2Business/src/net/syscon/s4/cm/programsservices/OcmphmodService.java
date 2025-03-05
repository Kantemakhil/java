package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.CourseActivitiesCommitBean;

/**
 * Interface OcmphmodService
 */
public interface OcmphmodService {
	List<CourseActivities> courseActivitiesExecuteQuery(CourseActivities objCourseActivities);

	CourseActivities courseActivitiesCommit(CourseActivitiesCommitBean CommitBean);

}
