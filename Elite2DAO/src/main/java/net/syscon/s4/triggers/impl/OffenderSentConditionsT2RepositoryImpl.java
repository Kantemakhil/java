package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.triggers.OffenderSentConditionsT2Repository;

@Repository
public class OffenderSentConditionsT2RepositoryImpl extends RepositoryBase
		implements OffenderSentConditionsT2Repository {
	Logger logger = LogManager.getLogger(OffenderSentConditionsT2RepositoryImpl.class);

	@Override
	public OffenderSentConditions getOffenderSentConditions(final Long offenderSentConditionId) {
		final String sql = getQuery("OFFENDER_SENT_CONDITIONS_T2_OFFENDER_SENT_CONDITIONS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderSentConditionId", offenderSentConditionId),
					new BeanPropertyRowMapper<OffenderSentConditions>(OffenderSentConditions.class));
		} catch (final Exception e) {
			logger.error("getOffenderSentConditions", e);
			return null;
		}
	}

	@Override
	public String prgMethodCur(final String commConditionType, final String commConditionCode, final String categoryType) {
		final String sql = getQuery("OFFENDER_SENT_CONDITIONS_T2_PRG_METHOD_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("commConditionType", commConditionType,"commConditionCode", commConditionCode,"categoryType", categoryType),String.class);
		} catch (final Exception e) {
			logger.error("prgMethodCur", e);
			return null;
		}
	}

	@Override
	public String oblCur(final BigDecimal offenderBookId, final Long offenderSentConditionId) {
		final String sql = getQuery("OFFENDER_SENT_CONDITIONS_T2_OBL_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId,"offenderSentConditionId", offenderSentConditionId),String.class);
		} catch (final Exception e) {
			logger.error("oblCur", e);
			return null;
		}
	}

	@Override
	public Long lvCntCommunityConditions(final String commConditionType, final String commConditionCode) {
		final String sql = getQuery("COMMUNITY_CONDITIONS_LV_CNT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("commConditionType", commConditionType,"commConditionCode", commConditionCode),Long.class);
		} catch (final Exception e) {
			logger.error("lvCntCommunityConditions", e);
			return null;
		}
	}

	@Override
	public Long lvCntOffePrgOblig(final Long offenderSentConditionId, final Long offenderBookId) {
		final String sql = getQuery("OFFENDER_PRG_OBLIGATIONS_LV_CNT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderSentCondition_id", offenderSentConditionId,"offenderBookId", offenderBookId),Long.class);
		} catch (final Exception e) {
			logger.error("lvCntOffePrgOblig", e);
			return null;
		}
	}

	@Override
	public Integer update(final OffenderPrgObligations offePrgOblig, final String oblCur) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_PRG_OBLIGATIONS_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offePrgOblig));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("update ", e);
		}
		return returnValue;

	}

	@Override
	public Integer insert(final OffenderPrgObligations offePrgOblig) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_PRG_OBLIGATIONS_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offePrgOblig));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("insert ", e);
		}
		return returnValue;

	}

}
