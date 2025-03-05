package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.Orders;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OrdersTjnRepository;

@Repository
public class OrdersTjnRepositoryImpl extends RepositoryBase implements OrdersTjnRepository {

	private static final Logger logger = LogManager.getLogger(OrdersTjnRepositoryImpl.class);

	@Override
	public Integer insertOrdersTjn(final Orders obj) {
		Integer retVal = null;
		final String sql = getQuery("ORDERS_TJN_INSERT");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("insertOrdersTjn :" + e);
		}
		return retVal;
	}

	@Override
	public Integer updateOrdersTjn(final Orders obj) {
		final String sql = getQuery("ORDERS_TJN_UPDATE");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("updateOrdersTjn :" + e);
		}
		return retVal;
	}

	@Override
	public Integer deleteOrdersTjn(final Orders obj) {
		final String sql = getQuery("ORDERS_TJN_DELETE");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("deleteOrdersTjn :" + e);
		}
		return retVal;
	}

}
