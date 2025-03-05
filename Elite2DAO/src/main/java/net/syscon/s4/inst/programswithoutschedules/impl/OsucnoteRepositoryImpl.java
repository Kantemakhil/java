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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.programswithoutschedules.OsucnoteRepository;


@Repository
public class OsucnoteRepositoryImpl extends RepositoryBase implements OsucnoteRepository{
	
	private static Logger logger = LogManager.getLogger(OsucnoteRepositoryImpl.class.getName());
	private static final String ONE = "1";
	private static final String TWO = "2";

	/**
	 * Creates new OsucnoteRepositoryImpl class Object 
	 */
	public OsucnoteRepositoryImpl() {
	}
	
	private final Map<String, FieldMapper> refrnceMapping = new ImmutableMap.Builder<String, FieldMapper>()
	.put("WORK_TYPE", 						new FieldMapper("workType"))
	.put("CODE", 						new FieldMapper("code"))
	.put("DESCRIPTION", 						new FieldMapper("description"))
	.put("WORK_SUB_TYPE", 						new FieldMapper("workSubType"))
	.build();
	
	
	/**
	* Used to capture results from select query
	* @return List<MReferenceCodes> 
	*/
	public List<ReferenceCodes> rgWorksRecordGroup(final String caseLoadType)  {
	 	final String sql = getQuery("OSUCNOTE_FIND_RGWORKS");
	 	final RowMapper<ReferenceCodes>refCodeMap = Row2BeanRowMapper.makeMapping(sql,ReferenceCodes.class, refrnceMapping);
		try {
	  		return namedParameterJdbcTemplate.query(sql,createParams("caseLoadType", caseLoadType),refCodeMap);
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
		final String sql = getQuery("OSUCNOTE_GET_DISPLAY_AUTO");
		return namedParameterJdbcTemplate.queryForObject(sql,createParams("offenderBookId", offenderBookId),String.class);
	}

	@Override
	public String submitAdhocWorkflow(final TagWorkflowBrowseQueue newMemoModel) {
		String code = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_OFFENDER_BOOK_ID", Types.NUMERIC),
				new SqlParameter("P_STAFF_ID", Types.NUMERIC), new SqlParameter("P_CONTACT_TIME", Types.DATE),
				new SqlParameter("P_CASE_NOTE_TYPE", Types.VARCHAR),new SqlParameter("P_CASE_NOTE_SUB_TYPE", Types.VARCHAR),
				new SqlParameter("P_CASE_NOTE_TEXT", Types.VARCHAR),new SqlParameter("P_NOTE_SOURCE_CODE", Types.VARCHAR),
				new SqlParameter("P_EVENT_ID", Types.NUMERIC),new SqlParameter("P_SENDER_ID", Types.VARCHAR)};

		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_WORKFLOW").withProcedureName("INSERT_CASE_NOTE")
				.declareParameters(sqlParameters);

		inParamMap.put("P_OFFENDER_BOOK_ID", newMemoModel.getOffenderBookId());
		inParamMap.put("P_STAFF_ID",newMemoModel.getStaffId());
		inParamMap.put("P_CONTACT_TIME",  newMemoModel.getDueDate());
		inParamMap.put("P_CASE_NOTE_TYPE", newMemoModel.getWorkType()); 
		inParamMap.put("P_CASE_NOTE_SUB_TYPE", newMemoModel.getWorkSubType());
		inParamMap.put("P_CASE_NOTE_TEXT", newMemoModel.getMessageText());
		inParamMap.put("P_NOTE_SOURCE_CODE", newMemoModel.getMsgId());
		inParamMap.put("P_EVENT_ID", null);
		inParamMap.put("P_SENDER_ID", newMemoModel.getSenderId());

		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			simpleJDBCCall.execute(inParameter);
			 code = ONE;
		} catch (Exception e) {
			logger.error("Exception :", e);
			 code = TWO;
		}
		return code;


	}
	
	@Override
	public String getSubString(final String offName,final String messageText) {
		final String sql = getQuery("OSUCNOTE_GET_SUB_STRING");
		return namedParameterJdbcTemplate.queryForObject(sql,createParams("offName",offName,"details", messageText),String.class);
	}


	@Override
	public Integer getStaffId(final String userId) {
		int returnData = 0;
		final String sql = getQuery("OSUCNOTE_GET_STAFF_ID");
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql,createParams("userId", userId),Integer.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}

		return returnData;
	}

}
