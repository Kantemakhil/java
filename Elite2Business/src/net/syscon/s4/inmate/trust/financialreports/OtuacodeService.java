package net.syscon.s4.inmate.trust.financialreports;

import java.util.List;

import net.syscon.s4.im.beans.AccountCodes;

/**
 * Interface OtuacodeService
 */
public interface OtuacodeService {
	List<AccountCodes> acCodeExecuteQuery(AccountCodes objAccountCodes);

}
