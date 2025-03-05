package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
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
import net.syscon.s4.inst.legals.OidhwdetRepository;
import net.syscon.s4.inst.legals.beans.Charges;
import net.syscon.s4.inst.legals.beans.HoldWarrentDetainer;
import net.syscon.s4.inst.legals.beans.HoldsWarantsHistory;

@Repository
public class OidhwdetRepositoryImpl extends RepositoryBase implements OidhwdetRepository {

	private static Logger logger = LogManager.getLogger(OumorcodRepositoryImpl.class);
	
	private final Map<String, FieldMapper> hwdMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("HWD_ID", 				new FieldMapper("holdWarrentId"))		
			.put("OFFENDER_BOOK_ID",  	new FieldMapper("offenderBookId"))
			.put("RECEIVED_DATE",  		new FieldMapper("receivedDate"))
			.put("ISSUING_AGY",  		new FieldMapper("issuingAgyLocId"))
			.put("HWD_TYPE",  			new FieldMapper("holdWarrentDetainerType"))
			.put("HWD_INFO_ID",  		new FieldMapper("warrentNumber"))
			.put("START_DATE", 			new FieldMapper("startDate"))		
			.put("EXPIRY_DATE",  		new FieldMapper("expiryDate"))
			.put("BAIL_AMOUNT",  		new FieldMapper("bailAmount"))
			.put("PROB_REVOC_FLAG",  	new FieldMapper("probRevocFlag"))
			.put("HWD_STATUS",  		new FieldMapper("holdWarrentDetainerTypeInfoIdStatus"))
			.build();
	
	private final Map<String, FieldMapper> chargesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("HWD_ID", 				new FieldMapper("holdWarrentId"))		
			.put("HWD_CHARGE_ID",  		new FieldMapper("holdWarrentDetainerChargeId"))
			.put("CHARGE_CODE",  		new FieldMapper("chargeCode"))
			.put("CHARGE_COMMENT",  	new FieldMapper("chargeComment"))
			.put("TRIED_UNTRIED_CODE",  new FieldMapper("triedUntried"))
			.put("CHARGE_STATUS",  		new FieldMapper("chargeStatus"))
			.build();
	
	private final Map<String, FieldMapper> historyMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("HWD_HTY_ID", 				new FieldMapper("holdWarrentDetainerHistoryId"))		
			.put("HWD_ID",  				new FieldMapper("holdWarrentId"))
			.put("EVENT_DATETIME",  		new FieldMapper("eventDateTime"))
			.put("EVENT_TYPE",  			new FieldMapper("eventType"))
			.put("EVENT_COMMENT",  			new FieldMapper("eventComment"))
			.put("CREATE_DATETIME",  		new FieldMapper("createDateTime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))		
			.put("MODIFY_DATETIME",  		new FieldMapper("modifyDateTime"))
			.put("MODIFY_USER_ID",  		new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG",  				new FieldMapper("sealFlag"))
			.build();

	@Override
	public List<HoldWarrentDetainer> searchHoldWarrentDetainer(Long offenderBookId) {
		List<HoldWarrentDetainer> holdWarrentDetainerData = new ArrayList<HoldWarrentDetainer>();
		final String sql = getQuery("SEARCH_HOLD_WARRENT_DETAINER");		
		final RowMapper<HoldWarrentDetainer> hwdRowMapper = Row2BeanRowMapper.makeMapping(sql,HoldWarrentDetainer.class, hwdMapping);
		try {
			holdWarrentDetainerData = namedParameterJdbcTemplate.query(sql,createParams("offenderBookId",offenderBookId), hwdRowMapper);
			} catch (Exception e) {
				logger.error("searchHoldWarrentDetainer,"+e.getMessage());
				}
		return holdWarrentDetainerData;
		}

	@Override
	public List<Charges> searchCharges(Long hwdId) {
		List<Charges> chargesData = new ArrayList<Charges>();
		final String sql = getQuery("SEARCH_CHARGES");		
		final RowMapper<Charges> chargesRowMapper = Row2BeanRowMapper.makeMapping(sql,Charges.class, chargesMapping);
		try {
			chargesData = namedParameterJdbcTemplate.query(sql,createParams("holdWarrentId",hwdId), chargesRowMapper);
			} catch (Exception e) {
				logger.error("searchCharges:,"+e.getMessage());
				}
		return chargesData;
		}
	
	@Override
	public Integer insertHwDetdata(List<HoldWarrentDetainer> insertedRecord) {
		int[] returnArray = new int[] {};
		final String sql = getQuery("INSERT_HWDET_DATA");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final HoldWarrentDetainer list : insertedRecord) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql,parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertHwDetdata : ", e);
			}
		if(insertedRecord.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
		}

	@Override
	public Integer insertHwdetCharges(List<Charges> hwDetChargesList) {
		int[] returnArray = new int[] {};
		final String sql = getQuery("INSERT_HWDET_CHARGES");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Charges list : hwDetChargesList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql,parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertHwdetCharges : ", e);
			}
		if(hwDetChargesList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
	}

	@Override
	public Integer updateHwDetdata(List<HoldWarrentDetainer> updatedRecordsList) {
		Integer liReturn = 0;
		int[] returnArray = new int[] {};
		final String sql = getQuery("UPDATE_HWDET_DATA");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final HoldWarrentDetainer hwDet : updatedRecordsList){
			parameters.add(new BeanPropertySqlParameterSource(hwDet));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("updateHwDetdata"+ e);
		}
		if(returnArray.length==updatedRecordsList.size()) {
			liReturn = 1;
		}
		return liReturn;	
	}

	@Override
	public Integer updateHwDetCharges(List<Charges> updatedHwdetChargesList) {
		Integer liReturn = 0;
		int[] returnArray = new int[] {};
		final String sql = getQuery("UPDATE_HWDET_CHARGES");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Charges hwDetChargs : updatedHwdetChargesList){
			parameters.add(new BeanPropertySqlParameterSource(hwDetChargs));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("updateHwDetCharges:"+ e);
		}
		if(returnArray.length==updatedHwdetChargesList.size()) {
			liReturn = 1;
		}
		return liReturn;	
	}

	@Override
	public Integer deleteHwdetCharges(List<Charges> deletedHwdetChargesList) {
		Integer liReturn = 0;
		int[] returnArray = new int[] {};
		final String sql = getQuery("DELETE_HWDET_CHARGES");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Charges hwDetChargs : deletedHwdetChargesList){
			parameters.add(new BeanPropertySqlParameterSource(hwDetChargs));
		}
		try {
			batchUpdatePreDeletedRows("OFFENDER_HWD_CHARGES", "HWD_CHARGE_ID=:holdWarrentDetainerChargeId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteHwdetCharges"+e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("deleteHwdetCharges:"+ e);
		}
		if(returnArray.length==deletedHwdetChargesList.size()) {
			liReturn = 1;
		}
		return liReturn;	
	}

	@Override
	public List<HoldsWarantsHistory> populateHistory(Long hwdId) {
		List<HoldsWarantsHistory> historyData = new ArrayList<HoldsWarantsHistory>();
		final String sql = getQuery("GET_HISTORY_RECORD");		
		final RowMapper<HoldsWarantsHistory> chargesRowMapper = Row2BeanRowMapper.makeMapping(sql,HoldsWarantsHistory.class, historyMapping);
		try {
			historyData = namedParameterJdbcTemplate.query(sql,createParams("hwdId",hwdId), chargesRowMapper);
			} catch (Exception e) {
				logger.error("populateHistory"+e.getMessage());
				}
		return historyData;
	}

	@Override
	public int insertHistoryRecord(List<HoldsWarantsHistory> insertList) {
		int[] returnArray = new int[] {};
		final String sql = getQuery("INSERT_HWDET_HISTORY");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final HoldsWarantsHistory list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql,parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertHistoryRecord", e);
			}
		if(insertList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
	}

	@Override
	public int updateHistoryRecord(List<HoldsWarantsHistory> updateList) {
		Integer liReturn = 0;
		int[] returnArray = new int[] {};
		final String sql = getQuery("UPDATE_HWDET_HISTORY");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final HoldsWarantsHistory history : updateList){
			parameters.add(new BeanPropertySqlParameterSource(history));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("updateHistoryRecord"+ e);
		}
		if(returnArray.length==updateList.size()) {
			liReturn = 1;
		}
		return liReturn;	
	}

	@Override
	public int deleteHistoryRecord(List<HoldsWarantsHistory> deleteList) {
		Integer liReturn = 0;
		int[] returnArray = new int[] {};
		final String sql = getQuery("DELETE_HWDET_HISTORY");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final HoldsWarantsHistory history : deleteList){
			parameters.add(new BeanPropertySqlParameterSource(history));
		}
		try {
			batchUpdatePreDeletedRows("OFFENDER_HWD_HTY", "HWD_HTY_ID=:holdWarrentDetainerHistoryId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteHistoryRecord"+e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("deleteHistoryRecord:"+ e);
		}
		if(returnArray.length==deleteList.size()) {
			liReturn = 1;
		}
		return liReturn;	
	}
	
	@Override
	public int deleteHwDetdata(List<HoldWarrentDetainer> deleteList) {
		Integer liReturn = 0;
		int[] returnArray = new int[] {};
		final String sql = getQuery("DELETE_HWDET_DATA");
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final HoldWarrentDetainer history : deleteList){
			parameters.add(new BeanPropertySqlParameterSource(history));
		}
		try {
			batchUpdatePreDeletedRows("OFFENDER_HWD", "hwd_id = :holdWarrentId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteHwDetdata"+e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("deleteHwDetdata:"+ e);
		}
		if(returnArray.length==deleteList.size()) {
			liReturn = 1;
		}
		return liReturn;	
	}

}


