package net.syscon.s4.pkgs.oumtrnbk;

import net.syscon.s4.common.beans.OffenderBookings;

public interface OumtrnbkPkgService {
	
	Boolean chkOffendersForTransfer(final OffenderBookings bean);
	
	Boolean isOneBookingOnly(final OffenderBookings bean);
	
	Boolean isBothBookingsActive(final OffenderBookings bean);
	
	Boolean isInactiveAfterActive(final OffenderBookings bean);

}
