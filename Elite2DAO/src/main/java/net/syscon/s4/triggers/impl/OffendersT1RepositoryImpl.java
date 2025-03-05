package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffendersT1Repository;
@Repository
public class OffendersT1RepositoryImpl extends RepositoryBase implements OffendersT1Repository {

	@Override
	public Integer gettingAddressCount(Long offenderId) {
		final String sql = getQuery("OFFENDERS_T1_COUNT_ADDRESS_RECORDS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offENDer_id", offenderId), Integer.class);
	}

	@Override
	 public Integer gettingPhoneCount(Long offenderId) {
		final String sql = getQuery("OFFENDERS_T1_COUNT_PHONE_RECORDS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offENDer_id", offenderId), Integer.class);
	}

	@Override
	public Integer gettingInternetAddressCount(Long offenderId) {
		final String sql = getQuery("OFFENDERS_T1_COUNT_INTERNET_ADDRESSES");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offENDer_id", offenderId), Integer.class);
	}

}
