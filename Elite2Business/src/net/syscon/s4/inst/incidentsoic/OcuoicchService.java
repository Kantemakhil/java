package net.syscon.s4.inst.incidentsoic;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentChargesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;

/**
 * Interface OcuoicchService
 */
public interface OcuoicchService {
	List<OicOffences> rgOffenceCodeRecordGroup(Date incidentDate);

	OicOffences agyInciChgPostQuery(OicOffences paramBean);

	List<AgencyIncidentCharges> agyInciChgExecuteQuery(AgencyIncidentCharges objAgencyIncidentCharges);
			
	List<OicHearingResults> oichearingSearchResults(OicHearingResults objOicHearingResults);
	
	Integer agyInciChgCommit(AgencyIncidentChargesCommitBean commitBean);
}
