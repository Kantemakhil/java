package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderCourseAttendances;
import net.syscon.s4.triggers.OffenderCourseAttndnsTwfRepository;

@Repository
public class OffenderCourseAttndnsTwfRepositoryImpl extends RepositoryBase
		implements OffenderCourseAttndnsTwfRepository {
	private static Logger logger = LogManager.getLogger(OffenderCourseAttndnsTwfRepositoryImpl.class);

	@Override
	public OffenderCourseAttendances getOffenderCourseAttendances(final Long eventId) {
		final String sql = getQuery("OFFENDER_COURSE_ATTNDNS_TWF_OFFENDER_COURSE_ATTENDANCES");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("eventId", eventId),
					new BeanPropertyRowMapper<OffenderCourseAttendances>(OffenderCourseAttendances.class));
		} catch (final Exception e) {
			logger.error("getOffenderCourseAttendances", e);
			return null;
		}
	}

}
