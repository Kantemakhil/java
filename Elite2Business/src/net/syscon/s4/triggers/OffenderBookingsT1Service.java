package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;

public interface OffenderBookingsT1Service {
	OffenderExternalMovements offenderBookingsT1(final OffenderBookings book);

}
