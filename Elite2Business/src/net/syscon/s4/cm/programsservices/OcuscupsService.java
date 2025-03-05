package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.common.beans.OffenderCourseAttendance;

/**
 * Interface OcuscupsService
 */
public interface OcuscupsService {
	Integer offCrsAttendCommit(OffenderCourseAttendancesCommitBean commitBean);

	List<OffenderCourseAttendance> offCrsAttendExecuteQuery(OffenderCourseAttendance objOffenderCourseAttendances);

}
