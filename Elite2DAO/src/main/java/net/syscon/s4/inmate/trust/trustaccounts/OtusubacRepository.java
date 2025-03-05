package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Interface OtusubacRepository
 */
public interface OtusubacRepository {
	Caseloads offSubaPreQuery(Caseloads paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts objOffenderSubAccounts);

	List<Offenders> offNameExecuteQuery(Offenders objOffenders);

	AccountCodes cgfkchkOffSubaOffSubaAc(AccountCodes paramBean);

	ReferenceCodes cgfkchkOffSubaOffSubaAc(ReferenceCodes paramBean);

}
