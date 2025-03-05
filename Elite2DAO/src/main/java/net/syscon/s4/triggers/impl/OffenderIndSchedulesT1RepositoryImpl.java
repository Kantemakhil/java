package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.triggers.OffenderIndSchedulesT1Repository;

@Repository
public class OffenderIndSchedulesT1RepositoryImpl extends RepositoryBase implements OffenderIndSchedulesT1Repository {
	Logger logger = LogManager.getLogger(OffenderIndSchedulesT1RepositoryImpl.class.getName());

	@Override
	public OffenderIndSchedules getOffenderIndSchedules(final OffenderIndSchedules offenderIndSchedules) {
		final String sql = getQuery("OFFENDER_IND_SCHEDULES_T1_OFFENDER_IND_SCHEDULES_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("eventId", offenderIndSchedules.getEventId()),
					new BeanPropertyRowMapper<OffenderIndSchedules>(OffenderIndSchedules.class));
		} catch (final Exception e) {
			logger.error("getOffenderIndSchedules", e);
			return null;
		}
	}

}
