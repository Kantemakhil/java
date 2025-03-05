package net.syscon.s4.inst.legals.impl;

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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
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
import net.syscon.s4.inst.legals.OiddecasRepository;
import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.CourtEvent;
import net.syscon.s4.inst.legals.beans.OffensesOutcome;
import net.syscon.s4.inst.legals.beans.Sentences;
import oracle.jdbc.OracleTypes;

@Repository
public class OiddecasRepositoryImpl extends RepositoryBase implements OiddecasRepository{

	private static Logger logger = LogManager.getLogger(OcupsrdeRepositoryImpl.class);
	private final JdbcTemplate jdbcTemplate ;
	
	public OiddecasRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
		
	final Map<String, FieldMapper> courtCasesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CASE_ID",     			new FieldMapper("caseId"))
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("CASE_INFO_NUMBER",  		new FieldMapper("caseInfoNumber"))
			.put("CASE_TYPE", 				new FieldMapper("caseType"))
			.put("CASE_STATUS",   			new FieldMapper("caseStatus"))
			.put("COMBINED_CASE_ID", 		new FieldMapper("combinedCaseId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID",  		new FieldMapper("modifyUserId"))
			.put("BEGIN_DATE", 				new FieldMapper("beginDate"))
			.put("AGY_LOC_ID",   			new FieldMapper("agy_loc_id"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDateTime"))
			.put("CASE_INFO_PREFIX", 		new FieldMapper("caseInfoPrefix"))
			.put("VICTIM_LIAISON_UNIT",  	new FieldMapper("victimLiaisonUnit"))
			.put("STATUS_UPDATE_REASON", 	new FieldMapper("statusUpdateReason"))
			.put("STATUS_UPDATE_COMMENT",   new FieldMapper("statusUpdateComment"))
			.put("STATUS_UPDATE_DATE", 		new FieldMapper("status_update_date"))
			.put("STATUS_UPDATE_STAFF_ID", 	new FieldMapper("status_update_staff_id"))
			.put("LIDS_CASE_NUMBER",   		new FieldMapper("lidsCaseNumber"))
			.put("CASE_SEQ", 				new FieldMapper("case_Seq"))
			.put("SEAL_FLAG", 				new FieldMapper("seal_flag"))
			.put("LINK_CASE_SEQ",	 		new FieldMapper("linkCaseSeq"))
			.build();
	
	private final Map<String, FieldMapper> courtEventMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))		
			.put("CASE_ID",  		 		new FieldMapper("caseId"))
			.put("EVENT_ID",  		 		new FieldMapper("eventId"))
			.put("EVENT_DATE", 			 	new FieldMapper("eventDate"))
			.put("START_TIME",  		 	new FieldMapper("startTime"))
			.put("AGY_LOC_ID",  		 	new FieldMapper("agyLocId"))
			.put("COURT_EVENT_TYPE", 	 	new FieldMapper("hearingType"))		
			.put("OUTCOME_REASON_CODE",  	new FieldMapper("outcomeReasonCode"))
			.put("NEXT_EVENT_DATE", 	 	new FieldMapper("nextEventDate"))		
			.put("NEXT_EVENT_START_TIME",	new FieldMapper("nextEventStartTime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID",  		new FieldMapper("modifyUserId"))
			.put("HOLD_FLAG", 				new FieldMapper("holdFlag"))		
			.put("ORDER_REQUESTED_FLAG",  	new FieldMapper("orderRequestedFlag"))
			.put("COMMENT_TEXT",  			new FieldMapper("commentText"))
			.build();
	
	private final Map<String, FieldMapper> offensesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 	new FieldMapper("offenderBookId"))
			.put("SENTENCE_SEQ", 	new FieldMapper("sentenceSeq"))
			.put("OFFENDER_CHARGE_ID", 	new FieldMapper("offenderChargeId")).build();
	
	@Override
	public Integer deleteChildToCourtCases(CourtCases deletedCourtCase) {
		Integer liReturn =1;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_CASE_ID", 		   OracleTypes.NUMBER),
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_DELETE_LEGALS")
				.withProcedureName("DELETE_CHILD_TO_CASE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", deletedCourtCase.getOffenderBookId());    
		inParamMap.put("P_CASE_ID", deletedCourtCase.getcaseId());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			liReturn = 0;
			logger.error("deleteChildToCourtCases:"+e.getMessage());
			}
		return liReturn;
	}

	
	@Override
	public Integer deleteOffenderCourtCases(CourtCases deleteCourtCase,String userName) {
		Integer liReturn = 1;
		final String sql = getQuery("DELETE_COURT_CASE");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			inputMap.put("offenderBookId", deleteCourtCase.getOffenderBookId());
			inputMap.put("caseId", deleteCourtCase.getcaseId());
			inputMap.put("modify_user_id",userName);
			updatePreDeletedRow("offender_cases", "offender_book_id=:offenderBookId and case_id=:caseId", inputMap);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteOffenderCourtCases"+e);
		}
		try {
			namedParameterJdbcTemplate.update(sql,createParams("offenderBookId",deleteCourtCase.getOffenderBookId(),"caseId",deleteCourtCase.getcaseId()));
			}catch (Exception e) {
				liReturn = 0;
				logger.error("deleteOffenderCourtCases: "+e.getMessage());
				}
		return liReturn;
	}


	@Override
	public Integer deleteOffenderCourtEvents(CourtEvent deletedCourtEvent,String userName) {
		Integer liReturn = 1;
		final String sql = getQuery("DELETE_COURT_EVENT");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			inputMap.put("offenderBookId", deletedCourtEvent.getOffenderBookId());
			inputMap.put("caseId", deletedCourtEvent.getCaseId());
			inputMap.put("eventId", deletedCourtEvent.getEventId());
			inputMap.put("modify_user_id", userName);
			updatePreDeletedRow("court_events", "offender_book_id=:offenderBookId and case_id=:caseId and event_id=:eventId", inputMap);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteOffenderCourtEvents"+e);
		}
		
		try {
			namedParameterJdbcTemplate.update(sql,createParams("offenderBookId",deletedCourtEvent.getOffenderBookId(),"caseId",deletedCourtEvent.getCaseId(),"eventId",deletedCourtEvent.getEventId()));
			
			}catch (Exception e) {
				liReturn = 0;
				logger.error("deleteOffenderCourtEvents: "+e.getMessage());
				}
		return liReturn;
	}

	@Override
	public Integer deleteEventChildRec(CourtEvent deletedCourtEvent) {
		Integer liReturn =1;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
						new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
						new SqlParameter("P_CASE_ID", 		   OracleTypes.NUMBER),
						new SqlParameter("P_EVENT_ID", 		   OracleTypes.NUMBER),
						};
				SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
						.withSchemaName("OMS_OWNER")
						.withCatalogName("TAG_DELETE_LEGALS")
						.withProcedureName("DELETE_ORDERS")
						.declareParameters(sqlParameters);
				inParamMap.put("P_OFFENDER_BOOK_ID", deletedCourtEvent.getOffenderBookId());    
				inParamMap.put("P_CASE_ID", deletedCourtEvent.getCaseId());
				inParamMap.put("P_EVENT_ID", deletedCourtEvent.getEventId());
				final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				try {
					returnObject = simpleJDBCCall.execute(inParameter);
				} catch (Exception e) {
					liReturn = 0;
					logger.error("deleteEventChildRec"+e.getMessage());
					}
				return liReturn;
			}	
	


	@Override
	public Boolean isLinkedCaseExist(Integer offenderChargeId) {
		Integer linkedCaseCount=0;
		/*final Boolean b= isCaselinkedByFunction(offenderChargeId);
		return b;*/
		final String sql = getQuery("IS_LINKED_CASE_QUERY");
		linkedCaseCount = namedParameterJdbcTemplate.queryForObject(sql,createParams("OFFENDERCHARGEID",offenderChargeId),Integer.class);
		if(linkedCaseCount>0) {
			return true;
		}else {
			return false;
		}
	}


	@Override
	public Boolean isSentenceAttached(OffensesOutcome offenses) {
		String sentenceObj=null;
		/*final Boolean b= isSentenceAttachedAlternateCalling(offenses);
		return b;*/
		final String sql = getQuery("IS_SENTENCE_EXIST_QUERY");
		try {
			sentenceObj = namedParameterJdbcTemplate.queryForObject(sql,createParams("OFFENDERCHARGEID",offenses.getOffenderChargeId()),String.class);
		}catch(EmptyResultDataAccessException e) {
			return false;
		}catch(Exception e) {
			logger.error("isSentenceAttached"+e.getMessage());
		}
		if(sentenceObj.equals("X")) {
			return true;
		}else {
			return false;
		}
	}

	private Boolean isCaselinkedByFunction(Integer offenderChargeId) {
		Boolean isExist =false;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_OFFENDER_CHARGE_ID", OracleTypes.NUMBER),
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_DELETE_LEGALS")
				.withFunctionName("is_case_linked")
				.declareParameters(sqlParameters);
		
		inParamMap.put("P_OFFENDER_CHARGE_ID", offenderChargeId);    
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			isExist = simpleJDBCCall.executeFunction(Boolean.class,inParameter);
		} catch (Exception e) {
			isExist=true;
			logger.error("alternateCalling"+e.getMessage());
			}
		return isExist;
		}
	
	private Boolean isSentenceAttachedAlternateCalling(OffensesOutcome deletedOffenses) {
		Boolean isSentAttached =false;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_CHARGE_ID", OracleTypes.NUMBER),
				new SqlInOutParameter("RETURN_VALUE", OracleTypes.BOOLEAN)
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_DELETE_LEGALS")
				.withFunctionName("is_sent_offence_attached")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", deletedOffenses.getOffenderBookId());
		inParamMap.put("P_OFFENDER_CHARGE_ID", deletedOffenses.getOffenderChargeId());    
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			isSentAttached = simpleJDBCCall.executeFunction(Boolean.class,inParameter);
		} catch (Exception e) {
			//isSentAttached=true;
			logger.error("isSentenceAttachedAlternateCalling"+e.getMessage());
			}
		return isSentAttached;
		}
	
	@Override
	public Integer deleteOffenderOffenses(OffensesOutcome deletedOffenses,String userName) {
		Integer liReturn = 1;
		final String sql = getQuery("DELETE_OFFENDER_OFFENSES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			inputMap.put("offenderBookId", deletedOffenses.getOffenderBookId());
			inputMap.put("offenderChargeId", deletedOffenses.getOffenderChargeId());
			inputMap.put("modify_user_id", userName);
			updatePreDeletedRow("offender_charges", "offender_book_id=:offenderBookId and offender_charge_id=:offenderChargeId", inputMap);
		} catch (Exception e) {
			logger.error("updatePreDeletedRow in deleteOffenderOffenses"+e);
		}
		try {
			namedParameterJdbcTemplate.update(sql,createParams("offenderBookId",deletedOffenses.getOffenderBookId(),"offenderChargeId",deletedOffenses.getOffenderChargeId()));
			}catch (Exception e) {
				liReturn = 0;
				logger.error("deleteOffenderOffenses : "+e.getMessage());
				}
		return liReturn;
	}


	@Override
	public Integer deleteCourtEventCharges(OffensesOutcome deletedOffenses,String userName) {
		Integer liReturn = 1;
		final String sql = getQuery("DELETE_COURT_EVENT_CHARGES");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			inputMap.put("offenderChargeId", deletedOffenses.getOffenderChargeId());
			inputMap.put("modify_user_id", userName);
			updatePreDeletedRow("court_event_charges", "offender_charge_id=:offenderChargeId", inputMap);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteCourtEventCharges"+e);
		}
		try {
			namedParameterJdbcTemplate.update(sql,createParams("offenderChargeId",deletedOffenses.getOffenderChargeId()));
			}catch (Exception e) {
				liReturn = 0;
				logger.error("deleteCourtEventCharges : "+e.getMessage());
				}
		return liReturn;
	}
	

	@Override
	public List<OffensesOutcome> populateOffensesOnSentence(Sentences sentence) {
		List<OffensesOutcome> resultList = null;
		Map<String, Object> returnObject = null;
		String Date = null;
		Date date;
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlOutParameter("P_REF_CUR", OracleTypes.CURSOR),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SENTENCE_SEQ", OracleTypes.NUMBER)
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_DELETE_LEGALS")
				.withProcedureName("CHARGE_SENTENCES_QUERY")
				.declareParameters(sqlParameters);
		
		inParamMap.put("P_REF_CUR", OracleTypes.CURSOR);
		inParamMap.put("P_OFFENDER_BOOK_ID", sentence.getOffenderBookId());    
		inParamMap.put("P_SENTENCE_SEQ", sentence.getLine());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			resultList = new ArrayList<>();
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("P_REF_CUR");
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> childMap = list.get(i);
				OffensesOutcome bean = new OffensesOutcome();
				bean.setOffenseDescription(String.valueOf(childMap.get("OFFENCE_DESCRIPTION")));
				bean.setOffenderBookId(Long.parseLong(String.valueOf(childMap.get("OFFENDER_BOOK_ID"))));
				bean.setOffenderChargeId(Long.parseLong(String.valueOf(childMap.get("OFFENDER_CHARGE_ID"))));
				bean.setSentenceSeq(Long.parseLong(String.valueOf(childMap.get("SENTENCE_SEQ"))));
				bean.setPlea(String.valueOf(childMap.get("PLEA_CODE")));
				//bean.setPropertyValue(Long.parseLong(String.valueOf(childMap.get("PROPERTY_VALUE"))));
				bean.setPropertyValue(String.valueOf(childMap.get("PROPERTY_VALUE")) == "null" ? 0L : Long.parseLong(String.valueOf(childMap.get("PROPERTY_VALUE"))));
				if(String.valueOf(childMap.get("OFFENCE_DATE"))=="null") {
					bean.setOffenseDate(null);
				}
				else {
					date = format.parse(String.valueOf(childMap.get("OFFENCE_DATE")));
					bean.setOffenseDate(date);
					}
				if(String.valueOf(childMap.get("OFFENCE_RANGE_DATE"))=="null") {
					bean.setRange(null);
				}
				else {
					date = format.parse(String.valueOf(childMap.get("OFFENCE_RANGE_DATE")));
					bean.setRange(date);
					}
				resultList.add(bean);
			}

		} catch (Exception e) {
			logger.error("populateOffensesOnSentence"+e.getMessage());
			}
		return resultList;
	}
	
	@Override
	public List<Condition> populateConditionsData(Sentences sentence) {
		List<Condition> resultList = null;
		Map<String, Object> returnObject = null;
		String Date = null;
		Date date;
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlOutParameter("P_REF_CUR", OracleTypes.CURSOR),
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SENTENCE_SEQ", OracleTypes.NUMBER)
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_DELETE_LEGALS")
				.withProcedureName("SENTENCES_CONDI_QUERY")
				.declareParameters(sqlParameters);
		
		inParamMap.put("P_REF_CUR", OracleTypes.CURSOR);
		inParamMap.put("P_OFFENDER_BOOK_ID", sentence.getOffenderBookId());    
		inParamMap.put("P_SENTENCE_SEQ", sentence.getLine());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			resultList = new ArrayList<>();
			final List<Map<String, String>> list = (List<Map<String, String>>) returnObject.get("P_REF_CUR");
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> childMap = list.get(i);
				Condition bean = new Condition();
				bean.setOffenderBookId(Long.parseLong(String.valueOf(childMap.get("OFFENDER_BOOK_ID"))));
				bean.setOffenderSentConditionId(Integer.parseInt(String.valueOf(childMap.get("OFFENDER_SENT_CONDITION_ID"))));
				//bean.setSentenceSeq(Integer.parseInt(String.valueOf(childMap.get("OFFENDER_SENT_CONDITION_ID"))));
				bean.setSentenceSeq(Integer.parseInt(String.valueOf(childMap.get("SENTENCE_SEQ"))));
				bean.setLength(String.valueOf(childMap.get("LENGTH")).equals("null")? 0 : Integer.parseInt(String.valueOf(childMap.get("LENGTH"))));
				bean.setLengthUnit(String.valueOf(childMap.get("LENGTH_UNIT")));
				bean.setCommentText(String.valueOf(childMap.get("LONG_COMMENT_TEXT")));
				bean.setStatus(String.valueOf(childMap.get("TAG_REFERENCE_CODES.GETDESCCODE('ACTIVE_TYPE',CONDITION_STATUS)=Active")));
				bean.setDescription(String.valueOf(childMap.get("DESCRIPTION")));
				//bean.setPropertyValue(Long.parseLong(String.valueOf(childMap.get("PROPERTY_VALUE"))));
				//bean.setPropertyValue(String.valueOf(childMap.get("PROPERTY_VALUE")) == "null" ? 0L : Long.parseLong(String.valueOf(childMap.get("PROPERTY_VALUE"))));
				if(String.valueOf(childMap.get("START_DATE"))=="null") {
					bean.setStartDate(null);
				}
				else {
					date = format.parse(String.valueOf(childMap.get("START_DATE")));
					bean.setStartDate(date);
					}
				resultList.add(bean);
			}

		} catch (Exception e) {
			logger.error("populateConditionsData"+e.getMessage());
			}
		return resultList;
	}


	@Override
	public Integer deleteSentences(Sentences sentence,String userName) {
		Integer deleteRecordSuccess =1;
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			inputMap.put("offenderBookId", sentence.getOffenderBookId());
			inputMap.put("sentenceSeq", sentence.getSentenceSeq());
			inputMap.put("modify_user_id", userName);
			
			updatePreDeletedRow("OFFENDER_SENTENCES", "OFFENDER_BOOK_ID=:offenderBookId AND SENTENCE_SEQ=:sentenceSeq", inputMap);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteOffederCondTransfer"+e);
		}
		
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SENTENCE_SEQ", OracleTypes.NUMBER),
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_DELETE_LEGALS")
				.withProcedureName("delete_sentences")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", sentence.getOffenderBookId());
		inParamMap.put("P_SENTENCE_SEQ", sentence.getLine());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			deleteRecordSuccess=0;
			logger.error("deleteSentences : "+e.getMessage());
			}
		return deleteRecordSuccess;
	}
	
	@Override
	public Boolean isConsecutiveSentenceExist(Sentences sentence) {
		Integer consecSentenceCount=0;
		/*final Boolean b= isCaselinkedByFunction(offenderChargeId);
		return b;*/
		final String sql = getQuery("IS_CONSEC_SENTENCE_EXIST");
		consecSentenceCount = namedParameterJdbcTemplate.queryForObject(sql,createParams("offenderBookId",sentence.getOffenderBookId(),"sentenceSeq",sentence.getLine()),Integer.class);
		if(consecSentenceCount>1) {
			return true;
		}else {
			return false;
		}
	}


	@Override
	public Integer deleteConditionsRecord(Condition conditionRecord) {
		Integer liReturn =1;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_OFFENDER_BOOK_ID",  OracleTypes.NUMBER),
				new SqlParameter("p_sentence_seq", 		OracleTypes.NUMBER),
				new SqlParameter("p_sent_condition_id", OracleTypes.NUMBER)
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_DELETE_LEGALS")
				.withProcedureName("delete_condition")
				.declareParameters(sqlParameters);
			inParamMap.put("P_OFFENDER_BOOK_ID", conditionRecord.getOffenderBookId());    
			inParamMap.put("p_sentence_seq",conditionRecord.getSentenceSeq());
			inParamMap.put("p_sent_condition_id", conditionRecord.getOffenderSentConditionId());
			final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			try {
				returnObject = simpleJDBCCall.execute(inParameter);
			} catch (Exception e) {
				liReturn = 0;
				logger.error("deleteConditionsRecord:"+e.getMessage());
				}
		
		
		return liReturn;
	}


	@Override
	public Integer okToDeleteRecord(Long keyCode,String tableName,String columnName,String excludeTable,String owner) {
		//final String schemaName = getSchemaName(owner);
		Integer resultValue=0;
		
		SqlParameterSource  args = new MapSqlParameterSource()
			.addValue("P_KEY_CODE", keyCode)
			.addValue("P_TABLE_NAME", tableName)
			.addValue("P_COLUMN_NAME", columnName)
			.addValue("P_EXCLUDE_TABLE", excludeTable)
			.addValue("P_OWNER",  owner);			

		
		SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_UTILS")
				.withFunctionName("OK_TO_DELETE_RECORD")
				.withoutProcedureColumnMetaDataAccess();
		
		
		jdbcCall.declareParameters(	
			new SqlOutParameter("RETURN", Types.BOOLEAN),
			new SqlParameter("P_KEY_CODE", Types.VARCHAR),
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
	public Integer deleteSentenceCharges(OffensesOutcome sentenceCharges) {
		Integer liReturn =1;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_OFFENDER_BOOK_ID",  OracleTypes.NUMBER),
				new SqlParameter("p_sentence_seq", 		OracleTypes.NUMBER),
				new SqlParameter("p_offender_charge_id", OracleTypes.NUMBER)
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_DELETE_LEGALS")
				.withProcedureName("delete_sentence_charges")
				.declareParameters(sqlParameters);
			inParamMap.put("P_OFFENDER_BOOK_ID", sentenceCharges.getOffenderBookId());    
			inParamMap.put("p_sentence_seq",sentenceCharges.getSentenceSeq());
			inParamMap.put("p_offender_charge_id", sentenceCharges.getOffenderChargeId());
			final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			try {
				returnObject = simpleJDBCCall.execute(inParameter);
			} catch (Exception e) {
				liReturn = 0;
				logger.error("deleteSentenceCharges"+e.getMessage());
				}
		
		
		return liReturn;
	}


	@Override
	public Integer deleteChildToSentenceCharges(OffensesOutcome sentenceCharges) {
		Integer liReturn =1;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_OFFENDER_BOOK_ID",  OracleTypes.NUMBER),
				new SqlParameter("p_sentence_seq", 		OracleTypes.NUMBER),
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_DELETE_LEGALS")
				.withProcedureName("delete_child_to_sent")
				.declareParameters(sqlParameters);
			inParamMap.put("P_OFFENDER_BOOK_ID", sentenceCharges.getOffenderBookId());    
			inParamMap.put("p_sentence_seq",sentenceCharges.getSentenceSeq());
			final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
			try {
				returnObject = simpleJDBCCall.execute(inParameter);
			} catch (Exception e) {
				liReturn = 0;
				logger.error("deleteChildToSentenceCharges:"+e.getMessage());
				}
		return liReturn;
	}


	@Override
	public Integer deleteFineSentence(OffensesOutcome sentenceCharges) {
		Integer deleteRecordSuccess =1;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SENTENCE_SEQ", OracleTypes.NUMBER)
				
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_DELETE_LEGALS")
				.withFunctionName("delete_fine_sent")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", sentenceCharges.getOffenderBookId());
		inParamMap.put("P_SENTENCE_SEQ", sentenceCharges.getSentenceSeq());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			deleteRecordSuccess=0;
			logger.error("deleteFineSentence : "+e.getMessage());
			}
		return deleteRecordSuccess;
	}


	@Override
	public Integer deleteSentencesProdc(Sentences sentenceCharges,String userName) {
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			inputMap.put("offenderBookId", sentenceCharges.getOffenderBookId());
			inputMap.put("sentenceSeq", sentenceCharges.getSentenceSeq());
			inputMap.put("modify_user_id", userName);
			updatePreDeletedRow("OFFENDER_SENTENCES", "OFFENDER_BOOK_ID=:offenderBookId AND SENTENCE_SEQ=:sentenceSeq", inputMap);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteSentencesProdc"+e);
		}
		
		Integer deleteRecordSuccess =1;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
				new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_SENTENCE_SEQ", OracleTypes.NUMBER),
				};
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_DELETE_LEGALS")
				.withProcedureName("delete_sentences")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_BOOK_ID", sentenceCharges.getOffenderBookId());
		inParamMap.put("P_SENTENCE_SEQ", sentenceCharges.getSentenceSeq());
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
		} catch (Exception e) {
			deleteRecordSuccess=0;
			logger.error("deleteSentencesProdc : "+e.getMessage());
			}
		return deleteRecordSuccess;
		
	}


	@Override
	public Integer deleteOffensesOnSentence(OffensesOutcome sentenceCharges) {
		Integer deleteSuccess=0;
		if(deleteSentenceCharges(sentenceCharges)==1) {
			if(deleteChildToSentenceCharges(sentenceCharges)==1) {
				deleteSuccess=1;
			}
		}else {
			deleteSuccess=0;
		}
		return deleteSuccess;
	}


	@Override
	public Integer okToModifyRecord(String keyString, String tableName, String columnName, String excludeTable,
			String owner) {
Integer resultValue=0;
		
		SqlParameterSource  args = new MapSqlParameterSource()
			.addValue("P_KEY_STRING", keyString)
			.addValue("P_TABLE_NAME", tableName)
			.addValue("P_COLUMN_NAME", columnName)
			.addValue("P_EXCLUDE_TABLE", excludeTable)
			.addValue("P_OWNER",  owner);			

		
		SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_UTILS")
				.withFunctionName("OK_TO_MODIFY_RECORD")
				.withoutProcedureColumnMetaDataAccess();
		
		
		jdbcCall.declareParameters(	
			new SqlOutParameter("RETURN", Types.BOOLEAN),
			new SqlParameter("P_KEY_STRING", Types.VARCHAR),
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
	public Boolean isSentAdjustAttached(Sentences sentence) {
		SqlParameterSource  args = new MapSqlParameterSource()
			.addValue("P_OFFENDER_BOOK_ID", sentence.getOffenderBookId())
			.addValue("P_SENTENCE_SEQ", sentence.getLine());				
		SimpleJdbcCall jdbcCall = new OracleSimpleJdbcCall(jdbcTemplate)
				.withSchemaName("OMS_OWNER")
				.withCatalogName("tag_delete_legals")
				.withFunctionName("is_sent_adjust_attached")
				.withoutProcedureColumnMetaDataAccess();	
		jdbcCall.declareParameters(	
			new SqlOutParameter("RETURN", Types.BOOLEAN),
			new SqlParameter("P_OFFENDER_BOOK_ID", Types.INTEGER),
			new SqlParameter("P_SENTENCE_SEQ", Types.INTEGER)
		);
			
		Boolean result = jdbcCall.executeFunction(Boolean.class, args);
		return result;

	}


	@Override
	public Integer deleteSentencesOnCharges(OffensesOutcome sentenceCharges) {
		Integer liReturn =1;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[5];
		sqlParameters = new SqlParameter[] { 
						new SqlParameter("P_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
						new SqlParameter("P_SENTENCE_SEQ", 		   OracleTypes.NUMBER),
						new SqlParameter("P_OFFENDER_CHARGE_ID", 		   OracleTypes.NUMBER),
						};
				SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
						.withSchemaName("OMS_OWNER")
						.withCatalogName("TAG_DELETE_LEGALS")
						.withProcedureName("delete_sentence_charges")
						.declareParameters(sqlParameters);
								
				inParamMap.put("P_OFFENDER_BOOK_ID", sentenceCharges.getOffenderBookId());    
				inParamMap.put("P_SENTENCE_SEQ", sentenceCharges.getSentenceSeq());
				inParamMap.put("P_OFFENDER_CHARGE_ID", sentenceCharges.getOffenderChargeId());
				final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
				try {
					returnObject = simpleJDBCCall.execute(inParameter);
				} catch (Exception e) {
					liReturn = 0;
					logger.error("deleteSentences:"+e.getMessage());
					}
				return liReturn;

	}
	
}
