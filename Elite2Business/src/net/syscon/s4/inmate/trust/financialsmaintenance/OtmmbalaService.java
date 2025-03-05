package net.syscon.s4.inmate.trust.financialsmaintenance;

import java.util.List;

import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inmate.beans.OffenderSubAccountsCommitBean;

/**
 * Interface OtmmbalaService
 */

public interface OtmmbalaService {

	Integer offSubaCommit(OffenderSubAccountsCommitBean commitBean);

	List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts objOffenderSubAccounts);

	List<AccountCodes> cgfkOffSubaTrustAccountCoRecordGroup();

}
