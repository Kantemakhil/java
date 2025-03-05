package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyVisitSlots;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAllVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitDetails;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitsCommitBean;

/**
 * Interface OiivisitService
 */
public interface OiivisitService {
	List<VOffenderVisitDetails> offVisExecuteQuery(VOffenderVisitDetails objVOffenderVisitDetails);

	List<VOffenderAllVisitors> offImpExecuteQuery(VOffenderVisitDetails objVOffenderAllVisitors);

	List<AgencyLocations> rgAgencyLocationsRecordGroup(String caseloadId, String caseloadType);

	List<AgencyInternalLocations> rgAgyVisitDayOfWeekRecordGroup(String agyLocId);

	List<VOffenderVisits> rgAgyVisitTimeSlotRecRecordGroup(String agyLocId, String weekDay);

	List<AgencyVisitSlots> rgAgyVisitSlotsRecRecordGroup(String agyLocId, String weekDay, String timeSlotSeq);

	Integer offenderVisitsSaveForm(VOffenderVisitsCommitBean commitBean);

	List<AgencyVisitSlots> rgVisitLocationWithoutDay(String agyLocId);

}
