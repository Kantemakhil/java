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
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.triggers.GlTransactionsJn;
import net.syscon.s4.triggers.GlTransactionsTjnRepository;

@Repository
public class GlTransactionsTjnRepositoryImpl extends RepositoryBase implements GlTransactionsTjnRepository {
	private final Logger logger = LogManager.getLogger(GlTransactionsTjnRepositoryImpl.class);

	@Override
	public GlTransactions getGlTransactions(final GlTransactions glTransactions) {
		final String sql = getQuery("GL_TRANSACTIONS_TJN_TRANSACTIONS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("txnId", glTransactions.getTxnId(), "txnEntrySeq", glTransactions.getTxnEntrySeq(),
							"glEntrySeq", glTransactions.getTxnEntrySeq()),
					new BeanPropertyRowMapper<GlTransactions>(GlTransactions.class));
		} catch (final Exception e) {
			logger.error("getGlTransactions", e);
			return null;
		}
	}

	@Override
	public Integer insertUpdateDelete(final List<GlTransactionsJn> glTransacJnList) {
		Integer returnValue = 0;
		final String sql = getQuery("GL_TRANSACTIONS_TJN_INSERT_UPDATE_DELETE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final GlTransactionsJn glTransactionsJn : glTransacJnList) {
			parameters.add(new BeanPropertySqlParameterSource(glTransactionsJn));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (glTransacJnList.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("insertUpdateDelete ", e);
		}
		return returnValue;

	}
}
