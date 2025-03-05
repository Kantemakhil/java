package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OffenderOicSanctions;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderOicSanctionsT1Repository;
@Repository
public class OffenderOicSanctionsT1RepositoryImpl extends RepositoryBase implements OffenderOicSanctionsT1Repository {
	
	@Override
	public Integer getValue(final OffenderOicSanctions off) {
		final String sql=getQuery("SELECT_QUERY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_book_id",off.getOffenderBookId(),"oic_incident_id",off.getOicIncidentId()), Integer.class);
	}

}
