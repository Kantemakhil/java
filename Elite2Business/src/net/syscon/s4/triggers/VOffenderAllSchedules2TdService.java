package net.syscon.s4.triggers;

import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules2;

public interface VOffenderAllSchedules2TdService {
Integer vOffenderAllSchedules2TdTgr(VOffenderAllSchedules2 old ,VOffenderAllSchedules2 newObj , String source) throws CustomException;
}
