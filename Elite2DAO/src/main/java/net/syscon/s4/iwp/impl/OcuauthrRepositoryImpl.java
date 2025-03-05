package net.syscon.s4.iwp.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.teamsworkflow.beans.TaskAssignmentHty;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.iwp.OcuauthrRepository;

/**
 * Class OcuauthrRepositoryImpl
 */
@Repository
public class OcuauthrRepositoryImpl extends RepositoryBase implements OcuauthrRepository {

	private final static Logger logger = LogManager.getLogger(OcuauthrRepositoryImpl.class.getName());

	/**
	 * Creates new OcuauthrRepositoryImpl class Object
	 */
	public OcuauthrRepositoryImpl() {
	}

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName")).build();

	private final Map<String, FieldMapper> tskAssHtyMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAME", new FieldMapper("lastName")).put("FIRST_NAME", new FieldMapper("firstName")).build();

	public List<TaskAssignmentHty> tskAssHtyExecuteQuery(final TaskAssignmentHty tskAsshty) {
		if(tskAsshty.getFlag()) {
			final String sql = getQuery("OCUAUTHR_TSKASSHTY_FIND_TASK_ASSIGNMENT_HTY_EMPTY");
			final RowMapper<TaskAssignmentHty> tskAssHtyRowMapper = Row2BeanRowMapper.makeMapping(sql,
					TaskAssignmentHty.class, tskAssHtyMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams(), tskAssHtyRowMapper);
		} else {
			final String sql = getQuery("OCUAUTHR_TSKASSHTY_FIND_TASK_ASSIGNMENT_HTY");
			final RowMapper<TaskAssignmentHty> tskAssHtyRowMapper = Row2BeanRowMapper.makeMapping(sql,
					TaskAssignmentHty.class, tskAssHtyMapping);
			return namedParameterJdbcTemplate.query(sql,
					createParams("WORKFLOW_HISTORY_ID", tskAsshty.getWorkflowHistoryId()), tskAssHtyRowMapper);
		}
	}

	public List<ReferenceCodes> rgStaffNameRecordGroup(final String teamId) {
		final String sql = getQuery("OCUAUTHR_FIND_RGSTAFFNAME");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("team_id", teamId), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	@Override
	public Teams teamCodeAndDescriptionPostquery(final Long teamId) {
		final String sql = getQuery("OCUAUTHR_TSKASSHTY_FIND_TEAM_DESC_POST_QUERY");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("TEAM_ID", teamId),
					new BeanPropertyRowMapper<Teams>(Teams.class));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public StaffMembers lastNameAndfirstNamePostquery(final Long staffId) {
		final String sql = getQuery("OCUAUTHR_TSKASSHTY_FIND_STAFF_NAME_POST_QUERY");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("STAFF_ID", staffId),
					new BeanPropertyRowMapper<StaffMembers>(StaffMembers.class));
		} catch (EmptyResultDataAccessException e) {
			logger.error(e);
			return null;
		}
	}

}
