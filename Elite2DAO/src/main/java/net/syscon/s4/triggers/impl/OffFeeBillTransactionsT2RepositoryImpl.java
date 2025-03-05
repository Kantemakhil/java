package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffFeeBillTransactionsT2Repository;

@Repository
public class OffFeeBillTransactionsT2RepositoryImpl extends RepositoryBase implements OffFeeBillTransactionsT2Repository {
	
	private static Logger logger = LogManager.getLogger(OffFeeBillTransactionsT2RepositoryImpl.class.getName());
	@Override
	public String getoffFeeBillTransactionsT2(String billTxnType) {
		final String sql = getQuery("OFF_FEE_BILL_TRANSACTIONS_T2_GET_TXN_USAGE");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("BILL_TXN_TYPE", billTxnType), String.class);
		} catch (Exception e) {
			logger.error("getoffFeeBillTransactionsT2 :" , e);
		}
		return retVal;
	}

}
