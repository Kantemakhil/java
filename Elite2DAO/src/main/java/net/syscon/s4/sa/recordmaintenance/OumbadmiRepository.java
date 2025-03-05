package net.syscon.s4.sa.recordmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.sa.admin.beans.VBookAdmin;

/**
 * Interface OumbadmiRepository
 */
public interface OumbadmiRepository {
	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<ReferenceCodes> cgfkOffContactsBookingStatRecordGroup();

	List<AgencyLocations> cgfkchkOffContactsOffB2(AgencyLocations paramBean);

	Integer offContactsUpdateOffenderBookings(List<OffenderBookings> lstOffenderBookings);

	List<ReferenceCodes> cgfkchkOffContactsOffBkg(ReferenceCodes paramBean);

	List<OffenderBookings> offContactsExecuteQuery(OffenderBookings objOffenderBookings);

	List<VBookAdmin> vBookAdmExecuteQuery(VBookAdmin objVBookAdmin);

	Integer offenderBookingsBookingStatus(OffenderBookings paramBean);

}
