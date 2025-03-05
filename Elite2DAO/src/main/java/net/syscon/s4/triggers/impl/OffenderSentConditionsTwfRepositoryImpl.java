package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.triggers.OffenderSentConditionsTwfRepository;

@Repository
public class OffenderSentConditionsTwfRepositoryImpl extends RepositoryBase
		implements OffenderSentConditionsTwfRepository {
	Logger logger = LogManager.getLogger(OffenderSentConditionsTwfRepositoryImpl.class.getName());

	@Override
	public OffenderSentConditions getOffenderSentConditions(final OffenderSentConditions newObj) {
		final String sql = getQuery("OFFENDER_SENT_CONDITIONS_TWF_OFFENDER_SENT_CONDITIONS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":offenderSentConditionId", newObj.getOffenderSentConditionId()),
					new BeanPropertyRowMapper<OffenderSentConditions>(OffenderSentConditions.class));
		} catch (final Exception e) {
			logger.error("getOffenderSentConditions", e);
			return null;
		}
	}

}
