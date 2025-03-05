package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.CourseSchedulesT1Repository;

@Repository
public class CourseSchedulesT1RepositoryImpl extends RepositoryBase implements CourseSchedulesT1Repository {
	private static Logger logger = LogManager.getLogger(CourseSchedulesT1RepositoryImpl.class);

	@Override
	public Long lCrsSchIdNextval() {
		final String sql = getQuery("COURSE_SCHEDULES_T1_L_CRS_SCH_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("lCrsSchIdNextval: " + e);
			return null;
		}
	}
}
