package net.syscon.s4.inmate.trust.financialsmaintenance;

import java.util.List;

import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

public interface OtmmbalaRepository {

	/**
	 * Interface OtmmbalaRepository(){
	 */
	Integer offSubaUpdateOffenderSubAccounts(List<OffenderSubAccounts> lstOffenderSubAccounts);

	List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts objOffenderSubAccounts);

	List<AccountCodes> cgfkOffSubaTrustAccountCoRecordGroup();

	List<AccountCodes> cgfkchkOffSubaOffSubaAc(AccountCodes paramBean);
}
