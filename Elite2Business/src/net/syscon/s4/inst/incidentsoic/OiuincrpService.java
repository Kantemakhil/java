package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairs;

/**
 * Interface OiuincrpService
 */
public interface OiuincrpService {
	
	 List<AgencyIncidentRepairs> agyIncExecuteQuery(AgencyIncidentRepairs searchRecord);
}
