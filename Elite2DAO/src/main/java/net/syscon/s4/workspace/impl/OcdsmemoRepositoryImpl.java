package net.syscon.s4.workspace.impl;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jdbc.support.oracle.SqlArrayValue;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.im.beans.VPimsNameSearch;
import net.syscon.s4.inst.legals.beans.StaffDetails;
import net.syscon.s4.workspace.OcdsmemoRepository;
import oracle.jdbc.OracleTypes;
/**
 * Class OcdsmemoRepositoryImpl
 */
@Repository
public class OcdsmemoRepositoryImpl extends RepositoryBase implements OcdsmemoRepository{


/**
* Logger object used to print the log in the file
*/
private static Logger logger =LogManager.getLogger(OcdsmemoRepositoryImpl.class.getName());
private static final String ONE = "1";
private static final String TWO = "2";
private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("WORK_ID", 						new FieldMapper("workId"))
.put("WORK_SUB_TYPE", 						new FieldMapper("workSubType"))
.build();
private final Map<String, FieldMapper> vHeaderBlockMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("WORK_ID", 						new FieldMapper("workId"))
.put("WORK_SUB_TYPE", 						new FieldMapper("workSubType"))
.build();
private final Map<String, FieldMapper> vPimsNameSearchMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("WORK_ID", 						new FieldMapper("workId"))
.put("WORK_SUB_TYPE", 						new FieldMapper("workSubType"))
.build();
private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("STAFF_ID", 							new FieldMapper("staffId"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("CODE",                        new FieldMapper("code"))
.put("WORK_ID", 						new FieldMapper("workId"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("LAST_NAME", 						new FieldMapper("lastName"))
.put("CODE", new FieldMapper("code"))
.build();

/**
 * Creates new OcdsmemoRepositoryImpl class Object 
 */
public OcdsmemoRepositoryImpl() {
	//OcdsmemoRepositoryImpl
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> rgWorkTypeRecordGroup(final String caseloadType)  {
 	final String sql = getQuery("OCDSMEMO_FIND_RGWORKTYPE");
 	final RowMapper<ReferenceCodes> MRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams("caseloadType", caseloadType),MRowMapper);
  	} catch (final EmptyResultDataAccessException e) {
  		logger.error("Exeception in rgWorkTypeRecordGroup", e);
  		return Collections.EMPTY_LIST;
	}
}
/**
* Used to capture results from select query
* @return List<MM> 
*/
public List<ReferenceCodes> rgWorkSubTypeRecordGroup(final String workType,final String caseloadType)  {
 	final String sql = getQuery("OCDSMEMO_FIND_RGWORKSUBTYPE");
 	final RowMapper<ReferenceCodes> MMRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, staffMembersMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams("workType", workType,"caseloadType", caseloadType),MMRowMapper);
  	} catch (final EmptyResultDataAccessException e) {
  		logger.error("Exeception in rgWorkSubTypeRecordGroup", e);
  		return Collections.EMPTY_LIST;  
	}
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> rgSeverityRecordGroup()  {
 	final String sql = getQuery("OCDSMEMO_FIND_RGSEVERITY");
 	final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (final EmptyResultDataAccessException e) {
  		logger.error("Exeception in rgSeverityRecordGroup", e);
  		return Collections.EMPTY_LIST;  
	}
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<StaffMembers> rgStaffRecordGroup()  {
 	final String sql = getQuery("OCDSMEMO_FIND_RGSTAFF");
 	final RowMapper<StaffMembers> mRowMapper = Row2BeanRowMapper.makeMapping(sql,StaffMembers.class, staffMembersMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (final EmptyResultDataAccessException e) {
  		logger.error("Exeception in rgStaffRecordGroup", e);
  		return Collections.EMPTY_LIST; 
	}
}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean)  {
		final String sql = getQuery("OCDSMEMO_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,OmsModules.class,  omsModulesMapping);
		final ArrayList<OmsModules> returnList = (ArrayList<OmsModules>)namedParameterJdbcTemplate.query(sql, createParams(paramBean), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getOffDetailsGET_OFF_DETAILS
	 *
	 * @param params
	 *
	 */
	public List<VHeaderBlock> getOffDetails(final VHeaderBlock paramBean)  {
		final String sql = getQuery("OCDSMEMO_GET_OFF_DETAILS_GET_OFF_DETAILS");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,VHeaderBlock.class,  vHeaderBlockMapping);
		final ArrayList<VHeaderBlock> returnList = (ArrayList<VHeaderBlock>)namedParameterJdbcTemplate.query(sql, createParams(paramBean), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getOffDetailsGET_OFF_DETAILS
	 *
	 * @param params
	 *
	 */
	public List<VPimsNameSearch> getOffDetails(final VPimsNameSearch paramBean)  {
		final String sql = getQuery("OCDSMEMO_GET_OFF_DETAILS_GET_OFF_DETAILS");
		final RowMapper<VPimsNameSearch> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,VPimsNameSearch.class,  vPimsNameSearchMapping);
		final ArrayList<VPimsNameSearch> returnList = (ArrayList<VPimsNameSearch>)namedParameterJdbcTemplate.query(sql, createParams(paramBean), columnRowMapper);
		return returnList;
	}
	@Override
	public String staffMemoComitt(final StaffMembers newMemoModel) {
		Map<String, Object> returnObject = null;
		String code = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_WORKFLOW_TYPE", Types.VARCHAR),
				new SqlParameter("P_WORK_ID", Types.NUMERIC),
				new SqlParameter("P_ACKNOWLEDGEMENT_REQUIRED", Types.VARCHAR),
				new SqlParameter("P_ACKNOWLEDGEMENT_SUBJECT", Types.VARCHAR),
				new SqlParameter("P_MESSAGE_TEXT", Types.VARCHAR),
		};

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("tag_workflow_adm").withProcedureName("validate_staff_message")
				.declareParameters(sqlParameters);
		inParamMap.put("P_WORKFLOW_TYPE", "MEMO");
		inParamMap.put("P_WORK_ID", newMemoModel.getWorkId());
		inParamMap.put("P_ACKNOWLEDGEMENT_REQUIRED", newMemoModel.isAcknowledgementFlag()?'Y':'N');
		inParamMap.put("P_ACKNOWLEDGEMENT_SUBJECT", newMemoModel.getAcknowledgementSubject());
		inParamMap.put("P_MESSAGE_TEXT", newMemoModel.getWorkMessage());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject=simpleJDBCCall.execute(inParameter);
			 code =  ONE;
			 
		} catch (final Exception e) {
			logger.error("Exception :", e);
			code = TWO;
		}
		return code;
	}
	@Override
	public String getStaffMessage(final StaffMembers newMemoModel) {
		 String code = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		List<Integer> teamList=new ArrayList<>();
		List<Integer> staffList=new ArrayList<>();
		 try {
		for (Teams obj:newMemoModel.getTeamList()) {
			teamList.add(obj.getTeamId());
		}
		for (StaffDetails obj:newMemoModel.getStaffList()) {
			staffList.add(obj.getStaffId());
		}
		Integer[] teamArray=new Integer[teamList.size()];
		teamArray=teamList.toArray(teamArray);
		
		Integer[] staffArray=new Integer[staffList.size()];
		staffArray=staffList.toArray(staffArray);
		sqlParameters = new SqlParameter[] { new SqlParameter("P_WORKFLOW_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_WORK_ID", OracleTypes.NUMERIC),
				new SqlParameter("P_SEVERITY", OracleTypes.VARCHAR),
				new SqlParameter("P_ACKNOWLEDGEMENT_REQUIRED", OracleTypes.VARCHAR),
				new SqlParameter("P_ACKNOWLEDGEMENT_SUBJECT", OracleTypes.VARCHAR),
				new SqlParameter("P_MESSAGE_TEXT", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMERIC),
				new SqlParameter("P_TEAMS_ARRAY",OracleTypes.ARRAY, "OMS_OWNER.TAG_WORKFLOW_ADM.TEAMS_STAFF_ARRAY"),
				new SqlParameter("P_STAFF_ARRAY", OracleTypes.ARRAY, "OMS_OWNER.TAG_WORKFLOW_ADM.TEAMS_STAFF_ARRAY")
				
				};

		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADM").withProcedureName("STAFF_MESSAGE_BULK")
				.declareParameters(sqlParameters);

		inParamMap.put("P_WORKFLOW_TYPE", "MEMO");
		inParamMap.put("P_WORK_ID", newMemoModel.getWorkId());
		inParamMap.put("P_SEVERITY", newMemoModel.getSeverity());
		inParamMap.put("P_ACKNOWLEDGEMENT_REQUIRED",  newMemoModel.isAcknowledgementFlag()?'Y':'N');
		inParamMap.put("P_ACKNOWLEDGEMENT_SUBJECT", newMemoModel.getAcknowledgementSubject());
		inParamMap.put("P_MESSAGE_TEXT", newMemoModel.getWorkMessage());
		inParamMap.put("P_OFFENDER_BOOK_ID", newMemoModel.getOffenderBookId());
		
		inParamMap.put("P_TEAMS_ARRAY",new SqlArrayValue<Integer>(teamArray)); 
		inParamMap.put("P_STAFF_ARRAY", new SqlArrayValue<Integer>(staffArray));
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			simpleJDBCCall.execute(inParameter);
			code =  ONE; 
		} 
		catch (final Exception e) {
			code = TWO;
			logger.error("Exception :", e);
		}
		return code;
	}

}
