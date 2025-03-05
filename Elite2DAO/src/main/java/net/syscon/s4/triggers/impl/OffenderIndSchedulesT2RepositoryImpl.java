package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.triggers.OffenderIndSchedulesT2Repository;

@Repository
public class OffenderIndSchedulesT2RepositoryImpl extends RepositoryBase implements OffenderIndSchedulesT2Repository {
	Logger logger = LogManager.getLogger(OffenderIndSchedulesT2RepositoryImpl.class.getName());

	@Override
	public OffenderIndSchedules getOffenderIndSchedules(final OffenderIndSchedules newObj) {
		final String sql = getQuery("OFFENDER_IND_SCHEDULES");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("eventId", newObj.getEventId()),
					new BeanPropertyRowMapper<OffenderIndSchedules>(OffenderIndSchedules.class));
		} catch (final Exception e) {
			logger.error("getOffenderIndSchedules", e);
			return null;
		}
	}

	@Override
	public Integer eventType(final String eventType) {
		final String sql = getQuery("OFFENDER_IND_SCHEDULES_T2_EVENT_TYPE");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("eventType", eventType), Integer.class);
		} catch (final Exception e) {
			logger.error("eventType", e);
			return 0;
		}
	}

	@Override
	public Integer eventStatus(final String eventStatus) {
		final String sql = getQuery("OFFENDER_IND_SCHEDULES_T2_EVENT_STATUS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("eventStatus", eventStatus), Integer.class);
		} catch (final Exception e) {
			logger.error("eventStatus", e);
			return 0;
		}
	}

}
