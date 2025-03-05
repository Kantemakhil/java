package net.syscon.s4.inst.classification.assessmentmaintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.classification.assessmentmaintenance.OimslevlRepository;
import net.syscon.s4.inst.classification.beans.AssessmentResults;
import net.syscon.s4.inst.classification.beans.AssessmentSupervisions;
import net.syscon.s4.inst.classification.beans.Assessments;

/**
 * Class OimslevlRepositoryImpl
 */
@Repository
public class OimslevlRepositoryImpl extends RepositoryBase implements OimslevlRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimslevlRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> assesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_ID", new FieldMapper("assessmentId")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> superVsnMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_ID", new FieldMapper("assessmentId")).build();
	private final Map<String, FieldMapper> mMMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_ID", new FieldMapper("assessmentId"))
			.put("SUPERVISION_LEVEL_TYPE", new FieldMapper("supervisionLevelType"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("UPDATE_ALLOWED_FLAG", new FieldMapper("updateAllowedFlag"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("CELL_SHARING_ALERT_FLAG", new FieldMapper("cellSharingAlertFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	/**
	 * Creates new OimslevlRepositoryImpl class Object
	 */
	public OimslevlRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Assessments
	 *
	 * @return List<Assessments>
	 *
	 * @throws SQLException
	 */
	public List<Assessments> assTypeExecuteQuery(final Assessments objSearchDao) {
		final String sql = getQuery("OIMSLEVL_ASSTYPE_FIND_ASSESSMENTS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().isEmpty()) {
				sqlQuery.append(" ASSESSMENT_CODE  = :description " + "AND ");
				params.addValue("description", objSearchDao.getDescription());
			}
			if (objSearchDao.getEffectiveDate() != null) {
				sqlQuery.append(" EFFECTIVE_DATE  = :effectiveDate " + " AND ");
				params.addValue("effectiveDate", objSearchDao.getEffectiveDate());
			}
			if (objSearchDao.getActiveFlag() != null && !objSearchDao.getActiveFlag().isEmpty()) {
				if ("true".equals(objSearchDao.getActiveFlag())) {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				sqlQuery.append(" ACTIVE_FLAG  = :activeFlag " + " AND ");
				params.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			sqlQuery.append(" ASSESSMENTS.ASSESSMENT_CLASS = 'TYPE' AND "
					+ "(CASELOAD_TYPE IN (SELECT CASELOAD_TYPE FROM CASELOADS WHERE CASELOAD_ID = "
					+ "(SELECT WORKING_CASELOAD_ID FROM STAFF_MEMBERS WHERE USER_ID = :USERID ) ) OR CASELOAD_TYPE='BOTH') ");
			params.addValue("USERID", objSearchDao.getCreateUserId());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		preSqlQuery = preparedSql.concat(" ORDER BY LIST_SEQ, ACTIVE_FLAG DESC, ASSESSMENT_CODE ");
		final RowMapper<Assessments> AssessmentsRowMapper = Row2BeanRowMapper.makeMapping(preSqlQuery,
				Assessments.class, assesMapping);
		List<Assessments> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, AssessmentsRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Assessments
	 *
	 * @return List<Assessments>
	 *
	 * @throws SQLException
	 */
	@Override
	public List<Assessments> assTypeAssSectExecuteQuery(final Assessments searchRecord) {
		final String sql = getQuery("OIMSLEVL_ASSTYPE_ASSSECT_FIND_ASSESSMENTS");
		final RowMapper<Assessments> assRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class, assesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("ASSESSMENT_ID", searchRecord.getAssessmentId()),
				assRowMapper);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            AssessmentSupervisions
	 *
	 * @return List<AssessmentSupervisions>
	 *
	 * @throws SQLException
	 */
	public List<AssessmentSupervisions> typeAssSupExecuteQuery(final AssessmentSupervisions objSearchDao) {
		final String sql = getQuery("OIMSLEVL_TYPEASSSUP_FIND_ASSESSMENT_SUPERVISIONS");
		final RowMapper<AssessmentSupervisions> superRowMaper = Row2BeanRowMapper.makeMapping(sql,
				AssessmentSupervisions.class, superVsnMapping);
		List<AssessmentSupervisions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("ASSESSMENTID", objSearchDao.getAssessmentId()),
				superRowMaper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstAssessmentSupervisions
	 *            List<AssessmentSupervisions>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer typeAssSupInsertAssessmentSupervisions(final List<AssessmentSupervisions> list) {
		final String sql = getQuery("OIMSLEVL_TYPEASSSUP_INSERT_ASSESSMENT_SUPERVISIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final AssessmentSupervisions supervsn : list) {
			parameters.add(new BeanPropertySqlParameterSource(supervsn));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstAssessmentSupervisions
	 *            List<AssessmentSupervisions>
	 *
	 * @throws SQLException
	 */
	public Integer typeAssSupUpdateAssessmentSupervisions(final List<AssessmentSupervisions> list) {
		final String sql = getQuery("OIMSLEVL_TYPEASSSUP_UPDATE_ASSESSMENT_SUPERVISIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final AssessmentSupervisions supervsn : list) {
			parameters.add(new BeanPropertySqlParameterSource(supervsn));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstAssessmentSupervisions
	 *            List<AssessmentSupervisions>
	 *
	 * @throws SQLException
	 */
	public Integer typeAssSupDeleteAssessmentSupervisions(final List<AssessmentSupervisions> list) {
		final String sql = getQuery("OIMSLEVL_TYPEASSSUP_DELETE_ASSESSMENT_SUPERVISIONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final AssessmentSupervisions supervsn : list) {
			parameters.add(new BeanPropertySqlParameterSource(supervsn));
		}
		try {
			String tableName = "ASSESSMENT_SUPERVISIONS";
			String whereClause = "ASSESSMENT_ID =   :assessmentId and SUPERVISION_LEVEL_TYPE =  :supervisionLevelType";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method typeAssSupDeleteAssessmentSupervisions", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Assessments>
	 */
	public List<Assessments> rgAssessmentSectionsRecordGroup() {
		final String sql = getQuery("OIMSLEVL_FIND_RGASSESSMENTSECTIONS");
		final RowMapper<Assessments> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in rgAssessmentSectionsRecordGroup: oimslevl", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Assessments>
	 */
	public List<Assessments> rgAssessmentTypesRecordGroup(String userId) {
		final String sql = getQuery("OIMSLEVL_FIND_RGASSESSMENTTYPES");
		final RowMapper<Assessments> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("userId", userId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in rgAssessmentTypesRecordGroup: oimslevl", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * assSectOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<AssessmentSupervisions> assSectOnCheckDeleteMaster(final AssessmentSupervisions paramBean) {
		final String sql = getQuery("OIMSLEVL_ASS_SECT_ONCHECKDELETEMASTER");
		final RowMapper<AssessmentSupervisions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AssessmentSupervisions.class, superVsnMapping);
		return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param objSearchDao
	 *            Assessments
	 *
	 * @return List<Assessments>
	 */

	@Override
	public List<Assessments> assSectExecuteQuery(final Assessments searchRecord) {
		final String sql = getQuery("OIMSLEVL_ASSTYPE_ASSSECT_FIND_ASSESSMENTS");
		final RowMapper<Assessments> rowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class, assesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("ASSESSMENT_ID", searchRecord.getAssessmentId()),
				rowMapper);

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            Assessments
	 *
	 * @return List<AssessmentSupervisions>
	 */

	@Override
	public List<AssessmentSupervisions> secAssSupExecuteQuery(final AssessmentSupervisions searchRecord) {
		final String sql = getQuery("OIMSLEVL_TYPEASSSUP_ASSSECT_FIND_ASSESSMENT_SUPERVISIONS");
		final RowMapper<AssessmentSupervisions> rowMapper = Row2BeanRowMapper.makeMapping(sql,
				AssessmentSupervisions.class, assesMapping);
		List<AssessmentSupervisions> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("ASSESSMENTID", searchRecord.getAssessmentId()),
				rowMapper);
		return returnList;
	}

	/**
	 * 
	 * checking validation condition on deletion of record
	 * 
	 */
	public Integer validateDelRow(final List<Long> assessmentId) {
		final String sql = getQuery("OIMSLEVL_WHEN_VALIDATE_ITEAM_DEL");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("ASSESSMENTID", assessmentId),
				Integer.class);
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AssessmentResults>
	 */

	@Override
	public List<AssessmentResults> rgAssessmentResultsRecordGroup(final Long assessmentId) {
		final String sql = getQuery("OIMSLEVL_FIND_RGASSESSMENTRESULTS");
		final RowMapper<AssessmentResults> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, AssessmentResults.class,
				mMMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("ASSESSMENT_ID", assessmentId), mMRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in rgAssessmentResultsRecordGroup: oimslevl", e);
			return Collections.emptyList();
		}
	}

	@Override
	public String checkLovData(final Long assessmentId) {
		final String sql = getQuery("OIMSLEVL_CHECK_LOV_DATA");
		String returnList;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("ASSESSMENTID", assessmentId),
					String.class);
		} catch (Exception e) {
			returnList = "N";
		}
		return returnList;
	}

	@Override
	public Integer updateEnforceFlag(Long assesmentId, String enforcementFlag,String userName) {
		final String sql = getQuery("OIMSLEVL_UPDATE_ENFORCE_MIN_MAX_FLAG_IN_ASSESSMENT");
		Integer returnData=0;
		try {
			returnData=namedParameterJdbcTemplate.update(sql, createParams("enforcementFlag",enforcementFlag,"assessmentId",assesmentId,"modifyUserId",userName));
		}catch (Exception e) {
			logger.error("error in updateEnforceFlag",e);
		}
		return returnData;	
	}

	@Override
	public String getEnforcementFalg(Long assessmentId) {
		final String sql = getQuery("OIMSLEVL_SELECT_ENFORCE_MIN_MAX_FLAG_FROM_ASSESSMENT");
		String returnList=null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("assessmentId", assessmentId),
					String.class);
		} catch (Exception e) {
			returnList = "N";
		}
		return returnList;
		
	}

	@Override
	public Integer updateSectionsDetails(List<Assessments> assList) {
		final String sql = getQuery("OIMSLEVL_UPDATE_SECTIONS_DETAILS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Assessments assessBean : assList) {
			parameters.add(new BeanPropertySqlParameterSource(assessBean));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updateSectionsDetails in error ");
		}
		if (assList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
}