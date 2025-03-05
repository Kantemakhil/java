package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmsoschRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseSchedules;

/**
 * Class OcmsoschRepositoryImpl
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
@Repository
public class OcmsoschRepositoryImpl extends RepositoryBase implements OcmsoschRepository {

	/**
	 * Creates new OcmsoschRepositoryImpl class Object
	 */
	private static Logger logger = LogManager.getLogger(OcmsoschRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> crsSchMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).build();

	private final Map<String, FieldMapper> crsActMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("SCHENDDATE", new FieldMapper("schEndDate")).put("ROW_ID", new FieldMapper("rowId")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            CourseActivities
	 *
	 * @return List<CourseActivities>
	 *
	 * @throws SQLException
	 */
	public List<CourseActivities> crsActExecuteQuery(final CourseActivities objSearchDao) {
		final String sql = getQuery("OCMSOSCH_CRSACT_FIND_COURSE_ACTIVITIES");
		final RowMapper<CourseActivities> crsActRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				crsActMapping);
		return (ArrayList<CourseActivities>) namedParameterJdbcTemplate.query(sql, createParams(), crsActRowMapper);

	}


	/**
	 * Fetch the records from database table
	 *
	 * @param searchDao
	 *            CourseSchedules
	 *
	 * @return List<CourseSchedules>
	 *
	 * @throws SQLException
	 */
	public List<CourseSchedules> courseSchedExecuteQuery(final CourseSchedules searchDao) {
		final String sql = getQuery("OCMSOSCH_COURSESCHED_FIND_COURSE_SCHEDULES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer(50);
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (searchDao != null) {
			sqlQuery.append(" AND ");

			params.addValue("p_crs_acty_id", searchDao.getCrsActyId());
			if (searchDao.getScheduleDate() != null) {
				sqlQuery.append(" SCHEDULE_DATE  = :scheduleDate ");
				params.addValue("scheduleDate", searchDao.getScheduleDate());
			}

		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		preSqlQuery = preparedSql.concat(" ORDER BY schedule_date, start_time,end_time ");
		final RowMapper<CourseSchedules> crsSchRowMapper = Row2BeanRowMapper.makeMapping(preSqlQuery,
				CourseSchedules.class, crsSchMapping);
		List<CourseSchedules> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, crsSchRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstCrsSchedules
	 *            List<CourseSchedules>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer courseSchedInsertCourseSchedules(final List<CourseSchedules> lstCrsSchedules) {
		final String sql = getQuery("OCMSOSCH_COURSESCHED_INSERT_COURSE_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseSchedules courseSchedules : lstCrsSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(courseSchedules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCrsSchedules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCrsSchedules
	 *            List<CourseSchedules>
	 *
	 * @throws SQLException
	 */
	public Integer courseSchedUpdateCourseSchedules(final List<CourseSchedules> lstCrsSchedules) {
		final String sql = getQuery("OCMSOSCH_COURSESCHED_UPDATE_COURSE_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseSchedules courseSchedules : lstCrsSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(courseSchedules));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCrsSchedules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCrsSchedules
	 *            List<CourseSchedules>
	 *
	 * @throws SQLException
	 */
	public Integer courseSchedDeleteCourseSchedules(final List<CourseSchedules> lstCrsSchedules) {
		final String sql = getQuery("OCMSOSCH_COURSESCHED_DELETE_COURSE_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourseSchedules courseSchedules : lstCrsSchedules) {
			parameters.add(new BeanPropertySqlParameterSource(courseSchedules));
		}
		try {
			String tableName = "COURSE_SCHEDULES";
			String whereClause = "CRS_SCH_ID = :crsSchId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method courseSchedDeleteCourseSchedules", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCrsSchedules.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public String getDate(final CourseSchedules courseSchedules) {
		final String sql = getQuery("OCMSOSCH_GET_DAY");
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		final String date = simpleDateFormat.format(courseSchedules.getScheduleDate());
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("scheduleDate", date), String.class);

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * crsActOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<CourseSchedules> crsActOnCheckDeleteMaster(final CourseSchedules paramBean) {
		final String sql = getQuery("OCMSOSCH_CRS_ACT_ONCHECKDELETEMASTER");
		final RowMapper<CourseSchedules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CourseSchedules.class,
				crsSchMapping);
		return (ArrayList<CourseSchedules>) namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
	}

	public String clearSchedules(final CourseSchedules courseSchedules) {
		String code = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CRS_ACTY_ID", Types.NUMERIC),
				new SqlParameter("P_CRS_SCH_ID", Types.NUMERIC) };

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("tag_service_sched").withProcedureName("cs_clear_schedules")
				.declareParameters(sqlParameters);

		inParamMap.put("P_CRS_ACTY_ID", courseSchedules.getCrsActyId());
		inParamMap.put("P_CRS_SCH_ID", courseSchedules.getCrsSchId());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			simpleJDBCCall.execute(inParameter);
			code = "1";

		} catch (Exception e) {

			code = "2";
		}
		return code;
	}
	
	
	public String diffBetweenDates(final Date startDate,final Date endDate) {
		final String sql = getQuery("OCMSOSCH_DIFF_BETWEEN_DATES");
		SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String startTime = form.format(startDate);
		String endTime = form.format(endDate);

		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("START_TIME", startTime, "END_TIME", endTime), String.class);
		} catch (Exception e) {
			logger.error("Exception in diffBetweenDates : ", e.getMessage());
			return "N";
		}
	}
}
