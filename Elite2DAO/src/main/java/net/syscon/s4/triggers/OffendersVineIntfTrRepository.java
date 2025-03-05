package net.syscon.s4.triggers;

import java.math.BigDecimal;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.pkgs.AnAudit;
import net.syscon.s4.pkgs.MeAudit;
import net.syscon.s4.pkgs.OrAudit;

public interface OffendersVineIntfTrRepository {
	OffenderBookings curActBookAn(final BigDecimal vRootOffId,final Long  vOffId);
	Integer insertAnAudit(final AnAudit searchBean);
	Integer updateAnAudit(final AnAudit searchBean);
	Offenders offendersExecuteQuery(final Long offenderId);
	OffenderBookings curActBookOr(final Long  vOffId);
	Integer updateOrAudit(final OrAudit searchBean);
	Integer insertOrAudit(final OrAudit searchBean);
	OffenderBookings curActBookAnMe(final BigDecimal vRootOffId,final Long  vOffId,final BigDecimal vOldRootOffId);
	Integer curMeExist(final String offenderIdDisplay);
	Integer insertMeAudit(final MeAudit searchBean);
}
