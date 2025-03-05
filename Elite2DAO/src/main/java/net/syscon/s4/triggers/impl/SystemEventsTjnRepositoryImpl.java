package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.of.payroll.maintenance.SystemEvents;
import net.syscon.s4.triggers.SystemEventsTjnRepository;

@Repository
public class SystemEventsTjnRepositoryImpl extends RepositoryBase implements SystemEventsTjnRepository {
	private static Logger logger = LogManager.getLogger(SystemEventsTjnRepositoryImpl.class.getName());

	@Override
	public SystemEvents getSystemEvents(final SystemEvents systemEvents) {
		final String sql = getQuery("SYSTEM_EVENTS_TJN_SYSTEM_EVENTS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":systemEventId", systemEvents.getSystemEventId()),
					new BeanPropertyRowMapper<SystemEvents>(SystemEvents.class));
		} catch (final Exception e) {
			logger.error("getSystemEvents", e);
			return null;
		}
	}

	@Override
	public Integer insert(final SystemEvents systemEvents) {
		final String sql = getQuery("SYSTEM_EVENTS_TJN_INS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(systemEvents));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insert", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer update(final SystemEvents systemEvents) {
		final String sql = getQuery("SYSTEM_EVENTS_TJN_UPD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(systemEvents));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("update", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer delete(final SystemEvents systemEvents) {
		final String sql = getQuery("SYSTEM_EVENTS_TJN_DEL");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(systemEvents));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("delete", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

}
