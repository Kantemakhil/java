package net.syscon.s4.triggers;

import java.util.List;

import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;

public interface AgencyIncidentsTwfRepository {
	AgencyIncidents getAgencyIncidents(Long agencyIncidentId);

	List<AgencyIncidentParties> agencyIncidentParties(String actionCode, Long agencyIncidentId);

	String stgPartiesCur(Long offenderBookId);

}
