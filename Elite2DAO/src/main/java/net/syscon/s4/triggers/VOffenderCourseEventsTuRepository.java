package net.syscon.s4.triggers;

import java.math.BigDecimal;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;

public interface VOffenderCourseEventsTuRepository {
	Integer insert(VOffenderCourseEvents vOffenderCourseEvents);

	Integer update(VOffenderCourseEvents vOffenderCourseEvents);
	
	BigDecimal getEventId();
}
