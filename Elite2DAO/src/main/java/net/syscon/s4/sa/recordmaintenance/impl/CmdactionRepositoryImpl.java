package net.syscon.s4.sa.recordmaintenance.impl;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SentenceCalcTypes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.sa.recordmaintenance.ActionApi;
import net.syscon.s4.sa.recordmaintenance.AutomationApiQuery;
import net.syscon.s4.sa.recordmaintenance.AutomationQueryParameters;
import net.syscon.s4.sa.recordmaintenance.CmdactionRepository;

@Repository
public class CmdactionRepositoryImpl extends RepositoryBase implements CmdactionRepository{
	public CmdactionRepositoryImpl() {
		// CmdactionRepositoryImpl
	}
	
	private static Logger logger = LogManager.getLogger(CmdactionRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> quickActionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACTIVE_FLAG",               new FieldMapper("activeFlag"))    
			.put("CREATE_DATETIME",           new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",            new FieldMapper("createUserId")) 
			.put("MODIFY_DATETIME",           new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID",            new FieldMapper("modifyUserId")) 
			.put("QUERY_DESC",                new FieldMapper("queryDesc"))     
			.put("QUERY_ID",                  new FieldMapper("queryId"))       
			.put("QUERY_KEY",                 new FieldMapper("queryKey"))    
			.put("API_ID",                 	  new FieldMapper("apiId"))  
			.put("QUERY_TEXT",                new FieldMapper("queryText"))     
			.put("VERIFIED_BY",               new FieldMapper("verifiedBy"))    
			.put("VERIFIED_DATE",             new FieldMapper("verifiedDate")) 
			.put("CATEGORY",             new FieldMapper("category"))  
			.build();
	
	private final Map<String, FieldMapper> parametersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CREATE_DATETIME",            new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",             new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",            new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID",             new FieldMapper("modifyUserId")) 
			.put("PARAMETER_ID",               new FieldMapper("parameterId"))   
			.put("PARAMETER_NAME",             new FieldMapper("parameterName")) 
			.put("PARAMETER_TYPE",             new FieldMapper("parameterType")) 
			.put("QUERY_KEY",                  new FieldMapper("queryKey"))   
			.put("API_ID",                     new FieldMapper("apiId")) 
			.build();  
	
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
			.build();
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 						new FieldMapper("code"))
			.put("DESCRIPTION", 				new FieldMapper("description"))
			.build();
	
	private final Map<String, FieldMapper> sentCalcMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("HEADER_SEQ", 						new FieldMapper("headerSeq"))
			.put("HEADER_LABEL", 					new FieldMapper("headerLabel"))
			.put("SENTENCE_CATEGORY",               new FieldMapper("sentenceCategory"))  
			.put("SENTENCE_CALC_TYPE",              new FieldMapper("sentenceCalcType"))  
			.put("SENTENCE_TYPE",                   new FieldMapper("sentenceType"))      
			.build();
	
	private final Map<String, FieldMapper> bookingsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("BOOKING_TYPE", 					new FieldMapper("bookingType"))
			.put("BOOKING_STATUS", 					new FieldMapper("bookingStatus"))
			.put("ROOT_OFFENDER_ID", 				new FieldMapper("rootOffenderId"))
			.build();
	
	@Override
	public List<AutomationApiQuery> quickActionsExecuteQuery() {
		final String sql = getQuery("CMDACTION_QUICK_ACTIONS_EXECUTE_QUERY");
		final RowMapper<AutomationApiQuery> quickActionsRowMapper = Row2BeanRowMapper.makeMapping(sql, AutomationApiQuery.class,
				quickActionsMapping);
		final ArrayList<AutomationApiQuery> returnList = (ArrayList<AutomationApiQuery>) namedParameterJdbcTemplate.query(sql,
				createParams(), quickActionsRowMapper);
		return returnList;
	}

	@Override
	public Integer insertQuickActions(List<AutomationApiQuery> insertList) {
		logger.info(this.getClass().getName(), "method: insertQuickActions insertList : {}", insertList);
		String sql = getQuery("CMDACTION_INSERT_QUICK_ACTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (final AutomationApiQuery automationApiQuery : insertList) {
				parameters.add(new BeanPropertySqlParameterSource(automationApiQuery));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (insertList.size() == returnArray.length) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			logger.info(this.getClass().getName(), "method: insertQuickActions Exception : {}", e);
			return 0;
		}
	}

	@Override
	public Integer updateQuickActions(List<AutomationApiQuery> updateList) {
		logger.info(this.getClass().getName(), "method: updateQuickActions updateList : {}", updateList);
		String sql = getQuery("CMDACTION_UPDATE_QUICK_ACTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (AutomationApiQuery automationApiQuery : updateList) {
				parameters.add(new BeanPropertySqlParameterSource(automationApiQuery));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (updateList.size() == returnArray.length) {
				return 1;
			} else {
				return 0;
			}
		} catch(Exception e) {
			logger.error(this.getClass().getName(), "method: updateQuickActions Exception : {}", e);
			return 0;
		}
	}

	@Override
	public Integer deleteQuickActions(List<AutomationApiQuery> deleteList) {
		String sql = getQuery("CMDACTION_DELETE_QUICK_ACTIONS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AutomationApiQuery automationApiQuery : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(automationApiQuery));
		}
		try {
			String tableName = "AUTOMATION_API_QUERY";
			String whereClause = "QUERY_ID = :queryId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteQuickActions", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<AutomationQueryParameters> parametersExecuteQuery(AutomationApiQuery searchBean) {
		final String sql = getQuery("CMDACTION_PARAMETERS_EXECUTE_QUERY");
		final RowMapper<AutomationQueryParameters>parametersRowMapper = Row2BeanRowMapper.makeMapping(sql, AutomationQueryParameters.class,
				parametersMapping);
		final ArrayList<AutomationQueryParameters> returnList = (ArrayList<AutomationQueryParameters>) namedParameterJdbcTemplate.query(sql,
				createParams("queryKey", searchBean.getQueryKey()), parametersRowMapper);
		return returnList;
	}

	@Override
	public Integer insertParameters(List<AutomationQueryParameters> insertList) {
		logger.info(this.getClass().getName(), "method: insertParameters insertList : {}", insertList);
		String sql = getQuery("CMDACTION_INSERT_PARAMETERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (final AutomationQueryParameters automationQueryParameters : insertList) {
				parameters.add(new BeanPropertySqlParameterSource(automationQueryParameters));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (insertList.size() == returnArray.length) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName(), "method: insertParameters Exception : {}", e);
			return 0;
		}
	}

	@Override
	public Integer updateParameters(List<AutomationQueryParameters> updateList) {
		logger.info(this.getClass().getName(), "method: updateParameters updateList : {}", updateList);
		String sql = getQuery("CMDACTION_UPDATE_PARAMETERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (AutomationQueryParameters automationQueryParameters : updateList) {
				parameters.add(new BeanPropertySqlParameterSource(automationQueryParameters));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (updateList.size() == returnArray.length) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName(), "method: updateParameters Exception : {}", e);
			return 0;
		}
	}

	@Override
	public Integer deleteParameters(List<AutomationQueryParameters> deleteList) {
		String sql = getQuery("CMDACTION_DELETE_PARAMETERS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final AutomationQueryParameters automationQueryParameters : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(automationQueryParameters));
		}
		try {
			String tableName = "AUTOMATION_QUERY_PARAMETERS";
			String whereClause = "PARAMETER_ID = :parameterId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteParameters", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public String getActionQuery(String queryKey) {
		final String sql = getQuery("CMDACTION_GET_ACTION_QUERY");
		return namedParameterJdbcTemplate.queryForObject(sql,createParams("queryKey",queryKey),String.class);
	}

	@Override
	public List<AutomationQueryParameters> getParamList(String queryKey) {
		final String sql = getQuery("CMDACTION_GET_PARAM_LIST");
		final RowMapper<AutomationQueryParameters>parametersRowMapper = Row2BeanRowMapper.makeMapping(sql, AutomationQueryParameters.class,
				parametersMapping);
		final ArrayList<AutomationQueryParameters> returnList = (ArrayList<AutomationQueryParameters>) namedParameterJdbcTemplate.query(sql,
				createParams("queryKey", queryKey), parametersRowMapper);
		return returnList;
	}

	@Override
	public Integer executeUpdateQuery(Map<String, Object> queryData, String query) {
		try {
			return namedParameterJdbcTemplate.update(query, queryData);
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> executeSelectQuery(Map<String, Object> objMap, String query) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try {
			result = namedParameterJdbcTemplate.queryForList(query, objMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ReferenceCodes> rgApiIdRecordGroup() {
		final String sql = getQuery("CMDACTION_FIND_API_ID");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Integer batchUpdateQuery(List<Map<String, Object>> queryData, String query) {
		int response[] = null; 
		try {
			response = namedParameterJdbcTemplate.batchUpdate(query, queryData.toArray(new HashMap[queryData.size()]));
		} catch(Exception e) {
			return 0;
		}
		
		return 1;
	}

	@Override
	public List<ActionApi> preDelete(AutomationApiQuery automationApiQuery) {
		final String sql = getQuery("CMDACTION_PRE_DELETE");
		final RowMapper<ActionApi> quickActionsRowMapper = Row2BeanRowMapper.makeMapping(sql, ActionApi.class,
				actionApiMapping);
		final ArrayList<ActionApi> returnList = (ArrayList<ActionApi>) namedParameterJdbcTemplate.query(sql,
				createParams("queryKey", automationApiQuery.getQueryKey()), quickActionsRowMapper);
		return returnList;
	}
	
	@Override
	public byte[] templateText(Map<String, Object> objMap, String query) {
		return namedParameterJdbcTemplate.queryForObject(query,objMap, byte[].class);
	}
	
	@Override
	public List<AutomationApiQuery> getquickAction(String queryKey) {
		final String sql = getQuery("CMDACTION_GET_QUICK_ACTION");
		final RowMapper<AutomationApiQuery> quickActionsRowMapper = Row2BeanRowMapper.makeMapping(sql, AutomationApiQuery.class,
				quickActionsMapping);
		final ArrayList<AutomationApiQuery> returnList = (ArrayList<AutomationApiQuery>) namedParameterJdbcTemplate.query(sql,
				createParams("queryKey", queryKey), quickActionsRowMapper);
		return returnList;
	}

	@Override
	public List<AutomationQueryParameters> getActionParams(String queryKey) {
		final String sql = getQuery("CMDACTION_PARAMETERS_EXECUTE_QUERY");
		final RowMapper<AutomationQueryParameters>parametersRowMapper = Row2BeanRowMapper.makeMapping(sql, AutomationQueryParameters.class,
				parametersMapping);
		final ArrayList<AutomationQueryParameters> returnList = (ArrayList<AutomationQueryParameters>) namedParameterJdbcTemplate.query(sql,
				createParams("queryKey", queryKey), parametersRowMapper);
		return returnList;
	}
	
	@Override
	public List<AutomationApiQuery> getQuickAction(String queryKey) {
		logger.info(this.getClass().getName(), "method: getQuickAction queryKey : {}", queryKey);
		final String sql = getQuery("CMDACTION_GET_QUICK_ACTION");
		ArrayList<AutomationApiQuery> returnList = null;
		final RowMapper<AutomationApiQuery> quickActionsRowMapper = Row2BeanRowMapper.makeMapping(sql, AutomationApiQuery.class,
				quickActionsMapping);
		try {
			returnList = (ArrayList<AutomationApiQuery>) namedParameterJdbcTemplate.query(sql,
					createParams("queryKey", queryKey), quickActionsRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName(), "method: getQuickAction Exception : {}", e);
		}
		return returnList;
	}

	@Override
	public List<AutomationQueryParameters> getActionParameters(String queryKey) {
		logger.info(this.getClass().getName(), "method: getActionParameters queryKey : {}", queryKey);
		final String sql = getQuery("CMDACTION_PARAMETERS_EXECUTE_QUERY");
		ArrayList<AutomationQueryParameters> returnList = null;
		final RowMapper<AutomationQueryParameters>parametersRowMapper = Row2BeanRowMapper.makeMapping(sql, AutomationQueryParameters.class,
				parametersMapping);
		try {
			returnList = (ArrayList<AutomationQueryParameters>) namedParameterJdbcTemplate.query(sql,
					createParams("queryKey", queryKey), parametersRowMapper);
		} catch(Exception e) {
			logger.error(this.getClass().getName(), "method: getActionParameters Exception : {}", e);
		}
		return returnList;
	}

	@Override
	public List<ActionApi> getActionApi(String queryKey) {
		logger.info(this.getClass().getName(), "method: getActionApi queryKey : {}", queryKey);
		final String sql = getQuery("CMDACTION_GET_ACTION_API");
		ArrayList<ActionApi> returnList = null;
		final RowMapper<ActionApi>actionApiRowMapper = Row2BeanRowMapper.makeMapping(sql, ActionApi.class,
				actionApiMapping);
		try {
			returnList = (ArrayList<ActionApi>) namedParameterJdbcTemplate.query(sql,
					createParams("queryKey", queryKey), actionApiRowMapper);
		} catch(Exception e) {
			logger.error(this.getClass().getName(), "method: getActionApi Exception : {}", e);
		}
		return returnList;
	}

	@Override
	public Integer insertActionApi(List<ActionApi> apiInsertList) {
		logger.info(this.getClass().getName(), "method: insertActionApi : {}", apiInsertList);
		String sql = getQuery("CMDACTION_INSERT_ACTION_API");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> actionApiList = new ArrayList<SqlParameterSource>();
		try {
			for (final ActionApi actionApi : apiInsertList) {
				actionApiList.add(new BeanPropertySqlParameterSource(actionApi));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, actionApiList.toArray(new SqlParameterSource[0]));
			if (apiInsertList.size() == returnArray.length) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName(), "method: insertActionApi Exception : {}", e);
			return 0;
		}
	}

	@Override
	public Integer updateActionApi(List<ActionApi> apiUpdateList) {
		logger.info(this.getClass().getName(), "method: updateActionApi : {}", apiUpdateList);
		String sql = getQuery("CMDACTION_UPDATE_ACTION_API");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> actionApiList = new ArrayList<SqlParameterSource>();
		try {
			for (ActionApi actionApi : apiUpdateList) {
				actionApiList.add(new BeanPropertySqlParameterSource(actionApi));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, actionApiList.toArray(new SqlParameterSource[0]));
			if (apiUpdateList.size() == returnArray.length) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName(), " method: updateActionApi Exception : {}", e);
			return 0;
		}
	}
	
	@Override
	public Integer deleteParams(List<AutomationApiQuery> automationApiQueryList) {
		String sql = getQuery("CMDACTION_DELETE_PARAMS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (AutomationApiQuery automationApiQuery : automationApiQueryList) {
				parameters.add(new BeanPropertySqlParameterSource(automationApiQuery));
			}
			try {
				String tableName = "AUTOMATION_QUERY_PARAMETERS";
				String whereClause = "QUERY_KEY = :queryKey";
				batchUpdatePreDeletedRows(tableName, whereClause , parameters);
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " in method deleteParams", e);
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (returnArray.length > 0) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName(), "deleteParams and  Exception : {}", e);
			return 0;
		}
	}


	@Override
	public SentenceCalcTypes getSentenceCalcTypes(SentenceCalcTypes sentenceCalcTypes) {
		final String sql = getQuery("CMDACTION_GET_SENTENCE_CALC_TYPES");
		SentenceCalcTypes returnObj = new SentenceCalcTypes();
		try {
			final RowMapper<SentenceCalcTypes> sentCalcRowMapper = Row2BeanRowMapper.makeMapping(sql, SentenceCalcTypes.class,
					sentCalcMapping);
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("sentenceCategory", sentenceCalcTypes.getSentenceCategory(),
					"sentenceCalcType", sentenceCalcTypes.getSentenceCalcType()), sentCalcRowMapper);
		} catch(Exception e) {
			logger.error(this.getClass().getName(), " getSentenceCalcTypes and  Exception ", e);
		}
		return returnObj;
	}
	
	@Override
	public OffenderBookings getBookingData(long offenderBookId) {
		final String sql = getQuery("CMDACTION_GET_BOOKING_TYPE");
		OffenderBookings returnObj = new OffenderBookings();
		try {
			final RowMapper<OffenderBookings> bookingsRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderBookings.class, bookingsMapping);
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", offenderBookId), bookingsRowMapper);
		} catch(Exception e) {
			logger.error(this.getClass().getName(), " getBookingType and  Exception ", e);
		}
		return returnObj;
	}

	@Override
	public Integer updateSentStatus(List<OffenderBookings> offenderBookingsList) {
		logger.info(this.getClass().getName(), "method: updateSentStatus updateList : {}", offenderBookingsList);
		String sql = getQuery("CMDACTION_UPDATE_SENT_STATUS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (OffenderBookings offenderBookings : offenderBookingsList) {
				parameters.add(new BeanPropertySqlParameterSource(offenderBookings));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (offenderBookingsList.size() == returnArray.length) {
				return 1;
			} else {
				return 0;
			}
		} catch(Exception e) {
			logger.error(this.getClass().getName(), " updateSentStatus and  Exception ", e);
			return 0;
		}
	}

}
