package net.syscon.s4.inmate.trust.checks.checksmainataince;

import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BankChequeBooks;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface OtmcnserRepository
 */

public interface OtmcnserRepository {
	// List<Object> cgwhenNewFormInstance(SysDual paramBean);

	Integer bankCbDeleteBankChequeBooks(final List<BankChequeBooks> lstBankChequeBooks);

	List<AccountCodes> cgfkchkCsldCaCsldAcAcCo(final AccountCodes paramBean);

	List<BankChequeBooks> bankCbExecuteQuery(final BankChequeBooks objBankChequeBooks);

	Integer bankcbInsertBankChequeBooks(final List<BankChequeBooks> lstBankChequeBooks);

	List<CaseloadCurrentAccounts> csldCaExecuteQuery(final CaseloadCurrentAccounts objCaseloadCurrentAccounts);
	// @Override
	// public Integer bankCbInsertBankChequeBooks(List<BankChequeBooks>
	// lstBankChequeBooks) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	String accountName(BigDecimal accountCode,String userId);

	List<String> checkChecqueBooks(Long firstChecknum, Long lastCheckNum, Long accountCode, String caseloadId);

	String checkifNewSeries(Long firstChecknum, Long accountCode, String caseloadId);

	String checkChecqueBooksLastCheck(Long lastCheckNumLong, Long firstChecknumLong, Long accountCode,
			String caseloadId);

	Long getChequeBookSeq();

	String getaccountNumber(String caseloadId, Long accountCode,String userName);

}
