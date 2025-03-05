package net.syscon.s4.pkgs.transfer_booking_core;

import java.util.Date;
import java.util.List;

import net.syscon.s4.pkgs.TransferBookingTables;
import net.syscon.s4.pkgs.TransferTableRelationships;
import net.syscon.s4.sa.admin.beans.MergeTransactions;

public interface TransferBookingCoreRepository {
	
	public Date getAdmDate(Long pOffBookId);
	
	public Date getIntakeDate(Long pOffBookId);
	
	public Date getInstEndDate(Long pOffBookId);
	
	public Date getCommEndDate(Long pOffBookId);
	
	public Integer isCommBooking(Long pOffBookId);
	
	public Object execPSqlDml(String pSqlDml);
	
	public Integer insertMrgOffSqls( String pSqlDml);

	public List<MergeTransactions> mergeTableListCur(Long pMergeTransactionId, String pApplnCode);
	
	public List<TransferBookingTables> cascadeTrnsMergeTableListCur(String pTableName);
	
	public TransferBookingTables getTransferBookingTablesVals(String pTableName);
	
	public Integer getCount(String sql);
	
	public TransferTableRelationships getTrTabRelData(String pParentTableName, String pTableName);
	
	public TransferTableRelationships getTrnsfTblRltnData(String pParentTable, String pChildTable);
	
	public Integer getMaxSeq(String sql);
	
	public TransferBookingTables getTransferBkngTablesData(String pTableName);
	
	public Long getPrvBookIdCur(Long pOffBookId, Long pRootOffId);
	
	public Long getNextBookIdCur(Long pOffBookId, Long pRootOffId);
	
}
