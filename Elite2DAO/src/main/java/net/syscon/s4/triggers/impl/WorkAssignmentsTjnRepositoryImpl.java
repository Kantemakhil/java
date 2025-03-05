package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.legals.legalorders.WorkAssignments;
import net.syscon.s4.triggers.WorkAssignmentsTjnRepository;

@Repository
public class WorkAssignmentsTjnRepositoryImpl extends RepositoryBase implements WorkAssignmentsTjnRepository {
	private static Logger logger = LogManager.getLogger(WorkAssignmentsTjnRepositoryImpl.class.getName());

	@Override
	public WorkAssignments getWorkAssignments(final WorkAssignments workAssignments) {
		final String sql = getQuery("WORK_ASSIGNMENTS_TJN_WORK_ASSIGNMENTS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":workAssignmentId", workAssignments.getWorkAssignmentId()),
					new BeanPropertyRowMapper<WorkAssignments>(WorkAssignments.class));
		} catch (final Exception e) {
			logger.error("getWorkAssignments", e);
			return null;
		}
	}

	@Override
	public Integer insert(final WorkAssignments workAssignments) {
		final String sql = getQuery("WORK_ASSIGNMENTS_TJN_INS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(workAssignments));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insert", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer update(final WorkAssignments workAssignments) {
		final String sql = getQuery("WORK_ASSIGNMENTS_TJN_UPD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(workAssignments));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("update", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer delete(final WorkAssignments workAssignments) {
		final String sql = getQuery("WORK_ASSIGNMENTS_TJN_DEL");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(workAssignments));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("delete", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

}
