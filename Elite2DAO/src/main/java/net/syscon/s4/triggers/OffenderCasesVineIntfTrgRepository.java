package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.im.beans.Offenders;

public interface OffenderCasesVineIntfTrgRepository {
	OffenderBookings curActBook(Long pOffBookId);

	Offenders curOff(Long pOffId);

	OffenderCases getOldOffenderCases(final Long caseId);

	Integer inserting(CaAudit caAudit);

	Integer updating(CaAudit caAudit);

	Integer deleting(CaAudit caAudit);
}
