package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
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
import net.syscon.s4.inst.legals.OidsenadRepository;
import net.syscon.s4.inst.legals.beans.AdjustmentDetails;
import net.syscon.s4.inst.legals.beans.Adjustments;
import net.syscon.s4.inst.legals.beans.BailStatus;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.SentenceAdjustment;
import oracle.jdbc.OracleTypes;
@Repository
public class OidsenadRepositoryImpl extends RepositoryBase implements OidsenadRepository{
	private static Logger logger = LogManager.getLogger(OidsenadRepositoryImpl.class);
	final Map<String, FieldMapper> sentenceAdjustmentListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.put("CASE_INFO_NUMBER", 			new FieldMapper("caseNumber"))
			.put("SENTENCE_SEQ",   				new FieldMapper("sentenceSeq"))
			.put("OFFENCE_CODE", 				new FieldMapper("offenseCode"))
			.put("SENTENCE_CALC_TYPE", 			new FieldMapper("sentenceCalcType"))
			.put("SENTENCE_STATUS", 			new FieldMapper("sentenceStatus")).build();
	
	private final Map<String, FieldMapper> debitTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 	  			new FieldMapper("description"))			
			.put("SENTENCE_ADJUST_CODE",  		new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> debitCreditGridMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 	  		new FieldMapper("offenderBookId"))			
			.put("OFFENDER_SENTENCE_ADJUST_ID", new FieldMapper("offenderSentenceAdjustId"))
			.put("SENTENCE_ADJUST_CODE", 		new FieldMapper("sentenceAdjustCode"))
			.put("ADJUST_FROM_DATE", 			new FieldMapper("fromDate"))
			.put("ADJUST_TO_DATE",				new FieldMapper("toDate"))
			.put("ADJUST_DAYS", 				new FieldMapper("days"))
			.put("COMMENT_TEXT", 				new FieldMapper("comment"))
			.put("ADJUST_DATE", 				new FieldMapper("postedDate"))
			.put("OFFENCE_DATE", 				new FieldMapper("offenceDate")).build();
	
	private final Map<String, FieldMapper> adjustGridMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_KEY_DATE_ADJUST_ID",  	new FieldMapper("keyDatesAdjustId"))			
			.put("OFFENDER_BOOK_ID",  				new FieldMapper("offenderBookId"))
			.put("SENTENCE_ADJUST_CODE", 			new FieldMapper("sentenceAdjustCode"))
			.put("ADJUST_FROM_DATE", 				new FieldMapper("adjustFromDate"))
			.put("ADJUST_TO_DATE", 					new FieldMapper("adjustToDate"))
			.put("ADJUST_DAYS", 					new FieldMapper("adjustDays"))
			.put("ADJUST_STATUS",					new FieldMapper("adjustStatus"))
			.put("COMMENT_TEXT", 					new FieldMapper("commentText"))
			.put("ADJUST_DATE", 	  				new FieldMapper("adjustDate"))			
			.put("CREATE_DATETIME",  				new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))			
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag")).build();
	
	@Override
	public List<SentenceAdjustment> populateSentAdjustment(CourtCases offenderCases) {

		List<SentenceAdjustment> gridDataList = new ArrayList<SentenceAdjustment>();
		final String sql = getQuery("SENTENCE_ADJUSTMENT_GRID_DATA");
		final RowMapper<SentenceAdjustment> sentenceAdjustmentRowMapper = Row2BeanRowMapper.makeMapping(sql,SentenceAdjustment.class, sentenceAdjustmentListMapping);
		try {
			gridDataList=  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId", offenderCases.getOffenderBookId()),sentenceAdjustmentRowMapper);
			} catch (Exception e) {
				logger.error("populateSentAdjustment"+e.getMessage());
		}
		return gridDataList;
	
}
	@Override
	public List<BailStatus> populateDebitTypeData() {

		List<BailStatus> gridDataList = new ArrayList<BailStatus>();
		final String sql = getQuery("DEBIT_TYPE_LOV");
		final RowMapper<BailStatus> sentenceAdjustmentRowMapper = Row2BeanRowMapper.makeMapping(sql,BailStatus.class, debitTypeMapping);
		try {
			gridDataList=  namedParameterJdbcTemplate.query(sql,sentenceAdjustmentRowMapper);
			} catch (Exception e) {
				logger.error("populateDebitTypeData"+e.getMessage());
		}
		return gridDataList;
	
}
	@Override
	public List<AdjustmentDetails> populateDebitCredit(SentenceAdjustment sentenceAdjustment)
	{
		List<AdjustmentDetails> gridDataList = new ArrayList<AdjustmentDetails>();
		final String sql = getQuery("DEBIT_CREDIT_GRID_DATA");
		final RowMapper<AdjustmentDetails> debitCreditRowMapper = Row2BeanRowMapper.makeMapping(sql,AdjustmentDetails.class, debitCreditGridMapping);
		try {
			gridDataList=  namedParameterJdbcTemplate.query(sql,createParams("sentence_seq", sentenceAdjustment.getSentenceSeq(),"offender_book_id",sentenceAdjustment.getOffenderBookId()),debitCreditRowMapper);
			} catch (Exception e) {
				logger.error("grid data exception"+e.getMessage());
		}
		return gridDataList;	
	}
	
	@Override
	public Long getPreInsertSentAdjId()
	{
		Long returnObj = null;
		final String sql = getQuery("SENT_ADJ_PREINSERT_ID");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams(),Long.class);
		return returnObj;
	}
	@Override
	public Integer insertDebitCreditRecord(List<AdjustmentDetails> adjustmentDetailsList) {

		final String sql = getQuery("INSERT_DEBIT_CREDIT_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AdjustmentDetails list : adjustmentDetailsList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertDebitCreditRecord : ", e);
			}
		if(adjustmentDetailsList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
		
	}
	@Override
	public Integer updateDebitCreditRecord(List<AdjustmentDetails> adjustmentDetailsList) {

		final String sql = getQuery("UPDATE_DEBIT_CREDIT_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AdjustmentDetails list : adjustmentDetailsList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("updateDebitCreditRecord : ", e);
			}
		if(adjustmentDetailsList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
		
	}
	@Override
	public Integer deleteDebitCreditRecord(List<AdjustmentDetails> adjustmentDetailsList) {

		final String sql = getQuery("DELETE_DEBIT_CREDIT_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AdjustmentDetails list : adjustmentDetailsList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			batchUpdatePreDeletedRows("OFFENDER_SENTENCE_ADJUSTS", "OFFENDER_SENTENCE_ADJUST_ID=:offenderSentenceAdjustId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteDebitCreditRecord"+e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("deleteDebitCreditRecord : ", e);
			}
		if(adjustmentDetailsList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
		
	}
	
	
	
	
	@Override
	public List<BailStatus> populateAdjustTypeLov() {
		List<BailStatus> gridDataList = new ArrayList<BailStatus>();
		final String sql = getQuery("ADJUST_TYPE_LOV");
		final RowMapper<BailStatus> sentenceAdjustmentRowMapper = Row2BeanRowMapper.makeMapping(sql,BailStatus.class, debitTypeMapping);
		try {
			gridDataList=  namedParameterJdbcTemplate.query(sql,sentenceAdjustmentRowMapper);
			} catch (Exception e) {
				logger.error("populateAdjustTypeData"+e.getMessage());
		}
		return gridDataList;
	}
	@Override
	public List<Adjustments> populateAdjustData(Long offenderBookId) {
		List<Adjustments> gridDataList = new ArrayList<Adjustments>();
		final String sql = getQuery("ADJUST_GRID_DATA");
		final RowMapper<Adjustments> adjustRowMapper = Row2BeanRowMapper.makeMapping(sql,Adjustments.class, adjustGridMapping);
		try {
			gridDataList=  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId",offenderBookId),adjustRowMapper);
			} catch (Exception e) {
				logger.error("populateAdjustData"+e.getMessage());
		}
		return gridDataList;	
	}
	@Override
	public Integer insertAdjustRecord(List<Adjustments> insertList) {    // Insert Adjust Record
		final String sql = getQuery("INSERT_ADJUST_GRID_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Adjustments list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}		
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertNewAdjustRecord"+ "", e);
			}
		if(insertList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
		
	}
	
	@Override
	public Long getNextAdjustId() {
		Long returnObj = null;
		final String sql = getQuery("GET_NEXT_ADJUST_ID");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(),Long.class);
		return returnObj;
	}
	
	public Integer postInsertAdjustRecord(List<Adjustments> recordList) {
		Integer liReturn = 1;
        @SuppressWarnings("unused")
		Map<String, Object> returnObject = null;
        final Map<String, Object> inParamMap = new HashMap<String, Object>();
        SqlParameter[] sqlParameters = new SqlParameter[5];
        
        sqlParameters = new SqlParameter[] { 
                    
                    new SqlParameter("p_offbook_id", OracleTypes.NUMBER),
                    new SqlParameter("p_key_date_id", OracleTypes.NUMBER),
                    };
        for(Adjustments adjustment : recordList) {
        SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource)
                    .withSchemaName("OMS_OWNER")
                    .withCatalogName("tag_sentence_calc")
                    .withProcedureName("insert_adjust_days")
                    .declareParameters(sqlParameters);
        inParamMap.put("p_offbook_id", adjustment.getOffenderBookId());    
        inParamMap.put("p_key_date_id",adjustment.getKeyDatesAdjustId()); //621   adjustment.getKeyDatesAdjustId()
        final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);

        try {
              returnObject = simpleJDBCCall.execute(inParameter);
              }catch (Exception e) {
                    liReturn=0;
                    logger.error("postInsertAdjustRecord"+e.getMessage());
                    }
        }
        return liReturn;
	}
	@Override
	public Integer updateAdjustRecord(List<Adjustments> updateList) {
		final String sql = getQuery("UPDATE__ADJUST_GRID_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Adjustments list : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}		
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));			
		} catch (Exception e) {
			logger.error("insertNewAdjustRecord"+ "", e);
			}
		if(updateList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
	}
	@Override
	public Integer prePostEventCall(List<Adjustments> recordList) {
		return postInsertAdjustRecord(recordList);
	}
	@Override
	public Integer deleteAdjustRecord(List<Adjustments> deleteList) {		
		final String sql = getQuery("DELETE_ADJUST_GRID_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Adjustments list : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}	
		try {
			batchUpdatePreDeletedRows("OFFENDER_KEY_DATE_ADJUSTS", "OFFENDER_BOOK_ID =:offenderBookId AND OFFENDER_KEY_DATE_ADJUST_ID =:keyDatesAdjustId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteAdjustRecord"+e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));			
		} catch (Exception e) {
			logger.error("DeleteAdjustRecord"+ "", e);
			}
		if(deleteList.size() == returnArray.length) {
			return 1;
			} else {
				return 2;
				}
	}

	
}
