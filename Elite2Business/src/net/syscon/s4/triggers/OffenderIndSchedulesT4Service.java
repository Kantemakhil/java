package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;

public interface OffenderIndSchedulesT4Service {
	String offenderIndSchedulesT4(OffenderCaseNotes offenderCaseNotes, OffenderIndSchedules offenderIndSchedules,String source);
}
