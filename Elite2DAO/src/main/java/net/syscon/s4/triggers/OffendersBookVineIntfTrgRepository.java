package net.syscon.s4.triggers;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.AnAudit;
import net.syscon.s4.pkgs.OrAudit;

public interface OffendersBookVineIntfTrgRepository {
	OffenderBookings getOffenderBookings(Long offenderBookId);

	Offenders curOff(BigDecimal offenderId);

	Integer insert(List<OrAudit> orAudit);

	Integer update(List<OrAudit> orAudit);

	Integer delete(List<OrAudit> orAudit);

	Integer anAuditUpdate(List<AnAudit> anAuditList);
}
