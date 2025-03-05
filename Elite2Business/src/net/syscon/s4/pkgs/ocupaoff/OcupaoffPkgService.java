package net.syscon.s4.pkgs.ocupaoff;

import net.syscon.s4.common.beans.OffenderCourseAttendance;

public interface OcupaoffPkgService {
	Integer updateVOffCrseEvents(final OffenderCourseAttendance bean);

	void offeCourseAttendance(final OffenderCourseAttendance offcrsAtt, final String userName);
}
