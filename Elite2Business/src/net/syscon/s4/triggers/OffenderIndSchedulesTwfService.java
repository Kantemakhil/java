package net.syscon.s4.triggers;

import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;

public interface OffenderIndSchedulesTwfService {
	Integer offenderIndSchedulesTwfTgr(OffenderIndSchedules newObj, String sqlOperation);
}
