package net.syscon.s4.pkgs.tag_multiple_failure;

import java.util.Map;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

public interface TagMultipleFailureService {

	void adjustUa(final VOffenderAllSchedules vOffSch, final String userName);

	Boolean checkUaEventOutcome(final OffenderCourseAttendance bean);

	Map<String, Object> checkUa(final VOffenderSentenceEvents bean);
}