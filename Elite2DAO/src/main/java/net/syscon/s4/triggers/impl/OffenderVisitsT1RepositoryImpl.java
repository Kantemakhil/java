package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderVisitsT1Repository;

@Repository("OffenderVisitsT1RepositoryImpl")
public class OffenderVisitsT1RepositoryImpl extends RepositoryBase implements OffenderVisitsT1Repository {

	@Override
	public Integer getvNumrows(final String visitStatus) {
		final String sql = getQuery("GET_V_NUMROWS");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("VISIT_STATUS", visitStatus),
					Integer.class);
		} catch (Exception e) {
			count = 0;
		}
		return count;
	}

}
