package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;

/**
 * Interface OumpurgeService
 */
public interface OumpurgeService {
	List<OffenderBookings> offBkgExecuteQuery(OffenderBookings objOffenderBookings);

	List<VHeaderBlock> offExecuteQuery(VHeaderBlock objVHeaderBlock);

	Integer offBkgCommit(OffenderBookingsCommitBean commitBean);

	VHeaderBlock  purgeOffenderCommit(VHeaderBlock object);

	String showCaseloadAcct(VHeaderBlock object);
	
	Integer checkActiveBooking(VHeaderBlock object);
	
	Integer processRecord(VHeaderBlock objVHeaderBlock);

	VHeaderBlock whenTimerExpired(VHeaderBlock object, String userName);

	Integer getSealButtonAccessPermission(String userName);

	Integer getLvCountSealBookings(String rootOffenderId);

}
