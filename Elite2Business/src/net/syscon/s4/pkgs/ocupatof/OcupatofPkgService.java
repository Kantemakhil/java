package net.syscon.s4.pkgs.ocupatof;

import net.syscon.s4.common.beans.OffenderCourseAttendance;

public interface OcupatofPkgService {

	OffenderCourseAttendance getOffenderCourseDetails(final OffenderCourseAttendance offCour);
	
	Integer updateOffCrseAttend(OffenderCourseAttendance offCouAtt);
}
