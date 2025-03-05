package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.careinplacement.beans.OffObservationPeriods;
import net.syscon.s4.triggers.OffObservationPeriodsT1Repository;

@Repository
public class OffObservationPeriodsT1RepositoryImpl extends RepositoryBase implements OffObservationPeriodsT1Repository {
	private static final Logger logger = LogManager.getLogger(OffObservationPeriodsT1RepositoryImpl.class);

	@Override
	public OffObservationPeriods getOffObservationPeriods(final Long offenderBookId, final Long obsPeriodId) {
		final String sql = getQuery("OFF_OBSERVATION_PERIODS_T1_OFF_OBSERVATION_PERIODS_OLD");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderBookId", offenderBookId, ":obsPeriodId", obsPeriodId),
					new BeanPropertyRowMapper<OffObservationPeriods>(OffObservationPeriods.class));
		} catch (final Exception e) {
			logger.error("getOffObservationPeriods", e);
			return null;
		}
	}

	@Override
	public String authCheckCur() {
		final String sql = getQuery("OFF_OBSERVATION_PERIODS_T1_AUTHCHECKCUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (final Exception e) {
			logger.error("authCheckCur", e);
			return null;
		}
	}

}
