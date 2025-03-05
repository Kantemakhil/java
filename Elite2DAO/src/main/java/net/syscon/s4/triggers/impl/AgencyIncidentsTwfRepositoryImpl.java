package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidents;
import net.syscon.s4.triggers.AgencyIncidentsTwfRepository;

@Repository
public class AgencyIncidentsTwfRepositoryImpl extends RepositoryBase implements AgencyIncidentsTwfRepository {
	Logger logger = LogManager.getLogger(AgencyIncidentsTwfRepositoryImpl.class);

	@Override
	public AgencyIncidents getAgencyIncidents(final Long agencyIncidentId) {
		final String sql = getQuery("AGENCY_INCIDENTS_TWF_AGENCY_INCIDENTS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("agencyIncidentId", agencyIncidentId),
					new BeanPropertyRowMapper<AgencyIncidents>(AgencyIncidents.class));
		} catch (final Exception e) {
			logger.error("getAgencyIncidents", e);
			return null;
		}
	}

	@Override
	public String stgPartiesCur(final Long offenderBookId) {
		final String sql = getQuery("AGENCY_INCIDENTS_TWF_STG_PARTIES_CUR");
		List<String> resultList = null;
		String result = null;
		try {
			resultList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId),
					new BeanPropertyRowMapper<String>(String.class));
			if(resultList != null && !resultList.isEmpty())
				result = resultList.get(0);
		} catch (final Exception e) {
			logger.error("stgPartiesCur", e);
			return result;
		}
		return result;
	}

	@Override
	public List<AgencyIncidentParties> agencyIncidentParties(final String actionCode, final Long agencyIncidentId) {
		final String sql = getQuery("AGENCY_INCIDENTS_TWF_INCIDENT_PARTIES_CUR");
		List<AgencyIncidentParties> agencyIncidentPartiesList = null;
		try {
			agencyIncidentPartiesList = namedParameterJdbcTemplate.query(sql,
					createParams("actionCode", actionCode, "agencyIncidentId", agencyIncidentId),
					new BeanPropertyRowMapper<AgencyIncidentParties>(AgencyIncidentParties.class));
		} catch (final Exception e) {
			logger.error("agencyIncidentParties", e);
			return agencyIncidentPartiesList;
		}
		return agencyIncidentPartiesList;
	}

}
