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
import net.syscon.s4.triggers.OffenderSentCondActs;
import net.syscon.s4.triggers.OffenderSentCondActsT2Repository;

@Repository
public class OffenderSentCondActsT2RepositoryImpl extends RepositoryBase implements OffenderSentCondActsT2Repository {
	Logger logger = LogManager.getLogger(OffenderSentCondActsT2RepositoryImpl.class.getName());

	@Override
	public OffenderSentCondActs getOffenderSentCondActs(final OffenderSentCondActs offenderSentCondActs) {
		final String sql = getQuery("OFFENDER_SENT_COND_ACTS_T2_OFFENDER_SENT_COND_ACTS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderSentCondActId", offenderSentCondActs.getOffenderSentCondActId()),
					new BeanPropertyRowMapper<OffenderSentCondActs>(OffenderSentCondActs.class));
		} catch (final Exception e) {
			logger.error("getOffenderSentCondActs", e);
			return null;
		}
	}

	@Override
	public Integer insert(final OffenderSentCondActs offenderSentCondActs) {
		final String sql = getQuery("OFFENDER_SENT_COND_ACTS_T2_OFFENDER_PRG_OBLIGATIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenderSentCondActs));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insert", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

}
