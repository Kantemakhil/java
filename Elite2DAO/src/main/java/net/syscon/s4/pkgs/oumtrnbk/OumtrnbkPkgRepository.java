package net.syscon.s4.pkgs.oumtrnbk;

import net.syscon.s4.common.beans.OffenderBookings;

public interface OumtrnbkPkgRepository {
	
	Integer isOneBookingOnly(final OffenderBookings bean);
	
	Integer getInstBookActive(final Long offenderBookId);
	
	Integer getToBookActive(final Long rootOffIdTo);
	
	Integer getActiveBookingCur(final Long rootOffId);

}
