package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.AgencyIncidentPartiesT1Repository;

@Repository
public class AgencyIncidentPartiesT1RepositoryImpl extends RepositoryBase implements AgencyIncidentPartiesT1Repository {

	@Override
	public Integer agencyIncidentParties(final Long offenderBookId, final Long agencyIncidentId) {
		final String sql = getQuery("AGENCY_INCIDENT_PARTIES_T1_AGENCY_INCIDENT_PARTIES");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", offenderBookId, "agencyIncidentId", agencyIncidentId), Integer.class);
	}

}
