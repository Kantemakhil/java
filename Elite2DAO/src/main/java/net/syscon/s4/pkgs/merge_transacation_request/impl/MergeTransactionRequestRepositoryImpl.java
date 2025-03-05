package net.syscon.s4.pkgs.merge_transacation_request.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.merge_transacation_request.MergeTransactionRequestRepository;
import net.syscon.s4.sa.admin.beans.MergeTransactions;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionBean;

@Repository
public class MergeTransactionRequestRepositoryImpl extends RepositoryBase implements MergeTransactionRequestRepository {

	private static Logger logger = LogManager.getLogger(MergeTransactionRequestRepositoryImpl.class.getName());

	@Override
	public Long getMergeTransactionsId() {
		final String sql = getQuery("MERGE_TRANSACTION_REQUEST_SEQ");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getMergeTransactionsId");
			return 0l;
		}
	}

	@Override
	public Integer insertMergeTransactions(MergeTransactions bean) {
		final String sql = getQuery("MERGE_TRANSACTION_REQUEST_MERGE_TRANSACTIONS");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(bean));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " insertMergeTransactions");
		}
		return result;
	}

	@Override
	public Integer setStatus(Long mergeTransactionId, String requestStatusCode) {
		final String sql = getQuery("MERGE_TRANSACTION_REQUEST_UPDATE_MERGE_TRANSACTIONS");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("mergeTransactionId", mergeTransactionId, "requestStatusCode", requestStatusCode));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " setStatus");
		}
		return result;
	}

	@Override
	public Integer updateTransaction(Long mergeTransactionId, Long fromOffenderBookId, Long fromRootOffenderId,
			Long fromOffenderId, String fromOffenderIdDisplay, String fromLastName, String fromfirstName,
			Long toOffenderBookId, Long toRootOffenderId, Long toOffenderId, String toOffenderIdDisplay,
			String toLastName, String tofirstName) {
		
		final String sql = getQuery("MERGE_TRANSACTION_REQUEST_UPDATE_TRANSACTIONS");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql,createParams("mergeTransactionId", mergeTransactionId, "fromOffenderBookId", fromOffenderBookId, "fromRootOffenderId", fromRootOffenderId, "fromOffenderId", fromOffenderId,
					"fromOffenderIdDisplay", fromOffenderIdDisplay, "fromLastName", fromLastName, "fromfirstName", fromfirstName, "toOffenderBookId", toOffenderBookId, "toRootOffenderId", toRootOffenderId, "toOffenderId", toOffenderId,
					"toOffenderIdDisplay", toOffenderIdDisplay, "toLastName", toLastName, "tofirstName", tofirstName));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method updateTransaction", e);
		}
		return result;
	}

	@Override
	public Integer insertMrgTransactions(MergeTransactionBean bean) {
		final String sql = getQuery("MERGE_TRANSACTION_REQUEST_INSERT_MRG_TRANSACTIONS");
		int result = 0;
		try {
			result = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(bean));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method insertMrgTransactions", e);
		}
		return result;
	}

}
