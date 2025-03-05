package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.CorporatesT1Repository;
@Repository
public class CorporatesT1RepositoryImpl extends RepositoryBase implements CorporatesT1Repository {

	@Override
	public Integer gettingAddressCount(BigDecimal corporateId) {
		
		final String sql = getQuery("CORPORATES_T1_COUNT_ADDRESS_RECORDS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("corporate_id", corporateId), Integer.class);
	}

	@Override
	public Integer gettingPhoneCount(BigDecimal corporateId) {
		final String sql = getQuery("CORPORATES_T1_COUNT_PHONE_RECORDS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("corporate_id", corporateId), Integer.class);
	}

	@Override
	public Integer gettingInternetAddressCount(BigDecimal corporateId) {
		final String sql = getQuery("CORPORATES_T1_COUNT_INTERNET_ADDRESSES");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("corporate_id", corporateId), Integer.class);
	}
	


}
