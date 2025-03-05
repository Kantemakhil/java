package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderCaseCommitBean;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.OcdlegstRepository;
import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.Sentences;

@Repository
public class OcdlegstRepositoryImpl extends RepositoryBase implements OcdlegstRepository {
	private static Logger logger = LogManager.getLogger(OcdlegstRepositoryImpl.class);
	
	final Map<String, FieldMapper> statusUpdateMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STATUS_UPDATE_REASON", 				new FieldMapper("statusUpdateReason"))			
			.put("STATUS_UPDATE_DATE",  			    new FieldMapper("statusUpdateDate"))
			.put("STATUS_UPDATE_STAFF_ID",  			new FieldMapper("statusUpdateStaffId")).build();
	final Map<String, FieldMapper> conditionListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 		new FieldMapper("offenderBookId"))
			.put("START_DATE",              new FieldMapper("startDate"))
			.put("EXPIRY_DATE",		 		new FieldMapper("endDate"))
			.put("COMM_CONDITION_TYPE",		new FieldMapper("conditionType"))
			.put("COMM_CONDITION_CODE",		new FieldMapper("conditionTypeCode"))
			.put("CONDITION_STATUS",		new FieldMapper("conditionStatus"))
			.put("LENGTH",					new FieldMapper("length"))
			.put("LENGTH_UNIT", 			new FieldMapper("lengthUnit"))
			.put("STATUS_UPDATE_REASON",	new FieldMapper("statusUpdateReason"))
			.put("STATUS_UPDATE_DATE", 		new FieldMapper("statusUpdateDate"))
			.put("STATUS_UPDATE_STAFF_ID", 	new FieldMapper("statusUpdateStaffId"))
			.put("OFFENDER_SENT_CONDITION_ID", 	new FieldMapper("offenderSentConditionId")).build();
	
	private final Map<String, FieldMapper> conditionTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))			
			.put("CODE",  					new FieldMapper("code")).build();
	private final Map<String, FieldMapper> condStatusMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("NBT_CASE_STATUS", 			new FieldMapper("description"))			
			.put("CASE_STATUS",  			new FieldMapper("code")).build();
	private final Map<String, FieldMapper> statusUpdateReasonMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))			
			.put("CODE",  					new FieldMapper("code")).build();

	@Override
	public Integer updateSetenceReason(List<Sentences> sentence) {
		final String sql = getQuery("UPDATE_SENTENCE_DATA");
		int[] returnArray = new int[] {};
		Integer result = 1;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Sentences list : sentence){
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("UpdateSetenceReason"+e.getMessage());
			result = 0;
		}
		
		return result;
	}

	@Override
	public List<Condition> populateConditionData(Sentences sentence) {
		List<Condition> conditionList = new ArrayList<Condition>();
		final String sql = getQuery("GET_CONDITION_DATA");	
		final RowMapper<Condition> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	Condition.class, conditionListMapping);
		try {
			conditionList=  namedParameterJdbcTemplate.query(sql,createParams("sentence_seq",sentence.getLine(),"offender_book_id",sentence.getOffenderBookId(),"comm_condition_type",sentence.getCategory()),referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateConditionData"+e.getMessage());
				}
		return conditionList;
	}
	
	@Override
	public Integer updateConditionData(List<Condition> condition) {
		final String sql = getQuery("UPDATE_CONDITION");
		int[] returnArray = new int[] {};
		Integer result = 1;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Condition list : condition){
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("updateConditionData"+e.getMessage());
			result = 0;
		}
		return result;
	}

	@Override
	public List<Condition> getConditionLov() {
		List<Condition> typeList = new ArrayList<Condition>();
		final String sql = getQuery("GET_CONDITION_TYPE_LOV");		
		final RowMapper<Condition> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,Condition.class,conditionTypeMapping);
		try {
			typeList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("getConditionLov"+e.getMessage());
				}
		return typeList;
	}

	@Override
	public List<Condition> populateCaseStatus() {
		List<Condition> typeList = new ArrayList<Condition>();
		final String sql = getQuery("CONDITION_STATUS");		
		final RowMapper<Condition> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,Condition.class,condStatusMapping);
		try {
			typeList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateCaseStatus"+e.getMessage());
				}
		return typeList;
	}
	
	@Override
	public List<Sentences> statusReasonUpdateLov(String sentenceCalcType) {
		final String sql = getQuery("STATUS_UPDATE_REASON_LOV"); 	
		final RowMapper<Sentences> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,Sentences.class, statusUpdateReasonMapping);
		List<Sentences> returnObj = new ArrayList<Sentences>();
		try {
			returnObj=  namedParameterJdbcTemplate.query(sql,createParams("SENTENCE_CALC_TYPE",sentenceCalcType),columnRowMapper);
		} catch (Exception e) {
			logger.error("statusReasonUpdateLov"+e.getMessage());
		}
		return returnObj;
	}

	@Override
	public int updateCaseReason(OffenderCaseCommitBean courtCaseCommit) {
		final String sql = getQuery("UPDATE_CASES");
		int[] returnArray = new int[] {};
		int result = 0;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		 if(courtCaseCommit.getUpdateList().size() > 0){
	        	for(CourtCases courtCase : courtCaseCommit.getUpdateList()){
	        		
	        		if(null == courtCase.getCaseStatus()|| courtCase.getCaseStatus().equals("")){
	        				return result;
	        		}
	        		
	        		if(null != courtCase.getStatusUpdateReason()){
	        		if(courtCase.getStatusUpdateReason().equals("EXPIRED")){
	            		courtCase.setCaseStatus("I");
	            	}else{
	            		courtCase.setCaseStatus("A");
	            	}
	        		}
	        		courtCase.setCreateDateTime(new Date());        		
	        		parameters.add(new BeanPropertySqlParameterSource(courtCase));	        		
	        	} 
	        	try {	        		
	    			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
	    			result=1;
	    			} catch (Exception e) {
	    			logger.error("updateCaseReason", e);
	    			}
	}
		return result;
	}	 
}