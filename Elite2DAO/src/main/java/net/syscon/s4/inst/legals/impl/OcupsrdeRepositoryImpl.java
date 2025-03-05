package net.syscon.s4.inst.legals.impl;

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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.OcupsrdeRepository;
import net.syscon.s4.inst.legals.beans.AssignReport;
import net.syscon.s4.inst.legals.beans.CourtReport;
import net.syscon.s4.inst.legals.beans.OrderType;
import net.syscon.s4.inst.legals.beans.StaffDetails;
import net.syscon.s4.inst.legals.beans.TeamResponsible;
import oracle.jdbc.OracleTypes;

@Repository
public class OcupsrdeRepositoryImpl extends RepositoryBase implements OcupsrdeRepository{

	private static Logger logger = LogManager.getLogger(OcupsrdeRepositoryImpl.class);
	final Map<String, FieldMapper> courtReportMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORDER_ID", 					new FieldMapper("orderId"))
			.put("CASE_ID", 					new FieldMapper("caseId"))
			.put("EVENT_ID", 					new FieldMapper("eventId"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.put("WORKFLOW_ID", 				new FieldMapper("workflowId"))
			.put("COURT_SERIOUSNESS_LEVEL", 	new FieldMapper("courtSeriousnessLevel"))
			.put("ORDER_TYPE", 					new FieldMapper("reportType"))
			.put("ISSUING_AGY_LOC_ID",  		new FieldMapper("agyLocId"))
			.put("REQUEST_DATE", 				new FieldMapper("dateRequested"))
			.put("COURT_DATE", 					new FieldMapper("courtDate"))
			.put("DUE_DATE", 					new FieldMapper("dueDate"))
			.put("COMPLETE_DATE",   			new FieldMapper("dateOfCompletion"))
			.put("ORDER_STATUS", 				new FieldMapper("status"))
			.put("NON_REPORT_FLAG", 			new FieldMapper("nr"))
			.put("COMMENT_TEXT", 				new FieldMapper("comments"))
			.build();

	final Map<String, FieldMapper> orderTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 				new FieldMapper("description"))			
			.put("REPORT_TYPE",  			    new FieldMapper("code")).build();
	
	final Map<String, FieldMapper> areaTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 				new FieldMapper("description"))			
			.put("CODE",  			        	new FieldMapper("code")).build();
	
	final Map<String, FieldMapper> areaMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 				new FieldMapper("description"))			
			.put("AREA_CODE",  			        new FieldMapper("code")).build();
	
	final Map<String, FieldMapper> teamResponsibleMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 				new FieldMapper("description"))			
			.put("CODE",  			        	new FieldMapper("code")).build();
	
	final Map<String, FieldMapper> staffDetailsLovMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_NAME", 					new FieldMapper("description"))
			.put("TEAM_MEMBER_ID", 				new FieldMapper("code")).build();
	
	final Map<String, FieldMapper> staffMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_NAME", 					new FieldMapper("lastName"))
			.put("Position", 					new FieldMapper("position"))
			.put("Role", 						new FieldMapper("role"))
			.put("From_Date", 					new FieldMapper("fromDate"))
			.build();
	
	
	@Override
	public List<CourtReport> populateReport(Integer eventId) {
		List<CourtReport> courtReportList = new ArrayList<CourtReport>();
		final String sql = getQuery("DISPLAY_COURT_REPORT");
		final RowMapper<CourtReport> courtReportDataRowMapper = Row2BeanRowMapper.makeMapping(sql,CourtReport.class, courtReportMapping);
		try {
			courtReportList=  namedParameterJdbcTemplate.query(sql,createParams("eventId" ,eventId),courtReportDataRowMapper);
			} catch (Exception e) {
				logger.error("populateReport"+e.getMessage());
		}
		return courtReportList;
	}

	@Override
	public List<OrderType> populateReportType() {
		List<OrderType> orderList = new ArrayList<OrderType>();
		final String sql = getQuery("LOV_REPORT_TYPE");
		final RowMapper<OrderType> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,OrderType.class, orderTypeMapping);
		try {
			orderList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
		}
		return orderList;
	} 
	
	@Override
	public List<AssignReport> populateAssignReport(CourtReport courtReport) {
		List<AssignReport> assignReportList = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_OFF_BOOK_ID", 	  	  OracleTypes.NUMBER),
				new SqlParameter("P_WORKFLOW_ID", 		  OracleTypes.NUMBER),
				new SqlOutParameter("P_OFF_MSG_REC_NEW", OracleTypes.CURSOR)
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADM")
				.withProcedureName("GET_REPORT_DETAILS_NEW")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFF_BOOK_ID", courtReport.getOffenderBookId());    
		inParamMap.put("P_WORKFLOW_ID", courtReport.getWorkflowId());
		inParamMap.put("P_OFF_MSG_REC_NEW", OracleTypes.CURSOR);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			assignReportList = new ArrayList<>();
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("P_OFF_MSG_REC_NEW");
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> childMap = list.get(i);
				AssignReport bean = new AssignReport();
				bean.setFunction(String.valueOf(childMap.get("FUNCTION_TYPE")));
				bean.setTeamResponsible(String.valueOf(childMap.get("TEAM_CODE")));
				bean.setTeamName(String.valueOf(childMap.get("DESCRIPTION")));
				bean.setLastName(String.valueOf(childMap.get("LAST_NAME")));
				bean.setFirstName(String.valueOf(childMap.get("FIRST_NAME")));
				assignReportList.add(bean);
			}

		} catch (Exception e) {
			logger.error("populateOffensesData"+e.getMessage());
			}
		return assignReportList;
	}

	@Override
	public Object setPostQueryReportType(String reportType) {
		final String sql = getQuery("SET_POSTQUERY_REPORTTYPE");
		final Object returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams("REPORTTYPE",reportType),Object.class);
		return returnObj;
		
	}
	
	@Override
	public Object setPostQueryAgyDesc(String agylocId) {
		final String sql = getQuery("SET_POSTQUERY_AGYLOCDESC");
		final Object returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams("AGENCYLOCID",agylocId),Object.class);
		return returnObj;
		
	}
	
	@Override
	public Object setPostQueryCourtSeriousnessLevel(String courtSeriousnessLevel) {
		final String sql = getQuery("SET_POSTQUERY_COURTSERIOUSNESSLEVEL");
		final Object returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams("courtSeriousnessLevel",courtSeriousnessLevel),Object.class);
		return returnObj;
		
	}
	
	@Override
	public Object setPostQueryReportstatus(String reportstatus) {
		final String sql = getQuery("SET_POSTQUERY_REPORTSTATUS");
		final Object returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams("REPORTSTATUS",reportstatus),Object.class);
		return returnObj;
		
	}
	
	@Override
	public String getStatusDesc(String reportStatus) {
		//String statusDesc=null;
		final String sql = getQuery("GET_PREINSERT_REPORTSTATUS");
		final String returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams("REPORTSTATUS",reportStatus),String.class);		
		return returnObj;
		
	}
	
	@Override
	public List<OrderType> populateFunctionType() {
		List<OrderType> orderList = new ArrayList<OrderType>();
		final String sql = getQuery("LOV_FUNCTION_TYPE");
		final RowMapper<OrderType> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,OrderType.class, orderTypeMapping);
		try {
			orderList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
		}
		return orderList;
	}
	
	@Override
	public List<OrderType> populateAreaType() {
		List<OrderType> orderList = new ArrayList<OrderType>();
		final String sql = getQuery("LOV_AREA_TYPE");
		final RowMapper<OrderType> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,OrderType.class, orderTypeMapping);
		try {
			orderList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
		}
		return orderList;
	}
	
	@Override
	public List<OrderType> populateArea(String areaType) {
		List<OrderType> orderList = new ArrayList<OrderType>();
		final String sql = getQuery("LOV_AREA");
		final RowMapper<OrderType> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,OrderType.class, areaMapping);
		try {
			orderList=  namedParameterJdbcTemplate.query(sql,createParams("AREATYPE",areaType),referenceCodeRowMapper);
			} catch (Exception e) {
		}
		return orderList;
	}
	
	@Override
	public List<TeamResponsible> populateTeamResponsible(String areaCode, String functionType) {
		List<TeamResponsible> teamList = new ArrayList<TeamResponsible>();
		final String sql = getQuery("LOV_TEAM_RESPONSIBLE");
		final RowMapper<TeamResponsible> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,TeamResponsible.class, teamResponsibleMapping);
		try {
			teamList=  namedParameterJdbcTemplate.query(sql,createParams("AREACODE",areaCode,"FUNCTIONTYPE",functionType),referenceCodeRowMapper);
			} catch (Exception e) {
		}
		return teamList;
	}
	
	@Override
	public List<StaffDetails> populateStaffDetails(Integer teamId) {
		List<StaffDetails> staffList = new ArrayList<StaffDetails>();
		final String sql = getQuery("LOV_STAFF_DETAILS");
		final RowMapper<StaffDetails> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,StaffDetails.class, staffMapping);
		try {
			staffList=  namedParameterJdbcTemplate.query(sql,createParams("TEAMID",teamId),referenceCodeRowMapper);
			} catch (Exception e) {
		}
		
		return staffList;
	}
	
	@Override
	public List<TeamResponsible> populateStaffLovDetails(Integer teamId) {
		List<TeamResponsible> staffList = new ArrayList<TeamResponsible>();
		final String sql = getQuery("LOV_STAFF_DETAILS_ALTER");
		final RowMapper<TeamResponsible> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,TeamResponsible.class, staffDetailsLovMapping);
		try {
			staffList=  namedParameterJdbcTemplate.query(sql,createParams("TEAMID",teamId),referenceCodeRowMapper);
			} catch (Exception e) {
		}
		
		return staffList;
	}
	
	
	
	@Override
	public Integer getPreInsertOrderId() {
		Integer returnObj = null;
		final String sql = getQuery("PREINSERT_ORDER_ID");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams(), Integer.class);
		return returnObj;
	}

	@Override
	public Integer insertNewCourtReport(List<CourtReport> insertedRecord) {
		int[] returnArray = new int[] {};
		final String sql = getQuery("INSERT_COURT_REPORT");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtReport list : insertedRecord) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql,parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertNewCourtReport : ", e);
			}
		if(insertedRecord.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
	}

	@Override
	public Integer updateCourtReport(List<CourtReport> updatedList) {
		final String sql = getQuery("UPDATE_COURT_REPORT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CourtReport list : updatedList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			} catch (Exception e) {
			logger.error("updateCourtReport:", e);
			}
		if(updatedList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
	}

	@Override
	public Integer deleteCourtReport(List<CourtReport> deletedList) {
		final String sql = getQuery("DELETE_COURT_REPORT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (CourtReport list : deletedList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try{
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (EmptyResultDataAccessException e){
			logger.error("deleteCourtReport", e);
		}
		if (deletedList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Boolean isReportExist(CourtReport courtReport) {
		final String sql = getQuery("IS_REPORT_EXIST");
		boolean isExist=false;
		Long orderId=null;
		try {
			 orderId = namedParameterJdbcTemplate.queryForObject(sql,createParams(
					"p_off_book_id",courtReport.getOffenderBookId(),
					"p_court_date",new java.sql.Date(courtReport.getCourtDate().getTime()),
					"p_order_type",courtReport.getReportType(),
					"p_iss_agy_loc_id",courtReport.getAgyLocId(),
					"p_request_date",new java.sql.Date(courtReport.getDateRequested().getTime()),
					"p_due_date",new java.sql.Date(courtReport.getDueDate().getTime()),
					"p_event_id",courtReport.getEventId(),
					"p_case_id",courtReport.getCaseId()),Long.class);
			
		}catch(EmptyResultDataAccessException e) {
			isExist=false;
		} catch (Exception e) {
			logger.error("isReportExist :"+e.getMessage());
		} 
		if(orderId==0) {
			isExist=false;
		}else {
			isExist=true;
		}
		return isExist;
	}
	
	@Override
	public Integer create_team_assign_hty_new(CourtReport courtReportAssign) {
		//int[] returnArray = new int[] {};
		int liReturn=1;
		final CourtReport bean = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[20];		
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("p_off_book_id", OracleTypes.NUMBER),
				new SqlParameter("p_team_id", OracleTypes.NUMBER),
				new SqlParameter("p_team_member_id", OracleTypes.NUMBER),
				new SqlParameter("p_message_text", OracleTypes.VARCHAR),
				new SqlParameter("p_request_date", OracleTypes.DATE),
				new SqlParameter("p_due_date", OracleTypes.DATE),
				new SqlParameter("p_order_id", OracleTypes.NUMBER),
				new SqlParameter("p_fun_type", OracleTypes.VARCHAR),
				};	
		
		
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
					.withCatalogName("TAG_WORKFLOW_ADM").withProcedureName("CREATE_TEAM_ASSIGN_HTY_NEW").declareParameters(sqlParameters);
			inParamMap.put("p_off_book_id", 	courtReportAssign.getOffenderBookId());    
			inParamMap.put("p_team_id", 		courtReportAssign.getTeamId());
			inParamMap.put("p_team_member_id",  courtReportAssign.getTeamMemberId());    
			inParamMap.put("p_message_text", 	courtReportAssign.getCommentText());
			inParamMap.put("p_request_date", 	new java.sql.Date(courtReportAssign.getDateRequested().getTime()));    
			inParamMap.put("p_due_date", 		new java.sql.Date(courtReportAssign.getDueDate().getTime()));
			inParamMap.put("p_order_id", 		courtReportAssign.getOrderId());    
			inParamMap.put("p_fun_type", 		courtReportAssign.getFunctionType());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			} catch (Exception e) {
			liReturn=0;	
			logger.error("create_team_assign_hty_new :"+e.getMessage());
			}
		
		return liReturn;
		}
	
	@Override
	public Integer getStaffId(Integer teamMemberId) {
		Integer staffId = null;
		final String sql = getQuery("GET_STAFF_ID");
		try {
			staffId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams( "teamMemberId", teamMemberId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return staffId;
	}

	@Override
	public Integer getWorkId() {
		Integer workId = null;
		final String sql = getQuery("GET_WORK_ID");
		try {
			workId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams( "workFlowType","TASK" ,"workType", "REPORT","workSubType", "CRTREPREQ"), Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return workId;
	}
	
}
