package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.VCoursePhases;
import net.syscon.s4.triggers.VCoursePhasesTiRepository;
@Repository
public class VCoursePhasesTiRepositoryImpl extends RepositoryBase implements VCoursePhasesTiRepository {
	private final Logger logger = LogManager.getLogger(VCoursePhasesTiRepositoryImpl.class.getName());

	@Override
	public Integer save(final VCoursePhases vCoursePhases) {
		final String sql = getQuery("V_COURSE_PHASES_TI_V_COURSE_PHASES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vCoursePhases));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("save", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

}
