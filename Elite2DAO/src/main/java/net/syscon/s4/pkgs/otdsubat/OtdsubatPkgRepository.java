package net.syscon.s4.pkgs.otdsubat;

public interface OtdsubatPkgRepository {

	Long getOffBookIdProcTxn(final Long pOffenderId, final String pCaseloadId);

	Integer updateOffenderTransactions(final Long pTxnId, final Integer pTxnEntrySeq, final String userName);
}