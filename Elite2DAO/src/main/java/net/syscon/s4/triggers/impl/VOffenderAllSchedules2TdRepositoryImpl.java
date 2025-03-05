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
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.triggers.VOffenderAllSchedules2TdRepository;

@Repository
public class VOffenderAllSchedules2TdRepositoryImpl extends RepositoryBase
		implements VOffenderAllSchedules2TdRepository {
	private static Logger logger = LogManager.getLogger(VOffenderAllSchedules2TdRepositoryImpl.class);

	@Override
	public Integer updateOffenderIndSchedules(final OffenderIndSchedules offenderIndSchedules) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_TD_OFFENDER_IND_SCHEDULES_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenderIndSchedules));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insertOffenderIndSchedules", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer deleteOffenderIndSchedules(final OffenderIndSchedules offenderIndSchedules) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_TD_OFFENDER_IND_SCHEDULES_DELETE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenderIndSchedules));
		try {
			String tableName = "OFFENDER_IND_SCHEDULES";
			String whereClause = "Event_ID = :eventId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deleteOffenderIndSchedules", e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("deleteOffenderIndSchedules", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer updateVOffenderCourseEvents(final VOffenderCourseEvents vOffenderCourseEvents) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_TD_V_OFFENDER_COURSE_EVENTS_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vOffenderCourseEvents));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("updateVOffenderCourseEvents", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer updateVOffenderCourseEvents2(final VOffenderCourseEvents vOffenderCourseEvents) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_TD_V_OFFENDER_COURSE_EVENTS2");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vOffenderCourseEvents));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("updateVOffenderCourseEvents2", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

}
