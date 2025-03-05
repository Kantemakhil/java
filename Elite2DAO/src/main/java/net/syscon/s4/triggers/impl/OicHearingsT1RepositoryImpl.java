package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.triggers.OicHearingsT1Repository;

@Repository
public class OicHearingsT1RepositoryImpl extends RepositoryBase implements OicHearingsT1Repository {
	private final Logger logger = LogManager.getLogger(OicHearingsT1RepositoryImpl.class.getName());

	@Override
	public OicHearings getOicHearings(final OicHearings oicHearings) {
		final String sql = getQuery("OIC_HEARINGS_T1_OIC_HEARINGS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":oicHearingId", oicHearings.getOicHearingId()),
					new BeanPropertyRowMapper<OicHearings>(OicHearings.class));
		} catch (final Exception e) {
			logger.error("getOicHearings", e);
			return null;
		}
	}

	@Override
	public Integer lEventId() {
		final String sql = getQuery("OIC_HEARINGS_T1_L_EVENT_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (final Exception e) {
			logger.error("lEventId", e);
			return null;
		}
	}
}
