package net.syscon.s4.sa.admin.mergeoffenders;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;
import net.syscon.s4.sa.recordmaintenance.MergeProcesses;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionBean;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionProcesses;

/**
 * Interface OumtrnbkRepository
 */
public interface OumtrnbkRepository {
	List<MergeProcesses> isProcessTimeRequired(MergeProcesses paramBean);

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	Integer mrgProcInsertVMergeTransactionProcesses(List<MergeTransactionProcesses> object);

	List<MergeProcesses> isProcessDefaultTransfer(MergeProcesses paramBean);

	List<VMergeTransactionProcesses> mrgProcExecuteQuery(VMergeTransactionProcesses object);

	Integer mrgProcDeleteVMergeTransactionProcesses(List<MergeTransactionProcesses> object);

	BigDecimal createMergeTransaction(MergeTransactionBean bean);

	Date getBookingStartDate(long getpFromOffBookId);

	Date getBookingEndDate(long fromOffBookId);

	String chkOffendersForTransfer(MergeTransactionBean bean);

	String processTransferTransaction(MergeTransactionBean bean);
	
	Integer countOffBookings(Long pFromOffRootId);
	
	Integer getInstBookActive(Long pFromOffBookId);
	
	Integer getToBookActive(Long pToOffRootId);
	
	Integer getActiveBookingCur(Long pOffBookId);
	
	public List<VMergeTransactionProcesses> mergeProcExecuteQuery(Integer mergeTransactionId);
	
	Integer updateMergeTransProc(List<MergeTransactionProcesses> object);

}
