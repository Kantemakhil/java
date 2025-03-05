package net.syscon.s4.pkgs.otdbacre.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.pkgs.otdbacre.OtdbacrePkgRepository;
import net.syscon.s4.pkgs.otdbacre.OtdbacrePkgService;

@Service
public class OtdbacrePkgServiceImpl implements OtdbacrePkgService {
	@Autowired
	private OtdbacrePkgRepository otdbacreRepository;

	private static final String N = "N";
	private static final String Y = "Y";

	@Override
	@Transactional
	public void fetchBankClearReconcile(final GlTransactions glTransactions, final String userName) {
		String lInterfaceFlag = null;
		String lFutureFlag = null;
		String lVoidFlag = null;

		// bank_clear_reconciles_tmp 113
		otdbacreRepository.deleteBankClearReconcilesTmp(glTransactions.getCaseloadId());

		// get lInterfaceFlag 117
		lInterfaceFlag = otdbacreRepository.getProfileValue("CLIENT", "BANK_CLR_INF");
		if (lInterfaceFlag == null) {
			lInterfaceFlag = N;
		}

		// get lVoidFlag 126
		lVoidFlag = otdbacreRepository.getProfileValue("CLIENT", "VOID_CLEAR");
		if (lVoidFlag == null) {
			lVoidFlag = Y;
		}

		// get lFutureFlag 135
		lFutureFlag = otdbacreRepository.getProfileValue("CLIENT", "FUTURE_TXN");
		if (lFutureFlag == null) {
			lFutureFlag = Y;
		}
		glTransactions.setInterfaceFlag(lInterfaceFlag);
		glTransactions.setVoidFlag(lVoidFlag);
		glTransactions.setFutureFlag(lFutureFlag);

		// insert bank_clear_reconciles_tmp 145
		glTransactions.setCreateUserId(userName);
		otdbacreRepository.insertBankClearReconcilesTmp(glTransactions);

		// update bank_clear_reconciles_tmp 239
		otdbacreRepository.updateBankClearReconcilesTmp(glTransactions.getCaseloadId(), glTransactions.getAccountCode(),
				userName);

		// update bank_clear_reconciles_tmp 249
		otdbacreRepository.updateBankClearReconcilesTmpOne(userName);

	}
}