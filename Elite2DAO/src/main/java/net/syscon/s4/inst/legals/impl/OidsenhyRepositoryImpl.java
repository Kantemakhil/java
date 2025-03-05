package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.OidsenhyRepository;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.KeyDates;
import net.syscon.s4.inst.legals.beans.SentenceHistory;
@Repository
public class OidsenhyRepositoryImpl extends RepositoryBase implements OidsenhyRepository {
	private static Logger logger = LogManager.getLogger(OidsenhyRepositoryImpl.class);
	final Map<String, FieldMapper> sentenceHistoryListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.put("HistoryDate", 					new FieldMapper("date"))
			.put("HistoryTime", 					new FieldMapper("time"))
			.put("CALC_REASON_CODE",   			new FieldMapper("calculationReason"))
			.put("COMMENT_TEXT", 				new FieldMapper("comment"))
			.put("STAFF_ID", 				new FieldMapper("staffId"))
			.put("STAFF_NAME", 				new FieldMapper("staffName"))
			.put("USER_ID", 				new FieldMapper("userId"))
			.put("OFFENDER_SENT_CALCULATION_ID", new FieldMapper("offenderSentCalculationId")).build();
	
			
	@Override
	public List<SentenceHistory> populateSentenceHistoryData(CourtCases offenderCases,String userId) {
		List<SentenceHistory> sentenceHistoryList = new ArrayList<SentenceHistory>();
		final String sql = getQuery("SENTENCE_HISTORY_GRID_DATA");
		final RowMapper<SentenceHistory> sentenceHistoryRowMapper = Row2BeanRowMapper.makeMapping(sql,SentenceHistory.class, sentenceHistoryListMapping);
		try {
			sentenceHistoryList=  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId", offenderCases.getOffenderBookId(),"userId",userId),sentenceHistoryRowMapper);
			}
			 catch (Exception e) {
				logger.error("populateSentenceHistoryData exception"+e.getMessage());
		}
		return sentenceHistoryList;
	}
	
	@Override
	public List<KeyDates> populateKeyDatesValues(List<KeyDates> keyDatesLablesList) {
		List<String> keyDateColumn = new ArrayList<String>();		
		StringBuilder sb = new StringBuilder();
		StringBuilder overideDateColumns = new StringBuilder();
		
		Long sentCalcId = null;
		for(KeyDates keyDatesLabel : keyDatesLablesList) {			
			keyDateColumn.add(keyDatesLabel.getProfileType());
			sentCalcId = keyDatesLabel.getSentCalculationId();
		}
		if(keyDateColumn.size() > 0){
			int index = 0;
			for(String key : keyDateColumn){
				if(index < keyDateColumn.size()-1) {
				sb.append(key+",");
				}else {
					sb.append(key);
					}	
			++index;
				}
			}
		String queryBuiler = "SELECT "+ sb +" FROM OFFENDER_SENT_CALCULATIONS WHERE OFFENDER_SENT_CALCULATION_ID = "+ sentCalcId;
		List<Map<String,Object>> resKeyDate = namedParameterJdbcTemplate.queryForList(queryBuiler, createParams());
		
		Map<String,Object> keyDateResultSetMap = resKeyDate.get(0);
		for(KeyDates keyDatesLabel : keyDatesLablesList) {			
			keyDatesLabel.setCalculatedDate((Date)keyDateResultSetMap.get(keyDatesLabel.getProfileType()));
		}
		
	/**
	 *  Overide Date Code Begins
	 **/
		
		List<String> dateColumn = new ArrayList<String>();
			if(keyDateColumn.size() > 0){
				int index = 0;
				for(String key : keyDateColumn){
	               String overrideDateColumn = key.replace("CALCULATED","OVERRIDED");
					if(index < keyDateColumn.size()-1) {
					overideDateColumns.append(overrideDateColumn+",");
					dateColumn.add(overrideDateColumn);
					}else {
						overideDateColumns.append(overrideDateColumn);
						dateColumn.add(overrideDateColumn);
						}	
				++index;
					}
				}
			
			String overrideDateQuery = "SELECT "+ overideDateColumns +" FROM OFFENDER_SENT_CALCULATIONS WHERE OFFENDER_SENT_CALCULATION_ID = "+ sentCalcId;
		    List<Map<String,Object>> resOverrideDate = namedParameterJdbcTemplate.queryForList(overrideDateQuery, createParams());
			
		    Map<String,Object> overideDateResultSetMap = resOverrideDate.get(0);
		    int getIndex=-1;
			for(KeyDates overrideDatesLabel : keyDatesLablesList) {
				++getIndex;	
				overrideDatesLabel.setOverrideDate((Date)overideDateResultSetMap.get(dateColumn.get(getIndex)));
			}
			/**
			 *  Overide Date Code Ends
			 **/
		return keyDatesLablesList;
	}


}
