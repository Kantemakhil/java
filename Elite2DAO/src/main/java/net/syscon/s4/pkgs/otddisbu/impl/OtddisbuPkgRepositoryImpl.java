package net.syscon.s4.pkgs.otddisbu.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.OtddisbuProcessTransactionsBean;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.otddisbu.OtddisbuPkgRepository;

@Repository
public class OtddisbuPkgRepositoryImpl extends RepositoryBase implements OtddisbuPkgRepository {

	private static Logger logger = LogManager.getLogger(OtddisbuPkgRepositoryImpl.class.getName());

	@Override
	public Long lOffenderBookCur(final Long offenderId, final String caseloadId) {
		final String sql = getQuery("L_OFFENDER_BOOK_CUR");
		Long retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_root_offender_id", offenderId, "p_caseload_id", caseloadId), Long.class);
		} catch (Exception e) {
			logger.error("lOffenderBookCur", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Long lOffenderInactCur(final Long offenderId, final String caseloadId) {
		final String sql = getQuery("L_OFFENDER_INACT_CUR");
		Long retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_root_offender_id", offenderId, "p_caseload_id", caseloadId), Long.class);
		} catch (Exception e) {
			logger.error("lOffenderInactCur", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public void insertOffenderTransactions(final OtddisbuProcessTransactionsBean proTxn) {
		final String sql = getQuery("INSERT_OFFENDER_TRANSACTIONS");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("P_TXN_ID", proTxn.getpTxnId());
		inParamMap.put("P_TXN_ENTRY_SEQ", proTxn.getpTxnEntrySeq());
		inParamMap.put("P_CASELOAD_ID", proTxn.getpCaseloadId());
		inParamMap.put("P_OFFENDER_ID", proTxn.getpOffenderId());

		inParamMap.put("L_OFFENDER_BOOK_ID", proTxn.getOffenderBookId());
		inParamMap.put("L_TXN_POSTING_TYPE", proTxn.getTxnPostType());
		inParamMap.put("P_TXN_TYPE", proTxn.getpTxnType());
		inParamMap.put("P_TXN_DESC", proTxn.getpTxnDesc());

		inParamMap.put("P_TXN_ENTRY_AMOUNT", proTxn.getpTxnEntryAmount());
		inParamMap.put("L_SUB_ACCOUNT_TYPE", proTxn.getSubActType());
		inParamMap.put("P_PAYEE_CORP_ID", proTxn.getpPayeeCorpId());
		inParamMap.put("P_PAYEE_PERSON_ID", proTxn.getpPayeePersonId());

		inParamMap.put("P_PAYEE_NAME_TEXT", proTxn.getpPayeeNameText());
		inParamMap.put("L_RECEIPT_NUMBER", proTxn.getpReceiptNum());
		inParamMap.put("P_TXN_REF_NUMBER", proTxn.getpTxnRefNumber());
		inParamMap.put("createUserId", proTxn.getCreateUserId());

		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("insertOffenderTransactions", e);
		}

	}

	@Override
	public Integer lChequeExistsCur(final Integer txnId) {
		final String sql = getQuery("L_CHEQUE_EXISTS_CUR");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_txn_id", txnId), Integer.class);
		} catch (Exception e) {
			logger.error("lChequeExistsCur", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public void updateBankChequeData(final Integer txnId, final BigDecimal txnEntryAmount, final String userName) {
		final String sql = getQuery("UPDATE_BANK_CHEQUE_DATA");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("P_TXN_ID", txnId);
		inParamMap.put("P_TXN_ENTRY_AMOUNT", txnEntryAmount);
		inParamMap.put("modifyUserId", userName);
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("updateBankChequeData", e);
		}

	}

}
