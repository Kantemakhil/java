package net.syscon.s4.triggers;

import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;

public interface VOffenderPrgObligationsTiService {
	Integer vOffenderPrgObligationsTiTgr(VOffenderPrgObligations oldObj, VOffenderPrgObligations newObj,
			String sqlOperation);
}
