package net.syscon.s4.inmate.trust.checks.checksmainataince;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.BankAccountsMaintenances;
import net.syscon.s4.im.beans.BankAccountsMaintenancesCommitBean;
import net.syscon.s4.im.beans.CaseloadCurrentAccountsBase;
import net.syscon.s4.im.beans.CaseloadCurrentAccountsBaseCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Interface OtmbaccoService
 */
public interface OtmbaccoService {
	List<Corporates> cgfkCsldCabPayeeCorporateRecordGroup();

	List<BankAccountsMaintenances> bankAmExecuteQuery(BankAccountsMaintenances objBankAccountsMaintenances);

	Integer csldCabCommit(CaseloadCurrentAccountsBaseCommitBean commitBean);

	List<CaseloadCurrentAccountsBase> csldCabExecuteQuery(CaseloadCurrentAccountsBase objCaseloadCurrentAccountsBase);

	List<AccountCodes> cgfkCsldCabAccountCodeRecordGroup(String str);

	List<ReferenceCodes> cgfkCsldCabBankAccountTypRecordGroup();

	Integer bankAmCommit(BankAccountsMaintenancesCommitBean commitBean);

	String corporeteNameData(BigDecimal payeeCorporateId);

}
