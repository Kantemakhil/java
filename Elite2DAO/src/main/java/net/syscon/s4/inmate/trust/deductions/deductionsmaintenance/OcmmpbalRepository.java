package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;
import java.util.List;

import net.syscon.s4.common.beans.MinimumPayableBalances;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.SysDual;

public interface OcmmpbalRepository {
	List<SysDual> cgwhenNewFormInstance(SysDual paramBean);

	List<MinimumPayableBalances> minPbExecuteQuery(MinimumPayableBalances objMinimumPayableBalances) ;

	Integer minPbInsertMinimumPayableBalances(List<MinimumPayableBalances> lstMinimumPayableBalances) ;

	List<AccountCodes> cgfkMinPbAccountCodeRecordGroup(String caseloadType) ;

	List<AccountCodes> cgfkchkMinPbMinPbAcCode(AccountCodes paramBean);

	Integer minPbUpdateMinimumPayableBalances(List<MinimumPayableBalances> lstMinimumPayableBalances) ;

}
