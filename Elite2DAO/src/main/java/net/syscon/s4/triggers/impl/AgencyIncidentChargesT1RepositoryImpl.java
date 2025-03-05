package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentCharges;
import net.syscon.s4.triggers.AgencyIncidentChargesT1Repository;

@Repository
public class AgencyIncidentChargesT1RepositoryImpl extends RepositoryBase implements AgencyIncidentChargesT1Repository {
	private final Logger logger = LogManager.getLogger(AgencyIncidentChargesT1RepositoryImpl.class);

	@Override
	public Integer lidsCur(final AgencyIncidentCharges agencyIncidentCharges) {
		final String sql = getQuery("AGENCY_INCIDENT_CHARGES_T1_LIDS_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("agencyIncidentId",
					agencyIncidentCharges.getAgencyIncidentId(), "partySeq", agencyIncidentCharges.getPartySeq()),
					Integer.class);
		} catch (final Exception e) {
			logger.error("lidsCur", e);
			return null;
			
		}
	}

}
