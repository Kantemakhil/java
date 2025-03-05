package net.syscon.s4.inmate.trust.checks.checksmainataince;

import java.util.List;

import net.syscon.s4.im.beans.BankChequeBooks;
import net.syscon.s4.im.beans.BankChequeBooksCommitBean;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;
import net.syscon.s4.im.beans.CaseloadCurrentAccountsCommitBean;

/**
 * Interface OtmcnserService
 **/
public interface OtmcnserService {

	Integer csldCaCommit(final CaseloadCurrentAccountsCommitBean commitBean);

	List<BankChequeBooks> bankCbExecuteQuery(final BankChequeBooks objBankChequeBooks);

	Integer bankCbCommit(final BankChequeBooksCommitBean commitBean) throws Exception;

	// List<AccountCodes> CgfkchkCsldCaCsldAcAcCo(final AccountCodes paramBean);

	List<CaseloadCurrentAccounts> csldCaExecuteQuery(final CaseloadCurrentAccounts objCaseloadCurrentAccounts);

	String checkChecqueBooks(Long firstChecknum, Long accountCode, String caseloadId);

	String checkChecqueBooksLastCheck(Long lastCheckNumLong, Long firstChecknumLong, Long accountCode,
			String caseloadId);

}
