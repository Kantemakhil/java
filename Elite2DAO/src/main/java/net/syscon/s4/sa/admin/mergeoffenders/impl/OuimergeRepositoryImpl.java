package net.syscon.s4.sa.admin.mergeoffenders.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.sa.admin.beans.MergeTransactions;
import net.syscon.s4.sa.admin.mergeoffenders.OuimergeRepository;

/**
 * Class OuimergeRepositoryImpl
 */
@Repository
public class OuimergeRepositoryImpl extends RepositoryBase implements OuimergeRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OuimergeRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.build();

	private final Map<String, FieldMapper> mTransMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("TRNBOOKINGNO", new FieldMapper("trnBookingNo"))
			.put("STAFFNAME", new FieldMapper("staffName"))
			.build();

	/**
	 * Creates new OuimergeRepositoryImpl class Object
	 */
	public OuimergeRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            MergeTransactions
	 *
	 * @return List<MergeTransactions>
	 *
	 * @throws SQLException
	 */
	public List<MergeTransactions> transactionsExecuteQuery(final MergeTransactions objSearchDao) {
		final String sql = getQuery("OUIMERGE_TRANSACTIONS_FIND_MERGE_TRANSACTIONS");
		final RowMapper<MergeTransactions> mTransRowMpr = Row2BeanRowMapper.makeMapping(sql,
				MergeTransactions.class, mTransMapping);

		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getRequestDate() != null && objSearchDao.getToDate() != null) {
				sqlQuery.append(" date_trunc('D',REQUEST_DATE) between :REQUEST_DATE AND :TO_DATE " + " AND ");
				inParameterSource.addValue("REQUEST_DATE", objSearchDao.getRequestDate());
				inParameterSource.addValue("TO_DATE", objSearchDao.getToDate());
			}
			if (objSearchDao.getRequestStatusCode() != null && !objSearchDao.getRequestStatusCode().isEmpty()) {
				sqlQuery.append(" REQUEST_STATUS_CODE = :REQUEST_STATUS_CODE" + " AND ");
				inParameterSource.addValue("REQUEST_STATUS_CODE", objSearchDao.getRequestStatusCode());
			}
			if (objSearchDao.getTransactionSource() != null && !objSearchDao.getTransactionSource().isEmpty()) {
				sqlQuery.append(" TRANSACTION_SOURCE = :TRANSACTION_SOURCE");
				inParameterSource.addValue("TRANSACTION_SOURCE", objSearchDao.getTransactionSource());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY REQUEST_DATE DESC";

		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, mTransRowMpr);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgStatusRecordGroup() {
		final String sql = getQuery("OUIMERGE_FIND_RGSTATUS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in rgStatusRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgSourceRecordGroup() {
		final String sql = getQuery("OUIMERGE_FIND_RGSOURCE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in rgSourceRecordGroup:", e);
			return Collections.emptyList();
		}
	}

}
