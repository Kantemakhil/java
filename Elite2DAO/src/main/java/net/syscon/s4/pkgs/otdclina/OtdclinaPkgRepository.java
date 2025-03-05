package net.syscon.s4.pkgs.otdclina;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.inmate.beans.OffenderTrustAccountsTemp;

public interface OtdclinaPkgRepository {

	Integer offenderTrustAccountsTemp(final Long psessionid, final String pcaseloadid);

	List<OffenderTrustAccounts> allC(final OffenderTrustAccountsTemp searchRecord);

	List<OffenderTrustAccounts> zeroC(final OffenderTrustAccountsTemp searchRecord);

	List<OffenderTrustAccounts> notifyC(final OffenderTrustAccountsTemp searchRecord);

	List<OffenderExternalMovements> maxMovementC(final OffenderTrustAccountsTemp searchRecord);

	Integer offenderTrustAccountsTemp(final OffenderTrustAccountsTemp searchRecord);

	Integer agyLocIDExistsC(final String pcaseloadid, final String pagylocid);
}