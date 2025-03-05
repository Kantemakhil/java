package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.beans.SentenceAdjustment;
import net.syscon.s4.inst.legalscreens.maintenance.OimsatypRepository;

/**
 * Class OimsatypRepositoryImpl
 */
@Repository
public class OimsatypRepositoryImpl extends RepositoryBase implements OimsatypRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimsatypRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> SentenceAdjustmentMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("DOMAIN", new FieldMapper("domain"))
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("DEBIT_CREDIT_CODE", new FieldMapper("debitCreditCode"))
			.put("USAGE_CODE", new FieldMapper("usageCode"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();

	/**
	 * Creates new OimsatypRepositoryImpl class Object
	 */
	public OimsatypRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SentenceAdjustment
	 *
	 * @return List<SentenceAdjustment>
	 *
	 * @throws SQLException
	 */
	public List<SentenceAdjustment> sentenceAdjustmentExecuteQuery(final SentenceAdjustment objSearchDao) {
		final String sql = getQuery("OIMSATYP_SENTENCEADJUSTMENTS_FIND_SENTENCE_ADJUSTMENTS");
		final RowMapper<SentenceAdjustment> senAdjMapper = Row2BeanRowMapper.makeMapping(sql, SentenceAdjustment.class,
				SentenceAdjustmentMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getSentenceAdjustCode() != null && !objSearchDao.getSentenceAdjustCode().isEmpty() && !objSearchDao.getSentenceAdjustCode().trim().equals("")) {
				sqlQuery.append(" SENTENCE_ADJUST_CODE = :SENTENCE_ADJUST_CODE" + " AND ");
				inParameterSource.addValue("SENTENCE_ADJUST_CODE", objSearchDao.getSentenceAdjustCode().trim());
			}
			if (objSearchDao.getUsageCode() != null && !objSearchDao.getUsageCode().isEmpty()) {
				sqlQuery.append(" USAGE_CODE = :USAGE_CODE" + " AND ");
				inParameterSource.addValue("USAGE_CODE", objSearchDao.getUsageCode());
			}
			if (objSearchDao.getActiveFlag() != null) {
				if ("true".equals(objSearchDao.getActiveFlag())) {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				sqlQuery.append(" ACTIVE_FLAG = :ACTIVE_FLAG" + " AND ");
				inParameterSource.addValue("ACTIVE_FLAG", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				sqlQuery.append(" EXPIRY_DATE = :EXPIRY_DATE" + " AND ");
				inParameterSource.addValue("EXPIRY_DATE", objSearchDao.getExpiryDate());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, senAdjMapper);

	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstSentenceAdjustment
	 *            List<SentenceAdjustment>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public String sentenceAdjustmentInsertQuery(final List<SentenceAdjustment> listObj) {
		final String sql = getQuery("OIMSATYP_SENTENCEADJUSTMENTS_INSERT_SENTENCE_ADJUSTMENTS");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final SentenceAdjustment bean : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception in sentenceAdjustmentInsertQuery", e);
			String excp = e.getMessage();
			return excp.toUpperCase();
		}
		if (listObj.size() == returnArray.length) {
			return "1";
		} else {
			return null;
		}

	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstSentenceAdjustment
	 *            List<SentenceAdjustment>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer sentenceAdjustmentUpdateQuery(final List<SentenceAdjustment> listObj) {
		final String sql = getQuery("OIMSATYP_SENTENCEADJUSTMENTS_UPDATE_SENTENCE_ADJUSTMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final SentenceAdjustment bean : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstSentenceAdjustment
	 *            List<SentenceAdjustment>
	 *
	 * @throws SQLException
	 */
	public Integer sentenceAdjustmentDeleteQuery(final List<SentenceAdjustment> listObj) {
		final String sql = getQuery("OIMSATYP_SENTENCEADJUSTMENTS_DELETE_SENTENCE_ADJUSTMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final SentenceAdjustment bean : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
			batchUpdatePreDeletedRows("SENTENCE_ADJUSTMENTS", "SENTENCE_ADJUST_CODE = :sentenceAdjustCode", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in sentenceAdjustmentDeleteQuery"+e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("in " + this.getClass().getName() + " in sentenceAdjustmentDeleteQuery ", e);
			if(e!=null && e.getMessage().contains("offender_legal_adjustments_fk2")) {
				return 18;	
			}
		}

		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkSentenceAdjustmentDspRecordGroup() {
		final String sql = getQuery("OIMSATYP_FIND_CGFKSENTENCEADJUSTMENTSDSP");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkSentenceAdjustmentUsagRecordGroup() {
		final String sql = getQuery("OIMSATYP_FIND_CGFKSENTENCEADJUSTMENTSUSAG");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("Exception :", e);
			return Collections.emptyList();
		}
	}

}
