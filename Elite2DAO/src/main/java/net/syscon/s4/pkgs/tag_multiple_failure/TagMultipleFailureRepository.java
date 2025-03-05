package net.syscon.s4.pkgs.tag_multiple_failure;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderCourseAttendance;
import net.syscon.s4.inst.legals.beans.VOffenderSentenceEvents;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;

public interface TagMultipleFailureRepository {
	Integer checkUaEventOutcomeOld(final VOffenderAllSchedules obj);

	Integer checkUaEventOutcomeNew(final VOffenderAllSchedules obj);

	List<VOffenderAllSchedules> osuaCurSelectOperation(final BigDecimal eventId);

	Integer osuaRecDeleteOperation(final VOffenderAllSchedules obj);

	Integer mfADdjustSelectOperation(final VOffenderAllSchedules obj);

	Integer mfDAdjustUpdate(final VOffenderAllSchedules obj);

	Integer mfDAdjustInsert(final VOffenderAllSchedules obj);

	List<VOffenderAllSchedules> oseCurSelectOperation(final BigDecimal eventId);

	Integer countSentenceUaSelectOperation(final VOffenderAllSchedules obj);

	Integer uaEventExists(final VOffenderAllSchedules obj);

	Integer uaEventExistsDelOperation(final VOffenderAllSchedules obj);

	Integer createUasInsert(final VOffenderAllSchedules obj);

	public List<VOffenderSentenceEvents> failcur(final Long eventId);

	public String curUaOldOutcome(final String eventType, final String eventSubType, final String oldOutCome);

	public Integer selectCount(final VOffenderSentenceEvents bean);

	Boolean checkUaEventOutcome(final OffenderCourseAttendance bean);

}
