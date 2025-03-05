package net.syscon.s4.triggers;

import net.syscon.s4.inst.careinplacement.beans.OffObservationPeriods;

public interface OffObservationPeriodsT1Repository {
	OffObservationPeriods getOffObservationPeriods(Long offenderBookId, Long obsPeriodId);
	String authCheckCur();
}
