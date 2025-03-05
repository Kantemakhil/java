package net.syscon.s4.pkgs.pims_file_tracking.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.legalorders.OffenderFileTransactions;
import net.syscon.s4.pkgs.pims_file_tracking.PimsFileTrackingRepository;

@Repository
public class PimsFileTrackingRepositoryImpl extends RepositoryBase implements PimsFileTrackingRepository {

	private static Logger logger = LogManager.getLogger(PimsFileTrackingRepositoryImpl.class.getName());

	@Override
	public OffenderFileTransactions tranCur(final Long offenderFileSeq, final Long offenderId) {
		final String sql = getQuery("SELECT_TRANSACTIONS");
		OffenderFileTransactions offFileTran = new OffenderFileTransactions();
		try {
			offFileTran = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_offender_file_seq", offenderFileSeq, "p_offender_id", offenderId),
					OffenderFileTransactions.class);
		} catch (Exception e) {
			logger.error("tranCur" + e);
		}
		return offFileTran;
	}

	@Override
	public Integer updateOffenderFileTransaction(final Long transactionId, final String userName) {
		final String sql = getQuery("UPDATE_OFFENDER_FILE_TRANSACTIO");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.update(sql,
					createParams("transaction_id", transactionId, "modifyUserId", userName));
		} catch (DataAccessException e) {
			logger.error("updateOffenderFileTransaction" + e);
			count = 0;
		}
		return count;
	}

	@Override
	public Integer insertTrans(final OffenderFileTransactions bean) {
		final String sql = getQuery("INSERT_OFFENDER_FILE_TRANSACTIONS");
		Integer count = null;
		final OffenderFileTransactions offFileTrans = new OffenderFileTransactions();
		try {
			count = namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(offFileTrans));
		} catch (DataAccessException e) {
			logger.error("updateOffenderFileTransaction" + e);
			count = 0;
		}
		return count;
	}

	@Override
	public Integer getMaxOffenderFileSeq(final Integer pOffenderFileSeq, final Long pOffenderId) {
		Integer returnOffFileSeq = null;
		final String sql = getQuery("TRANSFER_FILE_GET_MAX_OFFENDER_FILE_SEQ");
		try {
			returnOffFileSeq = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_FILE_SEQ", pOffenderFileSeq, "P_OFFENDER_ID", pOffenderId), Integer.class);
		} catch (Exception e) {
			logger.error("getMaxOffenderFileSeq :", e);
		}
		return returnOffFileSeq;
	}

	@Override
	public String getTranCur(final Long pOffenderId, final Integer pOffenderFileSeq) {
		final String sql = getQuery("TRANSFER_FILE_TRAN_CUR");
		String retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("P_OFFENDER_ID", pOffenderId, "P_OFFENDER_FILE_SEQ", pOffenderFileSeq), String.class);
		} catch (Exception e) {
			logger.error("getTranCur :", e);
		}
		return retVal;
	}

	@Override
	public Integer transferFileInsertOffenderFileTransactionsOne(final Integer pOffenderFileSeq, final Long pOffenderId,
			final String pAgyLocIdFrom, final String pAgyLocIdTo, final Long pStaffIdFrom, final Long pStaffIdTo,
			final String pFileTransComment, final String userName) {
		final String sql = getQuery("TRANSFER_FILE_OFFENDER_FILE_TRANSACTIONS_ONE");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("p_offender_file_seq", pOffenderFileSeq, "p_offender_id", pOffenderId,
							"p_agy_loc_id_from", pAgyLocIdFrom, "p_agy_loc_id_to", pAgyLocIdTo, "p_staff_id_from",
							pStaffIdFrom, "p_staff_id_to", pStaffIdTo, "p_file_trans_comment", pFileTransComment,
							"createUserId", userName));
		} catch (Exception e) {
			logger.error("transferFileInsertOffenderFileTransactions :", e);
		}
		return retVal;
	}

	@Override
	public Integer transferFileInsertOffenderFileTransactionsTwo(final Integer pOffenderFileSeq, final Long pOffenderId,
			final String lvConfirmed, final String pAgyLocIdFrom, final String pAgyLocIdTo, final Long pStaffIdFrom,
			final String pNonOfficerTo, final String pFileTransComment, final String userName) {
		final String sql = getQuery("TRANSFER_FILE_OFFENDER_FILE_TRANSACTIONS_SECOND");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("p_offender_file_seq", pOffenderFileSeq, "p_offender_id", pOffenderId, "lv_confirmed",
							lvConfirmed, "p_agy_loc_id_from", pAgyLocIdFrom, "p_agy_loc_id_to", pAgyLocIdTo,
							"p_staff_id_from", pStaffIdFrom, "p_non_officer_to", pNonOfficerTo, "p_file_trans_comment",
							pFileTransComment, "createUserId", userName));
		} catch (Exception e) {
			logger.error("transferFileInsertOffenderFileTransactionsTwo :", e);
		}
		return retVal;
	}

	@Override
	public Integer transferFileInsertOffenderFileTransactionsThree(final Integer pOffenderFileSeq,
			final Long pOffenderId, final String pAgyLocIdFrom, final String pAgyLocIdTo, final Long pStaffIdTo,
			final String pNonOfficerFrom, final String pFileTransComment, final String userName) {
		final String sql = getQuery("TRANSFER_FILE_OFFENDER_FILE_TRANSACTIONS_THREE");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("p_offender_file_seq", pOffenderFileSeq, "p_offender_id", pOffenderId,
							"p_agy_loc_id_from", pAgyLocIdFrom, "p_agy_loc_id_to", pAgyLocIdTo, "p_staff_id_to",
							pStaffIdTo, "p_non_officer_from", pNonOfficerFrom, "p_file_trans_comment",
							pFileTransComment, "createUserId", userName));
		} catch (Exception e) {
			logger.error("transferFileInsertOffenderFileTransactionsThree :", e);
		}
		return retVal;
	}

	@Override
	public Integer transferFileInsertOffenderFileTransactionsFour(final Integer pOffenderFileSeq,
			final Long pOffenderId, final String lvConfirmed, final String pAgyLocIdFrom, final String pAgyLocIdTo,
			final String pNonOfficerFrom, final String pNonOfficerTo, final String pFileTransComment,
			final String userName) {
		final String sql = getQuery("TRANSFER_FILE_OFFENDER_FILE_TRANSACTIONS_FOUR");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("p_offender_file_seq", pOffenderFileSeq, "p_offender_id", pOffenderId, "lv_confirmed",
							lvConfirmed, "p_agy_loc_id_from", pAgyLocIdFrom, "p_agy_loc_id_to", pAgyLocIdTo,
							"p_non_officer_from", pNonOfficerFrom, "p_non_officer_to", pNonOfficerTo,
							"p_file_trans_comment", pFileTransComment, "createUserId", userName));
		} catch (Exception e) {
			logger.error("transferFileInsertOffenderFileTransactionsFour :", e);
		}
		return retVal;
	}

}