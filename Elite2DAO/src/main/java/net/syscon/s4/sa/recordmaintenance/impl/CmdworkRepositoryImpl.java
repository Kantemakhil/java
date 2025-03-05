package net.syscon.s4.sa.recordmaintenance.impl;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.sa.recordmaintenance.CmdworkRepository;
import net.syscon.s4.sa.recordmaintenance.ModuleTriggers;
import net.syscon.s4.sa.recordmaintenance.BpmnProcess;
import net.syscon.s4.sa.recordmaintenance.CmdModules;
import net.syscon.s4.sa.recordmaintenance.WorkItems;

@Repository
public class CmdworkRepositoryImpl extends RepositoryBase implements CmdworkRepository{
	
	public CmdworkRepositoryImpl() {
		// CmdworkRepositoryImpl
	}
	
	private static Logger logger = LogManager.getLogger(CmdworkRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> referenceMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 		new FieldMapper("description"))
			.put("CODE", 				new FieldMapper("code"))
			.build();
	private final Map<String, FieldMapper> cmdModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 		new FieldMapper("description"))
			.put("CODE", 				new FieldMapper("code"))
			.build();
	private final Map<String, FieldMapper> cmdModuleTriggersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("CODE", 					new FieldMapper("code"))
			.put("MODULE_NAME", 			new FieldMapper("moduleName"))
			.put("MODULE_DESCRIPTION", 		new FieldMapper("moduleDescription"))
			.put("TRIGGER_NAME", 			new FieldMapper("triggerName"))
			.put("TRIGGER_ID", 				new FieldMapper("triggerId"))
			.put("DTO_NAME", 				new FieldMapper("dtoName"))
			.build();
	private final Map<String, FieldMapper> bpmnProcessMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROCESS_ID", 			new FieldMapper("processId"))
			.put("PROCESS_DESC", 		new FieldMapper("processDesc"))
			.put("DESCRIPTION", 		new FieldMapper("description"))
			.put("CODE", 				new FieldMapper("code"))
			.build();
	private final Map<String, FieldMapper> workItemsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("WORK_ID",				new FieldMapper("workId"))
			.put("BUSINESS_FUNCTION",	new FieldMapper("businessFunction"))
			.put("MODULE",				new FieldMapper("module"))
			.put("TRIGGER_ID",			new FieldMapper("triggerId"))
			.put("PROCESS",				new FieldMapper("process"))
			.put("ADD_TRIGGER",			new FieldMapper("addTrigger"))
			.put("UPDATE_TRIGGER",		new FieldMapper("updateTrigger"))
			.put("DELETE_TRIGGER",		new FieldMapper("deleteTrigger"))
			.put("CREATE_DATETIME",		new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",		new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",		new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 		new FieldMapper("modifyUserId"))
			.build();

	@Override
	public List<ReferenceCodes> rgWorkTypeRecordGroup() {
		final String sql = getQuery("CMDWORK_FIND_RGWORKTYPE");
		final RowMapper<ReferenceCodes> referenceRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, referenceMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception rgWorkTypeRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<CmdModules> rgModulesRecordGroup(String columnName) {
		String sql = null;
		if(columnName!=null && columnName.equals("PROCESS_WORKFLOW")) {
			 sql = getQuery("CMDWORK_FIND_RGMODULES_PROCESS_WORKFLOW");
		}else if(columnName!=null && columnName.equals("USER_TASK")) {
			sql = getQuery("CMDWORK_FIND_RGMODULES_USER_TASK");
		}
		
		final RowMapper<CmdModules> cmdModulesRowMapper = Row2BeanRowMapper.makeMapping(sql, CmdModules.class, cmdModulesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), cmdModulesRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception rgModulesRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<BpmnProcess> rgProcessRecordGroup() {
		final String sql = getQuery("CMDWORK_FIND_RGPROCESS");
		final RowMapper<BpmnProcess> referenceRowMapper = Row2BeanRowMapper.makeMapping(sql, BpmnProcess.class, bpmnProcessMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), referenceRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception rgProcessRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<WorkItems> workItemsExecuteQuery() {
		final String sql = getQuery("CMDWORK_WORKITEMS_EXECUTE_QUERY");
		final RowMapper<WorkItems> WorkItemsRowMapper = Row2BeanRowMapper.makeMapping(sql, WorkItems.class,
				workItemsMapping);
		final ArrayList<WorkItems> returnList = (ArrayList<WorkItems>) namedParameterJdbcTemplate.query(sql,
				createParams(), WorkItemsRowMapper);
		return returnList;
	}

	@Override
	public Integer insertWorkItems(List<WorkItems> insertWorkItemsList) {
		String sql = getQuery("CMDWORK_INSERT_WORKITEMS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WorkItems workItems : insertWorkItemsList) {
			long workId = workItemsPreInsert();
			workItems.setWorkId(workId);
			parameters.add(new BeanPropertySqlParameterSource(workItems));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertWorkItemsList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public long workItemsPreInsert() {
		final String sql = getQuery("CMDWORK_WORKITEMS_PREINSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), long.class);
	}

	@Override
	public Integer updateWorkItems(List<WorkItems> updateWorkItemsList) {
		String sql = getQuery("CMDWORK_UPDATE_WORKITEMS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (WorkItems workItems : updateWorkItemsList) {
			parameters.add(new BeanPropertySqlParameterSource(workItems));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateWorkItemsList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer deleteWorkItems(List<WorkItems> deleteWorkItemsList) {
		String sql = getQuery("CMDWORK_DELETE_WORKITEMS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final WorkItems workItems : deleteWorkItemsList) {
			parameters.add(new BeanPropertySqlParameterSource(workItems));
		}
		try {
			String tableName = "WORK_ITEMS";
			String whereClause = "WORK_ID = :workId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteWorkItems", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (deleteWorkItemsList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Long getWorkId(Long processId) {
		Long workId;
		final String sql = getQuery("CMDWORK_GET_WORK_ID");
		try {
			workId= namedParameterJdbcTemplate.queryForObject(sql, createParams("processId", processId), long.class);
		} catch (Exception e) {
			return null;
		}
		return workId;
	}
	
	@Override
	public String getRelatedDto(String moduleName) {
		String dto = "";
		final String sql = getQuery("CMDWORK_GET_RELATED_DTO");
		try{
			dto = namedParameterJdbcTemplate.queryForObject(sql, createParams("moduleName", moduleName), String.class);
		} catch (Exception e) {
			return dto;
		}
		return dto;
	}

	@Override
	public List<ModuleTriggers> rgModuleTriggersRecordGroup() {
		final String sql = getQuery("CMDWORK_RGMODULE_TRIGGERS_RECORD_GROUP");
		final RowMapper<ModuleTriggers> cmdModuleTriggersRowMapper = Row2BeanRowMapper.makeMapping(sql, ModuleTriggers.class, cmdModuleTriggersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), cmdModuleTriggersRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception rgModuleTriggersRecordGroup:", e);
			return Collections.emptyList();
		}
	}

	@Override
	public Integer deleteTrigger(String triggerId, String modifyUserId) {
		int result = 0;
		final String sql = getQuery("CMDWORK_DELETE_TRIGGER");
		try {
			String tableName = "WORK_ITEMS";
			String whereClause = "TRIGGER_ID = :triggerId";
			Map<String , Object> inputMap = new HashMap<String, Object>();
			inputMap.put("triggerId", triggerId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereClause , inputMap);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteTrigger", e);
		}
		try{
			result = namedParameterJdbcTemplate.update(sql, createParams("triggerId", triggerId));
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	@Override
	public List<BpmnProcess> validateDeploy(BpmnProcess bpmnProcess) {
		final String sql = getQuery("CMDWORK_VALIDATE_DEPLOY");
		final RowMapper<BpmnProcess> WorkItemsRowMapper = Row2BeanRowMapper.makeMapping(sql, BpmnProcess.class,
				bpmnProcessMapping);
		final ArrayList<BpmnProcess> returnList = (ArrayList<BpmnProcess>) namedParameterJdbcTemplate.query(sql,
				createParams("triggerId", bpmnProcess.getTriggerId(),"process",bpmnProcess.getProcessId()), WorkItemsRowMapper);
		return returnList;
	}

	@Override
	public Integer updateWorkItemsOnProcessDeploy(List<WorkItems> updateWorkItemsList) {
		String sql = getQuery("CMDWORK_UPDATE_WORKITEMS_ON_PROCESS_DEPLOY");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (WorkItems workItems : updateWorkItemsList) {
			parameters.add(new BeanPropertySqlParameterSource(workItems));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateWorkItemsList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer deleteWorkItemsByTriggerId(String triggerId) {
		String sql = getQuery("CMDWORK_DELETE_WORKITEMS_BY_TRIG_ID");
		try {
			namedParameterJdbcTemplate.update(sql,createParams("triggerId", triggerId));
		} catch (Exception e) {
			logger.error("Exception in deleteWorkItemsByTriggerId:", e);
			return 0;
		}
		return 1;
	}

	@Override
	public ModuleTriggers getModuleTriggerData(String triggerId) {
		final String sql = getQuery("GET_MODULE_TRIGGER_DATA");
		final RowMapper<ModuleTriggers> cmdModuleTriggersRowMapper = Row2BeanRowMapper.makeMapping(sql, ModuleTriggers.class, cmdModuleTriggersMapping);
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("triggerId",triggerId), cmdModuleTriggersRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in getModuleTriggerData:"+e.getMessage());
			return null;
		}
	}
}
