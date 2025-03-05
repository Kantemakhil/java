package net.syscon.s4.inst.movements.housingchanges;

import java.util.List;

import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.movements.beans.ReserveBedLocations;

/**
 * Interface OidrhlocRepository
 * 
 */
public interface OidrhlocRepository {
	CaseloadAgencyLocations cgfkchkResBlResBlCsldAl(CaseloadAgencyLocations paramBean);

	Integer resBlUpdateReserveBedLocations(List<ReserveBedLocations> object);

	Integer resBlInsertReserveBedLocations(List<ReserveBedLocations> object);

	List<CaseloadAgencyLocations> cgfkResBlAgyLocIdRecordGroup(String caseloadId);

	List<ReserveBedLocations> resBlExecuteQuery(ReserveBedLocations object);

	Integer resBlDeleteReserveBedLocations(List<ReserveBedLocations> object);

	LivingUnits getLivingUnitIddesc(final LivingUnits searchBean);

	Offenders getOffenderDetails(final Offenders searchBean);

	Integer validateLivingUnitId(OffenderBookings offbkgObj);

	ReserveBedLocations checkAllConflictsQuery(ReserveBedLocations obj);

	LivingUnits getBookingInfoCur(String rootOffIdValue, String agyLocId);

	Integer getOccCountCur(ReserveBedLocations obj);

	//Integer getCountForLivUnit(ReserveBedLocations obj);

	Integer getVcountCur(ReserveBedLocations searchRecord);

	OffenderBookings getBookingInfoCur(ReserveBedLocations obj);
}
