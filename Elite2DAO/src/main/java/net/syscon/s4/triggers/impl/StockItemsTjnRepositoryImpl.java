package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.StockItemsJn;
import net.syscon.s4.triggers.StockItemsTjnRepository;

@Repository
public class StockItemsTjnRepositoryImpl extends RepositoryBase implements StockItemsTjnRepository {
	private static Logger logger = LogManager.getLogger(StockItemsTjnRepositoryImpl.class);

	@Override
	public Integer inserting(final StockItemsJn stockItemsJn) {
		final String sql = getQuery("STOCK_ITEMS_TJN_INSERTING");
		try {
			return namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(stockItemsJn));
		} catch (final Exception e) {
			logger.error("inserting", e);
			return null;
		}
	}

}
