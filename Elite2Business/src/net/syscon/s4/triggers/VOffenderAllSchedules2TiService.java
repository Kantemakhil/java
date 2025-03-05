package net.syscon.s4.triggers;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;

public interface VOffenderAllSchedules2TiService {
	Integer vOffenderAllSchedules2TiTger(OffenderIndSchedules offenderIndSchedules) throws CustomException;
}
