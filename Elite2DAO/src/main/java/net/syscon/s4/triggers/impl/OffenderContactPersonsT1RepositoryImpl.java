package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderContactPersonsT1Repository;
@Repository
public class OffenderContactPersonsT1RepositoryImpl extends RepositoryBase implements OffenderContactPersonsT1Repository {
	
	@Override
	public Long personSequence() {
		final String sql=getQuery("PERSON_SEQUENCE");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
	}

}
