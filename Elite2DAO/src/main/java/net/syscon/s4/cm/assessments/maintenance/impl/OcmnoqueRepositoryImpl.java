package net.syscon.s4.cm.assessments.maintenance.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.assessments.maintenance.OcmnoqueRepository;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;
import oracle.jdbc.OracleTypes;

/**
 * Class OcmnoqueRepositoryImpl
 */
@Repository
public class OcmnoqueRepositoryImpl extends RepositoryBase implements OcmnoqueRepository {

	private static Logger logger = LogManager.getLogger(OcmnoqueRepositoryImpl.class);

	/**
	 * Creates new OcmnoqueRepositoryImpl class Object
	 */
	public OcmnoqueRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private final Map<String, FieldMapper> assessmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("BOOKMARK_NAME", new FieldMapper("ansBookMark"))
			.put("ASSESSMENT_ID", new FieldMapper("assessmentId")).build();
	
	private final Map<String, FieldMapper> assessmentResultsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_ID", new FieldMapper("assessmentId")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_CODE", new FieldMapper("assessmentCode")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).put("ASSESSMENT_ID", new FieldMapper("assessmentId"))
			.put("ASSESSMENT_TYPE", new FieldMapper("assessmentType")).build();
	private final Map<String, FieldMapper> parentMaqpping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TABLE_NAME", new FieldMapper("tableName")).put("COLUMN_NAME", new FieldMapper("columnName")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao Assessments
	 *
	 * @return List<Assessments>
	 */
	public List<Assessments> assessExecuteQuery(final Assessments objSearchDao) {
		final String sql = getQuery("OCMNOQUE_ASSESS_FIND_ASSESSMENTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			if (objSearchDao.getAssessmentCode() != null && !objSearchDao.getAssessmentCode().trim().equals("")) {
				sqlQuery.append(" AND assessment_code=:assessmentCode");
				params.addValue("assessmentCode", objSearchDao.getAssessmentCode());
			}
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().trim().equals("")) {
				sqlQuery.append(" AND description=:description");
				params.addValue("description", objSearchDao.getDescription());
			}
			if (objSearchDao.getEffectiveDate() != null) {
				sqlQuery.append(" AND :effectiveDate  = TO_CHAR(effective_date ,'MM/DD/YYYY')");
				params.addValue("effectiveDate",
						new SimpleDateFormat("MM/dd/yyyy").format(objSearchDao.getEffectiveDate()));
			}
			if (objSearchDao.getCaseloadType() != null) {
				sqlQuery.append(" AND caseload_type=:caseloadType");
				params.addValue("caseloadType", objSearchDao.getCaseloadType());
			}
			if (objSearchDao.getListSeq() != null) {
				sqlQuery.append(" AND LIST_SEQ=:listSeq");
				params.addValue("listSeq", objSearchDao.getListSeq());
			}
			if (objSearchDao.getCellSharingAlertFlag() != null) {
				if ("true".equals(objSearchDao.getCellSharingAlertFlag())) {
					objSearchDao.setCellSharingAlertFlag("Y");
				} else {
					objSearchDao.setCellSharingAlertFlag("N");
				}
				sqlQuery.append(" AND CELL_SHARING_ALERT_FLAG =:cellSharingAlertFlag");
				params.addValue("cellSharingAlertFlag", objSearchDao.getCellSharingAlertFlag());
			}
			if (objSearchDao.getActiveFlag() != null) {
				if ("true".equals(objSearchDao.getActiveFlag())) {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				sqlQuery.append(" AND active_flag =:activeFlag");
				params.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				sqlQuery.append(" AND :expiryDate  = TO_CHAR(EXPIRY_DATE ,'MM/DD/YYYY')");
				params.addValue("expiryDate", new SimpleDateFormat("MM/dd/yyyy").format(objSearchDao.getExpiryDate()));
			}
			sqlQuery.append(" ORDER BY LIST_SEQ ASC, ASSESSMENT_CODE ASC");
		}
		preparedSql = sqlQuery.toString().trim();
		final RowMapper<Assessments> assessmentsRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		List<Assessments> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, params, assessmentsRowMapper);
		} catch (final Exception e) {
			logger.error("assessExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAssessments List<Assessments>
	 *
	 * @return List<Integer>
	 */
	public Integer assessInsertAssessments(final List<Assessments> lstAssessments) {
		final String sql = getQuery("OCMNOQUE_ASSESS_INSERT_ASSESSMENTS");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final Assessments assessments : lstAssessments) {
			parameters.add(new BeanPropertySqlParameterSource(assessments));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error(this.getClass()+" assessInsertAssessments", e);
		}
		if (lstAssessments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAssessments List<Assessments>
	 */
	public Integer assessUpdateAssessments(final List<Assessments> lstAssessments) {
		final String sql = getQuery("OCMNOQUE_ASSESS_UPDATE_ASSESSMENTS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final Assessments assessments : lstAssessments) {
			parameters.add(new BeanPropertySqlParameterSource(assessments));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAssessments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAssessments List<Assessments>
	 */
	public Integer assessDeleteAssessments(final List<Assessments> lstAssessments) {
		final String sql = getQuery("OCMNOQUE_ASSESS_DELETE_ASSESSMENTS");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final Assessments assessments : lstAssessments) {
			parameters.add(new BeanPropertySqlParameterSource(assessments));
		}
		try {
			String tableName = "ASSESSMENTS";
			String whereClause = "assessment_id =:assessmentId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method assessDeleteAssessments", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("violates foreign key constraint")) {
				return 5;
			}
		}
		if (lstAssessments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao Assessments
	 *
	 * @return List<Assessments>
	 */
	public List<Assessments> assSectExecuteQuery(final Assessments objSearchDao) {
		final String sql = getQuery("OCMNOQUE_ASSSECT_FIND_ASSESSMENTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null && objSearchDao.getAssessmentId() != null) {
			sqlQuery.append("  AND PARENT_ASSESSMENT_ID=:parentAssessmentId");
			params.addValue("parentAssessmentId", objSearchDao.getAssessmentId());
		}
		sqlQuery.append(" ORDER BY LIST_SEQ ASC, ASSESSMENT_CODE ASC");
		preparedSql = sqlQuery.toString().trim();
		final RowMapper<Assessments> assessmentsRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		List<Assessments> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, params, assessmentsRowMapper);
		} catch (final Exception e) {
			logger.error("assSectExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao AssessmentResults
	 *
	 * @return List<AssessmentResults>
	 */
	public List<AssessmentResults> assResExecuteQuery(final AssessmentResults objSearchDao) {
		final String sql = getQuery("OCMNOQUE_ASSRES_FIND_ASSESSMENT_RESULTS");
		final MapSqlParameterSource params = new MapSqlParameterSource();
		List<AssessmentResults> returnList = new ArrayList<>();
		if (objSearchDao != null) {
			params.addValue("ASSESSMENTID", objSearchDao.getAssessmentId());
		}
		final RowMapper<AssessmentResults> assessmentResultsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AssessmentResults.class, assessmentResultsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, params, assessmentResultsRowMapper);
		} catch (final Exception e) {
			logger.error("assSectExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAssessmentResults List<AssessmentResults>
	 *
	 * @return List<Integer>
	 */
	public Integer assResInsertAssessmentResults(final List<AssessmentResults> lstAssessmentResults) {
		final String sql = getQuery("OCMNOQUE_ASSRES_INSERT_ASSESSMENT_RESULTS");
		int[] returnArray = null;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final AssessmentResults assessmentResults : lstAssessmentResults) {
			parameters.add(new BeanPropertySqlParameterSource(assessmentResults));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			if (e.getMessage().contains("duplicate")) {
				return 2;
			} else {
				return 0;
			}
		}
		if (lstAssessmentResults.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAssessmentResults List<AssessmentResults>
	 */
	public Integer assResUpdateAssessmentResults(final List<AssessmentResults> lstAssessmentResults) {
		final String sql = getQuery("OCMNOQUE_ASSRES_UPDATE_ASSESSMENT_RESULTS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final AssessmentResults assessmentResults : lstAssessmentResults) {
			parameters.add(new BeanPropertySqlParameterSource(assessmentResults));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAssessmentResults.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAssessmentResults List<AssessmentResults>
	 */
	public Integer assResDeleteAssessmentResults(final List<AssessmentResults> lstAssessmentResults) {
		final String sql = getQuery("OCMNOQUE_ASSRES_DELETE_ASSESSMENT_RESULTS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final AssessmentResults assessmentResults : lstAssessmentResults) {
			parameters.add(new BeanPropertySqlParameterSource(assessmentResults));
		}
		try {
			String tableName = "ASSESSMENT_RESULTS";
			String whereClause = "ASSESSMENT_ID =:assessmentId and SUPERVISION_LEVEL_TYPE =:supervisionLevelType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method assResDeleteAssessmentResults", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstAssessmentResults.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgCaseloadTypeRecordGroup() {
		final String sql = getQuery("OCMNOQUE_FIND_RGCASELOADTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		final List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			logger.error("rgCaseloadTypeRecordGroup", e);
		}
		return returnList;
	}

	@Override
	public List<Assessments> assQueExecuteQuery(final Assessments searchRecord) {
		final String sql = getQuery("OCMNOQUE_FIND_RGQUESTIONSASSESSMENTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (searchRecord != null && searchRecord.getAssessmentId() != null) {
			params.addValue("ASSESSMENTID", searchRecord.getAssessmentId());
			params.addValue("ASSESSMENT_CODE", searchRecord.getAssessmentCode());
		}
		sqlQuery.append(" ORDER BY LIST_SEQ ASC , ASSESSMENT_CODE ASC");
		preparedSql = sqlQuery.toString().trim();
		final RowMapper<Assessments> assessmentsRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		List<Assessments> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, params, assessmentsRowMapper);
		} catch (final Exception e) {
			logger.error("assSectExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public List<Assessments> assAnsExecuteQuery(final Assessments searchRecord) {
		final String sql = getQuery("OCMNOQUE_ASSANS_FIND_ASSESSMENTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (searchRecord != null && searchRecord.getAssessmentId() != null) {
			params.addValue("ASSESSMENT_ID", searchRecord.getAssessmentId());
		}
		preparedSql = sqlQuery.toString().trim();
		final RowMapper<Assessments> assessmentsRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		List<Assessments> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, params, assessmentsRowMapper);
		} catch (final Exception e) {
			logger.error("assSectExecuteQuery", e);
		}
		return returnList;
	}

	public List<Assessments> assSectLov(final Long assessmentId) {
		final String sql = getQuery("OCMNOQUE_FIND_ASSSECTLOV");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class,
				assessmentsMapping);
		List<Assessments> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("assessmentId", assessmentId),
					columnRowMapper);
		} catch (final Exception e) {
			logger.error("assSectExecuteQuery", e);
		}
		return returnList;
	}

	public List<Assessments> assQueLov(final Long assessmentId) {
		final String sql = getQuery("OCMNOQUE_FIND_ASSQUELOV");
		final RowMapper<Assessments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class, mMapping);
		List<Assessments> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("ASSESSMENTID", assessmentId),
					columnRowMapper);
		} catch (final Exception e) {
			logger.error("assSectExecuteQuery", e);
		}
		return returnList;
	}

	@Override
	public Long getAssessmentId() {
		Long openAnAccount = 0L;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters;
		sqlParameters = new SqlParameter[] { new SqlOutParameter("P_OPEN_AN_ACCOUNT", OracleTypes.NUMBER) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_ASSESSMENT").withFunctionName("GET_ASSESSMENT_ID")
				.declareParameters(sqlParameters);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			openAnAccount = Long.valueOf(String.valueOf(returnObject.get("P_OPEN_AN_ACCOUNT")));
		} catch (final Exception e) {
			logger.error("checkAccountSatus" + e);
		}
		return openAnAccount;
	}

	@Override
	public Integer validateCaseLoad(final Long assessmentId) {
		Integer count = 0;
		final String sql = getQuery("OCMNOQUE_VALIDATE_CASELOAD");
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("ASSESSMENTID", assessmentId),
					Integer.class);
		} catch (final Exception e) {
			logger.error("assSectExecuteQuery", e);
		}
		return count;
	}

	@Override
	public Integer getAssRelCount(final Long assessmentId) {
		Integer assRelCount = 0;
		final String sql = getQuery("OCMNOQUE_GET_ASSRELCOUNT");
		try {
			assRelCount = namedParameterJdbcTemplate.queryForObject(sql, createParams("ASSESSMENTID", assessmentId),
					Integer.class);
		} catch (final Exception e) {
			logger.error("assSectExecuteQuery", e);
		}
		return assRelCount;
	}

	@Override
	public Integer getAssResRelCount(final Long assessmentId) {
		Integer assResRelCount = 0;
		final String sql = getQuery("OCMNOQUE_GET_ASSRESRELCOUNT");
		try {
			assResRelCount = namedParameterJdbcTemplate.queryForObject(sql, createParams("ASSESSMENTID", assessmentId),
					Integer.class);
		} catch (final Exception e) {
			logger.error("assSectExecuteQuery", e);
		}
		return assResRelCount;
	}

	@Override
	public List<TableColumnNameBean> assessKeyDelList() {
		final String sql = getQuery("OCMNOQUE_ASSESSKEYDELLIST");
		final RowMapper<TableColumnNameBean> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TableColumnNameBean.class,
				parentMaqpping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Integer getAssessKeyDeleteRecRowCount(String tableName, String columnName, Long assessmentId) {
		final String sql = "SELECT COUNT(*) FROM  " + tableName + " WHERE " + columnName
				+ "=:updateReasonCode  limit 1 ";
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("updateReasonCode", assessmentId),
				Integer.class);
	}

	@Override
	public Integer getAssessmentSupCount(final AssessmentResults searchBean) {
		Integer rowCount = 0;
		final String sql = getQuery("OCMNOQUE_GETASSESSMENTSUPCOUNT");
		final MapSqlParameterSource params = new MapSqlParameterSource();
		if (searchBean != null) {
			params.addValue("ASSESSMENTID", searchBean.getAssessmentId());
			params.addValue("SUPERVISIONLEVELTYPE", searchBean.getSupervisionLevelType());
		}
		try {
			rowCount = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
		} catch (final Exception e) {
			logger.error("assSectExecuteQuery", e);
		}
		return rowCount;
	}

	@Override
	public Integer getOffenderAssCount(final AssessmentResults searchBean) {
		Integer rowCount = 0;
		final String sql = getQuery("OCMNOQUE_GETOFFENDERASSCOUNT");
		final MapSqlParameterSource params = new MapSqlParameterSource();
		if (searchBean != null) {
			params.addValue("ASSESSMENTID", searchBean.getAssessmentId());
			params.addValue("SUPERVISIONLEVELTYPE", searchBean.getSupervisionLevelType());
		}
		try {
			rowCount = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
		} catch (final Exception e) {
			logger.error("assSectExecuteQuery", e);
		}
		return rowCount;
	}

	@Override
	public String getDefaultAssessmentType() {
		final String sql = getQuery("OCMNOQUE_GETDEFAULTASSESSMENTTYPE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	
	@Override
	public List<ReferenceCodes> rgBookMarkRecordGroup() {
		final String sql = getQuery("OCMNOQUE_RG_BOOKMARK_RECORDGROUP");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		final List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" rgBookMarkRecordGroup", e);
		}
		return returnList;
	}
}
