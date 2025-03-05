package net.syscon.s4.cm.programsservices;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.im.beans.Offenders;

/**
 * Interface OcuscupsRepository
 */
public interface OcuscupsRepository {
	Integer offCrsAttendUpdateOffenderCourseAttendances(List<OffenderCourseAttendance> lstOffenderCourseAttendances);

	List<OffenderCourseAttendance> offCrsAttendExecuteQuery(OffenderCourseAttendance objOffenderCourseAttendances);

	Offenders getOffenderDetails(OffenderCourseAttendance offenderCourseAttendance);

	BigDecimal insertCourseScheduleCatchUp(OffenderCourseAttendance courseSchedules);

	int insOffCrsAttCatchup(OffenderCourseAttendance courseSchedules);

}
