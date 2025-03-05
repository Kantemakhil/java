package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.GlTransactionsT1Repository;
import net.syscon.s4.triggers.TrustAudits;

@Repository
public class GlTransactionsT1RepositoryImpl extends RepositoryBase implements GlTransactionsT1Repository {
	private final Logger logger = LogManager.getLogger(GlTransactionsT1RepositoryImpl.class);

	@Override
	public String getVProfileValue() {
		final String sql = getQuery("GL_TRANSACTIONS_T1_V_PROFILE_VALUE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (final Exception e) {
			logger.error("getVProfileValue", e);
			return "N";
		}
	}

	@Override
	public String getModuleName(final Long vSessionid) {
		final String sql = getQuery("GL_TRANSACTIONS_T1_V_MODULE_NAME");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("vSessionid", vSessionid), String.class);
		} catch (final Exception e) {
			logger.error("getModuleName", e);
			return null;
		}
	}

	@Override
	public Integer savetrustAudits(final TrustAudits trustAudits) {
		Integer returnValue = 0;
		final String sql = getQuery("GL_TRANSACTIONS_T1_TRUST_AUDITS_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(trustAudits));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("savetrustAudits ", e);
		}
		return returnValue;

	}

}
