package net.syscon.s4.triggers;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;

public interface OffenderIndSchedulesT3Service {
	Integer getVnumRows(OffenderIndSchedules object) throws CustomException;
}
