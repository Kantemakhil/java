package net.syscon.s4.inmate.trust.trustaccounts;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderTrustAccountsCommitBean;

/**
 * Interface OtdopctaService
 */
public interface OtdopctaService {
	List<Object> cgwhenNewFormInstance();

	Integer offTaCommit(OffenderTrustAccountsCommitBean commitBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<OffenderTrustAccounts> offTaExecuteQuery(OffenderTrustAccounts objOffTrust);

	List<OffenderSubAccounts> cgrichkOffenderTrustAccoun(OffenderSubAccounts paramBean);

	Integer preInsert();

}
