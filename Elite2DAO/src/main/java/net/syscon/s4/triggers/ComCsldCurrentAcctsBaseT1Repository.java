package net.syscon.s4.triggers;

public interface ComCsldCurrentAcctsBaseT1Repository {
	ComCsldCurrentAccountsBase getComCsldCurrentAccountsBase(ComCsldCurrentAccountsBase comCsldCurrentAccountsBase);

	String lBankChqBooksExistsCur(String caseloadId, String accountCode);

	Integer update(ComCsldCurrentAccountsBase comCsldCurrentAccountsBase);
}
