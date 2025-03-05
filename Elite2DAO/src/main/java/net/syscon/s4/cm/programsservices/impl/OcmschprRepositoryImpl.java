package net.syscon.s4.cm.programsservices.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
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
import net.syscon.s4.cm.programsservices.OcmschprRepository;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.CourseScheduleRules;
import net.syscon.s4.iwp.beans.VAcpSchedules;
import oracle.jdbc.OracleTypes;

@Repository
public class OcmschprRepositoryImpl extends RepositoryBase implements OcmschprRepository {

	/**
	 * Creates new OcmschprRepositoryImpl class Object
	 */
	public OcmschprRepositoryImpl() {
	}
	
	private static Logger logger = LogManager.getLogger(OcmschprRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> courseScheduleRulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("DESCRIPTION", new FieldMapper("description")).build();

	
	@Override
	public List<CourseActivities> crsActExecuteQuery(CourseActivities objSearchDao) {
		final String sql = getQuery("OCMSCHPR_CRSACT_FIND_COURSE_ACTIVITIES");
		final RowMapper<CourseActivities> CourseActivitiesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseActivities.class, mMapping);
		return  namedParameterJdbcTemplate
				.query(sql, createParams("crs_acty_id",objSearchDao.getCrsActyId()), CourseActivitiesRowMapper);
		
	}
	
	@Override
	public CourseActivities crsActPostQuery(CourseActivities objSearchDao) {
		final String sql = getQuery("OCMSCHPR_CRSACT_POST_QUERY");
		final RowMapper<CourseActivities> CourseActivitiesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseActivities.class, mMapping);
		return  namedParameterJdbcTemplate
				.queryForObject(sql, createParams("p_crs_acty_id",objSearchDao.getCrsActyId()),CourseActivitiesRowMapper);
		
	}
	
	@Override
	public Integer updatecrsAct(List<CourseActivities> listCrsAct) {
		String sql = getQuery("OCMSCHPR_CRSACT_UPDATE_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CourseActivities courseActivities : listCrsAct) {
			parameters.add(new BeanPropertySqlParameterSource(courseActivities));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public Long getActualSessions(CourseActivities objSearchDao) {
		final String sql = getQuery("OCMSCHPR_CRSACT_ACTUAL_SESSIONS");
		return  namedParameterJdbcTemplate
				.queryForObject(sql, createParams("p_crs_acty_id",objSearchDao.getCrsActyId()), Long.class);
		
	}
	

	@Override
	public List<ProgramServices> getProgramServices(CourseActivities objSearchDao) {
		List<ProgramServices> retList=null;
		final String sql = getQuery("OCMSCHPR_CRSACT_PROGRAM_SERVICES");
		final RowMapper<ProgramServices> programServicesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ProgramServices.class, mMapping);
		try {
			retList=namedParameterJdbcTemplate.query(sql, createParams("p_program_id",objSearchDao.getProgramId()), programServicesRowMapper);
		} catch(Exception e){
			logger.error(e);
		}
		return  retList;
		
	}
	
	
	@Override
	public Long getTotalSessions(CourseActivities objSearchDao) {
		final String sql = getQuery("OCMSCHPR_CRSACT_GET_TPTAL_SESSIONS");
		return  namedParameterJdbcTemplate
				.queryForObject(sql, createParams("p_program_instance_id",objSearchDao.getCrsActyId()), Long.class);
		
	}
	
	@Override
	public String getWeekday(VAcpSchedules objSearchDao) {
		final String sql = getQuery("OCMSCHPR_VACPSCHEDULES_POST_QUERY");
		return  namedParameterJdbcTemplate
				.queryForObject(sql, createParams("schedulDate",objSearchDao.getScheduleDate().toString()), String.class);
		
	}
	
	/**
		 * Fetch the records from database table
		 *
		 * @param objSearchDao VAcpSchedules
		 *
		 * @return List<VAcpSchedules>
		 *
		 * @throws SQLException
		 */
	
	public List<VAcpSchedules> vAcpSchedulesExecuteQuery(VAcpSchedules objSearchDao) {
		 StringBuffer sql = new StringBuffer(getQuery("OCMSCHPR_VACPSCHEDULES_FIND_V_ACP_SCHEDULES"));
		MapSqlParameterSource sqlInParameter = new MapSqlParameterSource();
	
		if(objSearchDao.getProgramInstanceId() != null) {
			sql.append(" WHERE PROGRAM_INSTANCE_ID = :programInstanceId");
			 sqlInParameter.addValue("programInstanceId", objSearchDao.getProgramInstanceId());
		} 
		else if(objSearchDao.getPhaseInstanceId() != null) {
			sql.append(" WHERE PHASE_INSTANCE_ID =:phaseInstanceId");
			 sqlInParameter.addValue("phaseInstanceId", objSearchDao.getPhaseInstanceId());
		}
		
		sql.append(" ORDER BY  SCHEDULE_DATE,START_TIME");
		final RowMapper<VAcpSchedules> VAcpSchedulesRowMapper = Row2BeanRowMapper.makeMapping(sql.toString(), VAcpSchedules.class,
				mMapping);
		return namedParameterJdbcTemplate.query(sql.toString(),sqlInParameter, VAcpSchedulesRowMapper);
		
	}
	
	@Override
	public VAcpSchedules vAcpSchedulesInsertChecking(final Long crsActyId) {
		Map<String, Object> returnObject = null;
		final VAcpSchedules bean = new VAcpSchedules();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_PHASE_INSTANCE_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_PHASE_INSTANCE_DESC", OracleTypes.VARCHAR),
				new SqlOutParameter("P_PHASE_LIST_SEQ", OracleTypes.NUMBER),
				new SqlOutParameter("P_PHASE_SESSION_LENGTH", OracleTypes.NUMBER),
				new SqlOutParameter("P_MODULE_INSTANCE_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_MODULE_INSTANCE_DESC", OracleTypes.VARCHAR),
				new SqlOutParameter("P_MODULE_LIST_SEQ", OracleTypes.NUMBER)};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE_SCHED").withProcedureName("GET_CRS_ACTY_WITH_GAPS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CRS_ACTY_ID", crsActyId);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setPhaseInstanceId(
					returnObject.get("P_PHASE_INSTANCE_ID") != null ? Long.parseLong(String.valueOf(returnObject.get("P_PHASE_INSTANCE_ID")))
							: null);
			bean.setPhaseInstanceDesc(
					returnObject.get("P_PHASE_INSTANCE_DESC") != null ? String.valueOf(returnObject.get("P_PHASE_INSTANCE_DESC"))
							: null);
			bean.setPhaseListSeq(
					returnObject.get("P_PHASE_LIST_SEQ") != null ? Long.parseLong(String.valueOf(returnObject.get("P_PHASE_LIST_SEQ")))
							: null);
			bean.setPhaseSessionLength(
					returnObject.get("P_PHASE_SESSION_LENGTH") != null ? Long.parseLong(String.valueOf(returnObject.get("P_PHASE_SESSION_LENGTH")))
							: null);
			bean.setModuleInstanceId(
					returnObject.get("P_MODULE_INSTANCE_ID") != null ? new BigDecimal(String.valueOf(returnObject.get("P_MODULE_INSTANCE_ID")))
							: null);
			bean.setModuleInstanceDesc(
					returnObject.get("P_MODULE_INSTANCE_DESC") != null ? String.valueOf(returnObject.get("P_MODULE_INSTANCE_DESC"))
							: null);
			bean.setModuleListSeq(
					returnObject.get("P_MODULE_LIST_SEQ") != null ?  new BigDecimal(String.valueOf(returnObject.get("P_MODULE_LIST_SEQ")))
							: null);
			
			if(bean.getPhaseInstanceId() != null) {
				Long val=null;
				final String sql = getQuery("OCMSCHPR_VACPSCHEDULES_GET_SESSION_NO_ONE");
				val = namedParameterJdbcTemplate
					.queryForObject(sql, createParams("p_phase_instance_id",bean.getPhaseInstanceId()), Long.class);
				if(val == null) {
					final String sqlQuery = getQuery("OCMSCHPR_VACPSCHEDULES_GET_SESSION_NO_TWO");
					val = namedParameterJdbcTemplate
						.queryForObject(sqlQuery, createParams("p_crs_acty_id",bean.getPhaseInstanceId()), Long.class)+1;
				}
				
				bean.setSessionNo(val);
			
			}

		} catch (Exception e) {
			logger.error("In method getOffenderDetails", e);
		}
		return bean;
	}
	
	
	@Override
	public CourseActivities vAcpSchedulesValidate(final VAcpSchedules vacp) {
		Map<String, Object> returnObject = null;
		final CourseActivities bean = new CourseActivities();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_PROGRAM_INSTANCE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PHASE_LIST_SEQ", OracleTypes.NUMBER),
				new SqlParameter("P_SESSION_NO", OracleTypes.NUMBER),
				new SqlOutParameter("P_PRIOR_DATE", OracleTypes.DATE),
				new SqlOutParameter("P_NEXT_DATE", OracleTypes.DATE),};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE_SCHED").withProcedureName("GET_SCHEDULE_DATE_LIMITS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_PROGRAM_INSTANCE_ID", vacp.getProgramInstanceId());
		inParamMap.put("P_PHASE_LIST_SEQ", vacp.getPhaseListSeq());
		inParamMap.put("P_SESSION_NO", vacp.getSessionNo());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			bean.setOfferingStartDate(
					returnObject.get("P_PRIOR_DATE") != null ?(Date)returnObject.get("P_PRIOR_DATE"): null);
			bean.setPhaseStartDate(
					returnObject.get("P_NEXT_DATE") != null ?(Date)returnObject.get("P_NEXT_DATE"): null);
			
			

		} catch (Exception e) {
			logger.error("In method getOffenderDetails", e);
		}
		return bean;
	}


	
	public List<CourseScheduleRules> crsScheduleRulExecuteQuery(CourseScheduleRules objSearchDao) {
		final String sql = getQuery("OCMSCHPR_CRSSCHEDULERUL_FIND_COURSE_SCHEDULE_RULES");
		final RowMapper<CourseScheduleRules> CourseScheduleRulesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				CourseScheduleRules.class, courseScheduleRulesMapping);
		return namedParameterJdbcTemplate
				.query(sql, createParams("crs_acty_id",objSearchDao.getCrsActyId()), CourseScheduleRulesRowMapper);
		 
	}
	 
	
	public Integer crsScheduleRulInsertCourseScheduleRules(final List<CourseScheduleRules> lstCourseScheduleRules) {
		String sql = getQuery("OCMSCHPR_CRSSCHEDULERUL_INSERT_COURSE_SCHEDULE_RULES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CourseScheduleRules courseScheduleRules : lstCourseScheduleRules) {
			parameters.add(new BeanPropertySqlParameterSource(courseScheduleRules));
		}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if(returnArray.length > 0) {
			return 1;
		} else {
			return 0;
		}

	}
	  
	 
	
		public Integer crsScheduleRulUpdateCourseScheduleRules(final List<CourseScheduleRules> lstCourseScheduleRules) {
			String sql = getQuery("OCMSCHPR_CRSSCHEDULERUL_UPDATE_COURSE_SCHEDULE_RULES");
			int[] returnArray = new int[] {};
			List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			for (CourseScheduleRules courseScheduleRules : lstCourseScheduleRules) {
				parameters.add(new BeanPropertySqlParameterSource(courseScheduleRules));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (returnArray.length > 0) {
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
			String sql = getQuery("OCMSCHPR_CRSSCHEDULERUL_DELETE_COURSE_SCHEDULE_RULES");
			int[] returnArray = new int[] {};
			List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			for (CourseScheduleRules courseScheduleRules : lstCourseScheduleRules) {
				parameters.add(new BeanPropertySqlParameterSource(courseScheduleRules));
			}
			try {
				String tableName = "COURSE_SCHEDULE_RULES";
				String whereClause = "COURSE_SCHEDULE_RULE_ID=:courseScheduleRuleId";
				batchUpdatePreDeletedRows(tableName, whereClause , parameters);
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " in method crsScheduleRulDeleteCourseScheduleRules", e);
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (returnArray.length > 0) {
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
	
		public List<ReferenceCodes> rgRemainingRecordGroup(Integer crsActyId,Integer  lastListSeq) {
			final String sql = getQuery("OCMSCHPR_FIND_RGREMAINING");
			final RowMapper<ReferenceCodes> MRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
			return namedParameterJdbcTemplate.query(sql, createParams("crs_acty_id",crsActyId ,"last_list_seq",lastListSeq), MRowMapper);
			
		}
	 /**
		 * This method is execute a sql query when trigger event is called
		 * 
		 * crsActOnCheckDeleteMaster
		 *
		 * @param params
		 *
		 */
	@Override
	public long getCourseScheduleRuleId() {
		final String sql = getQuery("OCMOFACC_COURSESCHEDULERULE_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

	@Override
	public Date insertVAcpSchedules(VAcpSchedules vacpSchedules) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SCHEDULE_DATE", OracleTypes.DATE),
				new SqlParameter("P_START_TIME", OracleTypes.DATE),
				new SqlParameter("P_END_TIME", OracleTypes.DATE),
				new SqlParameter("P_SESSION_NO", OracleTypes.NUMBER)};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE_SCHED").withFunctionName("CREATE_COURSE_SCHEDULE").declareParameters(sqlParameters);
		inParamMap.put("P_CRS_ACTY_ID", (vacpSchedules.getModuleInstanceId() != null) ? vacpSchedules.getModuleInstanceId() : vacpSchedules.getPhaseInstanceId());
		inParamMap.put("P_SCHEDULE_DATE", vacpSchedules.getScheduleDate());
		inParamMap.put("P_START_TIME", vacpSchedules.getStartTime());
		inParamMap.put("P_END_TIME", vacpSchedules.getEndTime());
		inParamMap.put("P_SESSION_NO", vacpSchedules.getSessionNo());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		return simpleJDBCCall.executeFunction(Date.class, inParameter);
	}

	@Override
	public Date updateVAcpSchedules(VAcpSchedules vacpSchedules) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CRS_SCH_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SCHEDULE_DATE", OracleTypes.DATE),
				new SqlParameter("P_START_TIME", OracleTypes.DATE),
				new SqlParameter("P_END_TIME", OracleTypes.DATE),
				new SqlParameter("P_SCHEDULE_STATUS", OracleTypes.VARCHAR),
				new SqlParameter("P_CATCH_UP_SESSION_FLAG", OracleTypes.VARCHAR)};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE_SCHED").withFunctionName("CHANGE_COURSE_SCHEDULE").declareParameters(sqlParameters);
		inParamMap.put("P_CRS_ACTY_ID", (vacpSchedules.getModuleInstanceId() != null) ? vacpSchedules.getModuleInstanceId() : vacpSchedules.getPhaseInstanceId());
		inParamMap.put("P_CRS_SCH_ID", vacpSchedules.getCrsSchId());
		inParamMap.put("P_SCHEDULE_DATE", vacpSchedules.getScheduleDate());
		inParamMap.put("P_START_TIME", vacpSchedules.getStartTime());
		inParamMap.put("P_END_TIME", vacpSchedules.getEndTime());
		inParamMap.put("P_SCHEDULE_STATUS", vacpSchedules.getScheduleStatus());
		inParamMap.put("P_CATCH_UP_SESSION_FLAG", vacpSchedules.getCatchUpSessionFlag());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		return simpleJDBCCall.executeFunction(Date.class, inParameter);
	}

	@Override
	public void updateCourseAttendanceDates(VAcpSchedules vacp) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CRS_SCH_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_DATE", OracleTypes.DATE),
				new SqlParameter("P_START_TIME", OracleTypes.DATE),
				new SqlParameter("P_END_TIME", OracleTypes.DATE),};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("UPDATE_COURSE_ATTENDANCE_DATES")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CRS_SCH_ID", vacp.getCrsSchId());
		inParamMap.put("P_EVENT_DATE", vacp.getScheduleDate());
		inParamMap.put("P_START_TIME", vacp.getStartTime());
		inParamMap.put("P_END_TIME", vacp.getEndTime());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		simpleJDBCCall.execute(inParameter);
		
	}

	@Override
	public void cancelAttendForSchedule(VAcpSchedules vacp) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CRS_SCH_ID", OracleTypes.NUMBER),};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_PROGRAMMES").withProcedureName("CANCEL_ATTEND_FOR_SCHEDULE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CRS_SCH_ID", vacp.getCrsSchId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		simpleJDBCCall.execute(inParameter);
		
	}
	

	@Override
	public void vAcpSchedulesDelete(VAcpSchedules searchBean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_PROGRAM_INSTANCE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PHASE_INSTANCE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CRS_SCH_ID", OracleTypes.NUMBER), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE_SCHED").withProcedureName("CS_ACP_CLEAR_SESSIONS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_PROGRAM_INSTANCE_ID", searchBean.getProgramInstanceId());
		inParamMap.put("P_PHASE_INSTANCE_ID", searchBean.getPhaseInstanceId());
		inParamMap.put("P_CRS_SCH_ID", searchBean.getCrsSchId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		simpleJDBCCall.execute(inParameter);

	}
	
	@Override
	public Integer updateCrsActyChecksum(Long programInstanceId, String userName) {
		String sql = getQuery("OCMSCHPR_VACPSCHEDULES_UPDATE_CRS_ACTY_CHECKSUM");
		return namedParameterJdbcTemplate.update(sql, createParams("p_crs_acty_id",programInstanceId,"modifyUserId",userName));
		

	}
	
	public Integer getCrsActyChecksum(Long programInstanceId) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER),};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE").withFunctionName("GET_CRS_ACTY_CHECKSUM").declareParameters(sqlParameters);
		inParamMap.put("P_CRS_ACTY_ID", programInstanceId);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		return simpleJDBCCall.executeFunction(BigDecimal.class, inParameter).intValue();
		
		
	}

	@Override
	public Boolean chkAllocationExists(CourseActivities searchBean) {
		 Boolean retVal=null;
		 Integer val=null;
		final String sql = getQuery("OCMSCHPR_VACPSCHEDULES_CHKALLOCATIONEXISTS");
		try {
			val = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_crs_acty_id",searchBean.getCrsActyId(),"p_crs_class",searchBean.getCourseClass()), Integer.class);
		} catch( Exception e) {
			logger.error(e);
			
		}
		if(val>0) {
			retVal=true;
		} else {
			retVal=false;
		}
		
		return retVal;
	}
	
	@Override
	public Date getLastProgramScheduleDate(CourseActivities obj) {
		Date date=null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_PROGRAM_INSTANCE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PHASE_LIST_SEQ", OracleTypes.NUMBER),
				new SqlOutParameter("RETURN_VALUE", OracleTypes.DATE)};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE_SCHED").withFunctionName("GET_LAST_PROGRAM_SCHEDULE_DATE").declareParameters(sqlParameters);
		inParamMap.put("P_PROGRAM_INSTANCE_ID", obj.getProgramIstanceId());
		inParamMap.put("P_PHASE_LIST_SEQ", (obj.getCourseClass().equals("CRS_PH"))?obj.getListSeq():null);
		
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			date = (Date)returnObject.get("RETURN_VALUE");
		} catch (Exception e) {
			logger.error(e);

		}
		return date;
	}
	
	@Override
	public Date getNextPhaseScheduleDate (CourseActivities obj) {
		Date date=null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_PROGRAM_INSTANCE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PHASE_LIST_SEQ", OracleTypes.NUMBER),
				new SqlOutParameter("RETURN_VALUE", OracleTypes.DATE)};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE_SCHED").withFunctionName("GET_NEXT_PHASE_SCHEDULE_DATE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_PROGRAM_INSTANCE_ID", obj.getProgramIstanceId());
		inParamMap.put("P_PHASE_LIST_SEQ", obj.getListSeq());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			date = (returnObject.get("RETURN_VALUE") != null ) ? (Date)returnObject.get("RETURN_VALUE") : null;
		} catch (Exception e) {
			logger.error(e);

		}
		return date;
	}
		

	@Override
	public CourseActivities csBuildAcpSchedule(final CourseActivities obj) {
		Map<String, Object> returnObject = null;
		final CourseActivities bean = new CourseActivities();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER),
				new SqlParameter("P_START_DATE", OracleTypes.DATE),
				new SqlParameter("P_LIST_SEQ_END", OracleTypes.NUMBER),
				new SqlParameter("P_WEEKS_BETWEEN", OracleTypes.NUMBER),
				new SqlOutParameter("P_NO_SCHEDULES", OracleTypes.NUMBER),
				new SqlOutParameter("P_FROM_DATE", OracleTypes.DATE),
				new SqlOutParameter("P_TO_DATE", OracleTypes.DATE), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE_SCHED").withProcedureName("CS_BUILD_ACP_SCHEDULE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CRS_ACTY_ID", obj.getCrsActyId());
		inParamMap.put("P_START_DATE", obj.getStartDate());
		inParamMap.put("P_LIST_SEQ_END", obj.getUptoListSeq());
		inParamMap.put("P_WEEKS_BETWEEN", (obj.getWeeks() != null) ? obj.getWeeks() : 0);

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		returnObject = simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);

		bean.setNoOfSessions(
				returnObject.get("P_NO_SCHEDULES") != null ? new Long(String.valueOf(returnObject.get("P_NO_SCHEDULES")))
						: null);
		bean.setOfferingStartDate(
				returnObject.get("P_FROM_DATE") != null ? (Date)(returnObject.get("P_FROM_DATE")) : null);
		bean.setOfferingEndDate(
				returnObject.get("P_TO_DATE") != null ? (Date)(returnObject.get("P_TO_DATE")) : null);

		return bean;
	}
	
	@Override
	public CourseActivities lastCrsActyBuilt(final CourseActivities obj) {
		Map<String, Object> returnObject = null;
		final CourseActivities bean = new CourseActivities();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_PARENT_CRS_ACTY_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_COURSE_CLASS", OracleTypes.VARCHAR),
				new SqlOutParameter("P_DESCRIPTION", OracleTypes.VARCHAR),
				new SqlOutParameter("P_LIST_SEQ", OracleTypes.NUMBER),};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE_SCHED").withProcedureName("LAST_CRS_ACTY_BUILT")
				.declareParameters(sqlParameters);
		inParamMap.put("p_parent_crs_acty_id", obj.getCrsActyId());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			
			bean.setCrsActyId(returnObject.get("P_CRS_ACTY_ID") != null ? new Long(String.valueOf(returnObject.get("P_CRS_ACTY_ID")))
					: null);
			bean.setCourseClass(returnObject.get("P_COURSE_CLASS") != null ? String.valueOf(returnObject.get("P_COURSE_CLASS"))
							: null);
			bean.setLastDescription(
					returnObject.get("P_DESCRIPTION") != null ? String.valueOf(returnObject.get("P_DESCRIPTION"))
							: null);
			bean.setLastListSeq(
					returnObject.get("P_LIST_SEQ") != null ? new Long(String.valueOf(returnObject.get("P_LIST_SEQ")))
							: null);
			
			

		} catch (Exception e) {
			logger.error(e);
		}
		return bean;
	}
	
	@Override
	public Long noSessionsDone(CourseActivities obj) {
		Long sessions=null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER),
				new SqlOutParameter("RETURN_VALUE", OracleTypes.NUMBER)};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE_SCHED").withFunctionName("NO_SESSIONS_DONE").declareParameters(sqlParameters);
		inParamMap.put("P_CRS_ACTY_ID", obj.getCrsActyId());
		
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		
		
		try {
			returnObject= simpleJDBCCall.execute(inParameter);
			sessions=new Long(String.valueOf(returnObject.get("RETURN_VALUE")));
		} catch (Exception e) {
			logger.error( e);
			
		}
		return sessions;
	}
	
	@Override
	public Boolean chkAnyToBuild(Long crsActyId,Long lastListSeq) {
		 Boolean retVal=null;
		 Integer val=null;
		final String sql = getQuery("OCMSCHPR_BUILD_BLOCK_CHK_ANY_TO_BUILD");
		try {
			val = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_parent_crs_acty_id",crsActyId,"p_last_list_seq",lastListSeq), Integer.class);
		} catch( Exception e) {
			logger.error(e);
		}
		if(val > 0) {
			retVal=true;
		} else {
			retVal=false;
		}
		return retVal;
	}
	
	
	@Override
	public Date getPriorScheduleDate(CourseActivities obj) {
		Date date=null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[4];
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_PROGRAM_INSTANCE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_PHASE_LIST_SEQ", OracleTypes.NUMBER),
				new SqlParameter("P_SESSION_NO", OracleTypes.NUMBER),
				new SqlOutParameter("RETURN_VALUE", OracleTypes.DATE)};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE_SCHED").withFunctionName("GET_PRIOR_SCHEDULE_DATE").declareParameters(sqlParameters);
		inParamMap.put("P_PROGRAM_INSTANCE_ID", obj.getProgramIstanceId());
		inParamMap.put("P_PHASE_LIST_SEQ", obj.getProgramId());
		inParamMap.put("P_SESSION_NO", obj.getNoOfSessions());
		
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		
		try {
			returnObject= simpleJDBCCall.execute(inParameter);
			date = (returnObject.get("RETURN_VALUE") != null ) ? (Date)returnObject.get("RETURN_VALUE") : null;
		} catch (Exception e) {
			logger.error( e);
			
		}
		return date;
		
	}

	@Override
	public CourseActivities csAcpReschedule(final CourseActivities obj) {
		Map<String, Object> returnObject = null;
		final CourseActivities bean = new CourseActivities();
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[6];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CRS_ACTY_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CRS_SCH_ID", OracleTypes.NUMBER),
				new SqlParameter("P_START_DATE", OracleTypes.DATE),
				new SqlOutParameter("P_FROM_DATE", OracleTypes.DATE),
				new SqlOutParameter("P_TO_DATE", OracleTypes.DATE), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SERVICE_SCHED").withProcedureName("CS_ACP_RESCHEDULE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CRS_ACTY_ID", obj.getCrsActyId());
		inParamMap.put("P_CRS_SCH_ID", obj.getNbtCrsSchId());
		inParamMap.put("P_START_DATE", obj.getNbtreScheduleDate());

		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		returnObject = simpleJDBCCall.execute(inParameter);

		bean.setOfferingStartDate(
				returnObject.get("P_FROM_DATE") != null ? (Date)returnObject.get("P_FROM_DATE")
						: null);
		bean.setOfferingEndDate(
				returnObject.get("P_TO_DATE") != null ? (Date)returnObject.get("P_TO_DATE") : null);

		return bean;
	}

	@Override
	public Long getSessionNo(Long phaseInstanceId) {
		Long val=null;
		try {
			final String sql = getQuery("OCMSCHPR_VACPSCHEDULES_GET_SESSION_NO_ONE");
			val = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_phase_instance_id", phaseInstanceId),
					Long.class);
		} catch (Exception e) {
			logger.error("getSessionNo "+e);
		}
		if(val == null) {
			try {
				final String sqlQuery = getQuery("OCMSCHPR_VACPSCHEDULES_GET_SESSION_NO_TWO");
				val = namedParameterJdbcTemplate.queryForObject(sqlQuery,
						createParams("p_crs_acty_id", phaseInstanceId), Long.class) + 1;
			} catch (Exception e) {
				logger.error("getSessionNo " + e);
			}
		}
		
		return val;
	}
	
	
	
	


	
}
