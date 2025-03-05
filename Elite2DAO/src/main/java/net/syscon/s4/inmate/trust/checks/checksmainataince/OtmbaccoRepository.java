package net.syscon.s4.inmate.trust.checks.checksmainataince;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BankAccountsMaintenances;
import net.syscon.s4.im.beans.CaseloadCurrentAccountsBase;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Interface OtmbaccoRepository
 */
public interface OtmbaccoRepository {
	List<AccountCodes> cgfkCsldCabAccountCodeRecordGroup(String str);

	Integer csldCabUpdateCaseloadCurrentAccountsBase(List<CaseloadCurrentAccountsBase> lstCaseloadCurrentAccountsBase);

	Integer csldCabDeleteCaseloadCurrentAccountsBase(List<CaseloadCurrentAccountsBase> lstCaseloadCurrentAccountsBase);

	Integer csldCabInsertCaseloadCurrentAccountsBase(List<CaseloadCurrentAccountsBase> lstCaseloadCurrentAccountsBase);

	List<Corporates> cgfkCsldCabPayeeCorporateRecordGroup();

	Integer bankAmInsertBankAccountsMaintenances(List<BankAccountsMaintenances> lstBankAccountsMaintenances);

	List<BankAccountsMaintenances> bankAmExecuteQuery(BankAccountsMaintenances objBankAccountsMaintenances);

	List<CaseloadCurrentAccountsBase> csldCabExecuteQuery(CaseloadCurrentAccountsBase objCaseloadCurrentAccountsBase);

	List<ReferenceCodes> cgfkCsldCabBankAccountTypRecordGroup();

	List<ReferenceCodes> cgfkchkCsldCabCsldCabRef(ReferenceCodes paramBean);

	Integer bankAmDeleteBankAccountsMaintenances(List<BankAccountsMaintenances> lstBankAccountsMaintenances);

	Integer bankAmUpdateBankAccountsMaintenances(List<BankAccountsMaintenances> lstBankAccountsMaintenances);

	Long accountPeriodIdData();

	String corporeteNameData(BigDecimal payeeCorporateId);

}
