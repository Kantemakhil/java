package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.im.beans.CourseActivities;

/**
 * Interface OcmphmodRepository
 */
public interface OcmphmodRepository {
	List<CourseActivities> courseActivitiesExecuteQuery(CourseActivities objCourseActivities);

	CourseActivities courseActivitiesUpdateCourseActivities(List<CourseActivities> lstCourseActivities);

	List<CourseActivities> getSrgSrvDetails(CourseActivities obj);

	Long countSessions(final Long crsActyId);

	Long getCrsSessionCount(Long crsActyId);

	Long doUpdateOnCrsPhase(Long crsActyId, Long pTotal);

	CourseActivities courseActDeleteCourseActivities(List<CourseActivities> deleteList);


}
