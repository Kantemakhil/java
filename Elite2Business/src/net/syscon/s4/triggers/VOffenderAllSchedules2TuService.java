package net.syscon.s4.triggers;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;

public interface VOffenderAllSchedules2TuService {
	Integer vOffenderAllSchedules2TuTrigger(OffenderIndSchedules newRef, OffenderIndSchedules oldRef,
			String recordSource ,String source) throws CustomException;
}
