package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.casemanagement.beans.ProgramAssessments;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmsvassRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OcmsvassRepositoryImpl
 * 
 */
@Repository
public class OcmsvassRepositoryImpl extends RepositoryBase implements OcmsvassRepository {

	/**
	 * Logger object used to print the log in the file
	 */

	private static Logger logger = LogManager.getLogger(OcmsvassRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ASSESSMENT_CODE", new FieldMapper("assessmentCode"))
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> programAssessmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROGRAM_ID", new FieldMapper("programId")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate")).put("ASSESSMENT_ID", new FieldMapper("assessmentId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();

	/**
	 * OcmsvassRepositoryImpl class Object
	 */
	public OcmsvassRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * List<ProgramAssessments>
	 * 
	 * @return List<ProgramAssessments>
	 *
	 * 
	 */
	public List<ProgramAssessments> prgQstExecuteQuery(ProgramAssessments objSearchDao) {
		final String sql = getQuery("OCMSVASS_PRGQST_FIND_PROGRAM_ASSESSMENTS");
		final RowMapper<ProgramAssessments> ProgramAssessmentsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ProgramAssessments.class, programAssessmentsMapping);
		List<ProgramAssessments> returnList = new ArrayList<ProgramAssessments>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("PROGRAMID",objSearchDao.getProgramId()), ProgramAssessmentsRowMapper);
		} catch (Exception e) {
			logger.error("prgQstExecuteQuery", e);

		}
		return returnList;
	}


	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * 
	 * List<ProgramAssessments>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer prgQstInsertProgramAssessments(final List<ProgramAssessments> lstProgramAssessments) {
		String sql = getQuery("OCMSVASS_PRGQST_INSERT_PROGRAM_ASSESSMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProgramAssessments programAssessments : lstProgramAssessments) {
			parameters.add(new BeanPropertySqlParameterSource(programAssessments));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstProgramAssessments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstProgramAssessments
	 *            List<ProgramAssessments>
	 *
	 * 
	 */
	public Integer prgQstUpdateProgramAssessments(final List<ProgramAssessments> lstProgramAssessments) {
		String sql = getQuery("OCMSVASS_PRGQST_UPDATE_PROGRAM_ASSESSMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProgramAssessments programAssessments : lstProgramAssessments) {
			parameters.add(new BeanPropertySqlParameterSource(programAssessments));
		}

		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstProgramAssessments.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstProgramAssessments
	 *            List<ProgramAssessments>
	 *
	 * @return Integer
	 */
	public Integer prgQstDeleteProgramAssessments(final List<ProgramAssessments> lstProgramAssessments) {
		String sql = getQuery("OCMSVASS_PRGQST_DELETE_PROGRAM_ASSESSMENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ProgramAssessments programAssessments : lstProgramAssessments) {
			parameters.add(new BeanPropertySqlParameterSource(programAssessments));
		}
		try {
			String tableName = "PROGRAM_ASSESSMENTS";
			String whereClause = "PROGRAM_ID =:programId and  ASSESSMENT_ID=:assessmentId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method prgQstDeleteProgramAssessments", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstProgramAssessments.size() == returnArray.length) {
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
	public List<Assessments> rgAssessmentsRecordGroup() {
		final String sql = getQuery("OCMSVASS_FIND_RGASSESSMENTS");
		final RowMapper<Assessments> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Assessments.class, mMapping);
		List<Assessments> returnList = new ArrayList<Assessments>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgAssessmentsRecordGroup", e);
		}
		return returnList;
	}

	public Integer postQuery(final ProgramAssessments object) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[1];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_ASSESSMENT_ID", OracleTypes.NUMBER) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE").withProcedureName("GET_ASSESSMENT_DETAILS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_ASSESSMENT_ID", object.getAssessmentId());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if (returnObject.size() == 0) {
				returnValue = 1;
			}
		} catch (Exception e) {
			returnValue = 0;
		}
		return returnValue;
	}

	public String getAssessmentCode(final BigDecimal assessmentId) {
		final String sql = getQuery("OCMSVASS_GET_ASSESSMENT_CODE");
		String returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("ASSESSMENTID", assessmentId),
					String.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

	public BigDecimal getAssessmentId(final String assessmentCode) {
		final String sql = getQuery("OCMSVASS_GET_ASSESSMENT_ID");
		BigDecimal returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("ASSESSMENTCODE", assessmentCode),
					BigDecimal.class);
		} catch (Exception e) {
			return returnList;
		}
		return returnList;
	}

}
