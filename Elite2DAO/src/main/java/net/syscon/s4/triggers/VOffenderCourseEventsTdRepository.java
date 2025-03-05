package net.syscon.s4.triggers;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;

public interface VOffenderCourseEventsTdRepository {
	Integer insert(VOffenderCourseEvents vOffenderCourseEvents);

	Integer update(VOffenderCourseEvents vOffenderCourseEvents);
}
