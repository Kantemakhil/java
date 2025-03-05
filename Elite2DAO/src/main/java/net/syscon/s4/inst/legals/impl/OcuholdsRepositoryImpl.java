package net.syscon.s4.inst.legals.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.OcuholdsRepository;
import net.syscon.s4.inst.legals.beans.Court;
import net.syscon.s4.inst.legals.beans.HoldStatus;
import net.syscon.s4.inst.legals.beans.Holds;
import net.syscon.s4.inst.legals.beans.OrderType;


@Repository
public class OcuholdsRepositoryImpl extends RepositoryBase implements OcuholdsRepository{

	private static Logger logger = LogManager.getLogger(OcuholdsRepositoryImpl.class);
	final Map<String, FieldMapper> holdsListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORDER_TYPE", 					new FieldMapper("orderType"))
			.put("NBT_COURT_DESC",  		    new FieldMapper("issuingAgyLocId"))
			.put("COURT_DATE", 					new FieldMapper("orderDate"))
			.put("COMPLETE_DATE",   			new FieldMapper("expiryDate"))
			.put("COMMENT_TEXT", 				new FieldMapper("commentText"))
			.put("NBT_STATUS_DESC", 			new FieldMapper("orderStatus"))
			.put("OFFENDER_BOOK_ID", 			new FieldMapper("offenderBookId"))
			.put("ORDER_ID",			 		new FieldMapper("orderId")).build();

	final Map<String, FieldMapper> orderTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("description", 				new FieldMapper("description"))			
			.put("report_type",  			    new FieldMapper("code")).build();
	
	final Map<String, FieldMapper> holdStatusMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("description", 				new FieldMapper("description"))			
			.put("code",  			        	new FieldMapper("code")).build();
	
	final Map<String, FieldMapper> courtListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("description", 				new FieldMapper("description"))			
			.put("agy_loc_id",  				new FieldMapper("code")).build();
	
	
	@Override
	public List<Holds> populateHoldsData(Integer eventId) {
		List<Holds> holdsList = new ArrayList<Holds>();
		final String sql = getQuery("DISPLAY_HOLDS_REMANDS");
		final RowMapper<Holds> holdDataRowMapper = Row2BeanRowMapper.makeMapping(sql,Holds.class, holdsListMapping);
		try {
			holdsList=  namedParameterJdbcTemplate.query(sql,createParams("eventId" ,eventId),holdDataRowMapper);
			} catch (Exception e) {
				logger.error("populateHoldsData"+e.getMessage());
		}
		return holdsList;
	} 

	@Override
	public List<OrderType> orderType() {
		List<OrderType> orderList = new ArrayList<OrderType>();
		final String sql = getQuery("LOV_ORDER_TYPE");
		final RowMapper<OrderType> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,OrderType.class, orderTypeMapping);
		try {
			orderList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("orderType"+e.getMessage());
		}
		return orderList;
	}
	
	@Override
	public List<HoldStatus> populateHoldStatus() {
		List<HoldStatus> holdStatusList = new ArrayList<HoldStatus>();
		final String sql = getQuery("LOV_HOLD_STATUS");
		final RowMapper<HoldStatus> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,HoldStatus.class, holdStatusMapping);
		try {
			holdStatusList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateHoldStatus"+e.getMessage());
		}
		return holdStatusList;
	} 

	@Override
	public List<Court> populateCourtData() {
		List<Court> courtList = new ArrayList<Court>();
		final String sql = getQuery("COURT_DATA");	
		final RowMapper<Court> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,	Court.class, courtListMapping);
		try {
			courtList=  namedParameterJdbcTemplate.query(sql,referenceCodeRowMapper);
			} catch (Exception e) {
				logger.error("populateCourtData"+e.getMessage());
		}
		return courtList;
	}

	@Override
	public Integer insertHoldData(List<Holds> holdsBeanCommit) {
		final String sql = getQuery("INSERT_HOLDS_DATA");
		int[] returnArray = new int[] {};
		Integer result = 1;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Holds list : holdsBeanCommit){
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertHoldData", e);
			result = 0;
		}
		return result;
	}
	
	@Override
	public Integer updateHoldData(List<Holds> holdsBeanCommit) {
		final String sql = getQuery("UPDATE_HOLDS_DATA");
		final String courtEventUpdateSql = getQuery("UPDATE_EVENT_FOR_HOLDS_DATA");
		int[] returnArray = new int[] {};
		int[] eventAffectedRows = new int[] {};
		Integer result = 0;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Holds list : holdsBeanCommit){
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			eventAffectedRows=namedParameterJdbcTemplate.batchUpdate(courtEventUpdateSql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("updateHoldData", e);
			result = 0;
		}
		if(returnArray.length==holdsBeanCommit.size() && eventAffectedRows.length==holdsBeanCommit.size()) {
			result=1;	
		}
		return result;
	}	
	

	@Override
	public Integer getPreInsertOrderId() {
		Integer returnObj = null;
		final String sql = getQuery("PREINSERT_ORDER_ID");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql,createParams(), Integer.class);
		return returnObj;
	}

	@Override
	public Integer calcexpdate(String orderType) {
		Integer result = 0;
		final String sql = getQuery("CALC_EXP_DATE");
		try {
		result = namedParameterJdbcTemplate.queryForObject(sql,createParams("orderType",orderType), Integer.class);
		}catch (Exception e){
			logger.error("calcexpdate", e);
		}
		return result;
	}

	@Override
	public Integer deleteHoldData(final List<Holds> deleteList) {
		final String sql = getQuery("DELETE_HOLDS_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (Holds list : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			batchUpdatePreDeletedRows("ORDERS", "order_id = :orderId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deleteHoldData"+e);
		}
		try{
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (EmptyResultDataAccessException e){
			logger.error("deleteHoldData", e);
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
}	
