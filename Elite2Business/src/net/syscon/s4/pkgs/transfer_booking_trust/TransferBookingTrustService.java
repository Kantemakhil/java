package net.syscon.s4.pkgs.transfer_booking_trust;

import java.util.Date;

public interface TransferBookingTrustService {
	
	public Boolean validateBeforeTransfer(Long pFromOffRootId, Long pFromOffBookId, Long pToOffRootId, Long pToOffBookId,
			Long pMergeTransactionId);
	
	public Date getBeginDate(Long pMergeTransactionId, String pProcessName);
	
	public Date getEndDate(Long pMergeTransactionId, String pProcessName);
	
	public Boolean isAccountInSync(Long pRootOffenderID);
	
	public Integer checkAlienDedTxn(Long pRootOffenderID, Date leftDateTime, Date rightDateTime);
	
	public Integer checkOwnDedTxn(Long pRootOffenderID, Date leftDateTime, Date rightDateTime);

	
	
	
}
