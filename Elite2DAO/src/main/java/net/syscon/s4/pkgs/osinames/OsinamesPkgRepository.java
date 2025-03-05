package net.syscon.s4.pkgs.osinames;

import java.math.BigDecimal;

import net.syscon.s4.common.beans.OffenderBookings;

public interface OsinamesPkgRepository {

	Object[] offDetCur(final Long offenderBookId);

	OffenderBookings getOffenderDetails(final String pOffenderIdDisplay, final String pAgyLocId,
			final String pCaseloadId);

	BigDecimal getOffBookId(final String vstOffIdDisplay, final String userId);

}