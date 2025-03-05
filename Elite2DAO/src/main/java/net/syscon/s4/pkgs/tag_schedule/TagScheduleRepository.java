package net.syscon.s4.pkgs.tag_schedule;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;

public interface TagScheduleRepository {
	Integer offnderIndScchedule(final OffenderIndSchedules bean);

	Integer deleteScheduleDeleteOperation(final BigDecimal eventId,String modifyUserId);

	Long GetIntoCheckSum(final Long eventId);

	Long GetIntoRowId(final Long eventId);

	public Long getCheckSumNum(final Date modiDate);

	public Integer checkScheduleConflict(final VOffenderAllSchedules schedules);

	public Integer checkScheduleConflict(final Long pOffenderBookId, final Date pEventDate, final Integer pEventId);

	Integer setScheduleStatus(VOffenderAllSchedules2 obj);
	
	Date scheduleDate();

	Integer updateScheduleDate(Date pScheduleDate);

	Integer updateoffProgramstatus();

	Integer materializePrgSchB(Date pScheduleDate);

	Integer materializePrgSchA(Date pScheduleDate);

	Integer flushDailySchUpdOffIndSch(Date pScheduleDate);
	
	List<VOffenderAllSchedules2> getVoffAllSch(Date pScheduleDate);

	Integer flushDailySchUpdOffCourse(Date pScheduleDate);

	Integer updateCourseSchDays();
	
	List<VOffenderAllSchedules2> getVoffAllSchExp(Date pScheduleDate);

}