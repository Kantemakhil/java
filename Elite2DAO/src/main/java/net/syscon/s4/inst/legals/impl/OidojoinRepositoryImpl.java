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
import net.syscon.s4.inst.legals.OidojoinRepository;
import net.syscon.s4.inst.legals.beans.BailStatus;
import net.syscon.s4.inst.legals.beans.CourtCases;
import net.syscon.s4.inst.legals.beans.PriorHistory;
@Repository
public class OidojoinRepositoryImpl extends RepositoryBase implements OidojoinRepository{
	private static Logger logger = LogManager.getLogger(OidojoinRepositoryImpl.class);
	final Map<String, FieldMapper> priorHistoryListMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.put("ORDER_TYPE", 					new FieldMapper("type"))
			.put("OFFENSE_DESC",   			new FieldMapper("offenseDescription"))
			.put("OFFENSE_STATUS", 				new FieldMapper("status"))
			.put("SOURCE", 			new FieldMapper("source"))
			.put("COUNTY", 			new FieldMapper("country"))
			.put("STATE",			 		new FieldMapper("state"))
			.put("VERIFIED",			 		new FieldMapper("verified"))
			.put("COMMENT_TEXT",			 		new FieldMapper("comment"))
			.put("OFFENSE_SEQ",			 		new FieldMapper("offenseSeq"))
			.put("OFFENSE_DATE",  		    new FieldMapper("priorHistoryDate")).build();
	
	private final Map<String, FieldMapper> orderTypeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 	  new FieldMapper("description"))			
			.put("CODE",  			  new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> statusMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 	  new FieldMapper("description"))			
			.put("CODE",  			  new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> sourceMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 	  new FieldMapper("description"))			
			.put("CODE",  			  new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> countyMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 	  new FieldMapper("description"))			
			.put("CODE",  			  new FieldMapper("code")).build();
	
	private final Map<String, FieldMapper> stateMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 	  new FieldMapper("description"))			
			.put("CODE",  			  new FieldMapper("code")).build();
	@Override
	public List<PriorHistory> populateGridData(CourtCases offenderCases) {
		List<PriorHistory> gridDataList = new ArrayList<PriorHistory>();
		final String sql = getQuery("PRIOR_HISTORY_GRID_DATA");
		final RowMapper<PriorHistory> priorHistoryRowMapper = Row2BeanRowMapper.makeMapping(sql,PriorHistory.class, priorHistoryListMapping);
		try {
			gridDataList=  namedParameterJdbcTemplate.query(sql,createParams("offenderBookId", offenderCases.getOffenderBookId()),priorHistoryRowMapper);
			} catch (Exception e) {
				logger.error("grid data exception"+e.getMessage());
		}
		return gridDataList;
	} 
	@Override
	public List<BailStatus> populateOrderType() {
		List<BailStatus> orderTypeList = new ArrayList<BailStatus>();
		final String sql = getQuery("PRIOR_ORDER_TYPE_DATA");
		final RowMapper<BailStatus> orderTypeRowMapper = Row2BeanRowMapper.makeMapping(sql,BailStatus.class, orderTypeMapping);
		try {
			orderTypeList=  namedParameterJdbcTemplate.query(sql,orderTypeRowMapper);
			} catch (Exception e) {
				logger.error("order type exception"+e.getMessage());
		}
		return orderTypeList;
	} 
	@Override
	public List<BailStatus> populateStatus() {
		List<BailStatus> orderTypeList = new ArrayList<BailStatus>();
		final String sql = getQuery("PRIOR_STATUS_DATA");
		final RowMapper<BailStatus> statusRowMapper = Row2BeanRowMapper.makeMapping(sql,BailStatus.class, statusMapping);
		try {
			orderTypeList=  namedParameterJdbcTemplate.query(sql,statusRowMapper);
			} catch (Exception e) {
				logger.error("populateStatus exception"+e.getMessage());
		}
		return orderTypeList;
	} 
	@Override
	public List<BailStatus> populateSource() {
		List<BailStatus> sourceList = new ArrayList<BailStatus>();
		final String sql = getQuery("PRIOR_SOURCE_DATA");
		final RowMapper<BailStatus> sourceRowMapper = Row2BeanRowMapper.makeMapping(sql,BailStatus.class, sourceMapping);
		try {
			sourceList=  namedParameterJdbcTemplate.query(sql,sourceRowMapper);
			} catch (Exception e) {
				logger.error("source type exception"+e.getMessage());
		}
		return sourceList;
	} 
	
	@Override
	public List<BailStatus> populateCounty() {
		List<BailStatus> countyList = new ArrayList<BailStatus>();
		final String sql = getQuery("PRIOR_COUNTY_DATA");
		final RowMapper<BailStatus> countyRowMapper = Row2BeanRowMapper.makeMapping(sql,BailStatus.class, countyMapping);
		try {
			countyList=  namedParameterJdbcTemplate.query(sql,countyRowMapper);
			} catch (Exception e) {
				logger.error("county type exception"+e.getMessage());
		}
		return countyList;
	}
	
	@Override
	public List<BailStatus> populateState() {
		List<BailStatus> stateList = new ArrayList<BailStatus>();
		final String sql = getQuery("PRIOR_STATE_DATA");
		final RowMapper<BailStatus> stateRowMapper = Row2BeanRowMapper.makeMapping(sql,BailStatus.class, stateMapping);
		try {
			stateList=  namedParameterJdbcTemplate.query(sql,stateRowMapper);
			} catch (Exception e) {
				logger.error("state type exception"+e.getMessage());
		}
		return stateList;
	}
	@Override
	public Integer insertPriorHistoryData(List<PriorHistory> insertList) {
		final String sql = getQuery("INSERT_PRIOR_HISTORY_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PriorHistory list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Insert PriorHistory Exception : ", e);
			}
		if(insertList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
		}
	@Override
	public Integer updatePriorHistoryData(List<PriorHistory> updateList) {
		final String sql = getQuery("UPDATE_PRIOR_HISTORY_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PriorHistory list : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Update PriorHistory Exception : ", e);
			}
		if(updateList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
		}
	@Override
	public Integer deletePriorHistoryData(List<PriorHistory> deleteList) {
		final String sql = getQuery("DELETE_PRIOR_HISTORY_RECORD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PriorHistory list : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			}
		try {
			batchUpdatePreDeletedRows("OFFENDER_OFFENCES", "OFFENDER_BOOK_ID = :offenderBookId AND OFFENSE_SEQ =:offenseSeq", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in deletePriorHistoryData"+e);
		}
		
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("deletePriorHistoryData Exception : ", e);
			}
		if(deleteList.size() == returnArray.length) {
			return 1;
			} else {
				return 0;
				}
		}
	@Override
	public Integer getPreInsertOffenseSeq(Long offenderBookId) {
		Integer returnObj=0;
		final String sql = getQuery("OIDOJOIN_NEWRECORD_PREINSERT_OFFENSE_SEQ");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId),Integer.class);
		return returnObj;
		}

}
