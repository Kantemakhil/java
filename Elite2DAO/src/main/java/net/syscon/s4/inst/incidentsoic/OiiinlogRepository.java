package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VAgencyIncidents;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;

/**
 * Interface OiiinlogRepository
 */
public interface OiiinlogRepository {
	 List<ReferenceCodes> rgOccurTypeRecordGroup();

	 List<CaseloadAgencyLocations> rgAgyLocRecordGroup(String caseloadId);

	 List<AgencyInternalLocations> rgLevelLocRecordGroup(AgencyInternalLocations searchBean);

	 List<StaffMembers> rgStaffRecordGroup(String caseloadId);

	 List<VAgencyIncidents> vAgyIncExecuteQuery(VAgencyIncidents objVAgencyIncidents);
	 
	 List<String> findIncidentTypeDescList();
	 
	 List<String> findIntLocationsList();
	 
    List<AgencyIncidents> agencyIncidentsExecuteQuery(AgencyIncidents objSearchDao);
    
    List<String> agencyIncidentsCallModuleOidincde();
    
    List<String> agencyIncidentsCallModuleOiuincrp();
    
    List<AgencyInternalLocations> rgLevelInternalLocationIdsRecordGroup(final String agencyLocId);
}
