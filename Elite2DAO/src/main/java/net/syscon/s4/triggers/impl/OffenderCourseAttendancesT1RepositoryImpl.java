package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.triggers.OffenderCourseAttendancesT1Repository;

@Repository
public class OffenderCourseAttendancesT1RepositoryImpl extends RepositoryBase implements OffenderCourseAttendancesT1Repository {
	private static final Logger logger = LogManager.getLogger(OffenderCourseAttendancesT1RepositoryImpl.class);

	@Override
	public VOffenderCourseAttendances getOffenderCourseAttendances(final Long eventId) { 
		final String sql = getQuery("GET_OFFENDER_COURSE_ATTENDANCES_RECORDS");
		VOffenderCourseAttendances retObj = new VOffenderCourseAttendances();
		try { 
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("EVENT_ID", eventId),
					new BeanPropertyRowMapper<VOffenderCourseAttendances> (VOffenderCourseAttendances.class));
		} catch (Exception e) {
			logger.error("getOffenderCourseAttendances", e);
			retObj = null;
		}
		return retObj;
	}

}
