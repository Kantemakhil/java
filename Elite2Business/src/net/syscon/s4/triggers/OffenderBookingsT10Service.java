package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;

public interface OffenderBookingsT10Service {
	OffenderExternalMovements offenderBookingsT10Trigger(OffenderBookings offenderBookings);
}
