package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderExternalMovements;

public interface OffenderExternalMovementsT9Repository {

	OffenderExternalMovements getOffenderExternalMovements(Long offenderBookId, Long movementSeq);
	

}
