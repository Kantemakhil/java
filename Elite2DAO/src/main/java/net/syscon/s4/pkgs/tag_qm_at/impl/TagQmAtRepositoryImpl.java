package net.syscon.s4.pkgs.tag_qm_at.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.QmActivities;
import net.syscon.s4.pkgs.QmConActivityTeams;
import net.syscon.s4.pkgs.tag_qm_at.TagQmAtRepository;

@Repository
public class TagQmAtRepositoryImpl extends RepositoryBase implements TagQmAtRepository {
	private final Logger logger = LogManager.getLogger(TagQmAtRepositoryImpl.class);

	@Override
	public Integer prInsQmActivities(final QmActivities qmActivities) {
		final String sql = getQuery("TAG_QM_AT_QM_ACTIVITIES_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(qmActivities));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("prInsQmActivities", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer prInsQmConActivityTeams(final QmConActivityTeams qmConActivityTeams) {
		final String sql = getQuery("TAG_QM_AT_QM_CON_ACTIVITY_TEAMS_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(qmConActivityTeams));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("prInsQmConActivityTeams", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer prUpdQmActivities(final QmActivities qmActivities) {
		final String sql = getQuery("TAG_QM_AT_QM_ACTIVITIES_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(qmActivities));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("prUpdQmActivities", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer prUpdQmConActivityTeams(final QmConActivityTeams qmConActivityTeams) {
		final String sql = getQuery("TAG_QM_AT_QM_CON_ACTIVITY_TEAMS_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(qmConActivityTeams));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("prUpdQmConActivityTeams", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer prDel(final QmActivities qmActivities) {
		final String sql = getQuery("");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(qmActivities));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insertOffenderCourseAttendances1", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Long activityIdCur() {
		final String sql = getQuery("TAG_QM_ATACTIVITY_ID_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("activityIdCur :" + e);
			return null;
		}
	}
}
