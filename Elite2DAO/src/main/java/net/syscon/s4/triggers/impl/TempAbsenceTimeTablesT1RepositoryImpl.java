package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.TempAbsenceTimeTables;
import net.syscon.s4.triggers.TempAbsenceTimeTablesT1Repository;

@Repository
public class TempAbsenceTimeTablesT1RepositoryImpl extends RepositoryBase implements TempAbsenceTimeTablesT1Repository {
	private static final Logger logger = LogManager.getLogger(TempAbsenceTimeTablesT1RepositoryImpl.class.getName());

	@Override
	public Long lvTempAbsSchId() {
		final String sql = getQuery("TEMP_ABSENCE_TIME_TABLES_T1_LV_TEMP_ABS_SCH_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("lvTempAbsSchId", e);
			return null;
		}
	}

	@Override
	public TempAbsenceTimeTables getTempAbsenceTimeTables(final TempAbsenceTimeTables tempAbsenceTimeTables) {
		final String sql = getQuery("TEMP_ABSENCE_TIME_TABLES_T1_GET");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":taId", tempAbsenceTimeTables.getTaId(), "taTtSeq",
							tempAbsenceTimeTables.getTaTtSeq()),
					new BeanPropertyRowMapper<TempAbsenceTimeTables>(TempAbsenceTimeTables.class));
		} catch (final Exception e) {
			logger.error("getTempAbsenceTimeTables", e);
			return null;
		}
	}

}
