package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.triggers.FarCsldCurrentAcctsTxnsT1Repository;

@Repository
public class FarCsldCurrentAcctsTxnsT1RepositoryImpl extends RepositoryBase
		implements FarCsldCurrentAcctsTxnsT1Repository {

	private static Logger logger = LogManager.getLogger(FarCsldCurrentAcctsTxnsT1RepositoryImpl.class);

	@Override
	public String getModuleName(Integer txnId) {
		final String sql = getQuery("GET_MODULE_NAME");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("txn_id"), String.class);
		} catch (Exception e) {
			logger.error("getModuleName :" + e);
		}
		return retVal;
	}

	@Override
	public Integer insertFarCsldCurrentAcctsTxns(final AccountCodes txns, final String vModuleName) {
		final String sql = getQuery("INSERT_INTO_FAR_CSLD_CURRENT_ACCTS_TXNS");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("CASELOAD_CURRENT_ACCOUNT_ID", txns.getCaseloadCurrentAccountId(), "CASELOAD_ID",
							txns.getCaseloadId(), "ACCOUNT_CODE", txns.getAccountCode(), "ACCOUNT_PERIOD_ID",
							txns.getAccountPeriodId(), "CURRENT_BALANCE", txns.getCurrentBalance(), "MODIFY_DATE",
							txns.getModifyDate(), "TXN_ID", txns.getTxnId(), "TXN_ENTRY_SEQ", txns.getTxnEntrySeq(),
							"GL_ENTRY_SEQ", txns.getGlEntrySeq(), "V_MODULE_NAME", vModuleName, "CREATEUSERID",
							txns.getCreateUserId()));
		} catch (Exception e) {
			logger.error("insertFarCsldCurrentAcctsTxns :" + e);
		}
		return retVal;
	}

}
