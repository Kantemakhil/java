package net.syscon.s4.pkgs.comunity_pkg;

import java.util.Date;

public interface ComunityPkgRepository {
	Long poNumCur(final String pAgyLocId, final Long pStaffId, final String pPosition, final String pRole,
			final Date pFromDate);
	Integer curPrimaryOwnPerOfficer(final Integer staffId);
}