package net.syscon.s4.triggers;

public interface CaseloadCurrentAcctsBaseT1Repository {

	String gettingCheckBookExistFlag(String caseloadId, Long accountCode);
	
	 public Integer updatingBankChequeBooks(String caseloadId, Long accountCode,String bankAccountNumber);
}
