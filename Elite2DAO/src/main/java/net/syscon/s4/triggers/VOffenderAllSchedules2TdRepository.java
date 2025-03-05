package net.syscon.s4.triggers;

import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;

public interface VOffenderAllSchedules2TdRepository {
	Integer updateOffenderIndSchedules(OffenderIndSchedules offenderIndSchedules);

	Integer deleteOffenderIndSchedules(OffenderIndSchedules offenderIndSchedules);

	Integer updateVOffenderCourseEvents(VOffenderCourseEvents vOffenderCourseEvents);

	Integer updateVOffenderCourseEvents2(VOffenderCourseEvents vOffenderCourseEvents);
}
