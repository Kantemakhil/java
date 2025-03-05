package net.syscon.s4.pkgs.ocdpatte.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.pkgs.ocdpatte.OcdpattePkgRepository;

@Repository
public class OcdpattePkgRepositoryImpl extends RepositoryBase implements OcdpattePkgRepository {

	private static Logger logger = LogManager.getLogger(OcdpattePkgRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PAY_FLAG", new FieldMapper("activeFlag"))
			.put("AUTHORISED_ABSENCE_FLAG", new FieldMapper("agencyLocationType")).build();

	@Override
	public List<CourseActivities> getActOutcomeFlag(final String eventType, final String outcomeCode, final Date eventDate) {
		final String sql = getQuery("GET_ACT_OUTCOME_FLAG");
		List<CourseActivities> retList = new ArrayList<CourseActivities>();

		final RowMapper<CourseActivities> rowMapper = Row2BeanRowMapper.makeMapping(sql, CourseActivities.class,
				mapping);
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("p_outcome_code", outcomeCode, "p_event_type", eventType, "p_event_date", eventDate),
					rowMapper);
		} catch (Exception e) {
			logger.error("getActOutcomeFlag", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public String getProviderType(final String agyLocId) {
		final String sql = getQuery("GET_PROVIDER_TYPE");
		String providerType = null;
		try {
			providerType = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_agy_loc_id", agyLocId),
					String.class);
		} catch (Exception e) {
			logger.error("getProviderType", e.getMessage());
		}
		return providerType;
	}

	@Override
	public String getPaylock(final Long eventId) {
		final String sql = getQuery("GET_PAY_LOCK");
		String paylock = null;
		try {
			paylock = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_event_id", eventId), String.class);
		} catch (Exception e) {
			logger.error("getPaylock", e.getMessage());
		}
		return paylock;
	}
}