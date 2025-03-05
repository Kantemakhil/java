package net.syscon.s4.sa.recordmaintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.sa.recordmaintenance.BpmnProcess;
import net.syscon.s4.sa.recordmaintenance.ProsdeacRepository;

@Repository
public class ProsdeacRepositoryImpl extends RepositoryBase implements ProsdeacRepository{
	
	private static Logger logger = LogManager.getLogger(ProsdeacRepositoryImpl.class.getName());
	public ProsdeacRepositoryImpl() {
		// ProsdeacRepositoryImpl
	}
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

	@Override
	public List<BpmnProcess> getProcessDetList(List<String> procDefIdList) {
		final String sql = getQuery("PROSDEAC_GET_PROCESS_DET_LIST");
		List<BpmnProcess> returnObj = new ArrayList<BpmnProcess>();
		try {
			final RowMapper<BpmnProcess> ProcessRowMapper = Row2BeanRowMapper.makeMapping(sql, BpmnProcess.class,
					processMapping);
			final ArrayList<BpmnProcess> returnList = (ArrayList<BpmnProcess>) namedParameterJdbcTemplate.query(sql,
					createParams("procDefIdList", procDefIdList), ProcessRowMapper);
			return returnList;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getProcessDetList and Exception is : {}", e.getMessage());
		}
		return returnObj;
	}

}
