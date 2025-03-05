package net.syscon.s4.sa.admin.mergeoffenders;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionBean;

/**
 * Interface OummerofService
 */
public interface OummerofService {
	List<OffenderBookings> offBkgOnCheckDeleteMaster(OffenderBookings paramBean);

	String offBooksPostQuery(OffenderBookings paramBean);

	List<ReferenceCodes> offBooks2PostQuery(ReferenceCodes paramBean);

	List<OffenderBookings> offBooksExecuteQuery(OffenderBookings objOffenderBookings);

	List<OffenderBookings> offBkg2OnCheckDeleteMaster(OffenderBookings paramBean);

	List<VHeaderBlock> vOffBkgExecuteQuery(VHeaderBlock objVHeaderBlock);

	List<ReferenceCodes> rgAssignmentReasonRecordGroup();

	String manualCreateRequest(MergeTransactionBean bean);

	Integer chkOffendersForTransfer(MergeTransactionBean bean);
	
	Long getNewOffId(Long offBookId);
}
