package net.syscon.s4.triggers.impl;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.StaffMembersT1Repository;
@Repository
public class StaffMembersT1RepositoryImpl extends RepositoryBase implements StaffMembersT1Repository {

	@Override
	public Integer getCountAddresses(Integer staffId) {
		final String sql = getQuery("GET_COUNT_ADDRESSES");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("STAFF_ID", staffId),
				Integer.class);
	}
	
	@Override
	public Integer getCountPhones(Integer staffId) {
		final String sql = getQuery("GET_COUNT_PHONES");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("STAFF_ID", staffId),
				Integer.class);
	}
	
	@Override
	public Integer getCountInternetAddresses(Integer staffId) {
		final String sql = getQuery("GET_COUNT_INTERNET_ADDRESSES");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("STAFF_ID", staffId),
				Integer.class);
	}

}
