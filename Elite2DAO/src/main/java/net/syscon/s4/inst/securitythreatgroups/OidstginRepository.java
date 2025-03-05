package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VStgGangSet;
import net.syscon.s4.common.beans.VStgGroup;
import net.syscon.s4.common.beans.VStgSet;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;

/**
 * Interface OidstginRepository
 */
public interface OidstginRepository {
	List<VStgGangSet> populateStgGroup(VStgGangSet paramBean);

	Offenders agencyIncidentPartiesPostQuery(Integer offBooId);

	List<ReferenceCodes> agencyIncidentPartiesPostQuery(ReferenceCodes paramBean);

	SecurityThreatGroups agencyIncidentPartiesPostQueryDescription(Integer stgId);

	List<AgencyInternalLocations> populateIncidentLocations(AgencyInternalLocations paramBean);

	List<StaffMembers> populateStaff(StaffMembers paramBean);

	List<AgencyIncidents> agyIncExecuteQuery(AgencyIncidents objAgnyIncds);

	List<AgencyIncidentParties> agencyIncidentPartiesExecuteQuery(AgencyIncidentParties paramBean);

	List<AgencyIncidentParties> agencyIncidentPartiesExecuteQuerySTGIDS(Integer offenderBookid);

	List<SystemProfiles> populateStgGroup(SystemProfiles paramBean);

	List<OffenderStgAffiliations> agencyIncidentPartiesPostQuery(OffenderStgAffiliations paramBean);

	List<SystemProfiles> populateLogNo(SystemProfiles paramBean);

	List<VStgGroup> populateStgGroup(VStgGroup paramBean);

	List<VStgSet> populateStgGroup(VStgSet paramBean);

	String tagEstablishMent(String agyLocId);

	String staffMembers(Integer reportedStaffId);

	String getDescription(BigDecimal stgId);

	AgencyInternalLocations getInternalLocations(Integer intLocId);

	BigDecimal getDescriptionStgId(BigDecimal stgId, Integer offBookId);

	String offenderIdDisplay(BigDecimal ofenderIdDisplay,String userId);

	BigDecimal getDescriptionStgIdElseCondition(Integer offenderBookId);

}
