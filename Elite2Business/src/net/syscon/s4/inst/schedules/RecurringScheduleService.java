package net.syscon.s4.inst.schedules;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.ScheduleSeries;
import net.syscon.s4.community_supervision_tiers.MaintainTierLevelsCommitBean;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedulesCommitBean;

public interface RecurringScheduleService {
	
	
	public List<Date> nextSchedules(Long recurringSeriesId);


	public List<Date> calculateSchedules(ScheduleSeries scheduleSeries);
	
	List<VOffenderAllSchedules> getScheduleConflicts(List<OffenderIndSchedules> obj);


	public Integer recurrRuleCommit(VOffenderAllSchedulesCommitBean commitBean);


	public ScheduleSeries getScheduleSeries(ScheduleSeries searchBean);
	
	Integer commitScheduledEventDetails( MaintainTierLevelsCommitBean commitBean);

}
