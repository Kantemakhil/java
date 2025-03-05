package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.triggers.CeAudit;
import net.syscon.s4.triggers.OffCourtEventVineIntfTrgRepository;

@Repository
public class OffCourtEventVineIntfTrgRepositoryImpl extends RepositoryBase implements OffCourtEventVineIntfTrgRepository {
	Logger logger = LogManager.getLogger(OffCourtEventVineIntfTrgRepositoryImpl.class);

	@Override
	public OffenderBookings curActBook(final Integer pOffBookId) {
		final String sql = getQuery("OFF_COURT_EVENT_VINE_INTF_TRG_CUR_ACT_BOOK");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offBookId", pOffBookId),
					new BeanPropertyRowMapper<OffenderBookings>(OffenderBookings.class));
		} catch (final Exception e) {
			logger.error("curActBook", e);
			return null;
		}
	}

	@Override
	public Offenders curOff(final Long pOffId) {
		final String sql = getQuery("OFF_COURT_EVENT_VINE_INTF_TRG_CUR_OFF");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offId", pOffId),
					new BeanPropertyRowMapper<Offenders>(Offenders.class));
		} catch (final Exception e) {
			logger.error("curOff", e);
			return null;
		}
	}

	@Override
	public OffenderCases curOffCase(final Integer pCaseId) {
		final String sql = getQuery("OFF_COURT_EVENT_VINE_INTF_TRG_CUR_OFF_CASE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("caseId", pCaseId),
					new BeanPropertyRowMapper<OffenderCases>(OffenderCases.class));
		} catch (final Exception e) {
			logger.error("curOffCase", e);
			return null;
		}
	}

	
	@Override
	public CourtEvents getCourtEvents(final Integer eventId) {
		final String sql = getQuery("OFF_COURT_EVENT_VINE_INTF_TRG_GET_OLD_OBJECT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("eventId", eventId),
					new BeanPropertyRowMapper<CourtEvents>(CourtEvents.class));
		} catch (final Exception e) {
			logger.error("getCourtEvents", e);
			return null;
		}
	}

	@Override
	public Integer insertUpdateDelete(final List<CeAudit> ceAuditList) {
		Integer returnValue = 0;
		final String sql = getQuery("OFF_COURT_EVENT_VINE_INTF_TRG_INSERTING");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CeAudit ceAudit : ceAuditList) {
			parameters.add(new BeanPropertySqlParameterSource(ceAudit));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (ceAuditList.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error(" insertUpdateDelete ", e);
		}
		return returnValue;

	}

	@Override
	public Integer update(final List<CeAudit> ceAuditList) {
		Integer returnValue = 0;
		final String sql = getQuery("OFF_COURT_EVENT_VINE_INTF_TRG_UPDADING");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CeAudit ceAudit : ceAuditList) {
			parameters.add(new BeanPropertySqlParameterSource(ceAudit));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (ceAuditList.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error(" update ", e);
		}
		return returnValue;

	}

	@Override
	public Integer delete(final List<CeAudit> ceAuditList) {
		Integer returnValue = 0;
		final String sql = getQuery("OFF_COURT_EVENT_VINE_INTF_TRG_DELETING");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CeAudit ceAudit : ceAuditList) {
			parameters.add(new BeanPropertySqlParameterSource(ceAudit));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (ceAuditList.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error(" delete ", e);
		}
		return returnValue;

	}

}
