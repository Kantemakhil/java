package net.syscon.s4.pkgs.ocupaoff;

import net.syscon.s4.common.beans.OffenderCourseAttendance;

public interface OcupaoffPkgRepository {

	Integer updateVOffCourseEvents(final OffenderCourseAttendance bean);

	Integer updateCourseAttendance(final OffenderCourseAttendance bean);
}