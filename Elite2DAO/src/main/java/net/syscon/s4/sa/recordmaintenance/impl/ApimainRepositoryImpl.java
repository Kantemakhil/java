package net.syscon.s4.sa.recordmaintenance.impl;

import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.sa.recordmaintenance.ActionApi;
import net.syscon.s4.sa.recordmaintenance.ApimainRepository;

@Repository
public class ApimainRepositoryImpl extends RepositoryBase implements ApimainRepository {
	
	private static Logger logger = LogManager.getLogger(ProsmainRepositoryImpl.class.getName());
	public ApimainRepositoryImpl() {
		// ApimainRepositoryImpl
	}
	
	private final Map<String, FieldMapper> actionApiMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("API_DESCRIPTION",            new FieldMapper("apiDescription"))
			.put("API_ID",                     new FieldMapper("apiId"))
			.put("QUERY_KEY",                  new FieldMapper("queryKey"))
			.put("REQUEST_TYPE",               new FieldMapper("requestType"))
			.put("URL",                        new FieldMapper("url"))
			.put("QUERY_ID",                   new FieldMapper("queryId"))    
			.put("QUERY_TEXT",                 new FieldMapper("queryText"))  
			.put("QUERY_DESC",                 new FieldMapper("queryDesc"))  
			.put("ACTIVE_FLAG",                new FieldMapper("activeFlag"))
			.put("VERIFIED_BY",                new FieldMapper("verifiedBy")) 
			.put("VERIFIED_DATE",              new FieldMapper("verifiedDate"))
			.put("CREATE_DATETIME",            new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",             new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",            new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID",             new FieldMapper("modifyUserId"))
			.put("CATEGORY",             new FieldMapper("category"))
			.build();
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 						new FieldMapper("code"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.build();
	
	@Override
	public List<ActionApi> apimainExecuteQuery() {
		final String sql = getQuery("APIMAIN_EXECUTE_QUERY");
		final RowMapper<ActionApi> ProcessRowMapper = Row2BeanRowMapper.makeMapping(sql, ActionApi.class,
				actionApiMapping);
		final ArrayList<ActionApi> returnList = (ArrayList<ActionApi>) namedParameterJdbcTemplate.query(sql,
				createParams(), ProcessRowMapper);
		return returnList;
	}

	@Override
	public Integer insertActionapi(List<ActionApi> insertList) {
		String sql = getQuery("APIMAIN_INSERT_ACTION_API");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ActionApi actionApi : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(actionApi));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateActionApi(List<ActionApi> updateList) {
		String sql = getQuery("APIMAIN_UPDATE_ACTION_API");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (ActionApi actionApi : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(actionApi));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer deleteActionApi(List<ActionApi> deleteList) {
		String sql = getQuery("APIMAIN_DELETE_ACTION_API");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ActionApi actionApi : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(actionApi));
		}
		try {
			String tableName = "ACTION_API";
			String whereClause = "API_ID = :apiId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteActionApi", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<ReferenceCodes> rgQueryKeyRecordGroup() {
		final String sql = getQuery("APIMAIN_FIND_QUERY_KEY");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<ActionApi> getQuickActions() {
		final String sql = getQuery("APIMAIN_GET_QUICK_ACTIONS");
		final RowMapper<ActionApi> ProcessRowMapper = Row2BeanRowMapper.makeMapping(sql, ActionApi.class,
				actionApiMapping);
		final ArrayList<ActionApi> returnList = (ArrayList<ActionApi>) namedParameterJdbcTemplate.query(sql,
				createParams(), ProcessRowMapper);
		return returnList;
	}
}
