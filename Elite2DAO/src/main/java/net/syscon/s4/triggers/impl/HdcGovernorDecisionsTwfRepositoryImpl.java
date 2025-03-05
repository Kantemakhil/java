package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.HdcGovernorDecisions;
import net.syscon.s4.triggers.HdcGovernorDecisionsTwfRepository;

@Repository
public class HdcGovernorDecisionsTwfRepositoryImpl extends RepositoryBase implements HdcGovernorDecisionsTwfRepository {
	private final Logger logger = LogManager.getLogger(HdcGovernorDecisionsTwfRepositoryImpl.class);

	@Override
	public HdcGovernorDecisions getHdcGovernorDecisions(final HdcGovernorDecisions hdcGovernorDeci) {
		final String sql = getQuery("HDC_GOVERNOR_DECISIONS_TWF_HDC_GOVERNOR_DECISIONS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":hdcRequestReferralId", hdcGovernorDeci.getHdcRequestReferralId(), "decisionSeq",
							hdcGovernorDeci.getDecisionSeq()),
					new BeanPropertyRowMapper<HdcGovernorDecisions>(HdcGovernorDecisions.class));
		} catch (final Exception e) {
			logger.error("getHdcGovernorDecisions", e);
			return null;
		}
	}

}
