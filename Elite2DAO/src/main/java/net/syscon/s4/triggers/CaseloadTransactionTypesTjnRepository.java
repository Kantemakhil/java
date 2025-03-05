package net.syscon.s4.triggers;


import net.syscon.s4.common.beans.CaseloadAccountCodesJn;
import net.syscon.s4.common.beans.CaseloadTransactionTypesjn;


public interface CaseloadTransactionTypesTjnRepository {

	public Integer insertCaseloadTransactionTypesJn(final CaseloadTransactionTypesjn caseloadTransactionTypesjn,final String userName);

}


