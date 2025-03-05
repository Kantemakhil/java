package net.syscon.s4.sa.recordmaintenance.impl;

import java.sql.Blob;
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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.sa.recordmaintenance.DmnProcess;
import net.syscon.s4.sa.recordmaintenance.DmnmainRepository;

@Repository
public class DmnmainRepositoryImpl extends RepositoryBase implements DmnmainRepository{
	
	private static Logger logger = LogManager.getLogger(DmnmainRepositoryImpl.class.getName());
	public DmnmainRepositoryImpl() {
		// DmnmainRepositoryImpl
	}

	private final Map<String, FieldMapper> processMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DECISION_ID ",			new FieldMapper("decisionId"))
			.put("DEFINITION_KEY",			new FieldMapper("definitionKey"))
			.put("DEPLOYE_ID",			new FieldMapper("deployeId"))
			.put("DEFINITION_DESC",		new FieldMapper("definitionDesc"))
			.put("DEF_VERSION",			new FieldMapper("defVersion"))
			.put("CREATE_DATETIME",		new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",		new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME",		new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 		new FieldMapper("modifyUserId"))
			.put("DEPLOY_DATETIME",		new FieldMapper("deployDatetime"))
			.put("DEPLOY_USER_ID", 		new FieldMapper("deployUserId"))
			.put("DMN_FILE", 			new FieldMapper("dmnFile"))
			.put("DEPLOY_FLAG", 		new FieldMapper("deployFlag"))
			.put("HISTORY_FLAG", 		new FieldMapper("historyFlag"))
			.put("DEFINITION_ID", 		new FieldMapper("definitionId"))
			.build();
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("CODE", 				new FieldMapper("code"))
			.build();
	public List<DmnProcess> dmnsExecuteQuery() {
		final String sql = getQuery("DMNMAIN_EXECUTE_QUERY");
		final RowMapper<DmnProcess> ProcessRowMapper = Row2BeanRowMapper.makeMapping(sql, DmnProcess.class,
				processMapping);
		final ArrayList<DmnProcess> returnList = (ArrayList<DmnProcess>) namedParameterJdbcTemplate.query(sql,
				createParams(), ProcessRowMapper);
		return returnList;
	}
	
	public Integer insertDecision(List<DmnProcess> lstOfProcessMain) {
		String sql = getQuery("DMNMAIN_INSERT_PROCESS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final DmnProcess processMain : lstOfProcessMain) {
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
	public Integer updateDecision(List<DmnProcess> lstOfProcessMain) {
		String sql;

		String deployId = preInsert(lstOfProcessMain.get(0).getDecisionId());
		if("0".equals(deployId)) {
			sql = getQuery("DMNMAIN_UPDATE_PROCESS");
		} else {
			Long defVersion = preUpdate(lstOfProcessMain.get(0).getDefinitionKey());
			if(defVersion == lstOfProcessMain.get(0).getDefVersion() && !("0".equals(deployId))) {
				sql = getQuery("DMNMAIN_INSERT_PROCESS2");
				lstOfProcessMain.get(0).setDefVersion(defVersion+1);

			} else {
				sql = getQuery("DMNMAIN_UPDATE_PROCESS2");
				lstOfProcessMain.get(0).setDefVersion(defVersion);
			}
		}
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(lstOfProcessMain.get(0)));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		if (lstOfProcessMain.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	public Integer deleteDecision(List<DmnProcess> lstOfProcessMain) {
		String sql = getQuery("DMNMAIN_DELETE_PROCESS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final DmnProcess processMain : lstOfProcessMain) {
			parameters.add(new BeanPropertySqlParameterSource(processMain));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOfProcessMain.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	
	public Integer updateDeployeId(DmnProcess bpmnProcess) {
		String sql = getQuery("DMNMAIN_UPDATE_DEPLOYE_ID");
		
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
	
	public String preInsert(Long decisionId) {
		String sql = getQuery("DMNMAIN_PRE_INSERT");
		String deployId =  namedParameterJdbcTemplate.queryForObject(sql,createParams("decisionId",decisionId),String.class);
		if(deployId == null){
			return "0";
		} else {
			return deployId;
		}
	}
	
	public Long preUpdate(String processKey) {
		String sql = getQuery("DMNMAIN_PRE_UPDATE");
		return namedParameterJdbcTemplate.queryForObject(sql,createParams("processKey",processKey),Long.class);
	}

	@Override
	public DmnProcess getDmnProcess(DmnProcess bpmnProcess) {
		String sql = getQuery("DMNMAIN_GET_DMN_PROCESS");
		final RowMapper<DmnProcess> ProcessRowMapper = Row2BeanRowMapper.makeMapping(sql, DmnProcess.class,
				processMapping);
		final ArrayList<DmnProcess> returnList = (ArrayList<DmnProcess>) namedParameterJdbcTemplate.query(sql,
				createParams("processId",bpmnProcess.getDecisionId()), ProcessRowMapper);
		return  returnList.get(0);
	}
	
	@Override
	public List<DmnProcess> getVersionHistory(DmnProcess bpmnProcess) {
		final String sql = getQuery("DMNMAIN_GET_VERSION_HISTORY");
		final RowMapper<DmnProcess> ProcessRowMapper = Row2BeanRowMapper.makeMapping(sql, DmnProcess.class,
				processMapping);
		final ArrayList<DmnProcess> returnList = (ArrayList<DmnProcess>) namedParameterJdbcTemplate.query(sql,
				createParams("processKey", bpmnProcess.getDefinitionKey()), ProcessRowMapper);
		return returnList;
	}
	
	

	
	@Override
	public String getProcDefId(String processKey) {
		String sql = getQuery("DMNMAIN_GET_PROC_DEF_ID");
		return namedParameterJdbcTemplate.queryForObject(sql,createParams("processKey",processKey),String.class);
	}
	
	@Override
	public String getInsertDecision(String moduleName) {
		final String sql = getQuery("DMNMAIN_GET_INSERT_PROCESS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("moduleName", moduleName), String.class);
	}

	@Override
	public String getUpdateDecision(String moduleName) {
		final String sql = getQuery("DMNMAIN_GET_UPDATE_PROCESS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("moduleName", moduleName), String.class);
	}

	@Override
	public String getDeleteDecision(String moduleName) {
		final String sql = getQuery("DMNMAIN_GET_DELETE_PROCESS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("moduleName", moduleName), String.class);
	}

	@Override
	public String getDecisionKey(String process) {
		final String sql = getQuery("DMNMAIN_GET_PROCESS_KEY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("processId", process), String.class);
	}

	

	@Override
	public List<ReferenceCodes> getDmnsDeployedList() {
		//final String sql = getQuery("DEPLOYED_DMN_LIST");  // nedd to remove hardcoded query
		final String sql = "select A.DEFINITION_KEY as code, A.DEFINITION_DESC as description from (select DEFINITION_KEY, max(def_version) max_def_version from DMN_PROCESS where def_version is not null group by DEFINITION_KEY)B, DMN_PROCESS A where A.def_version=B.max_def_version and A.DEFINITION_KEY=B.DEFINITION_KEY";
		final RowMapper<ReferenceCodes> ProcessRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				mMapping);
		final ArrayList<ReferenceCodes> returnList = (ArrayList<ReferenceCodes>) namedParameterJdbcTemplate.query(sql,
				createParams(), ProcessRowMapper);
		return returnList;
	}

	@Override
	public List<DmnProcess> getDmnDataByDmnDesc(DmnProcess DmnProcess) {
		final String sql = getQuery("DECISION_BY_DESCION_DESC");
		final RowMapper<DmnProcess> ProcessRowMapper = Row2BeanRowMapper.makeMapping(sql, DmnProcess.class,
				processMapping);
		final ArrayList<DmnProcess> returnList = (ArrayList<DmnProcess>) namedParameterJdbcTemplate.query(sql,
				createParams("decisionDesc", DmnProcess.getDefinitionDesc()), ProcessRowMapper);
		return returnList;
	}

	@Override
	public Blob getDmnFile(String dmnProcessKey) {
		final String sql = getQuery("DMNMAIN_GET_DMN_FILE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("definitionKey", dmnProcessKey), Blob.class);
	}

	@Override
	public Integer updateDecisionProcessCategory(List<DmnProcess> dmnList) {
		final String sql = getQuery("UPDATE_DECISION_PROCESS_CATEGORY");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final DmnProcess list : dmnList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			
		}catch(Exception e) {
			logger.error("error in updateDecisionProcessCategory"+e.getMessage());
		}
		
		if (dmnList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

}
