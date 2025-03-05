package net.syscon.s4.inst.workflow.managingteams.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.workflow.managingteams.OcdvteamRepository;
import net.syscon.s4.inst.workflow.managingteams.beans.OffenderTeamAssignments;
import oracle.jdbc.internal.OracleTypes;

/**
 * Class OcdvteamRepositoryImpl
 */
@Repository
public class OcdvteamRepositoryImpl extends RepositoryBase implements OcdvteamRepository {
	private static Logger logger = LogManager.getLogger(OcdvteamRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> offnderTeamMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("TEAM_ID", new FieldMapper("teamId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("FUNCTION_TYPE", new FieldMapper("functionType"))
			.put("ASSIGNMENT_DATE", new FieldMapper("assignmentDate"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE" , new FieldMapper("code"))
			.put("LIST_SEQ" , new FieldMapper("listSeq"))
			.build();

	/**
	 * Creates new OcdvteamRepositoryImpl class Object
	 */
	public OcdvteamRepositoryImpl() {
		//constructor
	}

	/**
	 * Logger object used to print the log in the file
	 */
	

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTeamAssignments
	 *
	 * @return List<OffenderTeamAssignments>
	 *
	 * @throws SQLException
	 */
	public List<OffenderTeamAssignments> offTeamAssignExecuteQuery(final OffenderTeamAssignments objSearchDao) {
		final String sql = getQuery("OCDVTEAM_OFFTEAMASSIGN_FIND_OFFENDER_TEAM_ASSIGNMENTS");
		final RowMapper<OffenderTeamAssignments> offenderRowMaper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTeamAssignments.class, offnderTeamMaping);
		return (ArrayList<OffenderTeamAssignments>) namedParameterJdbcTemplate
				.query(sql, createParams("offenderBookId", objSearchDao.getOffenderBookId()),
						offenderRowMaper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderTeamAssignments
	 *            List<OffenderTeamAssignments>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public OffenderTeamAssignments offTeamAssignInsertOffenderTeamAssignments(
			final List<OffenderTeamAssignments> lstOffenderTeamAssignments) {

		final String sql = getQuery("OCDVTEAM_OFFTEAMASSIGN_INSERT_OFFENDER_TEAM_ASSIGNMENTS");
		int[] returnArray = new int[] {};
		final OffenderTeamAssignments returnData = new OffenderTeamAssignments();
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTeamAssignments object : lstOffenderTeamAssignments) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstOffenderTeamAssignments.size() == returnArray.length) {
			returnData.setReturnValue(1);

		} else {
			returnData.setReturnValue(0);
		}
		return returnData;

	}

	public String errorNameValidationTimes(final String errorName) {
		final String sql = getQuery("OIMVDTSL_CONSTRAINT_VALIDATIONS_TIMES");
		String returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("CONSTRAINTNAME", errorName),
					String.class);
		} catch (Exception e) {
			logger.error("errorNameValidationTimes", e);
			returnData = null;
			return returnData;
		}
		return returnData;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderTeamAssignments
	 *            List<OffenderTeamAssignments>
	 *
	 * @throws SQLException
	 */
	public Integer offTeamAssignUpdateOffenderTeamAssignments(
			final List<OffenderTeamAssignments> lstOffenderTeamAssignments) {
		
		final String sql = getQuery("OCDVTEAM_OFFTEAMASSIGN_UPDATE_OFFENDER_TEAM_ASSIGNMENTS");
		
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderTeamAssignments object : lstOffenderTeamAssignments) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderTeamAssignments.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgFunctionRecordGroup() {
		final String sql = getQuery("OCDVTEAM_FIND_RGFUNCTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgFunctionRecordGroup :ocdvteam", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public OffenderTeamAssignments offBkgOnCheckDeleteMaster(final OffenderTeamAssignments paramBean) {
		final String sql = getQuery("OCDVTEAM_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<OffenderTeamAssignments> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderTeamAssignments.class, offnderTeamMaping);
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(),
				columnRowMapper);
	}

	@Override
	public String getTeamIdDescription(final String teamId) {
		final String sql = getQuery("OCDVTEAM_GET_TEAM_DESCRIPTION");
		String returnValue = null;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("teamId", teamId), String.class);
		} catch (Exception e) {
			logger.error("getTeamIdDescription :ocdvteam", e);
		}
		return returnValue;
	}

	@Override
	public OffenderTeamAssignments deleteOffenderAssignment(final OffenderTeamAssignments objSearchDao) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_OFF_BOOK_ID", objSearchDao.getOffenderBookId());
		inParamMap.put("P_FUNCTION_TYPE", objSearchDao.getFunctionType());
		inParamMap.put("P_TEAM_ID", objSearchDao.getTeamId());
		inParamMap.put("P_ASSIGN_DATE", objSearchDao.getAssignmentDate());
		
		try {
			namedParameterJdbcTemplate
			.update("call OMS_OWNER.tag_workflow_adm.delete_off_vteam_dtls(:P_OFF_BOOK_ID, :P_FUNCTION_TYPE, :P_TEAM_ID ,:P_ASSIGN_DATE)", inParamMap);
			objSearchDao.setReturnValue(1);
		} catch (Exception e) {
			logger.error("Exceptiong: deleteOffenderAssignment", e);
			objSearchDao.setReturnValue(0);
		}
		return objSearchDao;
	}
	@Override
	public OffenderTeamAssignments getTeamDetails(final OffenderTeamAssignments searchbean) {
		Map<String, Object> returnObject = null;
		final OffenderTeamAssignments bean = new OffenderTeamAssignments();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_FUNCTION_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_TEAM_CODE", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_TEAM_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("P_TEAM_DESC", OracleTypes.VARCHAR),
				 };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADM").withProcedureName("GET_TEAM_DETAILS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_FUNCTION_TYPE", searchbean.getFunctionType());
		inParamMap.put("P_TEAM_CODE", searchbean.getTeamCode());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setTeamId(returnObject.get("P_TEAM_ID") != null
					? new BigDecimal(returnObject.get("P_TEAM_ID").toString()) : null);
			bean.setTeamIdDesc(
					returnObject.get("P_TEAM_DESC") != null ? String.valueOf(returnObject.get("P_TEAM_DESC")) : null);
		} catch (Exception e) {
			logger.error("In method getTeamDetails", e);
		}
		return bean;
	}
	@Override
	public OffenderTeamAssignments getTeamDesc(final OffenderTeamAssignments searchbean) {
		Map<String, Object> returnObject = null;
		final OffenderTeamAssignments bean = new OffenderTeamAssignments();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] { 
				new SqlOutParameter("P_TEAM_CODE", OracleTypes.VARCHAR),
				new SqlInOutParameter("P_TEAM_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("P_TEAM_NAME", OracleTypes.VARCHAR),
				 };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADM").withProcedureName("GET_TEAM_DESC")
				.declareParameters(sqlParameters);
		inParamMap.put("P_TEAM_ID", searchbean.getTeamId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setTeamIdDesc(
					returnObject.get("P_TEAM_NAME") != null ? String.valueOf(returnObject.get("P_TEAM_NAME")) : null);
			bean.setTeamCode(
					returnObject.get("P_TEAM_CODE") != null ? String.valueOf(returnObject.get("P_TEAM_CODE")) : null);
		} catch (Exception e) {
			logger.error("In method getTeamDesc", e);
		}
		return bean;
	}
}
