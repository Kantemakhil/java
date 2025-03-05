package net.syscon.s4.inst.visitsmanagement.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyVisitDays;
import net.syscon.s4.im.beans.AgencyVisitSlots;
import net.syscon.s4.im.beans.AgencyVisitTimes;

/**
 * Interface OimvdtslRepository
 */
public interface OimvdtslRepository {
	List<AgencyVisitSlots> agyVisitSlotsExecuteQuery(AgencyVisitSlots object);

	List<AgencyVisitDays> agyVisitDaysExecuteQuery(AgencyVisitDays object);

	List<AgencyLocations> rgAgyIntLocRecordGroup(String userId);

	Integer agyVisitTimesOnCheckDeleteMaster(AgencyVisitSlots paramBean);

	List<AgencyVisitDays> rgWeekdayRecordGroup();

	List<AgencyInternalLocations> rgAgyVisitSlotsRecordGroup(String agyLocId, String userId);

	Integer agyVisitDaysOnCheckDeleteMaster(AgencyVisitTimes paramBean);

	List<AgencyVisitTimes> agyVisitTimesExecuteQuery(AgencyVisitTimes object);

	List<ReferenceCodes> rgAgyVisitDaysRecordGroup();

	Integer agyvisitdaysInsertAgencyVisitDays(List<AgencyVisitDays> object);

	Integer agyVisitDaysDeleteAgencyVisitDays(List<AgencyVisitDays> object);

	Integer agyVisitDaysUpdateAgencyVisitDays(List<AgencyVisitDays> object);

	Integer agyvisitslotsInsertAgencyVisitSlots(List<AgencyVisitSlots> object);

	AgencyVisitSlots agyVisitSlotsDeleteAgencyVisitSlots(List<AgencyVisitSlots> object);

	Integer agyVisitSlotsUpdateAgencyVisitSlots(List<AgencyVisitSlots> object);

	Integer agyvisittimesInsertAgencyVisitTimes(List<AgencyVisitTimes> object);

	AgencyVisitTimes agyVisitTimesDeleteAgencyVisitTimes(List<AgencyVisitTimes> object);

	AgencyVisitTimes agyVisitTimesUpdateAgencyVisitTimes(List<AgencyVisitTimes> object);

	AgencyVisitSlots getCapacityValue(AgencyVisitSlots objSearchDao);

	AgencyVisitSlots getLocationDescription(AgencyVisitSlots objSearchDao);

	String getCheckTimeSlots(AgencyVisitSlots object);

	Integer getNextAgencyVisitSlotId();

	String agyVisitTimescheckboxChange(AgencyVisitTimes searchBean);
}
