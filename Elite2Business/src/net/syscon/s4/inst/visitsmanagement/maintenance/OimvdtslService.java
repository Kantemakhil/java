package net.syscon.s4.inst.visitsmanagement.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyVisitDays;
import net.syscon.s4.im.beans.AgencyVisitDaysCommitBean;
import net.syscon.s4.im.beans.AgencyVisitSlots;
import net.syscon.s4.im.beans.AgencyVisitSlotsCommitBean;
import net.syscon.s4.im.beans.AgencyVisitTimes;
import net.syscon.s4.im.beans.AgencyVisitTimesCommitBean;

/**
 * Interface OimvdtslService
 */
public interface OimvdtslService {
	List<AgencyInternalLocations> rgAgyVisitSlotsRecordGroup(String agyLocId, String userId);

	List<AgencyVisitSlots> agyVisitSlotsExecuteQuery(AgencyVisitSlots object);

	List<AgencyVisitSlots> agyVisitSlotsCommit(AgencyVisitSlotsCommitBean commitBean);

	List<AgencyVisitDays> agyVisitDaysCommit(AgencyVisitDaysCommitBean commitBean);

	List<AgencyVisitTimes> agyVisitTimesCommit(AgencyVisitTimesCommitBean commitBean);

	List<AgencyVisitDays> agyVisitDaysExecuteQuery(AgencyVisitDays object);

	List<AgencyLocations> rgAgyIntLocRecordGroup(String userId);

	List<AgencyVisitTimes> agyVisitTimesExecuteQuery(AgencyVisitTimes object);

	Integer agyVisitDaysOnCheckDeleteMaster(AgencyVisitTimes paramBean);

	Integer agyVisitTimesOnCheckDeleteMaster(AgencyVisitSlots paramBean);

	List<AgencyVisitDays> rgWeekdayRecordGroup();

	List<ReferenceCodes> rgAgyVisitDaysRecordGroup();

	AgencyVisitSlots agyGetCapaityFrom(AgencyVisitSlots agencyVisitSlots);

	AgencyVisitSlots getLocationDescription(AgencyVisitSlots objSearchDao);

	String agyVisitTimescheckboxChange(AgencyVisitTimes searchBean);
}
