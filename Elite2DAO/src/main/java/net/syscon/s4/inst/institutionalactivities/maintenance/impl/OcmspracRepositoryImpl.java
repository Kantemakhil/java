package net.syscon.s4.inst.institutionalactivities.maintenance.impl;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.OracleSimpleJdbcCall;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmspracRepository;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;
import net.syscon.s4.inst.schedules.bean.IntLocUsageLocations;
import oracle.jdbc.OracleTypes;
/**
 * Class OcmspracRepositoryImpl
 */
@Repository
public class OcmspracRepositoryImpl extends RepositoryBase implements OcmspracRepository{
private final JdbcTemplate jdbcTemplate ;
	

	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OcmspracRepositoryImpl.class.getName());

private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("PROGRAM_CODE", 	new FieldMapper("programCode"))
.put("DESCRIPTION", 	new FieldMapper("description"))
.put("CODE", 			new FieldMapper("code"))
.put("LOCCODE",         new FieldMapper("locCode"))
.put("ACTIVE_FLAG",         new FieldMapper("activeFlag"))
.build();
private final Map<String, FieldMapper> mminternalLocationUsagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("INTERNAL_LOCATION_CODE", 						new FieldMapper("internalLocationCode"))
.build();
private final Map<String, FieldMapper> courseActivitiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("INTERNAL_LOCATION_CODE", 						new FieldMapper("internalLocationCode"))
.put("SCHENDDATE", 						new FieldMapper("schEndDate"))
.put("ROW_ID", 						new FieldMapper("rowId"))
.build();
private final Map<String, FieldMapper> parentMaqpping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TABLE_NAME", 							new FieldMapper("tableName"))
.put("COLUMN_NAME", 						new FieldMapper("columnName"))
.build();

	/**
	 * Creates new OcmspracRepositoryImpl class Object
	 */
public OcmspracRepositoryImpl(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

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
	public List<CourseActivities> courseActivitiesExecuteQuery(CourseActivities objSearchDao) {
		final String sql = getQuery("OCMSPRAC_COURSEACTIVITIES_FIND_COURSE_ACTIVITIES");
		final RowMapper<CourseActivities> CourseActivitiesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseActivities.class, courseActivitiesMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("caseload_type", objSearchDao.getCaseloadType(), "agy_loc_id", objSearchDao.getAgyLocId()),
				CourseActivitiesRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstCourseActivities
	 *            List<CourseActivities>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer courseActivitiesInsertCourseActivities(final List<CourseActivities> lstCourseActivities) {
		String sql = getQuery("OCMSPRAC_COURSEACTIVITIES_INSERT_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (CourseActivities courseActivities : lstCourseActivities) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCourseActivities.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstCourseActivities
	 *            List<CourseActivities>
	 *
	 * @throws SQLException
	 */
	public Integer courseActivitiesUpdateCourseActivities(final List<CourseActivities> lstCourseActivities) {
		String sql = getQuery("OCMSPRAC_COURSEACTIVITIES_UPDATE_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (CourseActivities courseActivities : lstCourseActivities) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCourseActivities.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstCourseActivities
	 *            List<CourseActivities>
	 *
	 * @throws SQLException
	 */
	public Integer courseActivitiesDeleteCourseActivities(final List<CourseActivities> lstCourseActivities) {
		String sql = getQuery("OCMSPRAC_COURSEACTIVITIES_DELETE_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<>();
		for (CourseActivities courseActivities : lstCourseActivities) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		try {
			String tableName = "COURSE_ACTIVITIES";
			String whereClause = "CRS_ACTY_ID =:crsActyId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method courseActivitiesDeleteCourseActivities", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstCourseActivities.size() == returnArray.length) {
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
	public List<AgencyLocations> rgAgyLocRecordGroup(final String caseloadId) {
		final String sql = getQuery("OCMSPRAC_FIND_RGAGYLOC");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID",caseloadId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in rgAgyLocRecordGroup : Ocmsprac:",e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ProgramServices> rgPrisonActivityRecordGroup() {
		final String sql = getQuery("OCMSPRAC_FIND_RGPRISONACTIVITY");
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in rgPrisonActivityRecordGroup : Ocmsprac:",e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MMInternalLocationUsages>
	 */
	public List<IntLocUsageLocations> rgInternalLocationRecordGroup(final String agyLocId) {
		final String sql = getQuery("OCMSPRAC_FIND_RGINTERNALLOCATION");
		final RowMapper<IntLocUsageLocations> mMInternalLocationUsagesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				IntLocUsageLocations.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("NBTAGYLOCID",agyLocId), mMInternalLocationUsagesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in rgInternalLocationRecordGroup : Ocmsprac:",e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgIepLevelRecordGroup() {
		final String sql = getQuery("OCMSPRAC_FIND_RGIEPLEVEL");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in rgIepLevelRecordGroup : Ocmsprac:",e);
			return Collections.emptyList();
		}
	}

	@Override
	public String chkActyEndDate(CourseActivities bean) {
		Map<String, Object> returnObject = null;
		String code = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CRS_ACTY_ID", Types.INTEGER),
				 new SqlParameter("P_SCHEDULE_END_DATE", Types.DATE),
				new SqlOutParameter("RETURN_VALUE", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PRISON_ACTIVITIES").withFunctionName("CHK_ACTY_END_DATE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CRS_ACTY_ID", bean.getCrsActyId());
		inParamMap.put("P_SCHEDULE_END_DATE", bean.getScheduleEndDate());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			if(returnObject.get("RETURN_VALUE") != null){
				code = (String) returnObject.get("RETURN_VALUE");
			} else {
				code = "null";
			}
		} catch (Exception e) {
			code = "null";
		}
		return code;
	}

	@Override
	public Integer checkCodeExists(List<String> agyLocId, List<String> code, List<String> caseloadId,
			List<String> caseloadType) {
		final String sql = getQuery("OCMSPRAC_CHECK_CODE_EXISTS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_agy_loc_id", agyLocId, "p_code", code,
				"p_caseload_id", caseloadId, "p_caseload_type", caseloadType), Integer.class);
	}
	@Override
	public Integer checkCodeExistsonUpdate(List<String> agyLocId, List<String> code, List<String> caseloadId,
			List<String> caseloadType,List<String> rowId) {
		final String sql = getQuery("OCMSPRAC_CHECK_CODE_EXISTS_ON_UPDATE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_agy_loc_id", agyLocId, "p_code", code,
				"p_caseload_id", caseloadId, "p_caseload_type", caseloadType,"rowid",rowId), Integer.class);
	}

	@Override
	public Integer deleteCourseActivityAreas(CourseActivities bean) {
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] {new SqlParameter("P_CRS_ACTY_ID", OracleTypes.INTEGER),
				};
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE").withProcedureName("DELETE_COURSE_ACTIVITY_AREAS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CRS_ACTY_ID", bean.getCrsActyId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
		 simpleJDBCCall.execute(inParameter);
		} catch(Exception e) {
			return 1;
		}
		return 0;
	}

	@Override
	public Integer updateCourseSchedules(CourseActivities updBean) {
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] {new SqlParameter("P_CRS_ACTY_ID", OracleTypes.INTEGER),
				new SqlParameter("P_SCHEDULE_END_DATE", OracleTypes.DATE) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PRISON_ACTIVITIES").withProcedureName("UPDATE_COURSE_SCHEDULES")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CRS_ACTY_ID", updBean.getCrsActyId());
		inParamMap.put("P_SCHEDULE_END_DATE", updBean.getScheduleEndDate());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
		 simpleJDBCCall.execute(inParameter);
		} catch(Exception e) {
			return 1;
		}
		return 0;
	}

	@Override
	public Integer removeCourseSchedules(CourseActivities updBean) {
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] {new SqlParameter("P_CRS_ACTY_ID", OracleTypes.INTEGER),
				new SqlParameter("P_SCHEDULE_END_DATE", OracleTypes.DATE) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PRISON_ACTIVITIES").withProcedureName("REMOVE_COURSE_SCHEDULES")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CRS_ACTY_ID", updBean.getCrsActyId());
		inParamMap.put("P_SCHEDULE_END_DATE", updBean.getScheduleEndDate());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
		 simpleJDBCCall.execute(inParameter);
		} catch(Exception e) {
			return 1;
		}
		return 0;
	}
	@Override
	public Long getcrsActyId() {
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlOutParameter("RETURN_VALUE", Types.VARCHAR) };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PRISON_ACTIVITIES").withFunctionName("GET_CRS_ACTY_ID")
				.declareParameters(sqlParameters);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		returnObject = simpleJDBCCall.execute(inParameter);
		return Long.valueOf(returnObject.get("RETURN_VALUE").toString());
	}
	@Override
	public Integer okToDeleteRecord(Long crsActyId) {
		Integer resultValue=0;
		
		SqlParameterSource  args = new MapSqlParameterSource()
			.addValue("P_KEY_ID", crsActyId)
			.addValue("P_TABLE_NAME", "COURSE_ACTIVITIES")
			.addValue("P_COLUMN_NAME", "CRS_ACTY_ID")
			.addValue("P_EXCLUDE_TABLE", "COURSE_ACTIVITY_AREAS")
			.addValue("P_OWNER",  "OMS_OWNER");			

		
		SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_UTILS")
				.withFunctionName("OK_TO_DELETE_RECORD")
				.withoutProcedureColumnMetaDataAccess();
		
		
		jdbcCall.declareParameters(	
			new SqlOutParameter("RETURN_VALUE", Types.BOOLEAN),
			new SqlParameter("P_KEY_ID", Types.INTEGER),
			new SqlParameter("P_TABLE_NAME", Types.VARCHAR),
			new SqlParameter("P_COLUMN_NAME", Types.VARCHAR),
			new SqlParameter("P_EXCLUDE_TABLE", Types.VARCHAR),
			new SqlParameter("P_OWNER", Types.VARCHAR)
		);
			
		Boolean result = jdbcCall.executeFunction(Boolean.class, args);
		if(result) {
			resultValue=1;
		}else {
			resultValue=0;
		}
		
		return resultValue;
		
	}
	@Override
	public List<TableColumnNameBean> okToDelRecord() {
		final String sql = getQuery("OK_TO_DELETE_RECORD");
		final RowMapper<TableColumnNameBean> mRowMapper = Row2BeanRowMapper.makeMapping(sql, TableColumnNameBean.class,
				parentMaqpping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Integer getChildCount(String tableName, String columnName, long crsactyId) {
		final String sql = "SELECT count(*) FROM ";
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (tableName != null) {
			sqlQuery.append(tableName);
			sqlQuery.append(" WHERE ");
			if (columnName != null) {
				sqlQuery.append(columnName +" = :crsactyId " + "limit 1");
				params.addValue("crsactyId", crsactyId);
			}
		}
		preparedSql = sqlQuery.toString().trim();
		return namedParameterJdbcTemplate.queryForObject(preparedSql, params, Integer.class);

	}

}
