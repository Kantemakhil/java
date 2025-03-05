package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;

public interface VOffenderAllSchedules2TuRepository {
	Integer schOffenderIndSchedules(List<OffenderIndSchedules> offenderIndSchedules);

	Integer vOffCrsVOffenderCourseEvents(VOffenderCourseEvents vOffenderCourseEvents);

	Integer vOffCrsVOffenderCourseEventsElse(VOffenderCourseEvents vOffenderCourseEvents);

	Integer offVisVOffenderVisitSchedules(VOffenderVisitSchedules vOffenderVisitSchedules);

	Integer oicHearingOicHearings(OicHearings oicHearings);

	Integer offRelOffenderReleaseDetails(OffenderReleaseDetails offenderReleaseDetails);

	Integer courtCourtEvents(CourtEvents courtEvents);

	Integer getvRecCount(Integer offenderBookId, String eventType, Integer referenceId);

	Integer courtOffenderIndSchedules(OffenderIndSchedules offenderIndSchedules);
}
