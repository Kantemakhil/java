package net.syscon.s4.cf.offendertransactions.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.offendertransactions.OcuotrahRepository;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.GlTransactions;

/**
 * Class OcuotrahRepositoryImpl
 */
@Repository
public class OcuotrahRepositoryImpl extends RepositoryBase implements OcuotrahRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuotrahRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offenderTransactionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASELOAD_ID",									     new FieldMapper("caseloadId"))
			.put("OFFENDER_ID",									     new FieldMapper("offenderId"))
			.put("CREATE_DATETIME",									 new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME",									 new FieldMapper("modifyDatetime"))
			.build();

	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_TYPE",                          new FieldMapper("profileType"))
			.put("PROFILE_CODE",                          new FieldMapper("profileCode"))
			.build();
	
	private final Map<String, FieldMapper> GlTransactionssMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACCOUNT_CODE",                          new FieldMapper("accountCode"))
			.build();

	
	/**
	 * Creates new OcuotrahRepositoryImpl class Object
	 */
	public OcuotrahRepositoryImpl() {
//		OcuotrahRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTransactions
	 *
	 * @return List<OffenderTransactions>
	 *
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OCUOTRAH_OFFTXN_FIND_OFFENDER_TRANSACTIONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			if (objSearchDao.getOffenderId() != null) {
				sqlQuery.append(" OFFENDER_ID = :offender_id and");
				params.addValue("offender_id", objSearchDao.getOffenderId());
			}
			if (objSearchDao.getCaseloadId() != null && !objSearchDao.getCaseloadId().trim().equals("")) {
				sqlQuery.append(" CASELOAD_ID = :caseload_id and");
				params.addValue("caseload_id", objSearchDao.getCaseloadId());
			}
			if (objSearchDao.getTxnEntryDate() != null) {
				sqlQuery.append(" TXN_ENTRY_DATE = :txnEntryDate and");
				params.addValue("txnEntryDate", objSearchDao.getTxnEntryDate());
			}
			if (objSearchDao.getTxnType() != null && !objSearchDao.getTxnType().trim().equals("")) {
				sqlQuery.append(" TXN_TYPE = :txnType");
				params.addValue("txnType", objSearchDao.getTxnType());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY TXN_ID DESC, TXN_ENTRY_SEQ DESC ");
		final RowMapper<OffenderTransactions> OffenderTransactionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTransactions.class, offenderTransactionsMapping);
		List<OffenderTransactions> returnList = new ArrayList<OffenderTransactions>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, params ,OffenderTransactionsRowMapper);
		} catch (Exception e) {
			logger.error("offTxnExecuteQuery", e);

		}
		return returnList;
	}	

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OCUOTRAH_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		List<SystemProfiles> returnList = new ArrayList<SystemProfiles>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), SystemProfilesRowMapper);
		} catch (final Exception e) {
			logger.error("sysPflExecuteQuery", e);
		}

		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oimoffenPreInsert
	 *
	 * @param params
	 *
	 */
	public List<GlTransactions> ocuotrahPostquery(final GlTransactions paramBean) {
		final String sql = getQuery("OCUOTRAH_OFFTXN_FIND_OFFENDER_TRANSACTIONS_TXN_ENTRY_TIME");
		final RowMapper<GlTransactions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, GlTransactions.class,
				GlTransactionssMapping);
		List<GlTransactions> returnList = new ArrayList<GlTransactions>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {

			logger.error("ocuotrahPostquery", e);
		}
		return returnList;
	}
//	@Override
	public String offenderTransactionsTxnEntryTime(final String transactionTime) {
		final String sql = getQuery("OCUOTRAH_OFFTXN_FIND_OFFENDER_TRANSACTIONS_TXN_ENTRY_TIME");
		String returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXN_ID", transactionTime),
					String.class);
		} catch (Exception e) {
			return returnVal;
		}
		return returnVal;
	}
}
