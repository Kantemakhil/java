package net.syscon.s4.inst.schedules;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.ScheduleSeries;
import net.syscon.s4.community_supervision_tiers.MaintainTierDefaultEvents;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.of.payroll.maintenance.SystemEvents;

public interface RecurringScheduleRepository {

	List<SystemEvents> getHolidaysList(Date fromDate);
	
	List<VOffenderAllSchedules> getScheduleConflicts(Integer offenderBookId,List<Date> eventDate,Integer seriesId);

	Integer recurrRuleCommit(ScheduleSeries commitBean);
	
	Integer recurrPreInsert();

	ScheduleSeries getScheduleSeries(ScheduleSeries searchBean);
	
	Integer schedulesInsert(List<VOffenderAllSchedules> insertList);
	
	Integer schedulesUpdate(Integer seriesId,Date eventDate,String modifyUserId);
	
	Integer schedulesDelete(BigDecimal eventId,String modifyUserId);
	
	Integer dTescheduleUpdate(List<MaintainTierDefaultEvents> list);
	
	Integer deleteDefaultTierEventsData(String tierLevel,String eventType,String eventSubType,Date startDate,BigDecimal offenderTierLevelId,Long versionNo,String modifyUserId);
	
	String getDefaultTierLevelUiRules(String eventType,String eventSubType,Long offenderBokId ,BigDecimal offenderTierLevelId);
}
