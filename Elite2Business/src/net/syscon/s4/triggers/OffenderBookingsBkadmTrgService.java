package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderBookings;

public interface OffenderBookingsBkadmTrgService {

	void offenderBookingsBkadmTrg(final OffenderBookings old, final OffenderBookings newbean, final String operation);
}
