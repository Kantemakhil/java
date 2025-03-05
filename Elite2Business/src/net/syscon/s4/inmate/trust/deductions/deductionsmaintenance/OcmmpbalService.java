package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;
import java.util.List;

import net.syscon.s4.common.beans.MinimumPayableBalances;
import net.syscon.s4.common.beans.MinimumPayableBalancescommitBean;
import net.syscon.s4.im.beans.AccountCodes;

public interface OcmmpbalService  {
	String minPbCommit(MinimumPayableBalancescommitBean CommitBean) ;

	List<Object> CgwhenNewFormInstance()  ;

	List<MinimumPayableBalances> minPbExecuteQuery(MinimumPayableBalances objMinimumPayableBalances) ;

	List<AccountCodes> cgfkMinPbAccountCodeRecordGroup(String caseloadType) ;
	
	List<AccountCodes> minPbAccountCodeRecordGroup(String caseloadType);

	List<AccountCodes> CgfkchkMinPbMinPbAcCode(AccountCodes paramBean)  ;

}
