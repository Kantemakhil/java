package net.syscon.s4.pkgs.transfer_booking_core;

import java.util.Date;

public interface TransferBookingCoreService {

	public void transferBookings(Long mergeTransactionId, Long fromRootOffenderId, Long fromOffenderBookId,
			Long toRootOffenderId, Long toOffenderBookId, String mergeType);

	public Date getBookingStartDate(Long pOffBookId);
	
	public Date getBookingEndDate(Long pOffBookId);
	
	public void runMergeSql(String pSqlDml);
	
	public void transferApplnRecords(Long pMergeTransactionId, String pApplnCode, String user);
	
	public Long getPrvBookId(Long pOffBookId, Long pRootOffId);
	
	public Long getNextBookId(Long pOffBookId, Long pRootOffId);
}
