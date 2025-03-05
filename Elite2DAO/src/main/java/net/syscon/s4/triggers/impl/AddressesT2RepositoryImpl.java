package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.AddressesT2Repository;

@Repository
public class AddressesT2RepositoryImpl extends RepositoryBase implements AddressesT2Repository {

	@Override
	public Integer phoneRecordsExists(final Long addressId) {
		final String sql = getQuery("ADDRESEST2_PHONE_RECORDS_EXISTS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("addressId", addressId), Integer.class);
	}

	@Override
	public Integer internetAddrRecordsExists(final Long addressId) {
		final String sql = getQuery("ADDRESEST2_INTERNET_ADDRESSES_RECORDS_EXISTS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("addressId", addressId), Integer.class);
	}

}
