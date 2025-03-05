package net.syscon.s4.inst.transportation.maintenance;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTripParameters;
import net.syscon.s4.inst.transportation.maintenance.beans.ScheduledTrips;

public interface OidgenstRepository {

	List<ScheduledTripParameters> schPlannerExecuteQuery(ScheduledTripParameters objScheduledTripParameters);

	Integer scheduledTripsUpdateScheduledTrips(List<ScheduledTrips> lstScheduledTrips);

	Integer scheduledTripsDeleteScheduledTrips(List<ScheduledTrips> lstScheduledTrips);

	Integer schPlannerInsertScheduledTripParameters(List<ScheduledTripParameters> lstScheduledTripParameters);

	List<ScheduledTrips> scheduledTripsExecuteQuery(ScheduledTrips objScheduledTrips);

	Integer scheduledTripsInsertScheduledTrips(List<ScheduledTrips> lstScheduledTrips);

	List<ReferenceCodes> rgRouteRecordGroup();

	Integer schPlannerUpdateScheduledTripParameters(List<ScheduledTripParameters> lstScheduledTripParameters);

	Integer scheduledTripsvalidate(ScheduledTrips bean);

	Integer scheduledGenerateCount(ScheduledTrips bean);

	Date scheduledGenerateDate(ScheduledTrips bean);
	
	Integer ifExistCur(ScheduledTrips bean);
	
	Integer oidgenst_if_sch_cur(ScheduledTrips bean);
	
	Integer oidgenstUpdateTrips(ScheduledTrips bean);

	Long getSchedSeq();
	
	String getWeekDay(ScheduledTrips bean);
	
	String getEndDay(ScheduledTrips bean);
	
	Date getMaxDate(ScheduledTrips schTrip);

}
