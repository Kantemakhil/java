package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.LivingUnitProfiles;
import net.syscon.s4.triggers.LivingUnitProfilesTidRepository;

@Repository
public class LivingUnitProfilesTidRepositoryImpl extends RepositoryBase implements LivingUnitProfilesTidRepository {
	private  Logger logger = LogManager.getLogger(LivingUnitProfilesTidRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public List<LivingUnitProfiles> getLivingUnitProfiles(final LivingUnitProfiles livingUnitProfiles) {
		final String sql = getQuery("LIVING_UNIT_PROFILES_TID_LIVING_UNIT_PROFILES");
		final RowMapper<LivingUnitProfiles> staffMembersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				LivingUnitProfiles.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), staffMembersRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("getLivingUnitProfiles",e);
			return Collections.emptyList();
		}

	}

	@Override
	public Integer insert(final AgyIntLocProfiles agyIntLocProfiles) {
		final String sql = getQuery("LIVING_UNIT_PROFILES_TID_AGY_INT_LOC_PROFILES");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(agyIntLocProfiles));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer delete(final AgyIntLocProfiles agyIntLocProfiles) {
		final String sql = getQuery("LIVING_UNIT_PROFILES_TID_DELETE.QUERY_TEAM_TASKS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(agyIntLocProfiles));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (1 == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}
}
