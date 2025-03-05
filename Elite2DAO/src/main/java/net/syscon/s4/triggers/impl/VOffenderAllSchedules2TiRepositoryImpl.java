package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.triggers.VOffenderAllSchedules2TiRepository;

@Repository
public class VOffenderAllSchedules2TiRepositoryImpl extends RepositoryBase
		implements VOffenderAllSchedules2TiRepository {
	private static Logger logger = LogManager.getLogger(VOffenderAllSchedules2TiRepositoryImpl.class);

	@Override
	public Integer insert(final OffenderIndSchedules offenderIndSchedules) {
		final String sql = getQuery("V_OFFENDER_ALL_SCHEDULES_2_TI_OFFENDER_IND_SCHEDULES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenderIndSchedules));
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
