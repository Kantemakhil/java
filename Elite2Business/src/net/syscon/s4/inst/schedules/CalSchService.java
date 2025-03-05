package net.syscon.s4.inst.schedules;

import java.util.List;

import net.syscon.s4.inst.schedules.bean.CalScheduleBean;
import net.syscon.s4.inst.schedules.bean.CourtEvents;

public interface CalSchService {
	public List<CalScheduleBean> getEventData(CalScheduleBean obj);
	
	Integer courtEventsSave(CourtEvents courtEvents);
	
	Boolean checkScreenAccess(String userId);
	
	public List<CalScheduleBean> updateViewClickData(CalScheduleBean obj);
}
