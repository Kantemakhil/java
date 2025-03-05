package net.syscon.s4.pkgs.ocdperso;

import java.math.BigDecimal;

import net.syscon.s4.inst.booking.beans.OffenderContactPersons;

public interface OcdpersoPkgService {

	void cancelFutureVisits(final Long pOffenderBookId, final Long pPersonId, final String userName);

	OffenderContactPersons getPersonNames(final Long personId);

	Integer copyOffAddr(final BigDecimal pRootOffId, final Long pPersonId, final String userName);

}
