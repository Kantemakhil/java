package net.syscon.s4.common.screenworkflow.impl;

import java.sql.SQLException;
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
import net.syscon.s4.common.screenworkflow.ScreenWorkFlowRepository;
import net.syscon.s4.common.beans.ScreenFlowWork;

@Repository
public class ScreenWorkFlowRepositoryImpl extends RepositoryBase implements ScreenWorkFlowRepository {

	private static Logger log = LogManager.getLogger(ScreenWorkFlowRepository.class.getName());

	public final Map<String, FieldMapper> screenWorkFlowMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("WORKFLOW_CODE", new FieldMapper("workFlowCode"))
			.put("MODULE_NAME", new FieldMapper("moduleName")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("CASELOAD_TYPE", new FieldMapper("caseLoadType")).put("WORKFLOW_SEQ", new FieldMapper("workSeq"))
			.build();

	public List<ScreenFlowWork> getWorkFlowScreens() throws SQLException {
		List<ScreenFlowWork> refList = new ArrayList<>();
		String sql = getQuery("GET_SCREEN_WORKFLOW_LIST");
		final RowMapper<ScreenFlowWork> screenWorkFlowRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ScreenFlowWork.class, screenWorkFlowMapping);
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams(), screenWorkFlowRowMapper);
		} catch (Exception e) {
			log.error("", e);
		}
		return refList;
	}
	
	/**
	 * Creates new ScreenWorkFlowRepositoryImpl class Object
	 */
	public ScreenWorkFlowRepositoryImpl() {
		// Omss40RepositoryImpl
	}

}