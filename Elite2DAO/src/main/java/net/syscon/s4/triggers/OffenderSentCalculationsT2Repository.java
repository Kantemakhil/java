package net.syscon.s4.triggers;

import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;

public interface OffenderSentCalculationsT2Repository {
	OffenderSentCalculations getOffenderSentCalculations(Long offenderSentCalculationId);

	Integer lvCount(Long offenderBookId);

	Integer update(OffenderReleaseDetails offenderReleaseDetails);

	Integer insert(OffenderReleaseDetails offenderReleaseDetails);
}
