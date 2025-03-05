package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderAssocPEventNotifs;
import net.syscon.s4.triggers.OffenderTmpRelSchedules;
import net.syscon.s4.triggers.OffenderTmpRelSchedulesT1Repository;

@Repository
public class OffenderTmpRelSchedulesT1RepositoryImpl extends RepositoryBase
		implements OffenderTmpRelSchedulesT1Repository {
	private final Logger logger = LogManager.getLogger(OffenderTmpRelSchedulesT1RepositoryImpl.class);

	@Override
	public OffenderTmpRelSchedules getOffenderTmpRelSchedules(final Long offenderBookId, final Long sessionId) {
		final String sql = getQuery("OFFENDER_TMP_REL_SCHEDULES_T1_OFFENDER_TMP_REL_SCHEDULES");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderBookId", offenderBookId, ":sessionId", sessionId),
					new BeanPropertyRowMapper<OffenderTmpRelSchedules>(OffenderTmpRelSchedules.class));
		} catch (final Exception e) {
			logger.error("getOffenderTmpRelSchedules", e);
			return null;
		}
	}

	@Override
	public String lCheckExistCur(final Long offenderBookId, final Date pReleaseDate) {
		final String sql = getQuery("OFFENDER_TMP_REL_SCHEDULES_T1_L_CHECK_EXIST_CUR");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderBookId", offenderBookId, ":releaseDate", pReleaseDate), String.class);
		} catch (final Exception e) {
			logger.error("lCheckExistCur", e);
			return "N";
		}
	}

	@Override
	public String lSexCheckExistCur(final Long offenderBookId) {
		final String sql = getQuery("OFFENDER_TMP_REL_SCHEDULES_T1_OFFENCE_INDICATORS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(":offenderBookId", offenderBookId),
					String.class);
		} catch (final Exception e) {
			logger.error("lSexCheckExistCur", e);
			return "N";
		}
	}

	@Override
	public Long trgEventIdSeq() {
		final String sql = getQuery("OFFENDER_TMP_REL_SCHEDULES_T1_TRG_EVENT_ID_NEXTVAL");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("trgEventIdSeq", e);
			return null;
		}
	}

	@Override
	public Integer insert(final OffenderAssocPEventNotifs offeAssPEvntNoti) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_TMP_REL_SCHEDULES_T1_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offeAssPEvntNoti));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("insert ", e);
		}
		return returnValue;

	}

	@Override
	public Integer update(final OffenderAssocPEventNotifs offeAssPEvntNoti) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_TMP_REL_SCHEDULES_T1_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offeAssPEvntNoti));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("update ", e);
		}
		return returnValue;

	}

}
