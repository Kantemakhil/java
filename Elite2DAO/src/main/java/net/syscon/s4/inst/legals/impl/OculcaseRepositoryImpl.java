package net.syscon.s4.inst.legals.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.OracleSimpleJdbcCall;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.OculcaseRepository;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import oracle.jdbc.OracleTypes;


@Repository
public class OculcaseRepositoryImpl extends RepositoryBase implements OculcaseRepository{
	private static Logger logger = LogManager.getLogger(OculcaseRepositoryImpl.class);
	private final JdbcTemplate jdbcTemplate ;
	
	public OculcaseRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	private final Map<String, FieldMapper> selectHearingMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EVENT_DATE", 			 	new FieldMapper("eventDate"))
			.put("START_TIME",  		 	new FieldMapper("startTime"))
			.put("COURT",  		 			new FieldMapper("agyLocId"))
			.put("HEARINGTYPE", 	 		new FieldMapper("hearingType"))		
			.put("COMMENT_TEXT",  			new FieldMapper("commentText"))
			.put("OFFENDER_BOOK_ID",  		new FieldMapper("offenderBookId")).build();
	
	private final Map<String, FieldMapper> linkLovTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASE_ID",     			new FieldMapper("caseId"))
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("CASE_SEQ", 				new FieldMapper("case_Seq"))
			.put("AGY_LOC",   				new FieldMapper("agy_loc_id"))
			.put("INFO_NUMBER",  			new FieldMapper("caseInfoNumber"))
			.put("DESCRIPTION", 			new FieldMapper("caseType"))
			.put("CASE_STATUS",   			new FieldMapper("caseStatus"))
			.put("COMBINED_CASE_ID", 		new FieldMapper("combinedCaseId"))
			.put("START_DATE", 				new FieldMapper("beginDate"))
			.put("INFO_PREFIX", 			new FieldMapper("caseInfoPrefix")).build();

	
	/***
	 * having some issue to execute this procedure 
	 * as the alternative we used query to get link cases in below method
	 */
	/*@Override
	public List<CourtCases> populateLinkCase(CourtCases courtCases) {
		Map<String, Object> returnObject = null;
		List<CourtCases> lListObj = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[15];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_CASE_SEQ", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_COMBINED_CASE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_LINE", OracleTypes.NUMBER),
				new SqlParameter("P_BEGIN_DATE", OracleTypes.DATE),
				new SqlParameter("P_DESCRIPTION", OracleTypes.VARCHAR),
				new SqlParameter("P_CASE_TYPE", OracleTypes.VARCHAR),
				new SqlParameter("P_CASE_INFO", OracleTypes.VARCHAR),
				new SqlParameter("P_CASE_INFO_NUMBER", OracleTypes.VARCHAR),
				new SqlParameter("P_CASE_ID", OracleTypes.NUMBER),
		};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER").withCatalogName("TAG_LEGAL_CASES")
					.withProcedureName("GET_LINKCASE_DETAILS")
						.declareParameters(sqlParameters);
		
		inParamMap.put("P_CASE_SEQ",courtCases.getCase_Seq());
		inParamMap.put("P_OFFENDER_BOOK_ID",courtCases.getOffenderBookId());    
		inParamMap.put("P_COMBINED_CASE_ID",courtCases.getCombinedCaseId());
		inParamMap.put("P_LINE", null);
		inParamMap.put("P_BEGIN_DATE", null);    
		inParamMap.put("P_DESCRIPTION", null);
		inParamMap.put("P_CASE_TYPE", null);
		inParamMap.put("P_CASE_INFO", null);    
		inParamMap.put("P_CASE_INFO_NUMBER", null);
		inParamMap.put("P_CASE_ID", null);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try{
			returnObject = (Map<String, Object>) simpleJDBCCall.execute(inParameter);
			lListObj = new ArrayList<>();
			final CourtCases bean = new CourtCases();
				bean.setCase_Seq(Long.valueOf(String.valueOf(returnObject.get("P_CASE_SEQ"))));
				final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				Date date;
				try {
					date = format.parse(String.valueOf(returnObject.get("P_BEGIN_DATE")));
					bean.setBeginDate(date);
				} catch (ParseException e) {
					logger.error("populateLinkCase", e);
				}
				bean.setCaseStatus(String.valueOf(returnObject.get("P_DESCRIPTION")));
				bean.setCaseType(String.valueOf(returnObject.get("P_CASE_TYPE")));
				bean.setCaseInfoPrefix(String.valueOf(returnObject.get("P_CASE_INFO")));
				bean.setCaseInfoNumber(String.valueOf(returnObject.get("P_CASE_INFO_NUMBER")));
				bean.setcaseId(Long.valueOf(String.valueOf(returnObject.get("P_CASE_ID"))));
			lListObj.add(bean);
		}catch (Exception e){
			logger.error("populateLinkCase"+e.getMessage());
		}
		return lListObj;
	}*/
	
	public List<CourtCases> populateLinkCase(CourtCases courtCases) {
		 List<CourtCases> resultList = new ArrayList<CourtCases>();
		 final String sql = getQuery("GET_LINKCASE_DETAILS");
		 final RowMapper<CourtCases> linklovRowMapper = Row2BeanRowMapper.makeMapping(sql,CourtCases.class, linkLovTypeMapping);
		 try {
				resultList=  namedParameterJdbcTemplate.query(sql,createParams("caseSeq" ,courtCases.getCase_Seq(), "offenderBookId",courtCases.getOffenderBookId(),"combinedCaseId",courtCases.getCombinedCaseId()),linklovRowMapper);
				} catch (Exception e) {
					logger.error("populateLinkCase"+e.getMessage());
			}
		return resultList;
	}
	
	
	
	@Override
	public List<CourtCases> populateLinkLovType(CourtCases courtCases) {
		 List<CourtCases> resultList = new ArrayList<CourtCases>();
			final String sql = getQuery("LOV_LINK_CASES");
			final RowMapper<CourtCases> linklovRowMapper = Row2BeanRowMapper.makeMapping(sql,CourtCases.class, linkLovTypeMapping);
			try {
				resultList=  namedParameterJdbcTemplate.query(sql,createParams("caseSeq" ,courtCases.getCase_Seq(), "offenderBookId",courtCases.getOffenderBookId()),linklovRowMapper);
				} catch (Exception e) {
					logger.error("populateLinkLovType"+e.getMessage());
			}
			return resultList;
	}

	@Override
	public List<CourtEvent> populateSelectHearing(CourtEvent courtEvent) {
		List<CourtEvent> resultList = new ArrayList<CourtEvent>();
		final String sql = getQuery("COURT_EVENTS_HEARING");
		final RowMapper<CourtEvent> hearingRowMapper = Row2BeanRowMapper.makeMapping(sql,CourtEvent.class, selectHearingMapping);
		try {
			resultList=  namedParameterJdbcTemplate.query(sql,createParams("caseId" ,courtEvent.getCaseId(), "offenderBookId",courtEvent.getOffenderBookId()),hearingRowMapper);
			} catch (Exception e) {
				logger.error("populateSelectHearing"+e.getMessage());
		}
		return resultList;
	}

	/***
	 * Link procedure call 
	 */
	
	@Override
	public List<CourtEvent> linkCase(CourtEvent courtEvent) {
		 List<CourtEvent> link = null;
		 final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_CASE_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CASE_ID1", OracleTypes.NUMBER),
				new SqlParameter("P_OFF_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER").withCatalogName("TAG_LEGAL_CASES")
				.withProcedureName("LINK_CASES").declareParameters(sqlParameters);
		
		inParamMap.put("P_CASE_ID", courtEvent.getCaseId());
		inParamMap.put("P_CASE_ID1", courtEvent.getCaseIdl());
		inParamMap.put("P_OFF_BOOK_ID", courtEvent.getOffenderBookId());
		inParamMap.put("P_EVENT_ID", courtEvent.getEventId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try{
			 simpleJDBCCall.execute(inParameter);
			
		}catch(Exception e){
			logger.error("linkCase"+e.getMessage());
		}
		return link;
	}

	
	/***
	 * Unlink procedure call
	 */
	
	@Override
	public List<CourtEvent> unLinkCase(CourtEvent courtEvent) {
		 List<CourtEvent> unlink = null;
			final Map<String, Object> inParamMap = new HashMap<String, Object>();
			SqlParameter[] sqlParameters = new SqlParameter[5];
			sqlParameters = new SqlParameter[] { 
					new SqlParameter("P_CASE_ID", OracleTypes.NUMBER),
					new SqlParameter("P_CASE_ID1", OracleTypes.NUMBER),
					new SqlParameter("P_OFF_BOOK_ID", OracleTypes.NUMBER),
					new SqlParameter("P_EVENT_ID", OracleTypes.NUMBER),
					};
			SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER").withCatalogName("TAG_LEGAL_CASES")
					.withProcedureName("UNLINK_CASES").declareParameters(sqlParameters);
			inParamMap.put("P_CASE_ID", courtEvent.getCaseId());
			inParamMap.put("P_CASE_ID1", courtEvent.getCaseIdl());
			inParamMap.put("P_OFF_BOOK_ID", courtEvent.getOffenderBookId());
			inParamMap.put("P_EVENT_ID", courtEvent.getEventId());
			final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			try{
				 simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
			}catch(Exception e){
				logger.error("unLinkCase"+e.getMessage());
			}
		return unlink;
	}


	@Override
	public boolean chkSentences(Integer caseId, Integer offenderBookId) {
		boolean result = false;
		SqlParameterSource  args = new MapSqlParameterSource()
				.addValue("P_CASE_ID", caseId)
				.addValue("P_OFF_BOOK_ID", offenderBookId);
		
		SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_LEGAL_CASES")
				.withFunctionName("CHECK_SENTENCES")
				.withoutProcedureColumnMetaDataAccess();
		
		jdbcCall.declareParameters(	
				new SqlOutParameter("RETURN", Types.BOOLEAN),
				new SqlParameter("P_CASE_ID", Types.INTEGER),
				new SqlParameter("P_OFF_BOOK_ID", Types.INTEGER)
			);
		Boolean count = jdbcCall.executeFunction(Boolean.class, args);
		if(count){
			result = true;
		}else{
			result = false ;
		}
		return result;
	}
	

}


