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
import net.syscon.s4.triggers.HdcGovernorDecisions;
import net.syscon.s4.triggers.HdcGovernorDecisionsT1Repository;
import net.syscon.s4.triggers.HdcRequestReferrals;

@Repository
public class HdcGovernorDecisionsT1RepositoryImpl extends RepositoryBase implements HdcGovernorDecisionsT1Repository {
	private final Logger logger = LogManager.getLogger(HdcGovernorDecisionsT1RepositoryImpl.class);

	@Override
	public HdcGovernorDecisions getHdcGovernorDecisions(final HdcGovernorDecisions hdcGovernorDecis) {
		final String sql = getQuery("HDC_GOVERNOR_DECISIONS_T1_HDC_GOVERNOR_DECISIONS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":hdcRequestReferralId", hdcGovernorDecis.getHdcRequestReferralId(), "decisionSeq",
							hdcGovernorDecis.getDecisionSeq()),
					new BeanPropertyRowMapper<HdcGovernorDecisions>(HdcGovernorDecisions.class));
		} catch (final Exception e) {
			logger.error("getHdcBoardDecisions", e);
			return null;
		}
	}

	@Override
	public Long getLNumber(final Long hdcReqReferralId) {
		final String sql = getQuery("HDC_GOVERNOR_DECISIONS_T1_L_NUMBER");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":hdcRequestReferralId", hdcReqReferralId), Long.class);
		} catch (final Exception e) {
			logger.error("getLNumber", e);
			return null;
		}
	}

	@Override
	public Integer update(final HdcRequestReferrals hdcRequestReferr) {
		Integer returnValue = 0;
		final String sql = getQuery("HDC_GOVERNOR_DECISIONS_T1_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(hdcRequestReferr));
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
