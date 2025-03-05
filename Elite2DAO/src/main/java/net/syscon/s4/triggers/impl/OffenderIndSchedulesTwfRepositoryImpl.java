package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.triggers.OffenderIndSchedulesTwfRepository;

@Repository
public class OffenderIndSchedulesTwfRepositoryImpl extends RepositoryBase implements OffenderIndSchedulesTwfRepository {
	private final Logger logger = LogManager.getLogger(OffenderIndSchedulesTwfRepositoryImpl.class);

	@Override
	public OffenderIndSchedules getOffenderIndSchedules(final Integer eventId) {
		final String sql = getQuery("OFFENDER_IND_SCHEDULES_TWF_OFFENDER_IND_SCHEDULES");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("eventId", eventId),
					new BeanPropertyRowMapper<OffenderIndSchedules>(OffenderIndSchedules.class));
		} catch (final Exception e) {
			logger.error("getOffenderIndSchedules", e);
			return null;
		}
	}

}
