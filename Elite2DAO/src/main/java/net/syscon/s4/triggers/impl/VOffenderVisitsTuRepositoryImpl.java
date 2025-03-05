package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;
import net.syscon.s4.triggers.VOffenderVisitsTuRepository;

@Repository
public class VOffenderVisitsTuRepositoryImpl extends RepositoryBase implements VOffenderVisitsTuRepository {
	private final Logger logger = LogManager.getLogger(VOffenderVisitsTuRepositoryImpl.class);
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public List<VOffenderVisits> getVOffenderVisits() {
		final String sql = getQuery("V_OFFENDER_VISITS_TU_V_OFFENDER_VISITS");
		final RowMapper<VOffenderVisits> vOffenderVisitsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderVisits.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), vOffenderVisitsRowMapper);
		} catch (final Exception e) {
			logger.error("getVOffenderVisits : ", e);
			return Collections.emptyList();
		}
	}

	@Override
	public Integer lCount(final BigDecimal offenderVisitId) {
		final String sql = getQuery("V_OFFENDER_VISITS_TU_L_COUNT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(":offenderVisitId", offenderVisitId),
					Integer.class);
		} catch (final Exception e) {
			logger.error("lCount :" + e);
			return 0;
		}
	}

	@Override
	public Integer insertOffenderVisits(final VOffenderVisits vOffenderVisits) {
		final String sql = getQuery("V_OFFENDER_VISITS_TU_OFFENDER_VISITS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vOffenderVisits));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insertOffenderVisits", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer insertOffenderVisitVisitors(final VOffenderVisits vOffenderVisits) {
		final String sql = getQuery("V_OFFENDER_VISITS_TU_OFFENDER_VISIT_VISITORS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vOffenderVisits));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insertOffenderVisitVisitors", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer updateOffenderVisits(VOffenderVisits vOffenderVisits) {
		final String sql = getQuery("V_OFFENDER_VISITS_TU_OFFENDER_VISITS_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vOffenderVisits));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insertOffenderVisitVisitors", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer updateOffenderVisitVisitors(VOffenderVisits vOffenderVisits) {
		final String sql = getQuery("V_OFFENDER_VISITS_TU_OFFENDER_VISIT_VISITORS_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vOffenderVisits));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insertOffenderVisitVisitors", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}

	}

}
