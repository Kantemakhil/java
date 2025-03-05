package net.syscon.s4.inst.incidentsoic;
import java.util.List;

import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentRepairs;

/**
 * Interface OiuincrpRepository
 */
public interface OiuincrpRepository {
	 List<AgencyIncidentRepairs> agyIncExecuteQuery(AgencyIncidentRepairs objSearchDao);


}
