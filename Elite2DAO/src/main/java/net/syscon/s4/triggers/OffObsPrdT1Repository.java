package net.syscon.s4.triggers;

import net.syscon.s4.inst.careinplacement.beans.OffObsPrdCharacteristics;
import net.syscon.s4.inst.careinplacement.beans.OffObservationPeriods;

public interface OffObsPrdT1Repository {

	OffObsPrdCharacteristics getOffObsPrdCharacteristics(Long obsPrdCharId);

	String authCheckCur();

	Long getFreqCur(String observationType, String characterisCode);

	OffObservationPeriods getPeriodFreqCur(Long offenderBookId, Long obsPeriodId);

}
