package net.syscon.s4.sa.admin.mergeoffenders;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionBean;

/**
 * Interface OummerofRepository
 */
public interface OummerofRepository {
	List<OffenderTrustAccounts> offBooks2PostQuery(OffenderTrustAccounts paramBean);

	List<ReferenceCodes> offBooks2PostQuery(ReferenceCodes paramBean);

	List<OffenderBookings> offBkg2OnCheckDeleteMaster(OffenderBookings paramBean);

	List<OffenderBookings> offBkgOnCheckDeleteMaster(OffenderBookings paramBean);

	List<OffenderBookings> offBooksExecuteQuery(OffenderBookings objOffenderBookings);

	String offBooksPostQuery(OffenderBookings paramBean);

	List<VHeaderBlock> vOffBkgExecuteQuery(VHeaderBlock objVHeaderBlock);

	List<ReferenceCodes> rgAssignmentReasonRecordGroup();

	Integer getTrustAccoutFlag(OffenderBookings searchRecord);

	String manualCreateRequest(MergeTransactionBean bean);

	String chkOffendersForTransfer(MergeTransactionBean bean);

	Integer updateOffenderBookingRecords(List<OffenderBookings> returnList);
	
	Integer getMergeTransactionID();

	Integer insertMergeTransactionRecord(List<MergeTransactionBean> saveList);

	Integer updateOffenderRecords(List<Offenders> offenderUpdate);
	
	public Long getOffenderBookId(Long offenderId);
	
	public Integer updateOffenderRecords1(Long offenderId, String lastName, String firstName);
	
	public Integer updateOffenderRecords2(Long offenderId, Long rootOffenderId);
	
	public List<Offenders> getOldOffenderData(Long offenderId); 
	
	public Long getNewOffId(Long offBookId);
	
}
