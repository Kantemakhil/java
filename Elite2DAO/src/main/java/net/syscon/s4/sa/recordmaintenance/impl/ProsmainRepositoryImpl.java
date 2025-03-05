package net.syscon.s4.sa.recordmaintenance.impl;

import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.sa.recordmaintenance.BpmnProcess;
import net.syscon.s4.sa.recordmaintenance.ProsmainRepository;
import net.syscon.s4.sa.recordmaintenance.WorkItems;

@Repository
public class ProsmainRepositoryImpl extends RepositoryBase implements ProsmainRepository{
	
	private static Logger logger = LogManager.getLogger(ProsmainRepositoryImpl.class.getName());
	public ProsmainRepositoryImpl() {
	}
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final Map<String, FieldMapper> processMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROCESS_ID",			new FieldMapper("processId"))
			.put("PROCESS_KEY",			new FieldMapper("processKey"))
			.put("DEPLOYE_ID",			new FieldMapper("deployeId"))
			.put("PROCESS_DESC",		new FieldMapper("processDesc"))
			.put("DEF_VERSION",			new FieldMapper("defVersion"))
			.put("CREATE_DATETIME",		new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",		new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",		new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 		new FieldMapper("modifyUserId"))
			.put("DEPLOY_DATETIME",		new FieldMapper("deployDatetime"))
			.put("DEPLOY_USER_ID", 		new FieldMapper("deployUserId"))
			.put("BPMN_FILE", 			new FieldMapper("bpmnFile"))
			.put("DEPLOY_FLAG", 		new FieldMapper("deployFlag"))
			.put("HISTORY_FLAG", 		new FieldMapper("historyFlag"))
			.put("PROC_DEF_ID", 		new FieldMapper("procDefId"))
			.put("MODULE",              new FieldMapper("module"))
			.put("TRIGGER_ID",			new FieldMapper("triggerId"))
			.put("COMMON_PROCESS",		new FieldMapper("commonProcess"))
			.put("CATEGORY",		new FieldMapper("category"))
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
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 		new FieldMapper("description"))
			.put("CODE", 				new FieldMapper("code"))
			.build();

	public List<BpmnProcess> processExecuteQuery() {
		final String sql = getQuery("PROSMAIN_EXECUTE_QUERY");
		final RowMapper<BpmnProcess> ProcessRowMapper = Row2BeanRowMapper.makeMapping(sql, BpmnProcess.class,
				processMapping);
		final ArrayList<BpmnProcess> returnList = (ArrayList<BpmnProcess>) namedParameterJdbcTemplate.query(sql,
				createParams(), ProcessRowMapper);
		return returnList;
	}
	
	public Integer insertProcess(List<BpmnProcess> lstOfProcessMain) {
		String sql = getQuery("PROSMAIN_INSERT_PROCESS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final BpmnProcess processMain : lstOfProcessMain) {
			parameters.add(new BeanPropertySqlParameterSource(processMain));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOfProcessMain.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateProcess(List<BpmnProcess> lstOfProcessMain) {
		String sql;
		String sql2 = "select work_id from WORK_ITEMS where process = (select max(process_id) from BPMN_PROCESS where process_key = :processKey)";
		String sql3 = "UPDATE WORK_ITEMS SET PROCESS = (select max(process_id) from BPMN_PROCESS), MODIFY_DATETIME = :modifyDatetime, MODIFY_USER_ID = :modifyUserId where work_id = :workId";
		Long workId = null;
		String deployId = preInsert(lstOfProcessMain.get(0).getProcessId());
		if("0".equals(deployId)) {
			sql = getQuery("PROSMAIN_UPDATE_PROCESS");
		} else {
			Long defVersion = preUpdate(lstOfProcessMain.get(0).getProcessKey());
			if(defVersion.equals(lstOfProcessMain.get(0).getDefVersion()) && !("0".equals(deployId))) {
				sql = getQuery("PROSMAIN_INSERT_PROCESS2");
				lstOfProcessMain.get(0).setDefVersion(defVersion+1);
				try {
					workId = namedParameterJdbcTemplate.queryForObject(sql2,createParams("processKey",lstOfProcessMain.get(0).getProcessKey()),Long.class);
				} catch (Exception e) {
					workId = null;
				}
			} else {
				sql = getQuery("PROSMAIN_UPDATE_PROCESS2");
				lstOfProcessMain.get(0).setDefVersion(defVersion);
			}
		}
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstOfProcessMain.get(0)));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if(workId != null && lstOfProcessMain.size() == returnArray.length) {
				WorkItems workItems = new WorkItems();
				workItems.setWorkId(workId);
				workItems.setModifyDatetime(lstOfProcessMain.get(0).getModifyDatetime());
				workItems.setModifyUserId(lstOfProcessMain.get(0).getModifyUserId());
				int[] returnArray2 = new int[] {};
				List<SqlParameterSource> parameters2 = new ArrayList<SqlParameterSource>();
				parameters2.add(new BeanPropertySqlParameterSource(workItems));
				returnArray2 = namedParameterJdbcTemplate.batchUpdate(sql3, parameters2.toArray(new SqlParameterSource[0]));
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		if (lstOfProcessMain.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer deleteProcess(List<BpmnProcess> lstOfProcessMain) {
		String sql = getQuery("PROSMAIN_DELETE_PROCESS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final BpmnProcess processMain : lstOfProcessMain) {
			parameters.add(new BeanPropertySqlParameterSource(processMain));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOfProcessMain.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	
	public Integer updateDeployeId(BpmnProcess bpmnProcess) {
		String sql = getQuery("PROSMAIN_UPDATE_DEPLOYE_ID");
		
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(bpmnProcess));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public String preInsert(Long processId) {
		String sql = getQuery("PROSMAIN_PRE_INSERT");
		String deployId =  namedParameterJdbcTemplate.queryForObject(sql,createParams("processId",processId),String.class);
		if(deployId == null){
			return "0";
		} else {
			return deployId;
		}
	}
	
	public Long preUpdate(String processKey) {
		String sql = getQuery("PROSMAIN_PRE_UPDATE");
		return namedParameterJdbcTemplate.queryForObject(sql,createParams("processKey",processKey),Long.class);
	}

	@Override
	public BpmnProcess getBpmnProcess(BpmnProcess bpmnProcess) {
		String sql = getQuery("PROSMAIN_GET_BPMN_PROCESS");
		final RowMapper<BpmnProcess> ProcessRowMapper = Row2BeanRowMapper.makeMapping(sql, BpmnProcess.class,
				processMapping);
		final ArrayList<BpmnProcess> returnList = (ArrayList<BpmnProcess>) namedParameterJdbcTemplate.query(sql,
				createParams("processId",bpmnProcess.getProcessId()), ProcessRowMapper);
		return  returnList.get(0);
	}
	
	@Override
	public List<BpmnProcess> getVersionHistory(BpmnProcess bpmnProcess) {
		final String sql = getQuery("PROSMAIN_GET_VERSION_HISTORY");
		final RowMapper<BpmnProcess> ProcessRowMapper = Row2BeanRowMapper.makeMapping(sql, BpmnProcess.class,
				processMapping);
		final ArrayList<BpmnProcess> returnList = (ArrayList<BpmnProcess>) namedParameterJdbcTemplate.query(sql,
				createParams("processKey", bpmnProcess.getProcessKey()), ProcessRowMapper);
		return returnList;
	}
	
	@Override
	public Integer updateBpmnProcess(byte[] fileByteArray, String processId, java.sql.Date modifyDate,
			String modifyUser,String processDesc) {
		String deployFlag = "N";
		try {
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection
						.prepareStatement("update BPMN_PROCESS SET BPMN_FILE=?,MODIFY_DATETIME=?,MODIFY_USER_ID=?,PROCESS_DESC=?, DEPLOY_FLAG=? where PROCESS_ID=?");
				ps.setBlob(1, new ByteArrayInputStream(fileByteArray), fileByteArray.length);
				ps.setDate(2, modifyDate);
				ps.setString(3, modifyUser);
				ps.setString(4, processDesc);
				ps.setString(5, deployFlag);
				ps.setString(6, processId);
				return ps;
			});
	} catch (Exception e) {
		logger.error("insertFileByteArrayFromWatcher Save file in DB Error :: "+ e.getLocalizedMessage());
		return 0;
	}
	
	return 1;
	}

	@Override
	public BpmnProcess getProcessDetails(String deployId) {
		final String sql = getQuery("GET_PROCESS_INSTANCE_BPMN");	
		BpmnProcess returnList = null;
		final RowMapper<BpmnProcess> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,BpmnProcess.class,
				processMapping);
		try{
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("deployId", deployId),columnRowMapper);
		}catch (Exception e) {
			logger.error("error in getProcessDetails"+e.getMessage());
		}
		return returnList;
	}
	
	@Override
	public String getProcDefId(String processKey) {
		String sql = getQuery("PROSMAIN_GET_PROC_DEF_ID");
		return namedParameterJdbcTemplate.queryForObject(sql,createParams("processKey",processKey),String.class);
	}
	
	@Override
	public Long getInsertProcess(String triggerId) {
		final String sql = getQuery("PROSMAIN_GET_INSERT_PROCESS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("triggerId", triggerId), Long.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Long getUpdateProcess(String triggerId) {
		final String sql = getQuery("PROSMAIN_GET_UPDATE_PROCESS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("triggerId", triggerId), Long.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Long getDeleteProcess(String triggerId) {
		final String sql = getQuery("PROSMAIN_GET_DELETE_PROCESS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("triggerId", triggerId), Long.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getProcessKey(Long process) {
		final String sql = getQuery("PROSMAIN_GET_PROCESS_KEY");
		try {
		 return namedParameterJdbcTemplate.queryForObject(sql, createParams("processId", process), String.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<BpmnProcess> getProcess(BpmnProcess bpmnProcess) {
		final String sql = getQuery("PROSMAIN_GET_BY_PROC_DESC");
		final RowMapper<BpmnProcess> ProcessRowMapper = Row2BeanRowMapper.makeMapping(sql, BpmnProcess.class,
				processMapping);
		final ArrayList<BpmnProcess> returnList = (ArrayList<BpmnProcess>) namedParameterJdbcTemplate.query(sql,
				createParams("processDesc", bpmnProcess.getProcessDesc()), ProcessRowMapper);
		return returnList;
	}

	@Override
	public BpmnProcess getProcessData(Integer processId) {
		final String sql = getQuery("GET_PROCESS_DATA");	
		BpmnProcess returnList = null;
		final RowMapper<BpmnProcess> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,BpmnProcess.class,
				processMapping);
		try{
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("processId", processId),columnRowMapper);
		}catch (Exception e) {
			logger.error("getProcessBpmn Error:"+e.getMessage());
		}
		return returnList;
	}
	
	@Override
	public Integer updateTrigger(Long processId) {
		int result = 0;
		final String sql = getQuery("PROSMAIN_UPDATE_TRIGGER_ID");
		try{
			result = namedParameterJdbcTemplate.update(sql, createParams("processId", processId));
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	@Override
	public String getCamundaServerUrl() {
		final String sql = getQuery("GET_CAMUNDA_SERVER_URL");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public String getAppServerUrl() {
		final String sql = getQuery("GET_APP_SERVER_URL");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public void saveCamundaErr(String errorData, String processKey, Long processId) {
		final String sql = getQuery("SAVE_CAMUNDA_ERR");
		Map<String, Object> inputMap  = new HashMap<>();
		logger.info("In saveCamundaErr Method ::");
		logger.info("Saving Error Data:: ",errorData);
		try {
			inputMap.put("processKey", processKey);
			inputMap.put("errorData", errorData.getBytes());
			inputMap.put("processId", processId);
			namedParameterJdbcTemplate.update(sql, inputMap);	
		} catch (Exception ex) {
			logger.error("Error in saveCamundaErr Method :: {}", ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public Integer updateBulkDeployeId(List<BpmnProcess> bpmnProcessList) {

		String sql = getQuery("PROSMAIN_UPDATE_DEPLOYE_ID");
		
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		bpmnProcessList.forEach(bpmnProcess->{
			parameters.add(new BeanPropertySqlParameterSource(bpmnProcess));
		});
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return returnArray.length;
	}
	
	@Override
	public List<ReferenceCodes> getCommonProcess() {
		final String sql = getQuery("PROSMAIN_GET_COMMON_PROCESS");
		final RowMapper<ReferenceCodes> ProcessRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), ProcessRowMapper);
		return returnList;
	}
	
	public int insertBulkProcess(List<BpmnProcess> lstOfProcessMain) {
		String sql = getQuery("PROSMAIN_BULK_INSERT_PROCESS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final BpmnProcess processMain : lstOfProcessMain) {
			processMain.setDefVersion((long) 1);
			processMain.setBpmnFile(processMain.getBpmn().getBytes());
			processMain.setDeployFlag("Y");
			processMain.setHistoryFlag("N");
			parameters.add(new BeanPropertySqlParameterSource(processMain));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOfProcessMain.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public Integer updateBulkProcess(List<BpmnProcess> lstOfProcessMain) {
		int[] returnArray = new int[] {};
		final String sql = getQuery("PROSMAIN_INSERT_PROCESS2");
		bulkPreUpdate(lstOfProcessMain);
		for(BpmnProcess bpmnProcess : lstOfProcessMain) {
			bpmnProcess.setDefVersion(bpmnProcess.getDefVersion()+1);
			bpmnProcess.setBpmnFile(bpmnProcess.getBpmn().getBytes());
		}
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final BpmnProcess processMain : lstOfProcessMain) {
			parameters.add(new BeanPropertySqlParameterSource(processMain));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (lstOfProcessMain.size() == returnArray.length) {
			return 1;
		} else {
		return 0;
	}
	}
	
	private Integer bulkPreUpdate(List<BpmnProcess> lstOfProcessMain) {
		final String sql = getQuery("PROSMAIN_BULK_PRE_UPDATE");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final BpmnProcess processMain : lstOfProcessMain) {
			parameters.add(new BeanPropertySqlParameterSource(processMain));
		}
		try {
			String tableName = "bpmn_process";
			String whereClause = "process_id = :processId and process_key is null";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method bulkPreUpdate", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (lstOfProcessMain.size() == returnArray.length) {
			return 1;
		} else {
		return 0;
	}
	}

	@Override
	public Long getWorkIdByTrigger(String triggerId) {
		final String sql = getQuery("PROSMAIN_GET_WORKID_BY_TRIGGERID");
		Long workId;
		try {
			workId = namedParameterJdbcTemplate.queryForObject(sql, createParams("triggerId", triggerId), long.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return workId;
	}

	@Override
	public Integer updateProcessCategory(List<BpmnProcess> lstOfProcessMain) {
		final String sql = getQuery("UPDATE_PROCESS_CATEGORY");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final BpmnProcess list : lstOfProcessMain) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			
		}catch(Exception e) {
			logger.error("error in updateProcessCategory"+e.getMessage());
		}
		
		if (lstOfProcessMain.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
}
