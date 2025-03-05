package net.syscon.s4.inst.incidentsoic;

import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentChargesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentPartiesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairs;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.IncidentFollowUpDetails;
import net.syscon.s4.im.incidentsoic.beans.IncidentFollowUpDetailsCommitBean;
import net.syscon.s4.im.incidentsoic.beans.SignificantIncident;
import net.syscon.s4.im.incidentsoic.beans.SignificantIncidentCommitBean;

/**
 * Interface OidincdeService
 */
public interface OidincdeService {
	List<ReferenceCodes> rgRepairTypesRecordGroup(String domain);

	List<CaseloadAgencyLocations> rgAgyLocIdsRecordGroup(String caseloadId, String caseLoadType);

	List<OicOffences> rgOicOffenceCodesRecordGroup(String startDate, String endDate);

	List<ReferenceCodes> rgIncidentTypesRecordGroup(String domain, String caseLoadType);

	List<AgencyInternalLocations> rgLevelInternalLocationIdsRecordGroup(String agencyLocId);

	List<ReferenceCodes> rgOffInvActionCodesRecordGroup(String domain);

	List<ReferenceCodes> rgOffInvIncidentRolesRecordGroup(String domain);

	List<StaffMembers> rgReportedStaffIdsRecordGroup(String caseloadId);

	List<ReferenceCodes> rgStaffInvIncidentRolesRecordGroup(String domain);

	Integer agencyIncidentRepairsInsertAgencyIncidentRepairs(List<AgencyIncidentRepairs> lstAgencyIncident);

	List<AgencyIncidents> agencyIncidentsExecuteQuery(AgencyIncidents searchBean);

	List<Object> agencyIncidentsOnCheckDeleteMasteragyIncPartiesOffenderCur(String lstagencyincident);

	List<AgencyIncidentParties> agyIncPartiesOffenderExecuteQuery(AgencyIncidentParties searchRecord);

	Integer agencyIncidentChargesUpdateAgencyIncidentCharges(List<AgencyIncidentCharges> lstAgencyIncident);

	Integer agencyIncidentRepairsDeleteAgencyIncidentRepairs(List<AgencyIncidentRepairs> lstAgencyIncident);

	Integer agencyIncidentChargesDeleteAgencyIncidentCharges(List<AgencyIncidentCharges> lstAgencyIncident);

	Integer agencyIncidentRepairsUpdateAgencyIncidentRepairs(List<AgencyIncidentRepairs> lstAgencyIncident);

	Integer agencyIncidentChargesInsertAgencyIncidentCharges(List<AgencyIncidentCharges> lstAgencyIncident);

	Integer agyIncPartiesOffenderDeleteAgencyIncidentParties(List<AgencyIncidentParties> lstAgencyIncident);

	Integer agyIncPartiesOffenderUpdateAgencyIncidentParties(List<AgencyIncidentParties> lstAgencyIncident);

	List<AgencyIncidentRepairs> agencyIncidentRepairsExecuteQuery(AgencyIncidentRepairs searchRecord);

	List<AgencyIncidentCharges> agencyIncidentChargesExecuteQuery(AgencyIncidentCharges searchRecord);

	Integer agyIncPartiesOffenderInsertAgencyIncidentParties(List<AgencyIncidentParties> lstAgencyIncident);

	Integer agencyIncidentPartiesCommit(AgencyIncidentPartiesCommitBean commitBean);

	List<Object> agencyIncidentsOnCheckDeleteMasteragyIncPartiesStaffCur(String lstagencyincident);

	List<Object> agencyIncidentsOnCheckDeleteMasteragencyIncidentRepairsCur(String lstagencyincident);

	List<Object> agyIncPartiesOffenderOnCheckDeleteMasteragencyIncidentChargesCur(String lstagencyincident,
			String lstpartyseq);

	List<AgencyIncidentParties> agyIncPartiesOffenderPreInsert(String offenderbookId, String agencyIncidentId);

	List<OicOffences> agencyIncidentChargesPostQuery(String systemMode);

	List<StaffMembers> setGlobalCaseloadIdworkingCaseloadCur(String systemMode);

	Object oidincdeStateadmUsrCsr(String systemMode, String userName);

	List<CaseloadAgencyLocations> oidincdeState(String caseloadId);

	List<AgencyIncidents> setUpdatedIncidentDetailsupdIncDetCur(String lstagencyincident);

	List<SystemProfiles> checkUnlockAccessspProfileCur(String systemMode);

	List<StaffMemberRoles> checkUnlockAccesscheckUnlockAccess(String pValue);

	List<OmsRoles> checkUnlockAccesscheckRoleAccess(String pValue, String pvalue);

	Integer agencyIncidentRepairsCommit(AgencyIncidentRepairsCommitBean commitBean);

	Integer agyIncPartiesOffenderCommit(AgencyIncidentPartiesCommitBean commitBean);

	Integer agencyIncidentChargesCommit(AgencyIncidentChargesCommitBean commitBean);

	Integer agyIncPartiesStaffCommit(AgencyIncidentPartiesCommitBean commitBean);

	List<StaffMembers> staffidExecuteQuery(String userId);

	Integer agyIncStaffCommit(AgencyIncidentPartiesCommitBean commitBean);

	List<AgencyIncidentParties> agyIncPartiesstaffExecuteQuery(AgencyIncidentParties searchBean);

	Integer agencyIncidentsCommit(AgencyIncidentsCommitBean agencyIncidentsBean);

	List<SignificantIncident> sigificantIncidentExecuteQuery(SignificantIncident commitBean);

	int sigificantIncidentCommmit(SignificantIncidentCommitBean commitBean);

	Boolean getEnhancedStaffReporter(Integer staffId);

	List<IncidentFollowUpDetails> incidentFollowUpcommit(IncidentFollowUpDetailsCommitBean commitBean);

	List<IncidentFollowUpDetails> getIncidentFollowUpDetails(IncidentFollowUpDetails searchBean);

	Map<String, Boolean> checkPermisionForTabAccess(String userName);
	
	List<StaffMembers> rgRoleStaffIdsRecordGroup(String caseloadId, String agyLocId);


}