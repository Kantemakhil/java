package net.syscon.s4.pkgs.transfer_booking_trust.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.pkgs.merge_process.impl.MergeProcessRepositoryImpl;
import net.syscon.s4.pkgs.transfer_booking_trust.TransferBookingTrustRepository;

@Repository
public class TransferBookingTrustRepositoryImpl extends RepositoryBase implements TransferBookingTrustRepository {

	private static Logger logger = LogManager.getLogger(MergeProcessRepositoryImpl.class.getName());

	@Override
	public Date getBeginDate(Long pMergeTransactionId, String pProcessName) {
		final String sql = getQuery("TRANSFER_BOOKING_TRUST_GET_BEGIN_DATE");
		Date beginDate = null;
		try {
			beginDate = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pMergeTransactionId", pMergeTransactionId, "pProcessName", pProcessName), Date.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getBeginDate", e);
		}
		return beginDate;
	}
	
	@Override
	public Date getEndDate(Long pMergeTransactionId, String pProcessName) {
		final String sql = getQuery("TRANSFER_BOOKING_TRUST_GET_END_DATE");
		Date beginDate = null;
		try {
			beginDate = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pMergeTransactionId", pMergeTransactionId, "pProcessName", pProcessName), Date.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getEndDate", e);
		}
		return beginDate;
	}

	@Override
	public BigDecimal getPostedOffTxn(Long pOffenderId, String pCaseLoadId, Long pSubAcctCode) {
		final String sql = getQuery("TRANSFER_BOOKING_TRUST_GET_POSTED_OFF_TXN");
		BigDecimal bal = null;
		try {
			bal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pOffenderId", pOffenderId, "pCaseLoadId", pCaseLoadId, "pSubAcctCode", pSubAcctCode), BigDecimal.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getPostedOffTxn", e);
		}
		return bal;
	}

	@Override
	public List<OffenderSubAccounts> getCurrSubAccountBal(Long pOffenderId) {
		final String sql = getQuery("TRANSFER_BOOKING_TRUST_GET_CURR_SUB_ACCOUNT_BAL");
		List<OffenderSubAccounts> offAcc = new ArrayList<>();
		try {
			offAcc = namedParameterJdbcTemplate.query(sql, createParams("pOffenderId", pOffenderId),
					new BeanPropertyRowMapper<OffenderSubAccounts>(OffenderSubAccounts.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getCurrSubAccountBal", e);
		}
		return offAcc;
	}

	@Override
	public Integer deductionTxnWithinTime(Long pRootOffenderId, Date leftDateTime, Date rightDateTime) {
		/// CHECK QUERY
		final String sql = getQuery("TRANSFER_BOOKING_TRUST_DEDUCTION_TXN_WITHIN_TIME");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pRootOffenderId", pRootOffenderId, "leftDateTime", leftDateTime, "rightDateTime", rightDateTime), Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deductionTxnWithinTime", e);
		}
		return count;
	}

	@Override
	public Integer deductionTxnOutofTime(Long pRootOffenderId, Date leftDateTime, Date rightDateTime) {
		final String sql = getQuery("TRANSFER_BOOKING_TRUST_DEDUCTION_TXN_OUTOF_TIME");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pRootOffenderId", pRootOffenderId, "leftDateTime", leftDateTime, "rightDateTime", rightDateTime), Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method deductionTxnOutofTime", e);
		}
		return count;
	}

}
