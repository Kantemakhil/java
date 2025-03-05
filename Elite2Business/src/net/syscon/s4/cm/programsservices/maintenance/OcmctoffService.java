package net.syscon.s4.cm.programsservices.maintenance;

import java.util.List;

/**
 * Interface OcmctoffService
 */
public interface OcmctoffService {

	List<CourseActivityProfiles> crPrfGdExecuteQuery(CourseActivityProfiles objCourseActivityProfiles);

	Integer crPrfGdCommit(CourseActivityProfilesCommitBean commitBean);

}
