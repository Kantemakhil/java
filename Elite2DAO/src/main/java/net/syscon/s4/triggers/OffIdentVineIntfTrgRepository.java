package net.syscon.s4.triggers;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.OrAudit;

public interface OffIdentVineIntfTrgRepository {
	
	OffenderBookings curBookOr(final Long vOffId);
	Offenders curOff(final Long pOffId);
	Integer updateOrAudit(final OrAudit searchBean);
	Integer insertOrAudit(final OrAudit searchBean);
}
