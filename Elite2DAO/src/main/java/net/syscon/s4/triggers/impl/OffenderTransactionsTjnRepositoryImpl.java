package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.triggers.OffenderTransactionsTjnRepository;

@Repository
public class OffenderTransactionsTjnRepositoryImpl extends RepositoryBase implements OffenderTransactionsTjnRepository {
	
	private static final Logger logger = LogManager.getLogger(OffenderTransactionsTjnRepositoryImpl.class);


	@Override
	public Integer insertOffenderTransactionsTjn(OffenderTransactions obj) {
		Integer retVal = null;
		final String sql = getQuery("OFFENDER_TRANSACTIONS_TJN_INSERT");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Error in Class" + this.getClass().getName() + "insertOffenderTransactionsTjn error ::" , e);
		}
		return retVal;
	}

	@Override
	public Integer updateOffenderTransactionsTjn(OffenderTransactions obj) {
		Integer retVal = null;
		final String sql = getQuery("OFFENDER_TRANSACTIONS_TJN_UPDATE");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Error in Class" + this.getClass().getName() + "updateOffenderTransactionsTjn error ::" , e);
		}
		return retVal;
	}

	@Override
	public Integer deleteOffenderTransactionsTjn(OffenderTransactions obj) {
		Integer retVal = null;
		final String sql = getQuery("OFFENDER_TRANSACTIONS_TJN_DELETE");
		try {
			retVal = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		} catch (Exception e) {
			logger.error("Error in Class" + this.getClass().getName() + "deleteOffenderTransactionsTjn error ::" , e);
		}
		return retVal;
	}

}
