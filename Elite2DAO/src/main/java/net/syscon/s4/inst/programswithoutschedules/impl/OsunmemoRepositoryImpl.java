package net.syscon.s4.inst.programswithoutschedules.impl;
import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.beans.TagWorkflowBrowseQueue;
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.programswithoutschedules.OsunmemoRepository;


@Repository
public class OsunmemoRepositoryImpl extends RepositoryBase implements OsunmemoRepository{
	
	private static Logger logger = LogManager.getLogger(OsunmemoRepositoryImpl.class.getName());
	
	private static final String ONE = "1";
	private static final String TWO = "2";

/**
 * Creates new OsunmemoRepositoryImpl class Object 
 */
public OsunmemoRepositoryImpl() {
}
private final Map<String, FieldMapper> staffMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("CODE", 						new FieldMapper("code"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> refrnceMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("WORK_TYPE", 						new FieldMapper("workType"))
.put("CODE", 						new FieldMapper("code"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.put("WORK_SUB_TYPE", 						new FieldMapper("workSubType"))
.build();
private final Map<String, FieldMapper> teamMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("STAFF_ID", 						new FieldMapper("staffId"))
.put("TEAM_MEMBER_ID", 						new FieldMapper("teamMemberId"))
.put("TEAM_ID", 						new FieldMapper("teamId"))
.build();
private final Map<String, FieldMapper> mTeamIdMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("TEAM_ID", 						new FieldMapper("teamId"))
.put("CODE", 						new FieldMapper("code"))
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();
private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DESCRIPTION", 						new FieldMapper("description"))
.build();

/**
* Used to capture results from select query
* @return List<MReferenceCodes> 
*/
public List<ReferenceCodes> rgWorksRecordGroup()  {
 	final String sql = getQuery("OSUNMEMO_FIND_RGWORKS");
 	final RowMapper<ReferenceCodes>codesRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, refrnceMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),codesRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<MReferenceCodesM'StfMbrs.staffIdTeams> 
*/
public List<StaffMembers> rgStaffRecordGroup(final String workId,final String offenderBookId)  {
 	final String sql = getQuery("OSUNMEMO_FIND_RGSTAFF");
 	final RowMapper<StaffMembers> satffMapper = Row2BeanRowMapper.makeMapping(sql,StaffMembers.class, staffMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams("workId",workId ,"offenderBookId",offenderBookId),satffMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<MReferenceCodesM'StfMbrs.staffIdTeamsMTeams.teamCodeTeams.teamId> 
*/
public List<TeamMembers> rgTeamsRecordGroup(final String workId,final String offenderBookId,final String staffId)  {
 	final String sql = getQuery("OSUNMEMO_FIND_RGTEAMS");
 	final RowMapper<TeamMembers> teamIdRowMapper = Row2BeanRowMapper.makeMapping(sql,TeamMembers.class, mTeamIdMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams("workId",workId ,"offenderBookId",offenderBookId,"staffId",staffId),teamIdRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
/**
* Used to capture results from select query
* @return List<M> 
*/
public List<ReferenceCodes> rgSeverityRecordGroup()  {
 	final String sql = getQuery("OSUNMEMO_FIND_RGSEVERITY");
 	final RowMapper<ReferenceCodes>mRowMapper = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, mMapping);
 
	try {
  		return namedParameterJdbcTemplate.query(sql,createParams(),mRowMapper);
  	} catch (EmptyResultDataAccessException e) {
  		return Collections.emptyList();  
	}
}
	

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getDisplayAuto
	 *
	 * @param params
	 *
	 */
	public String getDisplayAuto(final String offenderBookId)  {
		final String sql = getQuery("OSUNMEMO_GET_DISPLAY_AUTO");
		return namedParameterJdbcTemplate.queryForObject(sql,createParams("offenderBookId", offenderBookId),String.class);
	}
	
	@Override
	public String submitAdhocWorkflow(final TagWorkflowBrowseQueue newMemoModel) {
		String code = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_WORKFLOW_TYPE", Types.VARCHAR),
				new SqlParameter("P_WORK_ID", Types.NUMERIC), new SqlParameter("P_OFFENDER_BOOK_ID", Types.NUMERIC),
				new SqlParameter("P_TEAM_ID", Types.NUMERIC),new SqlParameter("P_STAFF_ID", Types.NUMERIC),
				new SqlParameter("P_MESSAGE_TEXT", Types.VARCHAR),new SqlParameter("P_DUE_DATE", Types.DATE),
				new SqlParameter("P_SEVERITY", Types.VARCHAR),new SqlParameter("P_ACKNOWLEDGEMENT_REQUIRED", Types.VARCHAR),
				new SqlParameter("P_ACKNOWLEDGEMENT_SUBJECT", Types.VARCHAR)};

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW_ADHOC").withProcedureName("CREATE_ADHOC_WORKFLOW")
				.declareParameters(sqlParameters);

		inParamMap.put("P_WORKFLOW_TYPE", "MEMO");
		inParamMap.put("P_WORK_ID", newMemoModel.getWorkId());
		inParamMap.put("P_OFFENDER_BOOK_ID", newMemoModel.getOffenderBookId());
		inParamMap.put("P_TEAM_ID", newMemoModel.getTeamId()); 
		inParamMap.put("P_STAFF_ID", newMemoModel.getStaffId() != 0 ? newMemoModel.getStaffId() : null );
		inParamMap.put("P_MESSAGE_TEXT", newMemoModel.getMessageText());
		inParamMap.put("P_DUE_DATE", newMemoModel.getDueDate());
		inParamMap.put("P_SEVERITY", newMemoModel.getSeverityCode());
		inParamMap.put("P_ACKNOWLEDGEMENT_REQUIRED", newMemoModel.getAcknowledgementRequired());
		inParamMap.put("P_ACKNOWLEDGEMENT_SUBJECT", newMemoModel.getAcknowledgementSubject());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			 simpleJDBCCall.execute(inParameter);
			 code =  ONE;
			 
		} catch (Exception e) {
			logger.error("Exception :", e);
			code = TWO;
		}
		return code;

	}
	@Override
	public Integer getTeamemberId(final String staffId, final String teamId) {
		int returnData = 0;
		final String sql = getQuery("OSUNMEMO_GET_TEAM_MEMBER_ID");
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,createParams("teamId", teamId, "staffId", staffId),Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return returnData;
	}
	@Override
	public String getSubString(final String offName,final String messageText) {
		final String sql = getQuery("OSUNMEMO_GET_SUB_STRING");
		return namedParameterJdbcTemplate.queryForObject(sql,createParams("offName",offName,"details", messageText),String.class);
	}

}
