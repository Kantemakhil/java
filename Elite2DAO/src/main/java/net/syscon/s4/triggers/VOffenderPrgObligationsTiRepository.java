package net.syscon.s4.triggers;

import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;

public interface VOffenderPrgObligationsTiRepository {
	Integer insert(VOffenderPrgObligations OffenPrgOblig);

	Integer update(VOffenderPrgObligations OffenPrgOblig);

	Integer delete1(VOffenderPrgObligations offenderPrgObligationHty);

	Integer delete2(VOffenderPrgObligations OffenPrgOblig);
}
