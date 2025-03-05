package net.syscon.s4.pkgs.tag_schedule;

import java.math.BigDecimal;
import java.util.Date;

import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;

public interface TagScheduleService {
	Integer createSchedule(final OffenderIndSchedules bean);

	Integer deleteSchedule(final BigDecimal eventId,String modifyUserId);

	Long GetIntoCheckSum(final Long eventId);

	Long checkSum(final Date pTimestamp);

	Integer checkScheduleConflict(final VOffenderAllSchedules schedules);

	Integer checkScheduleConflict(final Long pOffenderBookId, final Date pEventDate, final Integer pEventId);

	Integer setScheduleStatus(VOffenderAllSchedules2 old);

	void flushSchedules();
	
	Date scheduleDate();
	
	Integer setScheduleDate(Date pScheduleDate);
	
	void flushDailySchedules(Date pScheduleDate);
}