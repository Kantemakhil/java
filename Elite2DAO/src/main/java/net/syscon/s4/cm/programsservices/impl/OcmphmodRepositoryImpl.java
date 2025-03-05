package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
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
import net.syscon.s4.cm.programsservices.OcmphmodRepository;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;

/**
 * Class OcmphmodRepositoryImpl
 * 
 * @version 1.0
 */
@Repository
public class OcmphmodRepositoryImpl extends RepositoryBase implements OcmphmodRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmphmodRepositoryImpl.class);

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", new FieldMapper("corporateName")).put("CORPORATE_ID", new FieldMapper("corporateId"))
			.put("PROVIDER_PARTY_ID", new FieldMapper("providerPartyId")).put("AREA", new FieldMapper("area"))
			.put("COUNTRY", new FieldMapper("country")).put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code")).put("TEAM_CODE", new FieldMapper("teamCode"))
			.put("ZIP_POSTAL_CODE", new FieldMapper("zipPostalCode")).put("HOUSE", new FieldMapper("house"))
			.put("STREET", new FieldMapper("street")).put("TEAM_ID", new FieldMapper("teamId"))
			.put("PROGRAM_ID", new FieldMapper("programId"))
			.put("placementCorporateId", new FieldMapper("placementCorporateId"))
			.put("NBT_DESCRIPTION", new FieldMapper("nbtDescription")).put("START_FLAG", new FieldMapper("startFlag"))
			.build();

	/**
	 * Creates new OcmphmodRepositoryImpl class Object
	 */
	public OcmphmodRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CourseActivities
	 *
	 * @return List<CourseActivities>
	 *
	 * @throws SQLException
	 */
	public List<CourseActivities> courseActivitiesExecuteQuery(CourseActivities objSearchDao) {
		final String sql = getQuery("OCMPHMOD_COURSEACTIVITIES_FIND_COURSE_ACTIVITIES");
		final RowMapper<CourseActivities> CourseActivitiesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseActivities.class, mMapping);
		List<CourseActivities> returnList = new ArrayList<CourseActivities>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("CRSACTYID", objSearchDao.getParentCrsActyId()), CourseActivitiesRowMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * @param
	 *
	 * @throws SQLException
	 *
	 */
	public CourseActivities courseActDeleteCourseActivities(final List<CourseActivities> lstCourseActivities) {
		String sql = getQuery("OCMPHMOD_DELETE_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		CourseActivities returnData = new CourseActivities();
		for (CourseActivities courseActivities : lstCourseActivities) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		try {
			String tableName = "COURSE_ACTIVITIES";
			String whereClause = "CRS_ACTY_ID=:crsActyId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method courseActDeleteCourseActivities", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("OMS_OWNER")) {
				error = error.substring(error.indexOf("OMS_OWNER."), error.indexOf(")")).replaceFirst("OMS_OWNER.", "");
				final String tableName = errorNameValidation(error);
				returnData.setSealFlag(tableName);
				returnData.setSeqOne(BigDecimal.valueOf(2292));
				return returnData;
			}
		}
		if (lstCourseActivities.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCourseActivities List<CourseActivities>
	 *
	 * @throws SQLException
	 */
	public CourseActivities courseActivitiesUpdateCourseActivities(final List<CourseActivities> lstCourseActivities) {
		String sql = getQuery("OCMPHMOD_COURSEACTIVITIES_UPDATE_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		CourseActivities returnData = new CourseActivities();
		for (CourseActivities courseActivities : lstCourseActivities) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			String error = "Error : " + e.getMessage();
			if (error.contains("OMS_OWNER")) {
				error = error.substring(error.indexOf("OMS_OWNER."), error.indexOf(")")).replaceFirst("OMS_OWNER.", "");
				final String tableName = errorNameValidation(error);
				returnData.setSealFlag(tableName);
				returnData.setSeqOne(BigDecimal.valueOf(2291));
				return returnData;
			}
		}
		if (lstCourseActivities.size() == returnArray.length) {
			returnData.setSealFlag("1");
			return returnData;
		} else {
			returnData.setSealFlag("0");
			return returnData;
		}
	}

	public Long countSessions(final Long bean) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CRS_ACTY_ID", Types.NUMERIC),
				new SqlOutParameter("RETURN", Types.NUMERIC) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE_SCHED").withFunctionName("COUNT_SESSIONS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CRS_ACTY_ID", bean);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		returnObject = simpleJDBCCall.execute(inParameter);
		Long count = returnObject.get("RETURN") != null ? new Long(String.valueOf(returnObject.get("RETURN"))) : null;
		return count;
	}

	public List<CourseActivities> getSrgSrvDetails(CourseActivities obj) {
		List<CourseActivities> courtList = new ArrayList<CourseActivities>();
		final String sql = getQuery("OCMPHMOD_GET_PRG_SRV_DETAILS");
		final RowMapper<CourseActivities> mRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mMapping);

		try {
			courtList = namedParameterJdbcTemplate.query(sql, createParams("programId", obj.getProgramId()),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception :", e);
		}
		return courtList;

	}

	public Long getCrsSessionCount(final Long bean) {
		final String sql = getQuery("OCMPHMOD_SESSION_COUNT");
		Long count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_CRS_ACTY_ID", bean), Long.class);
		} catch (Exception e) {
			logger.error("getCrsSessionCount :", e);
			count = 0l;
		}

		return count;
	}

	public Long doUpdateOnCrsPhase(final Long bean, Long obj) {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CRS_ACTY_ID", Types.NUMERIC),
				new SqlParameter("P_TOTAL", Types.NUMERIC) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE").withProcedureName("DO_UPDATE_ON_CRS_PHASE")
				.declareParameters(sqlParameters);

		inParamMap.put("P_CRS_ACTY_ID", bean);
		inParamMap.put("P_TOTAL", obj);

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		Long count = 0L;
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			count = 1L;
		} catch (Exception e) {
			return count;
		}
		return count;
	}

	public String errorNameValidation(final String errorName) {
		final String sql = getQuery("OCMPHMOD_CONSTRAINT_VALIDATIONS");
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

}
