package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;

public interface OffenderExternalMovementsT3Repository {
	OffenderExternalMovements getOffenderExternalMovements(Long offenderBookId, Long movementSeq);

	String getLocationCur(String agyLocId);

	OffenderBookings communityActiveCur(OffenderExternalMovements obj);

}
