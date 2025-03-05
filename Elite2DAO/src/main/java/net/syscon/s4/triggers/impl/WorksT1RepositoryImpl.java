package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.WorksT1Repository;

@Repository
public class WorksT1RepositoryImpl extends RepositoryBase implements WorksT1Repository {

	@Override
	public Long getNextWorkId() {
		final String sql = getQuery("GET_NEXT_WORK_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

}
