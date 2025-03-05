package net.syscon.s4.inst.incidentsoic;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OicOffences;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.im.incidentsoic.beans.OicHearingResults;

/**
 * Interface OcuoicchRepository
 */
public interface OcuoicchRepository {
	List<OicOffences> rgOffenceCodeRecordGroup(String mode, Date incidentdate);

	Integer agyInciChgDeleteAgencyIncidentCharges(List<AgencyIncidentCharges> lstAgencyIncidentCharges);

	Integer agyInciChgInsertAgencyIncidentCharges(List<AgencyIncidentCharges> lstAgencyIncidentCharges);

	List<AgencyIncidentCharges> agyInciChgExecuteQuery(AgencyIncidentCharges objAgencyIncidentCharges);

	OicOffences agyInciChgPostQuery(OicOffences paramBean);

	Integer agyInciChgUpdateAgencyIncidentCharges(List<AgencyIncidentCharges> lstAgencyIncidentCharges);

	Integer preInsertgetChargeSeqId(AgencyIncidentCharges bean);
	
	List<OicHearingResults> oichearingSearchResults(OicHearingResults objOicHearingResults);
	
}
