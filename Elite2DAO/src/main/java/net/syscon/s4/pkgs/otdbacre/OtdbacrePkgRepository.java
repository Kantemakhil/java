package net.syscon.s4.pkgs.otdbacre;

import java.math.BigDecimal;

import net.syscon.s4.inmate.beans.GlTransactions;

public interface OtdbacrePkgRepository {
	void deleteBankClearReconcilesTmp(final String caseloadId);

	String getProfileValue(final String profileType, final String profileCode);

	void insertBankClearReconcilesTmp(final GlTransactions glTransactions);

	void updateBankClearReconcilesTmp(final String caseloadId, final BigDecimal accountCode, final String userName);

	void updateBankClearReconcilesTmpOne(final String userName);
}