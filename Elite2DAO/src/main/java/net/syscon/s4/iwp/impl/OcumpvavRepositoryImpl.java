package net.syscon.s4.iwp.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.inst.institutionalactivities.maintenance.impl.OcmschrcRepositoryImpl;
import net.syscon.s4.iwp.OcumpvavRepository;

/**
 * Class OcumpvavRepositoryImpl
 * 
 * @version 1.0
 */
@Repository
public class OcumpvavRepositoryImpl extends RepositoryBase implements OcumpvavRepository {
	private static Logger logger = LogManager.getLogger(OcmschrcRepositoryImpl.class.getName());

	/**
	 * Creates new OcumpvavRepositoryImpl class Object
	 */
	public OcumpvavRepositoryImpl() {
		// OcumpvavRepositoryImpl;
	}

	private final Map<String, FieldMapper> courseScheduleRulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COURSE_SCHEDULE_RULE_ID", new FieldMapper("courseScheduleRuleId"))
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).put("WEEK_NO", new FieldMapper("weekNo"))
			.put("MONDAY_FLAG", new FieldMapper("mondayFlag")).put("TUESDAY_FLAG", new FieldMapper("tuesdayFlag"))
			.put("WEDNESDAY_FLAG", new FieldMapper("wednesdayFlag"))
			.put("THURSDAY_FLAG", new FieldMapper("thursdayFlag")).put("FRIDAY_FLAG", new FieldMapper("fridayFlag"))
			.put("SATURDAY_FLAG", new FieldMapper("saturdayFlag")).put("SUNDAY_FLAG", new FieldMapper("sundayFlag"))
			.put("START_TIME", new FieldMapper("startTime")).put("END_TIME", new FieldMapper("endTime"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CAPACITY", new FieldMapper("capacity")).build();
	private final Map<String, FieldMapper> courseActivitiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROVIDER_PARTY_ID", new FieldMapper("providerPartyId"))
			.put("P_CRS_ACTY_ID", new FieldMapper("pCrsActyId")).put("CRS_ACTY_ID", new FieldMapper("crsActyId"))
			.put("CASELOAD_ID", new FieldMapper("caseloadId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("SCHEDULE_START_DATE", new FieldMapper("scheduleStartDate"))
			.put("SCHEDULE_END_DATE", new FieldMapper("scheduleEndDate"))
			.put("PROGRAM_ID", new FieldMapper("programId")).put("HOLIDAY_FLAG", new FieldMapper("holidayFlag"))
			.put("SCHEDULE_NOTES", new FieldMapper("scheduleNotes")).put("CODE", new FieldMapper("code")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CourseActivities
	 *
	 * @return List<CourseActivities>
	 *
	 * @throws SQLException
	 */
	public List<CourseActivities> crsActExecuteQuery(final CourseActivities objSearchDao) {
		final String sql = getQuery("OCUMPVAV_CRSACT_FIND_COURSE_ACTIVITIES");
		final RowMapper<CourseActivities> CourseActivitiesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseActivities.class, courseActivitiesMapping);
		final ArrayList<CourseActivities> returnList = (ArrayList<CourseActivities>) namedParameterJdbcTemplate
				.query(sql, createParams("crsActyId", objSearchDao.getCrsActyId()), CourseActivitiesRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao CourseScheduleRules
	 *
	 * @return List<CourseScheduleRules>
	 *
	 * @throws SQLException
	 */
	public List<CourseScheduleRules> crsScheduleRulExecuteQuery(final CourseScheduleRules objSearchDao) {
		final String sql = getQuery("OCUMPVAV_CRSSCHEDULERUL_FIND_COURSE_SCHEDULE_RULES");
		final RowMapper<CourseScheduleRules> CourseScheduleRulesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseScheduleRules.class, courseScheduleRulesMapping);
		final ArrayList<CourseScheduleRules> returnList = (ArrayList<CourseScheduleRules>) namedParameterJdbcTemplate
				.query(sql, createParams("crsActyId", objSearchDao.getCrsActyId()), CourseScheduleRulesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstCourseScheduleRules List<CourseScheduleRules>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer crsScheduleRulInsertCourseScheduleRules(final List<CourseScheduleRules> lstCourseScheduleRules) {
		String sql = getQuery("OCUMPVAV_CRSSCHEDULERUL_INSERT_COURSE_SCHEDULE_RULES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseScheduleRules courSchRules : lstCourseScheduleRules) {
			parameters.add(new BeanPropertySqlParameterSource(courSchRules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCourseScheduleRules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	private long getNextCsRuleSeq() {
		long result = 0;
		Map<String, Object> returnObject = null;
		SqlParameter[] sqlParameters = new SqlParameter[1];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("lv_cs_rule_seq", Types.NUMERIC) };

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("tag_service").withFunctionName("get_next_cs_rule_seq")
				.declareParameters(sqlParameters);
		try {
			returnObject = simpleJDBCCall.execute();
			result = ((BigDecimal) returnObject.get("lv_cs_rule_seq")).longValue();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return result;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCourseScheduleRules List<CourseScheduleRules>
	 *
	 * @throws SQLException
	 */
	public Integer crsScheduleRulUpdateCourseScheduleRules(final List<CourseScheduleRules> lstCourseScheduleRules) {
		final String sql = getQuery("OCUMPVAV_CRSSCHEDULERUL_UPDATE_COURSE_SCHEDULE_RULES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseScheduleRules courseScheduleRules : lstCourseScheduleRules) {
			parameters.add(new BeanPropertySqlParameterSource(courseScheduleRules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCourseScheduleRules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCourseScheduleRules List<CourseScheduleRules>
	 *
	 * @throws SQLException
	 */
	public Integer crsScheduleRulDeleteCourseScheduleRules(final List<CourseScheduleRules> lstCourseScheduleRules) {
		final String sql = getQuery("OCUMPVAV_CRSSCHEDULERUL_DELETE_COURSE_SCHEDULE_RULES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseScheduleRules courseScheduleRules : lstCourseScheduleRules) {
			parameters.add(new BeanPropertySqlParameterSource(courseScheduleRules));
		}
		try {
			String tableName = "COURSE_SCHEDULE_RULES";
			String whereCondition = "COURSE_SCHEDULE_RULE_ID = :courseScheduleRuleId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCourseScheduleRules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public String getPrgSrvDetails(final Long programId) {
		final String sql = getQuery("OCUMPVAV_PROGRAM_SERVICES_FIND_DESCRIPTION");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("programId", programId), String.class);
	}

	@Override
	public Integer holidayFlagUpdateCourseActivities(List<CourseActivities> lstCourseActivities) {
		final String sql = getQuery("OCUMPVAV_HOLIDAY_FLAG_UPDATE_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseActivities courseScheduleRules : lstCourseActivities) {
			parameters.add(new BeanPropertySqlParameterSource(courseScheduleRules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCourseActivities.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
}
