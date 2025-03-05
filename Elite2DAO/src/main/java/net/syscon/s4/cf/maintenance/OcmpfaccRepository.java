package net.syscon.s4.cf.maintenance;

import java.util.List;

import net.syscon.s4.cf.maintenance.beans.FeeAccounts;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.DeductionTypes;

public interface OcmpfaccRepository {

	List<FeeAccounts> feeAccountsExecuteQuery();

	Integer preInsert(FeeAccounts bean);

	FeeAccounts insertFeeAccounts(List<FeeAccounts> insertList);

	FeeAccounts updateFeeAccounts(List<FeeAccounts> updateList);

	List<AccountCodes> getAccCodes();

	List<DeductionTypes> getFeeCodes();

	Integer preInsertListSeq(FeeAccounts bean);

	Integer preUpdateListSeq(FeeAccounts bean);
}
