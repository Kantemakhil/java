package net.syscon.s4.inmate.trust.trustaccounts;
import java.util.List;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OffendersCommitBean;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderSubAccountsCommitBean;
/**
 * Interface OtusubacService 
 */
public interface OtusubacService  {
	Integer offSubaCommit(OffenderSubAccountsCommitBean CommitBean);

	Integer offNameCommit(OffendersCommitBean commitBean);

	AccountCodes CgfkchkOffSubaOffSubaAc(AccountCodes paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts objOffenderSubAccounts);

	Caseloads offSubaPreQuery(Caseloads paramBean);

	List<Offenders> offNameExecuteQuery(Offenders objOffenders);

}
