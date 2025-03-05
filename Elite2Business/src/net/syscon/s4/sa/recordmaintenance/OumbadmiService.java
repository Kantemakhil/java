package net.syscon.s4.sa.recordmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.admin.beans.VBookAdmin;
import net.syscon.s4.sa.admin.beans.VBookAdminCommitBean;

/**
 * Interface OumbadmiService
 */
public interface OumbadmiService {
	List<AgencyLocations> CgfkchkOffContactsOffB2(AgencyLocations paramBean);

	
	List<ReferenceCodes> cgfkOffContactsBookingStatRecordGroup();

	List<Object> CgwhenNewFormInstance();

	Integer vBookAdmCommit(VBookAdminCommitBean commitBean);

	List<OffenderBookings> offContactsExecuteQuery(OffenderBookings objOffenderBookings);

	List<VBookAdmin> vBookAdmExecuteQuery(VBookAdmin objVBookAdmin);

	Integer offContactsCommit(OffenderBookingsCommitBean commitBean);

}
