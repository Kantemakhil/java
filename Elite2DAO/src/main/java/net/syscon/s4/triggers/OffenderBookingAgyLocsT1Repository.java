package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.OffenderBookings;

public interface OffenderBookingAgyLocsT1Repository {
	OffenderBookingAgyLocs getOffenderBookingAgyLocs(OffenderBookingAgyLocs offeBookAgyLocs);

	OffenderBookings getOffenderBookings(Long offenderBookId);

	Integer update(OffenderBookings offenderBookings);
}
