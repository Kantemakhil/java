package net.syscon.s4.inst.incidentsoic;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairs;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.im.incidentsoic.beans.IncidentFollowUpDetails;
import net.syscon.s4.im.incidentsoic.beans.SignificantIncident;

/**
 * Interface OidincdeRepository
 */

public interface OidincdeRepository {
	List<ReferenceCodes> rgRepairTypesRecordGroup(String domain);

	List<CaseloadAgencyLocations> rgAgyLocIdsRecordGroup(String caseloadId);

	List<OicOffences> rgOicOffenceCodesRecordGroup(String startDate, String endDate);

	List<ReferenceCodes> rgIncidentTypesRecordGroup(String domain);

	List<AgencyInternalLocations> rgLevelInternalLocationIdsRecordGroup(String agencyLocId);

	List<ReferenceCodes> rgOffInvActionCodesRecordGroup(String domain);

	List<ReferenceCodes> rgOffInvIncidentRolesRecordGroup(String domain);

	List<StaffMembers> rgReportedStaffIdsRecordGroup(String caseloadId);

	List<ReferenceCodes> rgStaffInvIncidentRolesRecordGroup(String domain);

	Integer agencyIncidentRepairsInsertAgencyIncidentRepairs(List<AgencyIncidentRepairs> lstAgencyIncidentRepairs);

	List<AgencyIncidents> agencyIncidentsExecuteQuery(AgencyIncidents objSearchDao);

	List<AgencyIncidentParties> agyIncPartiesOffenderExecuteQuery(AgencyIncidentParties objSearchDao);

	Integer agencyIncidentChargesUpdateAgencyIncidentCharges(List<AgencyIncidentCharges> lstAgencyIncidentCharges);

	List<Object> agyIncPartiesOffenderOnCheckDeleteMasteragencyIncidentChargesCur(String lstagencyincident,
			String lstpartyseq);

	Integer agyIncPartiesOffenderPreInsert(Integer offenderbookId, String agencyIncidentId);

	Integer agencyIncidentRepairsDeleteAgencyIncidentRepairs(List<AgencyIncidentRepairs> lstAgencyIncidentRepairs);

	Integer agencyIncidentChargesDeleteAgencyIncidentCharges(List<AgencyIncidentCharges> lstAgencyIncidentCharges);

	Integer agencyIncidentRepairsUpdateAgencyIncidentRepairs(List<AgencyIncidentRepairs> lstAgencyIncidentRepairs);

	Integer agencyIncidentChargesInsertAgencyIncidentCharges(List<AgencyIncidentCharges> lstAgencyIncidentCharges);

	Integer agencyIncidentChargesInsertAgencyIncidentChargesSeq(Integer integer);

	Integer agencyIncidentsUpdateAgencyIncidents(List<AgencyIncidents> lstAgencyIncidents);

	Integer agyIncPartiesOffenderDeleteAgencyIncidentParties(List<AgencyIncidentParties> lstAgencyIncidentParties);

	List<Object> agencyIncidentsOnCheckDeleteMasteragencyIncidentRepairsCur(String lstagencyincident);

	Integer agyIncPartiesOffenderUpdateAgencyIncidentParties(List<AgencyIncidentParties> lstAgencyIncidentParties);

	List<AgencyIncidentRepairs> agencyIncidentRepairsExecuteQuery(AgencyIncidentRepairs objSearchDao);

	List<Object> agencyIncidentsOnCheckDeleteMasteragyIncPartiesOffenderCur(String lstagencyincident);

	List<AgencyIncidentCharges> agencyIncidentChargesExecuteQuery(AgencyIncidentCharges objSearchDao);

	List<Object> agencyIncidentsOnCheckDeleteMasteragyIncPartiesStaffCur(String lstagencyincident);

	Integer agyIncPartiesOffenderInsertAgencyIncidentParties(List<AgencyIncidentParties> lstAgencyIncidentParties);

	Integer agencyIncidentsInsertAgencyIncidents(List<AgencyIncidents> lstAgencyIncidents);

	List<OicOffences> agyIncidentChargePostQuery(AgencyIncidentCharges searchRecord);

	List<StaffMembers> searchStaffMembers(StaffMembers staffCodeBean);
	
	List<StaffMembers> searchStaffMembersByUserId(StaffMembers staffCodeBean);

	List<ReferenceCodes> searchEvidenceType(ReferenceCodes staffCodeBean);

	List<Offenders> agencyIncidentsPartiesOffenderIdCur(Offenders offenders);

	List<OicOffences> agencyIncidentChargesPostQuery(String systemMode);

	List<StaffMembers> setGlobalCaseloadIdworkingCaseloadCur(String systemMode);

	Object oidincdeStateadmUsrCsr(String systemMode, String userName);

	List<CaseloadAgencyLocations> oidincdeState(String caseloadId);

	List<AgencyIncidents> setUpdatedIncidentDetailsupdIncDetCur(String lstagencyincident);

	List<SystemProfiles> checkUnlockAccessspProfileCur(String systemMode);

	List<StaffMemberRoles> checkUnlockAccesscheckUnlockAccess(String pValue3);

	List<OmsRoles> checkUnlockAccesscheckRoleAccess(String pValue, String pValue2);

	// Integer agencyIncidentPartiesPreSeqPreInsert(AgencyIncidentParties
	// lstAgencyIncidentParties);

	List<StaffMembers> staffidExecuteQuery(String userId);

	Integer agencyIncidentRepaiPreInsertcDAO(Integer incidentId);

	Integer agyIncPartiesStaffInsertAgency(List<AgencyIncidentParties> lstAgencyIncidentParties);

	List<VNameSearch> nameSrchExecuteQuery(VNameSearch objSearchDao);

	List<VNameSearch> nameSrchExecuteoffenderQuery(VNameSearch objSearchDao);

	List<AgencyIncidentParties> agyIncPartiesOffenderStaffxecuteQuery(AgencyIncidentParties searchRecord);

	List<OicOffences> rgOicOffenceCodesRecord();

	OicOffences agyInciChgPostQuery(OicOffences paramBean);

	Integer agencyIncidentPreInsertcDAO();

	List<SignificantIncident> sigificantIncidentExecuteQuery(SignificantIncident commitBean);

	int offenderWeaponcommitInsert(List<SignificantIncident> insertList);

	int offenderWeaponcommitupdate(List<SignificantIncident> updateList);

	int offenderWeaponcommitdelete(List<SignificantIncident> deleteList);

	Integer agencyIncidentPartiesPreSeqPreInsert(List<AgencyIncidentParties> lstAgencyIncidentParties);

	String getEnhancedStaffReporter(Integer code);

	int updIncStaffReportType(Integer agencyIncidentId, Integer staffId, String staffReportType , Integer partySeq ,Date modifyDateTime ,String modifyUserId);

	Integer agencyIncidentPartiesPreSeqPreInsertcDAO(Integer agencyIncidentId);

	Integer incidentFollowUpInsertData(List<IncidentFollowUpDetails> insertList);

	Integer incidentFollowUpUpdateData(List<IncidentFollowUpDetails> updateList);

	Integer incidentFollowUpDeleteData(List<IncidentFollowUpDetails> deleteList);

	List<IncidentFollowUpDetails> getIncidentFollowUpDetails(IncidentFollowUpDetails searchBean);
	Map<String, Boolean> checkPermissionForTabAccess(String userName);

	Integer deleteIncidentStaffReport(List<AgencyIncidentParties> lstAgencyIncidentParties);

	Integer deleteIncidentStaffEquipmentData(List<AgencyIncidentParties> lstAgencyIncidentParties);

	Integer deleteIncidentStaffForcesData(List<AgencyIncidentParties> lstAgencyIncidentParties);
	
	String getProfileCode();

	List<CaseloadAgencyLocations> getCommunityOfficesData(String caseloadId);

	List<ReferenceCodes> rgIncidentTypesCommRecordGroup(final String domain);

	List<StaffMembers> rgRoleStaffIdsRecordGroup(String caseloadId, String agyLocId);

	boolean getStaffReportsMaintCount(String reportType);
	
	String getProfileCodeForStaffSearch();

	List<StaffMembers> rgRoleStaffIdsForAllAgyLocId(String agylocId);

}