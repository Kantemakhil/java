package net.syscon.s4.triggers.impl;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.SystemProfilesTjnRepository;

@Repository
public class SystemProfilesTjnRepositoryImpl extends RepositoryBase implements SystemProfilesTjnRepository {

	@Override
	public Integer insert(final SystemProfiles newprofile) {
		final String sql = getQuery("INSERT_QUERY");
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(newprofile));
	}

	@Override
	public Integer update(final SystemProfiles newprofile) {
		final String sql = getQuery("UPDATE_QUERY");
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(newprofile));
	}
	
	
	@Override
	public Integer delete(final SystemProfiles old) {
		final String sql = getQuery("DELETE_QUERY");
		return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(old));
	}
}
