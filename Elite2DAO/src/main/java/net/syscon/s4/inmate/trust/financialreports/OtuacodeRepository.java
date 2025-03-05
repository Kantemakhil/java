package net.syscon.s4.inmate.trust.financialreports;

import java.util.List;

import net.syscon.s4.im.beans.AccountCodes;

/**
 * Interface OtuacodeRepository
 */
public interface OtuacodeRepository {
	List<AccountCodes> acCodeExecuteQuery(AccountCodes objAccountCodes);

}
