package net.syscon.s4.inst.movements.housingchanges;

import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.movements.beans.ReserveBedLocations;
import net.syscon.s4.inst.movements.beans.ReserveBedLocationscommitBean;

/**
 * Interface OidrhlocService
 */
public interface OidrhlocService {
	List<CaseloadAgencyLocations> cgfkResBlAgyLocIdRecordGroup(String caseloadId);

	Integer resBlCommit(ReserveBedLocationscommitBean commitBean);

	List<ReserveBedLocations> resBlExecuteQuery(ReserveBedLocations object);

	List<CaseloadAgencyLocations> cgfkchkResBlResBlCsldAl(CaseloadAgencyLocations paramBean);

	Integer validateLivingUnitId(OffenderBookings searchRecord);

	LivingUnits getBookingInfoCur(String offenderId, String caseloadId);

	Boolean getOcFlagValue(ReserveBedLocations searchRecord);

}
