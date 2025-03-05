package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.VOffenderCourseAttendances;

public interface OffenderCourseAttendancesT1Repository { 

	VOffenderCourseAttendances getOffenderCourseAttendances(Long eventId);
}
