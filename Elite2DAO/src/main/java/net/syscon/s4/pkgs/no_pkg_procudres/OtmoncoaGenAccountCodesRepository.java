package net.syscon.s4.pkgs.no_pkg_procudres;

import java.util.List;

import net.syscon.s4.common.beans.CaseloadAccountPeriods;
import net.syscon.s4.common.beans.CaseloadAccountSummaries;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;

public interface OtmoncoaGenAccountCodesRepository {
	List<String> lCaseloadsCur(String caseloadId);

	Integer lCurrentPeriodCur();

	int lLastClosedCur(String caseloadId);

	List<AccountCodes> lCsldCurrAcctBaseCur(final String caseloadId, final Integer accountCode,
			final Integer pAccountPeriodId);

	List<CaseloadAccountPeriods> lCsldAcctPeriodsCur(final String caseloadId, final Integer pAccountPeriodId);

	List<CaseloadAccountSummaries> lCsldAcctSummCur(final String caseloadId, final Integer accountCode,
			final Integer pAccountPeriodId);

	void insertCaseloadCurrentAccountsBase(final String caseloadId, final Integer accountCode,
			final Integer lCurrentPeriodId, final String userName);

	List<CaseloadCurrentAccounts> lCsldCurrAcctTxnsCur(final String caseloadId, final Integer accountCode,
			final Integer pLastPeriodId, final Integer lCurrentPeriodId);

	void insertCaseloadCurrentAccountsTxns(final List<CaseloadCurrentAccounts> currAcctTxnsCur, final String userName);

	void insertCaseloadAccountPeriods(final List<CaseloadAccountPeriods> acctPeriodsCur, final String userName);

	void insertCaseloadAccountSummaries(final List<CaseloadAccountSummaries> lCsldAcctSummCur, final String userName);
}
