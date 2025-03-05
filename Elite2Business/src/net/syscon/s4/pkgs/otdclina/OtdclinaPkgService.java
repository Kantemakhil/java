package net.syscon.s4.pkgs.otdclina;

import java.math.BigDecimal;

import net.syscon.s4.inmate.beans.OffenderTrustAccountsTemp;

public interface OtdclinaPkgService {

	BigDecimal populateTable(final OffenderTrustAccountsTemp searchRecord, final String userName);

}
