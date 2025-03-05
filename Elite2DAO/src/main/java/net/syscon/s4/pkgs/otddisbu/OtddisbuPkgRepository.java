package net.syscon.s4.pkgs.otddisbu;

import java.math.BigDecimal;

import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;

public interface OtddisbuPkgRepository {

	Long lOffenderBookCur(final Long offenderId, final String caseloadId);

	Long lOffenderInactCur(final Long offenderId, final String caseloadId);

	void insertOffenderTransactions(final OtddisbuProcessTransactionsBean proTxn);

	Integer lChequeExistsCur(final Integer txnId);

	void updateBankChequeData(final Integer txnId, final BigDecimal txnEntryAmount, final String userName);

}
