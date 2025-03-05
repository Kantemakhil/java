package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.OrAudit;

public interface OffExtMvVineIntfTrgRepository {
	OffenderExternalMovements getOffenderExternalMovements(OffenderExternalMovements offenderExtMov);

	OffenderBookings curBookOr(Long offenderBookId);

	Offenders curOff(BigDecimal pOffId);

	Integer updateOrAudit(List<OrAudit> orAudit);

	Integer insertOrAudit(List<OrAudit> orAudit);

}
