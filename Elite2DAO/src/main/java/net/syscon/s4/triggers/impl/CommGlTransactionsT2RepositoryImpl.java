package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.CommGlTransactions;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.TagExceptions;
import net.syscon.s4.triggers.CaseloadCurrentAccountsTxns;
import net.syscon.s4.triggers.CommGlTransactionsT2Repository;

@Repository
public class CommGlTransactionsT2RepositoryImpl extends RepositoryBase implements CommGlTransactionsT2Repository {

	
	Logger logger = LogManager.getLogger(CommGlTransactionsT2RepositoryImpl.class);

	final DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public CommGlTransactions getCommGlTransactions(final BigDecimal taxId) {
		final String sql = getQuery("GET_COMM_GL_TRANSACTIONS");
		CommGlTransactions retObj = new CommGlTransactions();
		try {
			retObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("TXN_ID", taxId),
					CommGlTransactions.class);
		} catch (Exception e) {
			logger.error("getCommGlTransactions", e);
			retObj = null;
		}
		return retObj;
	}
	
	@Override
	public Integer commCaseloadAcSummariesCount(final String caseloadId, final Integer accountCode, final Date txnEntryDate)
			throws Exception {
		final String sql = getQuery("COMM_CASELOAD_AC_SUMMARIES_COUNT");
		Integer result = 0;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("caseload_id", caseloadId,
					"account_code", accountCode, ":txn_entry_date", sdf.format(txnEntryDate)), Integer.class);
		}catch (final EmptyResultDataAccessException e) {
				logger.error("commCaseloadAcSummariesCount", e);
				return 0;
			} catch (final Exception e) {
			logger.error("Account Code not found in Caseload Account Summaries for account " + accountCode
					+ "  and caseload " + caseloadId, e);
			throw new Exception("Account Code not found in Caseload Account Summaries for account " + accountCode
					+ "  and caseload " + caseloadId, e);
		}
		return result;
	}

	@Override
	public String getTxnPostingType(final Integer accountCode) throws Exception {
		final String sql = getQuery("GET_TXN_POSTING_TYPE");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("account_code", accountCode),
					String.class);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("getTxnPostingType", e);
			return null;
		} catch (final IncorrectResultSizeDataAccessException e) {
			return "twoManyRows";
		} catch (final Exception e) {
			logger.error("getTxnPostingType", e);
			throw new Exception("Error: GL_TRANSACTIONS_T2.  " + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Integer getAccountPeriodId(final Date txnEntryDate) throws Exception {
		final String sql = getQuery("GET_ACCOUNT_PERIOD_ID");
		Integer result = 0;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("txn_entry_date", sdf.format(txnEntryDate)), Integer.class);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("getAccountPeriodId", e);
			return 0;
		} catch (final Exception e) {
			logger.error("getAccountPeriodId", e);
			logger.error("RAISE_APPLICATION_ERROR(-20009, 'Error: GL_TRANSACTIONS_T2. '||SQLERRM)" + e.getMessage());
			throw new Exception("Error: GL_TRANSACTIONS_T2.  " + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Integer getComCsldCurrentAccountId() throws Exception {
		final String sql = getQuery("GET_COM_CSLD_CURRENT_ACCOUNT_ID");
		Integer result = 0;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (final EmptyResultDataAccessException e) {
			final TagExceptions tagExceptions = new TagExceptions();
			logger.error("getComCsldCurrentAccountId", e);
			result = null;
			throw new Exception("Error: Caseload Current Account ID Sequence Not Found.", e);
		} catch (final Exception e) {
			final TagExceptions tagExceptions = new TagExceptions();
			logger.error("acntId", e);
			result = null;
			throw new Exception("'Error: GL_TRANSACTIONS_T2. " + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Integer insertComCsldCurrentAccountsTxns(final List<CaseloadCurrentAccountsTxns> caseCurAccTxList) throws Exception {
		Integer returnValue = 0;
		final String sql = getQuery("INSERT_COM_CSLD_CURRENT_ACCOUNTS_TXNS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CaseloadCurrentAccountsTxns obj : caseCurAccTxList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (caseCurAccTxList.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			logger.error("insertComCsldCurrentAccountsTxns", e);
			throw new Exception("Error: Cannot insert into caseload current account txns for account" 
			+ caseCurAccTxList.get(0).getAccountCode()+" and caseload "+ caseCurAccTxList.get(0).getCaseloadId()+" .",
					e);
		}
		return returnValue;

	}
}
