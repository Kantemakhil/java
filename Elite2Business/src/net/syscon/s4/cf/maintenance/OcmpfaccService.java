package net.syscon.s4.cf.maintenance;

import java.util.List;

import net.syscon.s4.cf.maintenance.beans.FeeAccounts;
import net.syscon.s4.cf.maintenance.beans.FeeAccountsCommitBean;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.DeductionTypes;

public interface OcmpfaccService {

	List<FeeAccounts> feeAccountsExecuteQuery();

	FeeAccounts feeAccountCommit(FeeAccountsCommitBean commitBean);

	List<AccountCodes> getAccCodes();

	List<DeductionTypes> getFeeCodes();

}
