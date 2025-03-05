package net.syscon.s4.inst.movements.housingchanges;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.inst.movements.beans.ReserveBedLocations;

/**
 * Interface OmurmresRepository
 */
public interface OmurmresRepository {
	Integer resBlUpdateReserveBedLocations(List<ReserveBedLocations> lstReserveBedLocations);

	Integer sysPflInsertSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	Integer resBlInsertReserveBedLocations(List<ReserveBedLocations> lstReserveBedLocations);

	ReferenceCodes cgfkchkResBlResBlRefCod(ReferenceCodes paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<ReferenceCodes> cgfkResBlRemoveReasonRecordGroup();

	List<ReserveBedLocations> resBlExecuteQuery(ReserveBedLocations objReserveBedLocations);

	Integer sysPflDeleteSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	Integer resBlDeleteReserveBedLocations(List<ReserveBedLocations> lstReserveBedLocations);

	List<VHeaderBlock> cgfkchkResBlResBlVHbF1(VHeaderBlock paramBean);

	VHeaderBlock displayItemsExecuteQuery(VHeaderBlock beanObj);

	Integer offBookingUpdate(VHeaderBlock vblock);

	Integer bedAhInsertBedAssignmentHistories(BedAssignmentHistories insertBed);

	Integer bedAhPreInsert(BedAssignmentHistories insertBed);
	
	List<OffenderBookings>  getoffBookOldRec(BigDecimal OffenderBookId);

}
