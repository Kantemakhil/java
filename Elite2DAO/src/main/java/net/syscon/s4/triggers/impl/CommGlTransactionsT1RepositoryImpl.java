package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.CommGlTransactions;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.CommGlTransactionsT1Repository;
import net.syscon.s4.triggers.CommissaryAudits;

@Repository
public class CommGlTransactionsT1RepositoryImpl extends RepositoryBase implements CommGlTransactionsT1Repository {
	private final Logger logger = LogManager.getLogger(CommGlTransactionsT1RepositoryImpl.class);

	@Override
	public CommGlTransactions getCommGlTransactions(final CommGlTransactions commGlTransactions) {
		final String sql = getQuery("COMM_GL_TRANSACTIONS_T1_COMM_GL_TRANSACTIONS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":txnId", commGlTransactions.getTxnId(), "txnEntrySeq",
							commGlTransactions.getTxnEntrySeq(), "glEntrySeq", commGlTransactions.getGlEntrySeq()),
					new BeanPropertyRowMapper<CommGlTransactions>(CommGlTransactions.class));
		} catch (final Exception e) {
			logger.error("getCommGlTransactions", e);
			return null;
		}
	}

	@Override
	public String vProfileValue() {
		final String sql = getQuery("COMM_GL_TRANSACTIONS_T1_V_PROFILE_VALUE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (final Exception e) {
			logger.error("vProfileValue", e);
			return "N";
		}
	}

	@Override
	public String vModuleName(final Long sid) {
		final String sql = getQuery("COMM_GL_TRANSACTIONS_T1_V_MODULE_NAME");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (final Exception e) {
			logger.error("vProfileValue", e);
			return null;
		}
	}

	@Override
	public Integer insertCommissaryAudits(final CommissaryAudits commissaryAudits) {
		Integer returnValue = 0;
		final String sql = getQuery("COMM_GL_TRANSACTIONS_T1_COMMISSARY_AUDITS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(commissaryAudits));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("insertCommissaryAudits ", e);
		}
		return returnValue;

	}

}
