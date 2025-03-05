package net.syscon.s4.pkgs.no_pkg_procudres.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.CaseloadAccountPeriods;
import net.syscon.s4.common.beans.CaseloadAccountSummaries;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.CaseloadCurrentAccounts;
import net.syscon.s4.pkgs.no_pkg_procudres.OtmoncoaGenAccountCodesRepository;
import net.syscon.s4.pkgs.no_pkg_procudres.OtmoncoaGenAccountCodesService;

@Service
public class OtmoncoaGenAccountCodesServiceImpl implements OtmoncoaGenAccountCodesService {

	@Autowired
	private OtmoncoaGenAccountCodesRepository otmoncoaGenAccountCodesRepository;

	@Override
	@Transactional
	public Integer otmoncoaGenAccountCodes(final AccountCodes accCodes, final String userName) {
		final List<String> lCaseloadsCur = otmoncoaGenAccountCodesRepository.lCaseloadsCur(accCodes.getCaseloadId());
		final Integer lCurrentPeriodId = otmoncoaGenAccountCodesRepository.lCurrentPeriodCur();
		for (final String caseloadRec : lCaseloadsCur) {
			final Integer lLastClosedPeriod = otmoncoaGenAccountCodesRepository.lLastClosedCur(caseloadRec);
			final List<AccountCodes> icAccBaseCur = otmoncoaGenAccountCodesRepository.lCsldCurrAcctBaseCur(caseloadRec,
					accCodes.getAccountCode(), lLastClosedPeriod);
			for (final AccountCodes insertRec : icAccBaseCur) {
				otmoncoaGenAccountCodesRepository.insertCaseloadCurrentAccountsBase(insertRec.getCaseloadId(),
						insertRec.getAccountCode(), lCurrentPeriodId, userName);
			}

			final List<CaseloadCurrentAccounts> accTnxsCur = otmoncoaGenAccountCodesRepository
					.lCsldCurrAcctTxnsCur(caseloadRec, accCodes.getAccountCode(), lLastClosedPeriod, lCurrentPeriodId);
			otmoncoaGenAccountCodesRepository.insertCaseloadCurrentAccountsTxns(accTnxsCur, userName);

			final List<CaseloadAccountPeriods> accPeriodCur = otmoncoaGenAccountCodesRepository
					.lCsldAcctPeriodsCur(caseloadRec, lLastClosedPeriod);
			otmoncoaGenAccountCodesRepository.insertCaseloadAccountPeriods(accPeriodCur, userName);

			final List<CaseloadAccountSummaries> lCsldAcctSummCur = otmoncoaGenAccountCodesRepository
					.lCsldAcctSummCur(caseloadRec, accCodes.getAccountCode(), lLastClosedPeriod);
			otmoncoaGenAccountCodesRepository.insertCaseloadAccountSummaries(lCsldAcctSummCur, userName);
		}
		return 2;
	}
}
