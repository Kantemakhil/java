package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderCourseAttendances;
import net.syscon.s4.triggers.VOffenderCourseEventsTiRepository;

@Repository
public class VOffenderCourseEventsTiRepositoryImpl extends RepositoryBase implements VOffenderCourseEventsTiRepository {
	private final Logger logger = LogManager.getLogger(VOffenderCourseEventsTiRepositoryImpl.class);

	@Override
	public Integer insertOffenderCourseAttendances1(final OffenderCourseAttendances offenderCourseAttendances) {
		final String sql = getQuery("V_OFFENDER_COURSE_EVENTS_TI_OFFENDER_COURSE_ATTENDANCES1");
		int[] returnArray = new int[] {};
		if(offenderCourseAttendances!=null && offenderCourseAttendances.getEventId() == 0) {
			offenderCourseAttendances.setEventId(null);
		}
		if(offenderCourseAttendances!=null && (offenderCourseAttendances.getEventStatus().trim()).equalsIgnoreCase("")){
			offenderCourseAttendances.setEventStatus(null);
			
		}
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenderCourseAttendances));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0])); 
		} catch (final Exception e) {
			logger.error("insertOffenderCourseAttendances1", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer insertOffenderCourseAttendances2(final OffenderCourseAttendances offenderCourseAttendances) {
		final String sql = getQuery("V_OFFENDER_COURSE_EVENTS_TI_OFFENDER_COURSE_ATTENDANCES2");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenderCourseAttendances));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insertOffenderCourseAttendances2", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

}
