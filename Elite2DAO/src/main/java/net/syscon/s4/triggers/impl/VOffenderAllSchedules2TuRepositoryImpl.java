package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.incidentsoic.beans.OicHearings;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.VOffenderCourseEvents;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;
import net.syscon.s4.triggers.VOffenderAllSchedules2TuRepository;
import net.syscon.s4.triggers.VOffenderVisitSchedules;

@Repository
public class VOffenderAllSchedules2TuRepositoryImpl extends RepositoryBase
		implements VOffenderAllSchedules2TuRepository {
	private static Logger logger = LogManager.getLogger(VOffenderAllSchedules2TuRepositoryImpl.class);

	@Override
	public Integer schOffenderIndSchedules(final List<OffenderIndSchedules> offenderIndSchedules) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_TU_SCH_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for(OffenderIndSchedules obj : offenderIndSchedules) {
		parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("schOffenderIndSchedules", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer vOffCrsVOffenderCourseEvents(final VOffenderCourseEvents vOffenderCourseEvents) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_TU_V_OFF_CRS_SCH_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vOffenderCourseEvents));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("vOffCrsVOffenderCourseEvents", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer vOffCrsVOffenderCourseEventsElse(final VOffenderCourseEvents vOffenderCourseEvents) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_TU_V_OFF_CRS_SCH_UPDATE_ELSE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vOffenderCourseEvents));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("vOffCrsVOffenderCourseEventsElse", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offVisVOffenderVisitSchedules(final VOffenderVisitSchedules vOffenderVisitSchedules) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_TU_OFF_VIS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vOffenderVisitSchedules));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("offVisVOffenderVisitSchedules", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer oicHearingOicHearings(final OicHearings oicHearings) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_TU_OIC_HEARING");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(oicHearings));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("oicHearingOicHearings", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer offRelOffenderReleaseDetails(final OffenderReleaseDetails offenderReleaseDetails) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_TU_OFF_REL");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenderReleaseDetails));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("offRelOffenderReleaseDetails", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer courtCourtEvents(final CourtEvents courtEvents) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_TU_COURT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(courtEvents));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("courtCourtEvents", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer getvRecCount(final Integer offenderBookId, final String eventType, final Integer referenceId) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_TU_COURT_COUNT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderBookId", offenderBookId, "eventType", eventType, "referenceId", referenceId),
				Integer.class);
	}

	@Override
	public Integer courtOffenderIndSchedules(final OffenderIndSchedules offenderIndSchedules) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_TU_COURT_ELSE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenderIndSchedules));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("courtOffenderIndSchedules", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

}
