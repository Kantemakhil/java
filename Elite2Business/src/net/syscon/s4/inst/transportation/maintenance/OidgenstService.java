package net.syscon.s4.inst.transportation.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripParameters;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripParametersCommitBean;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTrips;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripsCommitBean;

public interface OidgenstService {
	List<ScheduledTripParameters> schPlannerExecuteQuery(ScheduledTripParameters objScheduledTripParameters);

	Integer schPlannerCommit(ScheduledTripParametersCommitBean CommitBean);

	ScheduledTrips scheduledTripsCommit(ScheduledTripsCommitBean CommitBean);

	List<ScheduledTrips> scheduledTripsExecuteQuery(ScheduledTrips objScheduledTrips);

	List<ReferenceCodes> rgRouteRecordGroup();

	ScheduledTrips scheduledTripsvalidate(ScheduledTrips bean);

	ScheduledTrips scheduledGenerateCommit(List<ScheduledTripParameters> bean);
	

}
