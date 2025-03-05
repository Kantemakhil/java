package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.triggers.VOffenderCourseEventsTdRepository;

@Repository
public class VOffenderCourseEventsTdRepositoryImpl extends RepositoryBase implements VOffenderCourseEventsTdRepository {
	private final Logger logger = LogManager.getLogger(VOffenderCourseEventsTdRepositoryImpl.class.getName());

	@Override
	public Integer insert(final VOffenderCourseEvents vOffenderCourseEvents) {
		final String sql = getQuery("V_OFFENDER_COURSE_EVENTS_TD_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vOffenderCourseEvents));
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
	public Integer update(final VOffenderCourseEvents vOffenderCourseEvents) {
		final String sql = getQuery("V_OFFENDER_COURSE_EVENTS_TD_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vOffenderCourseEvents));
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
}
