package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.AgencyLocationsT2Repository;

@Repository
public class AgencyLocationsT2RepositoryImpl extends RepositoryBase implements AgencyLocationsT2Repository {

	@Override
	public Integer addressRecords(final String agyLocId) {
		final String sql = getQuery("AGENCY_LOCATIONS_T2_ADDRESS_RECORDS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("agyLocId", agyLocId), Integer.class);
	}

	@Override
	public Integer phoneRecords(final String agyLocId) {
		final String sql = getQuery("AGENCY_LOCATIONS_T2_PHONE_RECORDS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("agyLocId", agyLocId), Integer.class);
	}

	@Override
	public Integer internetAddresses(final String agyLocId) {
		final String sql = getQuery("AGENCY_LOCATIONS_T2_INTERNET_ADDRESSES");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("agyLocId", agyLocId), Integer.class);
	}

}
