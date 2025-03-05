package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.triggers.OffenderIndSchedulesT3Repository;

@Repository
public class OffenderIndSchedulesT3RepositoryImpl extends RepositoryBase implements OffenderIndSchedulesT3Repository {

	@Override
	public Integer getVnumRows(OffenderIndSchedules object) {
		final String sql = getQuery("OFFENDER_IND_SCHEDULE_T3_GET_V_NUM");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("EVENT_TYPE", object.getMovementType(),
				"REFERENCE_ID", object.getReferenceId(), "OFFENDER_BOOK_ID", object.getOffenderBookId()), Integer.class);
	}

}
