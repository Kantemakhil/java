package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

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
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmserviRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OcmserviRepositoryImpl
 */
@Repository
public class OcmserviRepositoryImpl extends RepositoryBase implements OcmserviRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmserviRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> refCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();
	private final Map<String, FieldMapper> programServicesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("FUNCTION_TYPE", new FieldMapper("functionType"))
			.put("PROGRAM_CATEGORY", new FieldMapper("programCategory")).put("PROGRAM_ID", new FieldMapper("programId"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("PROGRAM_CODE", new FieldMapper("programCode"))
			.build();

	/**
	 * Creates new OcmserviRepositoryImpl class Object
	 */
	public OcmserviRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao ProgramServices
	 *
	 * @return List<ProgramServices>
	 *
	 * @throws SQLException
	 */
	public List<ProgramServices> prgSrvExecuteQuery(final ProgramServices objSearchDao) {
		final String sql = getQuery("OCMSERVI_PRGSRV_FIND_PROGRAM_SERVICES");
		final RowMapper<ProgramServices> prgRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				programServicesMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE PROGRAM_CLASS = 'PRG' AND PROGRAM_CATEGORY != 'MED_SCHED' AND ");
			if (objSearchDao.getProgramCategory() != null && !("").equals(objSearchDao.getDescription())) {
				sqlQuery.append(
						" PROGRAM_CATEGORY IN (SELECT CODE FROM REFERENCE_CODES WHERE  DOMAIN='PS_CATEGORY' AND UPPER(CODE) LIKE UPPER(:PROGRAM_CATEGORY))"
								+ " AND ");
				inParameterSource.addValue("PROGRAM_CATEGORY", objSearchDao.getProgramCategory().trim());
			}
			if (objSearchDao.getProgramCode() != null && !("").equals(objSearchDao.getDescription())) {
				sqlQuery.append(" PROGRAM_CODE = :PROGRAM_CODE" + " AND ");
				inParameterSource.addValue("PROGRAM_CODE", objSearchDao.getProgramCode().trim());
			}
			if (objSearchDao.getDescription() != null && !("").equals(objSearchDao.getDescription())) {
				sqlQuery.append(" DESCRIPTION = :DESCRIPTION" + " AND ");
				inParameterSource.addValue("DESCRIPTION", objSearchDao.getDescription().trim());
			}
			if (objSearchDao.getFunctionType() != null && !("").equals(objSearchDao.getFunctionType())) {
				sqlQuery.append(" FUNCTION_TYPE = :FUNCTIONTYPE" + " AND ");
				inParameterSource.addValue("FUNCTIONTYPE", objSearchDao.getFunctionType().trim());
			}
			if (objSearchDao != null && objSearchDao.getActiveFlag() != null
					&& !("").equals(objSearchDao.getActiveFlag())) {
				if ("true".equals(objSearchDao.getActiveFlag())) {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				sqlQuery.append(" ACTIVE_FLAG  = :ACTIVEFLAG " + " AND");
				inParameterSource.addValue("ACTIVEFLAG", objSearchDao.getActiveFlag());
			}
			if (objSearchDao != null && objSearchDao.getExpiryDate() != null
					&& !("").equals(objSearchDao.getExpiryDate())) {
				sqlQuery.append(" trunc(EXPIRY_DATE)   = trunc(:expiryDate)" + " AND");
				inParameterSource.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
		}
		preparedSql = sqlQuery.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " ORDER BY ACTIVE_FLAG DESC, PROGRAM_CODE";
		List<ProgramServices> returnList = namedParameterJdbcTemplate.query(preparedSql, inParameterSource,
				prgRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstProgramServices List<ProgramServices>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public String prgSrvInsertProgramServices(final List<ProgramServices> listObj) {
		final String sql = getQuery("OCMSERVI_PRGSRV_INSERT_PROGRAM_SERVICES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProgramServices programServices : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(programServices));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("constraint")) {
				error = error.substring(error.indexOf("constraint"), error.indexOf("Detail"))
						.replaceFirst("constraint", "");
				String constraintName = error.substring(1, error.length()-1);
				return constraintName;
			}
		}
		if (listObj.size() == returnArray.length) {
			return "1";
		} else {
			return "0";
		}

	}

	@Override
	public String getTableName(final String errorName) {
		final String sql = getQuery("OCMSERVI_CONSTRAINT_VALIDATIONS");
		List<String> returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForList(sql, createParams("P_CONSTRAINT_NAME", errorName),
					String.class);
		} catch (Exception e) {
			return null;
		}
		return returnData.get(0);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstProgramServices List<ProgramServices>
	 *
	 * @throws SQLException
	 */
	public Integer prgSrvUpdateProgramServices(final List<ProgramServices> listObj) {
		final String sql = getQuery("OCMSERVI_PRGSRV_UPDATE_PROGRAM_SERVICES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProgramServices programServices : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(programServices));
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
	 * @param lstProgramServices List<ProgramServices>
	 *
	 * @throws SQLException
	 */
	public String prgSrvDeleteProgramServices(final List<ProgramServices> listObj) {
		final String sql = getQuery("OCMSERVI_PRGSRV_DELETE_PROGRAM_SERVICES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProgramServices programServices : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(programServices));
		}
		try {
			String tableName = "PROGRAM_SERVICES";
			String whereClause = "PROGRAM_ID = :programId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method prgSrvDeleteProgramServices", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String errorMessage = "Error : " + e.getMessage();
			if (errorMessage.contains("constraint")) {
				String conName = errorMessage
						.substring(errorMessage.indexOf("constraint"), errorMessage.indexOf("\" on"))
						.replaceFirst("constraint", "").trim();
				String constraintName = conName.substring(1, conName.length());
				return constraintName;
			}
		}

		if (listObj.size() == returnArray.length) {
			return "1";
		} else {
			return "0";
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPsCategoryRecordGroup() {
		final String sql = getQuery("OCMSERVI_FIND_RGPSCATEGORY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in rgPsCategoryRecordGroup :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgFunctionTypeRecordGroup() {
		final String sql = getQuery("OCMSERVI_FIND_RGFUNCTIONTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in rgFunctionTypeRecordGroup :", e);
			return Collections.emptyList();
		}
	}

	@Override
	public Integer preUpdate(final ProgramServices object) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[9];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_PROGRAM_ID", OracleTypes.NUMBER) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE").withProcedureName("CHECK_PROGRAM_SERVICE_UPDATE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_PROGRAM_ID", object.getProgramId());

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

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offSchPreInsert
	 *
	 *
	 */
	public BigDecimal preInsertPrgSrv(final ProgramServices object) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		BigDecimal returnValue = null;
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_PROGRAM_CATEGORY", OracleTypes.VARCHAR),
				new SqlParameter("P_ACTIVE_FLAG", OracleTypes.VARCHAR),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.NUMBER) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE").withFunctionName("PRE_INSERT_PROGRAM_SERVICE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_PROGRAM_CATEGORY", object.getProgramCategory());
		inParamMap.put("P_ACTIVE_FLAG", object.getActiveFlag());
		inParamMap.put("RETURN_VALUE", 0);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnValue = simpleJDBCCall.executeFunction(BigDecimal.class, inParameter);
		} catch (Exception e) {
			return returnValue;
		}
		return returnValue;
	}

	@Override
	public Integer checkProgramCode(final ProgramServices object) {
		Map<String, Object> returnObject = null;
		Integer returnValue = 0;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[9];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_PROGRAM_CODE", OracleTypes.VARCHAR) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE").withProcedureName("CHECK_PROGRAM_CODE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_PROGRAM_CODE", object.getProgramCode());

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
}
