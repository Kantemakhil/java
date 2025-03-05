package net.syscon.s4.pkgs.transfer_booking_trust;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.inmate.beans.OffenderSubAccounts;

public interface TransferBookingTrustRepository {

	public Date getBeginDate(Long pMergeTransactionId, String pProcessName);
	
	public Date getEndDate(Long pMergeTransactionId, String pProcessName);
	
	public BigDecimal getPostedOffTxn(Long pOffenderId, String pCaseLoadId, Long pSubAcctCode);
	
	public List<OffenderSubAccounts> getCurrSubAccountBal(Long pOffenderId);
	
	public Integer deductionTxnWithinTime(Long pRootOffenderId, Date leftDateTime, Date rightDateTime);
	
	public Integer deductionTxnOutofTime(Long pRootOffenderId, Date leftDateTime, Date rightDateTime);
	
}
