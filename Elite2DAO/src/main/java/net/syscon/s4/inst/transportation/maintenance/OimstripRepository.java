package net.syscon.s4.inst.transportation.maintenance;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovement;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripAssignments;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTrips;
import net.syscon.s4.inst.transportation.maintenance.beans.Trips;

/**
 * Interface OimstripRepository
 */
public interface OimstripRepository {
	List<StaffMembers> rgStaffIdRecordGroup();

	List<ScheduledTrips> scheduledTripsExecuteQuery(ScheduledTrips objScheduledTrips);

	List<ReferenceCodes> rgTripTypeRecordGroup();

	List<Trips> tripsExecuteQuery(Trips objTrips);

	Integer scheduledTripAssignmentsInsertScheduledTripAssignments(
			List<ScheduledTripAssignments> lstScheduledTripAssignments);

	Integer tripsInsertTrips(List<Trips> lstTrips);

	Integer scheduledTripsUpdateScheduledTrips(List<ScheduledTrips> lstScheduledTrips);

	Integer staffAssignmentUpdateScheduledTripAssignments(List<ScheduledTripAssignments> lstScheduledTripAssignments);

	Integer tripsCommit(List<Trips> lstTrips);

	Integer scheduledTripAssignmentsDeleteScheduledTripAssignments(
			List<ScheduledTripAssignments> lstScheduledTripAssignments);

	Integer staffAssignmentInsertScheduledTripAssignments(List<ScheduledTripAssignments> lstScheduledTripAssignments);

	List<ScheduledTripAssignments> staffAssignmentExecuteQuery(ScheduledTripAssignments objScheduledTripAssignments);

	List<ScheduledTripAssignments> scheduledTripAssignmentsExecuteQuery(
			ScheduledTripAssignments objScheduledTripAssignments);

	Integer staffAssignmentDeleteScheduledTripAssignments(List<ScheduledTripAssignments> lstScheduledTripAssignments);

	Integer tagtransportCTrip(String tripCode);

	List<ScheduledTrips> scheduleGenerateOidgenst(String tripCode);

	List<OffenderExternalMovement> offBkgCur(Long sacheduledTripId);

	List<BigDecimal> offNadmtCur(Long sacheduledTripId);

	List<ScheduledTrips> schidCur(String tripCode, Date departureDate);

	Long ifOffOnTrip(String tripCode, Date departureDate);

	Long ifNadtOnTrip(String tripCode, Date departureDate);

	List<BigDecimal> getSchIdCur(String tripCode, Date departureDate);

	List<BigDecimal> getEventIdCur(Long pSchId);

	Integer vOffenderAllSchedules2(Long vEventId);

	Integer nonAdmittedInmateMvmts(Long pSchId);

	Integer tripsOidgenstInsert(Trips tripsModel);

	List<ScheduledTrips> scheduledTripsExecuteQueryExtra(Long scheduledTripId);

	void triptableupdate(String tripCode, String modifyUserId);

	int ifAssignedCur(Long assignedId, Date departureDate, Date completionDate);

}
