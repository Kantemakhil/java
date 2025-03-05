package net.syscon.s4.cm.programsservices.maintenance;

import java.util.List;

/**
 * Interface OcmctoffRepository
 */
public interface OcmctoffRepository {

	Integer crPrfGdInsertCourseActivityProfiles(List<CourseActivityProfiles> lstCourseActivityProfiles);

	Integer crPrfGdDeleteCourseActivityProfiles(List<CourseActivityProfiles> lstCourseActivityProfiles);

	List<CourseActivityProfiles> crPrfGdExecuteQuery(CourseActivityProfiles objCourseActivityProfiles);

}
