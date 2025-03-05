package net.syscon.s4.pkgs.merge_transacation_request;

import net.syscon.s4.sa.admin.beans.MergeTransactions;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionBean;

public interface MergeTransactionRequestRepository {
	
	public Long getMergeTransactionsId();
	
	public Integer insertMergeTransactions(MergeTransactions bean);
	
	public Integer setStatus(Long mergeTransactionId, String requestStatusCode);
	
	public Integer updateTransaction(Long mergeTransactionId, Long fromOffenderBookId, Long fromRootOffenderId,
			Long fromOffenderId, String fromOffenderIdDisplay, String fromLastName, String fromfirstName,
			Long toOffenderBookId, Long toRootOffenderId, Long toOffenderId, String toOffenderIdDisplay,
			String toLastName, String tofirstName);

	public Integer insertMrgTransactions(MergeTransactionBean bean);
}
