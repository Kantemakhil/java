package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.legals.OcdintpaRepository;
import net.syscon.s4.inst.legals.beans.InterestedParties;

@Repository
public class OcdintpaRepositoryImpl extends RepositoryBase implements OcdintpaRepository {
	
	private static Logger logger = LogManager.getLogger(OcdintpaRepositoryImpl.class);
	
	private final Map<String, FieldMapper> interestedPartiesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PARTY_ID",  new FieldMapper("partyId"))
			.put("PARTY_DESCRIPTION",  new FieldMapper("partyDescription"))
			.put("PARTY_TYPE",  new FieldMapper("partyType"))
			.put("PARTY_COMMENT",  new FieldMapper("partyComment"))
			.put("OFFENDER_BOOK_ID",  new FieldMapper("offenderBookId"))
			.put("RECORD_TYPE",  new FieldMapper("recordType"))
			.put("RECORD_ID",  new FieldMapper("recordId"))
			.build();

	@Override
	public List<InterestedParties> executeQuery(InterestedParties interestedParties) {
		
		List<InterestedParties> interestedPartiesList = new ArrayList<>();
		final String sql = getQuery("GET_INTERESTED_PARTIES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if(interestedParties!= null && interestedParties.getRecordId()!= null) {
			sqlQuery.append(" RECORD_ID = :recordId and");
			params.addValue("recordId", interestedParties.getRecordId());
		}
		if(interestedParties!= null && interestedParties.getRecordType()!= null) {
			sqlQuery.append(" RECORD_TYPE = :recordType and");
			params.addValue("recordType", interestedParties.getRecordType());
		}
		if(interestedParties!= null && interestedParties.getOffenderBookId()!= null) {
			sqlQuery.append(" OFFENDER_BOOK_ID = :offenderBookId and");
			params.addValue("offenderBookId", interestedParties.getOffenderBookId());
		}
		preparedSql = sqlQuery.toString().trim(); 
		if (preparedSql.endsWith("and")) { 
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		if (preparedSql.endsWith("where")) { 
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		preparedSql = preparedSql.concat("  order by MODIFY_DATETIME desc");
		final RowMapper<InterestedParties> ipRowMapper = Row2BeanRowMapper.makeMapping(sql,	InterestedParties.class, interestedPartiesMapping);
		try {
			interestedPartiesList=  namedParameterJdbcTemplate.query(preparedSql,params,ipRowMapper);
			} catch (Exception e) {
				logger.error("executeQuery"+e.getMessage());
			}
		return interestedPartiesList;
	}
	
	@Override
	public Integer insertInterestedParties(List<InterestedParties> insertList) {
		
		final String sql = getQuery("INSERT_INTERESTED_PARTIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final InterestedParties list : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertInterestedParties: ", e);
		}
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {

			return 0;
		}
	}

	@Override
	public Integer updateInterestedParties(List<InterestedParties> updateList) {
		
		final String sql = getQuery("UPDATE_INTERESTED_PARTIES");
		int[] returnArray = new int[] {};
		Integer result = 1;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final InterestedParties interestedParties : updateList){
			parameters.add(new BeanPropertySqlParameterSource(interestedParties));
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
	public Integer deleteInterestedParties(List<InterestedParties> deleteList) {
		
		final String sql = getQuery("DELETE_INTERESTED_PARTIES");
		int[] returnArray = new int[] {};
		Integer result = 1;
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final InterestedParties interestedParties : deleteList){
			parameters.add(new BeanPropertySqlParameterSource(interestedParties));
		}
		try {
			batchUpdatePreDeletedRows("OFFENDER_INTERESTED_PARTIES", "PARTY_ID = :partyId", parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("deleteInterestedParties"+e.getMessage());
			result = 0;
		}
		
		return result;
	}
}
