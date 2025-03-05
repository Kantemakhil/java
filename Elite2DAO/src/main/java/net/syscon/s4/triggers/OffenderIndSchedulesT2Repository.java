package net.syscon.s4.triggers;

import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;

public interface OffenderIndSchedulesT2Repository {

	OffenderIndSchedules getOffenderIndSchedules(OffenderIndSchedules newObj);

	Integer eventType(String eventType);
	
	Integer eventStatus(String eventStatus);

}
