package net.syscon.s4.inst.institutionalactivities.maintenance.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
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
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmschrcRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import oracle.jdbc.OracleTypes;

/**
 * Class OcmschrcRepositoryImpl
 * @version 1.0 
 */
@Repository
public class OcmschrcRepositoryImpl extends RepositoryBase implements OcmschrcRepository{
	private static Logger logger = LogManager.getLogger(OcmschrcRepositoryImpl.class.getName());
	
	/**
	 * Creates new OcmschrcRepositoryImpl class Object 
	 */
	public OcmschrcRepositoryImpl() {
	}
	
	private final Map<String, FieldMapper> crsSchdMap = new ImmutableMap.Builder<String, FieldMapper>()
	.put("CRS_ACTY_ID", 					new FieldMapper("crsActyId"))
	.put("COURSE_SCHEDULE_RULE_ID", 		new FieldMapper("courseScheduleRuleId"))
	.put("WEEK_NO", 						new FieldMapper("weekNo"))
	.put("MONDAY_FLAG", 					new FieldMapper("mondayFlag"))
	.put("TUESDAY_FLAG", 					new FieldMapper("tuesdayFlag"))
	.put("WEDNESDAY_FLAG", 					new FieldMapper("wednesdayFlag"))
	.put("THURSDAY_FLAG", 					new FieldMapper("thursdayFlag"))
	.put("FRIDAY_FLAG", 					new FieldMapper("fridayFlag"))
	.put("SATURDAY_FLAG", 					new FieldMapper("saturdayFlag"))
	.put("SUNDAY_FLAG", 					new FieldMapper("sundayFlag"))
	.put("START_TIME", 						new FieldMapper("startTime"))
	.put("END_TIME", 						new FieldMapper("endTime"))
	.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
	.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
	.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
	.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
	.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
	.put("CAPACITY", 						new FieldMapper("capacity"))
	.build();
	
	private final Map<String, FieldMapper> progServMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROGRAM_ID", 						new FieldMapper("programId"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.build();
	
	/**
	* This method is used to update the data base tables based on
	* @param lstCourseActivities List<CourseActivities>
	* @throws SQLException
	*/
	 public Integer crsActUpdateCourseActivities(final List<CourseActivities> CourActList)  {
		final String sql = getQuery("OCMSCHRC_CRSACT_UPDATE_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseActivities courseActivities : CourActList) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (CourActList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	* Fetch the records from database table
	* @param objSearchDao CourseScheduleRules
	* @return List<CourseScheduleRules>
	* @throws SQLException
	*/
	 public List<CourseScheduleRules> crsScheduleRulExecuteQuery(final CourseScheduleRules objSearchDao)  {
			final String sql = getQuery("OCMSCHRC_CRSSCHEDULERUL_FIND_COURSE_SCHEDULE_RULES");
			final RowMapper<CourseScheduleRules> CourSchRulRowMap = Row2BeanRowMapper.makeMapping(sql, CourseScheduleRules.class,crsSchdMap);
			return (ArrayList<CourseScheduleRules>)namedParameterJdbcTemplate.query(sql, createParams("crsActyId", objSearchDao.getCrsActyId()), CourSchRulRowMap);
	} 
	 
	/**
	* This method is used to update the data base tables based on
	* @param lstCourseScheduleRules List<CourseScheduleRules>
	* @throws SQLException
	*/
	 public Integer crsScheduleRulUpdateCourseScheduleRules(final List<CourseScheduleRules> courSchRulList)  {
		final String sql = getQuery("OCMSCHRC_CRSSCHEDULERUL_UPDATE_COURSE_SCHEDULE_RULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseScheduleRules courSchRules : courSchRulList) {
			parameters.add(new BeanPropertySqlParameterSource(courSchRules));
		}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (courSchRulList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	 
	
	/**
	* This method is used to delete records from  data base tables based on
	* @param lstCourseScheduleRules List<CourseScheduleRules>
	* @throws SQLException
	*/
	 public Integer crsScheduleRulDeleteCourseScheduleRules(final List<CourseScheduleRules> courSchRulList)  {
		final String sql = getQuery("OCMSCHRC_CRSSCHEDULERUL_DELETE_COURSE_SCHEDULE_RULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseScheduleRules courSchRules : courSchRulList) {
			 parameters.add(new BeanPropertySqlParameterSource(courSchRules));
		}
		try {
			String tableName = "COURSE_SCHEDULE_RULES";
			String whereClause = "COURSE_SCHEDULE_RULE_ID = :courseScheduleRuleId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method crsScheduleRulDeleteCourseScheduleRules", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (courSchRulList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	 
	/**
	*  This method is used to insert the records in the data base tables based on
	* @param lstCourseScheduleRules List<CourseScheduleRules>
	* @return List<Integer>
	* @throws SQLException
	*/
	public Integer crsScheduleRulInsertCourseScheduleRules(final List<CourseScheduleRules> courSchRulList) {
		final String sql = getQuery("OCMSCHRC_CRSSCHEDULERUL_INSERT_COURSE_SCHEDULE_RULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseScheduleRules courSchRules : courSchRulList) {
			parameters.add(new BeanPropertySqlParameterSource(courSchRules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (courSchRulList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public CourseScheduleRules buildRecurringSchedule(final CourseScheduleRules courSchRules) {
		CourseScheduleRules result = new CourseScheduleRules();
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("p_crs_acty_id", OracleTypes.NUMBER),
				new SqlParameter("p_no_days",OracleTypes.NUMBER), 
				new SqlOutParameter("p_no_sessions", OracleTypes.NUMBER),
				new SqlOutParameter("p_last_session_date", Types.DATE)};

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE_SCHED").withProcedureName("CS_BUILD_RECURRING_SCHEDULE")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(sqlParameters);

		inParamMap.put("p_crs_acty_id", courSchRules.getCrsActyId());
		inParamMap.put("p_no_days",courSchRules.getNoOfDays()); 
		
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			result.setNoBuilt(((BigDecimal)returnObject.get("p_no_sessions")).longValue()); 
			if(returnObject.get("p_last_session_date") != null) {
				result.setLastDate((Date) returnObject.get("p_last_session_date"));
			}
		} catch (Exception e) {
			logger.error("Exception :", e);
			 result = null;
		}
		return result;
	}


	@Override
	public Long getProfileValues() {
		long result = 0;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_PROFILE_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_PROFILE_CODE",OracleTypes.VARCHAR), 
				new SqlOutParameter("P_PROFILE_VALUE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_PROFILE_VALUE_2", Types.VARCHAR)};

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OMS_MISCELLANEOUS").withProcedureName("GET_PROFILE_VALUES")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(sqlParameters);

		inParamMap.put("P_PROFILE_TYPE", "CLIENT");
		inParamMap.put("P_PROFILE_CODE", "SCH_GEN"); 
		
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			result = Long.parseLong((String) returnObject.get("P_PROFILE_VALUE"));
		} catch (Exception e) {
			logger.error("Exception :", e);
			 result = 0;
		}
		return result;
	}

	
	@Override
	public String getPrgSrvDetails(final Long programId) {
		String result = "";
		final String sql = getQuery("OCMSCHRC_PROGRAM_SERVICES_FIND_DESCRIPTION");
		final RowMapper<ProgramServices> ProgServMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class, progServMap);
		final ArrayList<ProgramServices> progServicesList = (ArrayList<ProgramServices>)namedParameterJdbcTemplate.query(sql, createParams("programId", programId), ProgServMapper);
		result = progServicesList.get(0).getDescription();
		return result;		
	}

	
	@Override
	public String getFlag(final Long crsActyId) {
		final String sql = getQuery("OCMSCHRC_GET_FLAG");
		return namedParameterJdbcTemplate.queryForObject(sql,createParams("crsActyId",crsActyId),String.class);
	}

}
