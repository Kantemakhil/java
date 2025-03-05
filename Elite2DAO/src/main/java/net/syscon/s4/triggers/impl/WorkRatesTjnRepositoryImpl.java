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
import net.syscon.s4.triggers.WorkRates;
import net.syscon.s4.triggers.WorkRatesTjnRepository;

@Repository
public class WorkRatesTjnRepositoryImpl extends RepositoryBase implements WorkRatesTjnRepository {
	private final Logger logger = LogManager.getLogger(WorkRatesTjnRepositoryImpl.class);

	@Override
	public Integer insertOrUpdateOrDelete(final WorkRates workRates) {
		final String sql = getQuery("WORK_RATES_JN_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(workRates));
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
	public WorkRates getWorkRates(final WorkRates workRates) {
		final String sql = getQuery("WORK_RATES_OLD");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":payRangeCode", workRates.getPayRangeCode(), ":unitCode", workRates.getUnitCode(),
							":compensationCode", workRates.getCompensationCode(), ":performanceCode",
							workRates.getPerformanceCode()),
					new BeanPropertyRowMapper<WorkRates>(WorkRates.class));
		} catch (final Exception e) {
			logger.error("getWorkRates", e);
			return null;
		}
	}

}
