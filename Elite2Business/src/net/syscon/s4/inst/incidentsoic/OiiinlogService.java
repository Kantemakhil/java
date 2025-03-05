package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VAgencyIncidents;

/**
 * Interface OiiinlogService
 */
public interface OiiinlogService {
	 List<ReferenceCodes> rgOccurTypeRecordGroup();

	 List<CaseloadAgencyLocations> rgAgyLocRecordGroup(String caseloadId, String caseloadType);

	 List<AgencyInternalLocations> rgLevelLocRecordGroup(String agyLocId);

	 List<StaffMembers> rgStaffRecordGroup(String caseloadId);

	 List<VAgencyIncidents> vAgyIncExecuteQuery(VAgencyIncidents objVAgencyIncidents);
	 
	 List<String> findIncidentTypeDescList();
	 
	 List<String> findIntLocationsList();
	 
	 List<String> agencyIncidentsCallModuleOidincde();
	 
	 List<String> agencyIncidentsCallModuleOiuincrp();
}
