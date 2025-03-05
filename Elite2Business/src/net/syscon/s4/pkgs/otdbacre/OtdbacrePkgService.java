package net.syscon.s4.pkgs.otdbacre;

import net.syscon.s4.inmate.beans.GlTransactions;

public interface OtdbacrePkgService {
	void fetchBankClearReconcile(final GlTransactions glTransactions, final String userName);
}