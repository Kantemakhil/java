package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.common.beans.ReferenceCodes;

/**
 * Interface OcupatofService
 */
public interface OcupatofService {
	List<ReferenceCodes> rgUnderstandingRecordGroup();

	List<ReferenceCodes> rgAttendancyViewRecordGroup();

	Integer offCrsAttCommit(final OffenderCourseAttendancesCommitBean CommitBean);

	List<ReferenceCodes> rgAttendancesRecordGroup();

	List<OffenderCourseAttendance> offCrsAttExecuteQuery(final OffenderCourseAttendance objO);

	List<ReferenceCodes> rgEngagementRecordGroup();

}
