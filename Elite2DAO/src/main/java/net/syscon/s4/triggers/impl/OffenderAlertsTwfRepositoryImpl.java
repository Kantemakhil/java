package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderAlerts;
import net.syscon.s4.triggers.OffenderAlertsTwfRepository;

@Repository
public class OffenderAlertsTwfRepositoryImpl extends RepositoryBase implements OffenderAlertsTwfRepository {
	private final Logger logger = LogManager.getLogger(OffenderAlertsTwfRepositoryImpl.class);

	@Override
	public OffenderAlerts getOffenderAlerts(final Long offenderBookId, final Long alertSeq) {
		final String sql = getQuery("OFFENDER_ALERTS_TWF_OFFENDER_ALERTS_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId, "alertSeq", alertSeq),
					new BeanPropertyRowMapper<OffenderAlerts>(OffenderAlerts.class));
		} catch (final Exception e) {
			logger.error("getOffenderAlerts", e);
			return null;
		}
	}
}
