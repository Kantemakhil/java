package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.VCourseModules;
import net.syscon.s4.triggers.VCourseModulesTiRepository;

@Repository
public class VCourseModulesTiRepositoryImpl extends RepositoryBase implements VCourseModulesTiRepository {
	private final Logger logger = LogManager.getLogger(VCourseModulesTiRepositoryImpl.class.getName());

	@Override
	public Integer insert(final VCourseModules vCourseModules) {
		final String sql = getQuery("V_COURSE_MODULES_TI_COURSE_ACTIVITIES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vCourseModules));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insert", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}
}
