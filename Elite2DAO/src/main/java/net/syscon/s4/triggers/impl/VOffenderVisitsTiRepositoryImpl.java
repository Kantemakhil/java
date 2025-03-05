package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;
import net.syscon.s4.triggers.VOffenderVisitsTiRepository;

@Repository
public class VOffenderVisitsTiRepositoryImpl extends RepositoryBase implements VOffenderVisitsTiRepository {
	private final Logger logger = LogManager.getLogger(VOffenderVisitsTiRepositoryImpl.class);

	@Override
	public Integer lCount(final BigDecimal offenderVisitId) {
		final String sql = getQuery("V_OFFENDER_VISITS_TI_L_COUNT");
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
		final String sql = getQuery("V_OFFENDER_VISITS_TI_OFFENDER_VISITS_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vOffenderVisits));
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
	public Integer insertOffenderVisitVisitors(final VOffenderVisits vOffenderVisits) {
		final String sql = getQuery("V_OFFENDER_VISITS_TI_OFFENDER_VISIT_VISITORS_INSERT");
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
