package net.syscon.s4.triggers;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;

public interface VOffenderCourseEventsTdService {
	Integer vOffenderCourseEventsTdTgr(VOffenderCourseEvents old, VOffenderCourseEvents newObj);
}
