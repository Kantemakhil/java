package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.KeyDatesRepository;
import net.syscon.s4.inst.legals.beans.KeyDateValueMapping;
import net.syscon.s4.inst.legals.beans.KeyDates;

@Repository
public class KeyDatesRepositoryImpl extends RepositoryBase implements KeyDatesRepository{
	 
	private static Logger logger = LogManager.getLogger(KeyDatesRepositoryImpl.class);

	private final Map<String, FieldMapper> keyDatesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DSP_DESCRIPTION", 	new FieldMapper("keyDates"))
			.put("PROFILE_TYPE", 	new FieldMapper("profileType"))
			.put("PROFILE_TYPE_2", 	new FieldMapper("profileType2"))
			.put("DSP_LIST_SEQ", 	new FieldMapper("dispListSeq"))
			.build();
	
	@Override
	public List<KeyDates> fetchKeyDates() {
		List<KeyDates> result = new ArrayList<KeyDates>();
		final String sql = getQuery("DISPLAY_KEY_DATES");		
		final RowMapper<KeyDates> keyRowMapper = Row2BeanRowMapper.makeMapping(sql,KeyDates.class, keyDatesMapping);
		try {
			result=  namedParameterJdbcTemplate.query(sql,keyRowMapper);
			 
			} catch (Exception e) {
				logger.error("fetchKeyDates"+e.getMessage());
				
				}
		
		return result;
		}

	@Override
	public List<KeyDates> populateKeyDates(List<KeyDates> keyDatesLablesList) {
		Map<String, Date> keyDateMapping = new HashMap<String, Date>();
		Map<String, Date> overrideKeyDateMapping = new HashMap<String, Date>();
		List<String> keyDateColumn = new ArrayList<String>();		
		StringBuilder sb = new StringBuilder();
		StringBuilder overideDateColumns = new StringBuilder();
		
		Long offenderBookId = null;
		for(KeyDates keyDatesLabel : keyDatesLablesList) {			
			keyDateColumn.add(keyDatesLabel.getProfileType());
			offenderBookId = keyDatesLabel.getOffenderBookId();
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
		String queryBuiler = "SELECT "+ sb +" FROM OFFENDER_SENT_CALCULATIONS WHERE OFFENDER_SENT_CALCULATION_ID = (SELECT MAX(OFFENDER_SENT_CALCULATION_ID) FROM OFFENDER_SENT_CALCULATIONS WHERE OFFENDER_BOOK_ID ="+ offenderBookId +")";
		List<Map<String,Object>> resKeyDate = namedParameterJdbcTemplate.queryForList(queryBuiler, createParams());
		
		Map<String,Object> keyDateResultSetMap = resKeyDate.get(0);
		for(KeyDates keyDatesLabel : keyDatesLablesList) {			
			keyDatesLabel.setCalculatedDate((Date)keyDateResultSetMap.get(keyDatesLabel.getProfileType()));
		}
		
	/**
	 * Override Date Code Fetching Logic Begin
	 */
		
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
			
			String overrideDateQuery = "SELECT "+ overideDateColumns +" FROM OFFENDER_SENT_CALCULATIONS WHERE OFFENDER_SENT_CALCULATION_ID = (SELECT MAX(OFFENDER_SENT_CALCULATION_ID) FROM OFFENDER_SENT_CALCULATIONS WHERE OFFENDER_BOOK_ID ="+ offenderBookId +")";
		    List<Map<String,Object>> resOverrideDate = namedParameterJdbcTemplate.queryForList(overrideDateQuery, createParams());
			
		    Map<String,Object> overideDateResultSetMap = resOverrideDate.get(0);
		    int getIndex=-1;
			for(KeyDates overrideDatesLabel : keyDatesLablesList) {
				++getIndex;	
				overrideDatesLabel.setOverrideDate((Date)overideDateResultSetMap.get(dateColumn.get(getIndex)));
		}
		/**
		 * Override Date Code Fetching Logic End
		 */
		return keyDatesLablesList;
	}

	@Override
	public Boolean isCurfewRecordExist(Long offenderBookId) {
		return false;
	}

	@Override
	public Integer createKeyDatesHistory(List<KeyDateValueMapping> updatedKeyDatesData) {
		Integer result=1;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();		
		parameters.add(new BeanPropertySqlParameterSource(updatedKeyDatesData));
		StringBuilder column = new StringBuilder();
		StringBuilder values = new StringBuilder();
		for(KeyDateValueMapping keyDatevalueMap:  updatedKeyDatesData) {
			column.append(keyDatevalueMap.getCalculationDateColumn()+","+keyDatevalueMap.getOverrideDateColumn()+",");
			values.append("'"+keyDatevalueMap.getCalculationDateValue()+"','"+keyDatevalueMap.getOverrideDateValue()+"',");
			
		}
		String query = "Insert into offender_sent_calculations (offender_sent_calculation_id,offender_book_id,calculation_date,staff_id,"+
		column+"calc_reason_code,record_user_id,record_datetime,comment_text ) values ( NEXTVAL('offender_sent_calculation_id'),"+
		updatedKeyDatesData.get(0).getOffenderBookId()+",'"+updatedKeyDatesData.get(0).getCreateDateTime()+"',"+ updatedKeyDatesData.get(0).getStaffId()+","+
		values+"'"+updatedKeyDatesData.get(0).getCalculatedReason()+"','"+updatedKeyDatesData.get(0).getCreateUserId()+"',SYSTIMESTAMP,'"+updatedKeyDatesData.get(0).getCommentText()+"')";
		try {
			result = namedParameterJdbcTemplate.update(query, createParams());
		}catch(Exception e) {
			result = 0;
			logger.error("createKeyDatesHistory"+e.getMessage());
		}
		
		return result;
	}

	@Override
	public Integer fetchstaffId(String staffName) {
		Integer returnObj = null;
		String[] staff = staffName.split(",");
		final String sql = getQuery("FETCH_STAFF_ID_USER_NAME");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("staffName",staff[0]),Integer.class);
		return returnObj;
	}
}

