package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.AgencyVisitSlots;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAllVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitDetails;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;

/**
 * Interface OiivisitRepository
 */
public interface OiivisitRepository {
	List<VOffenderVisitDetails> offVisExecuteQuery(VOffenderVisitDetails objVOffenderVisitDetails);

	Integer offImpInsertVOffenderAllVisitors(List<VOffenderAllVisitors> lstVOffenderAllVisitors);

	List<VOffenderAllVisitors> offImpExecuteQuery(VOffenderVisitDetails objVOffenderAllVisitors);

	List<AgencyLocations> rgAgencyLocationsRecordGroup(String caseloadId, String caseloadType);

	List<AgencyInternalLocations> rgAgyVisitDayOfWeekRecordGroup(String agyLocId);

	List<VOffenderVisits> rgAgyVisitTimeSlotRecRecordGroup(String agyLocId, String weekDay);

	List<AgencyVisitSlots> rgAgyVisitSlotsRecRecordGroup(String agyLocId, String weekDay, String timeSlotSeq);

	Integer offenderVisitsSaveForm(List<VOffenderVisits> updateList);

	List<AgencyVisitSlots> rgVisitLocationWithoutDay(String agyLocId);

}
