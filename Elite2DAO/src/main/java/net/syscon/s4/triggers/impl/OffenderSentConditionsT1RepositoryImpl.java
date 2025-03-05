package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderSentConditions;
import net.syscon.s4.triggers.OffenderSentConditionsT1Repository;

@Repository
public class OffenderSentConditionsT1RepositoryImpl extends RepositoryBase
		implements OffenderSentConditionsT1Repository {
	Logger logger = LogManager.getLogger(OffenderSentConditionsT1RepositoryImpl.class.getName());

	@Override
	public OffenderSentConditions getOffenderSentConditions(final OffenderSentConditions newObj) {
		final String sql = getQuery("OFFENDER_SENT_CONDITIONS_T1_OFFENDER_SENT_CONDITIONS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderSentConditionId", newObj.getOffenderSentConditionId()),
					new BeanPropertyRowMapper<OffenderSentConditions>(OffenderSentConditions.class));
		} catch (final Exception e) {
			logger.error("getOffenderSentConditions", e);
			return null;
		}
	}

	@Override
	public Integer insert(final OffenderSentConditions newObj) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_SENT_COND_STATUSES_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(newObj));
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
