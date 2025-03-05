package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.careinplacement.beans.OffObsPrdCharacteristics;
import net.syscon.s4.inst.careinplacement.beans.OffObservationPeriods;
import net.syscon.s4.triggers.OffObsPrdT1Repository;

@Repository
public class OffObsPrdT1RepositoryImpl extends RepositoryBase implements OffObsPrdT1Repository {
	private final Logger logger = LogManager.getLogger(OffObsPrdT1RepositoryImpl.class);

	@Override
	public String authCheckCur() {
		final String sql = getQuery("OFF_OBS_PRD_T1_AUTH_CHECK_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (final Exception e) {
			logger.error("authCheckCur", e);
			return null;
		}
	}

	@Override
	public Long getFreqCur(final String observationType, final String characterisCode) {
		final String sql = getQuery("OFF_OBS_PRD_T1_GET_FREQ_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":observationType", observationType, ":characterisCode", characterisCode), Long.class);
		} catch (final Exception e) {
			logger.error("getFreqCur", e);
			return null;
		}
	}

	@Override
	public OffObservationPeriods getPeriodFreqCur(final Long offenderBookId, final Long obsPeriodId) {
		final String sql = getQuery("OFF_OBS_PRD_T1_GET_PERIOD_FREQ_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderBookId", offenderBookId, ":obsPeriodId", obsPeriodId),
					new BeanPropertyRowMapper<OffObservationPeriods>(OffObservationPeriods.class));
		} catch (final Exception e) {
			logger.error("getPeriodFreqCur", e);
			return null;
		}
	}

	@Override
	public OffObsPrdCharacteristics getOffObsPrdCharacteristics(final Long obsPrdCharId) {
		final String sql = getQuery("OFF_OBS_PRD_T1_OFF_OBS_PRD_CHARACTERISTICS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(":obsPrdCharId", obsPrdCharId),
					new BeanPropertyRowMapper<OffObsPrdCharacteristics>(OffObsPrdCharacteristics.class));
		} catch (final Exception e) {
			logger.error("getOffObsPrdCharacteristics", e);
			return null;
		}
	}

}
