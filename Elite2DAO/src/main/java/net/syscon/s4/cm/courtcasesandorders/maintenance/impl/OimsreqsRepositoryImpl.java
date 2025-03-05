package net.syscon.s4.cm.courtcasesandorders.maintenance.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.courtcasesandorders.maintenance.OimsreqsRepository;
import net.syscon.s4.common.beans.LegalUpdateReasons;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.beans.SentenceCustodyStatus;
import net.syscon.s4.common.beans.SentenceTerms;
import net.syscon.s4.common.beans.SentenceUpdateReasons;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;

/**
 * Class OimsreqsRepositoryImpl
 */
@Repository
public class OimsreqsRepositoryImpl extends RepositoryBase implements OimsreqsRepository {
	private static Logger logger = LogManager.getLogger(OimsreqsRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> sentenceTermsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SENTENCE_CALC_TYPE", new FieldMapper("sentenceCalcType"))
			.put("SENTENCE_CATEGORY", new FieldMapper("sentenceCategory"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate")).put("FUNCTION_TYPE", new FieldMapper("functionType"))
			.put("HEADER_LABEL", new FieldMapper("headerLabel")).put("HEADER_SEQ", new FieldMapper("headerSeq"))
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("PROGRAM_METHOD", new FieldMapper("programMethod")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("SENTENCE_TYPE", new FieldMapper("sentenceType")).put("TERM_CODE", new FieldMapper("termCode"))
			.put("CHARGES_FLAG", new FieldMapper("chargesFlag")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("UPDATE_REASON_CODE", new FieldMapper("updateReasonCode"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("ACTIVE_TYPE", new FieldMapper("activeType"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("EFFECTIVE_DATE", new FieldMapper("effectiveDate")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("REASON_CATEGORY", new FieldMapper("reasonCategory")).build();
	private final Map<String, FieldMapper> sentenceUpdateReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("LEGAL_CLASS", new FieldMapper("legalClass"))
			.put("SENTENCE_CALC_TYPE", new FieldMapper("sentenceCalcType"))
			.put("SENTENCE_CATEGORY", new FieldMapper("sentenceCategory"))
			.put("UPDATE_REASON_CODE", new FieldMapper("updateReasonCode")).build();

	/**
	 * Creates new OimsreqsRepositoryImpl class Object
	 */
	public OimsreqsRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SentenceCalcTypes
	 *
	 * @return List<SentenceCalcTypes>
	 */

	public List<SentenceCalcTypes> senCalcExecuteQuery(final SentenceCalcTypes objSearchDao) {
		final String sql = getQuery("OIMSREQS_SENCALC_FIND_SENTENCE_CALC_TYPES");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			pSql.append(" WHERE ");
			if (objSearchDao.getSentenceCategory() != null && !objSearchDao.getSentenceCategory().isEmpty()) {
				pSql.append(" SENTENCE_CATEGORY LIKE :sentenceCategory  AND ");
				param.addValue("sentenceCategory", objSearchDao.getSentenceCategory());
			}
			if (objSearchDao.getSentenceCalcType() != null && !objSearchDao.getSentenceCalcType().isEmpty()
					&& !objSearchDao.getSentenceCalcType().trim().equals("")) {
				pSql.append(" SENTENCE_CALC_TYPE LIKE :sentenceCalcType AND ");
				param.addValue("sentenceCalcType", objSearchDao.getSentenceCalcType().trim());
			}
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().isEmpty()
					&& !objSearchDao.getDescription().trim().equals("")) {
				pSql.append(" DESCRIPTION LIKE :description AND ");
				param.addValue("description", objSearchDao.getDescription().trim());
			}
			if (objSearchDao.getSentenceType() != null && !objSearchDao.getSentenceType().isEmpty()) {
				pSql.append(" SENTENCE_TYPE LIKE :sentenceType AND ");
				param.addValue("sentenceType", objSearchDao.getSentenceType());
			}
			if (objSearchDao.getHeaderSeq() != null) {
				pSql.append(" HEADER_SEQ ::text LIKE (:headerSeq::text) AND ");
				param.addValue("headerSeq", objSearchDao.getHeaderSeq());
			}
			if (objSearchDao.getHeaderLabel() != null && !objSearchDao.getHeaderLabel().isEmpty()
					&& !objSearchDao.getHeaderLabel().trim().equals("")) {
				pSql.append(" HEADER_LABEL LIKE :headerLabel AND ");
				param.addValue("headerLabel", objSearchDao.getHeaderLabel().trim());
			}

			if (objSearchDao.getProgramMethod() != null && !objSearchDao.getProgramMethod().isEmpty()) {
				pSql.append(" PROGRAM_METHOD LIKE :programMethod AND ");
				param.addValue("programMethod", objSearchDao.getProgramMethod());
			}
			if (objSearchDao.getFunctionType() != null && !objSearchDao.getFunctionType().isEmpty()) {
				pSql.append(" FUNCTION_TYPE LIKE :functionType AND ");
				param.addValue("functionType", objSearchDao.getFunctionType());
			}
			if (objSearchDao.getListSeq() != null) {
				pSql.append(" LIST_SEQ ::text LIKE (:listSeq::text) AND ");
				param.addValue("listSeq", objSearchDao.getListSeq());
			}
			if (objSearchDao.getActiveFlag() != null && !objSearchDao.getActiveFlag().isEmpty()) {
				if (objSearchDao.getActiveFlag() == "true") {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				pSql.append(" ACTIVE_FLAG LIKE :activeFlag AND ");
				param.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				pSql.append(" EXPIRY_DATE = :expiryDate AND ");
				param.addValue("expiryDate", objSearchDao.getExpiryDate());
			}

		}
		preparedSql = pSql.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by SENTENCE_CALC_TYPE ");
		final RowMapper<SentenceCalcTypes> sentenceCalcTypesRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				SentenceCalcTypes.class, sentenceTermsMapping);
		List<SentenceCalcTypes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, param, sentenceCalcTypesRowMapper);
		} catch (Exception e) {
			logger.error("senCalcExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstSentenceCalcTypes
	 *            List<SentenceCalcTypes>
	 *
	 * @return List<Integer>
	 */
	public Integer senCalcInsertSentenceCalcTypes(final List<SentenceCalcTypes> lstSentenceCalcTypes) {
		final String sql = getQuery("OIMSREQS_SENCALC_INSERT_SENTENCE_CALC_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final SentenceCalcTypes list : lstSentenceCalcTypes) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}

		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSentenceCalcTypes.size() == returnArray.length) {
			return 1;
		} else {

			return 0;
		}
	}

	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OIMSREQS_CONSTRAINT_VALIDATIONS");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (Exception e) {
			returnData = null;
			return returnData;
		}
		return returnData;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstSentenceCalcTypes
	 *            List<SentenceCalcTypes>
	 */
	public Integer senCalcUpdateSentenceCalcTypes(final List<SentenceCalcTypes> lstSentenceCalcTypes) {
		final String sql = getQuery("OIMSREQS_SENCALC_UPDATE_SENTENCE_CALC_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SentenceCalcTypes sentenceCalcTypes : lstSentenceCalcTypes) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("senCalcUpdateSentenceCalcTypes: ", e);
		}
		if (lstSentenceCalcTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstSentenceCalcTypes
	 *            List<SentenceCalcTypes>
	 */
	public Integer senCalcDeleteSentenceCalcTypes(final List<SentenceCalcTypes> lstSentenceCalcTypes) {
		final String sql = getQuery("OIMSREQS_SENCALC_DELETE_SENTENCE_CALC_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SentenceCalcTypes sentenceCalcTypes : lstSentenceCalcTypes) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceCalcTypes));
		}
		try {
			String tableName = "SENTENCE_CALC_TYPES";
			String whereClause = "SENTENCE_CATEGORY=:sentenceCategory AND SENTENCE_CALC_TYPE=:sentenceCalcType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method senCalcDeleteSentenceCalcTypes", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("senCalcDeleteSentenceCalcTypes : ", e);
		}
		if (lstSentenceCalcTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SentenceTerms
	 *
	 * @return List<SentenceTerms>
	 */
	public List<SentenceTerms> senTermsExecuteQuery(final SentenceTerms objSearchDao) {
		final String sql = getQuery("OIMSREQS_SENTERMS_FIND_SENTENCE_TERMS");
		final RowMapper<SentenceTerms> sentRowMapper = Row2BeanRowMapper.makeMapping(sql, SentenceTerms.class,
				sentenceTermsMapping);
		List<SentenceTerms> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("sentenceCategory",
					objSearchDao.getSentenceCategory(), "sentenceCalcType", objSearchDao.getSentenceCalcType()),
					sentRowMapper);
		} catch (Exception e) {
			logger.error("senTermsExecuteQuery", e);
		}

		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstSentenceTerms
	 *            List<SentenceTerms>
	 *
	 * @return List<Integer>
	 */
	public Integer senTermsInsertSentenceTerms(final List<SentenceTerms> lstSentenceTerms) {
		final String sql = getQuery("OIMSREQS_SENTERMS_INSERT_SENTENCE_TERMS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SentenceTerms sentenceTerms : lstSentenceTerms) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstSentenceTerms.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstSentenceTerms
	 *            List<SentenceTerms>
	 */
	public Integer senTermsUpdateSentenceTerms(final List<SentenceTerms> lstSentenceTerms) {
		final String sql = getQuery("OIMSREQS_SENTERMS_UPDATE_SENTENCE_TERMS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SentenceTerms sentenceTerms : lstSentenceTerms) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("senTermsUpdateSentenceTerms : ", e);
		}
		if (lstSentenceTerms.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstSentenceTerms
	 *            List<SentenceTerms>
	 */
	public Integer senTermsDeleteSentenceTerms(final List<SentenceTerms> lstSentenceTerms) {
		final String sql = getQuery("OIMSREQS_SENTERMS_DELETE_SENTENCE_TERMS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SentenceTerms sentenceTerms : lstSentenceTerms) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			String tableName = "SENTENCE_TERMS";
			String whereClause = "SENTENCE_CATEGORY=:sentenceCategory AND SENTENCE_CALC_TYPE=:sentenceCalcType AND TERM_CODE=:termCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method senTermsDeleteSentenceTerms", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("senTermsDeleteSentenceTerms : ", e);
		}
		if (lstSentenceTerms.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SentenceUpdateReasons
	 *
	 * @return List<SentenceUpdateReasons>
	 */
	public List<SentenceUpdateReasons> senUpdExecuteQuery(final SentenceUpdateReasons objSearchDao) {
		final String sql = getQuery("OIMSREQS_SENUPD_FIND_SENTENCE_UPDATE_REASONS");
		final RowMapper<SentenceUpdateReasons> sentUpdRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SentenceUpdateReasons.class, sentenceUpdateReasonsMapping);
		List<SentenceUpdateReasons> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("sentenceCategory",
					objSearchDao.getSentenceCategory(), "sentenceCalcType", objSearchDao.getSentenceCalcType()),
					sentUpdRowMapper);
			for (final SentenceUpdateReasons sentUpdReasons : returnList) {
				getResDescValues(sentUpdReasons);
			}
			for (final SentenceUpdateReasons sentUpdReasons : returnList) {
				getNbtStatus(sentUpdReasons);
			}
		} catch (Exception e) {
			logger.error("senUpdExecuteQuery", e);
		}
		return returnList;
	}

	public SentenceUpdateReasons getNbtStatus(final SentenceUpdateReasons sentUpdReasons) {
		final String sql = getQuery("OIMSREQS_GETNBTSTATUS_UPDATE_REASON");
		try {
			String nbtDescStatus = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("NBTSTATUS", sentUpdReasons.getpType()), String.class);
			sentUpdReasons.setNbtStatusDesc(nbtDescStatus);
		} catch (Exception e) {
			logger.error("setNbtStatus", e);
		}
		return sentUpdReasons;
	}

	public SentenceUpdateReasons getResDescValues(final SentenceUpdateReasons objSearchDao) {
		final String sql = getQuery("GET_REASON_DESC");
		final RowMapper<SentenceUpdateReasons> SentenceUpdateReasonsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SentenceUpdateReasons.class, sentenceTermsMapping);
		List<SentenceUpdateReasons> returnList = new ArrayList<>(); 
	try {
		returnList = namedParameterJdbcTemplate.query(sql, createParams("P_CODE",objSearchDao.getUpdateReasonCode()), SentenceUpdateReasonsRowMapper);
		objSearchDao.setpDesc(returnList.get(0).getDescription());
		objSearchDao.setpType(returnList.get(0).getActiveType());
	} catch (Exception e) {
		logger.error("getResDescValues", e);
	}
	return objSearchDao;
}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstSentenceUpdateReasons
	 *            List<SentenceUpdateReasons>
	 *
	 * @return List<Integer>
	 */
	public Integer senUpdInsertSentenceUpdateReasons(final List<SentenceUpdateReasons> lstSentenceUpdateReasons) {
		final String sql = getQuery("OIMSREQS_SENUPD_INSERT_SENTENCE_UPDATE_REASONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SentenceUpdateReasons list : lstSentenceUpdateReasons) {
			list.setLegalClass("SENTENCE");
			parameters.add(new BeanPropertySqlParameterSource(list));
		}

		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstSentenceUpdateReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstSentenceUpdateReasons
	 *            List<SentenceUpdateReasons>
	 */
	public Integer senUpdUpdateSentenceUpdateReasons(final List<SentenceUpdateReasons> lstSentenceUpdateReasons) {
		final String sql = getQuery("OIMSREQS_SENUPD_UPDATE_SENTENCE_UPDATE_REASONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final SentenceUpdateReasons sentenceUpdateReasons : lstSentenceUpdateReasons) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceUpdateReasons));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("senUpdUpdateSentenceUpdateReasons : ", e);
		}
		if (lstSentenceUpdateReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstSentenceUpdateReasons
	 *            List<SentenceUpdateReasons>
	 */
	public Integer senUpdDeleteSentenceUpdateReasons(final List<SentenceUpdateReasons> lstSentenceUpdateReasons) {
		final String sql = getQuery("OIMSREQS_SENUPD_DELETE_SENTENCE_UPDATE_REASONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final SentenceUpdateReasons sentenceUpdateReasons : lstSentenceUpdateReasons) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceUpdateReasons));
		}
		try {
			String tableName = "SENTENCE_UPDATE_REASONS";
			String whereClause = "SENTENCE_CATEGORY=:sentenceCategory AND SENTENCE_CALC_TYPE=:sentenceCalcType AND UPDATE_REASON_CODE=:updateReasonCode";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method senUpdDeleteSentenceUpdateReasons", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("senUpdDeleteSentenceUpdateReasons : ", e);
		}
		if (lstSentenceUpdateReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgCatRecordGroup() {
		final String sql = getQuery("OIMSREQS_FIND_RGCAT");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgCatRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSentRecordGroup() {
		final String sql = getQuery("OIMSREQS_FIND_RGSENT");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgSentRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSvcOblRecordGroup() {
		final String sql = getQuery("OIMSREQS_FIND_RGSVCOBL");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgSvcOblRecordGroup : ", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgTermCodeRecordGroup() {
		final String sql = getQuery("OIMSREQS_FIND_RGTERMCODE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LegalUpdateReasons>
	 */
	public List<LegalUpdateReasons> rgReasonRecordGroup() {
		final String sql = getQuery("OIMSREQS_FIND_RGREASON");
		final RowMapper<LegalUpdateReasons> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, LegalUpdateReasons.class,
				mMapping);
		List<LegalUpdateReasons> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mMRowMapper);
		} catch (Exception e) {
			logger.error("rgReasonRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgFunctionTypeRecordGroup() {
		final String sql = getQuery("OIMSREQS_FIND_RGFUNCTIONTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * senCalcKeyDelrec
	 *
	 * @param params
	 */
	public Integer senCalcKeyDelrec(final SentenceCalcTypes paramBean) {
		final String sql = getQuery("OIMSREQS_SEN_CALC_KEYDELREC");
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("SENTENCECATEGORY",
				paramBean.getSentenceCategory(), "SENTENCECALCTYPE", paramBean.getSentenceCalcType()), Integer.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * senCalcOnCheckDeleteMaster
	 *
	 * @param params
	 */
	public List<SentenceTerms> senCalcOnCheckDeleteMaster(final SentenceTerms paramBean) {
		final String sql = getQuery("OIMSREQS_SEN_CALC_ONCHECKDELETEMASTER");
		final RowMapper<SentenceTerms> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SentenceTerms.class,
				sentenceTermsMapping);
		List<SentenceTerms> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("senCalcOnCheckDeleteMaster", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * senCalcOnCheckDeleteMaster
	 *
	 * @param params
	 */
	public List<SentenceUpdateReasons> senCalcOnCheckDeleteMaster(final SentenceUpdateReasons paramBean) {
		final String sql = getQuery("OIMSREQS_SEN_CALC_ONCHECKDELETEMASTER");
		final RowMapper<SentenceUpdateReasons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SentenceUpdateReasons.class, sentenceUpdateReasonsMapping);
		List<SentenceUpdateReasons> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("senCalcOnCheckDeleteMaster", e);
		}
		return returnList;
	}
	
	
	public List<ReferenceCodes> getOrderStatus() {
		try {
			return namedParameterJdbcTemplate.query(getQuery("OIMSREQS_FETCH_ORDERS_STATUS"), createParams(), new RowMapperResultSetExtractor<ReferenceCodes>(new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class)));
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
	
	public List<ReferenceCodes> getCustodyStatus() {
		try {
			return namedParameterJdbcTemplate.query(getQuery("OIMSREQS_FETCH_CUSTODY_STATUS"), createParams(), new RowMapperResultSetExtractor<ReferenceCodes>(new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class)));
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
	
	public Integer saveCustodyStatus(final List<SentenceCustodyStatus> lstSentenceUpdateReasons) {
		final String sql = getQuery("OIMSREQS_SAVE_CUSTODY_STATUS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final SentenceCustodyStatus sentenceUpdateReasons : lstSentenceUpdateReasons) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceUpdateReasons));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("saveCustodyStatus : ", e);
			if(e.getMessage()!=null && e.getMessage().contains("sentence_custody_status_pk")) {
				return 18;
			}
		}
		if (lstSentenceUpdateReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	
	public Integer updateCustodyStatus(final List<SentenceCustodyStatus> lstSentenceUpdateReasons) {
		final String sql = getQuery("OIMSREQS_UPDATE_CUSTODY_STATUS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final SentenceCustodyStatus sentenceUpdateReasons : lstSentenceUpdateReasons) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceUpdateReasons));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			if(e!=null && e.getMessage().contains("sentence_custody_status_pk")) {
				return 18;
			}
			logger.error("saveCustodyStatus : ", e);
		}
		if (lstSentenceUpdateReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	
	public Integer deleteCustodyStatus(final List<SentenceCustodyStatus> lstSentenceUpdateReasons) {
		final String sql = getQuery("OIMSREQS_DELETE_CUSTODY_STATUS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final SentenceCustodyStatus sentenceUpdateReasons : lstSentenceUpdateReasons) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceUpdateReasons));
		}
		try {
			String tableName = "sentence_custody_status";
			String whereClause = "sentence_calc_type =:sentenceCalcType and sentence_category =:sentenceCategory and "
					+ "sentence_order_status =:sentenceOrderStatus and custody_status =:custodyStatus and legal_class =:legalClass";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteCustodyStatus", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("saveCustodyStatus : ", e);
		}
		if (lstSentenceUpdateReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
	
	
	public List<SentenceCustodyStatus> fetchCustodyStatus(SentenceCustodyStatus status) {
		try {
			return namedParameterJdbcTemplate.query(getQuery("OIMSREQS_FETCH_ALL_CUSTODY_VALUES"), createParams("sentenceCalcType",status.getSentenceCalcType(),"sentenceCategory",status.getSentenceCategory()), new RowMapperResultSetExtractor<SentenceCustodyStatus>(new BeanPropertyRowMapper<SentenceCustodyStatus>(SentenceCustodyStatus.class)));
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
}
