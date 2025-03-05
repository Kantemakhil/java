package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;

public interface OffenderIndSchedulesT2Service {
	List<OffenderIndSchedules> offenderIndSchedulesT2Tgr(List<OffenderIndSchedules> offenderIndSchedulesList)
			throws CustomException;
}
