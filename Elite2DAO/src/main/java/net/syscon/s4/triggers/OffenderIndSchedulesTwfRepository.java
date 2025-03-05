package net.syscon.s4.triggers;

import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;

public interface OffenderIndSchedulesTwfRepository {
	OffenderIndSchedules getOffenderIndSchedules(Integer eventId);
}
