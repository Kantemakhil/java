package net.syscon.s4.inst.workflow.managingworkassignments;

import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OidcoasiOffenderAssignments;

/**
 * Interface OidcoasiRepository
 */
public interface OidcoasiRepository {
	List<AgencyLocations> rgAgyLocIdRecordGroup(String caseloadId);

	List<AgencyLocations> rgLivingUnitCodeThreeRecordGroup(String agyLocId, String livingUnitId);

	List<AgencyLocations> rgLivingUnitCodeOneRecordGroup(String agyLocId);

	List<OidcoasiOffenderAssignments> offAsgnExecuteQuery(OidcoasiOffenderAssignments offenderAssignments);

	List<StaffMembers> rgStaffIdRecordGroup(String agyLocId);

	List<AgencyLocations> rgLivingUnitCodeTwoRecordGroup(String agyLocId, String livingUnitId);

	List<AgencyLocations> rgLivingUnitCodeFourRecordGroup(String agyLocId, String livingUnitId);

	int getOffenderCaseOfficersCount(OidcoasiOffenderAssignments offenderAssignments);

	int deleteOffenderCaseOfficers(Long offenderBookingId,String modifyUserId);

	int insertOffenderCaseOfficers(OidcoasiOffenderAssignments offenderAssignments);

}
