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
import net.syscon.s4.im.beans.OffenderCaseNotes;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.triggers.OffenderIndSchedulesT4Repository;

@Repository
public class OffenderIndSchedulesT4RepositoryImpl extends RepositoryBase implements OffenderIndSchedulesT4Repository {
	Logger logger = LogManager.getLogger(OffenderIndSchedulesT4RepositoryImpl.class);

	@Override
	public OffenderIndSchedules getOffenderIndSchedules(final Integer eventId) {
		final String sql = getQuery("OFFENDER_IND_SCHEDULES_T4_OFFENDER_IND_SCHEDULES_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("eventId", eventId),
					new BeanPropertyRowMapper<OffenderIndSchedules>(OffenderIndSchedules.class));
		} catch (final Exception e) {
			logger.error("getOffenderIndSchedules", e);
			return null;
		}
	}

	@Override
	public OffenderCaseNotes getCasenoteDetailsC(final Integer eventId) {
		final String sql = getQuery("OFFENDER_IND_SCHEDULES_T4_GET_CASENOTE_DETAILS_C");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("eventId", eventId),
					new BeanPropertyRowMapper<OffenderCaseNotes>(OffenderCaseNotes.class));
		} catch (final Exception e) {
			logger.error("getCasenoteDetailsC", e);
			return null;
		}
	}

	@Override
	public Integer insert(final List<OffenderCaseNotes> offeCaseNotesList) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_IND_SCHEDULES_T4_OFFENDER_CASE_NOTES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderCaseNotes offenderCaseNotes : offeCaseNotesList) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCaseNotes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (offeCaseNotesList.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("insert ", e);
		}
		return returnValue;

	}

	@Override
	public Integer caseNoteIdNextval() {
		final String sql = getQuery("OFFENDER_IND_SCHEDULES_T4_CASE_NOTE_ID_NEXTVAL");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (final Exception e) {
			logger.error("caseNoteIdNextval", e);
			return null;
		}
	}

}
