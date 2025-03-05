package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.HdcRequestReferrals;
import net.syscon.s4.triggers.HdcRequestReferralsTwfRepository;

@Repository
public class HdcRequestReferralsTwfRepositoryImpl extends RepositoryBase implements HdcRequestReferralsTwfRepository {
	private final Logger logger = LogManager.getLogger(HdcRequestReferralsTwfRepositoryImpl.class);

	@Override
	public HdcRequestReferrals getHdcRequestReferrals(final HdcRequestReferrals hdcRequestRefe) {
		final String sql = getQuery("HDC_REQUEST_REFERRALS_TWF_HDC_REQUEST_REFERRALS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":hdcRequestReferralId", hdcRequestRefe.getHdcRequestReferralId()),
					new BeanPropertyRowMapper<HdcRequestReferrals>(HdcRequestReferrals.class));
		} catch (final Exception e) {
			logger.error("getHdcRequestReferrals", e);
			return null;
		}
	}

}
