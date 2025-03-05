package net.syscon.s4.triggers;

import net.syscon.s4.inst.legals.beans.OffenderCaseIdentifiers;

public interface OffenderCaseIdentifiersT1Repository {
	OffenderCaseIdentifiers getOffenderCaseIdentifiers(OffenderCaseIdentifiers offenderCaseIdentifiers);

	Integer vNumrows(OffenderCaseIdentifiers offenderCaseIdentifiers);

}
