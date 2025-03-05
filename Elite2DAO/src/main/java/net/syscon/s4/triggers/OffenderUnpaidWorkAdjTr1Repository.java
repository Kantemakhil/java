package net.syscon.s4.triggers;

import net.syscon.s4.cm.programsservices.OffenderUnpaidWorkAdj;

public interface OffenderUnpaidWorkAdjTr1Repository {
	OffenderUnpaidWorkAdj getOffenderUnpaidWorkAdj(OffenderUnpaidWorkAdj offenderUnpaidWorkAdj);

	Integer vNumrows(OffenderUnpaidWorkAdj offenderUnpaidWorkAdj);
}