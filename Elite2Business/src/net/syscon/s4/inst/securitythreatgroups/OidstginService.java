package net.syscon.s4.inst.securitythreatgroups;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VStgSet;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentPartiesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentsCommitBean;

/**
 * Interface OidstginService
 */
public interface OidstginService {
	Integer agencyIncidentPartiesCommit(AgencyIncidentPartiesCommitBean commitBean);

	List<AgencyIncidents> agyIncExecuteQuery(AgencyIncidents objAgency);

	List<AgencyIncidentParties> agencyIncidentPartiesExecuteQuery(AgencyIncidentParties objAgencyIP);

	Integer agyIncCommit(AgencyIncidentsCommitBean commitBean);

	List<VStgSet> populateStgGroup(VStgSet paramBean);

	List<StaffMembers> populateStaff(StaffMembers paramBean);

	String offenderIdDisplay(BigDecimal ofenderIdDisplay,String userId);

}
