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
import net.syscon.s4.triggers.StockLocations;
import net.syscon.s4.triggers.StockLocationsTjnRepository;

@Repository
public class StockLocationsTjnRepositoryImpl extends RepositoryBase implements StockLocationsTjnRepository {
	private static Logger logger = LogManager.getLogger(StockLocationsTjnRepositoryImpl.class.getName());

	@Override
	public StockLocations getStockLocations(final StockLocations stockLocations) {
		final String sql = getQuery("STOCK_LOCATIONS_TJN_STOCK_LOCATIONS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams(":caseloadId", stockLocations.getCaseloadId(), ":stockLocId",
							stockLocations.getStockLocId()),
					new BeanPropertyRowMapper<StockLocations>(StockLocations.class));
		} catch (final Exception e) {
			logger.error("getStockLocations", e);
			return null;
		}
	}

	@Override
	public Integer insert(final StockLocations stockLocations) {
		final String sql = getQuery("STOCK_LOCATIONS_TJN_INS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(stockLocations));
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
	public Integer update(final StockLocations stockLocations) {
		final String sql = getQuery("STOCK_LOCATIONS_TJN_UPD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(stockLocations));
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
	public Integer delete(final StockLocations stockLocations) {
		final String sql = getQuery("STOCK_LOCATIONS_TJN_DEL");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(stockLocations));
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

}
