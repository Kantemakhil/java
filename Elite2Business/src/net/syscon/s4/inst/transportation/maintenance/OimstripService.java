package net.syscon.s4.inst.transportation.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripAssignments;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripAssignmentsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTrips;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripsCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.Trips;
import net.syscon.s4.inst.transportation.maintenance.beans.TripsCommitBean;

/**
 * Interface OimstripService
 */
public interface OimstripService {
	Integer staffAssignmentCommit(ScheduledTripAssignmentsCommitBean CommitBean);

	List<StaffMembers> rgStaffIdRecordGroup();

	Integer scheduledTripsCommit(ScheduledTripsCommitBean CommitBean);

	Integer tripsCommit(TripsCommitBean CommitBean);

	List<ScheduledTrips> scheduledTripsExecuteQuery(ScheduledTrips objScheduledTrips);

	List<ReferenceCodes> rgTripTypeRecordGroup();

	Integer scheduledTripAssignmentsCommit(ScheduledTripAssignmentsCommitBean CommitBean);

	List<ScheduledTripAssignments> staffAssignmentExecuteQuery(ScheduledTripAssignments objScheduledTripAssignments);

	List<ScheduledTripAssignments> scheduledTripAssignmentsExecuteQuery(
			ScheduledTripAssignments objScheduledTripAssignments);

	List<Trips> tripsExecuteQuery(Trips objTrips);

	Integer tagtransportCTrip(String tripCode);

	List<ScheduledTrips> scheduleGenerateOidgenst(String tripCode);

	Integer tripsOidgenstInsert(Trips tripsModel);

}
