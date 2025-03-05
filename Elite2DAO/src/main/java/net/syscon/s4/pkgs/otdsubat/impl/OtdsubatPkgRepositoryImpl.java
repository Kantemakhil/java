package net.syscon.s4.pkgs.otdsubat.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.otdsubat.OtdsubatPkgRepository;

@Repository
public class OtdsubatPkgRepositoryImpl extends RepositoryBase implements OtdsubatPkgRepository {

	private static Logger logger = LogManager.getLogger(OtdsubatPkgRepositoryImpl.class.getName());

	@Override
	public Long getOffBookIdProcTxn(final Long pOffenderId, final String pCaseloadId) {
		final String sql = getQuery("PROCESS_TRANSACTION_GET_OFFENDER_ID");
		Long offBookId = null;
		try {
			offBookId = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_ID", pOffenderId, "P_CASELOAD_ID", pCaseloadId), Long.class);
		} catch (Exception e) {
			logger.error("getOffBookIdProcTxn :", e);
		}
		return offBookId;
	}

	@Override
	public Integer updateOffenderTransactions(final Long pTxnId, final Integer pTxnEntrySeq, final String userName) {
		final String sql = getQuery("PROCESS_TRANSACTION_UP_OFF_TRANS");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("p_txn_id", pTxnId, "p_txn_entry_seq", pTxnEntrySeq, "modifyUserId", userName));
		} catch (Exception e) {
			logger.error("updateOffenderTransactions :", e);
		}
		return retVal;
	}
}