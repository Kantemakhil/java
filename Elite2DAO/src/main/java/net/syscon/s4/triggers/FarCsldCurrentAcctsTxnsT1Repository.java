package net.syscon.s4.triggers;

import net.syscon.s4.im.beans.AccountCodes;

public interface FarCsldCurrentAcctsTxnsT1Repository {

	String getModuleName(final Integer txnId);
	
	Integer insertFarCsldCurrentAcctsTxns(AccountCodes txns,String vModuleName);
}
