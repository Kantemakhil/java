package net.syscon.s4.pkgs.merge_transacation_request;

import net.syscon.s4.sa.admin.beans.MergeTransactions;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionBean;

public interface MergeTransactionRequestService {
	
	public Long manualCreateRequest(MergeTransactions bean);
	
	public void setStatusToInprogress(Long mergeTransactionId, String requestStatusCode);
	
	public Integer setStatus(Long mergeTransactionId, String requestStatusCode);
	
	public Integer setStatusToCompleted(Long mergeTransactionId, String requestStatusCode);
	
	public String processTrnBkgRequest(MergeTransactionBean bean);
	
	public Integer createTransferBkgRequest(MergeTransactionBean bean);

	public void setStatusToFailed(Long mergeTransactionId, String requestStatusCode);
}
