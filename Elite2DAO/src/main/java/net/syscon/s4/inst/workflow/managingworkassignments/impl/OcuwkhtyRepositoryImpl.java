package net.syscon.s4.inst.workflow.managingworkassignments.impl;

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
import net.syscon.s4.inst.workflow.managingworkassignments.OcuwkhtyRepository;
import net.syscon.s4.inst.workflow.managingworkassignments.VWorkAssignmentHistory;

/**
 * Class OcuwkhtyRepositoryImpl
 */
@Repository
public class OcuwkhtyRepositoryImpl extends RepositoryBase implements OcuwkhtyRepository {

	/**
	 * Creates new OcuwkhtyRepositoryImpl class Object
	 */
	public OcuwkhtyRepositoryImpl() {
		/*
		 * OcuwkhtyRepositoryImpl
		 */
	}

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code")).build();

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuwkhtyRepositoryImpl.class);

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VWorkAssignmentHistory
	 *
	 * @return List<VWorkAssignmentHistory>
	 *
	 */
	public List<VWorkAssignmentHistory> vWorkAssignmentHistoryExecuteQuery(final VWorkAssignmentHistory objSearchDao) {
		final String sql = getQuery("OCUWKHTY_VWORKASSIGNMENTHISTORY_FIND_V_WORK_ASSIGNMENT_HISTORY");
		final RowMapper<VWorkAssignmentHistory> vWorkAssignmentHistoryRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VWorkAssignmentHistory.class, mMapping);
		return namedParameterJdbcTemplate.query(
				sql, createParams("workId", objSearchDao.getWorkId(), "offenderBookId",
						objSearchDao.getOffenderBookId(), "workflowHistoryId", objSearchDao.getWorkflowHistoryId()),
				vWorkAssignmentHistoryRowMapper);
	}

}
